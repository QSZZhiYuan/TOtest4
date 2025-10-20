/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.stellothorn;

import com.gametechbc.traveloptics.item.bossweapon.stellothorn.StellothornItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class StellothornModel
extends GeoModel<StellothornItem> {
    public ResourceLocation getAnimationResource(StellothornItem animatable) {
        return new ResourceLocation("traveloptics", "animations/abyssal_tidecaller.animation.json");
    }

    public ResourceLocation getModelResource(StellothornItem animatable) {
        return new ResourceLocation("traveloptics", "geo/stellothorn.geo.json");
    }

    public ResourceLocation getTextureResource(StellothornItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/stellothorn.png");
    }
}

