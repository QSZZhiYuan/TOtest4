/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.living.LivingKnockBackEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 */
package com.gametechbc.traveloptics.effects.Reversal;

import com.gametechbc.traveloptics.init.TravelopticsEffects;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ReversalEffectHandler {
    private static final Map<UUID, Float> storedDamageMap = new HashMap<UUID, Float>();
    private static final Map<UUID, Projectile> lastProjectileHitMap = new HashMap<UUID, Projectile>();

    @SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.recreateFromPacket((MobEffect)TravelopticsEffects.REVERSAL.get())) {
            float damage = event.getAmount();
            ReversalEffectHandler.storeDamage(entity, damage);
            Entity entity2 = event.getSource().getDirectEntity();
            if (entity2 instanceof Projectile) {
                Projectile projectile = (Projectile)entity2;
                ReversalEffectHandler.storeLastProjectileHit(entity, projectile);
            }
            entity.setIsInPowderSnow(0.0, 0.0, 0.0);
            entity.getAddEntityPacket = false;
            entity.setYBodyRot(10);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onLivingKnockBack(LivingKnockBackEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.recreateFromPacket((MobEffect)TravelopticsEffects.REVERSAL.get())) {
            event.setCanceled(true);
        }
    }

    public static void storeDamage(LivingEntity entity, float damage) {
        storedDamageMap.put(entity.getUUID(), Float.valueOf(storedDamageMap.getOrDefault(entity.getUUID(), Float.valueOf(0.0f)).floatValue() + damage));
    }

    public static float getStoredDamage(LivingEntity entity) {
        return storedDamageMap.getOrDefault(entity.getUUID(), Float.valueOf(0.0f)).floatValue();
    }

    public static void clearStoredDamage(LivingEntity entity) {
        storedDamageMap.remove(entity.getUUID());
    }

    public static void storeLastProjectileHit(LivingEntity entity, Projectile projectile) {
        lastProjectileHitMap.put(entity.getUUID(), projectile);
    }

    public static Projectile getLastProjectileHit(LivingEntity entity) {
        return lastProjectileHitMap.get(entity.getUUID());
    }

    public static void clearLastProjectileHit(LivingEntity entity) {
        lastProjectileHitMap.remove(entity.getUUID());
    }
}

