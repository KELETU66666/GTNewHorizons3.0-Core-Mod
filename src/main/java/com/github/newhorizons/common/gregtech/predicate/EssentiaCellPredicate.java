package com.github.newhorizons.common.gregtech.predicate;

import com.github.newhorizons.common.gregtech.GTMMetaBlocks;
import com.github.newhorizons.common.gregtech.metablock.GTMMetaCasing;
import gregtech.api.pattern.PatternStringError;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.BlockInfo;
import net.minecraft.block.state.IBlockState;

import java.util.Arrays;
import java.util.Comparator;
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
    }, () -> Arrays.stream(GTMMetaCasing.MetalCasingType.values())
            .sorted(Comparator.comparing(GTMMetaCasing.MetalCasingType::getName))
            .map(type -> new BlockInfo(GTMMetaBlocks.GTM_BLOCK_CASING.getState(type), null))
            .toArray(BlockInfo[]::new))
            .addTooltips("gtm.multiblock.pattern.error.essentia");

  //  () -> Arrays.stream(BlockCrucible.CrucibleType.values())
    //        .sorted(Comparator.comparingInt(BlockCrucible.CrucibleType::getTemperature))
   //         .map(type -> new BlockInfo(GCYSMetaBlocks.CRUCIBLE.getState(type), null))
      //      .toArray(BlockInfo[]::new));

}
