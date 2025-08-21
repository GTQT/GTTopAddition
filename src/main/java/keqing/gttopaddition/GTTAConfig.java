package keqing.gttopaddition;

import net.minecraftforge.common.config.Config;

@Config(modid = GTTOPAddition.MODID)
public class GTTAConfig {

    @Config.Comment({"If true, recipe fluid and outputs will be displayed.", "Default: true"})
    public static boolean displayRecipeOutputs = true;

    @Config.Comment({"If true, the average energy and amperage of a cable net will be shown.", "Default: true"})
    public static boolean displayCableAverage = true;

    @Config.Comment({"How many items and fluids can be shown at once before it starts hiding names.", "Default: 4", "Range: 1 ~ 40"})
    public static int itemFluidDetailLimit = 4;

    @Config.Comment({"How many items and fluids are shown in seperate rows (when ItemFluidDetailLimit is exceeded)", "Default: 12", "Range: 1 ~ 40"})
    public static int maxEntriesToShowInRow = 12;

    @Config.Comment({"How far items and fluids are seperated when shown in rows.", "Default: 2", "Range: 0 ~ 10"})
    public static int rowDistanceSeperation = 2;

    @Config.Comment({"Enable TOP Electric Container IO Info Provider", "Default: true"})
    public static boolean enableTOPElectricContainerIOInfoProvider = true;

    @Config.Comment({"Enable TOP Multiblock Face Provider", "Default: true"})
    public static boolean enableMultiblockFaceProvider = true;
}
