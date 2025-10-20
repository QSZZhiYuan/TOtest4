/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.thorns_of_oblivion.evo_three;

import com.gametechbc.traveloptics.item.bossweapon.thornsofoblivion.ThornsOfOblivionLevelThreeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ThornsOfOblivionLevelThreeModel
extends GeoModel<ThornsOfOblivionLevelThreeItem> {
    public ResourceLocation getAnimationResource(ThornsOfOblivionLevelThreeItem animatable) {
        return new ResourceLocation("traveloptics", "animations/thorns_of_oblivion.animation.json");
    }

    public ResourceLocation getModelResource(ThornsOfOblivionLevelThreeItem animatable) {
        return new ResourceLocation("traveloptics", "geo/thorns_of_oblivion.geo.json");
    }

    public ResourceLocation getTextureResource(ThornsOfOblivionLevelThreeItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/thorns_of_oblivion.png");
    }
}

