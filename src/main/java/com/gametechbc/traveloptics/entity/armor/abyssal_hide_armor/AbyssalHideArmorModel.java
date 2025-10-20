/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.armor.abyssal_hide_armor;

import com.gametechbc.traveloptics.item.armor.AbyssalHideArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AbyssalHideArmorModel
extends GeoModel<AbyssalHideArmorItem> {
    public ResourceLocation getAnimationResource(AbyssalHideArmorItem object) {
        return new ResourceLocation("traveloptics", "animations/abyssal_hide_mage_armor.animation.json");
    }

    public ResourceLocation getModelResource(AbyssalHideArmorItem object) {
        return new ResourceLocation("traveloptics", "geo/abyssal_hide_armor.geo.json");
    }

    public ResourceLocation getTextureResource(AbyssalHideArmorItem object) {
        return new ResourceLocation("traveloptics", "textures/models/armor/abyssal_hide_mage_armor.png");
    }
}

