/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.projectiles.supernova.supermassive_black_hole;

import com.gametechbc.traveloptics.entity.projectiles.supernova.supermassive_black_hole.SupermassiveBlackHoleEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SupermassiveBlackHoleModel
extends GeoModel<SupermassiveBlackHoleEntity> {
    public static final ResourceLocation modelResource = new ResourceLocation("traveloptics", "geo/supermassive_blackhole.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation("traveloptics", "textures/models/entity/supermassive_black_hole/supermassive_blackhole.png");
    public static final ResourceLocation animationResource = new ResourceLocation("traveloptics", "animations/super_massive_blackhole.animation.json");

    public ResourceLocation getModelResource(SupermassiveBlackHoleEntity object) {
        return modelResource;
    }

    public ResourceLocation getTextureResource(SupermassiveBlackHoleEntity object) {
        return textureResource;
    }

    public ResourceLocation getAnimationResource(SupermassiveBlackHoleEntity animatable) {
        return animationResource;
    }
}

