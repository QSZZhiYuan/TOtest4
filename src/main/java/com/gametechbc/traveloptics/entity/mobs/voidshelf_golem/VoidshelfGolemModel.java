/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.mobs.voidshelf_golem;

import com.gametechbc.traveloptics.entity.mobs.voidshelf_golem.VoidshelfGolemEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VoidshelfGolemModel
extends GeoModel<VoidshelfGolemEntity> {
    public static final ResourceLocation modelResource = new ResourceLocation("traveloptics", "geo/voidshelf_golem.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation("traveloptics", "textures/models/entity/voidshelf_golem/voidshelf_golem_obsidian_camo.png");
    public static final ResourceLocation animationResource = new ResourceLocation("traveloptics", "animations/voidshelf_golem.animation.json");

    public ResourceLocation getModelResource(VoidshelfGolemEntity object) {
        return modelResource;
    }

    public ResourceLocation getTextureResource(VoidshelfGolemEntity object) {
        if (object.isCamouflage()) {
            return new ResourceLocation("traveloptics", "textures/models/entity/voidshelf_golem/voidshelf_golem_chorus_camo.png");
        }
        return textureResource;
    }

    public ResourceLocation getAnimationResource(VoidshelfGolemEntity animatable) {
        return animationResource;
    }
}

