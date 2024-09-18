package keqing.gttopaddition;

import net.minecraftforge.common.config.Config;

@Config(modid = GTTOPAddition.MODID)
public class GTTAConfig {

    @Config.Comment({"Enable While player Sneaking", "Default: true"})
    public static boolean enablePlayerSneakingShow = true;

    @Config.Comment({"Enable TOP Recipe Fluid Output Icon Render", "Default: true"})
    public static boolean enableTOPRecipeFluidOutputTextureRender = true;

    @Config.Comment({"Enable TOP Recipe Item Output Icon Render", "Default: true"})
    public static boolean enableTOPRecipeItemOutputTextureRender = true;

    @Config.Comment({"Enable TOP Recipe Item Output Label Render,Work only enableTOPRecipeItemOutputTextureRender is true", "Default: true"})
    public static boolean enableTOPRecipeItemOutputIconRender = true;

    @Config.Comment({"Enable TOP Electric Container IO Info Provider", "Default: true"})
    public static boolean enableTOPElectricContainerIOInfoProvider = true;

    @Config.Comment({"Enable TOP Multiblock Face Provider", "Default: true"})
    public static boolean enableMultiblockFaceProvider = true;
}
