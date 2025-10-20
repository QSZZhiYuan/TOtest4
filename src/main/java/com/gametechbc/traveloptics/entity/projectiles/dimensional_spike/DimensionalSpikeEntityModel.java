/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.projectiles.dimensional_spike;

import com.gametechbc.traveloptics.entity.projectiles.dimensional_spike.DimensionalSpikeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DimensionalSpikeEntityModel
extends GeoModel<DimensionalSpikeEntity> {
    public static final ResourceLocation modelResource = new ResourceLocation("traveloptics", "geo/dimensional_spike.geo.json");
    public static final ResourceLocation obsidianTextureResource = new ResourceLocation("traveloptics", "textures/models/entity/dimensional_spike/dimensional_spike_obsidian.png");
    public static final ResourceLocation endStoneTextureResource = new ResourceLocation("traveloptics", "textures/models/entity/dimensional_spike/dimensional_spike_end_stone.png");
    public static final ResourceLocation animationResource = new ResourceLocation("traveloptics", "animations/dimensional_spike.animation.json");

    public ResourceLocation getModelResource(DimensionalSpikeEntity object) {
        return modelResource;
    }

    public ResourceLocation getTextureResource(DimensionalSpikeEntity object) {
        if (object.isEndStone()) {
            return endStoneTextureResource;
        }
        return obsidianTextureResource;
    }

    public ResourceLocation getAnimationResource(DimensionalSpikeEntity animatable) {
        return animationResource;
    }
}

