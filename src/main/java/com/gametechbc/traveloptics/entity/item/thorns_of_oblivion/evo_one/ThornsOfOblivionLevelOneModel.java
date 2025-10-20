/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.thorns_of_oblivion.evo_one;

import com.gametechbc.traveloptics.item.bossweapon.thornsofoblivion.ThornsOfOblivionLevelOneItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ThornsOfOblivionLevelOneModel
extends GeoModel<ThornsOfOblivionLevelOneItem> {
    public ResourceLocation getAnimationResource(ThornsOfOblivionLevelOneItem animatable) {
        return new ResourceLocation("traveloptics", "animations/thorns_of_oblivion.animation.json");
    }

    public ResourceLocation getModelResource(ThornsOfOblivionLevelOneItem animatable) {
        return new ResourceLocation("traveloptics", "geo/thorns_of_oblivion.geo.json");
    }

    public ResourceLocation getTextureResource(ThornsOfOblivionLevelOneItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/thorns_of_oblivion.png");
    }
}

