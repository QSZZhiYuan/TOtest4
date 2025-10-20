/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible
 *  io.redspace.ironsspellbooks.entity.spells.AoeEntity
 *  io.redspace.ironsspellbooks.util.ParticleHelper
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.entity.projectiles;

import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.util.TravelopticsDamageTypes;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import java.util.Optional;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

public class BlizzardAoe
extends AoeEntity
implements AntiMagicSusceptible {
    private int slownessAmplifier = 0;
    private DamageSource damageSource;

    public BlizzardAoe(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BlizzardAoe(Level level) {
        this((EntityType<? extends Projectile>)((EntityType)TravelopticsEntities.BLIZZARD_ENTITY.get()), level);
    }

    public int getSlownessAmplifier() {
        return this.slownessAmplifier;
    }

    public void setSlownessAmplifier(int slownessAmplifier) {
        this.slownessAmplifier = slownessAmplifier;
    }

    public void applyEffect(LivingEntity target) {
        if (this.damageSource == null) {
            this.damageSource = new DamageSource(DamageSources.getHolderFromResource((Entity)target, TravelopticsDamageTypes.BLIZZARD_DAMAGE), (Entity)this, this.getOwner());
        }
        DamageSources.ignoreNextKnockback((LivingEntity)target);
        target.sendSystemMessage(this.damageSource, this.getDamage());
        target.getStandingEyeHeight(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, this.getSlownessAmplifier()));
    }

    public void lerpMotion() {
        super.lerpMotion();
        if (!this.level().isClientSide && this.getTags == 1) {
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.BLIZZARD_AOE_IDLE.get(), 1.0f, 1.0f);
        }
        this.spawnRainParticles();
        if (this.getTags % 5 == 0) {
            this.spawnCloudParticles();
        }
        if (!this.level().isClientSide && this.getTags >= 100) {
            this.discard();
        }
    }

    private void spawnRainParticles() {
        double radius = this.getRadius();
        double snowStartY = this.getY() + 4.0;
        int snowParticleCount = (int)(radius * radius);
        for (int i = 0; i < snowParticleCount; ++i) {
            double angle = this.getId.nextDouble() * Math.PI * 2.0;
            double distance = this.getId.nextDouble() * radius;
            double randomX = this.getX() + Math.cos(angle) * distance;
            double randomZ = this.getZ() + Math.sin(angle) * distance;
            double randomY = snowStartY + this.getId.nextDouble() * 3.0;
            double velocityX = (this.getId.nextDouble() - 0.5) * 0.2;
            double velocityY = -0.3 - this.getId.nextDouble() * 0.4;
            double velocityZ = (this.getId.nextDouble() - 0.5) * 0.2;
            this.level().addDestroyBlockEffect((ParticleOptions)ParticleTypes.WHITE_ASH, randomX, randomY, randomZ, velocityX, velocityY, velocityZ);
        }
        int additionalSnow = (int)(radius * radius);
        for (int i = 0; i < additionalSnow; ++i) {
            double angle = this.getId.nextDouble() * Math.PI * 2.0;
            double distance = this.getId.nextDouble() * radius;
            double randomX = this.getX() + Math.cos(angle) * distance;
            double randomZ = this.getZ() + Math.sin(angle) * distance;
            double randomY = snowStartY + this.getId.nextDouble() * 4.0;
            double velocityX = (this.getId.nextDouble() - 0.5) * 0.3;
            double velocityY = -0.1 - this.getId.nextDouble() * 0.2;
            double velocityZ = (this.getId.nextDouble() - 0.5) * 0.3;
            this.level().addDestroyBlockEffect((ParticleOptions)ParticleTypes.SNOWFLAKE, randomX, randomY, randomZ, velocityX, velocityY, velocityZ);
        }
        if (this.getTags % 3 == 0) {
            int windParticles = (int)(radius * 2.0);
            for (int i = 0; i < windParticles; ++i) {
                double angle = this.getId.nextDouble() * Math.PI * 2.0;
                double distance = this.getId.nextDouble() * radius;
                double randomX = this.getX() + Math.cos(angle) * distance;
                double randomZ = this.getZ() + Math.sin(angle) * distance;
                double randomY = this.getY() + this.getId.nextDouble() * 1.5;
                double velocityX = (this.getId.nextDouble() - 0.5) * 0.1;
                double velocityY = 0.02 + this.getId.nextDouble() * 0.03;
                double velocityZ = (this.getId.nextDouble() - 0.5) * 0.1;
                this.level().addDestroyBlockEffect((ParticleOptions)ParticleTypes.CLOUD, randomX, randomY, randomZ, velocityX, velocityY, velocityZ);
            }
        }
    }

    private void spawnCloudParticles() {
        double radius = this.getRadius();
        int particleCount = (int)(1.0f * this.getRadius());
        for (int i = 0; i < particleCount; ++i) {
            double randomX = this.getX() + (this.getId.nextDouble() * 2.0 * radius - radius);
            double randomY = this.getY() + 8.0;
            double randomZ = this.getZ() + (this.getId.nextDouble() * 2.0 * radius - radius);
            double velocityY = -0.5 - this.getId.nextDouble() * 0.5;
            this.level().addDestroyBlockEffect(ParticleHelper.FOG, randomX, randomY, randomZ, 0.0, velocityY, 0.0);
        }
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
        compound.accept("SlownessAmplifier", this.slownessAmplifier);
    }

    protected void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.readNamedTagName("SlownessAmplifier", 3)) {
            this.slownessAmplifier = compound.copy("SlownessAmplifier");
        }
    }
}

