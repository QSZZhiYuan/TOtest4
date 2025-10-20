/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.projectile.Wither_Homing_Missile_Entity
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.entity.extended_projectiles;

import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.github.L_Ender.cataclysm.entity.projectile.Wither_Homing_Missile_Entity;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.damage.DamageSources;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ExtendedWitherHomingMissileEntity
extends Wither_Homing_Missile_Entity {
    private static final EntityDataAccessor<Float> EXTENDED_DAMAGE = SynchedEntityData.assignValue(ExtendedWitherHomingMissileEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> USE_MAGIC_DAMAGE = SynchedEntityData.assignValue(ExtendedWitherHomingMissileEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);

    public ExtendedWitherHomingMissileEntity(EntityType<? extends Wither_Homing_Missile_Entity> entityType, Level level) {
        super(entityType, level);
    }

    public ExtendedWitherHomingMissileEntity(Level level, LivingEntity owner) {
        super(level, owner);
    }

    public ExtendedWitherHomingMissileEntity(EntityType<? extends Wither_Homing_Missile_Entity> type, double x, double y, double z, Vec3 vec3, Level level) {
        super(type, x, y, z, vec3, level);
    }

    public ExtendedWitherHomingMissileEntity(LivingEntity owner, Vec3 vec3, Level level, float damage, LivingEntity target) {
        super(owner, vec3, level, damage, target);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.makeBoundingBox.assignValue(EXTENDED_DAMAGE, (Object)Float.valueOf(3.0f));
        this.makeBoundingBox.assignValue(USE_MAGIC_DAMAGE, (Object)true);
    }

    public float getExtendedHomingMissilesDamage() {
        return ((Float)this.makeBoundingBox.packDirty(EXTENDED_DAMAGE)).floatValue();
    }

    public void setExtendedHomingMissilesDamage(float damage) {
        this.makeBoundingBox.packDirty(EXTENDED_DAMAGE, (Object)Float.valueOf(damage));
    }

    public boolean shouldDealMagicDamage() {
        return (Boolean)this.makeBoundingBox.packDirty(USE_MAGIC_DAMAGE);
    }

    public void setShouldDealMagicDamage(boolean value) {
        this.makeBoundingBox.packDirty(USE_MAGIC_DAMAGE, (Object)value);
    }

    protected void setDangerous(EntityHitResult result) {
        super.setDangerous(result);
        if (!this.level().isClientSide) {
            Entity target = result.getEntity();
            float totalDamage = this.getExtendedHomingMissilesDamage();
            if (target instanceof LivingEntity) {
                LivingEntity livingTarget = (LivingEntity)target;
                if (this.shouldDealMagicDamage()) {
                    target.sendSystemMessage(this.damageSources().magic(), totalDamage);
                } else {
                    DamageSources.applyDamage((Entity)livingTarget, (float)totalDamage, (DamageSource)((AbstractSpell)TravelopticsSpells.RAPID_LASER_SPELL.get()).getDamageSource((Entity)this, this.getOwner()));
                }
                livingTarget.getStandingEyeHeight(new MobEffectInstance(MobEffects.WITHER, 100, 0));
            }
            this.level().getChunk((Entity)this, this.getX(), this.getY(), this.getZ(), 1.0f, false, Level.ExplosionInteraction.NONE);
            this.discard();
        }
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.accept("ExtendedHomingMissilesDamage", this.getExtendedHomingMissilesDamage());
        tag.accept("ShouldDealMagicDamage", this.shouldDealMagicDamage());
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("ExtendedHomingMissilesDamage")) {
            this.setExtendedHomingMissilesDamage(tag.getFloat("ExtendedHomingMissilesDamage"));
        }
        if (tag.contains("ShouldDealMagicDamage")) {
            this.setShouldDealMagicDamage(tag.getBoolean("ShouldDealMagicDamage"));
        }
    }
}

