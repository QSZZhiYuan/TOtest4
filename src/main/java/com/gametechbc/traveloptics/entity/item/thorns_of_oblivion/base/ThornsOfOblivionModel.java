/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.thorns_of_oblivion.base;

import com.gametechbc.traveloptics.item.bossweapon.thornsofoblivion.ThornsOfOblivionItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ThornsOfOblivionModel
extends GeoModel<ThornsOfOblivionItem> {
    public ResourceLocation getAnimationResource(ThornsOfOblivionItem animatable) {
        return new ResourceLocation("traveloptics", "animations/thorns_of_oblivion.animation.json");
    }

    public ResourceLocation getModelResource(ThornsOfOblivionItem animatable) {
        return new ResourceLocation("traveloptics", "geo/thorns_of_oblivion.geo.json");
    }

    public ResourceLocation getTextureResource(ThornsOfOblivionItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/thorns_of_oblivion.png");
    }
}

