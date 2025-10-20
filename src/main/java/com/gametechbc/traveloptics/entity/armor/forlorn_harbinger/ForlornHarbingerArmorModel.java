/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.armor.forlorn_harbinger;

import com.gametechbc.traveloptics.item.armor.ForlornHarbingerArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ForlornHarbingerArmorModel
extends GeoModel<ForlornHarbingerArmorItem> {
    public ResourceLocation getAnimationResource(ForlornHarbingerArmorItem object) {
        return new ResourceLocation("traveloptics", "animations/forlorn_harbinger.animation.json");
    }

    public ResourceLocation getModelResource(ForlornHarbingerArmorItem object) {
        return new ResourceLocation("traveloptics", "geo/forlorn_harbinger.geo.json");
    }

    public ResourceLocation getTextureResource(ForlornHarbingerArmorItem object) {
        return new ResourceLocation("traveloptics", "textures/models/armor/forlorn_harbinger.png");
    }
}

