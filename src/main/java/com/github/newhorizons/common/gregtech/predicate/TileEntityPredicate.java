package com.github.newhorizons.common.gregtech.predicate;

import com.github.newhorizons.common.gregtech.CommonBlocks;
import com.github.newhorizons.common.gregtech.GTMMetaBlocks;
import com.github.newhorizons.common.gregtech.metablock.GTMMetaCasing;
import com.github.newhorizons.common.gregtech.metatileentity.MetaTileEntityEssentiaGenerator;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.BlockInfo;
import net.minecraft.tileentity.TileEntity;

import java.util.Arrays;
import java.util.Comparator;

public class TileEntityPredicate extends TraceabilityPredicate {

    public TileEntityPredicate(Class<? extends TileEntity> tileClazz, MetaTileEntityEssentiaGenerator host) {
        super(blockWorldState -> {
            TileEntity te = blockWorldState.getTileEntity();
            if (tileClazz.isInstance(te)) {
                host.addEssentiaHatch(te);
                return true;
            }
            return false;
        }, () -> Arrays.stream(GTMMetaCasing.MetalCasingType.values())
                .sorted(Comparator.comparing(GTMMetaCasing.MetalCasingType::getName))
                .map(type -> new BlockInfo(CommonBlocks.ESSENTIA_HATCH.getDefaultState(), null))
                .toArray(BlockInfo[]::new));
    }

    public static TileEntityPredicate get(Class<? extends TileEntity> tileClazz, MetaTileEntityEssentiaGenerator host) {
        return new TileEntityPredicate(tileClazz, host);
    }

}
