/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.client.particle.LightningParticle$OrbData
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.effects;

import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.github.L_Ender.cataclysm.client.particle.LightningParticle;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import java.util.List;
import java.util.Random;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class PsychicControlEffect
extends MobEffect {
    private static final Random RANDOM = new Random();

    public PsychicControlEffect() {
        super(MobEffectCategory.HARMFUL, 2744299);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide && entity instanceof Mob) {
            Mob mob = (Mob)entity;
            Entity caster = this.getEffectSource(entity);
            if (caster instanceof LivingEntity) {
                LivingEntity newTarget;
                LivingEntity livingCaster = (LivingEntity)caster;
                if (mob.getTarget() == livingCaster) {
                    mob.setTarget(null);
                }
                if ((newTarget = this.findNewTarget(entity, livingCaster)) != null) {
                    mob.setTarget(newTarget);
                }
            }
            for (int i = 0; i < 5; ++i) {
                double offsetX = (entity.getRandom().nextDouble() - 0.5) * (double)entity.getBbWidth();
                double offsetY = entity.getRandom().nextDouble() * (double)entity.getBbHeight();
                double offsetZ = (entity.getRandom().nextDouble() - 0.5) * (double)entity.getBbWidth();
                MagicManager.spawnParticles((Level)entity.level(), (ParticleOptions)new LightningParticle.OrbData(41, 223, 235), (double)(entity.getX() + offsetX), (double)(entity.getY() + offsetY), (double)(entity.getZ() + offsetZ), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.3, (boolean)false);
            }
        }
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return duration % 10 == 0;
    }

    private LivingEntity findNewTarget(LivingEntity affectedEntity, LivingEntity caster) {
        Level level = affectedEntity.level();
        double range = 12.0;
        List possibleTargets = level.getNearbyEntities(LivingEntity.class, caster.getBoundingBox().inflate(range), entity -> entity != caster && entity != affectedEntity && entity.isAlive() && !(entity instanceof Player));
        if (!possibleTargets.isEmpty()) {
            return (LivingEntity)possibleTargets.get(RANDOM.nextInt(possibleTargets.size()));
        }
        return null;
    }

    private Entity getEffectSource(LivingEntity entity) {
        return entity.getStandingEyeHeight((MobEffect)TravelopticsEffects.PSYCHIC_CONTROL.get()) != null ? entity : null;
    }
}

