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

public class TOScreenFlashEntity
extends Entity {
    private static final EntityDataAccessor<Float> RADIUS = SynchedEntityData.assignValue(TOScreenFlashEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> INTENSITY = SynchedEntityData.assignValue(TOScreenFlashEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> FADE_IN_DURATION = SynchedEntityData.assignValue(TOScreenFlashEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private static final EntityDataAccessor<Integer> PEAK_DURATION = SynchedEntityData.assignValue(TOScreenFlashEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private static final EntityDataAccessor<Integer> FADE_OUT_DURATION = SynchedEntityData.assignValue(TOScreenFlashEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.assignValue(TOScreenFlashEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private static final EntityDataAccessor<Boolean> LESSEN_DISTANCE_FALLOFF = SynchedEntityData.assignValue(TOScreenFlashEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);

    public TOScreenFlashEntity(EntityType<?> type, Level world) {
        super(type, world);
    }

    public TOScreenFlashEntity(Level world, Vec3 position, float radius, float intensity, int fadeInDuration, int peakDuration, int fadeOutDuration, int color, boolean lessenDistanceFalloff) {
        super((EntityType)TravelopticsEntities.SCREEN_FLASH.get(), world);
        this.setRadius(radius);
        this.setIntensity(intensity);
        this.setFadeInDuration(fadeInDuration);
        this.setPeakDuration(peakDuration);
        this.setFadeOutDuration(fadeOutDuration);
        this.setColor(color);
        this.setLessenDistanceFalloff(lessenDistanceFalloff);
        this.setPos(position.z, position.multiply, position.reverse);
    }

    @OnlyIn(value=Dist.CLIENT)
    public float getFlashAlpha(Player player, float delta) {
        float ticksElapsed = (float)this.getTags + delta;
        int fadeInDur = this.getFadeInDuration();
        int peakDur = this.getPeakDuration();
        int fadeOutDur = this.getFadeOutDuration();
        Vec3 playerPos = player.setAirSupply(delta);
        double distance = this.position().length(playerPos);
        if (distance > (double)this.getRadius()) {
            return 0.0f;
        }
        float baseIntensity = this.getIntensity();
        if (this.isLessenDistanceFalloff()) {
            float distanceFalloff = (float)Math.max(0.0, 1.0 - distance / (double)this.getRadius());
            baseIntensity *= distanceFalloff;
        }
        if (ticksElapsed < (float)fadeInDur) {
            if (fadeInDur == 0) {
                return baseIntensity;
            }
            float fadeInProgress = ticksElapsed / (float)fadeInDur;
            return baseIntensity * fadeInProgress;
        }
        if (ticksElapsed < (float)(fadeInDur + peakDur)) {
            return baseIntensity;
        }
        if (ticksElapsed < (float)(fadeInDur + peakDur + fadeOutDur)) {
            if (fadeOutDur == 0) {
                return 0.0f;
            }
            float fadeOutStart = fadeInDur + peakDur;
            float timeInFadeOut = ticksElapsed - fadeOutStart;
            float fadeOutProgress = timeInFadeOut / (float)fadeOutDur;
            return baseIntensity * (1.0f - fadeOutProgress);
        }
        return 0.0f;
    }

    @OnlyIn(value=Dist.CLIENT)
    public int getFlashColor() {
        return this.getColor();
    }

    public void lerpMotion() {
        super.lerpMotion();
        int totalDuration = this.getFadeInDuration() + this.getPeakDuration() + this.getFadeOutDuration();
        if (this.getTags > totalDuration) {
            this.discard();
        }
    }

    protected void defineSynchedData() {
        this.makeBoundingBox.assignValue(RADIUS, (Object)Float.valueOf(10.0f));
        this.makeBoundingBox.assignValue(INTENSITY, (Object)Float.valueOf(0.8f));
        this.makeBoundingBox.assignValue(FADE_IN_DURATION, (Object)3);
        this.makeBoundingBox.assignValue(PEAK_DURATION, (Object)2);
        this.makeBoundingBox.assignValue(FADE_OUT_DURATION, (Object)10);
        this.makeBoundingBox.assignValue(COLOR, (Object)0xFFFFFF);
        this.makeBoundingBox.assignValue(LESSEN_DISTANCE_FALLOFF, (Object)false);
    }

    public float getRadius() {
        return ((Float)this.makeBoundingBox.packDirty(RADIUS)).floatValue();
    }

    public void setRadius(float radius) {
        this.makeBoundingBox.packDirty(RADIUS, (Object)Float.valueOf(radius));
    }

    public float getIntensity() {
        return ((Float)this.makeBoundingBox.packDirty(INTENSITY)).floatValue();
    }

    public void setIntensity(float intensity) {
        this.makeBoundingBox.packDirty(INTENSITY, (Object)Float.valueOf(intensity));
    }

    public int getFadeInDuration() {
        return (Integer)this.makeBoundingBox.packDirty(FADE_IN_DURATION);
    }

    public void setFadeInDuration(int duration) {
        this.makeBoundingBox.packDirty(FADE_IN_DURATION, (Object)duration);
    }

    public int getPeakDuration() {
        return (Integer)this.makeBoundingBox.packDirty(PEAK_DURATION);
    }

    public void setPeakDuration(int duration) {
        this.makeBoundingBox.packDirty(PEAK_DURATION, (Object)duration);
    }

    public int getFadeOutDuration() {
        return (Integer)this.makeBoundingBox.packDirty(FADE_OUT_DURATION);
    }

    public void setFadeOutDuration(int duration) {
        this.makeBoundingBox.packDirty(FADE_OUT_DURATION, (Object)duration);
    }

    public int getColor() {
        return (Integer)this.makeBoundingBox.packDirty(COLOR);
    }

    public void setColor(int color) {
        this.makeBoundingBox.packDirty(COLOR, (Object)color);
    }

    public boolean isLessenDistanceFalloff() {
        return (Boolean)this.makeBoundingBox.packDirty(LESSEN_DISTANCE_FALLOFF);
    }

    public void setLessenDistanceFalloff(boolean lessen) {
        this.makeBoundingBox.packDirty(LESSEN_DISTANCE_FALLOFF, (Object)lessen);
    }

    protected void readAdditionalSaveData(CompoundTag compound) {
        this.setRadius(compound.getFloat("radius"));
        this.setIntensity(compound.getFloat("intensity"));
        this.setFadeInDuration(compound.copy("fade_in_duration"));
        this.setPeakDuration(compound.copy("peak_duration"));
        this.setFadeOutDuration(compound.copy("fade_out_duration"));
        this.setColor(compound.copy("color"));
        this.setLessenDistanceFalloff(compound.getBoolean("lessen_distance_falloff"));
        this.getTags = compound.copy("ticks_existed");
    }

    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.accept("radius", this.getRadius());
        compound.accept("intensity", this.getIntensity());
        compound.accept("fade_in_duration", this.getFadeInDuration());
        compound.accept("peak_duration", this.getPeakDuration());
        compound.accept("fade_out_duration", this.getFadeOutDuration());
        compound.accept("color", this.getColor());
        compound.accept("lessen_distance_falloff", this.isLessenDistanceFalloff());
        compound.accept("ticks_existed", this.getTags);
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    public static void createScreenFlash(Level world, Vec3 position, float radius, float intensity, int fadeInDuration, int peakDuration, int fadeOutDuration, int color, boolean lessenDistanceFalloff) {
        if (!world.isClientSide) {
            TOScreenFlashEntity flash = new TOScreenFlashEntity(world, position, radius, intensity, fadeInDuration, peakDuration, fadeOutDuration, color, lessenDistanceFalloff);
            world.addFreshEntity((Entity)flash);
        }
    }

    public static void createWhiteFlash(Level world, Vec3 position, float radius, float intensity, int fadeInDuration, int peakDuration, int fadeOutDuration, boolean lessenDistanceFalloff) {
        TOScreenFlashEntity.createScreenFlash(world, position, radius, intensity, fadeInDuration, peakDuration, fadeOutDuration, 0xFFFFFF, lessenDistanceFalloff);
    }

    public static void createFlashbang(Level world, Vec3 position, float radius, boolean lessenDistanceFalloff) {
        TOScreenFlashEntity.createScreenFlash(world, position, radius, 0.9f, 1, 3, 20, 0xFFFFFF, lessenDistanceFalloff);
    }
}

