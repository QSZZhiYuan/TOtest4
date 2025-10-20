/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.shapes.VoxelShape
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
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_slam_clone;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.misc.NightwardenScytheSlamAnimatedParticle;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.NightwardenCloneBase;
import com.gametechbc.traveloptics.entity.projectiles.dimensional_spike.DimensionalSpikeEntity;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class NightwardenSlamCloneEntity
extends NightwardenCloneBase
implements GeoEntity,
AntiMagicSusceptible {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
    private static final EntityDataAccessor<Float> DATA_LINES = SynchedEntityData.assignValue(NightwardenSlamCloneEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_DAMAGE = SynchedEntityData.assignValue(NightwardenSlamCloneEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> DATA_SPAWNS_RUNES = SynchedEntityData.assignValue(NightwardenSlamCloneEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private boolean playSoundOnRuneSpawn = false;
    private final RawAnimation EXPLODE_ANIMATION = RawAnimation.begin().thenPlay("nightwarden_clone_scythe_slam");
    private final AnimationController<NightwardenSlamCloneEntity> controller = new AnimationController((GeoAnimatable)this, "nightwarden_clone_slam_controller", 0, this::animationPredicate);

    public NightwardenSlamCloneEntity(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public NightwardenSlamCloneEntity(Level level, LivingEntity entityToCopy) {
        this((EntityType<? extends LivingEntity>)((EntityType)TravelopticsEntities.NIGHTWARDEN_SLAM_CLONE.get()), level);
        this.moveTo(entityToCopy.getX(), entityToCopy.getY(), entityToCopy.getZ(), entityToCopy.getYRot(), entityToCopy.getXRot());
        this.setYBodyRot(entityToCopy.yBodyRot);
        this.yBodyRotO = this.yBodyRot;
        this.setYHeadRot(entityToCopy.getYHeadRot());
        this.yHeadRotO = this.yHeadRot;
        this.setSummoner(entityToCopy);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.makeBoundingBox.assignValue(DATA_LINES, (Object)Float.valueOf(10.0f));
        this.makeBoundingBox.assignValue(DATA_DAMAGE, (Object)Float.valueOf(5.0f));
        this.makeBoundingBox.assignValue(DATA_SPAWNS_RUNES, (Object)false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.accept("Lines", this.getLines());
        tag.accept("Damage", this.getDamage());
        tag.accept("SpawnsRunes", this.doesSpawnRunes());
        tag.accept("PlaySoundOnRuneSpawn", this.playSoundOnRuneSpawn);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("Lines")) {
            this.setLines(tag.getFloat("Lines"));
        }
        if (tag.contains("Damage")) {
            this.setDamage(tag.getFloat("Damage"));
        }
        if (tag.contains("SpawnsRunes")) {
            this.setSpawnsRunes(tag.getBoolean("SpawnsRunes"));
        }
        if (tag.contains("PlaySoundOnRuneSpawn")) {
            this.playSoundOnRuneSpawn = tag.getBoolean("PlaySoundOnRuneSpawn");
        }
    }

    public void setPlaySoundOnRuneSpawn(boolean play) {
        this.playSoundOnRuneSpawn = play;
    }

    public boolean shouldPlaySoundOnRuneSpawn() {
        return this.playSoundOnRuneSpawn;
    }

    public void setSpawnsRunes(boolean spawns) {
        this.makeBoundingBox.packDirty(DATA_SPAWNS_RUNES, (Object)spawns);
    }

    public boolean doesSpawnRunes() {
        return (Boolean)this.makeBoundingBox.packDirty(DATA_SPAWNS_RUNES);
    }

    public void setLines(float lines) {
        this.makeBoundingBox.packDirty(DATA_LINES, (Object)Float.valueOf(lines));
    }

    public float getLines() {
        return ((Float)this.makeBoundingBox.packDirty(DATA_LINES)).floatValue();
    }

    public void setDamage(float damage) {
        this.makeBoundingBox.packDirty(DATA_DAMAGE, (Object)Float.valueOf(damage));
    }

    public float getDamage() {
        return ((Float)this.makeBoundingBox.packDirty(DATA_DAMAGE)).floatValue();
    }

    public void lerpMotion() {
        super.lerpMotion();
        NightwardenScytheSlamAnimatedParticle.spawnScytheSwingParticles(this, this.getTags, 26, 33);
        if (this.getTags == 33 && this.doesSpawnRunes()) {
            LivingEntity summoner = this.getSummoner();
            if (this.shouldPlaySoundOnRuneSpawn()) {
                this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_BIG_SLAM_CLONES.get(), 1.0f, 1.0f);
            }
            if (!this.level().isClientSide()) {
                int runeCount = Mth.roundToward((float)this.getLines());
                float spacing = 1.2f;
                float initialOffset = 6.0f;
                float yawRad = (float)Math.toRadians(this.getYRot());
                double dx = -Mth.outFromOrigin((float)yawRad);
                double dz = Mth.randomBetween((float)yawRad);
                for (int i = 0; i < runeCount; ++i) {
                    double distance = initialOffset + (float)i * spacing;
                    double x = this.getX() + dx * distance;
                    double z = this.getZ() + dz * distance;
                    double y = this.getY();
                    int warmup = i * 2;
                    this.spawnDimensionalSpike(x, y, z, this.getYRot(), warmup, this.level(), summoner, this.getDamage());
                }
            }
        }
        if (this.getTags >= 50) {
            this.discard();
        }
    }

    private boolean spawnDimensionalSpike(double x, double y, double z, float yRot, int warmupDelayTicks, Level world, LivingEntity summoner, float damage) {
        BlockPos blockpos = BlockPos.breadthFirstTraversal((double)x, (double)y, (double)z);
        boolean foundGround = false;
        double surfaceY = 0.0;
        while (blockpos.getY() > world.getMinBuildHeight()) {
            BlockPos below = blockpos.below();
            BlockState stateBelow = world.getBlockState(below);
            if (stateBelow.isFaceSturdy((BlockGetter)world, below, Direction.UP)) {
                BlockState stateAt;
                VoxelShape shape;
                if (!world.isEmptyBlock(blockpos) && !(shape = (stateAt = world.getBlockState(blockpos)).getCollisionShape((BlockGetter)world, blockpos)).calculateFace()) {
                    surfaceY = shape.optimize(Direction.Axis.Y);
                }
                foundGround = true;
                break;
            }
            blockpos = below;
        }
        if (foundGround) {
            double finalY = (double)blockpos.getY() + surfaceY;
            DimensionalSpikeEntity spike = new DimensionalSpikeEntity(world, x, finalY, z, yRot, warmupDelayTicks, damage, summoner);
            world.addFreshEntity((Entity)spike);
            return true;
        }
        return false;
    }

    public void onAntiMagic(MagicData playerMagicData) {
        this.discard();
    }

    @Nullable
    protected SoundEvent giveExperiencePoints(DamageSource pDamageSource) {
        return (SoundEvent)TravelopticsSounds.CRIMSON_DESCEND_IMPACT.get();
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return (SoundEvent)TravelopticsSounds.CRIMSON_DESCEND_IMPACT.get();
    }

    public boolean sendSystemMessage(DamageSource pSource, float pAmount) {
        return false;
    }

    private PlayState animationPredicate(AnimationState<NightwardenSlamCloneEntity> event) {
        event.getController().setAnimation(this.EXPLODE_ANIMATION);
        return PlayState.CONTINUE;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{this.controller});
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}

