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
 *  software.bernie.geckolib.cache.object.BakedGeoModel
 *  software.bernie.geckolib.core.animatable.GeoAnimatable
 *  software.bernie.geckolib.renderer.GeoRenderer
 *  software.bernie.geckolib.renderer.layer.GeoRenderLayer
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenBossEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class NightwardenBossEmissiveLayer
extends GeoRenderLayer<AbstractSpellCastingMob> {
    public NightwardenBossEmissiveLayer(GeoRenderer<AbstractSpellCastingMob> entityRenderer) {
        super(entityRenderer);
    }

    public void render(PoseStack poseStack, AbstractSpellCastingMob animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        NightwardenBossEntity nightwarden;
        ResourceLocation layerTex = new ResourceLocation("traveloptics", "textures/models/entity/nightwarden/nightwarden_layer.png");
        if (animatable instanceof NightwardenBossEntity && (nightwarden = (NightwardenBossEntity)animatable).hasPendingResurgence()) {
            layerTex = new ResourceLocation("traveloptics", "textures/models/entity/nightwarden/nightwarden_resurgence_layer.png");
        }
        RenderType glowRenderType = RenderType.glintDirect((ResourceLocation)layerTex);
        this.getRenderer().reRender(this.getDefaultBakedModel((GeoAnimatable)animatable), poseStack, bufferSource, (GeoAnimatable)animatable, glowRenderType, bufferSource.getBuffer(glowRenderType), partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
    }
}

