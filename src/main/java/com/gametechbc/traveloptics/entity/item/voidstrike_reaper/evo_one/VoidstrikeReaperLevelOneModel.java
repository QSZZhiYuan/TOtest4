/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.voidstrike_reaper.evo_one;

import com.gametechbc.traveloptics.item.bossweapon.voidstrikereaper.VoidstrikeReaperLevelOneItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VoidstrikeReaperLevelOneModel
extends GeoModel<VoidstrikeReaperLevelOneItem> {
    public ResourceLocation getAnimationResource(VoidstrikeReaperLevelOneItem animatable) {
        return new ResourceLocation("traveloptics", "animations/voidstrike_reaper.animation.json");
    }

    public ResourceLocation getModelResource(VoidstrikeReaperLevelOneItem animatable) {
        return new ResourceLocation("traveloptics", "geo/voidstrike_reaper.geo.json");
    }

    public ResourceLocation getTextureResource(VoidstrikeReaperLevelOneItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/voidstrike_reaper.png");
    }
}

