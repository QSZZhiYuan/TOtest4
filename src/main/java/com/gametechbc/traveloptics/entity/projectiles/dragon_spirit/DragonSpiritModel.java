/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.projectiles.dragon_spirit;

import com.gametechbc.traveloptics.entity.projectiles.dragon_spirit.DragonSpiritEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DragonSpiritModel
extends GeoModel<DragonSpiritEntity> {
    public static final ResourceLocation modelResource = new ResourceLocation("traveloptics", "geo/dragon_spirit.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation("traveloptics", "textures/models/entity/dragon_spirit/dragon_spirit.png");
    public static final ResourceLocation animationResource = new ResourceLocation("traveloptics", "animations/dragon_spirit.animation.json");

    public ResourceLocation getModelResource(DragonSpiritEntity object) {
        return modelResource;
    }

    public ResourceLocation getTextureResource(DragonSpiritEntity object) {
        return textureResource;
    }

    public ResourceLocation getAnimationResource(DragonSpiritEntity animatable) {
        return animationResource;
    }
}

