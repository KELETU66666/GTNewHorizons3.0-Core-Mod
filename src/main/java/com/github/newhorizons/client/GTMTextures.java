package com.github.newhorizons.client;

import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;

public class GTMTextures {

    public static SimpleOverlayRenderer MAGIC_CASING;

    public static void preInit() {
        MAGIC_CASING = new SimpleOverlayRenderer("magic_machine_casing");
    }

}
