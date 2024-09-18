package keqing.gttopaddition.integration.theoneprobe;

import gregtech.api.capability.IEnergyContainer;
import gregtech.integration.theoneprobe.provider.ElectricContainerInfoProvider;
import keqing.gttopaddition.GTTAConfig;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;

public class ElectricContainerIOInfoProvider extends ElectricContainerInfoProvider {

    @Override
    protected void addProbeInfo(IEnergyContainer capability, IProbeInfo probeInfo,
                                EntityPlayer player, TileEntity tileEntity, IProbeHitData data) {
        super.addProbeInfo(capability, probeInfo, player, tileEntity, data);

        if (GTTAConfig.enableTOPElectricContainerIOInfoProvider) {


            long inA = capability.getInputVoltage();
            long inB = capability.getInputAmperage();
            long inC = capability.getInputPerSec();

            if (inA > 0) {
                probeInfo.text("{*gtqt.top.in*}");
                probeInfo.text(" " + TextFormatting.BLUE + inA + "EU/t * " + inB + "A // " + TextFormatting.RED + inC / 20 + "EU/t");
            }

            long onA = capability.getOutputVoltage();
            long onB = capability.getOutputAmperage();
            long onC = capability.getOutputPerSec();

            if (onA > 0) {
                probeInfo.text("{*gtqt.top.on*}");
                probeInfo.text(" " + TextFormatting.BLUE + onA + "EU/t * " + onB + "A // " + TextFormatting.RED + onC / 20 + "EU/t");
            }

        }
    }
}