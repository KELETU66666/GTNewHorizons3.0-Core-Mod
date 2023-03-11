package com.github.newhorizons.proxy;

import com.github.newhorizons.common.gregtech.GTMMetaEntities;
import com.github.newhorizons.common.gregtech.materials.GTMMaterial;
import com.github.newhorizons.common.gregtech.tileentity.EssentiaHatch;
import com.github.newhorizons.common.item.itemList;
import com.github.newhorizons.common.item.itemUpgrade;
import com.github.newhorizons.common.thaumcraft.LargeEssentiaEnergyData;
import gregtech.api.block.VariantItemBlock;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

import java.util.Objects;
import java.util.function.Function;

import static com.github.newhorizons.common.gregtech.CommonBlocks.ESSENTIA_HATCH;
import static com.github.newhorizons.common.gregtech.GTMMetaBlocks.GTM_BLOCK_CASING;

public class CommonProxy {

    public static Item itemIL = new itemList();
    public static Item Upgrades = new itemUpgrade();

    public static final CreativeTabs Tab = new CreativeTabs("gtmagiccoremod") {
        @Override
        @Nonnull
        public ItemStack createIcon() {
            return new ItemStack(itemIL, 1, 0);
        }
    };

    public void preInit( FMLPreInitializationEvent event ) {
        GTMMetaEntities.register();
        LargeEssentiaEnergyData.processEssentiaData();
    }

    public void init( FMLInitializationEvent event ) {
    }

    public void registerItems(RegistryEvent.Register<Item> event) {
        itemIL.setCreativeTab(Tab);
        Upgrades.setCreativeTab(Tab);
        event.getRegistry().register(itemIL);
        event.getRegistry().register(Upgrades);
        event.getRegistry().register(createItemBlock(GTM_BLOCK_CASING, VariantItemBlock::new));
        event.getRegistry().register(createItemBlock(ESSENTIA_HATCH, ItemBlock::new));
    }

    public void registerBlocks(RegistryEvent.Register<Block> event) {
        GTM_BLOCK_CASING.setCreativeTab(Tab);
        ESSENTIA_HATCH.setCreativeTab(Tab);
        event.getRegistry().register(GTM_BLOCK_CASING);
        event.getRegistry().register(ESSENTIA_HATCH);
        GameRegistry.registerTileEntity(EssentiaHatch.class, Objects.requireNonNull(ESSENTIA_HATCH.getRegistryName()));
    }

    public void onModelRegister() {

    }

    private static <T extends Block> ItemBlock createItemBlock(@Nonnull T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        itemBlock.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
        return itemBlock;
    }
}
