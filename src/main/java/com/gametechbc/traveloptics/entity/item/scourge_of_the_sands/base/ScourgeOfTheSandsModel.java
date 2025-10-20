/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.scourge_of_the_sands.base;

import com.gametechbc.traveloptics.item.bossweapon.scourgeofthesands.ScourgeOfTheSandsItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ScourgeOfTheSandsModel
extends GeoModel<ScourgeOfTheSandsItem> {
    public ResourceLocation getAnimationResource(ScourgeOfTheSandsItem animatable) {
        return new ResourceLocation("traveloptics", "animations/scourge_of_the_sands.animation.json");
    }

    public ResourceLocation getModelResource(ScourgeOfTheSandsItem animatable) {
        return new ResourceLocation("traveloptics", "geo/scourge_of_the_sands.geo.json");
    }

    public ResourceLocation getTextureResource(ScourgeOfTheSandsItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/scourge_of_the_sands.png");
    }
}

