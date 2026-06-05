package meowmel.gttopaddition.integration.theoneprobe;

import gregtech.api.capability.IActiveOutputSide;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class MetaTileEntityIOInfo implements IProbeInfoProvider {

    @Override
    public String getID() {
        return "gtqt:io_info";
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, EntityPlayer entityPlayer, World world, IBlockState iBlockState, IProbeHitData iProbeHitData) {
        if (iBlockState.getBlock().hasTileEntity(iBlockState)) {
            TileEntity te = world.getTileEntity(iProbeHitData.getPos());
            if (te instanceof IGregTechTileEntity ignite) {
                MetaTileEntity mte = ignite.getMetaTileEntity();
                if (mte instanceof IActiveOutputSide io) {
                    boolean isAutoOutputItems = io.isAutoOutputItems();
                    boolean isAutoOutputFluids = io.isAutoOutputFluids();


                    if (mte instanceof SimpleMachineMetaTileEntity machineMetaTile) {
                        EnumFacing outputFacingItems = machineMetaTile.getOutputFacingItems();
                        EnumFacing outputFacingFluids = machineMetaTile.getOutputFacingFluids();

                        iProbeInfo.text("{*gtqt.top.autoOutputItem*}" + " " + isAutoOutputItems + " " + TextFormatting.GOLD + outputFacingItems.getName());
                        iProbeInfo.text("{*gtqt.top.autoOutputFluid*}" + " " + isAutoOutputFluids+ " " + TextFormatting.GOLD + outputFacingFluids.getName());
                        return;
                    }
                    iProbeInfo.text("{*gtqt.top.autoOutputItem*}" + " " + isAutoOutputItems);
                    iProbeInfo.text("{*gtqt.top.autoOutputFluid*}" + " " + isAutoOutputFluids);
                }
            }
        }
    }
}
