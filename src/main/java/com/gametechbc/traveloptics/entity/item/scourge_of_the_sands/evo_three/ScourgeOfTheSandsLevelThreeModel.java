/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.scourge_of_the_sands.evo_three;

import com.gametechbc.traveloptics.item.bossweapon.scourgeofthesands.ScourgeOfTheSandsLevelThreeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ScourgeOfTheSandsLevelThreeModel
extends GeoModel<ScourgeOfTheSandsLevelThreeItem> {
    public ResourceLocation getAnimationResource(ScourgeOfTheSandsLevelThreeItem animatable) {
        return new ResourceLocation("traveloptics", "animations/scourge_of_the_sands.animation.json");
    }

    public ResourceLocation getModelResource(ScourgeOfTheSandsLevelThreeItem animatable) {
        return new ResourceLocation("traveloptics", "geo/scourge_of_the_sands.geo.json");
    }

    public ResourceLocation getTextureResource(ScourgeOfTheSandsLevelThreeItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/scourge_of_the_sands.png");
    }
}

