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

public class ExtendedWaveEntity extends Entity {
    private float damageAmount;
    private int wetAmplifier = 1;
    private LivingEntity owner;

    public ExtendedWaveEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public ExtendedWaveEntity(Level level, LivingEntity shooter) {
        this((EntityType<?>)TravelopticsEntities.EXTENDED_WAVE.get(), level);
        this.owner = shooter;
    }

    public ExtendedWaveEntity(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this((EntityType<?>)TravelopticsEntities.EXTENDED_WAVE.get(), level);
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

    public LivingEntity getOwner() {
        return this.owner;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            this.modifiedAttackEntities();
        }
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
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putFloat("DamageAmount", this.damageAmount);
        compoundTag.putInt("WetAmplifier", this.wetAmplifier);
    }
}
