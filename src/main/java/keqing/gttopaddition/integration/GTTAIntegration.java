package keqing.gttopaddition.integration;


import keqing.gttopaddition.element.*;
import keqing.gttopaddition.integration.theoneprobe.*;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;

public class GTTAIntegration {

    public static int FLUID_NAME_ELEMENT;
    public static int FLUID_STACK_ELEMENT;
    public static int CHANCED_ITEM_STACK_ELEMENT;
    public static int CHANCED_FLUID_STACK_ELEMENT;
    public static int CHANCED_FLUID_NAME_ELEMENT;

    public static void init() {

        ITheOneProbe oneProbe = TheOneProbe.theOneProbeImp;
        oneProbe.registerProvider(new MultiblockFaceProvider());
        oneProbe.registerProvider(new CableInfoProvider());
        oneProbe.registerProvider(new RecipeOutputInfoProvider());
        oneProbe.registerProvider(new MultiblockTemperatureProvider());
        oneProbe.registerProvider(new RecipeParallelInfoProvider());
        oneProbe.registerProvider(new MultiblockCLRProvider());
        oneProbe.registerProvider(new MultiblockPSSProvider());
        oneProbe.registerProvider(new ComputationProvider());
        oneProbe.registerProvider(new FusionReactorProvider());

        FLUID_NAME_ELEMENT = oneProbe.registerElementFactory(FluidNameElement::new);
        FLUID_STACK_ELEMENT = oneProbe.registerElementFactory(FluidStackElement::new);
        CHANCED_ITEM_STACK_ELEMENT = oneProbe.registerElementFactory(ChancedItemStackElement::new);
        CHANCED_FLUID_STACK_ELEMENT = oneProbe.registerElementFactory(ChancedFluidStackElement::new);
        CHANCED_FLUID_NAME_ELEMENT = oneProbe.registerElementFactory(ChancedFluidNameElement::new);
    }


    public GTTAIntegration() {
    }
}

