package keqing.gttopaddition.element;

import io.netty.buffer.ByteBuf;
import keqing.gttopaddition.integration.GTTAIntegration;
import keqing.gttopaddition.integration.theoneprobe.RecipeOutputInfoProvider;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
/*
 * From : https://github.com/Supernoobv/GregicProbeCEu/blob/master/src/main/java/vfyjxf/gregicprobe/element/ChancedFluidStackElement.java
 */
public class ChancedFluidStackElement extends FluidStackElement {
    private final int chance;

    public ChancedFluidStackElement(@Nonnull FluidStack stack, int chance) {
        super(stack);
        this.chance = chance;
    }

    public ChancedFluidStackElement(@Nonnull ByteBuf buf) {
        super(buf);
        chance = buf.readInt();
    }

    @Override
    public void render(int x, int y) {
        super.render(x, y);
        RecipeOutputInfoProvider.renderChance(chance, x, y);
    }

    @Override
    public void toBytes(@Nonnull ByteBuf buf) {
        super.toBytes(buf);
        buf.writeInt(chance);
    }

    @Override
    public int getID() {
        return GTTAIntegration.CHANCED_FLUID_STACK_ELEMENT;
    }
}