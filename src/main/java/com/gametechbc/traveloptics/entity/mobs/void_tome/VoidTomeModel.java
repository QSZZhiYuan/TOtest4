/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.mobs.void_tome;

import com.gametechbc.traveloptics.entity.mobs.void_tome.VoidTomeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VoidTomeModel
extends GeoModel<VoidTomeEntity> {
    public static final ResourceLocation MODEL = new ResourceLocation("traveloptics", "geo/void_tome.geo.json");
    public static final ResourceLocation TEXTURE = new ResourceLocation("traveloptics", "textures/models/entity/void_tome.png");
    public static final ResourceLocation ANIMATION = new ResourceLocation("traveloptics", "animations/flying_book.animation.json");

    public ResourceLocation getModelResource(VoidTomeEntity object) {
        return MODEL;
    }

    public ResourceLocation getTextureResource(VoidTomeEntity object) {
        return TEXTURE;
    }

    public ResourceLocation getAnimationResource(VoidTomeEntity object) {
        return ANIMATION;
    }
}

