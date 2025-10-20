/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.projectile.Ignis_Fireball_Entity
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  net.minecraft.world.phys.EntityHitResult
 */
package com.gametechbc.traveloptics.entity.extended_projectiles;

import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.github.L_Ender.cataclysm.entity.projectile.Ignis_Fireball_Entity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class ExtendedIgnisFireballEntity
extends Ignis_Fireball_Entity {
    private float customDamage;

    public ExtendedIgnisFireballEntity(EntityType<? extends ExtendedIgnisFireballEntity> type, Level level) {
        super(type, level);
    }

    public ExtendedIgnisFireballEntity(Level level, LivingEntity entity, double x, double y, double z) {
        super(level, entity, x, y, z);
        this.customDamage = 1.0f;
    }

    public ExtendedIgnisFireballEntity(Level worldIn, LivingEntity entity) {
        this((EntityType<? extends ExtendedIgnisFireballEntity>)((EntityType)TravelopticsEntities.EXTENDED_IGNIS_FIREBALL.get()), worldIn);
        this.addAdditionalSaveData((Entity)entity);
        this.customDamage = 1.0f;
    }

    public void setCustomDamage(float damage) {
        this.customDamage = damage;
    }

    public float getCustomDamage() {
        return this.customDamage;
    }

    protected void setDangerous(EntityHitResult result) {
        super.setDangerous(result);
        Entity shooter = this.getOwner();
        if (!this.level().isClientSide && this.getFired() && !(result.getEntity() instanceof Ignis_Fireball_Entity)) {
            Entity entity = result.getEntity();
            if (shooter instanceof LivingEntity) {
                boolean flag;
                float damage;
                LivingEntity owner = (LivingEntity)shooter;
                float f = damage = this.isSoul() ? this.customDamage + 2.0f : this.customDamage;
                if (entity instanceof LivingEntity) {
                    LivingEntity target = (LivingEntity)entity;
                    flag = entity.sendSystemMessage(this.damageSources().badRespawnPointExplosion((Entity)this, owner), damage + target.getMaxHealth() * 0.07f);
                } else {
                    flag = entity.sendSystemMessage(this.damageSources().badRespawnPointExplosion((Entity)this, owner), damage);
                }
                if (flag) {
                    this.setLevel(owner, entity);
                    owner.sendRidingJump(5.0f);
                }
            } else {
                boolean flag = entity.sendSystemMessage(this.damageSources().magic(), this.customDamage);
            }
            this.level().getChunk((Entity)this, this.getX(), this.getY(), this.getZ(), 1.0f, true, Level.ExplosionInteraction.NONE);
            this.discard();
        }
    }

    public void lerpMotion() {
        super.lerpMotion();
    }
}

