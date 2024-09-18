package keqing.gttopaddition.api.gui.impl;

import keqing.gttopaddition.api.gui.IFluidStyle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@Accessors(chain = true, fluent = true)
@NoArgsConstructor
public class FluidStyle implements IFluidStyle {

    private int width = 20;
    private int height = 20;

    public IFluidStyle copy() {
        return new FluidStyle().bounds(this.width, this.height);
    }
}
