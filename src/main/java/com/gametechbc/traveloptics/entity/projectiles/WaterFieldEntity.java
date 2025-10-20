/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.entity.spells.AoeEntity
 *  io.redspace.ironsspellbooks.util.ParticleHelper
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.entity.projectiles;

import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedWaterBoltEntity;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import java.util.Optional;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

public class WaterFieldEntity
extends AoeEntity {
    private int tickCounter;
    private float directBoltDamage = 50.0f;
    private float aoeBoltDamage = 30.0f;

    public WaterFieldEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    public WaterFieldEntity(Level level) {
        this((EntityType<? extends Projectile>)((EntityType)TravelopticsEntities.WATER_FIELD.get()), level);
    }

    public void lerpMotion() {
        super.lerpMotion();
        ++this.tickCounter;
        if (!this.level().isClientSide && this.tickCounter % 15 == 0) {
            this.summonWaterBolt();
        }
    }

    private void summonWaterBolt() {
        double radius = this.getRadius();
        double angle = this.getId.nextDouble() * Math.PI * 2.0;
        double distance = this.getId.nextDouble() * radius;
        double offsetX = distance * Math.cos(angle);
        double offsetZ = distance * Math.sin(angle);
        double x = this.getX() + offsetX;
        double y = this.getY() + 10.0;
        double z = this.getZ() + offsetZ;
        ExtendedWaterBoltEntity waterBolt = new ExtendedWaterBoltEntity((EntityType<? extends WaterBoltEntity>)((EntityType)TravelopticsEntities.EXTENDED_WATER_BOLT.get()), this.level());
        waterBolt.setPos(x, y, z);
        waterBolt.addAdditionalSaveData(this.getOwner());
        if (this.getOwner() != null && waterBolt.getOwner() != null && waterBolt.getOwner().equals((Object)this.getOwner())) {
            return;
        }
        waterBolt.setDirectDamageAmount(this.directBoltDamage);
        waterBolt.setAoeRadius(2.5f);
        waterBolt.setAoeDamageAmount(this.aoeBoltDamage);
        this.level().addFreshEntity((Entity)waterBolt);
    }

    public void applyEffect(LivingEntity target) {
    }

    public float getParticleCount() {
        return 1.2f * this.getRadius();
    }

    protected float particleYOffset() {
        return 0.25f;
    }

    protected float getParticleSpeedModifier() {
        return 1.4f;
    }

    public Optional<ParticleOptions> getParticle() {
        return Optional.of(ParticleHelper.ACID);
    }

    public float getDirectWaterBoltDamage() {
        return this.directBoltDamage;
    }

    public void setDirectWaterBoltDamage(float waterBoltDamage) {
        this.directBoltDamage = waterBoltDamage;
    }

    public float getAoeWaterBoltDamage() {
        return this.aoeBoltDamage;
    }

    public void setAoeWaterBoltDamage(float aoeWaterBoltDamage) {
        this.aoeBoltDamage = aoeWaterBoltDamage;
    }
}

