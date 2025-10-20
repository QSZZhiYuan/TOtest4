/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.abyssal_tidecaller.base;

import com.gametechbc.traveloptics.item.bossweapon.abyssaltidecaller.AbyssalTidecallerItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AbyssalTidecallerModel
extends GeoModel<AbyssalTidecallerItem> {
    public ResourceLocation getAnimationResource(AbyssalTidecallerItem animatable) {
        return new ResourceLocation("traveloptics", "animations/abyssal_tidecaller.animation.json");
    }

    public ResourceLocation getModelResource(AbyssalTidecallerItem animatable) {
        return new ResourceLocation("traveloptics", "geo/abyssal_tidecaller.geo.json");
    }

    public ResourceLocation getTextureResource(AbyssalTidecallerItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/abyssal_tidecaller.png");
    }
}

