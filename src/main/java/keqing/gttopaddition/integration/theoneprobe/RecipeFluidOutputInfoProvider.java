package keqing.gttopaddition.integration.theoneprobe;

import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IWorkable;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.integration.theoneprobe.provider.CapabilityInfoProvider;
import keqing.gttopaddition.GTTAConfig;
import keqing.gttopaddition.api.gui.impl.FluidStyle;
import mcjty.theoneprobe.api.ElementAlignment;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.TextStyleClass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static keqing.gttopaddition.GTTAConfig.enablePlayerSneakingShow;

public class RecipeFluidOutputInfoProvider extends CapabilityInfoProvider<IWorkable> {

    public RecipeFluidOutputInfoProvider() {
    }

    @Override
    protected @Nonnull Capability<IWorkable> getCapability() {
        return GregtechTileCapabilities.CAPABILITY_WORKABLE;
    }

    protected void addProbeInfo(IWorkable capability, IProbeInfo probeInfo, EntityPlayer entityPlayer, TileEntity tileEntity, IProbeHitData iProbeHitData) {
        if ((!enablePlayerSneakingShow || entityPlayer.isSneaking()) && capability.getProgress() > 0 && capability instanceof AbstractRecipeLogic) {


            if (GTTAConfig.enableTOPRecipeFluidOutputTextureRender) {
                IProbeInfo horizontalPane = probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
                List<FluidStack> fluidOutputs = new ArrayList<>(ObfuscationReflectionHelper.getPrivateValue(AbstractRecipeLogic.class, (AbstractRecipeLogic) capability, "fluidOutputs"));
                if (!fluidOutputs.isEmpty()) {
                    horizontalPane.text(TextStyleClass.INFO + "{*gtqt.top.fluid_outputs*}");
                    for (FluidStack fluidStack : fluidOutputs) {
                        IProbeInfo HorizontalPane = probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
                        HorizontalPane.element(new FluidStackElement(fluidStack, new FluidStyle()));
                        HorizontalPane.text(TextStyleClass.INFO + fluidStack.getLocalizedName());
                        if (fluidStack.amount >= 1000) {
                            HorizontalPane.text(TextStyleClass.INFO + " * " + (fluidStack.amount / 1000) + "B");
                        } else {
                            HorizontalPane.text(TextStyleClass.INFO + " * " + fluidStack.amount + "mB");
                        }
                    }
                }
            } else {
                IProbeInfo horizontalPane = probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
                List<FluidStack> fluidOutputs = new ArrayList(ObfuscationReflectionHelper.getPrivateValue(AbstractRecipeLogic.class, (AbstractRecipeLogic) capability, "fluidOutputs"));
                if (!fluidOutputs.isEmpty()) {
                    horizontalPane.text(TextStyleClass.INFO + "{*gtqt.top.fluid_outputs*}");
                    Iterator var8 = fluidOutputs.iterator();

                    while (var8.hasNext()) {
                        FluidStack fluidOutput = (FluidStack) var8.next();
                        if (fluidOutput != null && fluidOutput.amount > 0) {
                            IProbeInfo horizontal = probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
                            horizontal.text(TextStyleClass.INFO + fluidOutput.getLocalizedName() + " * " + TextFormatting.BLUE + this.FormatFluidAmount(fluidOutput));
                        }
                    }
                }
            }


        }

    }

    private String FormatFluidAmount(FluidStack fluidOutput) {
        return TextFormatting.DARK_PURPLE + (fluidOutput.amount >= 1000 ? fluidOutput.amount / 1000 + " B" : fluidOutput.amount + " mB");
    }


    @Override
    public String getID() {
        return "gtqt:recipe_info_fluid_output";
    }
}

