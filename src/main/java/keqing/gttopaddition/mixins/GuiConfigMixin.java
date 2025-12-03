package keqing.gttopaddition.mixins;
import org.apache.commons.lang3.tuple.Pair;
import mcjty.theoneprobe.gui.GuiConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

@Mixin(GuiConfig.class)
public class GuiConfigMixin {

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void onStaticInit(CallbackInfo ci) {
        try {
            // 使用反射完全避免在代码中直接引用 Preset
            Class<?> presetClass = Class.forName("mcjty.theoneprobe.gui.Preset");

            // 获取正确的构造函数参数类型
            Constructor<?> constructor = presetClass.getDeclaredConstructor(
                    String.class,      // name
                    int.class,         // borderColor
                    int.class,         // fillColor
                    int.class,         // thickness
                    int.class,         // offset
                    Pair[].class       // stylePairs (注意这里是 Pair[].class，不是 Object[].class)
            );
            constructor.setAccessible(true);

            // 创建 Preset 实例
            Object customPreset = constructor.newInstance(
                    "Jade Style",     // 主题名称
                    0xFF4b4b4b,        // 深灰色（接近黑色）边框 - Slate Gray (ARGB: 255, 47, 79, 79)
                    0x881f1f1f,        // 淡灰色半透明填充 (ARGB: 136, 224, 224, 224)
                    1,                 // 边框厚度
                    1,                 // 边框偏移 (根据您的要求设为0)
                    new Pair[0]        // 空的样式对数组
            );

            // 获取 presets 字段并添加自定义预设
            Field presetsField = GuiConfig.class.getDeclaredField("presets");
            presetsField.setAccessible(true);

            // 注意：这里需要处理泛型类型，但可以通过原始类型操作
            @SuppressWarnings("unchecked")
            List<Object> presets = (List<Object>) presetsField.get(null);
            presets.add(customPreset);

        } catch (Exception e) {

        }
    }
}