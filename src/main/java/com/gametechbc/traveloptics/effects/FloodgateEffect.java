/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.entity.LivingEntity
 */
package com.gametechbc.traveloptics.effects;

import com.gametechbc.traveloptics.init.TravelopticsEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class FloodgateEffect
extends MobEffect {
    public FloodgateEffect() {
        super(MobEffectCategory.BENEFICIAL, 65280);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return false;
    }

    public static float applyDamageCap(LivingEntity entity, float originalDamage) {
        double maxHealth;
        double damageCap;
        if (entity.recreateFromPacket((MobEffect)TravelopticsEffects.FLOODGATE_EFFECT.get()) && (double)originalDamage > (damageCap = (maxHealth = (double)entity.getMaxHealth()) * 0.3)) {
            return (float)damageCap;
        }
        return originalDamage;
    }
}

