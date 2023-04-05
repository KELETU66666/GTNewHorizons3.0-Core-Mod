package com.github.newhorizons.common.gregtech;

import com.github.newhorizons.common.gregtech.metatileentity.MetaTileEntityEssentiaGenerator;
import com.github.newhorizons.common.gregtech.metatileentity.MetaTileEntityIndustrialFishingPond;
import com.github.newhorizons.common.gregtech.metatileentity.MetaTileEntityLightningRod;
import com.github.newhorizons.util.libs.Refstrings;
import net.minecraft.util.ResourceLocation;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

public class GTMMetaEntities {

    public static MetaTileEntityEssentiaGenerator ESSENTIA_GENERATOR;
    public static MetaTileEntityIndustrialFishingPond INDUSTRIAL_POND;
    public static MetaTileEntityLightningRod LIGHTNING_ROD;

    public static void register() {
        ESSENTIA_GENERATOR = registerMetaTileEntity(11001, new MetaTileEntityEssentiaGenerator(gtmID("essentia_generator")));
        INDUSTRIAL_POND = registerMetaTileEntity(11002, new MetaTileEntityIndustrialFishingPond(gtmID("industrial_fishing_pond")));
        LIGHTNING_ROD = registerMetaTileEntity(11003, new MetaTileEntityLightningRod(gtmID("lightning_rod")));

    }

    private static ResourceLocation gtmID(String name) {
        return new ResourceLocation(Refstrings.MODID, name);
    }

}
