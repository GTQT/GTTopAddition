package keqing.gttopaddition;

import keqing.gttopaddition.blur.SimpleBlurHandler;
import keqing.gttopaddition.integration.GTTAIntegration;
import keqing.gttopaddition.tooltips.AdvancedTooltipHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = "gttopaddition",
        name = "GTTOPAddition",
        acceptedMinecraftVersions = "[1.12.2,1.13)",
        version = "0.0.1-beta",
        dependencies = "required-after:gregtech;required-after:theoneprobe"
)
public class GTTOPAddition {
    public static final String MODID = "gttopaddition";
    public static final String NAME = "GTTOPAddition";
    public static final String VERSION = "0.2";
    public static Logger LOGGER;
    @Mod.Instance(GTTOPAddition.MODID)
    public static GTTOPAddition instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        GTTAIntegration.init();

        MinecraftForge.EVENT_BUS.register(new SimpleBlurHandler());
        MinecraftForge.EVENT_BUS.register(new AdvancedTooltipHandler());
    }
}