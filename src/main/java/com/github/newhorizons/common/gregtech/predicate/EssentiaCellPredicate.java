package com.github.newhorizons.common.gregtech.predicate;

import com.github.newhorizons.common.gregtech.GTMMetaBlocks;
import gregtech.api.pattern.PatternStringError;
import gregtech.api.pattern.TraceabilityPredicate;
import net.minecraft.block.state.IBlockState;

import java.util.LinkedList;

public class EssentiaCellPredicate {

    public static TraceabilityPredicate ESSENTIA_CELLS = new TraceabilityPredicate(blockWorldState -> {
        IBlockState blockState = blockWorldState.getBlockState();
        if (GTMMetaBlocks.isEssentiaCell(blockState)) {
            int tier = GTMMetaBlocks.getCellTier(blockState);
            Object currentCell = blockWorldState.getMatchContext().getOrPut("ESS_CELL", tier);
            if (!currentCell.equals(tier)) {
                blockWorldState.setError(new PatternStringError("gtm.multiblock.pattern.error.essentia"));
                return false;
            }
            blockWorldState.getMatchContext().getOrPut("VABlock", new LinkedList<>()).add(blockWorldState.getPos());
            return true;
        }
        return false;
    });

}
