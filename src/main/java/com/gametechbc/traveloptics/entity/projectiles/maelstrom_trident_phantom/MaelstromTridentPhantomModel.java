/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.projectiles.maelstrom_trident_phantom;

import com.gametechbc.traveloptics.entity.projectiles.maelstrom_trident_phantom.MaelstromTridentPhantomEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MaelstromTridentPhantomModel
extends GeoModel<MaelstromTridentPhantomEntity> {
    public static final ResourceLocation modelResource = new ResourceLocation("traveloptics", "geo/maelstorm_trident_phantom_entity.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation("traveloptics", "textures/models/entity/maelstrom_trident_phantom/maelstrom_trident_phantom_entity.png");
    public static final ResourceLocation animationResource = new ResourceLocation("traveloptics", "animations/maelstorm_trident_phantom_entity.animation.json");

    public ResourceLocation getModelResource(MaelstromTridentPhantomEntity object) {
        return modelResource;
    }

    public ResourceLocation getTextureResource(MaelstromTridentPhantomEntity object) {
        return textureResource;
    }

    public ResourceLocation getAnimationResource(MaelstromTridentPhantomEntity animatable) {
        return animationResource;
    }
}

