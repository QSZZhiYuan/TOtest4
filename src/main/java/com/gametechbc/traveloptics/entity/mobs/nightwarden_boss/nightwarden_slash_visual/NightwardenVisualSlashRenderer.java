/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.PoseStack$Pose
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  org.joml.Matrix3f
 *  org.joml.Matrix4f
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_slash_visual;

import com.gametechbc.traveloptics.TravelopticsMod;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_slash_visual.NightwardenVisualSlashEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import java.util.Random;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class NightwardenVisualSlashRenderer
extends EntityRenderer<NightwardenVisualSlashEntity> {
    private static final ResourceLocation[] TEXTURES = new ResourceLocation[]{TravelopticsMod.id("textures/entity/nightwarden_slash_visual/nightwarden_slash_visual_1.png"), TravelopticsMod.id("textures/entity/nightwarden_slash_visual/nightwarden_slash_visual_2.png"), TravelopticsMod.id("textures/entity/nightwarden_slash_visual/nightwarden_slash_visual_3.png"), TravelopticsMod.id("textures/entity/nightwarden_slash_visual/nightwarden_slash_visual_4.png")};

    public NightwardenVisualSlashRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public void render(NightwardenVisualSlashEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        poseStack.mulPoseMatrix();
        PoseStack.Pose pose = poseStack.last();
        poseStack.mulPoseMatrix(Axis.YP.rotationDegrees(90.0f - entity.getYRot()));
        poseStack.mulPoseMatrix(Axis.ZP.rotationDegrees(entity.getXRot()));
        float randomZ = new Random(31L * (long)entity.getId()).nextInt(-8, 8);
        poseStack.mulPoseMatrix(Axis.XP.rotationDegrees(randomZ));
        this.drawSlash(pose, entity, bufferSource, entity.getBbWidth() * 1.5f, entity.isMirrored());
        poseStack.popPose();
        super.getRenderOffset((Entity)entity, yaw, partialTicks, poseStack, bufferSource, light);
    }

    private void drawSlash(PoseStack.Pose pose, NightwardenVisualSlashEntity entity, MultiBufferSource bufferSource, float width, boolean mirrored) {
        Matrix4f poseMatrix = pose.pose();
        Matrix3f normalMatrix = pose.normal();
        VertexConsumer consumer = bufferSource.getBuffer(RenderType.leash((ResourceLocation)this.getTextureLocation(entity)));
        float halfWidth = width * 0.5f;
        float height = entity.getBbHeight() * 0.5f;
        consumer.normal(poseMatrix, -halfWidth, height, -halfWidth).vertex(255, 255, 255, 255).vertex(0.0f, mirrored ? 0.0f : 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(0xF000F0).normal(normalMatrix, 0.0f, 1.0f, 0.0f).endVertex();
        consumer.normal(poseMatrix, halfWidth, height, -halfWidth).vertex(255, 255, 255, 255).vertex(1.0f, mirrored ? 0.0f : 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(0xF000F0).normal(normalMatrix, 0.0f, 1.0f, 0.0f).endVertex();
        consumer.normal(poseMatrix, halfWidth, height, halfWidth).vertex(255, 255, 255, 255).vertex(1.0f, mirrored ? 1.0f : 0.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(0xF000F0).normal(normalMatrix, 0.0f, 1.0f, 0.0f).endVertex();
        consumer.normal(poseMatrix, -halfWidth, height, halfWidth).vertex(255, 255, 255, 255).vertex(0.0f, mirrored ? 1.0f : 0.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(0xF000F0).normal(normalMatrix, 0.0f, 1.0f, 0.0f).endVertex();
    }

    public ResourceLocation getTextureLocation(NightwardenVisualSlashEntity entity) {
        int frame = entity.getTags / entity.ticksPerFrame % TEXTURES.length;
        return TEXTURES[frame];
    }
}

