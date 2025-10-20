/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.the_obliterator.evo_two;

import com.gametechbc.traveloptics.item.bossweapon.the_obliterator.TheObliteratorLevelTwoItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TheObliteratorLevelTwoModel
extends GeoModel<TheObliteratorLevelTwoItem> {
    public ResourceLocation getAnimationResource(TheObliteratorLevelTwoItem animatable) {
        return new ResourceLocation("traveloptics", "animations/the_obliterator.animation.json");
    }

    public ResourceLocation getModelResource(TheObliteratorLevelTwoItem animatable) {
        return new ResourceLocation("traveloptics", "geo/the_obliterator.geo.json");
    }

    public ResourceLocation getTextureResource(TheObliteratorLevelTwoItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/the_obliterator.png");
    }
}

