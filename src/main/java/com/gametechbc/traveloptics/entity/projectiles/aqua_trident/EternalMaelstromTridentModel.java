/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.projectiles.aqua_trident;

import com.gametechbc.traveloptics.entity.projectiles.aqua_trident.EternalMaelstromTridentEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EternalMaelstromTridentModel
extends GeoModel<EternalMaelstromTridentEntity> {
    public static final ResourceLocation modelResource = new ResourceLocation("traveloptics", "geo/trident_of_the_eternal_maelstrom_entity.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation("traveloptics", "textures/models/entity/trident_of_the_eternal_maelstrom_entity.png");
    public static final ResourceLocation animationResource = new ResourceLocation("traveloptics", "animations/abyssal_hide_mage_armor.animation.json");

    public ResourceLocation getModelResource(EternalMaelstromTridentEntity object) {
        return modelResource;
    }

    public ResourceLocation getTextureResource(EternalMaelstromTridentEntity object) {
        return textureResource;
    }

    public ResourceLocation getAnimationResource(EternalMaelstromTridentEntity animatable) {
        return animationResource;
    }
}

