package com.github.newhorizons.client;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleSidedCubeRenderer;

public class GTMTextures {

    public static SimpleOverlayRenderer MAGIC_CASING;
    public static SimpleOverlayRenderer FISHING_CASING;
    public static OrientedOverlayRenderer LARGE_ESSENTIA_GENERATOR;
    public static SimpleSidedCubeRenderer LIGHINING_ROD;
    public static SimpleSidedCubeRenderer LIGHINING_ROD_ACTIVE;

    public static void preInit() {
        MAGIC_CASING = new SimpleOverlayRenderer("magic_machine_casing");
        FISHING_CASING = new SimpleOverlayRenderer("fishing_machine_casing");
        LARGE_ESSENTIA_GENERATOR = new OrientedOverlayRenderer("multiblock/large_essentia_generator", new OrientedOverlayRenderer.OverlayFace[]{OrientedOverlayRenderer.OverlayFace.FRONT});
        LIGHINING_ROD = new SimpleSidedCubeRenderer("casings/lightning_rod/normal");;
        LIGHINING_ROD_ACTIVE = new SimpleSidedCubeRenderer("casings/lightning_rod/active");;
    }

}
