/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.util.CameraShakeData
 *  io.redspace.ironsspellbooks.api.util.CameraShakeManager
 *  io.redspace.ironsspellbooks.util.ParticleHelper
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 */
package com.gametechbc.traveloptics.effects;

import com.gametechbc.traveloptics.api.particle.JetFlamesParticleManager;
import com.gametechbc.traveloptics.api.utils.TOGeneralUtils;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class AerialCollapseHelperEffect
extends MobEffect {
    public AerialCollapseHelperEffect() {
        super(MobEffectCategory.BENEFICIAL, 0);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        MobEffectInstance effectInstance = entity.getStandingEyeHeight((MobEffect)this);
        if (effectInstance != null) {
            int duration = effectInstance.getDuration();
            if (duration > 60) {
                entity.broadcastBreakEvent((MobEffect)this);
                entity.getStandingEyeHeight(new MobEffectInstance((MobEffect)this, 60, amplifier, false, true));
            }
            if (duration == 5 && !entity.level().isClientSide) {
                CameraShakeManager.addCameraShake((CameraShakeData)new CameraShakeData(20, entity.position(), 8.0f));
            }
        }
        if (!entity.level().isClientSide) {
            TOGeneralUtils.applyHovering((Entity)entity, 5.0, 0.06, 0.1, true);
            ParticleOptions[] particleTypes = new ParticleOptions[]{ParticleHelper.ACID, ParticleHelper.ACID_BUBBLE};
            int[] spawnPercentages = new int[]{10, 90};
            JetFlamesParticleManager.createJetFlamesBelowDefaulted(entity.level(), (Entity)entity, particleTypes, spawnPercentages, 8, false);
        }
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }
}

