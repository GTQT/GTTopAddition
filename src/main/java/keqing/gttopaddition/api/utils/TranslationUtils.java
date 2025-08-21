package keqing.gttopaddition.api.utils;

import mcjty.theoneprobe.api.IProbeInfo;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.IllegalFormatException;

public class TranslationUtils {

    public static String translate(String key, Object... params) {
        if (FMLCommonHandler.instance().getSide().isServer()) return translateServerSide(key, params);

        try {
            return net.minecraft.client.resources.I18n.format(key, params);
        } catch (Exception e) {
            return translateServerSide(key, params);
        }
    }

    private static String translateServerSide(String key, Object... params) {
        try {
            var localTranslated = I18n.translateToLocalFormatted(key, params);
            if (!localTranslated.equals(key)) return localTranslated;


            var fallbackTranslated = I18n.translateToFallback(key);
            if (!fallbackTranslated.equals(key) && params.length != 0) {
                try {
                    fallbackTranslated = String.format(fallbackTranslated, params);
                } catch (IllegalFormatException err) {
                    fallbackTranslated = "Format error: " + fallbackTranslated;
                }
            }
            return fallbackTranslated;
        } catch (Exception e) {
            return key;
        }
    }

    public static String topTranslate(String key) {
        return IProbeInfo.STARTLOC + key + IProbeInfo.ENDLOC;
    }
}