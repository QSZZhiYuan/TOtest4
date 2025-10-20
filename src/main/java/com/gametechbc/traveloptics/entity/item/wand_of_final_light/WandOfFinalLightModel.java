/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.wand_of_final_light;

import com.gametechbc.traveloptics.item.staff.WandOfFinalLightItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WandOfFinalLightModel
extends GeoModel<WandOfFinalLightItem> {
    public ResourceLocation getAnimationResource(WandOfFinalLightItem animatable) {
        return new ResourceLocation("traveloptics", "animations/supernova_staff.animation.json");
    }

    public ResourceLocation getModelResource(WandOfFinalLightItem animatable) {
        return new ResourceLocation("traveloptics", "geo/supernova_staff.geo.json");
    }

    public ResourceLocation getTextureResource(WandOfFinalLightItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/supernova_staff/supernova_staff.png");
    }
}

