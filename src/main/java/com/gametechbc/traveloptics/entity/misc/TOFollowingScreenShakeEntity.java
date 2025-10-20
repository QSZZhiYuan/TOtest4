/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.network.NetworkHooks
 */
package com.gametechbc.traveloptics.entity.misc;

import com.gametechbc.traveloptics.init.TravelopticsEntities;
import java.util.Optional;
import java.util.UUID;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

public class TOFollowingScreenShakeEntity
extends Entity {
    private static final EntityDataAccessor<Float> RADIUS = SynchedEntityData.assignValue(TOFollowingScreenShakeEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> MAGNITUDE = SynchedEntityData.assignValue(TOFollowingScreenShakeEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> DURATION = SynchedEntityData.assignValue(TOFollowingScreenShakeEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private static final EntityDataAccessor<Integer> FADE_DURATION = SynchedEntityData.assignValue(TOFollowingScreenShakeEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private static final EntityDataAccessor<Integer> FADE_IN_DURATION = SynchedEntityData.assignValue(TOFollowingScreenShakeEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private static final EntityDataAccessor<Boolean> FADE_OUT_IN_DISTANCE = SynchedEntityData.assignValue(TOFollowingScreenShakeEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Optional<UUID>> TARGET_UUID = SynchedEntityData.assignValue(TOFollowingScreenShakeEntity.class, (EntityDataSerializer)EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Boolean> IS_FORCE_FADING = SynchedEntityData.assignValue(TOFollowingScreenShakeEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private Entity cachedTarget = null;
    private int forceFadeStartTick = -1;
    private int lastPositionUpdateTick = -1;

    public TOFollowingScreenShakeEntity(EntityType<?> type, Level world) {
        super(type, world);
    }

    public TOFollowingScreenShakeEntity(Level world, Entity targetEntity, float radius, float magnitude, int duration, int fadeInDuration, int fadeOutDuration, boolean fadeOutInDistance) {
        super((EntityType)TravelopticsEntities.FOLLOWING_SCREEN_SHAKE.get(), world);
        this.setRadius(radius);
        this.setMagnitude(magnitude);
        this.setDuration(duration);
        this.setFadeInDuration(fadeInDuration);
        this.setFadeDuration(fadeOutDuration);
        this.setFadeOutInDistance(fadeOutInDistance);
        this.setTargetUUID(targetEntity.getUUID());
        this.setForceFading(false);
        this.setPos(targetEntity.getX(), targetEntity.getY(), targetEntity.getZ());
        this.cachedTarget = targetEntity;
        this.lastPositionUpdateTick = 0;
    }

    @OnlyIn(value=Dist.CLIENT)
    public float getShakeAmount(Player player, float delta) {
        float baseAmount;
        float ticksDelta = (float)this.getTags + delta;
        float fadeInDur = this.getFadeInDuration();
        float duration = this.getDuration();
        float fadeDur = this.getFadeDuration();
        float magnitude = this.getMagnitude();
        if (this.isForceFading()) {
            float timeSinceForceFade = ticksDelta - (float)this.forceFadeStartTick;
            if (timeSinceForceFade >= fadeDur) {
                baseAmount = 0.0f;
            } else {
                float timeFrac = 1.0f - timeSinceForceFade / (fadeDur + 1.0f);
                baseAmount = timeFrac * timeFrac * magnitude;
            }
        } else if (ticksDelta < fadeInDur) {
            float progress = ticksDelta / fadeInDur;
            baseAmount = progress * progress * magnitude;
        } else if (ticksDelta < fadeInDur + duration) {
            baseAmount = magnitude;
        } else if (ticksDelta < fadeInDur + duration + fadeDur) {
            float timeFrac = 1.0f - (ticksDelta - fadeInDur - duration) / (fadeDur + 1.0f);
            baseAmount = timeFrac * timeFrac * magnitude;
        } else {
            baseAmount = 0.0f;
        }
        Vec3 playerPos = player.setAirSupply(delta);
        double distance = this.position().length(playerPos);
        float minStrengthFactor = 0.1f;
        float falloffStart = 0.7f * this.getRadius();
        float distanceFactor = this.getFadeOutInDistance() ? (distance <= (double)falloffStart ? 1.0f : (distance >= (double)this.getRadius() ? minStrengthFactor : minStrengthFactor + (float)((double)this.getRadius() - distance) / (this.getRadius() - falloffStart) * (1.0f - minStrengthFactor))) : (distance <= (double)this.getRadius() ? 1.0f : 0.0f);
        return distance <= (double)this.getRadius() ? baseAmount * distanceFactor : 0.0f;
    }

    public void lerpMotion() {
        super.lerpMotion();
        boolean shouldUpdatePosition = this.getTags - this.lastPositionUpdateTick >= 2;
        Entity target = this.getTargetEntity();
        if (target == null || target.isRemoved()) {
            if (!this.isForceFading()) {
                this.startForceFading();
            }
        } else if (shouldUpdatePosition) {
            this.setPos(target.getX(), target.getY(), target.getZ());
            this.cachedTarget = target;
            this.lastPositionUpdateTick = this.getTags;
        }
        if (this.isForceFading()) {
            float timeSinceForceFade = this.getTags - this.forceFadeStartTick;
            if (timeSinceForceFade > (float)this.getFadeDuration()) {
                this.discard();
            }
        } else {
            int totalDuration = this.getFadeInDuration() + this.getDuration() + this.getFadeDuration();
            if (this.getTags > totalDuration) {
                this.discard();
            }
        }
    }

    private Entity getTargetEntity() {
        if (this.cachedTarget != null && !this.cachedTarget.isRemoved()) {
            return this.cachedTarget;
        }
        Optional<UUID> targetUUID = this.getTargetUUID();
        if (targetUUID.isPresent()) {
            Level level = this.level();
            if (level instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel)level;
                Entity entity = serverLevel.getRandomSequence(targetUUID.get());
                if (entity != null) {
                    this.cachedTarget = entity;
                    return entity;
                }
            } else {
                for (Entity entity : this.level().getNearbyEntities(Entity.class, this.getBoundingBox().inflate((double)(this.getRadius() * 2.0f)))) {
                    if (!entity.getUUID().equals(targetUUID.get())) continue;
                    this.cachedTarget = entity;
                    return entity;
                }
            }
        }
        this.cachedTarget = null;
        return null;
    }

    private void startForceFading() {
        this.setForceFading(true);
        this.forceFadeStartTick = this.getTags;
    }

    protected void defineSynchedData() {
        this.makeBoundingBox.assignValue(RADIUS, (Object)Float.valueOf(10.0f));
        this.makeBoundingBox.assignValue(MAGNITUDE, (Object)Float.valueOf(1.0f));
        this.makeBoundingBox.assignValue(DURATION, (Object)0);
        this.makeBoundingBox.assignValue(FADE_DURATION, (Object)5);
        this.makeBoundingBox.assignValue(FADE_IN_DURATION, (Object)0);
        this.makeBoundingBox.assignValue(FADE_OUT_IN_DISTANCE, (Object)false);
        this.makeBoundingBox.assignValue(TARGET_UUID, Optional.empty());
        this.makeBoundingBox.assignValue(IS_FORCE_FADING, (Object)false);
    }

    public float getRadius() {
        return ((Float)this.makeBoundingBox.packDirty(RADIUS)).floatValue();
    }

    public void setRadius(float radius) {
        this.makeBoundingBox.packDirty(RADIUS, (Object)Float.valueOf(radius));
    }

    public float getMagnitude() {
        return ((Float)this.makeBoundingBox.packDirty(MAGNITUDE)).floatValue();
    }

    public void setMagnitude(float magnitude) {
        this.makeBoundingBox.packDirty(MAGNITUDE, (Object)Float.valueOf(magnitude));
    }

    public int getDuration() {
        return (Integer)this.makeBoundingBox.packDirty(DURATION);
    }

    public void setDuration(int duration) {
        this.makeBoundingBox.packDirty(DURATION, (Object)duration);
    }

    public int getFadeDuration() {
        return (Integer)this.makeBoundingBox.packDirty(FADE_DURATION);
    }

    public void setFadeDuration(int fadeDuration) {
        this.makeBoundingBox.packDirty(FADE_DURATION, (Object)fadeDuration);
    }

    public int getFadeInDuration() {
        return (Integer)this.makeBoundingBox.packDirty(FADE_IN_DURATION);
    }

    public void setFadeInDuration(int fadeInDuration) {
        this.makeBoundingBox.packDirty(FADE_IN_DURATION, (Object)fadeInDuration);
    }

    public boolean getFadeOutInDistance() {
        return (Boolean)this.makeBoundingBox.packDirty(FADE_OUT_IN_DISTANCE);
    }

    public void setFadeOutInDistance(boolean fadeOutInDistance) {
        this.makeBoundingBox.packDirty(FADE_OUT_IN_DISTANCE, (Object)fadeOutInDistance);
    }

    public Optional<UUID> getTargetUUID() {
        return (Optional)this.makeBoundingBox.packDirty(TARGET_UUID);
    }

    public void setTargetUUID(UUID targetUUID) {
        this.makeBoundingBox.packDirty(TARGET_UUID, Optional.of(targetUUID));
    }

    public boolean isForceFading() {
        return (Boolean)this.makeBoundingBox.packDirty(IS_FORCE_FADING);
    }

    public void setForceFading(boolean forceFading) {
        this.makeBoundingBox.packDirty(IS_FORCE_FADING, (Object)forceFading);
    }

    protected void readAdditionalSaveData(CompoundTag compound) {
        this.setRadius(compound.getFloat("radius"));
        this.setMagnitude(compound.getFloat("magnitude"));
        this.setDuration(compound.copy("duration"));
        this.setFadeDuration(compound.copy("fade_duration"));
        this.setFadeInDuration(compound.copy("fade_in_duration"));
        this.setFadeOutInDistance(compound.getBoolean("fade_out_in_distance"));
        if (compound.readNamedTagName("target_uuid")) {
            this.setTargetUUID(compound.accept("target_uuid"));
        }
        this.setForceFading(compound.getBoolean("is_force_fading"));
        this.forceFadeStartTick = compound.copy("force_fade_start_tick");
        this.lastPositionUpdateTick = compound.copy("last_position_update_tick");
        this.getTags = compound.copy("ticks_existed");
    }

    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.accept("radius", this.getRadius());
        compound.accept("magnitude", this.getMagnitude());
        compound.accept("duration", this.getDuration());
        compound.accept("fade_duration", this.getFadeDuration());
        compound.accept("fade_in_duration", this.getFadeInDuration());
        compound.accept("fade_out_in_distance", this.getFadeOutInDistance());
        Optional<UUID> targetUUID = this.getTargetUUID();
        targetUUID.ifPresent(value -> compound.accept("target_uuid", value));
        compound.accept("is_force_fading", this.isForceFading());
        compound.accept("force_fade_start_tick", this.forceFadeStartTick);
        compound.accept("last_position_update_tick", this.lastPositionUpdateTick);
        compound.accept("ticks_existed", this.getTags);
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    public static void createFollowingScreenShake(Level world, Entity targetEntity, float radius, float magnitude, int duration, int fadeInDuration, int fadeOutDuration, boolean fadeOutInDistance) {
        if (!world.isClientSide) {
            TOFollowingScreenShakeEntity screenShake = new TOFollowingScreenShakeEntity(world, targetEntity, radius, magnitude, duration, fadeInDuration, fadeOutDuration, fadeOutInDistance);
            world.addFreshEntity((Entity)screenShake);
        }
    }
}

