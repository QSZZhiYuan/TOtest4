/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_slam_clone;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_slam_clone.NightwardenSlamCloneEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class NightwardenSlamCloneModel
extends GeoModel<NightwardenSlamCloneEntity> {
    public static final ResourceLocation modelResource = new ResourceLocation("traveloptics", "geo/nightwarden.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation("traveloptics", "textures/models/entity/nightwarden/nightwarden_clone.png");
    public static final ResourceLocation animationResource = new ResourceLocation("traveloptics", "animations/nightwarden_clone.animation.json");

    public ResourceLocation getModelResource(NightwardenSlamCloneEntity object) {
        return modelResource;
    }

    public ResourceLocation getTextureResource(NightwardenSlamCloneEntity object) {
        return textureResource;
    }

    public ResourceLocation getAnimationResource(NightwardenSlamCloneEntity animatable) {
        return animationResource;
    }
}

