/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.event.TickEvent$ServerTickEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 */
package com.gametechbc.traveloptics.effects.LingeringStrain;

import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.util.TravelopticsDamageTypes;
import io.redspace.ironsspellbooks.damage.DamageSources;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LingeringStrainHandler {
    private static final Map<UUID, PendingDamage> pendingDamage = new HashMap<UUID, PendingDamage>();

    @SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();
        if (source.is(TravelopticsDamageTypes.LINGERING_STRAIN)) {
            return;
        }
        if (entity.recreateFromPacket((MobEffect)TravelopticsEffects.LINGERING_STRAIN.get())) {
            float originalDamage = event.getAmount();
            event.setCanceled(true);
            float phasedDamage = originalDamage / 4.0f;
            pendingDamage.compute(entity.getUUID(), (uuid, existing) -> {
                if (existing == null) {
                    return new PendingDamage(phasedDamage, 4, (ResourceKey<Level>)entity.level().dimension());
                }
                existing.addDamage(phasedDamage);
                return existing;
            });
        }
    }

    @SubscribeEvent
    public static void onLivingDeathEvent(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        pendingDamage.remove(entity.getUUID());
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (pendingDamage.isEmpty()) {
                return;
            }
            ArrayList<UUID> toRemove = new ArrayList<UUID>();
            for (Map.Entry<UUID, PendingDamage> entry : pendingDamage.entrySet()) {
                UUID entityUUID = entry.getKey();
                PendingDamage damage = entry.getValue();
                ServerLevel level = event.getServer().minBlock(damage.dimension);
                if (level != null) {
                    LivingEntity entity = (LivingEntity)level.getRandomSequence(entityUUID);
                    if (entity != null && entity.isAlive()) {
                        damage.tick(entity);
                        if (!damage.isComplete()) continue;
                        toRemove.add(entityUUID);
                        continue;
                    }
                    toRemove.add(entityUUID);
                    continue;
                }
                toRemove.add(entityUUID);
            }
            toRemove.forEach(pendingDamage::remove);
        }
    }

    private static class PendingDamage {
        private float damagePerTick;
        private int ticksRemaining;
        private final ResourceKey<Level> dimension;
        private int tickCounter;

        public PendingDamage(float damagePerTick, int ticksRemaining, ResourceKey<Level> dimension) {
            this.damagePerTick = damagePerTick;
            this.ticksRemaining = ticksRemaining;
            this.dimension = dimension;
            this.tickCounter = 0;
        }

        public void addDamage(float extraDamage) {
            this.damagePerTick += extraDamage / 4.0f;
            this.ticksRemaining += 4;
        }

        public void tick(LivingEntity entity) {
            DamageSource damageSource = new DamageSource(DamageSources.getHolderFromResource((Entity)entity, TravelopticsDamageTypes.LINGERING_STRAIN), (Entity)entity, null);
            if (this.ticksRemaining > 0) {
                ++this.tickCounter;
                if (this.tickCounter >= 30) {
                    int armorPieces = 0;
                    for (ItemStack stack : entity.getArmorSlots()) {
                        if (stack.onUseTick()) continue;
                        ++armorPieces;
                    }
                    float armorMultiplier = 1.0f + (float)armorPieces * 0.15f;
                    float adjustedDamage = this.damagePerTick * armorMultiplier;
                    DamageSources.ignoreNextKnockback((LivingEntity)entity);
                    entity.sendSystemMessage(damageSource, adjustedDamage);
                    --this.ticksRemaining;
                    this.tickCounter = 0;
                    entity.level().getChunk(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.WARDEN_HEARTBEAT, SoundSource.PLAYERS, 1.0f, 1.0f);
                }
            }
        }

        public boolean isComplete() {
            return this.ticksRemaining <= 0;
        }
    }
}

