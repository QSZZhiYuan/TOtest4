/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.cache.object.BakedGeoModel
 *  software.bernie.geckolib.core.animatable.GeoAnimatable
 *  software.bernie.geckolib.renderer.GeoRenderer
 *  software.bernie.geckolib.renderer.layer.GeoRenderLayer
 */
package com.gametechbc.traveloptics.entity.item.scourge_of_the_sands.evo_three;

import com.gametechbc.traveloptics.item.bossweapon.scourgeofthesands.ScourgeOfTheSandsLevelThreeItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class ScourgeOfTheSandsLevelThreeLayer
extends GeoRenderLayer<ScourgeOfTheSandsLevelThreeItem> {
    private static final ResourceLocation LAYER = new ResourceLocation("traveloptics", "textures/models/item/scourge_of_the_sands_layer.png");

    public ScourgeOfTheSandsLevelThreeLayer(GeoRenderer<ScourgeOfTheSandsLevelThreeItem> itemRenderer) {
        super(itemRenderer);
    }

    public void render(PoseStack poseStack, ScourgeOfTheSandsLevelThreeItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType glowRenderType = RenderType.glintDirect((ResourceLocation)LAYER);
        this.getRenderer().reRender(this.getDefaultBakedModel((GeoAnimatable)animatable), poseStack, bufferSource, (GeoAnimatable)animatable, glowRenderType, bufferSource.getBuffer(glowRenderType), partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 0.5f);
    }
}

