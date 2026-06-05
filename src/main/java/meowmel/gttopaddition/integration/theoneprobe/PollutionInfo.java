package meowmel.gttopaddition.integration.theoneprobe;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PollutionInfo implements IProbeInfoProvider {

    @Override
    public String getID() {
        return "gtqt:pollution";
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, EntityPlayer entityPlayer, World world, IBlockState iBlockState, IProbeHitData iProbeHitData) {
        if (iBlockState.getBlock().hasTileEntity(iBlockState)) {
            TileEntity te = world.getTileEntity(iProbeHitData.getPos());
            if (te instanceof IGregTechTileEntity ignite) {
                MetaTileEntity mte = ignite.getMetaTileEntity();

                double amount = mte.getPollutionAmount();
                int time = mte.getPollutionTicks();
                iProbeInfo.text("{*gtqt.top.pollution*}" + " " + amount + "/" + time + "tick");
            }
        }
    }
}
