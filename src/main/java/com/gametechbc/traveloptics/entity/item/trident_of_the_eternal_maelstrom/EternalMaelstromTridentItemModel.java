/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.trident_of_the_eternal_maelstrom;

import com.gametechbc.traveloptics.item.bossweapon.trident_of_the_eternal_maelstrom.TridentOfTheEternalMaelstromItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EternalMaelstromTridentItemModel
extends GeoModel<TridentOfTheEternalMaelstromItem> {
    public ResourceLocation getAnimationResource(TridentOfTheEternalMaelstromItem animatable) {
        return new ResourceLocation("traveloptics", "animations/titanlords_scepter.animation.json");
    }

    public ResourceLocation getModelResource(TridentOfTheEternalMaelstromItem animatable) {
        return new ResourceLocation("traveloptics", "geo/trident_of_the_eternal_maelstrom.geo.json");
    }

    public ResourceLocation getTextureResource(TridentOfTheEternalMaelstromItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/trident_of_the_eternal_maelstrom_item.png");
    }
}

