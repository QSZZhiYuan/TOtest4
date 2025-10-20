/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.world.entity.Entity
 *  software.bernie.geckolib.cache.object.BakedGeoModel
 *  software.bernie.geckolib.model.GeoModel
 *  software.bernie.geckolib.renderer.GeoEntityRenderer
 *  software.bernie.geckolib.renderer.GeoRenderer
 */
package com.gametechbc.traveloptics.entity.projectiles.end_eruption_bomb;

import com.gametechbc.traveloptics.entity.projectiles.end_eruption_bomb.EruptionBombProjectileEntity;
import com.gametechbc.traveloptics.entity.projectiles.end_eruption_bomb.EruptionBombProjectileLayer;
import com.gametechbc.traveloptics.entity.projectiles.end_eruption_bomb.EruptionBombProjectileModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

public class EruptionBombProjectileRenderer
extends GeoEntityRenderer<EruptionBombProjectileEntity> {
    public EruptionBombProjectileRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, (GeoModel)new EruptionBombProjectileModel());
        this.addRenderLayer(new EruptionBombProjectileLayer((GeoRenderer<EruptionBombProjectileEntity>)this));
        this.shadowRadius = 0.0f;
    }

    public void preRender(PoseStack poseStack, EruptionBombProjectileEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, (Entity)entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        float scale = 1.4f;
        poseStack.popPose(scale, scale, scale);
    }
}

