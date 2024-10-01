package keqing.gttopaddition.integration.theoneprobe;

import gregicality.multiblocks.common.metatileentities.multiblock.standard.MetaTileEntityAlloyBlastSmelter;
import gregicality.multiblocks.common.metatileentities.multiblock.standard.MetaTileEntityMegaBlastFurnace;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.util.TextFormattingUtil;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityElectricBlastFurnace;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityPowerSubstation;
import keqing.gttopaddition.api.utils.Mods;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class MultiblockPSSProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return "gtqt:temperature";
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, EntityPlayer entityPlayer, World world, IBlockState iBlockState, IProbeHitData iProbeHitData) {
        if (iBlockState.getBlock().hasTileEntity(iBlockState)) {
            TileEntity te = world.getTileEntity(iProbeHitData.getPos());
            if (te instanceof IGregTechTileEntity) {
                IGregTechTileEntity ignite = (IGregTechTileEntity) te;
                MetaTileEntity mte = ignite.getMetaTileEntity();
                if (mte instanceof MetaTileEntityPowerSubstation) {
                    long averageInLastSec = ((MetaTileEntityPowerSubstation) mte).getAverageInLastSec();
                    long averageOutLastSec = ((MetaTileEntityPowerSubstation) mte).getAverageOutLastSec();

                    long getCapacityLong = ((MetaTileEntityPowerSubstation) mte).getCapacityLong();
                    long getStoredLong = ((MetaTileEntityPowerSubstation) mte).getStoredLong();

                    iProbeInfo.progress(getStoredLong, getCapacityLong, iProbeInfo.defaultProgressStyle()
                            .suffix(" / " + TextFormattingUtil.formatNumbers(getCapacityLong) + " EU")
                            .filledColor(0xFFEEE600)
                            .alternateFilledColor(0xFFEEE600)
                            .borderColor(0xFF555555).numberFormat(NumberFormat.COMMAS));

                    iProbeInfo.text(TextStyleClass.INFO + "{*gtqt.top.averageInLastSec*}");
                    iProbeInfo.text(TextStyleClass.INFO + " " + TextFormatting.RED + averageInLastSec + " EU");

                    iProbeInfo.text(TextStyleClass.INFO + "{*gtqt.top.averageOutLastSec*}");
                    iProbeInfo.text(TextStyleClass.INFO + " " + TextFormatting.RED + averageOutLastSec + " EU");

                }
            }
        }
    }
}