/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  software.bernie.geckolib.cache.object.BakedGeoModel
 *  software.bernie.geckolib.core.animatable.GeoAnimatable
 *  software.bernie.geckolib.renderer.GeoRenderer
 *  software.bernie.geckolib.renderer.layer.GeoRenderLayer
 */
package com.gametechbc.traveloptics.entity.mobs.aquamancer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class AquamancerEmissiveLayer
extends GeoRenderLayer<AbstractSpellCastingMob> {
    private static final ResourceLocation LAYER = new ResourceLocation("traveloptics", "textures/models/entity/aquamancer_layer.png");

    public AquamancerEmissiveLayer(GeoRenderer<AbstractSpellCastingMob> entityRenderer) {
        super(entityRenderer);
    }

    public void render(PoseStack poseStack, AbstractSpellCastingMob animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        renderType = RenderType.glintDirect((ResourceLocation)LAYER);
        VertexConsumer vertexconsumer = bufferSource.getBuffer(renderType);
        poseStack.mulPoseMatrix();
        float f = Mth.outFromOrigin((float)((float)(((double)((float)animatable.getTags + partialTick) + (animatable.getX() + animatable.getZ()) * 500.0) * (double)0.15f))) * 0.5f + 0.5f;
        this.getRenderer().actuallyRender(poseStack, (GeoAnimatable)animatable, bakedModel, renderType, bufferSource, vertexconsumer, true, partialTick, 0xF000F0, OverlayTexture.NO_OVERLAY, f, f, f, 1.0f);
        poseStack.popPose();
    }
}

