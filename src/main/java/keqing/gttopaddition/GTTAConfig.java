package keqing.gttopaddition;

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

    @Config.Name("Tooltips Color")
    public static TooltipColor tooltips_custom = new TooltipColor();

    @Config.Name("The One Probe Color")
    public static TOPCustomColor top_custom = new TOPCustomColor();

    public static class TooltipColor {
        @Config.Comment("是否启用自定义物品提示框颜色")
        @Config.Name("启用")
        public boolean enabled = true;

        @Config.Comment({
                "背景颜色",
                "格式: 0xAARRGGBB",
                "默认: 0xCC1f1f1f (80% 不透明的深灰色)"
        })
        @Config.RangeInt(min = 0x00000000, max = 0xFFFFFFFF)
        @Config.Name("背景颜色")
        public int backgroundColor = 0xCC1f1f1f;

        @Config.Comment({
                "默认边框颜色",
                "格式: 0xAARRGGBB",
                "默认: 0xFF4b4b4b (不透明灰色)",
                "当【稀有度着色】关闭时使用此颜色"
        })
        @Config.RangeInt(min = 0x00000000, max = 0xFFFFFFFF)
        @Config.Name("边框颜色")
        public int borderColor = 0xFF4b4b4b;

        @Config.Comment({
                "是否根据物品稀有度自动着色边框",
                "true: 启用 (边框颜色根据物品稀有度变化)",
                "false: 禁用 (始终使用默认边框颜色)"
        })
        @Config.Name("稀有度着色")
        public boolean enableRarityColors = true;
    }

    public static class TOPCustomColor {

        @Config.Comment({
                " 【边框颜色】",
                " 格式: 0xAARRGGBB (ARGB 十六进制)",
                " - AA: 透明度 (00=透明, FF=不透明)",
                " - RR: 红色分量 (00-FF)",
                " - GG: 绿色分量 (00-FF)",
                " - BB: 蓝色分量 (00-FF)",
                " 默认值: 0xFF4b4b4b (不透明的深灰色)",
        })
        @Config.RangeInt(min = 0x00000000, max = 0xFFFFFFFF)
        @Config.Name("边框颜色")
        public int borderColor = -16711936;

        @Config.Comment({
                " 【填充颜色】",
                " 格式: 0xAARRGGBB (ARGB 十六进制)",
                " - AA: 透明度 (推荐 88 = 约 53% 不透明)",
                " - RR: 红色分量 (00-FF)",
                " - GG: 绿色分量 (00-FF)",
                " - BB: 蓝色分量 (00-FF)",
                " 默认值: 0x884b4b4b (半透明深灰色)",
        })
        @Config.RangeInt(min = 0x00000000, max = 0xFFFFFFFF)
        @Config.Name("填充颜色")
        public int fillColor = 0xCC000033;

        @Config.Comment({
                " 【边框厚度】",
                " 单位: 像素",
                " 范围: 0-10",
                " - 0: 无边框 (完全透明主题)",
                " - 1: 细边框 (简约风格)",
                " - 2: 标准边框 (默认值)",
                " - 3+: 粗边框 (强调效果)",
        })
        @Config.RangeInt(min = 0, max = 10)
        @Config.Name("边框厚度")
        public int thickness = 1;

        @Config.Comment({
                " 【边框偏移】",
                " 单位: 像素",
                " 范围: 0-20",
                " - 0: 边框紧贴内容 (无间距)",
                " - 1: 轻微内边距 (推荐)",
                " - 2-5: 中等间距",
                " - 5+: 较大间距",
        })
        @Config.RangeInt(min = 0, max = 20)
        @Config.Name("边框偏移")
        public int offset = 1;
    }
}
