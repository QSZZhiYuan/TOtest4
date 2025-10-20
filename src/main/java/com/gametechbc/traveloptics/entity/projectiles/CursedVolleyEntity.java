/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.projectile.Phantom_Arrow_Entity
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile
 *  io.redspace.ironsspellbooks.registries.SoundRegistry
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.entity.projectiles;

import com.github.L_Ender.cataclysm.entity.projectile.Phantom_Arrow_Entity;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import java.util.Optional;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CursedVolleyEntity
extends AbstractMagicProjectile {
    int rows;
    int arrowsPerRow;
    int delay = 5;

    public CursedVolleyEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.getY(true);
        this.getType = true;
    }

    public void lerpMotion() {
        super.lerpMotion();
        if (!this.level().isClientSide) {
            if (this.getTags % this.delay == 0) {
                int arrows = this.arrowsPerRow;
                float speed = 0.85f;
                Vec3 motion = Vec3.x((float)(this.getXRot() - (float)this.getTags / 5.0f * 7.0f), (float)this.getYRot()).multiply().x((double)speed);
                Vec3 orth = new Vec3((double)(-Mth.randomBetween((float)(-this.getYRot() * ((float)Math.PI / 180) - (float)Math.PI))), 0.0, (double)Mth.outFromOrigin((float)(-this.getYRot() * ((float)Math.PI / 180) - (float)Math.PI)));
                for (int i = 0; i < arrows; ++i) {
                    float distance = ((float)i - (float)arrows * 0.5f) * 0.7f;
                    Phantom_Arrow_Entity phantomArrow = new Phantom_Arrow_Entity(this.level(), (LivingEntity)this.getOwner());
                    Vec3 spawn = this.position().reverse(orth.x((double)distance));
                    phantomArrow.setLevel(spawn);
                    phantomArrow.shoot(motion.reverse(Utils.getRandomVec3((double)0.04f)).x(), motion.reverse(Utils.getRandomVec3((double)0.04f)).y(), motion.reverse(Utils.getRandomVec3((double)0.04f)).z(), 1.5f, 0.0f);
                    phantomArrow.addAdditionalSaveData(this.getOwner());
                    this.level().addFreshEntity((Entity)phantomArrow);
                    MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)ParticleTypes.FIREWORK, (double)spawn.z, (double)spawn.multiply, (double)spawn.reverse, (int)2, (double)0.1, (double)0.1, (double)0.1, (double)0.05, (boolean)false);
                }
                this.level().getChunk(null, this.position().z, this.position().multiply, this.position().reverse, SoundEvents.FIREWORK_ROCKET_LAUNCH, SoundSource.NEUTRAL, 3.0f, 1.1f + Utils.random.nextFloat() * 0.3f);
                this.level().getChunk(null, this.position().z, this.position().multiply, this.position().reverse, (SoundEvent)SoundRegistry.BOW_SHOOT.get(), SoundSource.NEUTRAL, 2.0f, (float)Utils.random.triangle(16, 20) * 0.1f);
            } else if (this.getTags > this.rows * this.delay) {
                this.discard();
            }
        }
    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.accept("rows", this.rows);
        tag.accept("arrowsPerRow", this.arrowsPerRow);
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.rows = tag.copy("rows");
        this.arrowsPerRow = tag.copy("arrowsPerRow");
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setArrowsPerRow(int arrowsPerRow) {
        this.arrowsPerRow = arrowsPerRow;
    }

    public void trailParticles() {
    }

    public void impactParticles(double x, double y, double z) {
    }

    public float getSpeed() {
        return 0.0f;
    }

    public Optional<SoundEvent> getImpactSound() {
        return Optional.empty();
    }
}

