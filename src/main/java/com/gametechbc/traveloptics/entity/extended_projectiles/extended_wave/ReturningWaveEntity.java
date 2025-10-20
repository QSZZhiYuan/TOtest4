/*
 * Decompiled with CFR 0.152.
 * 
 * Refactored to remove Alex's Caves dependency - now extends Entity directly
 */
package com.gametechbc.traveloptics.entity.extended_projectiles.extended_wave;

import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.damage.DamageSources;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.PlayMessages;

public class ReturningWaveEntity extends Entity {
    private float damageAmount;
    private int wetAmplifier = 1;
    private boolean isReturnWave = false;
    private boolean hasSpawnedReturn = false;
    private LivingEntity owner;
    
    // Wave properties for return mechanics
    protected int activeWaveTicks = 0;
    private int lifespan = 100;
    private float waveScale = 1.0f;
    private boolean slamming = false;

    public ReturningWaveEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public ReturningWaveEntity(Level level, LivingEntity shooter) {
        this((EntityType<?>)TravelopticsEntities.RETURNING_WAVE.get(), level);
        this.owner = shooter;
    }

    public ReturningWaveEntity(Level level, LivingEntity shooter, boolean isReturnWave) {
        this(level, shooter);
        this.isReturnWave = isReturnWave;
    }

    public ReturningWaveEntity(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this((EntityType<?>)TravelopticsEntities.RETURNING_WAVE.get(), level);
    }

    public void setDamageAmount(float damageAmount) {
        this.damageAmount = damageAmount;
    }

    public float getDamageAmount() {
        return this.damageAmount;
    }

    public int getWetAmplifier() {
        return this.wetAmplifier;
    }

    public void setWetAmplifier(int wetAmplifier) {
        this.wetAmplifier = wetAmplifier;
    }

    public boolean isReturnWave() {
        return this.isReturnWave;
    }

    public void setReturnWave(boolean returnWave) {
        this.isReturnWave = returnWave;
    }

    public LivingEntity getOwner() {
        return this.owner;
    }

    public int getLifespan() {
        return this.lifespan;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }

    public float getWaveScale() {
        return this.waveScale;
    }

    public void setWaveScale(float waveScale) {
        this.waveScale = waveScale;
    }

    public boolean isSlamming() {
        return this.slamming;
    }

    @Override
    public void tick() {
        super.tick();
        this.activeWaveTicks++;
        
        if (!this.level().isClientSide) {
            this.modifiedAttackEntities();
            if (!this.isReturnWave && !this.hasSpawnedReturn && this.shouldSpawnReturnWave()) {
                this.spawnReturnWave();
                this.hasSpawnedReturn = true;
            }
        }
    }

    private boolean shouldSpawnReturnWave() {
        return this.activeWaveTicks > this.getLifespan() - 1 || 
               this.activeWaveTicks > 9 && this.getDeltaMovement().lengthSqr() < 0.04 || 
               this.isSlamming();
    }

    private void spawnReturnWave() {
        if (this.level().isClientSide || this.getOwner() == null) {
            return;
        }
        ReturningWaveEntity returnWave = new ReturningWaveEntity(this.level(), this.getOwner(), true);
        returnWave.setPos(this.getX(), this.getY(), this.getZ());
        float reverseYaw = this.getYRot() + 180.0f;
        returnWave.setYRot(reverseYaw);
        returnWave.setDamageAmount(this.getDamageAmount());
        returnWave.setWetAmplifier(this.getWetAmplifier());
        returnWave.setWaveScale(this.getWaveScale());
        returnWave.setLifespan(this.getLifespan());
        this.level().addFreshEntity(returnWave);
    }

    private void modifiedAttackEntities() {
        AABB bashBox = this.getBoundingBox().inflate(0.5, 0.5, 0.5);
        for (LivingEntity entity : this.level().getEntitiesOfClass(LivingEntity.class, bashBox)) {
            if (this.isPassenger(entity) || 
                this.getOwner() != null && (this.getOwner().equals(entity) || this.getOwner().isPassenger(entity))) {
                continue;
            }
            DamageSources.applyDamage(entity, this.getDamageAmount(), 
                ((AbstractSpell)TravelopticsSpells.TSUNAMI_SPELL.get()).getDamageSource(this, this.getOwner()));
            entity.addEffect(new MobEffectInstance((MobEffect)TravelopticsEffects.WET.get(), 200, this.getWetAmplifier()));
        }
    }

    @Override
    protected void defineSynchedData() {
        // Required override for Entity
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("DamageAmount")) {
            this.damageAmount = tag.getFloat("DamageAmount");
        }
        if (tag.contains("WetAmplifier", 3)) {
            this.wetAmplifier = tag.getInt("WetAmplifier");
        }
        if (tag.contains("IsReturnWave")) {
            this.isReturnWave = tag.getBoolean("IsReturnWave");
        }
        if (tag.contains("HasSpawnedReturn")) {
            this.hasSpawnedReturn = tag.getBoolean("HasSpawnedReturn");
        }
        if (tag.contains("Lifespan")) {
            this.lifespan = tag.getInt("Lifespan");
        }
        if (tag.contains("WaveScale")) {
            this.waveScale = tag.getFloat("WaveScale");
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putFloat("DamageAmount", this.damageAmount);
        compoundTag.putInt("WetAmplifier", this.wetAmplifier);
        compoundTag.putBoolean("IsReturnWave", this.isReturnWave);
        compoundTag.putBoolean("HasSpawnedReturn", this.hasSpawnedReturn);
        compoundTag.putInt("Lifespan", this.lifespan);
        compoundTag.putFloat("WaveScale", this.waveScale);
    }
}
