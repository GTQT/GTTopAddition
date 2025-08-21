package keqing.gttopaddition.integration.theoneprobe;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import keqing.gttopaddition.GTTAConfig;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import static keqing.gttopaddition.GTTAConfig.enableMultiblockFaceProvider;

public class MultiblockFaceProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return "gtqt:face";
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, EntityPlayer entityPlayer, World world, IBlockState iBlockState, IProbeHitData iProbeHitData) {
        if (GTTAConfig.enableMultiblockFaceProvider && iBlockState.getBlock().hasTileEntity(iBlockState) && entityPlayer.isSneaking()) {
            TileEntity te = world.getTileEntity(iProbeHitData.getPos());
            IProbeInfo horizontalPane = iProbeInfo.horizontal(iProbeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
            if (te instanceof IGregTechTileEntity) {
                IGregTechTileEntity ignite = (IGregTechTileEntity) te;
                MetaTileEntity mte = ignite.getMetaTileEntity();
                EnumFacing Facing = mte.getFrontFacing();
                horizontalPane.text(TextStyleClass.INFO + "{*gtqt.top.face*}");
                horizontalPane.text(TextStyleClass.INFO + " " + TextFormatting.BLUE + Facing + "  ");

            }
        }
    }
}