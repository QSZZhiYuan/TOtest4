/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.titanlord_scepter.retro;

import com.gametechbc.traveloptics.item.staff.TitanlordScepterRetroItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TitanlordScepterRetroModel
extends GeoModel<TitanlordScepterRetroItem> {
    public ResourceLocation getAnimationResource(TitanlordScepterRetroItem animatable) {
        return new ResourceLocation("traveloptics", "animations/titanlords_scepter.animation.json");
    }

    public ResourceLocation getModelResource(TitanlordScepterRetroItem animatable) {
        return new ResourceLocation("traveloptics", "geo/titanlords_scepter.geo.json");
    }

    public ResourceLocation getTextureResource(TitanlordScepterRetroItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/titanlords_scepter_retro.png");
    }
}

