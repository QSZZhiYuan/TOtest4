/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.armor.deepling_mage_armor;

import com.gametechbc.traveloptics.item.armor.DeeplingMageArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DeeplingMageArmorModel
extends GeoModel<DeeplingMageArmorItem> {
    public ResourceLocation getAnimationResource(DeeplingMageArmorItem object) {
        return new ResourceLocation("traveloptics", "animations/deepling_mage_armor.animation.json");
    }

    public ResourceLocation getModelResource(DeeplingMageArmorItem object) {
        return new ResourceLocation("traveloptics", "geo/deepling_mage_armor.geo.json");
    }

    public ResourceLocation getTextureResource(DeeplingMageArmorItem object) {
        return new ResourceLocation("traveloptics", "textures/models/armor/deepling_mage_armor.png");
    }
}

