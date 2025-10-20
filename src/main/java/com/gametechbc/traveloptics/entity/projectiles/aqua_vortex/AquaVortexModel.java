/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.projectiles.aqua_vortex;

import com.gametechbc.traveloptics.entity.projectiles.aqua_vortex.AquaVortexEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AquaVortexModel
extends GeoModel<AquaVortexEntity> {
    public static final ResourceLocation modelResource = new ResourceLocation("traveloptics", "geo/aqua_vortex.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation("traveloptics", "textures/models/entity/aqua_vortex.png");
    public static final ResourceLocation animationResource = new ResourceLocation("traveloptics", "animations/aqua_vortex.animation.json");

    public ResourceLocation getModelResource(AquaVortexEntity object) {
        return modelResource;
    }

    public ResourceLocation getTextureResource(AquaVortexEntity object) {
        return textureResource;
    }

    public ResourceLocation getAnimationResource(AquaVortexEntity animatable) {
        return animationResource;
    }
}

