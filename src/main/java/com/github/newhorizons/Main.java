package com.github.newhorizons;
import com.github.newhorizons.proxy.CommonProxy;
import com.github.newhorizons.thaumcraft.Research;
import com.github.newhorizons.util.libs.Refstrings;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = Refstrings.MODID,
        name = Refstrings.NAME,
        version = Refstrings.VERSION,
        dependencies="after:thaumcraft"
)
public class Main {

    @SidedProxy(clientSide = "com.github.newhorizons.proxy.ClientProxy", serverSide = "com.github.newhorizons.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static Logger logger = LogManager.getLogger(Refstrings.MODID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	if(Loader.isModLoaded("thaumcraft"))
                Research.addResearch();
    }

    @Mod.EventBusSubscriber
    public static class ObjectRegistryHandler {
        /** Listen for the register event for creating custom items */
        @SubscribeEvent
        public static void addItems(RegistryEvent.Register<Item> event) {
            proxy.registerItems(event);
        }
        /** Listen for the register event for creating custom blocks */
        @SubscribeEvent
        public static void addBlocks(RegistryEvent.Register<Block> event) {
        }

        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public static void modelRegistryEvent(ModelRegistryEvent event) {
            proxy.onModelRegister();
        }
    }
}
