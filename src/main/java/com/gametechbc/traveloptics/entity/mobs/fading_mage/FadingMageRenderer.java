/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer
 *  io.redspace.ironsspellbooks.render.RenderHelper
 *  javax.annotation.Nullable
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.core.object.Color
 */
package com.gametechbc.traveloptics.entity.mobs.fading_mage;

import com.gametechbc.traveloptics.entity.mobs.fading_mage.FadingMageEntity;
import com.gametechbc.traveloptics.entity.mobs.fading_mage.FadingMageModel;
import com.mojang.blaze3d.vertex.PoseStack;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer;
import io.redspace.ironsspellbooks.render.RenderHelper;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.object.Color;

public class FadingMageRenderer
extends AbstractSpellCastingMobRenderer {
    public FadingMageRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, (AbstractSpellCastingMobModel)new FadingMageModel());
    }

    public void render(AbstractSpellCastingMob entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (entity instanceof FadingMageEntity) {
            FadingMageEntity fadingMage = (FadingMageEntity)entity;
            float scale = 1.2f;
            poseStack.mulPoseMatrix();
            poseStack.popPose(scale, scale, scale);
            this.getGeoModel().getBone("wings").ifPresent(bone -> bone.setHidden(true));
            this.getGeoModel().getBone("weapon").ifPresent(bone -> bone.setHidden(false));
            super.render((AbstractSpellCastingMob)fadingMage, entityYaw, partialTick, poseStack, bufferSource, packedLight);
            poseStack.popPose();
        } else {
            super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        }
    }

    public RenderType getRenderType(AbstractSpellCastingMob animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        FadingMageEntity fadingMage;
        if (animatable instanceof FadingMageEntity && (fadingMage = (FadingMageEntity)animatable).isDespawnTriggered()) {
            return RenderType.entityTranslucent((ResourceLocation)texture);
        }
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    public Color getRenderColor(AbstractSpellCastingMob animatable, float partialTick, int packedLight) {
        FadingMageEntity fadingMage;
        Color color = super.getRenderColor(animatable, partialTick, packedLight);
        if (animatable instanceof FadingMageEntity && (fadingMage = (FadingMageEntity)animatable).isDespawnTriggered()) {
            float alpha = fadingMage.getFadeOpacity();
            return new Color(RenderHelper.colorf((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha));
        }
        return color;
    }
}

