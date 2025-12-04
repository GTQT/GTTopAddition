package keqing.gttopaddition;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class SimpleTooltipColor
{
    // 配置类
    public static class Config
    {
        // 固定背景颜色 - 半透明深灰色
        public static final int BACKGROUND_COLOR = 0xCC1f1f1f;

        // 固定边框颜色 - 不透明灰色
        public static final int BORDER_COLOR = 0xFF4b4b4b;

        // 是否根据物品稀有度自动着色边框
        public static boolean enableRarityColors = true;

        // 稀有度颜色映射 - 保持与Minecraft原版一致
        public static int getRarityColor(net.minecraft.item.EnumRarity rarity)
        {
            return switch (rarity) {
                case UNCOMMON -> 0xFF55FF55;
                case RARE -> 0xFF5555FF;
                case EPIC -> 0xFFAA00AA;
                default -> 0xFF4b4b4b;
            };
        }
    }

    @SubscribeEvent
    public static void onRenderTooltip(RenderTooltipEvent.Color event)
    {
        ItemStack stack = event.getStack();
        if (stack.isEmpty()) return;

        // 固定背景颜色为 0x881f1f1f (半透明深灰色)
        event.setBackground(Config.BACKGROUND_COLOR);

        // 根据配置设置边框颜色
        if (Config.enableRarityColors)
        {
            // 根据物品稀有度设置边框颜色
            net.minecraft.item.EnumRarity rarity = stack.getRarity();
            int rarityColor = Config.getRarityColor(rarity);

            // 设置边框颜色为稀有度颜色
            event.setBorderStart(rarityColor);
            event.setBorderEnd(rarityColor); // 纯色边框，起始和结束颜色相同
        }
        else
        {
            // 使用固定边框颜色 0xFF4b4b4b (不透明灰色)
            event.setBorderStart(Config.BORDER_COLOR);
            event.setBorderEnd(Config.BORDER_COLOR); // 纯色边框
        }
    }
}