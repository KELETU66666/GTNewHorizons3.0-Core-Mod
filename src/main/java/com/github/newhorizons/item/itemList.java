package com.github.newhorizons.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

public class itemList extends Item{

    public itemList() {
        super();
        setCreativeTab(CreativeTabs.MISC);
        setRegistryName("packresource");
        setHasSubtypes(true);
    }

    public static final String[] ListItem = new String[] {
            "itemArtificialLeather",
            "itemAsteroidsStoneDust",
            "itemDraconiumEgg",
            "itemEnderEgg",
            "itemLichBone",
            "itemMarsStoneDust",
            "itemMoonStoneDust",
            "itemMutantEgg",
            "itemReinforcedGlassLense",
            "itemReinforcedGlassPlate",
            "itemSnowQueenBlood",
            "itemTheBigEgg",
            "itemTungstenString",
            "itemTwilightCrystal",
            "itemVenusStoneDust",
            "itemWetTofu",
            "itemDraconicSchematic",
            "itemWyvernSchematic",
            "itemAwakenedSchematic",
            "itemChaoticSchematic",
            "itemRobotArm"
    };

    @Override
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items) {
        if (!isInCreativeTab(tab)) {
            return;
        }
        for (int i = 0; i < ListItem.length; i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    @Nonnull
    public String getTranslationKey(@Nonnull ItemStack stack) {
        return "item.packresource." + stack.getItemDamage();
    }

}