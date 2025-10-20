/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.effects.Blackout;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class BlackoutEffect
extends MobEffect {
    public BlackoutEffect() {
        super(MobEffectCategory.NEUTRAL, 0);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        Level level;
        entity.getStandingEyeHeight(new MobEffectInstance(MobEffects.DARKNESS, 60, 0, false, false, false));
        if (!entity.level().isClientSide() && (level = entity.level()) instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            int particleCount = 3;
            for (int i = 0; i < particleCount; ++i) {
                double offsetX = (entity.getRandom().nextDouble() - 0.5) * (double)entity.getBbWidth();
                double offsetY = entity.getRandom().nextDouble() * (double)entity.getBbHeight();
                double offsetZ = (entity.getRandom().nextDouble() - 0.5) * (double)entity.getBbWidth();
                double velocityX = (entity.getRandom().nextDouble() - 0.5) * 0.2;
                double velocityY = (entity.getRandom().nextDouble() - 0.5) * 0.2;
                double velocityZ = (entity.getRandom().nextDouble() - 0.5) * 0.2;
                serverLevel.getRandomSequence((ParticleOptions)ParticleTypes.SMOKE, entity.getX() + offsetX, entity.getY() + offsetY, entity.getZ() + offsetZ, 1, velocityX, velocityY, velocityZ, 0.0);
            }
        }
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }
}

