package meowmel.gttopaddition;

import net.minecraftforge.common.config.Config;

@Config(modid = GTTOPAddition.MODID)
public class GTTAConfig {

    @Config.Comment({"If true, recipe fluid and outputs will be displayed.", "Default: true"})
    @Config.Name("显示配方输出")
    public static boolean displayRecipeOutputs = true;

    @Config.Comment({"If true, the average energy and amperage of a cable net will be shown.", "Default: true"})
    @Config.Name("显示线缆网络平均值")
    public static boolean displayCableAverage = true;

    @Config.Comment({"How many items and fluids can be shown at once before it starts hiding names.", "Default: 4", "Range: 1 ~ 40"})
    @Config.RangeInt(min = 1, max = 40)
    @Config.Name("物品流体详细显示限制")
    public static int itemFluidDetailLimit = 4;

    @Config.Comment({"How many items and fluids are shown in seperate rows (when ItemFluidDetailLimit is exceeded)", "Default: 12", "Range: 1 ~ 40"})
    @Config.RangeInt(min = 1, max = 40)
    @Config.Name("每行最大显示数量")
    public static int maxEntriesToShowInRow = 12;

    @Config.Comment({"How far items and fluids are seperated when shown in rows.", "Default: 2", "Range: 0 ~ 10"})
    @Config.RangeInt(min = 0, max = 10)
    @Config.Name("行间距")
    public static int rowDistanceSeperation = 2;

    @Config.Comment({"Enable TOP Electric Container IO Info Provider", "Default: true"})
    @Config.Name("启用电力容器IO信息提供器")
    public static boolean enableTOPElectricContainerIOInfoProvider = true;

    @Config.Comment({"Enable TOP Multiblock Face Provider", "Default: true"})
    @Config.Name("启用多方块面提供器")
    public static boolean enableMultiblockFaceProvider = true;
}
