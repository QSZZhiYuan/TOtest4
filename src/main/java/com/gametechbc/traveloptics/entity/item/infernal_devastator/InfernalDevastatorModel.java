/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.infernal_devastator;

import com.gametechbc.traveloptics.item.bossweapon.infernal_devastator.InfernalDevastatorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class InfernalDevastatorModel
extends GeoModel<InfernalDevastatorItem> {
    public ResourceLocation getAnimationResource(InfernalDevastatorItem animatable) {
        return new ResourceLocation("traveloptics", "animations/infernal_devastator.animation.json");
    }

    public ResourceLocation getModelResource(InfernalDevastatorItem animatable) {
        return new ResourceLocation("traveloptics", "geo/infernal_devastator.geo.json");
    }

    public ResourceLocation getTextureResource(InfernalDevastatorItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/infernal_devastator.png");
    }
}

