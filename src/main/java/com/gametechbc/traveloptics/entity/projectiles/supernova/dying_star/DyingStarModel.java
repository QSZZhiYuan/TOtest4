/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.projectiles.supernova.dying_star;

import com.gametechbc.traveloptics.entity.projectiles.supernova.dying_star.DyingStarEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DyingStarModel
extends GeoModel<DyingStarEntity> {
    public static final ResourceLocation modelResource = new ResourceLocation("traveloptics", "geo/dying_star.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation("traveloptics", "textures/models/entity/dying_star/dying_star.png");
    public static final ResourceLocation redTextureResource = new ResourceLocation("traveloptics", "textures/models/entity/dying_star/dying_star_red_giant.png");
    public static final ResourceLocation animationResource = new ResourceLocation("traveloptics", "animations/dying_star.animation.json");

    public ResourceLocation getModelResource(DyingStarEntity object) {
        return modelResource;
    }

    public ResourceLocation getTextureResource(DyingStarEntity object) {
        return object.isAntiMagicVulnerable() ? redTextureResource : textureResource;
    }

    public ResourceLocation getAnimationResource(DyingStarEntity animatable) {
        return animationResource;
    }
}

