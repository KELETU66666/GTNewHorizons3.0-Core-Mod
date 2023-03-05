package com.github.newhorizons.common.gregtech.predicate;

import com.github.newhorizons.common.gregtech.metatileentity.MetaTileEntityEssentiaGenerator;
import gregtech.api.pattern.TraceabilityPredicate;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPredicate extends TraceabilityPredicate {

    public TileEntityPredicate(Class<? extends TileEntity> tileClazz, MetaTileEntityEssentiaGenerator host) {
        super(blockWorldState -> {
            TileEntity te = blockWorldState.getTileEntity();
            if (tileClazz.isInstance(te)) {
                host.addEssentiaHatch(te);
                return true;
            }
            return false;
        });
    }

    public static TileEntityPredicate get(Class<? extends TileEntity> tileClazz, MetaTileEntityEssentiaGenerator host) {
        return new TileEntityPredicate(tileClazz, host);
    }

}
