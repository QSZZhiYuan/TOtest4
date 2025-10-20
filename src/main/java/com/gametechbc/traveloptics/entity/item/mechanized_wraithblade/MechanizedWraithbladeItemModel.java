/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.mechanized_wraithblade;

import com.gametechbc.traveloptics.item.bossweapon.mechanized_wraithblade.MechanizedWraithbladeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MechanizedWraithbladeItemModel
extends GeoModel<MechanizedWraithbladeItem> {
    public ResourceLocation getAnimationResource(MechanizedWraithbladeItem animatable) {
        return new ResourceLocation("traveloptics", "animations/mechanized_wraithblade.animation.json");
    }

    public ResourceLocation getModelResource(MechanizedWraithbladeItem animatable) {
        return new ResourceLocation("traveloptics", "geo/mechanized_wraithblade.geo.json");
    }

    public ResourceLocation getTextureResource(MechanizedWraithbladeItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/mechanized_wraithblade.png");
    }
}

