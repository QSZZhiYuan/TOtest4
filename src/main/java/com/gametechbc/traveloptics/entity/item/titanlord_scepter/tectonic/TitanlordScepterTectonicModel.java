/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.titanlord_scepter.tectonic;

import com.gametechbc.traveloptics.item.staff.TitanlordScepterTectonicItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TitanlordScepterTectonicModel
extends GeoModel<TitanlordScepterTectonicItem> {
    public ResourceLocation getAnimationResource(TitanlordScepterTectonicItem animatable) {
        return new ResourceLocation("traveloptics", "animations/titanlords_scepter.animation.json");
    }

    public ResourceLocation getModelResource(TitanlordScepterTectonicItem animatable) {
        return new ResourceLocation("traveloptics", "geo/titanlords_scepter.geo.json");
    }

    public ResourceLocation getTextureResource(TitanlordScepterTectonicItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/titanlords_scepter_tectonic.png");
    }
}

