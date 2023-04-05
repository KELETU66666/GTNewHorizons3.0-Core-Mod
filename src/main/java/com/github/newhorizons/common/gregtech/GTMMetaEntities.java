package com.github.newhorizons.common.gregtech;

import com.github.newhorizons.common.gregtech.metatileentity.MetaTileEntityEssentiaGenerator;
import com.github.newhorizons.common.gregtech.metatileentity.MetaTileEntityIndustrialFishingPond;
import com.github.newhorizons.common.gregtech.metatileentity.MetaTileEntityLightningRod;
import com.github.newhorizons.util.libs.Refstrings;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.SimpleGeneratorMetaTileEntity;
import net.minecraft.util.ResourceLocation;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

public class GTMMetaEntities {

    public static MetaTileEntityEssentiaGenerator ESSENTIA_GENERATOR;
    public static MetaTileEntityIndustrialFishingPond INDUSTRIAL_POND;
    public static final MetaTileEntityLightningRod[] LIGHTNING_ROD = new MetaTileEntityLightningRod[4];

    public static void register() {
        ESSENTIA_GENERATOR = registerMetaTileEntity(11001, new MetaTileEntityEssentiaGenerator(gtmID("essentia_generator")));
        INDUSTRIAL_POND = registerMetaTileEntity(11002, new MetaTileEntityIndustrialFishingPond(gtmID("industrial_fishing_pond")));
        LIGHTNING_ROD[0] = registerMetaTileEntity(11003, new MetaTileEntityLightningRod(gtmID("lightning_rod.hv"), GTValues.HV));
        LIGHTNING_ROD[1] = registerMetaTileEntity(11004, new MetaTileEntityLightningRod(gtmID("lightning_rod.ev"), GTValues.EV));
        LIGHTNING_ROD[2] = registerMetaTileEntity(11005, new MetaTileEntityLightningRod(gtmID("lightning_rod.iv"), GTValues.IV));
    }

    private static ResourceLocation gtmID(String name) {
        return new ResourceLocation(Refstrings.MODID, name);
    }

}
