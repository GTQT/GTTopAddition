package keqing.gttopaddition.integration;


import keqing.gttopaddition.integration.theoneprobe.*;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;

public class GTTAIntegration {

    public static void init() {

        ITheOneProbe oneProbe = TheOneProbe.theOneProbeImp;
        oneProbe.registerProvider(new MultiblockFaceProvider());
        oneProbe.registerProvider(new ElectricContainerIOInfoProvider());
        oneProbe.registerProvider(new CableInfoProvider());
        oneProbe.registerProvider(new MultiblockTemperatureProvider());
        oneProbe.registerProvider(new RecipeParallelInfoProvider());
        oneProbe.registerProvider(new RecipeItemOutputInfoProvider());
        oneProbe.registerProvider(new RecipeFluidOutputInfoProvider());
        oneProbe.registerProvider(new MultiblockCLRProvider());
        oneProbe.registerProvider(new MultiblockPSSProvider());
    }


    public GTTAIntegration() {
    }
}

