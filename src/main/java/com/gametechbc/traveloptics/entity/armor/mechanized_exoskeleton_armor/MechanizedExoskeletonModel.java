/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.armor.mechanized_exoskeleton_armor;

import com.gametechbc.traveloptics.item.armor.MechanizedExoskeletonArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MechanizedExoskeletonModel
extends GeoModel<MechanizedExoskeletonArmorItem> {
    public ResourceLocation getAnimationResource(MechanizedExoskeletonArmorItem object) {
        return new ResourceLocation("traveloptics", "animations/mechanized_exoskeleton.animation.json");
    }

    public ResourceLocation getModelResource(MechanizedExoskeletonArmorItem object) {
        return new ResourceLocation("traveloptics", "geo/mechanized_exoskeleton.geo.json");
    }

    public ResourceLocation getTextureResource(MechanizedExoskeletonArmorItem object) {
        return new ResourceLocation("traveloptics", "textures/models/armor/mechanized_exoskeleton.png");
    }
}

