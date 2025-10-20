/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.projectile.Flare_Bomb_Entity
 *  com.github.L_Ender.cataclysm.init.ModParticle
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.SchoolType
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.particle.ShockwaveParticleOptions
 *  io.redspace.ironsspellbooks.util.ParticleHelper
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  top.theillusivec4.curios.api.CuriosApi
 */
package com.gametechbc.traveloptics.effects;

import com.gametechbc.traveloptics.api.utils.TOCurioUtils;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedFlareBombEntity;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedIgnisFireballEntity;
import com.gametechbc.traveloptics.entity.mobs.EnragedDeadKingBoss;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.github.L_Ender.cataclysm.entity.projectile.Flare_Bomb_Entity;
import com.github.L_Ender.cataclysm.init.ModParticle;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.particle.ShockwaveParticleOptions;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.CuriosApi;

public class MeteorStormEffect
extends MobEffect {
    private int ticksSinceLastSummon = 0;
    private double outerRadius = 25.0;

    public MeteorStormEffect() {
        super(MobEffectCategory.BENEFICIAL, 0);
    }

    public void setOuterRadius(double radius) {
        this.outerRadius = radius;
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        Level level;
        if (!entity.level().isClientSide && (level = entity.level()) instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            ++this.ticksSinceLastSummon;
            if (this.ticksSinceLastSummon >= 15) {
                if (TOCurioUtils.getWearingCurio(entity, (Item)TravelopticsItems.FIRESTORM_RING.get()) || entity instanceof EnragedDeadKingBoss) {
                    this.summonFlareBombs(serverLevel, entity);
                } else {
                    this.summonProjectiles(serverLevel, entity);
                }
                this.ticksSinceLastSummon = 0;
            }
        }
        super.applyEffectTick(entity, amplifier);
    }

    private void summonProjectiles(ServerLevel serverLevel, LivingEntity target) {
        RandomSource random = target.getRandom();
        double innerRadius = 8.0;
        List nearbyEntities = serverLevel.getChunk((Entity)target, target.getBoundingBox().inflate(this.outerRadius), entity -> {
            LivingEntity targetEntity;
            return entity instanceof LivingEntity && !this.isTeammate(target, targetEntity = (LivingEntity)entity) && !this.isTamedCreature(target, targetEntity);
        });
        Collections.shuffle(nearbyEntities, new Random(random.nextLong()));
        int numEntitiesToTarget = Math.min(nearbyEntities.size(), 2);
        MobEffectInstance effect = target.getStandingEyeHeight((MobEffect)this);
        int amplifier = effect != null ? effect.getAmplifier() : 0;
        for (int i = 0; i < 3; ++i) {
            Vec3 spawnPosition;
            if (i < numEntitiesToTarget) {
                Entity targetEntity = (Entity)nearbyEntities.get(i);
                Vec3 motion = targetEntity.getDeltaMovement();
                double predictionTime = 1.0;
                Vec3 predictedPosition = targetEntity.position().reverse(motion.x(predictionTime));
                spawnPosition = new Vec3(predictedPosition.z, predictedPosition.multiply + 30.0, predictedPosition.reverse);
                MagicManager.spawnParticles((Level)serverLevel, (ParticleOptions)new ShockwaveParticleOptions(((SchoolType)SchoolRegistry.FIRE.get()).getTargetingColor(), -1.5f, true), (double)predictedPosition.z, (double)predictedPosition.multiply, (double)predictedPosition.reverse, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
            } else {
                double angle = random.nextDouble() * Math.PI * 2.0;
                double distance = innerRadius + random.nextDouble() * (this.outerRadius - innerRadius);
                double xOffset = Math.cos(angle) * distance;
                double zOffset = Math.sin(angle) * distance;
                double yOffset = 30.0;
                spawnPosition = new Vec3(target.getX() + xOffset, target.getY() + yOffset, target.getZ() + zOffset);
            }
            int adjustedAmplifier = amplifier;
            if (CuriosApi.getCuriosInventory((LivingEntity)target).map(curios -> !curios.findCurios(item -> item != null && item.onDestroyed((Item)TravelopticsItems.AZURE_IGNITION_BRACELET.get())).isEmpty()).orElse(false).booleanValue()) {
                ++adjustedAmplifier;
            }
            ExtendedIgnisFireballEntity fireball = new ExtendedIgnisFireballEntity((EntityType<? extends ExtendedIgnisFireballEntity>)((EntityType)TravelopticsEntities.EXTENDED_IGNIS_FIREBALL.get()), (Level)serverLevel);
            fireball.setLevel(spawnPosition);
            fireball.shoot(0.0, -1.0, 0.0, 2.5f, 0.0f);
            fireball.setSoul(target.getHealth() < target.getMaxHealth() * 0.5f || CuriosApi.getCuriosInventory((LivingEntity)target).map(curios -> !curios.findCurios(item -> item != null && item.onDestroyed((Item)TravelopticsItems.AZURE_IGNITION_BRACELET.get())).isEmpty()).orElse(false) != false);
            fireball.setCustomDamage(adjustedAmplifier);
            serverLevel.addFreshEntity((Entity)fireball);
            MagicManager.spawnParticles((Level)serverLevel, (ParticleOptions)((ParticleOptions)ModParticle.TRAP_FLAME.get()), (double)spawnPosition.z, (double)spawnPosition.multiply, (double)spawnPosition.reverse, (int)1, (double)0.0, (double)-1.0, (double)0.0, (double)0.0, (boolean)true);
            MagicManager.spawnParticles((Level)serverLevel, (ParticleOptions)ParticleHelper.SUNBEAM, (double)spawnPosition.z, (double)spawnPosition.multiply, (double)spawnPosition.reverse, (int)1, (double)1.0, (double)1.0, (double)1.0, (double)1.0, (boolean)false);
            MagicManager.spawnParticles((Level)serverLevel, (ParticleOptions)ParticleHelper.SUNBEAM, (double)spawnPosition.z, (double)spawnPosition.multiply, (double)spawnPosition.reverse, (int)1, (double)1.0, (double)1.0, (double)1.0, (double)1.0, (boolean)true);
        }
    }

    private void summonFlareBombs(ServerLevel serverLevel, LivingEntity entity) {
        MobEffectInstance effect = entity.getStandingEyeHeight((MobEffect)this);
        int amplifier = effect != null ? effect.getAmplifier() : 0;
        RandomSource random = entity.getRandom();
        double innerRadius = 4.0;
        for (int i = 0; i < 3; ++i) {
            double angle = random.nextDouble() * Math.PI * 2.0;
            double distance = innerRadius + random.nextDouble() * (this.outerRadius - innerRadius);
            double xOffset = Math.cos(angle) * distance;
            double zOffset = Math.sin(angle) * distance;
            double yOffset = 30.0;
            Vec3 spawnPosition = new Vec3(entity.getX() + xOffset, entity.getY() + yOffset, entity.getZ() + zOffset);
            int adjustedAmplifier = amplifier;
            if (CuriosApi.getCuriosInventory((LivingEntity)entity).map(curios -> !curios.findCurios(item -> item != null && item.onDestroyed((Item)TravelopticsItems.AZURE_IGNITION_BRACELET.get())).isEmpty()).orElse(false).booleanValue()) {
                ++adjustedAmplifier;
            }
            ExtendedFlareBombEntity flareBomb = new ExtendedFlareBombEntity((EntityType<? extends Flare_Bomb_Entity>)((EntityType)TravelopticsEntities.EXTENDED_FLARE_BOMB.get()), (Level)serverLevel);
            flareBomb.setLevel(spawnPosition);
            flareBomb.setIsInPowderSnow(0.0, -1.0, 0.0);
            flareBomb.setFlameJetDamage(adjustedAmplifier);
            flareBomb.addAdditionalSaveData((Entity)entity);
            serverLevel.addFreshEntity((Entity)flareBomb);
            MagicManager.spawnParticles((Level)serverLevel, (ParticleOptions)((ParticleOptions)ModParticle.TRAP_FLAME.get()), (double)spawnPosition.z, (double)spawnPosition.multiply, (double)spawnPosition.reverse, (int)1, (double)0.0, (double)-1.0, (double)0.0, (double)0.0, (boolean)true);
            MagicManager.spawnParticles((Level)serverLevel, (ParticleOptions)ParticleHelper.SUNBEAM, (double)spawnPosition.z, (double)spawnPosition.multiply, (double)spawnPosition.reverse, (int)1, (double)1.0, (double)1.0, (double)1.0, (double)1.0, (boolean)false);
            MagicManager.spawnParticles((Level)serverLevel, (ParticleOptions)ParticleHelper.SUNBEAM, (double)spawnPosition.z, (double)spawnPosition.multiply, (double)spawnPosition.reverse, (int)1, (double)1.0, (double)1.0, (double)1.0, (double)1.0, (boolean)true);
        }
    }

    private boolean isTeammate(LivingEntity caster, LivingEntity target) {
        return caster.isAlliedTo((Entity)target);
    }

    private boolean isTamedCreature(LivingEntity caster, LivingEntity target) {
        if (target instanceof TamableAnimal) {
            TamableAnimal tamedAnimal = (TamableAnimal)target;
            return tamedAnimal.isTame() && tamedAnimal.getOwner() == caster;
        }
        return false;
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }
}

