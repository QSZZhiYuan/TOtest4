/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.titanlord_scepter.base;

import com.gametechbc.traveloptics.item.staff.TitanlordScepterItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TitanlordScepterModel
extends GeoModel<TitanlordScepterItem> {
    public ResourceLocation getAnimationResource(TitanlordScepterItem animatable) {
        return new ResourceLocation("traveloptics", "animations/titanlords_scepter.animation.json");
    }

    public ResourceLocation getModelResource(TitanlordScepterItem animatable) {
        return new ResourceLocation("traveloptics", "geo/titanlords_scepter.geo.json");
    }

    public ResourceLocation getTextureResource(TitanlordScepterItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/titanlords_scepter.png");
    }
}

