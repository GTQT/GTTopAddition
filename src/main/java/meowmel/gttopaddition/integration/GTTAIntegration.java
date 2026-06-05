package meowmel.gttopaddition.integration;


import gregtech.common.ConfigHolder;
import keqing.gttopaddition.element.*;
import keqing.gttopaddition.integration.theoneprobe.*;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;
import meowmel.gttopaddition.element.*;
import meowmel.gttopaddition.integration.theoneprobe.*;

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
        oneProbe.registerProvider(new RecipeParallelInfoProvider());
        oneProbe.registerProvider(new MultiblockCLRProvider());
        oneProbe.registerProvider(new MultiblockPSSProvider());
        oneProbe.registerProvider(new ComputationProvider());
        oneProbe.registerProvider(new FusionReactorProvider());
        oneProbe.registerProvider(new MetaTileEntityIOInfo());
        if(ConfigHolder.machines.enablePollution)
            oneProbe.registerProvider(new PollutionInfo());

        FLUID_NAME_ELEMENT = oneProbe.registerElementFactory(FluidNameElement::new);
        FLUID_STACK_ELEMENT = oneProbe.registerElementFactory(FluidStackElement::new);
        CHANCED_ITEM_STACK_ELEMENT = oneProbe.registerElementFactory(ChancedItemStackElement::new);
        CHANCED_FLUID_STACK_ELEMENT = oneProbe.registerElementFactory(ChancedFluidStackElement::new);
        CHANCED_FLUID_NAME_ELEMENT = oneProbe.registerElementFactory(ChancedFluidNameElement::new);
    }


    public GTTAIntegration() {
    }
}

