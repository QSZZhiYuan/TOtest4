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
package com.gametechbc.traveloptics.entity.mobs.aqua_grandmaster;

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

public class AquaGrandmasterEmissiveLayer
extends GeoRenderLayer<AbstractSpellCastingMob> {
    private static final ResourceLocation NORMAL_LAYER = new ResourceLocation("traveloptics", "textures/models/entity/aqua_grandmaster_layer.png");
    private static final ResourceLocation ANGRY_LAYER = new ResourceLocation("traveloptics", "textures/models/entity/aqua_grandmaster_angry_layer.png");

    public AquaGrandmasterEmissiveLayer(GeoRenderer<AbstractSpellCastingMob> entityRenderer) {
        super(entityRenderer);
    }

    public void render(PoseStack poseStack, AbstractSpellCastingMob animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        float healthPercentage = animatable.getHealth() / animatable.getMaxHealth();
        ResourceLocation emissiveTexture = (double)healthPercentage < 0.5 ? ANGRY_LAYER : NORMAL_LAYER;
        RenderType glowRenderType = RenderType.glintDirect((ResourceLocation)emissiveTexture);
        this.getRenderer().reRender(this.getDefaultBakedModel((GeoAnimatable)animatable), poseStack, bufferSource, (GeoAnimatable)animatable, glowRenderType, bufferSource.getBuffer(glowRenderType), partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
    }
}

