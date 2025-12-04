package keqing.gttopaddition.tooltips;

import keqing.gttopaddition.GTTAConfig;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class SimpleTooltipColor {

    // 配置类
    public static class Config {
        // 是否启用
        public static boolean isEnabled() {
            return GTTAConfig.tooltips_custom.enabled;
        }

        // 背景颜色
        public static final int BACKGROUND_COLOR = GTTAConfig.tooltips_custom.backgroundColor;

        // 默认边框颜色
        public static final int BORDER_COLOR = GTTAConfig.tooltips_custom.borderColor;

        // 是否启用稀有度颜色
        public static boolean enableRarityColors = GTTAConfig.tooltips_custom.enableRarityColors;

        // 稀有度颜色映射 - 保持与Minecraft原版一致
        public static int getRarityColor(net.minecraft.item.EnumRarity rarity) {
            return switch (rarity) {
                case COMMON -> BORDER_COLOR;
                case UNCOMMON -> 0xFFFFFF55;
                case RARE -> 0xFF5555FF;
                case EPIC -> 0xFFAA00AA;
            };
        }
    }

    @SubscribeEvent
    public static void onRenderTooltip(RenderTooltipEvent.Color event) {
        // 检查是否启用
        if (!Config.isEnabled()) {
            return;
        }

        ItemStack stack = event.getStack();
        if (stack.isEmpty()) return;

        // 设置背景颜色
        event.setBackground(Config.BACKGROUND_COLOR);

        // 根据配置设置边框颜色
        if (Config.enableRarityColors) {
            // 根据物品稀有度设置边框颜色
            net.minecraft.item.EnumRarity rarity = stack.getRarity();
            int rarityColor = Config.getRarityColor(rarity);

            // 设置边框颜色
            event.setBorderStart(rarityColor);
            event.setBorderEnd(rarityColor); // 纯色边框，起始和结束颜色相同
        } else {
            // 使用固定边框颜色
            event.setBorderStart(Config.BORDER_COLOR);
            event.setBorderEnd(Config.BORDER_COLOR); // 纯色边框
        }
    }
}