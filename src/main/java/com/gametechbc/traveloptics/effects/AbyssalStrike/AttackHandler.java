/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 */
package com.gametechbc.traveloptics.effects.AbyssalStrike;

import com.gametechbc.traveloptics.effects.AbyssalStrike.AbyssalStrikeEffect;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid="traveloptics")
public class AttackHandler {
    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        Entity entity = event.getSource().getEntity();
        if (entity instanceof LivingEntity) {
            LivingEntity attackerEntity = (LivingEntity)entity;
            if (event.getEntity() instanceof LivingEntity) {
                LivingEntity targetEntity = event.getEntity();
                if (attackerEntity.recreateFromPacket((MobEffect)TravelopticsEffects.ABYSSAL_STRIKE.get())) {
                    int amplifier = attackerEntity.getStandingEyeHeight((MobEffect)TravelopticsEffects.ABYSSAL_STRIKE.get()).getAmplifier();
                    AbyssalStrikeEffect effect = (AbyssalStrikeEffect)attackerEntity.getStandingEyeHeight((MobEffect)TravelopticsEffects.ABYSSAL_STRIKE.get()).compareTo();
                    effect.onAttack(attackerEntity, targetEntity, amplifier);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event) {
        LivingEntity attacker;
        Entity entity = event.getSource().getEntity();
        if (entity instanceof LivingEntity && (attacker = (LivingEntity)entity).recreateFromPacket((MobEffect)TravelopticsEffects.ASSASSIN.get())) {
            attacker.broadcastBreakEvent((MobEffect)TravelopticsEffects.ASSASSIN.get());
        }
    }
}

