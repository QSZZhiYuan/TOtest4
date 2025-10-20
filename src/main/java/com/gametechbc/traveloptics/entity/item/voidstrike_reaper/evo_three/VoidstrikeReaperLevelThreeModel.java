/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.voidstrike_reaper.evo_three;

import com.gametechbc.traveloptics.item.bossweapon.voidstrikereaper.VoidstrikeReaperLevelThreeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VoidstrikeReaperLevelThreeModel
extends GeoModel<VoidstrikeReaperLevelThreeItem> {
    public ResourceLocation getAnimationResource(VoidstrikeReaperLevelThreeItem animatable) {
        return new ResourceLocation("traveloptics", "animations/voidstrike_reaper.animation.json");
    }

    public ResourceLocation getModelResource(VoidstrikeReaperLevelThreeItem animatable) {
        return new ResourceLocation("traveloptics", "geo/voidstrike_reaper.geo.json");
    }

    public ResourceLocation getTextureResource(VoidstrikeReaperLevelThreeItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/voidstrike_reaper.png");
    }
}

