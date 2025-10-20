/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.gauntlet_of_extinction;

import com.gametechbc.traveloptics.item.bossweapon.gauntletofextinction.GauntletOfExtinctionItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GauntletOfExtinctionItemModel
extends GeoModel<GauntletOfExtinctionItem> {
    public ResourceLocation getAnimationResource(GauntletOfExtinctionItem animatable) {
        return new ResourceLocation("traveloptics", "animations/gauntlet_of_extinction.animation.json");
    }

    public ResourceLocation getModelResource(GauntletOfExtinctionItem animatable) {
        return new ResourceLocation("traveloptics", "geo/gauntlet_of_extinction.geo.json");
    }

    public ResourceLocation getTextureResource(GauntletOfExtinctionItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/gauntlet_of_extinction.png");
    }
}

