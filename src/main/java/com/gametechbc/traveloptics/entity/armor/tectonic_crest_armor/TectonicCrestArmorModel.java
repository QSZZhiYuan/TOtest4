/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.armor.tectonic_crest_armor;

import com.gametechbc.traveloptics.item.armor.TectonicCrestArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TectonicCrestArmorModel
extends GeoModel<TectonicCrestArmorItem> {
    public ResourceLocation getAnimationResource(TectonicCrestArmorItem object) {
        return new ResourceLocation("traveloptics", "animations/tectonic_crest.animation.json");
    }

    public ResourceLocation getModelResource(TectonicCrestArmorItem object) {
        return new ResourceLocation("traveloptics", "geo/tectonic_crest.geo.json");
    }

    public ResourceLocation getTextureResource(TectonicCrestArmorItem object) {
        return new ResourceLocation("traveloptics", "textures/models/armor/tectonic_crest.png");
    }
}

