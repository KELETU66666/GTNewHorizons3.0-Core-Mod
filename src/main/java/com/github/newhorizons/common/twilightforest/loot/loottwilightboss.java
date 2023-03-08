package com.github.newhorizons.common.twilightforest.loot;

import com.github.newhorizons.proxy.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import twilightforest.entity.boss.EntityTFLich;
import twilightforest.entity.boss.EntityTFSnowQueen;

@Mod.EventBusSubscriber
public class loottwilightboss {
    @SubscribeEvent
    public static void onDrops(LivingDropsEvent event) {
        Entity e = event.getEntity();
        if(e instanceof EntityTFLich) {
            event.getDrops().add(new EntityItem(e.world, e.posX, e.posY, e.posZ, new ItemStack(CommonProxy.itemIL, 3, 4)));
        }
        if(e instanceof EntityTFSnowQueen){
            event.getDrops().add(new EntityItem(e.world, e.posX, e.posY, e.posZ, new ItemStack(CommonProxy.itemIL, 3, 10)));
        }
    }
}
