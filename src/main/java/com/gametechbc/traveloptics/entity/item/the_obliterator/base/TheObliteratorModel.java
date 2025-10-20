/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.the_obliterator.base;

import com.gametechbc.traveloptics.item.bossweapon.the_obliterator.TheObliteratorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TheObliteratorModel
extends GeoModel<TheObliteratorItem> {
    public ResourceLocation getAnimationResource(TheObliteratorItem animatable) {
        return new ResourceLocation("traveloptics", "animations/the_obliterator.animation.json");
    }

    public ResourceLocation getModelResource(TheObliteratorItem animatable) {
        return new ResourceLocation("traveloptics", "geo/the_obliterator.geo.json");
    }

    public ResourceLocation getTextureResource(TheObliteratorItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/the_obliterator.png");
    }
}

