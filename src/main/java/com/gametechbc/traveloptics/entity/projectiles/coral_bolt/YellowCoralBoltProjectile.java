/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile
 *  io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions
 *  javax.annotation.Nullable
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.entity.IEntityAdditionalSpawnData
 *  net.minecraftforge.network.NetworkHooks
 *  org.joml.Vector3f
 */
package com.gametechbc.traveloptics.entity.projectiles.coral_bolt;

import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;
import org.joml.Vector3f;

public class YellowCoralBoltProjectile
extends AbstractMagicProjectile
implements IEntityAdditionalSpawnData {
    private int age;
    private static final int EXPIRE_TIME = 60;
    @Nullable
    Entity cachedHomingTarget;
    @Nullable
    UUID homingTargetUUID;

    public YellowCoralBoltProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.getY(true);
    }

    public YellowCoralBoltProjectile(Level pLevel, LivingEntity pShooter) {
        this((EntityType<? extends Projectile>)((EntityType)TravelopticsEntities.YELLOW_CORAL_BOLT_PROJECTILE.get()), pLevel);
        this.addAdditionalSaveData((Entity)pShooter);
    }

    public void shoot(Vec3 rotation, float inaccuracy) {
        double speed = rotation.length();
        Vec3 offset = Utils.getRandomVec3((double)1.0).multiply().x((double)inaccuracy);
        Vec3 motion = rotation.multiply().reverse(offset).multiply().x(speed);
        super.shoot(motion);
    }

    @Nullable
    public Entity getHomingTarget() {
        if (this.cachedHomingTarget != null && !this.cachedHomingTarget.isRemoved()) {
            return this.cachedHomingTarget;
        }
        if (this.homingTargetUUID != null && this.level() instanceof ServerLevel) {
            this.cachedHomingTarget = ((ServerLevel)this.level()).getRandomSequence(this.homingTargetUUID);
            return this.cachedHomingTarget;
        }
        return null;
    }

    public void setHomingTarget(LivingEntity entity) {
        this.homingTargetUUID = entity.getUUID();
        this.cachedHomingTarget = entity;
    }

    public void lerpMotion() {
        super.lerpMotion();
        if (++this.age > 60) {
            this.discard();
            return;
        }
        Entity homingTarget = this.getHomingTarget();
        if (homingTarget != null && !this.doHomingTowards(homingTarget)) {
            this.homingTargetUUID = null;
            this.cachedHomingTarget = null;
        }
    }

    private boolean doHomingTowards(Entity entity) {
        if (entity.isRemoved()) {
            return false;
        }
        Vec3 motion = this.getDeltaMovement();
        double speed = this.getDeltaMovement().length();
        Vec3 delta = entity.getBoundingBox().getCenter().multiply(this.position()).reverse(entity.getDeltaMovement());
        float f = 0.08f;
        Vec3 newMotion = new Vec3(Mth.roundToward((double)f, (double)motion.z, (double)delta.z), Mth.roundToward((double)f, (double)motion.multiply, (double)delta.multiply), Mth.roundToward((double)f, (double)motion.reverse, (double)delta.reverse)).multiply().x(speed);
        this.setDeltaMovement(newMotion);
        return this.getTags <= 10 || !(newMotion.y(delta) < 0.0);
    }

    protected void setDangerous(EntityHitResult pResult) {
        if (!this.level().isClientSide) {
            Entity target = pResult.getEntity();
            Entity owner = this.getOwner();
            double radius = 2.0;
            DamageSources.applyDamage((Entity)target, (float)this.damage, (DamageSource)((AbstractSpell)TravelopticsSpells.CORAL_BARRAGE_SPELL.get()).getDamageSource((Entity)this, owner));
            if (target.getUUID().equals(this.homingTargetUUID)) {
                target.onClientRemoval = 0;
            }
            AABB aoeRegion = new AABB(this.getX() - radius, this.getY() - radius, this.getZ() - radius, this.getX() + radius, this.getY() + radius, this.getZ() + radius);
            List nearbyEntities = this.level().getChunk((Entity)this, aoeRegion, entity -> entity instanceof LivingEntity);
            for (Entity entity2 : nearbyEntities) {
                LivingEntity livingEntity;
                if (entity2 == target || entity2 == owner || !(entity2 instanceof LivingEntity) || this.isAlly((LivingEntity)owner, livingEntity = (LivingEntity)entity2) || this.isTamed(livingEntity)) continue;
                DamageSources.applyDamage((Entity)entity2, (float)this.damage, (DamageSource)((AbstractSpell)TravelopticsSpells.CORAL_BARRAGE_SPELL.get()).getDamageSource((Entity)this, owner));
            }
            MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)new BlastwaveParticleOptions(new Vector3f(0.9294f, 0.9255f, 0.298f), 2.0f), (double)this.getX(), (double)(this.getY() + (double)0.165f), (double)this.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
        }
    }

    protected void dowseFire(BlockHitResult pResult) {
        super.dowseFire(pResult);
        if (!this.level().isClientSide) {
            this.discard();
        }
    }

    protected void setDangerous(HitResult pResult) {
        super.setDangerous(pResult);
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

    public void trailParticles() {
        Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() - vec3.z;
        double d1 = this.getY() - vec3.multiply;
        double d2 = this.getZ() - vec3.reverse;
        int count = Mth.outFromOrigin((int)((int)(vec3.lengthSqr() * 4.0)), (int)1, (int)5);
        for (int i = 0; i < count; ++i) {
            Vec3 random = Utils.getRandomVec3((double)0.1);
            float f = (float)i / (float)count;
            double x = Mth.roundToward((double)f, (double)d0, (double)this.getX());
            double y = Mth.roundToward((double)f, (double)d1, (double)this.getY());
            double z = Mth.roundToward((double)f, (double)d2, (double)this.getZ());
            this.level().addDestroyBlockEffect(TravelopticsParticleHelper.YELLOW_CORAL_BUBBLE, x - random.z, y + 0.5 - random.multiply, z - random.reverse, random.z * 0.5, random.multiply * 0.5, random.reverse * 0.5);
        }
    }

    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)TravelopticsParticleHelper.YELLOW_CORAL_SPARKS, (double)x, (double)y, (double)z, (int)15, (double)0.1, (double)0.1, (double)0.1, (double)0.25, (boolean)true);
    }

    public float getSpeed() {
        return 1.85f;
    }

    public Optional<SoundEvent> getImpactSound() {
        return Optional.empty();
    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (this.homingTargetUUID != null) {
            tag.accept("homingTarget", this.homingTargetUUID);
        }
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.readNamedTagName("homingTarget", 11)) {
            this.homingTargetUUID = tag.accept("homingTarget");
        }
    }

    public void writeSpawnData(FriendlyByteBuf buffer) {
        Entity owner = this.getOwner();
        buffer.writeInt(owner == null ? 0 : owner.getId());
        Entity homingTarget = this.getHomingTarget();
        buffer.writeInt(homingTarget == null ? 0 : homingTarget.getId());
    }

    public void readSpawnData(FriendlyByteBuf additionalData) {
        Entity homingTarget;
        Entity owner = this.level().addDestroyBlockEffect(additionalData.readInt());
        if (owner != null) {
            this.addAdditionalSaveData(owner);
        }
        if ((homingTarget = this.level().addDestroyBlockEffect(additionalData.readInt())) != null) {
            this.cachedHomingTarget = homingTarget;
            this.homingTargetUUID = homingTarget.getUUID();
        }
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }
}

