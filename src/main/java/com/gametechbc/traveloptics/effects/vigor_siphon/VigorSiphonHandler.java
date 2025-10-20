/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.entity.mobs.MagicSummon
 *  io.redspace.ironsspellbooks.util.ParticleHelper
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 */
package com.gametechbc.traveloptics.effects.vigor_siphon;

import com.gametechbc.traveloptics.effects.vigor_siphon.VigorSiphonEffect;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.entity.mobs.MagicSummon;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import java.util.Comparator;
import java.util.List;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class VigorSiphonHandler {
    private static final int COOLDOWN_TICKS = 50;

    @SubscribeEvent
    public static void onDamage(LivingHurtEvent event) {
        long lastHeal;
        MagicSummon summon;
        LivingEntity owner;
        LivingEntity dl;
        LivingEntity le;
        Entity direct = event.getSource().getDirectEntity();
        Entity source = event.getSource().getEntity();
        LivingEntity sourceEntity = source instanceof LivingEntity ? (le = (LivingEntity)source) : (direct instanceof LivingEntity ? (dl = (LivingEntity)direct) : null);
        long gameTime = event.getEntity().level().getGameTime();
        if (sourceEntity instanceof MagicSummon && (owner = (summon = (MagicSummon)sourceEntity).getSummoner()) != null && owner.isAlive() && owner.recreateFromPacket((MobEffect)TravelopticsEffects.VIGOR_SIPHON.get()) && owner.getZ((Entity)summon) <= VigorSiphonEffect.getConnectionRange() * VigorSiphonEffect.getConnectionRange() && gameTime - (lastHeal = owner.getPersistentData().entries("vigor_siphon_summon_to_owner")) >= 50L) {
            int amplifier = owner.getStandingEyeHeight((MobEffect)TravelopticsEffects.VIGOR_SIPHON.get()).getAmplifier();
            float healAmount = owner.getMaxHealth() * (float)(amplifier + 1) / 100.0f;
            owner.getPersistentData().accept("vigor_siphon_summon_to_owner", gameTime);
            owner.sendRidingJump(healAmount);
        }
        if (sourceEntity != null && sourceEntity.recreateFromPacket((MobEffect)TravelopticsEffects.VIGOR_SIPHON.get())) {
            Level level = sourceEntity.level();
            if (level.isClientSide) {
                return;
            }
            double radius = VigorSiphonEffect.getConnectionRange();
            AABB box = sourceEntity.getBoundingBox().inflate(radius);
            List summons = level.getNearbyEntities(LivingEntity.class, box, entity -> {
                MagicSummon magicSummon;
                return entity instanceof MagicSummon && (magicSummon = (MagicSummon)entity).getSummoner() != null && magicSummon.getSummoner().getUUID().equals(sourceEntity.getUUID()) && entity.isAlive();
            });
            summons.stream().min(Comparator.comparingDouble(arg_0 -> ((LivingEntity)sourceEntity).getZ(arg_0))).ifPresent(nearest -> {
                long lastHeal = nearest.getPersistentData().entries("vigor_siphon_owner_to_summon");
                if (gameTime - lastHeal >= 50L) {
                    int amplifier = sourceEntity.getStandingEyeHeight((MobEffect)TravelopticsEffects.VIGOR_SIPHON.get()).getAmplifier();
                    float healAmount = nearest.getMaxHealth() * (float)(amplifier + 1) / 100.0f;
                    nearest.getPersistentData().accept("vigor_siphon_owner_to_summon", gameTime);
                    nearest.sendRidingJump(healAmount);
                }
            });
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (!entity.recreateFromPacket((MobEffect)TravelopticsEffects.VIGOR_SIPHON.get())) {
            return;
        }
        Level level = entity.level();
        if (level.isClientSide) {
            return;
        }
        double radius = VigorSiphonEffect.getConnectionRange();
        AABB searchBox = entity.getBoundingBox().inflate(radius);
        List summons = level.getNearbyEntities(LivingEntity.class, searchBox, e -> {
            MagicSummon magicSummon;
            return e instanceof MagicSummon && (magicSummon = (MagicSummon)e).getSummoner() != null && magicSummon.getSummoner().getUUID().equals(entity.getUUID()) && e.isAlive();
        });
        LivingEntity sacrifice = summons.stream().min(Comparator.comparingDouble(arg_0 -> ((LivingEntity)entity).getZ(arg_0))).orElse(null);
        if (sacrifice != null) {
            event.setCanceled(true);
            entity.setHealth(8.0f);
            sacrifice.sendSystemMessage(sacrifice.damageSources().magic(), Float.MAX_VALUE);
            VigorSiphonHandler.createDeathSwapParticleEffect(level, entity, sacrifice);
            entity.broadcastBreakEvent((MobEffect)TravelopticsEffects.VIGOR_SIPHON.get());
        }
    }

    private static void createDeathSwapParticleEffect(Level level, LivingEntity owner, LivingEntity sacrifice) {
        Vec3 ownerPos = owner.position().y(0.0, (double)owner.getBbHeight() * 0.5, 0.0);
        Vec3 sacrificePos = sacrifice.position().y(0.0, (double)sacrifice.getBbHeight() * 0.5, 0.0);
        MagicManager.spawnParticles((Level)level, (ParticleOptions)ParticleHelper.BLOOD, (double)sacrificePos.z, (double)sacrificePos.multiply, (double)sacrificePos.reverse, (int)60, (double)2.0, (double)1.5, (double)2.0, (double)0.4, (boolean)false);
        MagicManager.spawnParticles((Level)level, (ParticleOptions)ParticleHelper.BLOOD, (double)sacrificePos.z, (double)(sacrificePos.multiply + 0.8), (double)sacrificePos.reverse, (int)40, (double)0.3, (double)0.1, (double)0.3, (double)0.4, (boolean)false);
        MagicManager.spawnParticles((Level)level, (ParticleOptions)ParticleHelper.BLOOD, (double)sacrificePos.z, (double)(sacrificePos.multiply - 0.5), (double)sacrificePos.reverse, (int)35, (double)1.8, (double)0.1, (double)1.8, (double)0.4, (boolean)false);
        MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.WARNING_PATH_RED_GLOWING_ENCHANT, (double)sacrificePos.z, (double)sacrificePos.multiply, (double)sacrificePos.reverse, (int)45, (double)1.5, (double)1.5, (double)1.5, (double)0.4, (boolean)false);
        MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.WARNING_PATH_RED_GLOWING_ENCHANT, (double)ownerPos.z, (double)ownerPos.multiply, (double)ownerPos.reverse, (int)50, (double)1.2, (double)1.2, (double)1.2, (double)0.4, (boolean)false);
        MagicManager.spawnParticles((Level)level, (ParticleOptions)ParticleHelper.BLOOD, (double)ownerPos.z, (double)ownerPos.multiply, (double)ownerPos.reverse, (int)25, (double)0.8, (double)0.8, (double)0.8, (double)0.4, (boolean)false);
        MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.WARNING_PATH_RED_GLOWING_ENCHANT, (double)ownerPos.z, (double)(ownerPos.multiply - 0.5), (double)ownerPos.reverse, (int)30, (double)0.8, (double)0.2, (double)0.8, (double)0.4, (boolean)false);
    }
}

