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
package com.gametechbc.traveloptics.entity.mobs.voidshelf_golem;

import com.gametechbc.traveloptics.entity.mobs.voidshelf_golem.VoidshelfGolemEmissiveLayer;
import com.gametechbc.traveloptics.entity.mobs.voidshelf_golem.VoidshelfGolemEntity;
import com.gametechbc.traveloptics.entity.mobs.voidshelf_golem.VoidshelfGolemModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

public class VoidshelfGolemRenderer
extends GeoEntityRenderer<VoidshelfGolemEntity> {
    public VoidshelfGolemRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, (GeoModel)new VoidshelfGolemModel());
        this.addRenderLayer(new VoidshelfGolemEmissiveLayer((GeoRenderer<VoidshelfGolemEntity>)this));
    }

    public void render(VoidshelfGolemEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        float scale = 1.0f;
        poseStack.mulPoseMatrix();
        poseStack.popPose(scale, scale, scale);
        this.getGeoModel().getBone("candles").ifPresent(bone -> bone.setHidden(!entity.shouldShowCandle()));
        super.getRenderOffset((Entity)entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.popPose();
    }
}

