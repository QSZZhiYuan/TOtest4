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
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_slash_clone;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_slash_clone.NightwardenSlashCloneEmissiveLayer;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_slash_clone.NightwardenSlashCloneEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_slash_clone.NightwardenSlashCloneModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

public class NightwardenSlashCloneRenderer
extends GeoEntityRenderer<NightwardenSlashCloneEntity> {
    public NightwardenSlashCloneRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, (GeoModel)new NightwardenSlashCloneModel());
        this.addRenderLayer(new NightwardenSlashCloneEmissiveLayer((GeoRenderer<NightwardenSlashCloneEntity>)this));
    }

    public void render(NightwardenSlashCloneEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        float scale = 1.5f;
        poseStack.mulPoseMatrix();
        poseStack.popPose(scale, scale, scale);
        this.getGeoModel().getBone("wings").ifPresent(bone -> bone.setHidden(true));
        this.getGeoModel().getBone("weapon").ifPresent(bone -> bone.setHidden(false));
        super.getRenderOffset((Entity)entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.popPose();
    }
}

