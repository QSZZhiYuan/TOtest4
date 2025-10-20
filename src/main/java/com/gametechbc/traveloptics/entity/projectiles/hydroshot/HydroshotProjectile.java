/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.Mth
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.entity.projectiles.hydroshot;

import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import java.util.Optional;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class HydroshotProjectile
extends AbstractMagicProjectile {
    private int slownessAmplifier = 0;

    public HydroshotProjectile(EntityType<? extends HydroshotProjectile> entityType, Level level) {
        super(entityType, level);
        this.getY(true);
    }

    public HydroshotProjectile(Level levelIn, LivingEntity shooter) {
        this((EntityType<? extends HydroshotProjectile>)((EntityType)TravelopticsEntities.HYDROSHOT_PROJECTILE.get()), levelIn);
        this.addAdditionalSaveData((Entity)shooter);
    }

    public int getSlownessAmplifier() {
        return this.slownessAmplifier;
    }

    public void setSlownessAmplifier(int slownessAmplifier) {
        this.slownessAmplifier = slownessAmplifier;
    }

    public float getSpeed() {
        return 1.75f;
    }

    public Optional<SoundEvent> getImpactSound() {
        return Optional.of((SoundEvent)TravelopticsSounds.HYDROSHOT_IMPACT.get());
    }

    protected void doImpactSound(SoundEvent sound) {
        this.level().getChunk(null, this.getX(), this.getY(), this.getZ(), sound, SoundSource.NEUTRAL, 2.0f, 1.2f + Utils.random.nextFloat() * 0.2f);
    }

    protected void dowseFire(BlockHitResult blockHitResult) {
        super.dowseFire(blockHitResult);
        this.discard();
    }

    protected void setDangerous(EntityHitResult entityHitResult) {
        super.setDangerous(entityHitResult);
        Entity target = entityHitResult.getEntity();
        DamageSources.applyDamage((Entity)target, (float)this.getDamage(), (DamageSource)((AbstractSpell)TravelopticsSpells.HYDROSHOT_SPELL.get()).getDamageSource((Entity)this, this.getOwner()));
        if (target instanceof LivingEntity) {
            LivingEntity livingTarget = (LivingEntity)target;
            livingTarget.getStandingEyeHeight(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, this.getSlownessAmplifier()));
            livingTarget.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.WET.get(), 40, 0));
        }
        this.discard();
    }

    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)TravelopticsParticleHelper.WATER_SPARKS, (double)x, (double)y, (double)z, (int)15, (double)0.1, (double)0.1, (double)0.1, (double)0.25, (boolean)true);
    }

    public void trailParticles() {
        for (int i = 0; i < 1; ++i) {
            float yHeading = -((float)(Mth.roundToward((double)this.getDeltaMovement().reverse, (double)this.getDeltaMovement().z) * 57.2957763671875) + 90.0f);
            float radius = 0.25f;
            int steps = 6;
            for (int j = 0; j < steps; ++j) {
                float offset = 1.0f / (float)steps * (float)i;
                double radians = ((float)this.getTags + offset) / 7.5f * 360.0f * ((float)Math.PI / 180);
                Vec3 swirl = new Vec3(Math.cos(radians) * (double)radius, Math.sin(radians) * (double)radius, 0.0).y(yHeading * ((float)Math.PI / 180));
                double x = this.getX() + swirl.z;
                double y = this.getY() + swirl.multiply + (double)(this.getBbHeight() / 2.0f);
                double z = this.getZ() + swirl.reverse;
                Vec3 jitter = Utils.getRandomVec3((double)0.05f);
                this.level().calculateBlockTint(TravelopticsParticleHelper.WATER_BUBBLE, true, x, y, z, jitter.z, jitter.multiply, jitter.reverse);
            }
        }
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.accept("SlownessAmplifier", this.slownessAmplifier);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.readNamedTagName("SlownessAmplifier", 3)) {
            this.slownessAmplifier = compound.copy("SlownessAmplifier");
        }
    }
}

