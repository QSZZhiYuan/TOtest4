/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.flames_of_eldritch.evo_two;

import com.gametechbc.traveloptics.item.bossweapon.flamesofeldritch.FlamesOfEldritchLevelTwoItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FlamesOfEldritchLevelTwoModel
extends GeoModel<FlamesOfEldritchLevelTwoItem> {
    public ResourceLocation getAnimationResource(FlamesOfEldritchLevelTwoItem animatable) {
        return new ResourceLocation("traveloptics", "animations/flames_of_eldritch.animation.json");
    }

    public ResourceLocation getModelResource(FlamesOfEldritchLevelTwoItem animatable) {
        return new ResourceLocation("traveloptics", "geo/flames_of_eldritch.geo.json");
    }

    public ResourceLocation getTextureResource(FlamesOfEldritchLevelTwoItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/flames_of_eldritch.png");
    }
}

