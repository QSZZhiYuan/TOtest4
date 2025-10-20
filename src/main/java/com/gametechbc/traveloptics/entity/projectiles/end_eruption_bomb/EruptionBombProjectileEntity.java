/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.phys.shapes.VoxelShape
 *  net.minecraftforge.network.NetworkHooks
 *  software.bernie.geckolib.animatable.GeoEntity
 *  software.bernie.geckolib.core.animatable.GeoAnimatable
 *  software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
 *  software.bernie.geckolib.core.animation.AnimatableManager$ControllerRegistrar
 *  software.bernie.geckolib.core.animation.AnimationController
 *  software.bernie.geckolib.core.animation.AnimationState
 *  software.bernie.geckolib.core.animation.RawAnimation
 *  software.bernie.geckolib.core.object.PlayState
 *  software.bernie.geckolib.util.GeckoLibUtil
 */
package com.gametechbc.traveloptics.entity.projectiles.end_eruption_bomb;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.misc.EndEruptionEntity;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsParticles;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class EruptionBombProjectileEntity
extends AbstractMagicProjectile
implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
    private static final EntityDataAccessor<Boolean> IS_FUSING = SynchedEntityData.assignValue(EruptionBombProjectileEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private int animationTimer = 20;
    private boolean hasBounced = false;
    private int bounceCooldown = 0;
    private static final RawAnimation TRAVEL_ANIM = RawAnimation.begin().thenLoop("eruption_bomb_travel");
    private static final RawAnimation FUSE_ANIM = RawAnimation.begin().thenPlay("eruption_bomb_fuse");
    private final AnimationController<EruptionBombProjectileEntity> controller = new AnimationController((GeoAnimatable)this, "bomb_controller", 0, this::animationPredicate);

    public EruptionBombProjectileEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public EruptionBombProjectileEntity(Level pLevel, LivingEntity pShooter) {
        this((EntityType<? extends Projectile>)((EntityType)TravelopticsEntities.END_ERUPTION_BOMB.get()), pLevel);
        this.addAdditionalSaveData((Entity)pShooter);
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.makeBoundingBox.assignValue(IS_FUSING, (Object)true);
    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.accept("IsFusing", this.isFusing());
        tag.accept("HasBounced", this.hasBounced);
        tag.accept("BounceCooldown", this.bounceCooldown);
        tag.accept("AnimTimer", this.animationTimer);
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("IsFusing")) {
            this.setFusing(tag.getBoolean("IsFusing"));
        }
        if (tag.contains("HasBounced")) {
            this.hasBounced = tag.getBoolean("HasBounced");
        }
        if (tag.contains("BounceCooldown")) {
            this.bounceCooldown = tag.copy("BounceCooldown");
        }
        if (tag.contains("AnimTimer")) {
            this.animationTimer = tag.copy("AnimTimer");
        }
    }

    public boolean isFusing() {
        return (Boolean)this.makeBoundingBox.packDirty(IS_FUSING);
    }

    private void setFusing(boolean fusing) {
        this.makeBoundingBox.packDirty(IS_FUSING, (Object)fusing);
    }

    public void lerpMotion() {
        super.lerpMotion();
        if (!this.level().isClientSide && this.animationTimer > 0) {
            --this.animationTimer;
            if (this.animationTimer <= 0) {
                this.setFusing(false);
            }
        }
        if (this.bounceCooldown > 0) {
            --this.bounceCooldown;
        }
    }

    protected void dowseFire(BlockHitResult result) {
        super.dowseFire(result);
        if (!this.level().isClientSide) {
            if (result.getDirection() == Direction.UP) {
                this.spawnGroundedEruption(this.getX(), this.getZ(), this.getY() - 10.0, this.getY() + 5.0);
                this.discard();
            } else if (this.bounceCooldown <= 0 && !this.hasBounced) {
                Vec3 currentMovement = this.getDeltaMovement();
                Vec3 normal = Vec3.x((Vec3i)result.getDirection().getNormal());
                Vec3 reflection = currentMovement.multiply(normal.x(2.0 * currentMovement.y(normal)));
                Vec3 newMovement = new Vec3(reflection.z * 0.3, Math.min(reflection.multiply * 0.5, -0.1), reflection.reverse * 0.3);
                this.setDeltaMovement(newMovement);
                this.getY(false);
                this.hasBounced = true;
                this.bounceCooldown = 10;
                this.updateTutorialInventoryAction(SoundEvents.SLIME_BLOCK_HIT, 0.6f, 1.2f);
            }
        }
    }

    private void spawnGroundedEruption(double x, double z, double minY, double maxY) {
        BlockPos pos = new BlockPos((int)x, (int)maxY, (int)z);
        boolean flag = false;
        double d0 = 0.0;
        do {
            BlockState blockState1;
            VoxelShape voxelShape;
            BlockPos pos1 = pos.below();
            BlockState blockState = this.level().getBlockState(pos1);
            if (!blockState.isFaceSturdy((BlockGetter)this.level(), pos1, Direction.UP)) continue;
            if (!this.level().isEmptyBlock(pos) && !(voxelShape = (blockState1 = this.level().getBlockState(pos)).getCollisionShape((BlockGetter)this.level(), pos)).calculateFace()) {
                d0 = voxelShape.optimize(Direction.Axis.Y);
            }
            flag = true;
            break;
        } while ((pos = pos.below()).getY() >= Mth.outFromOrigin((double)minY) - 1);
        if (flag) {
            double finalX = x;
            double finalY = (double)pos.getY() + d0;
            double finalZ = z;
            if (!this.level().isClientSide) {
                MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)ParticleTypes.EXPLOSION_EMITTER, (double)finalX, (double)(finalY + 1.0), (double)finalZ, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
                MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)((ParticleOptions)TravelopticsParticles.PURPLE_STAR_OUTWARD_PARTICLE.get()), (double)finalX, (double)(finalY + 0.75), (double)finalZ, (int)25, (double)1.5, (double)0.75, (double)1.5, (double)0.05, (boolean)true);
            }
            this.updateTutorialInventoryAction(SoundEvents.GENERIC_EXPLODE, 1.0f, 1.0f);
            EndEruptionEntity eruption = new EndEruptionEntity(this.level());
            eruption.getRandomX(finalX, finalY, finalZ);
            eruption.addAdditionalSaveData(this.getOwner());
            eruption.setWindupDuration(30);
            eruption.setRadius(4.0f);
            eruption.setDamage(this.getDamage());
            this.level().addFreshEntity((Entity)eruption);
        }
    }

    protected void setDangerous(EntityHitResult hitResult) {
        if (!this.level().isClientSide && this.bounceCooldown <= 0 && !this.hasBounced) {
            Vec3 currentMovement = this.getDeltaMovement();
            Entity hitEntity = hitResult.getEntity();
            Vec3 entityPos = hitEntity.position();
            Vec3 projectilePos = this.position();
            Vec3 bounceDirection = projectilePos.multiply(entityPos).multiply();
            Vec3 newMovement = new Vec3(bounceDirection.z * Math.abs(currentMovement.z) * 0.4 + (Math.random() - 0.5) * 0.2, Math.min(currentMovement.multiply * 0.3, -0.1), bounceDirection.reverse * Math.abs(currentMovement.reverse) * 0.4 + (Math.random() - 0.5) * 0.2);
            this.setDeltaMovement(newMovement);
            this.getY(false);
            this.hasBounced = true;
            this.bounceCooldown = 10;
            this.updateTutorialInventoryAction(SoundEvents.SLIME_BLOCK_STEP, 0.6f, 1.5f);
        }
    }

    public void trailParticles() {
        int i;
        double x = this.getX();
        double y = this.getY() + 0.1;
        double z = this.getZ();
        for (i = 0; i < 3; ++i) {
            double offsetX = (this.getId.nextDouble() - 0.5) * 0.3;
            double offsetY = (this.getId.nextDouble() - 0.5) * 0.3;
            double offsetZ = (this.getId.nextDouble() - 0.5) * 0.3;
            double velX = (this.getId.nextDouble() - 0.5) * 0.02;
            double velY = (this.getId.nextDouble() - 0.5) * 0.02;
            double velZ = (this.getId.nextDouble() - 0.5) * 0.02;
            if (this.isFusing()) {
                if (this.getId.nextFloat() < 0.25f) {
                    this.level().addDestroyBlockEffect((ParticleOptions)TravelopticsParticles.ABYSS_SPIKE_PARTICLE.get(), x + offsetX, y + offsetY, z + offsetZ, velX, velY, velZ);
                    continue;
                }
                this.level().addDestroyBlockEffect((ParticleOptions)ParticleTypes.SMOKE, x + offsetX, y + offsetY, z + offsetZ, velX * 0.5, velY * 0.5, velZ * 0.5);
                continue;
            }
            this.level().addDestroyBlockEffect((ParticleOptions)ParticleTypes.SMOKE, x + offsetX, y + offsetY, z + offsetZ, velX * 0.5, velY * 0.5, velZ * 0.5);
        }
        if (this.isUnderWater()) {
            for (i = 0; i < 2; ++i) {
                double bubbleOffsetX = (this.getId.nextDouble() - 0.5) * 0.4;
                double bubbleOffsetY = (this.getId.nextDouble() - 0.5) * 0.2;
                double bubbleOffsetZ = (this.getId.nextDouble() - 0.5) * 0.4;
                double bubbleVelX = (this.getId.nextDouble() - 0.5) * 0.03;
                double bubbleVelY = this.getId.nextDouble() * 0.05;
                double bubbleVelZ = (this.getId.nextDouble() - 0.5) * 0.03;
                this.level().addDestroyBlockEffect((ParticleOptions)ParticleTypes.BUBBLE, x + bubbleOffsetX, y + bubbleOffsetY, z + bubbleOffsetZ, bubbleVelX, bubbleVelY, bubbleVelZ);
            }
        }
    }

    public void impactParticles(double v, double v1, double v2) {
    }

    public float getSpeed() {
        return 0.0f;
    }

    public Optional<SoundEvent> getImpactSound() {
        return Optional.empty();
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{this.controller});
    }

    private PlayState animationPredicate(AnimationState<EruptionBombProjectileEntity> state) {
        if (this.isFusing()) {
            state.getController().setAnimation(FUSE_ANIM);
        } else {
            state.getController().setAnimation(TRAVEL_ANIM);
        }
        return PlayState.CONTINUE;
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}

