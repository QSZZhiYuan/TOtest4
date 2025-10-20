/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  software.bernie.geckolib.cache.object.BakedGeoModel
 *  software.bernie.geckolib.core.animatable.GeoAnimatable
 *  software.bernie.geckolib.renderer.GeoRenderer
 *  software.bernie.geckolib.renderer.layer.GeoRenderLayer
 */
package com.gametechbc.traveloptics.api.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.function.Predicate;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class TORenderEmissiveLayer<T extends GeoAnimatable>
extends GeoRenderLayer<T> {
    private final RenderType renderType;
    private final float alpha;
    private final float red;
    private final float green;
    private final float blue;
    private final int packedLight;
    private final int packedOverlay;
    private final Predicate<T> shouldRender;

    public TORenderEmissiveLayer(GeoRenderer<T> renderer, RenderType renderType, float alpha) {
        this(renderer, renderType, alpha, 1.0f, 1.0f, 1.0f);
    }

    public TORenderEmissiveLayer(GeoRenderer<T> renderer, RenderType renderType, float alpha, float red, float green, float blue) {
        this(renderer, renderType, alpha, red, green, blue, 0xF000F0, OverlayTexture.NO_OVERLAY);
    }

    public TORenderEmissiveLayer(GeoRenderer<T> renderer, RenderType renderType, float alpha, float red, float green, float blue, int packedLight, int packedOverlay) {
        this(renderer, renderType, alpha, red, green, blue, packedLight, packedOverlay, null);
    }

    public TORenderEmissiveLayer(GeoRenderer<T> renderer, RenderType renderType, float alpha, float red, float green, float blue, int packedLight, int packedOverlay, Predicate<T> shouldRender) {
        super(renderer);
        this.renderType = renderType;
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.packedLight = packedLight;
        this.packedOverlay = packedOverlay;
        this.shouldRender = shouldRender;
    }

    public TORenderEmissiveLayer(GeoRenderer<T> renderer, RenderType renderType, float alpha, int hexColor) {
        this(renderer, renderType, alpha, (float)(hexColor >> 16 & 0xFF) / 255.0f, (float)(hexColor >> 8 & 0xFF) / 255.0f, (float)(hexColor & 0xFF) / 255.0f);
    }

    public TORenderEmissiveLayer(GeoRenderer<T> renderer, RenderType renderType, float alpha, int hexColor, int packedLight, int packedOverlay) {
        this(renderer, renderType, alpha, (float)(hexColor >> 16 & 0xFF) / 255.0f, (float)(hexColor >> 8 & 0xFF) / 255.0f, (float)(hexColor & 0xFF) / 255.0f, packedLight, packedOverlay);
    }

    public TORenderEmissiveLayer(GeoRenderer<T> renderer, RenderType renderType, float alpha, Predicate<T> shouldRender) {
        this(renderer, renderType, alpha, 1.0f, 1.0f, 1.0f, 0xF000F0, OverlayTexture.NO_OVERLAY, shouldRender);
    }

    public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        if (this.shouldRender != null && !this.shouldRender.test(animatable)) {
            return;
        }
        this.getRenderer().reRender(this.getDefaultBakedModel((GeoAnimatable)animatable), poseStack, bufferSource, animatable, this.renderType, bufferSource.getBuffer(this.renderType), partialTick, this.packedLight, this.packedOverlay, this.red, this.green, this.blue, this.alpha);
    }
}

