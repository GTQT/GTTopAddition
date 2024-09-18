package keqing.gttopaddition.integration.theoneprobe;

import gregtech.api.GTValues;
import gregtech.api.util.GTUtility;
import gregtech.api.util.TextFormattingUtil;
import gregtech.common.pipelike.cable.tile.TileEntityCable;
import mcjty.theoneprobe.api.*;
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
        if (world.getTileEntity(iProbeHitData.getPos()) instanceof TileEntityCable) {
            TileEntityCable s = (TileEntityCable) world.getTileEntity(iProbeHitData.getPos());
            double EUt = s.getAverageVoltage();
            double EUm = s.getMaxVoltage();

            double It = s.getAverageAmperage();
            double Im = s.getMaxAmperage();

            if (EUt <= EUm) {
                iProbeInfo.progress((int) (EUt * It / getTrueAm(It)), (int) (EUm * Im), iProbeInfo.defaultProgressStyle()
                        .suffix(" / " + TextFormattingUtil.formatNumbers(EUm * Im) + " W")
                        .filledColor(0xFFEEE600)
                        .alternateFilledColor(0xFFEEE600)
                        .borderColor(0xFF555555).numberFormat(NumberFormat.COMMAS));


                iProbeInfo.text("{*gtqt.top.i*}" + TextFormatting.RED + " " + TextFormatting.GOLD + EUt / getTrueAm(It) + TextFormatting.BOLD + "/" + TextFormatting.RED + EUm +
                        " EU" + TextFormatting.GREEN +
                        " (" + GTValues.VNF[GTUtility.getTierByVoltage((long) EUt / getTrueAm(It))] + TextFormatting.GRAY + "/" + GTValues.VNF[GTUtility.getTierByVoltage((long) EUm)] + TextFormatting.GREEN + ")");

                iProbeInfo.text("{*gtqt.top.v*}" + " " + TextFormatting.YELLOW + It + TextFormatting.BOLD + "/" + TextFormatting.RED + Im + TextFormatting.LIGHT_PURPLE + " A");
            } else {
                iProbeInfo.progress((int) (EUm * It), (int) (EUm * Im), iProbeInfo.defaultProgressStyle()
                        .suffix(" / " + TextFormattingUtil.formatNumbers(EUm * Im) + " W")
                        .filledColor(0xFFEEE600)
                        .alternateFilledColor(0xFFEEE600)
                        .borderColor(0xFF555555).numberFormat(NumberFormat.COMMAS));

                iProbeInfo.text("{*gtqt.top.i*}" + TextFormatting.RED + " " + TextFormatting.GOLD + EUm + TextFormatting.BOLD + "/" + TextFormatting.RED + EUm +
                        " EU" + TextFormatting.GREEN +
                        " (" + GTValues.VNF[GTUtility.getTierByVoltage((long) EUm)] + TextFormatting.GRAY + "/" + GTValues.VNF[GTUtility.getTierByVoltage((long) EUm)] + TextFormatting.GREEN + ")");

                iProbeInfo.text("{*gtqt.top.v*}" + " " + TextFormatting.YELLOW + It + TextFormatting.BOLD + "/" + TextFormatting.RED + Im + TextFormatting.LIGHT_PURPLE + " A");

            }

        }
    }

    public int getTrueAm(double A) {
        if (A <= 4) return 1;
        if (A <= 16) return 2;
        if (A <= 64) return 3;
        if (A <= 256) return 4;
        if (A <= 1024) return 5;
        if (A <= 4096) return 6;
        if (A <= 16384) return 7;
        return 1;
    }
}