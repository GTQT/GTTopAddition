package keqing.gttopaddition.integration.theoneprobe;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.common.metatileentities.multi.MetaTileEntityPrimitiveWaterPump;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityCleanroom;
import lombok.Cleanup;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class MultiblockCLRProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return "gtqt:cleanroom";
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, EntityPlayer entityPlayer, World world, IBlockState iBlockState, IProbeHitData iProbeHitData) {
        if (iBlockState.getBlock().hasTileEntity(iBlockState)) {
            TileEntity te = world.getTileEntity(iProbeHitData.getPos());
            IProbeInfo horizontalPane = iProbeInfo.horizontal(iProbeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
            if (te instanceof IGregTechTileEntity) {
                IGregTechTileEntity ignite = (IGregTechTileEntity) te;
                MetaTileEntity mte = ignite.getMetaTileEntity();
                if (mte instanceof MetaTileEntityCleanroom) {
                    boolean isClean = ((MetaTileEntityCleanroom) mte).isClean();

                    horizontalPane.text(TextStyleClass.INFO + "{*gtqt.top.cleanroom*}");
                    horizontalPane.text(TextStyleClass.INFO + " " + TextFormatting.RED + isClean);
                }
            }
        }
    }
}