/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.LivingEntity
 */
package com.gametechbc.traveloptics.effects;

import com.gametechbc.traveloptics.init.TravelopticsParticles;
import java.util.Random;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class VoidCollapseEffect
extends MobEffect {
    private static final Random RANDOM = new Random();

    public VoidCollapseEffect() {
        super(MobEffectCategory.HARMFUL, 0);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        MobEffectInstance effectInstance = entity.getStandingEyeHeight((MobEffect)this);
        if (effectInstance != null) {
            int duration = effectInstance.getDuration();
            if (duration > 60) {
                entity.broadcastBreakEvent((MobEffect)this);
                entity.getStandingEyeHeight(new MobEffectInstance((MobEffect)this, 60, amplifier, false, true));
            }
            if (duration > 20) {
                entity.setIsInPowderSnow(entity.getDeltaMovement().z, 0.3, entity.getDeltaMovement().reverse);
            } else if (duration <= 10) {
                entity.setIsInPowderSnow(entity.getDeltaMovement().z, -3.0, entity.getDeltaMovement().reverse);
                if (duration == 5) {
                    float damageAmount = amplifier + 1;
                    entity.sendSystemMessage(entity.damageSources().magic(), damageAmount);
                }
            }
            if (!entity.level().isClientSide && entity.getTags % 2 == 0) {
                for (int i = 0; i < 5; ++i) {
                    double offsetX = (RANDOM.nextDouble() - 0.5) * (double)entity.getBbWidth();
                    double offsetY = RANDOM.nextDouble() * (double)entity.getBbHeight();
                    double offsetZ = (RANDOM.nextDouble() - 0.5) * (double)entity.getBbWidth();
                    ((ServerLevel)entity.level()).getRandomSequence((ParticleOptions)((SimpleParticleType)TravelopticsParticles.PURPLE_STAR_OUTWARD_PARTICLE.get()), entity.getX() + offsetX, entity.getY() + offsetY, entity.getZ() + offsetZ, 1, 0.0, 0.0, 0.0, 0.0);
                }
            }
        }
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }
}

