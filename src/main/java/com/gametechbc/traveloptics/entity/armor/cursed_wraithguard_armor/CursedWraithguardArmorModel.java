/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.GeoModel
 */
package com.gametechbc.traveloptics.entity.armor.cursed_wraithguard_armor;

import com.gametechbc.traveloptics.item.armor.CursedWraithguardArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CursedWraithguardArmorModel
extends GeoModel<CursedWraithguardArmorItem> {
    public ResourceLocation getAnimationResource(CursedWraithguardArmorItem object) {
        return new ResourceLocation("traveloptics", "animations/cursed_wraithguard_armor.animation.json");
    }

    public ResourceLocation getModelResource(CursedWraithguardArmorItem object) {
        return new ResourceLocation("traveloptics", "geo/cursed_wraithguard_armor.geo.json");
    }

    public ResourceLocation getTextureResource(CursedWraithguardArmorItem object) {
        return new ResourceLocation("traveloptics", "textures/models/armor/cursed_wraithguard_armor.png");
    }
}

