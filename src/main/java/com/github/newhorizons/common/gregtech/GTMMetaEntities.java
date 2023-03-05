package com.github.newhorizons.common.gregtech;

import com.github.newhorizons.common.gregtech.metatileentity.MetaTileEntityEssentiaGenerator;
import com.github.newhorizons.util.libs.Refstrings;
import net.minecraft.util.ResourceLocation;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

public class GTMMetaEntities {

    public static MetaTileEntityEssentiaGenerator ESSENTIA_GENERATOR;

    public static void register() {
        ESSENTIA_GENERATOR = registerMetaTileEntity(11001, new MetaTileEntityEssentiaGenerator(gtmID("essentia_generator")));
    }

    private static ResourceLocation gtmID(String name) {
        return new ResourceLocation(Refstrings.MODID, name);
    }

}
