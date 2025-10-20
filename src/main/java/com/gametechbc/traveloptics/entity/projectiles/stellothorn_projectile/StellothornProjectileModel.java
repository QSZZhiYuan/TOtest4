/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.projectiles.stellothorn_projectile;

import com.gametechbc.traveloptics.entity.projectiles.stellothorn_projectile.StellothornProjectileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class StellothornProjectileModel
extends GeoModel<StellothornProjectileEntity> {
    public static final ResourceLocation modelResource = new ResourceLocation("traveloptics", "geo/stellothorn_projectile.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation("traveloptics", "textures/models/entity/stellothorn_projectile/stellothorn.png");
    public static final ResourceLocation animationResource = new ResourceLocation("traveloptics", "animations/stellothorn_projectile.animation.json");

    public ResourceLocation getModelResource(StellothornProjectileEntity object) {
        return modelResource;
    }

    public ResourceLocation getTextureResource(StellothornProjectileEntity object) {
        return textureResource;
    }

    public ResourceLocation getAnimationResource(StellothornProjectileEntity animatable) {
        return animationResource;
    }
}

