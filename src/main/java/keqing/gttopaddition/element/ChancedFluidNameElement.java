package keqing.gttopaddition.element;

import io.netty.buffer.ByteBuf;
import keqing.gttopaddition.integration.GTTAIntegration;
import keqing.gttopaddition.integration.theoneprobe.RecipeOutputInfoProvider;
import net.minecraftforge.fluids.FluidStack;
/*
 * From : https://github.com/Supernoobv/GregicProbeCEu/blob/master/src/main/java/vfyjxf/gregicprobe/element/ChancedFluidNameElement.java
 */
public class ChancedFluidNameElement extends FluidNameElement {
    private final int chance;

    public ChancedFluidNameElement(FluidStack fluid, int chance, boolean showLang) {
        super(fluid, showLang);
        this.chance = chance;
    }

    public ChancedFluidNameElement(ByteBuf byteBuf) {
        super(byteBuf);
        chance = byteBuf.readInt();
    }

    @Override
    public String getTranslated() {
        return super.getTranslated() + " (" + RecipeOutputInfoProvider.formatChance(chance) + ")";
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        super.toBytes(byteBuf);
        byteBuf.writeInt(chance);
    }

    @Override
    public int getID() {
        return GTTAIntegration.CHANCED_FLUID_NAME_ELEMENT;
    }
}