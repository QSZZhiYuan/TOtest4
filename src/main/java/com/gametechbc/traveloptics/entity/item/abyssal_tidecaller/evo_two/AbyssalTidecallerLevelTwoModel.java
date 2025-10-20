/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.item.abyssal_tidecaller.evo_two;

import com.gametechbc.traveloptics.item.bossweapon.abyssaltidecaller.AbyssalTidecallerLevelTwoItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AbyssalTidecallerLevelTwoModel
extends GeoModel<AbyssalTidecallerLevelTwoItem> {
    public ResourceLocation getAnimationResource(AbyssalTidecallerLevelTwoItem animatable) {
        return new ResourceLocation("traveloptics", "animations/abyssal_tidecaller.animation.json");
    }

    public ResourceLocation getModelResource(AbyssalTidecallerLevelTwoItem animatable) {
        return new ResourceLocation("traveloptics", "geo/abyssal_tidecaller.geo.json");
    }

    public ResourceLocation getTextureResource(AbyssalTidecallerLevelTwoItem animatable) {
        return new ResourceLocation("traveloptics", "textures/models/item/abyssal_tidecaller.png");
    }
}

