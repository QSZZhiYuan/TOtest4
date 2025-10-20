/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.armor.primordial_crest_armor;

import com.gametechbc.traveloptics.item.armor.PrimordialCrestArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PrimordialCrestArmorModel
extends GeoModel<PrimordialCrestArmorItem> {
    public ResourceLocation getAnimationResource(PrimordialCrestArmorItem object) {
        return new ResourceLocation("traveloptics", "animations/primordial_crest.animation.json");
    }

    public ResourceLocation getModelResource(PrimordialCrestArmorItem object) {
        return new ResourceLocation("traveloptics", "geo/primordial_crest.geo.json");
    }

    public ResourceLocation getTextureResource(PrimordialCrestArmorItem object) {
        return new ResourceLocation("traveloptics", "textures/models/armor/primordial_crest.png");
    }
}

