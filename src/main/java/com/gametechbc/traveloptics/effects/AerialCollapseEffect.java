/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.util.ParticleHelper
 *  net.minecraft.core.Holder
 *  net.minecraft.core.Holder$Reference
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.LivingEntity
 */
package com.gametechbc.traveloptics.effects;

import com.gametechbc.traveloptics.config.SpellsConfig;
import com.gametechbc.traveloptics.util.TravelopticsDamageTypes;
import com.gametechbc.traveloptics.util.TravelopticsTags;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import java.util.Random;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class AerialCollapseEffect
extends MobEffect {
    private static final Random RANDOM = new Random();

    public AerialCollapseEffect() {
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
                    float currentHealth = entity.getHealth();
                    float damagePercent = 0.01f * (float)(amplifier + 1);
                    float damageAmount = currentHealth * damagePercent;
                    if (entity.getType().tryCast(TravelopticsTags.AERIAL_COLLAPSE_DR)) {
                        double damageReductionMultiplier = (Double)SpellsConfig.aerialCollapseDamageReductionModifier.get();
                        damageAmount *= (float)damageReductionMultiplier;
                    }
                    Holder.Reference damageTypeHolder = entity.level().registryAccess().allRegistriesLifecycle(Registries.DAMAGE_TYPE).getHolderOrThrow(TravelopticsDamageTypes.AERIAL_COLLAPSE);
                    DamageSource damageSource = new DamageSource((Holder)damageTypeHolder);
                    entity.sendSystemMessage(damageSource, damageAmount);
                }
            }
            if (!entity.level().isClientSide && entity.getTags % 2 == 0) {
                for (int i = 0; i < 5; ++i) {
                    double offsetX = (RANDOM.nextDouble() - 0.5) * (double)entity.getBbWidth();
                    double offsetY = RANDOM.nextDouble() * (double)entity.getBbHeight();
                    double offsetZ = (RANDOM.nextDouble() - 0.5) * (double)entity.getBbWidth();
                    ((ServerLevel)entity.level()).getRandomSequence(ParticleHelper.ACID_BUBBLE, entity.getX() + offsetX, entity.getY() + offsetY, entity.getZ() + offsetZ, 1, 0.0, 0.0, 0.0, 0.0);
                }
            }
        }
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }
}

