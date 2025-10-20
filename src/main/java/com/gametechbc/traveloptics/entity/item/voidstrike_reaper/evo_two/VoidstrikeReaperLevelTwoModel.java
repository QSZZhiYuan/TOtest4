/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.voidstrike_reaper.evo_two;

import com.gametechbc.traveloptics.item.bossweapon.voidstrikereaper.VoidstrikeReaperLevelTwoItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VoidstrikeReaperLevelTwoModel
extends GeoModel<VoidstrikeReaperLevelTwoItem> {
    public ResourceLocation getAnimationResource(VoidstrikeReaperLevelTwoItem animatable) {
        return new ResourceLocation("traveloptics", "animations/voidstrike_reaper.animation.json");
    }

    public ResourceLocation getModelResource(VoidstrikeReaperLevelTwoItem animatable) {
        return new ResourceLocation("traveloptics", "geo/voidstrike_reaper.geo.json");
    }

    public ResourceLocation getTextureResource(VoidstrikeReaperLevelTwoItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/voidstrike_reaper.png");
    }
}

