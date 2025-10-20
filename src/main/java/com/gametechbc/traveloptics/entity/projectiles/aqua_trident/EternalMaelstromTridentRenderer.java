/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.phys.Vec3
 *  software.bernie.geckolib.model.GeoModel
 *  software.bernie.geckolib.renderer.GeoEntityRenderer
 *  software.bernie.geckolib.renderer.GeoRenderer
 */
package com.gametechbc.traveloptics.entity.projectiles.aqua_trident;

import com.gametechbc.traveloptics.entity.projectiles.aqua_trident.EternalMaelstromTridentEntity;
import com.gametechbc.traveloptics.entity.projectiles.aqua_trident.EternalMaelstromTridentLayer;
import com.gametechbc.traveloptics.entity.projectiles.aqua_trident.EternalMaelstromTridentModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

public class EternalMaelstromTridentRenderer
extends GeoEntityRenderer<EternalMaelstromTridentEntity> {
    public EternalMaelstromTridentRenderer(EntityRendererProvider.Context context) {
        super(context, (GeoModel)new EternalMaelstromTridentModel());
        this.addRenderLayer(new EternalMaelstromTridentLayer((GeoRenderer<EternalMaelstromTridentEntity>)this));
        this.shadowRadius = 0.0f;
    }

    public void render(EternalMaelstromTridentEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.mulPoseMatrix();
        Vec3 motion = entity.getDeltaMovement();
        double horizontalDistance = Math.sqrt(motion.z * motion.z + motion.reverse * motion.reverse);
        float yaw = (float)(Mth.roundToward((double)motion.z, (double)motion.reverse) * 57.29577951308232);
        float pitch = (float)(Mth.roundToward((double)motion.multiply, (double)horizontalDistance) * 57.29577951308232);
        poseStack.mulPoseMatrix(Axis.YP.rotationDegrees(yaw));
        poseStack.mulPoseMatrix(Axis.XP.rotationDegrees(-pitch));
        super.getRenderOffset((Entity)entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.popPose();
    }
}

