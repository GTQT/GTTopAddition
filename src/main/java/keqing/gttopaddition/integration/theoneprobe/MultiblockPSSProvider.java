package keqing.gttopaddition.integration.theoneprobe;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.util.TextFormattingUtil;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityPowerSubstation;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import static gregtech.client.utils.TooltipHelper.isCtrlDown;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import net.minecraft.util.text.ITextComponent;

import gregtech.api.util.TextComponentUtil;


import mcjty.theoneprobe.apiimpl.elements.ElementProgress;
public class MultiblockPSSProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return "gtqt:power_station";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo info, EntityPlayer player, World world,
                             IBlockState state, IProbeHitData data) {
        if (state.getBlock().hasTileEntity(state)) {
            TileEntity tileEntity = world.getTileEntity(data.getPos());
            if (tileEntity instanceof IGregTechTileEntity) {
                MetaTileEntity metaTileEntity = ((IGregTechTileEntity) tileEntity).getMetaTileEntity();
                if (metaTileEntity instanceof MetaTileEntityPowerSubstation &&
                        ((MetaTileEntityPowerSubstation) metaTileEntity).isStructureFormed()) {
                    // Fill Percentage Line
                    BigInteger stored = castToBI(((MetaTileEntityPowerSubstation) metaTileEntity).getStored());
                    BigInteger capacity = castToBI(((MetaTileEntityPowerSubstation) metaTileEntity).getCapacity());

                    info.progress(percentage(stored, capacity).intValue(), 100, info.defaultProgressStyle()
                            .suffix(" / " + ElementProgress.format(100, NumberFormat.FULL, " %"))
                            .filledColor(0xFFEEE600)
                            .alternateFilledColor(0xFFEEE600)
                            .borderColor(0xFF555555));

                    // Fill Percentage Text
                    ITextComponent percent = TextComponentUtil.translationWithColor(
                            TextFormatting.WHITE,
                            "gtqt.top.pss.details",
                            TextComponentUtil.stringWithColor(
                                    TextFormatting.GREEN,
                                    String.format("%.3f", 100 *
                                            (((MetaTileEntityPowerSubstation) metaTileEntity).getFillPercentage(1))) +
                                            "%"));
                    info.text(percent.getFormattedText());

                    // Stored and Capacity Lines
                    addStoredAndCapacityLines(info, metaTileEntity, stored, capacity);

                    // IO Line
                    long in = ((MetaTileEntityPowerSubstation) metaTileEntity).getAverageInLastSec();
                    long out = ((MetaTileEntityPowerSubstation) metaTileEntity).getAverageOutLastSec();
                    long passive = ((MetaTileEntityPowerSubstation) metaTileEntity).getPassiveDrain();
                    long io = in - out;

                    String ioText = (io > 0 ? TextFormatting.GREEN : TextFormatting.RED) +
                            (isCtrlDown() ? TextFormattingUtil.formatNumbers(io) : formatNumber(io)) +
                            "EU/t";
                    if (io > 0) {
                        info.text(TextFormatting.WHITE + "{*gtqt.top.averageInLastSec*}" + " " + ioText);
                    } else {
                        info.text(TextFormatting.WHITE + "{*gtqt.top.averageOutLastSec*}" + " " + ioText);
                    }

                    // Additional IO Lines when sneaking
                    if (player.isSneaking()) {
                        addAdditionalIOLines(info, in, out, passive);
                    }
                }
            }
        }
    }

    private void addStoredAndCapacityLines(IProbeInfo info, MetaTileEntity metaTileEntity, BigInteger stored, BigInteger capacity) {
        ITextComponent storedS = TextComponentUtil.translationWithColor(
                TextFormatting.WHITE,
                "gregtech.multiblock.power_substation.stored",
                TextComponentUtil.stringWithColor(
                        TextFormatting.WHITE,
                        isCtrlDown() ?
                                ((MetaTileEntityPowerSubstation) metaTileEntity).getStored() + " EU" :
                                formatNumber(stored) + "EU"));
        info.text(storedS.getFormattedText());

        ITextComponent capacityS = TextComponentUtil.translationWithColor(
                TextFormatting.WHITE,
                "gregtech.multiblock.power_substation.capacity",
                TextComponentUtil.stringWithColor(
                        TextFormatting.WHITE,
                        isCtrlDown() ?
                                ((MetaTileEntityPowerSubstation) metaTileEntity).getCapacity() + " EU" :
                                formatNumber(capacity) + "EU"));
        info.text(capacityS.getFormattedText());
    }

    private void addAdditionalIOLines(IProbeInfo info, long in, long out, long passive) {
        ITextComponent averageIn = TextComponentUtil.translationWithColor(
                TextFormatting.GREEN,
                "gregtech.multiblock.power_substation.average_in",
                TextComponentUtil.stringWithColor(TextFormatting.WHITE,
                        isCtrlDown() || in < 10_000 ?
                                TextFormattingUtil.formatNumbers(in) + " EU/t" :
                                ElementProgress.format(in, NumberFormat.COMPACT, "EU/t")));
        info.text(averageIn.getFormattedText());

        ITextComponent averageOut = TextComponentUtil.translationWithColor(
                TextFormatting.RED,
                "gregtech.multiblock.power_substation.average_out",
                TextComponentUtil.stringWithColor(TextFormatting.WHITE,
                        isCtrlDown() || out < 10_000 ?
                                TextFormattingUtil.formatNumbers(out) + " EU/t" :
                                ElementProgress.format(out, NumberFormat.COMPACT, "EU/t")));
        info.text(averageOut.getFormattedText());

        ITextComponent passiveDrain = TextComponentUtil.translationWithColor(
                TextFormatting.DARK_RED,
                "gregtech.multiblock.power_substation.passive_drain",
                TextComponentUtil.stringWithColor(TextFormatting.WHITE,
                        isCtrlDown() || passive < 10_000 ?
                                TextFormattingUtil.formatNumbers(passive) + " EU/t" :
                                ElementProgress.format(passive, NumberFormat.COMPACT, "EU/t")));
        info.text(" " + passiveDrain.getFormattedText());
    }

    private static String formatNumber(long value) {
        boolean negative = false;
        DecimalFormat df = new DecimalFormat("#.#");

        String[] suffixes = { " ", " k", " M", " G", " T", " P", " E" };

        int suffixIndex = 0;
        if (value < 0) {
            negative = true;
            value = -1 * value;
        }
        double displayValue = value;
        if (value > 10000) {
            while (displayValue >= 1000 && suffixIndex < suffixes.length - 1) {
                displayValue /= 1000;
                suffixIndex++;
            }
        }
        String prefix = negative ? "-" : "";
        return prefix + df.format(displayValue) + " " + suffixes[suffixIndex];
    }

    private static String formatNumber(BigInteger value) {
        boolean negative = false;
        if (value.compareTo(BigInteger.ZERO) < 0) {
            negative = true;
            value = value.abs();
        }

        String[] suffixes = { " ", " k", " M", " G", " T", " P", " E", " Z" };
        int suffixIndex = 0;
        BigInteger thousand = BigInteger.valueOf(1000);

        while (value.compareTo(thousand) >= 0 && suffixIndex < suffixes.length - 1) {
            value = value.divide(thousand);
            suffixIndex++;
        }

        String prefix = negative ? "-" : "";
        return prefix + value + " " + suffixes[suffixIndex];
    }

    private static BigInteger castToBI(String value) {
        try {
            String number = value.replaceAll(",", "");
            return new BigInteger(number);
        } catch (NumberFormatException e) {
            // 处理异常情况
            System.err.println("Invalid input: " + value);
            return BigInteger.ZERO; // 返回默认值
        }
    }

    private static BigInteger percentage(BigInteger value1, BigInteger value2) {
        if (value2.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }
        // 使用 BigDecimal 进行精确除法运算
        BigDecimal numerator = new BigDecimal(value1.toString());
        BigDecimal denominator = new BigDecimal(value2.toString());
        BigDecimal result = numerator.divide(denominator, 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
        return result.toBigInteger();
    }
}