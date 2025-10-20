/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  io.redspace.ironsspellbooks.damage.ISSDamageTypes
 *  io.redspace.ironsspellbooks.entity.spells.AoeEntity
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.entity.projectiles;

import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import java.util.Optional;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class TectonicRiftEntity
extends AoeEntity {
    private DamageSource damageSource;

    public TectonicRiftEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public TectonicRiftEntity(Level level) {
        this((EntityType<? extends Projectile>)((EntityType)TravelopticsEntities.TECTONIC_RIFT_FIELD.get()), level);
    }

    public void applyEffect(LivingEntity target) {
        if (this.damageSource == null) {
            this.damageSource = new DamageSource(DamageSources.getHolderFromResource((Entity)target, (ResourceKey)ISSDamageTypes.FIRE_FIELD), (Entity)this, this.getOwner());
        }
        DamageSources.ignoreNextKnockback((LivingEntity)target);
        target.sendSystemMessage(this.damageSource, this.getDamage());
        target.getRandomZ(7);
    }

    public EntityDimensions setLastDeathLocation(Pose pPose) {
        return EntityDimensions.scalable((float)(this.getRadius() * 1.5f), (float)(this.getRadius() * 1.5f));
    }

    public void lerpMotion() {
        super.lerpMotion();
        if (this.getTags == 1 && !this.level().isClientSide) {
            ScreenShake_Entity.ScreenShake((Level)this.level(), (Vec3)this.position(), (float)(this.getRadius() + 12.0f), (float)0.038f, (int)this.duration, (int)(this.duration + 10));
            this.level().gameEvent(null, this.blockPosition(), (SoundEvent)TravelopticsSounds.TECTONIC_RIFT_ERUPTION.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
        }
        if (!this.level().isClientSide && this.getTags >= 200) {
            this.discard();
        }
        if (this.level().isClientSide) {
            double radius = this.getRadius();
            double innerRadius = 5.0;
            for (int i = 0; i < 15; ++i) {
                double angle = this.level().random.nextDouble() * 2.0 * Math.PI;
                double distance = innerRadius + this.level().random.nextDouble() * (radius - innerRadius);
                double xOffset = Math.cos(angle) * distance;
                double zOffset = Math.sin(angle) * distance;
                double x = this.getX() + xOffset;
                double y = this.getY() + this.level().random.nextDouble();
                double z = this.getZ() + zOffset;
                double motionY = 0.3 + this.level().random.nextDouble() * 0.4;
                SimpleParticleType particle = switch (this.level().random.nextInt(3)) {
                    case 0 -> (SimpleParticleType)ParticleTypes.LAVA;
                    case 1 -> (SimpleParticleType)ParticleTypes.LAVA;
                    case 2 -> (SimpleParticleType)ParticleTypes.LAVA;
                    default -> (SimpleParticleType)ParticleTypes.LAVA;
                };
                this.level().addDestroyBlockEffect((ParticleOptions)particle, x, y, z, 0.0, motionY, 0.0);
            }
        }
    }

    public float getParticleCount() {
        return 0.15f;
    }

    public Optional<ParticleOptions> getParticle() {
        return Optional.empty();
    }
}

