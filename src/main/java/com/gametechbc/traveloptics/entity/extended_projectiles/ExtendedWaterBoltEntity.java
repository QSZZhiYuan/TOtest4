/*
 * Decompiled with CFR 0.152.
 * 
 * Refactored to remove Alex's Caves dependency - now extends AbstractHurtingProjectile
 */
package com.gametechbc.traveloptics.entity.extended_projectiles;

import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import java.util.List;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

public class ExtendedWaterBoltEntity extends AbstractHurtingProjectile {
    private float directDamageAmount;
    private float aoeDamageAmount;
    private float aoeRadius;
    private int dieIn = -1;
    private int tickCount = 0;

    public ExtendedWaterBoltEntity(PlayMessages.SpawnEntity spawnEntity, Level level) {
        super((EntityType<? extends AbstractHurtingProjectile>)com.gametechbc.traveloptics.init.TravelopticsEntities.EXTENDED_WATER_BOLT.get(), level);
    }

    public ExtendedWaterBoltEntity(EntityType<? extends AbstractHurtingProjectile> type, Level level) {
        super(type, level);
    }

    public ExtendedWaterBoltEntity(Level level, LivingEntity owner, float directDamageAmount, float aoeDamageAmount, float aoeRadius) {
        super((EntityType<? extends AbstractHurtingProjectile>)com.gametechbc.traveloptics.init.TravelopticsEntities.EXTENDED_WATER_BOLT.get(), level);
        this.setOwner(owner);
        this.directDamageAmount = directDamageAmount;
        this.aoeDamageAmount = aoeDamageAmount;
        this.aoeRadius = aoeRadius;
    }

    public void setDirectDamageAmount(float directDamageAmount) {
        this.directDamageAmount = directDamageAmount;
    }

    public float getDirectDamageAmount() {
        return this.directDamageAmount;
    }

    public void setAoeDamageAmount(float aoeDamageAmount) {
        this.aoeDamageAmount = aoeDamageAmount;
    }

    public float getAoeDamageAmount() {
        return this.aoeDamageAmount;
    }

    public void setAoeRadius(float aoeRadius) {
        this.aoeRadius = aoeRadius;
    }

    public float getAoeRadius() {
        return this.aoeRadius;
    }

    @Override
    public void tick() {
        super.tick();
        this.tickCount++;
        if (this.dieIn > 0) {
            this.dieIn--;
            if (this.dieIn <= 0) {
                this.discard();
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        if (!this.level().isClientSide && !this.isPassengerOfSameVehicle(hitResult.getEntity()) && this.tickCount > 2) {
            this.applyDirectDamage(hitResult);
            this.applyAOEDamage();
            if (this.getDieIn() == -1) {
                this.setDieIn();
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult blockResult) {
        super.onHitBlock(blockResult);
        if (!this.level().isClientSide && this.tickCount > 2) {
            this.applyAOEDamage();
            if (this.getDieIn() == -1) {
                this.setDieIn();
            }
        }
    }

    protected void applyDirectDamage(EntityHitResult hitResult) {
        Entity hitEntity = hitResult.getEntity();
        if (hitEntity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)hitEntity;
            if (!livingEntity.level().isClientSide) {
                SpellDamageSource damageSource = ((AbstractSpell)TravelopticsSpells.AQUA_MISSILES_SPELL.get()).getDamageSource(this, this.getOwner());
                livingEntity.hurt(damageSource, this.getDirectDamageAmount());
            }
        }
    }

    protected void applyAOEDamage() {
        List<Entity> nearbyEntities = this.level().getEntities(this, this.getBoundingBox().inflate(this.getAoeRadius()));
        for (Entity entity : nearbyEntities) {
            if (!(entity instanceof LivingEntity)) continue;
            LivingEntity livingEntity = (LivingEntity)entity;
            Entity owner = this.getOwner();
            if (this.isPassengerOfSameVehicle(entity) || 
                (owner instanceof LivingEntity && (this.isAlly((LivingEntity)owner, livingEntity) || this.isTamed(livingEntity)))) {
                continue;
            }
            SpellDamageSource damageSource = ((AbstractSpell)TravelopticsSpells.AQUA_MISSILES_SPELL.get()).getDamageSource(this, this.getOwner());
            livingEntity.hurt(damageSource, this.getAoeDamageAmount());
            livingEntity.addEffect(new MobEffectInstance((MobEffect)TravelopticsEffects.WET.get(), 100, 1));
        }
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

    protected int getDieIn() {
        return this.dieIn;
    }

    protected void setDieIn() {
        this.dieIn = 20;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("DirectDamageAmount", this.directDamageAmount);
        tag.putFloat("AoeDamageAmount", this.aoeDamageAmount);
        tag.putFloat("AoeRadius", this.aoeRadius);
        tag.putInt("DieIn", this.dieIn);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("DirectDamageAmount")) {
            this.directDamageAmount = tag.getFloat("DirectDamageAmount");
        }
        if (tag.contains("AoeDamageAmount")) {
            this.aoeDamageAmount = tag.getFloat("AoeDamageAmount");
        }
        if (tag.contains("AoeRadius")) {
            this.aoeRadius = tag.getFloat("AoeRadius");
        }
        if (tag.contains("DieIn")) {
            this.dieIn = tag.getInt("DieIn");
        }
    }
}
