/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.config.CMConfig
 *  com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Harbinger_Entity
 *  com.github.L_Ender.cataclysm.entity.projectile.Wither_Howitzer_Entity
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.HitResult
 */
package com.gametechbc.traveloptics.entity.extended_projectiles;

import com.github.L_Ender.cataclysm.config.CMConfig;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Harbinger_Entity;
import com.github.L_Ender.cataclysm.entity.projectile.Wither_Howitzer_Entity;
import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ExtendedWitherHowitzerEntity
extends Wither_Howitzer_Entity {
    private float entityHitDamage;
    private float aoeEntityDamage;

    public ExtendedWitherHowitzerEntity(EntityType<? extends Wither_Howitzer_Entity> type, Level world) {
        super(type, world);
        this.entityHitDamage = 2.0f;
        this.aoeEntityDamage = 1.0f;
    }

    public ExtendedWitherHowitzerEntity(EntityType<? extends Wither_Howitzer_Entity> type, Level world, LivingEntity thrower, float entityHitDamage, float aoeEntityDamage) {
        super(type, world, thrower);
        this.entityHitDamage = entityHitDamage;
        this.aoeEntityDamage = aoeEntityDamage;
    }

    protected void setDangerous(EntityHitResult hitResult) {
        super.setDangerous(hitResult);
        if (!this.level().isClientSide) {
            boolean successfulHit;
            Entity hitEntity = hitResult.getEntity();
            Entity owner = this.getOwner();
            if (owner instanceof LivingEntity) {
                LivingEntity livingOwner = (LivingEntity)owner;
                successfulHit = hitEntity.sendSystemMessage(this.damageSources().badRespawnPointExplosion((Entity)this, livingOwner), this.entityHitDamage);
                if (successfulHit) {
                    if (hitEntity.isAlive()) {
                        this.setLevel(livingOwner, hitEntity);
                    } else if (owner instanceof The_Harbinger_Entity) {
                        livingOwner.sendRidingJump(5.0f * (float)CMConfig.HarbingerHealingMultiplier);
                    } else {
                        livingOwner.sendRidingJump(5.0f);
                    }
                }
            } else {
                successfulHit = hitEntity.sendSystemMessage(this.damageSources().magic(), this.entityHitDamage);
            }
            if (successfulHit) {
                this.applyAoeDamage(hitEntity.getX(), hitEntity.getY(), hitEntity.getZ(), this.aoeEntityDamage, this.getRadius());
            }
        }
    }

    protected void setDangerous(HitResult hitResult) {
        super.setDangerous(hitResult);
        if (!this.level().isClientSide) {
            this.level().getChunk((Entity)this, this.getX(), this.getY(), this.getZ(), 2.0f, false, Level.ExplosionInteraction.NONE);
            this.applyAoeDamage(this.getX(), this.getY(), this.getZ(), this.aoeEntityDamage, this.getRadius());
            this.discard();
        }
    }

    private void applyAoeDamage(double x, double y, double z, float damage, float radius) {
        List nearbyEntities = this.level().getNearbyEntities(LivingEntity.class, new AABB(x - (double)radius, y - (double)radius, z - (double)radius, x + (double)radius, y + (double)radius, z + (double)radius));
        for (LivingEntity entity : nearbyEntities) {
            if (!(this.getZ((Entity)entity) <= (double)(radius * radius))) continue;
            entity.sendSystemMessage(this.damageSources().magic(), damage);
        }
    }

    public void setEntityHitDamage(float entityHitDamage) {
        this.entityHitDamage = entityHitDamage;
    }

    public void setAoeEntityDamage(float aoeEntityDamage) {
        this.aoeEntityDamage = aoeEntityDamage;
    }

    public float getEntityHitDamage() {
        return this.entityHitDamage;
    }

    public float getAoeEntityDamage() {
        return this.aoeEntityDamage;
    }
}

