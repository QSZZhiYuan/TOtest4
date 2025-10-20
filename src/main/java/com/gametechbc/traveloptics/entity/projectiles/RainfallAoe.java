/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible
 *  io.redspace.ironsspellbooks.entity.spells.AoeEntity
 *  io.redspace.ironsspellbooks.util.ParticleHelper
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.entity.projectiles;

import com.gametechbc.traveloptics.config.CommonConfig;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

public class RainfallAoe
extends AoeEntity
implements AntiMagicSusceptible {
    private int wetEffectAmplifier = 0;
    private int tickCounter;

    public static List<? extends String> getImmuneEffectsList() {
        return (List)CommonConfig.rainfallImmuneEffects.get();
    }

    public RainfallAoe(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public RainfallAoe(Level level) {
        this((EntityType<? extends Projectile>)((EntityType)TravelopticsEntities.RAINFALL_ENTITY.get()), level);
    }

    public int getWetEffectAmplifier() {
        return this.wetEffectAmplifier;
    }

    public void setWetEffectAmplifier(int wetEffectAmplifier) {
        this.wetEffectAmplifier = wetEffectAmplifier;
    }

    public void lerpMotion() {
        super.lerpMotion();
        ++this.tickCounter;
        if (!this.level().isClientSide && this.tickCounter == 1) {
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.RAINFALL_ACTIVE.get(), 1.5f, 1.0f);
        }
        if (this.getTags % 5 == 0) {
            this.cleanseEntities();
            this.applyWetEffectToEntities();
            this.extinguishEntitiesFire();
        }
        this.spawnRainParticles();
        if (this.getTags % 5 == 0) {
            this.spawnCloudParticles();
        }
        if (!this.level().isClientSide && this.getTags >= 100) {
            this.discard();
        }
    }

    private void applyWetEffectToEntities() {
        this.level().getChunk((Entity)this, this.getBoundingBox(), entity -> entity instanceof LivingEntity).forEach(entity -> {
            LivingEntity target = (LivingEntity)entity;
            target.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.WET.get(), 100, this.getWetEffectAmplifier(), false, false, true));
        });
    }

    private void extinguishEntitiesFire() {
        this.level().getChunk((Entity)this, this.getBoundingBox(), entity -> entity instanceof LivingEntity).forEach(Entity::clearFire);
    }

    private void cleanseEntities() {
        LivingEntity owner = (LivingEntity)this.getOwner();
        if (owner == null) {
            return;
        }
        this.level().getChunk((Entity)this, this.getBoundingBox(), entity -> entity instanceof LivingEntity).forEach(entity -> {
            LivingEntity target = (LivingEntity)entity;
            if (target == owner || this.isAlly(owner, target) || this.isTamed(target)) {
                this.cleanseNegativeEffects(target);
            }
        });
    }

    private void cleanseNegativeEffects(LivingEntity entity) {
        List<MobEffect> negativeEffects = entity.getActiveEffects().stream().map(MobEffectInstance::compareTo).filter(effect -> effect.getCategory() == MobEffectCategory.HARMFUL).filter(effect -> !this.isEffectImmune((MobEffect)effect)).toList();
        negativeEffects.forEach(arg_0 -> ((LivingEntity)entity).broadcastBreakEvent(arg_0));
    }

    private boolean isEffectImmune(MobEffect effect) {
        ResourceLocation effectLocation = BuiltInRegistries.MOB_EFFECT.getTag((Object)effect);
        List<? extends String> immuneEffects = RainfallAoe.getImmuneEffectsList();
        return effectLocation != null && immuneEffects.contains(effectLocation.toString());
    }

    private boolean isAlly(LivingEntity owner, LivingEntity target) {
        return owner.getTeam() != null && owner.getTeam().isAlliedTo(target.getTeam());
    }

    private boolean isTamed(LivingEntity target) {
        if (target instanceof TamableAnimal) {
            TamableAnimal tamableAnimal = (TamableAnimal)target;
            return tamableAnimal.isTame();
        }
        return false;
    }

    private void spawnRainParticles() {
        double radius = this.getRadius();
        int particleCount = (int)(6.0f * this.getRadius());
        for (int i = 0; i < particleCount; ++i) {
            double randomX = this.getX() + (this.getId.nextDouble() * 2.0 * radius - radius);
            double randomY = this.getY() + 10.0;
            double randomZ = this.getZ() + (this.getId.nextDouble() * 2.0 * radius - radius);
            double velocityY = -0.5 - this.getId.nextDouble() * 0.5;
            this.level().addDestroyBlockEffect(TravelopticsParticleHelper.WATER_DROP, randomX, randomY, randomZ, 0.0, velocityY, 0.0);
        }
    }

    private void spawnCloudParticles() {
        double radius = this.getRadius();
        int particleCount = (int)(1.0f * this.getRadius());
        for (int i = 0; i < particleCount; ++i) {
            double randomX = this.getX() + (this.getId.nextDouble() * 2.0 * radius - radius);
            double randomY = this.getY() + 12.0;
            double randomZ = this.getZ() + (this.getId.nextDouble() * 2.0 * radius - radius);
            double velocityY = -0.5 - this.getId.nextDouble() * 0.5;
            this.level().addDestroyBlockEffect(ParticleHelper.FOG, randomX, randomY, randomZ, 0.0, velocityY, 0.0);
        }
    }

    public void applyEffect(LivingEntity target) {
    }

    public float getParticleCount() {
        return 0.0f;
    }

    protected float particleYOffset() {
        return 0.25f;
    }

    protected float getParticleSpeedModifier() {
        return 1.4f;
    }

    public Optional<ParticleOptions> getParticle() {
        return Optional.empty();
    }

    public void onAntiMagic(MagicData magicData) {
        this.discard();
    }

    protected void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.accept("WetEffectAmplifier", this.wetEffectAmplifier);
    }

    protected void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.readNamedTagName("WetEffectAmplifier", 3)) {
            this.wetEffectAmplifier = compound.copy("WetEffectAmplifier");
        }
    }
}

