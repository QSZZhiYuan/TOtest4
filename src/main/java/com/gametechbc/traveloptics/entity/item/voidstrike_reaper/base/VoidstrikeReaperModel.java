/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.voidstrike_reaper.base;

import com.gametechbc.traveloptics.item.bossweapon.voidstrikereaper.VoidstrikeReaperItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VoidstrikeReaperModel
extends GeoModel<VoidstrikeReaperItem> {
    public ResourceLocation getAnimationResource(VoidstrikeReaperItem animatable) {
        return new ResourceLocation("traveloptics", "animations/voidstrike_reaper.animation.json");
    }

    public ResourceLocation getModelResource(VoidstrikeReaperItem animatable) {
        return new ResourceLocation("traveloptics", "geo/voidstrike_reaper.geo.json");
    }

    public ResourceLocation getTextureResource(VoidstrikeReaperItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/voidstrike_reaper.png");
    }
}

