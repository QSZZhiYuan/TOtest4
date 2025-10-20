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
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_defeated;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_defeated.NightwardenDefeatedEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_defeated.NightwardenDefeatedModel;
import com.mojang.blaze3d.vertex.PoseStack;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class NightwardenDefeatedRenderer
extends AbstractSpellCastingMobRenderer {
    public NightwardenDefeatedRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, (AbstractSpellCastingMobModel)new NightwardenDefeatedModel());
    }

    public void render(AbstractSpellCastingMob entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (entity instanceof NightwardenDefeatedEntity) {
            float scale = 1.5f;
            poseStack.mulPoseMatrix();
            poseStack.popPose(scale, scale, scale);
            this.getGeoModel().getBone("wings").ifPresent(bone -> bone.setHidden(true));
            this.getGeoModel().getBone("weapon").ifPresent(bone -> bone.setHidden(true));
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.popPose();
    }
}

