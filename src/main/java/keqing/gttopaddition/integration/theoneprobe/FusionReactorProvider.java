package keqing.gttopaddition.integration.theoneprobe;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityFusionReactor;
import gregtech.integration.theoneprobe.provider.ElectricContainerInfoProvider;

import mcjty.theoneprobe.api.*;
import mcjty.theoneprobe.apiimpl.elements.ElementProgress;

public class FusionReactorProvider extends ElectricContainerInfoProvider {

    protected void addProbeInfo(IEnergyContainer capability, IProbeInfo probeInfo,
                                EntityPlayer player, TileEntity tileEntity, IProbeHitData data) {
        super.addProbeInfo(capability, probeInfo, player, tileEntity, data);

        if (tileEntity instanceof IGregTechTileEntity) {
            IGregTechTileEntity gregTechTileEntity = (IGregTechTileEntity) tileEntity;
            MetaTileEntity metaTileEntity = gregTechTileEntity.getMetaTileEntity();

            if (metaTileEntity instanceof MetaTileEntityFusionReactor) {
                MetaTileEntityFusionReactor fusionReactor = (MetaTileEntityFusionReactor) metaTileEntity;

                if (fusionReactor.isStructureFormed()) {
                    long heat = fusionReactor.getHeat();
                    long capacity = capability.getEnergyCapacity();

                    // 提取字符串常量
                    String heatLabel = getHeatLabel(); // 假设有一个方法获取字符串

                    probeInfo.text(TextFormatting.RED + heatLabel);
                    probeInfo.progress(heat, capacity, probeInfo.defaultProgressStyle()
                            .numberFormat(player.isSneaking() || heat < 10000 ? NumberFormat.FULL : NumberFormat.COMPACT)
                            .suffix(" / " + (player.isSneaking() || capacity < 10000 ? capacity :
                                    ElementProgress.format(capacity, NumberFormat.COMPACT, "")))
                            .filledColor(getFilledColor())
                            .alternateFilledColor(getAlternateFilledColor())
                            .borderColor(getBorderColor()));
                }
            }
        }
    }

    // 假设的方法用于获取字符串常量
    private String getHeatLabel() {
        return "{*gtqt.top.fusion_reactor.heat*}";
    }

    // 假设的方法用于获取颜色值
    private int getFilledColor() {
        return 0xFFEEE600;
    }

    private int getAlternateFilledColor() {
        return 0xFFEEE600;
    }

    private int getBorderColor() {
        return 0xFF555555;
    }
}