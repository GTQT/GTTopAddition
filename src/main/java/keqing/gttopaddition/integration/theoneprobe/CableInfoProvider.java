package keqing.gttopaddition.integration.theoneprobe;

import gregtech.api.GTValues;
import gregtech.api.util.GTUtility;
import gregtech.api.util.TextFormattingUtil;
import gregtech.common.pipelike.cable.tile.TileEntityCable;
import mcjty.theoneprobe.api.*;
import mcjty.theoneprobe.apiimpl.elements.ElementProgress;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class CableInfoProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return "gtqt:cable";
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, EntityPlayer entityPlayer, World world, IBlockState iBlockState, IProbeHitData iProbeHitData) {
        if (world.getTileEntity(iProbeHitData.getPos()) instanceof TileEntityCable s) {
            double EUt = s.getAverageVoltage();
            double EUm = s.getMaxVoltage();

            double It = s.getAverageAmperage();
            double Im = s.getMaxAmperage();

            String currentAmperage = TextFormattingUtil.formatNumbers(It);
            String currentMaxAmperage = TextFormattingUtil.formatNumbers(Im);

            String currentTier = GTValues.VNF[GTUtility.getTierByVoltage((long) EUt)];
            String maxTier = GTValues.VNF[GTUtility.getTierByVoltage((long) EUm)];


            iProbeInfo.progress((int) (EUt * It), (int) (EUm * Im), iProbeInfo.defaultProgressStyle()
                    .numberFormat(entityPlayer.isSneaking() || EUt * It < 10000 ?
                            NumberFormat.COMMAS :
                            NumberFormat.COMPACT)
                    .suffix(" / " + (entityPlayer.isSneaking() || EUm * Im < 10000 ?
                            ElementProgress.format((long) (EUm * Im), NumberFormat.COMMAS, " W") :
                            ElementProgress.format((long) (EUm * Im), NumberFormat.COMPACT, "W")))
                    .filledColor(0xFFEEE600)
                    .alternateFilledColor(0xFFEEE600)
                    .borderColor(0xFF555555));

            iProbeInfo.text("{*gtqt.top.v*}" + " " +
                    TextFormatting.AQUA + EUt +
                    TextFormatting.WHITE + TextFormatting.BOLD + "/" +
                    TextFormatting.GOLD + EUm +
                    TextFormatting.RED + " EU" +
                    TextFormatting.DARK_GRAY +
                    " (" +  currentTier +
                    TextFormatting.DARK_GRAY + "/" +
                    maxTier +
                    TextFormatting.DARK_GRAY + ")");

            iProbeInfo.text("{*gtqt.top.i*}" + " " +
                    TextFormatting.AQUA + currentAmperage +
                    TextFormatting.WHITE + TextFormatting.BOLD + "/" +
                    TextFormatting.GOLD + currentMaxAmperage +
                    TextFormatting.RED + " A");
        }
    }
}