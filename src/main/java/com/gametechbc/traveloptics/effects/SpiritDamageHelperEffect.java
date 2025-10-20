/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.effects;

import java.util.List;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class SpiritDamageHelperEffect
extends MobEffect {
    public SpiritDamageHelperEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFFFFF);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        int duration;
        MobEffectInstance effectInstance = entity.getStandingEyeHeight((MobEffect)this);
        if (effectInstance != null && (duration = effectInstance.getDuration()) == 2) {
            this.findAndDamageTremorsaurus(entity, amplifier);
        }
    }

    private void findAndDamageTremorsaurus(LivingEntity entity, int amplifier) {
        // REMOVED: DinosaurSpirit functionality (Alex's Caves dependency)
        // This effect no longer interacts with Tremorsaurus spirits
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }
}

