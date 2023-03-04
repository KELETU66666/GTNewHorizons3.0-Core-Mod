package com.github.newhorizons.proxy;

import com.github.newhorizons.item.itemList;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    public void preInit( FMLPreInitializationEvent event )
    {
        super.preInit( event );
    }

    public void init( FMLInitializationEvent event )
    {
        super.init(event);
    }

    public void onModelRegister() {
        for (int i = 0; i < itemList.ListItem.length; i++)
            ModelLoader.setCustomModelResourceLocation(itemIL, i, new ModelResourceLocation("gtnhcore" + ":" + itemList.ListItem[i], "inventory"));
    }
}