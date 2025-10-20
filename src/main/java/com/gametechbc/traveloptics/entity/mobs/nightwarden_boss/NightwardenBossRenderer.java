/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  software.bernie.geckolib.renderer.GeoRenderer
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenBossEmissiveLayer;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenBossEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenBossModel;
import com.mojang.blaze3d.vertex.PoseStack;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import software.bernie.geckolib.renderer.GeoRenderer;

public class NightwardenBossRenderer
extends AbstractSpellCastingMobRenderer {
    public NightwardenBossRenderer(EntityRendererProvider.Context context) {
        super(context, (AbstractSpellCastingMobModel)new NightwardenBossModel());
        this.addRenderLayer(new NightwardenBossEmissiveLayer((GeoRenderer<AbstractSpellCastingMob>)this));
    }

    public void render(AbstractSpellCastingMob entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (entity instanceof NightwardenBossEntity) {
            NightwardenBossEntity boss = (NightwardenBossEntity)entity;
            float scale = 1.5f;
            poseStack.mulPoseMatrix();
            poseStack.popPose(scale, scale, scale);
            this.getGeoModel().getBone("wings").ifPresent(bone -> bone.setHidden(!boss.isShowingWings()));
            this.getGeoModel().getBone("weapon").ifPresent(bone -> {
                boolean validPhase = !boss.isPhase(NightwardenBossEntity.Phase.FIRST);
                bone.setHidden(!validPhase && !boss.isShowingWeapon());
            });
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.popPose();
    }

    protected float getDeathMaxRotation(AbstractSpellCastingMob entityLivingBaseIn) {
        return 0.0f;
    }

    public int getPackedOverlay(AbstractSpellCastingMob animatable, float u, float partialTick) {
        return OverlayTexture.NO_OVERLAY;
    }
}

