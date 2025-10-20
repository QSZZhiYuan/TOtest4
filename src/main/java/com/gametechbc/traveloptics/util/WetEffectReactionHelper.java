/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.entity.spells.ChainLightning
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.util;

import com.gametechbc.traveloptics.entity.projectiles.BlizzardAoe;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.spells.ChainLightning;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.level.Level;

public class WetEffectReactionHelper {
    public static void triggerWetChainLightning(Level world, LivingEntity caster, LivingEntity victim) {
        if (!world.isClientSide) {
            ChainLightning chainLightning = new ChainLightning(world, (Entity)caster, (Entity)victim);
            chainLightning.setDamage(WetEffectReactionHelper.calculateWetChainLightningDamage(caster, victim));
            chainLightning.range = 8.0f;
            chainLightning.maxConnections = 3;
            world.addFreshEntity((Entity)chainLightning);
            WetEffectReactionHelper.consumeWetEffectAmplifier(victim);
        }
    }

    public static void triggerWetBlizzard(Level world, LivingEntity caster, LivingEntity victim) {
        if (!world.isClientSide) {
            BlizzardAoe blizzard = new BlizzardAoe(world);
            blizzard.setPos(victim.getX(), victim.getY(), victim.getZ());
            blizzard.addAdditionalSaveData((Entity)caster);
            blizzard.setRadius(4.5f);
            blizzard.setDamage(WetEffectReactionHelper.calculateWetBlizzardDamage(caster, victim));
            blizzard.setDuration(100);
            blizzard.setSlownessAmplifier(2);
            world.addFreshEntity((Entity)blizzard);
            WetEffectReactionHelper.removeWetEffect(victim);
        }
    }

    private static float calculateWetChainLightningDamage(LivingEntity caster, LivingEntity victim) {
        float baseDamage = 2.79f;
        MobEffectInstance wetEffect = victim.getStandingEyeHeight((MobEffect)TravelopticsEffects.WET.get());
        int wetAmplifier = wetEffect != null ? wetEffect.getAmplifier() : 0;
        float wetBonusDamage = (float)wetAmplifier * 0.86f;
        double lightningSpellPower = caster.getStandingEyeHeight((Attribute)AttributeRegistry.LIGHTNING_SPELL_POWER.get());
        float chainLightningTotalDamage = (baseDamage + wetBonusDamage) * (float)lightningSpellPower;
        return chainLightningTotalDamage *= 0.9f;
    }

    private static void consumeWetEffectAmplifier(LivingEntity entity) {
        MobEffectInstance wetEffect = entity.getStandingEyeHeight((MobEffect)TravelopticsEffects.WET.get());
        if (wetEffect == null) {
            return;
        }
        int currentAmplifier = wetEffect.getAmplifier();
        int currentDuration = wetEffect.getDuration();
        if (currentAmplifier <= 0) {
            entity.broadcastBreakEvent((MobEffect)TravelopticsEffects.WET.get());
            return;
        }
        entity.broadcastBreakEvent((MobEffect)TravelopticsEffects.WET.get());
        MobEffectInstance downgradedWet = new MobEffectInstance((MobEffect)TravelopticsEffects.WET.get(), currentDuration, currentAmplifier - 1, false, false, true);
        entity.getStandingEyeHeight(downgradedWet);
    }

    private static float calculateWetBlizzardDamage(LivingEntity caster, LivingEntity victim) {
        float baseDamage = 6.5f;
        MobEffectInstance wetEffect = victim.getStandingEyeHeight((MobEffect)TravelopticsEffects.WET.get());
        int wetAmplifier = wetEffect != null ? wetEffect.getAmplifier() : 0;
        float wetBonusDamage = (float)wetAmplifier * 2.0f;
        double iceSpellPower = caster.getStandingEyeHeight((Attribute)AttributeRegistry.ICE_SPELL_POWER.get());
        float blizzardTotalDamage = (baseDamage + wetBonusDamage) * (float)iceSpellPower;
        return blizzardTotalDamage / 10.0f;
    }

    private static void removeWetEffect(LivingEntity entity) {
        if (entity.recreateFromPacket((MobEffect)TravelopticsEffects.WET.get())) {
            entity.broadcastBreakEvent((MobEffect)TravelopticsEffects.WET.get());
        }
    }
}

