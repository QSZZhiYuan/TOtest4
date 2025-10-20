/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.cache.object.GeoBone
 *  software.bernie.geckolib.core.animatable.GeoAnimatable
 *  software.bernie.geckolib.model.GeoModel
 *  software.bernie.geckolib.renderer.GeoArmorRenderer
 *  software.bernie.geckolib.renderer.GeoRenderer
 */
package com.gametechbc.traveloptics.entity.armor.tectonic_crest_armor;

import com.gametechbc.traveloptics.entity.armor.tectonic_crest_armor.TectonicCrestArmorLayer;
import com.gametechbc.traveloptics.entity.armor.tectonic_crest_armor.TectonicCrestArmorModel;
import com.gametechbc.traveloptics.item.armor.TectonicCrestArmorItem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

public class TectonicCrestArmorRenderer
extends GeoArmorRenderer<TectonicCrestArmorItem> {
    public TectonicCrestArmorRenderer(TectonicCrestArmorModel tectonicCrestArmorModel) {
        super((GeoModel)new TectonicCrestArmorModel());
        this.addRenderLayer(new TectonicCrestArmorLayer((GeoRenderer<TectonicCrestArmorItem>)this));
        this.head = new GeoBone(null, "armorHead", Boolean.valueOf(false), Double.valueOf(0.0), Boolean.valueOf(false), Boolean.valueOf(false));
        this.body = new GeoBone(null, "armorBody", Boolean.valueOf(false), Double.valueOf(0.0), Boolean.valueOf(false), Boolean.valueOf(false));
        this.rightArm = new GeoBone(null, "armorRightArm", Boolean.valueOf(false), Double.valueOf(0.0), Boolean.valueOf(false), Boolean.valueOf(false));
        this.leftArm = new GeoBone(null, "armorLeftArm", Boolean.valueOf(false), Double.valueOf(0.0), Boolean.valueOf(false), Boolean.valueOf(false));
        this.rightLeg = new GeoBone(null, "armorRightLeg", Boolean.valueOf(false), Double.valueOf(0.0), Boolean.valueOf(false), Boolean.valueOf(false));
        this.leftLeg = new GeoBone(null, "armorLeftLeg", Boolean.valueOf(false), Double.valueOf(0.0), Boolean.valueOf(false), Boolean.valueOf(false));
        this.rightBoot = new GeoBone(null, "armorRightBoot", Boolean.valueOf(false), Double.valueOf(0.0), Boolean.valueOf(false), Boolean.valueOf(false));
        this.leftBoot = new GeoBone(null, "armorLeftBoot", Boolean.valueOf(false), Double.valueOf(0.0), Boolean.valueOf(false), Boolean.valueOf(false));
    }

    public RenderType getRenderType(TectonicCrestArmorItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent((ResourceLocation)this.getTextureLocation((GeoAnimatable)animatable));
    }
}

