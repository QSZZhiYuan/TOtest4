/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  software.bernie.geckolib.model.GeoModel
 *  software.bernie.geckolib.renderer.GeoEntityRenderer
 */
package com.gametechbc.traveloptics.entity.projectiles.maelstrom_trident_phantom;

import com.gametechbc.traveloptics.api.render.TORenderEmissiveLayer;
import com.gametechbc.traveloptics.entity.projectiles.maelstrom_trident_phantom.MaelstromTridentPhantomEntity;
import com.gametechbc.traveloptics.entity.projectiles.maelstrom_trident_phantom.MaelstromTridentPhantomModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MaelstromTridentPhantomRenderer
extends GeoEntityRenderer<MaelstromTridentPhantomEntity> {
    public static final ResourceLocation LAYER_EMISSIVE = new ResourceLocation("traveloptics", "textures/models/entity/maelstrom_trident_phantom/maelstrom_trident_phantom_entity.png");

    public MaelstromTridentPhantomRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, (GeoModel)new MaelstromTridentPhantomModel());
        this.addRenderLayer(new TORenderEmissiveLayer(this, RenderType.glintDirect((ResourceLocation)LAYER_EMISSIVE), 1.0f, 1.0f, 1.0f, 1.0f));
    }

    public void render(MaelstromTridentPhantomEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        float scale = 2.0f;
        poseStack.mulPoseMatrix();
        poseStack.popPose(scale, scale, scale);
        poseStack.mulPoseMatrix(Axis.YP.rotationDegrees(-entityYaw));
        super.getRenderOffset((Entity)entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.popPose();
    }
}

