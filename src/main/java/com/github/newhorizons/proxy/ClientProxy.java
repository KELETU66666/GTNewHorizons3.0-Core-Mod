package com.github.newhorizons.proxy;

import com.github.newhorizons.client.GTMTextures;
import com.github.newhorizons.common.gregtech.CommonBlocks;
import com.github.newhorizons.common.gregtech.GTMMetaBlocks;
import com.github.newhorizons.common.item.itemList;
import com.github.newhorizons.util.libs.Refstrings;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    public void preInit( FMLPreInitializationEvent event ) {
        super.preInit(event);
        GTMTextures.preInit();
    }

    public void init( FMLInitializationEvent event ) {
        super.init(event);
    }

    public void onModelRegister() {
        GTMMetaBlocks.registerItemModels();
        CommonBlocks.registerItemModels();
        for (int i = 0; i < itemList.ListItem.length; i++)
            ModelLoader.setCustomModelResourceLocation(itemIL, i, new ModelResourceLocation(Refstrings.MODID + ":" + itemList.ListItem[i], "inventory"));
    }
}