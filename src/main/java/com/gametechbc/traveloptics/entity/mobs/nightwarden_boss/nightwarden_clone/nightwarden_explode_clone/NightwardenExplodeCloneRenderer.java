/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.world.entity.Entity
 *  software.bernie.geckolib.model.GeoModel
 *  software.bernie.geckolib.renderer.GeoEntityRenderer
 *  software.bernie.geckolib.renderer.GeoRenderer
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_explode_clone;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_explode_clone.NightwardenExplodeCloneEmissiveLayer;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_explode_clone.NightwardenExplodeCloneEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_explode_clone.NightwardenExplodeCloneModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

public class NightwardenExplodeCloneRenderer
extends GeoEntityRenderer<NightwardenExplodeCloneEntity> {
    public NightwardenExplodeCloneRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, (GeoModel)new NightwardenExplodeCloneModel());
        this.addRenderLayer(new NightwardenExplodeCloneEmissiveLayer((GeoRenderer<NightwardenExplodeCloneEntity>)this));
    }

    public void render(NightwardenExplodeCloneEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (entity instanceof NightwardenExplodeCloneEntity) {
            float scale = 1.5f;
            poseStack.mulPoseMatrix();
            poseStack.popPose(scale, scale, scale);
            this.getGeoModel().getBone("wings").ifPresent(bone -> bone.setHidden(true));
            this.getGeoModel().getBone("weapon").ifPresent(bone -> bone.setHidden(false));
            super.getRenderOffset((Entity)entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
            poseStack.popPose();
        }
    }
}

