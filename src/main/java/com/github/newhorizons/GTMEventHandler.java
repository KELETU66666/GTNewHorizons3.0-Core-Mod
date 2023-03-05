package com.github.newhorizons;

import com.github.newhorizons.common.gregtech.materials.GTMMaterial;
import com.github.newhorizons.util.libs.Refstrings;
import gregtech.api.GregTechAPI;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Refstrings.MODID)
public class GTMEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onMaterialsInit(GregTechAPI.MaterialEvent event) {
        GTMMaterial.register();
    }

}
