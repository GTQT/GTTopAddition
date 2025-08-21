package keqing.gttopaddition.element;

import gregtech.api.util.TextFormattingUtil;
import gregtech.client.utils.RenderUtil;
import io.netty.buffer.ByteBuf;
import keqing.gttopaddition.integration.GTTAIntegration;
import mcjty.theoneprobe.api.IElement;
import mcjty.theoneprobe.network.NetworkTools;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class FluidStackElement implements IElement {

    private final String location;
    private final int color;

    private final int amount;
    private TextureAtlasSprite sprite = null;

    public FluidStackElement(@Nonnull FluidStack stack) {
        this(stack.getFluid().getStill(stack), stack.getFluid().getColor(stack), stack.amount);
    }

    public FluidStackElement(@Nonnull ResourceLocation location, int color, int amount) {
        this.location = location.toString();
        this.color = color;
        this.amount = amount;
    }

    public FluidStackElement(@Nonnull ByteBuf buf) {
        this.location = NetworkTools.readStringUTF8(buf);
        this.color = buf.readInt();
        this.amount = buf.readInt();
    }

    @Override
    public void render(int x, int y) {
        String actualLocation = location;

        // Gregtech fluids added by GRS do this for some reason
        if (location.contains("material_sets/fluid/") && (location.contains("/gas") || location.contains("/plasma"))) {
            actualLocation = location.replace("material_sets/fluid/", "material_sets/dull/");
        }

        if (sprite == null) {
            sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(actualLocation);
        }

        GlStateManager.enableBlend();
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        RenderUtil.setGlColorFromInt(color, 0xFF);
        RenderUtil.drawFluidTexture(x, y, sprite, 0, 0, 0);

        if (amount > 0) {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5, 0.5, 1);
            Minecraft minecraft = Minecraft.getMinecraft();
            String format = TextFormattingUtil.formatLongToCompactString(amount) + "L";
            minecraft.fontRenderer.drawStringWithShadow(
                    format,
                    (x + (16 / 3F)) * 2 - minecraft.fontRenderer.getStringWidth(format) + 21,
                    (y + (16 / 3F) + 6) * 2,
                    0xFFFFFF
            );
            GlStateManager.popMatrix();
        }
        GlStateManager.disableBlend();
    }

    @Override
    public int getWidth() {
        return 16;
    }

    @Override
    public int getHeight() {
        return 16;
    }

    @Override
    public void toBytes(@Nonnull ByteBuf buf) {
        NetworkTools.writeStringUTF8(buf, location);
        buf.writeInt(color);
        buf.writeInt(amount);
    }

    @Override
    public int getID() {
        return GTTAIntegration.FLUID_STACK_ELEMENT;
    }

}