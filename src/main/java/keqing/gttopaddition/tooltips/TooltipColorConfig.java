package keqing.gttopaddition.tooltips;

import keqing.gttopaddition.GTTAConfig;

// ========== 颜色配置 ==========

public class TooltipColorConfig {
    public static boolean isEnabled() {
        return GTTAConfig.tooltips_custom.enabled;
    }

    public static int getBackgroundColor() {
        return GTTAConfig.tooltips_custom.backgroundColor;
    }

    public static int getBorderColor() {
        return GTTAConfig.tooltips_custom.borderColor;
    }

    public static boolean enableRarityColors() {
        return GTTAConfig.tooltips_custom.enableRarityColors;
    }

    public static int getRarityColor(net.minecraft.item.EnumRarity rarity) {
        switch (rarity) {
            case UNCOMMON: return 0xFFFFFF55; // 黄绿色
            case RARE: return 0xFF5555FF;     // 蓝色
            case EPIC: return 0xFFAA00AA;     // 紫色
            default: return getBorderColor();
        }
    }
}