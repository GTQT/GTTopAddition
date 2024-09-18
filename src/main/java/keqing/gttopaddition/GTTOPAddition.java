package keqing.gttopaddition;

import keqing.gttopaddition.integration.GTTAIntegration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        modid = "gttopaddition",
        name = "GTTOPAddition",
        acceptedMinecraftVersions = "[1.12.2,1.13)",
        version = "0.0.1-beta",
        dependencies = "required-after:gregtech@[2.8.0-beta,);"
)
public class GTTOPAddition {
    public static final String PACK = "1.0.1";

    public static final String MODID = "gttopaddition";
    public static final String NAME = "GTTOPAddition";
    public static final String VERSION = "0.2";

    @Mod.Instance(GTTOPAddition.MODID)
    public static GTTOPAddition instance;

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        GTTAIntegration.init();
    }
}
