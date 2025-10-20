/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.projectiles.end_eruption_bomb;

import com.gametechbc.traveloptics.entity.projectiles.end_eruption_bomb.EruptionBombProjectileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EruptionBombProjectileModel
extends GeoModel<EruptionBombProjectileEntity> {
    public static final ResourceLocation modelResource = new ResourceLocation("traveloptics", "geo/end_eruption_bomb.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation("traveloptics", "textures/models/entity/end_eruption_bomb/end_eruption_bomb.png");
    public static final ResourceLocation animationResource = new ResourceLocation("traveloptics", "animations/end_eruption_bomb.animation.json");

    public ResourceLocation getModelResource(EruptionBombProjectileEntity object) {
        return modelResource;
    }

    public ResourceLocation getTextureResource(EruptionBombProjectileEntity object) {
        return textureResource;
    }

    public ResourceLocation getAnimationResource(EruptionBombProjectileEntity animatable) {
        return animationResource;
    }
}

