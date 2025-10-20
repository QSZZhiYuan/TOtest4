/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.BlockParticleOption
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.RenderShape
 *  net.minecraft.world.level.block.state.BlockState
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
package com.gametechbc.traveloptics.entity.projectiles.dimensional_spike;

import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.util.TravelopticsTags;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
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

public class DimensionalSpikeEntity
extends Entity
implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
    private static final EntityDataAccessor<Boolean> ACTIVATE = SynchedEntityData.assignValue(DimensionalSpikeEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.assignValue(DimensionalSpikeEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> WARMUP_TICKS = SynchedEntityData.assignValue(DimensionalSpikeEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private static final EntityDataAccessor<Boolean> IS_RISING = SynchedEntityData.assignValue(DimensionalSpikeEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_GOING_DOWN = SynchedEntityData.assignValue(DimensionalSpikeEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_END_STONE = SynchedEntityData.assignValue(DimensionalSpikeEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private int warmupDelayTicks;
    private LivingEntity caster;
    private UUID casterUuid;
    private boolean hasDamaged = false;
    private int riseTickCount = 0;
    private boolean hasDetectedBlock = false;
    private static final RawAnimation BLANK_ANIM = RawAnimation.begin().thenLoop("blank");
    private static final RawAnimation SPIKE_RISE_ANIM = RawAnimation.begin().thenPlay("spike_rise");
    private final AnimationController<DimensionalSpikeEntity> controller = new AnimationController((GeoAnimatable)this, "spike_controller", 0, this::animationPredicate);

    public DimensionalSpikeEntity(EntityType<? extends DimensionalSpikeEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public DimensionalSpikeEntity(Level worldIn, double x, double y, double z, float yRot, int warmupDelay, float damage, LivingEntity casterIn) {
        this((EntityType<? extends DimensionalSpikeEntity>)((EntityType)TravelopticsEntities.DIMENSIONAL_SPIKE.get()), worldIn);
        this.warmupDelayTicks = warmupDelay;
        this.setCaster(casterIn);
        this.setDamage(damage);
        this.setWarmupTicks(warmupDelay);
        this.setYRot(yRot);
        this.setPos(x, y, z);
        this.detectBlockBeneath();
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    protected void defineSynchedData() {
        this.makeBoundingBox.assignValue(ACTIVATE, (Object)false);
        this.makeBoundingBox.assignValue(DAMAGE, (Object)Float.valueOf(0.0f));
        this.makeBoundingBox.assignValue(WARMUP_TICKS, (Object)0);
        this.makeBoundingBox.assignValue(IS_RISING, (Object)false);
        this.makeBoundingBox.assignValue(IS_GOING_DOWN, (Object)false);
        this.makeBoundingBox.assignValue(IS_END_STONE, (Object)false);
    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.accept("Warmup", this.warmupDelayTicks);
        if (this.casterUuid != null) {
            tag.accept("Owner", this.casterUuid);
        }
        tag.accept("damage", this.getDamage());
        tag.accept("isEndStone", this.isEndStone());
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        this.warmupDelayTicks = tag.copy("Warmup");
        if (tag.readNamedTagName("Owner")) {
            this.casterUuid = tag.accept("Owner");
        }
        this.setDamage(tag.getFloat("damage"));
        if (tag.contains("isEndStone")) {
            this.setEndStone(tag.getBoolean("isEndStone"));
        }
    }

    public float getDamage() {
        return ((Float)this.makeBoundingBox.packDirty(DAMAGE)).floatValue();
    }

    public void setDamage(float damage) {
        this.makeBoundingBox.packDirty(DAMAGE, (Object)Float.valueOf(damage));
    }

    public int getWarmupTicks() {
        return (Integer)this.makeBoundingBox.packDirty(WARMUP_TICKS);
    }

    public void setWarmupTicks(int ticks) {
        this.makeBoundingBox.packDirty(WARMUP_TICKS, (Object)ticks);
    }

    public void setCaster(@Nullable LivingEntity caster) {
        this.caster = caster;
        this.casterUuid = caster == null ? null : caster.getUUID();
    }

    @Nullable
    public LivingEntity getCaster() {
        Entity entity;
        if (this.caster == null && this.casterUuid != null && this.level() instanceof ServerLevel && (entity = ((ServerLevel)this.level()).getRandomSequence(this.casterUuid)) instanceof LivingEntity) {
            this.caster = (LivingEntity)entity;
        }
        return this.caster;
    }

    public boolean isActivate() {
        return (Boolean)this.makeBoundingBox.packDirty(ACTIVATE);
    }

    public void setActivate(boolean activate) {
        this.makeBoundingBox.packDirty(ACTIVATE, (Object)activate);
    }

    public boolean isRising() {
        return (Boolean)this.makeBoundingBox.packDirty(IS_RISING);
    }

    public void setRising(boolean rising) {
        this.makeBoundingBox.packDirty(IS_RISING, (Object)rising);
    }

    public boolean isGoingDown() {
        return (Boolean)this.makeBoundingBox.packDirty(IS_GOING_DOWN);
    }

    public void setGoingDown(boolean goingDown) {
        this.makeBoundingBox.packDirty(IS_GOING_DOWN, (Object)goingDown);
    }

    public boolean isEndStone() {
        return (Boolean)this.makeBoundingBox.packDirty(IS_END_STONE);
    }

    public void setEndStone(boolean endStone) {
        this.makeBoundingBox.packDirty(IS_END_STONE, (Object)endStone);
    }

    private void detectBlockBeneath() {
        BlockPos posBelow = this.blockPosition().below();
        BlockState blockBelow = this.level().getBlockState(posBelow);
        boolean isEndStoneBlock = blockBelow.isFaceSturdy(TravelopticsTags.IS_ENDSTONE_CATEGORY);
        this.setEndStone(isEndStoneBlock);
        this.hasDetectedBlock = true;
    }

    public void lerpMotion() {
        super.lerpMotion();
        if (this.getTags == 1 && !this.hasDetectedBlock && !this.level().isClientSide) {
            this.detectBlockBeneath();
        }
        if (this.level().isClientSide) {
            if (this.isRising()) {
                this.spawnBlockDiggingParticles();
            }
            if (this.isGoingDown()) {
                this.spawnReversePortalParticles();
            }
        } else {
            int currentWarmup = this.getWarmupTicks();
            if (currentWarmup > 0) {
                this.setWarmupTicks(currentWarmup - 1);
            } else if (!this.isActivate()) {
                this.setActivate(true);
                this.setRising(true);
            }
            if (this.isActivate()) {
                ++this.riseTickCount;
                if (this.riseTickCount == 7) {
                    this.setRising(false);
                    if (!this.hasDamaged) {
                        this.dealDamageOnce();
                        this.hasDamaged = true;
                    }
                    this.setGoingDown(true);
                }
                if (this.riseTickCount >= 20) {
                    this.discard();
                }
            }
        }
    }

    private void dealDamageOnce() {
        for (LivingEntity livingentity : this.level().getNearbyEntities(LivingEntity.class, this.getBoundingBox().getYsize(0.2, 0.0, 0.2))) {
            this.damage(livingentity);
        }
    }

    private void damage(LivingEntity hitEntity) {
        LivingEntity livingentity = this.getCaster();
        if (hitEntity.isAlive() && !hitEntity.isInvulnerable() && hitEntity != livingentity) {
            if (livingentity == null) {
                hitEntity.sendSystemMessage(this.damageSources().magic(), this.getDamage());
            } else if (!livingentity.isAlliedTo((Entity)hitEntity) && !hitEntity.isAlliedTo((Entity)livingentity)) {
                hitEntity.sendSystemMessage(this.damageSources().indirectMagic((Entity)this, (Entity)livingentity), this.getDamage());
            }
        }
    }

    private void spawnBlockDiggingParticles() {
        for (int i = 0; i < 4; ++i) {
            BlockState block = this.level().getBlockState(this.blockPosition().below());
            double d0 = this.getX() + (this.getId.nextDouble() * 2.0 - 1.0) * (double)this.getBbWidth() * 0.5;
            double d1 = this.getY() + 0.03;
            double d2 = this.getZ() + (this.getId.nextDouble() * 2.0 - 1.0) * (double)this.getBbWidth() * 0.5;
            double d3 = this.getId.nextGaussian() * 0.07;
            double d4 = this.getId.nextGaussian() * 0.07;
            double d5 = this.getId.nextGaussian() * 0.07;
            if (block.getBlockSupportShape() == RenderShape.INVISIBLE) continue;
            this.level().addDestroyBlockEffect((ParticleOptions)new BlockParticleOption(ParticleTypes.BLOCK, block), d0, d1, d2, d3, d4, d5);
        }
    }

    private void spawnReversePortalParticles() {
        for (int i = 0; i < 3; ++i) {
            double d0 = this.getX() + (this.getId.nextDouble() * 2.0 - 1.0) * (double)this.getBbWidth() * 0.5;
            double d1 = this.getY() + 0.05 + this.getId.nextDouble();
            double d2 = this.getZ() + (this.getId.nextDouble() * 2.0 - 1.0) * (double)this.getBbWidth() * 0.5;
            double d3 = (this.getId.nextDouble() * 2.0 - 1.0) * 0.3;
            double d4 = 0.3 + this.getId.nextDouble() * 0.3;
            double d5 = (this.getId.nextDouble() * 2.0 - 1.0) * 0.3;
            this.level().addDestroyBlockEffect((ParticleOptions)ParticleTypes.REVERSE_PORTAL, d0, d1, d2, d3, d4, d5);
        }
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{this.controller});
    }

    private PlayState animationPredicate(AnimationState<DimensionalSpikeEntity> state) {
        if (this.isActivate()) {
            state.getController().setAnimation(SPIKE_RISE_ANIM);
        } else {
            state.getController().setAnimation(BLANK_ANIM);
        }
        return PlayState.CONTINUE;
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}

