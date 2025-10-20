/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.projectiles.maelstrom;

import com.gametechbc.traveloptics.entity.projectiles.maelstrom.MaelstromEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MaelstromModel
extends GeoModel<MaelstromEntity> {
    public static final ResourceLocation modelResource = new ResourceLocation("traveloptics", "geo/maelstrom.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation("traveloptics", "textures/models/entity/maelstrom.png");
    public static final ResourceLocation animationResource = new ResourceLocation("traveloptics", "animations/maelstrom_entity.animation.json");

    public ResourceLocation getModelResource(MaelstromEntity object) {
        return modelResource;
    }

    public ResourceLocation getTextureResource(MaelstromEntity object) {
        return textureResource;
    }

    public ResourceLocation getAnimationResource(MaelstromEntity animatable) {
        return animationResource;
    }
}

