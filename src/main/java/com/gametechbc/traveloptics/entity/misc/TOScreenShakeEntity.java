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
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

public class TOScreenShakeEntity
extends Entity {
    private static final EntityDataAccessor<Float> RADIUS = SynchedEntityData.assignValue(TOScreenShakeEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> MAGNITUDE = SynchedEntityData.assignValue(TOScreenShakeEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> DURATION = SynchedEntityData.assignValue(TOScreenShakeEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private static final EntityDataAccessor<Integer> FADE_DURATION = SynchedEntityData.assignValue(TOScreenShakeEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private static final EntityDataAccessor<Integer> FADE_IN_DURATION = SynchedEntityData.assignValue(TOScreenShakeEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private static final EntityDataAccessor<Boolean> FADE_OUT_IN_DISTANCE = SynchedEntityData.assignValue(TOScreenShakeEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);

    public TOScreenShakeEntity(EntityType<?> type, Level world) {
        super(type, world);
    }

    public TOScreenShakeEntity(Level world, Vec3 position, float radius, float magnitude, int duration, int fadeInDuration, int fadeOutDuration, boolean fadeOutInDistance) {
        super((EntityType)TravelopticsEntities.SCREEN_SHAKE.get(), world);
        this.setRadius(radius);
        this.setMagnitude(magnitude);
        this.setDuration(duration);
        this.setFadeInDuration(fadeInDuration);
        this.setFadeDuration(fadeOutDuration);
        this.setFadeOutInDistance(fadeOutInDistance);
        this.setPos(position.z, position.multiply, position.reverse);
    }

    @OnlyIn(value=Dist.CLIENT)
    public float getShakeAmount(Player player, float delta) {
        float baseAmount;
        float ticksDelta = (float)this.getTags + delta;
        float fadeInDur = this.getFadeInDuration();
        float duration = this.getDuration();
        float fadeDur = this.getFadeDuration();
        float magnitude = this.getMagnitude();
        if (ticksDelta < fadeInDur) {
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
        int totalDuration = this.getFadeInDuration() + this.getDuration() + this.getFadeDuration();
        if (this.getTags > totalDuration) {
            this.discard();
        }
    }

    protected void defineSynchedData() {
        this.makeBoundingBox.assignValue(RADIUS, (Object)Float.valueOf(10.0f));
        this.makeBoundingBox.assignValue(MAGNITUDE, (Object)Float.valueOf(1.0f));
        this.makeBoundingBox.assignValue(DURATION, (Object)0);
        this.makeBoundingBox.assignValue(FADE_DURATION, (Object)5);
        this.makeBoundingBox.assignValue(FADE_IN_DURATION, (Object)0);
        this.makeBoundingBox.assignValue(FADE_OUT_IN_DISTANCE, (Object)false);
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

    protected void readAdditionalSaveData(CompoundTag compound) {
        this.setRadius(compound.getFloat("radius"));
        this.setMagnitude(compound.getFloat("magnitude"));
        this.setDuration(compound.copy("duration"));
        this.setFadeDuration(compound.copy("fade_duration"));
        this.setFadeInDuration(compound.copy("fade_in_duration"));
        this.setFadeOutInDistance(compound.getBoolean("fade_out_in_distance"));
        this.getTags = compound.copy("ticks_existed");
    }

    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.accept("radius", this.getRadius());
        compound.accept("magnitude", this.getMagnitude());
        compound.accept("duration", this.getDuration());
        compound.accept("fade_duration", this.getFadeDuration());
        compound.accept("fade_in_duration", this.getFadeInDuration());
        compound.accept("fade_out_in_distance", this.getFadeOutInDistance());
        compound.accept("ticks_existed", this.getTags);
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    public static void createScreenShake(Level world, Vec3 position, float radius, float magnitude, int duration, int fadeInDuration, int fadeOutDuration, boolean fadeOutInDistance) {
        if (!world.isClientSide) {
            TOScreenShakeEntity screenShake = new TOScreenShakeEntity(world, position, radius, magnitude, duration, fadeInDuration, fadeOutDuration, fadeOutInDistance);
            world.addFreshEntity((Entity)screenShake);
        }
    }
}

