/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity
 *  io.redspace.ironsspellbooks.entity.spells.AoeEntity
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.misc;

import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsParticles;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class EndEruptionEntity
extends AoeEntity {
    private static final EntityDataAccessor<Integer> WINDUP = SynchedEntityData.assignValue(EndEruptionEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private boolean triggered = false;
    private boolean eruptionStarted = false;

    public EndEruptionEntity(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    public EndEruptionEntity(Level level) {
        this((EntityType<? extends Projectile>)((EntityType)TravelopticsEntities.END_ERUPTION.get()), level);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.makeBoundingBox.assignValue(WINDUP, (Object)60);
    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.accept("WindupDuration", this.getWindupDuration());
        tag.accept("Triggered", this.triggered);
        tag.accept("EruptionStarted", this.eruptionStarted);
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("WindupDuration")) {
            this.setWindupDuration(tag.copy("WindupDuration"));
        }
        if (tag.contains("Triggered")) {
            this.triggered = tag.getBoolean("Triggered");
        }
        if (tag.contains("EruptionStarted")) {
            this.eruptionStarted = tag.getBoolean("EruptionStarted");
        }
    }

    public void setWindupDuration(int ticks) {
        this.makeBoundingBox.packDirty(WINDUP, (Object)ticks);
    }

    public int getWindupDuration() {
        return (Integer)this.makeBoundingBox.packDirty(WINDUP);
    }

    public void lerpMotion() {
        super.lerpMotion();
        if (this.level().isClientSide) {
            this.handleClientParticles();
        }
        if (!this.eruptionStarted && this.getTags >= this.getWindupDuration() - 5) {
            this.eruptionStarted = true;
        }
        if (!this.triggered && this.getTags >= this.getWindupDuration()) {
            this.triggerEruption();
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.END_ERUPTION_AMBIENT.get(), 1.0f, 1.0f);
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.END_ERUPTION_BLAST.get(), 1.0f, 1.0f);
        } else if (this.triggered && this.getTags >= this.getWindupDuration() + 20) {
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.END_ERUPTION_BLAST.get(), 1.0f, 1.0f);
            this.triggerEruption();
            this.discard();
        }
    }

    private void handleClientParticles() {
        double radius = this.getRadius();
        Vec3 center = this.position();
        if (this.getTags < this.getWindupDuration() - 5) {
            if ((double)this.getTags < (double)this.getWindupDuration() * 0.3) {
                this.createMagicCircleFormation(center, radius);
            } else if ((double)this.getTags < (double)this.getWindupDuration() * 0.7) {
                this.createEnergyGathering(center, radius);
            } else {
                this.createIntenseBuildup(center, radius);
            }
        } else if (this.getTags < this.getWindupDuration()) {
            this.createPreEruptionParticles(center, radius);
        } else if (this.getTags < this.getWindupDuration() + 20) {
            this.createEruptionParticles(center, radius);
        }
    }

    private void createMagicCircleFormation(Vec3 center, double radius) {
        float progress = (float)this.getTags / ((float)this.getWindupDuration() * 0.3f);
        int circleCount = 3;
        for (int circle = 0; circle < circleCount; ++circle) {
            double circleRadius = radius * (0.4 + (double)circle * 0.3);
            int particlesPerCircle = 16 * (circle + 1);
            for (int i = 0; i < particlesPerCircle; ++i) {
                double angle = Math.PI * 2 * (double)i / (double)particlesPerCircle + (double)this.getTags * 0.05 * (double)(circle + 1);
                double x = center.z + Math.cos(angle) * circleRadius;
                double z = center.reverse + Math.sin(angle) * circleRadius;
                double y = center.multiply + 0.1 + Math.sin((double)this.getTags * 0.1 + (double)i) * 0.05;
                if (!(this.getId.nextFloat() < progress * 0.8f)) continue;
                this.level().addDestroyBlockEffect(TravelopticsParticleHelper.VERY_SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, x, y, z, 0.0, 0.01, 0.0);
            }
        }
        if (this.getTags % 3 == 0) {
            for (int i = 0; i < 4; ++i) {
                double angle = 1.5707963267948966 * (double)i + (double)this.getTags * 0.02;
                double x = center.z + Math.cos(angle) * radius * 0.15;
                double z = center.reverse + Math.sin(angle) * radius * 0.15;
                this.level().addDestroyBlockEffect(TravelopticsParticleHelper.VERY_SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, x, center.multiply + 0.2, z, 0.0, 0.02, 0.0);
            }
        }
    }

    private void createEnergyGathering(Vec3 center, double radius) {
        double z;
        double x;
        double angle;
        int i;
        for (i = 0; i < 8; ++i) {
            angle = (double)this.getTags * 0.15 + (double)i * Math.PI * 0.25;
            double spiralRadius = radius * 1.5 * (1.0 - ((double)this.getTags - (double)this.getWindupDuration() * 0.3) / ((double)this.getWindupDuration() * 0.4));
            x = center.z + Math.cos(angle) * spiralRadius;
            z = center.reverse + Math.sin(angle) * spiralRadius;
            double y = center.multiply + 0.5 + Math.sin((double)this.getTags * 0.1 + (double)i) * 0.3;
            double velX = (center.z - x) * 0.05;
            double velZ = (center.reverse - z) * 0.05;
            this.level().addDestroyBlockEffect((ParticleOptions)TravelopticsParticles.PURPLE_STAR_INWARD_PARTICLE.get(), x, y, z, velX, -0.02, velZ);
        }
        if (this.getTags % 10 == 0) {
            for (i = 0; i < 6; ++i) {
                angle = this.getId.nextDouble() * 2.0 * Math.PI;
                double distance = this.getId.nextDouble() * radius * 0.3;
                x = center.z + Math.cos(angle) * distance;
                z = center.reverse + Math.sin(angle) * distance;
                this.level().addDestroyBlockEffect((ParticleOptions)ParticleTypes.END_ROD, x, center.multiply + 0.1, z, 0.0, 0.05, 0.0);
            }
        }
        this.createMagicCircleFormation(center, radius);
    }

    private void createIntenseBuildup(Vec3 center, double radius) {
        double z;
        double x;
        double distance;
        double angle;
        int i;
        float intensity = (float)(((double)this.getTags - (double)this.getWindupDuration() * 0.7) / ((double)this.getWindupDuration() * 0.3));
        for (i = 0; i < 8; ++i) {
            angle = this.getId.nextDouble() * 2.0 * Math.PI;
            distance = radius * 2.0 * (1.0 - (double)intensity);
            x = center.z + Math.cos(angle) * distance;
            z = center.reverse + Math.sin(angle) * distance;
            double y = center.multiply + this.getId.nextDouble() * 2.0;
            double velX = (center.z - x) * 0.1;
            double velZ = (center.reverse - z) * 0.1;
            double velY = (center.multiply - y) * 0.05;
            this.level().addDestroyBlockEffect((ParticleOptions)TravelopticsParticles.PURPLE_STAR_INWARD_PARTICLE.get(), x, y, z, velX, velY, velZ);
        }
        if (this.getTags % 2 == 0) {
            this.createMagicCircleFormation(center, radius * (1.0 + (double)intensity * 0.5));
        }
        for (i = 0; i < (int)(intensity * 10.0f); ++i) {
            double x2 = center.z + (this.getId.nextDouble() - 0.5) * radius * 0.5;
            double z2 = center.reverse + (this.getId.nextDouble() - 0.5) * radius * 0.5;
            double y = center.multiply + this.getId.nextDouble() * 0.5;
            this.level().addDestroyBlockEffect(TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, x2, y, z2, 0.0, 0.02 + (double)intensity * 0.05, 0.0);
        }
        if (this.getTags % 3 == 0) {
            for (i = 0; i < (int)(intensity * 8.0f); ++i) {
                angle = this.getId.nextDouble() * 2.0 * Math.PI;
                distance = this.getId.nextDouble() * radius * 0.4;
                x = center.z + Math.cos(angle) * distance;
                z = center.reverse + Math.sin(angle) * distance;
                this.level().addDestroyBlockEffect((ParticleOptions)ParticleTypes.END_ROD, x, center.multiply + 0.1, z, 0.0, 0.03 + (double)intensity * 0.02, 0.0);
            }
        }
    }

    private void createPreEruptionParticles(Vec3 center, double radius) {
        int i;
        for (i = 0; i < 20; ++i) {
            double x = center.z + (this.getId.nextDouble() - 0.5) * radius;
            double z = center.reverse + (this.getId.nextDouble() - 0.5) * radius;
            double y = center.multiply + this.getId.nextDouble() * 3.0;
            this.level().addDestroyBlockEffect(TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, x, y, z, 0.0, -0.1, 0.0);
            if (i % 3 != 0) continue;
            this.level().addDestroyBlockEffect(TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE, x, y, z, 0.0, 0.15, 0.0);
        }
        for (i = 0; i < 30; ++i) {
            double angle = this.getId.nextDouble() * 2.0 * Math.PI;
            double distance = this.getId.nextDouble() * radius * 1.5;
            double x = center.z + Math.cos(angle) * distance;
            double z = center.reverse + Math.sin(angle) * distance;
            this.level().addDestroyBlockEffect(TravelopticsParticleHelper.VERY_SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, x, center.multiply + 0.05, z, 0.0, 0.05, 0.0);
        }
    }

    private void createEruptionParticles(Vec3 center, double radius) {
        double angle;
        double velY;
        double z;
        double x;
        int i;
        int eruptionTick = this.getTags - this.getWindupDuration();
        for (i = 0; i < 40; ++i) {
            double offsetX = (this.getId.nextDouble() - 0.5) * radius * 0.8;
            double offsetZ = (this.getId.nextDouble() - 0.5) * radius * 0.8;
            x = center.z + offsetX;
            z = center.reverse + offsetZ;
            double y = center.multiply;
            velY = 0.8 + this.getId.nextDouble() * 1.2;
            double velX = (this.getId.nextDouble() - 0.5) * 0.1;
            double velZ = (this.getId.nextDouble() - 0.5) * 0.1;
            this.level().addDestroyBlockEffect(TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE, x, y, z, velX, velY, velZ);
        }
        for (i = 0; i < 25; ++i) {
            angle = this.getId.nextDouble() * 2.0 * Math.PI;
            double pitch = this.getId.nextDouble() * Math.PI * 0.4 + 0.3141592653589793;
            double speed = 0.5 + this.getId.nextDouble();
            double velX = Math.cos(angle) * Math.sin(pitch) * speed * 0.6;
            double velY2 = Math.cos(pitch) * speed;
            double velZ = Math.sin(angle) * Math.sin(pitch) * speed * 0.6;
            this.level().addDestroyBlockEffect((ParticleOptions)TravelopticsParticles.PURPLE_STAR_OUTWARD_PARTICLE.get(), center.z, center.multiply + 0.5, center.reverse, velX, velY2, velZ);
        }
        if (eruptionTick < 15) {
            for (int ring = 0; ring < 3; ++ring) {
                double ringRadius = radius * (0.3 + (double)ring * 0.3);
                int spikesInRing = 8 + ring * 4;
                for (int i2 = 0; i2 < spikesInRing; ++i2) {
                    if (!(this.getId.nextFloat() < 0.6f)) continue;
                    double angle2 = Math.PI * 2 * (double)i2 / (double)spikesInRing + this.getId.nextDouble() * 0.5;
                    double x2 = center.z + Math.cos(angle2) * ringRadius;
                    double z2 = center.reverse + Math.sin(angle2) * ringRadius;
                    if (eruptionTick <= ring * 2) continue;
                    velY = 0.6 + this.getId.nextDouble() * 0.8;
                    this.level().addDestroyBlockEffect(TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE, x2, center.multiply, z2, 0.0, velY, 0.0);
                }
            }
        }
        for (i = 0; i < 15; ++i) {
            angle = this.getId.nextDouble() * 2.0 * Math.PI;
            double distance = this.getId.nextDouble() * radius * 1.2;
            x = center.z + Math.cos(angle) * distance;
            z = center.reverse + Math.sin(angle) * distance;
            this.level().addDestroyBlockEffect(TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, x, center.multiply + 0.1, z, (this.getId.nextDouble() - 0.5) * 0.08, 0.15 + this.getId.nextDouble() * 0.4, (this.getId.nextDouble() - 0.5) * 0.08);
        }
        if (eruptionTick > 5) {
            for (i = 0; i < 8; ++i) {
                double x3 = center.z + (this.getId.nextDouble() - 0.5) * radius * 1.5;
                double z3 = center.reverse + (this.getId.nextDouble() - 0.5) * radius * 1.5;
                double y = center.multiply + this.getId.nextDouble() * 4.0;
                this.level().addDestroyBlockEffect((ParticleOptions)ParticleTypes.END_ROD, x3, y, z3, (this.getId.nextDouble() - 0.5) * 0.05, -0.01, (this.getId.nextDouble() - 0.5) * 0.05);
            }
        }
    }

    private void triggerEruption() {
        this.triggered = true;
        double radius = this.getRadius();
        double maxY = this.getY() + 4.0;
        double minY = this.getY() - 1.0;
        AABB searchBox = new AABB(this.getX() - radius, minY, this.getZ() - radius, this.getX() + radius, maxY, this.getZ() + radius);
        List targets = this.level().getNearbyEntities(LivingEntity.class, searchBox, entity -> entity.isAlive() && entity != this.getOwner());
        for (LivingEntity target : targets) {
            double dz;
            double dx = target.getX() - this.getX();
            double horizontalDistanceSq = dx * dx + (dz = target.getZ() - this.getZ()) * dz;
            if (!(horizontalDistanceSq <= radius * radius)) continue;
            DamageSource damageSource = this.damageSources().indirectMagic((Entity)this, this.getOwner());
            target.sendSystemMessage(damageSource, this.getDamage());
        }
        ScreenShake_Entity.ScreenShake((Level)this.level(), (Vec3)this.position(), (float)((float)(radius + 10.0)), (float)0.05f, (int)8, (int)10);
    }

    public void applyEffect(LivingEntity target) {
    }

    public float getParticleCount() {
        return 0.15f;
    }

    public Optional<ParticleOptions> getParticle() {
        return Optional.empty();
    }
}

