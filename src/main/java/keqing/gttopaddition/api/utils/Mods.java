package keqing.gttopaddition.api.utils;

import net.minecraftforge.fml.common.Loader;

import java.util.function.Function;

public enum Mods {
    GregicalityMultiblocks("gcym");

    private final String ID;
    private final Function<Mods, Boolean> extraCheck;
    private Boolean modLoaded;

    Mods(String ID) {
        this.ID =ID;
        this.extraCheck = null;
    }

    public boolean isModLoaded() {
        //  Assign all Mod in this class a {@link modLoaded} parameter.
        if (this.modLoaded == null) {
            this.modLoaded = Loader.isModLoaded(this.ID);
            if (this.modLoaded) {
                //  If Mod has {@link extraCheck}, and it is not meeting the conditions,
                //  then return false (this is also a extended predicate condiction).
                if (this.extraCheck != null && !this.extraCheck.apply(this)) {
                    this.modLoaded = false;
                }
            }
        }
        return this.modLoaded;
    }
}
