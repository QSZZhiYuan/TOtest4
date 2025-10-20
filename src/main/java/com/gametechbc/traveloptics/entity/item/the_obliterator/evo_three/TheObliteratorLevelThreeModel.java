/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.the_obliterator.evo_three;

import com.gametechbc.traveloptics.item.bossweapon.the_obliterator.TheObliteratorLevelThreeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TheObliteratorLevelThreeModel
extends GeoModel<TheObliteratorLevelThreeItem> {
    public ResourceLocation getAnimationResource(TheObliteratorLevelThreeItem animatable) {
        return new ResourceLocation("traveloptics", "animations/the_obliterator.animation.json");
    }

    public ResourceLocation getModelResource(TheObliteratorLevelThreeItem animatable) {
        return new ResourceLocation("traveloptics", "geo/the_obliterator.geo.json");
    }

    public ResourceLocation getTextureResource(TheObliteratorLevelThreeItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/the_obliterator.png");
    }
}

