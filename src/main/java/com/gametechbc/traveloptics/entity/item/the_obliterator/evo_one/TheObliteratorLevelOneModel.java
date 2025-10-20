/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.the_obliterator.evo_one;

import com.gametechbc.traveloptics.item.bossweapon.the_obliterator.TheObliteratorLevelOneItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TheObliteratorLevelOneModel
extends GeoModel<TheObliteratorLevelOneItem> {
    public ResourceLocation getAnimationResource(TheObliteratorLevelOneItem animatable) {
        return new ResourceLocation("traveloptics", "animations/the_obliterator.animation.json");
    }

    public ResourceLocation getModelResource(TheObliteratorLevelOneItem animatable) {
        return new ResourceLocation("traveloptics", "geo/the_obliterator.geo.json");
    }

    public ResourceLocation getTextureResource(TheObliteratorLevelOneItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/the_obliterator.png");
    }
}

