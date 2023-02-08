package com.github.newhorizons.proxy;

import com.github.newhorizons.item.itemList;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public static Item itemIL = new itemList();

    public void preInit( FMLPreInitializationEvent event )
    {
    }

    public void init( FMLInitializationEvent event )
    {

    }

    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(itemIL);
    }

    public void onModelRegister(){}
}
