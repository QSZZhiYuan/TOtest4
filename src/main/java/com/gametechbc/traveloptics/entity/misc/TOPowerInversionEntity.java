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

public class TOPowerInversionEntity
extends Entity {
    private static final EntityDataAccessor<Float> RADIUS = SynchedEntityData.assignValue(TOPowerInversionEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> INTENSITY = SynchedEntityData.assignValue(TOPowerInversionEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> FADE_DURATION = SynchedEntityData.assignValue(TOPowerInversionEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private static final EntityDataAccessor<Boolean> INVERT_COLORS = SynchedEntityData.assignValue(TOPowerInversionEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> FLASH_COLOR = SynchedEntityData.assignValue(TOPowerInversionEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);

    public TOPowerInversionEntity(EntityType<?> type, Level world) {
        super(type, world);
    }

    public TOPowerInversionEntity(Level world, Vec3 position, float radius, float intensity, int fadeDuration, boolean invertColors, int flashColor) {
        super((EntityType)TravelopticsEntities.SCREEN_POWER_INVERSION.get(), world);
        this.setRadius(radius);
        this.setIntensity(intensity);
        this.setFadeDuration(fadeDuration);
        this.setInvertColors(invertColors);
        this.setFlashColor(flashColor);
        this.setPos(position.z, position.multiply, position.reverse);
    }

    @OnlyIn(value=Dist.CLIENT)
    public PowerEffectData getEffectData(Player player, float delta) {
        float ticksDelta = (float)this.getTags + delta;
        Vec3 playerPos = player.setAirSupply(delta);
        double distance = this.position().length(playerPos);
        if (distance > (double)this.getRadius()) {
            return new PowerEffectData(0.0f, 0.0f, EffectPhase.NONE);
        }
        float distanceFalloff = (float)Math.max(0.0, 1.0 - distance / (double)this.getRadius());
        float baseIntensity = this.getIntensity() * distanceFalloff;
        if (ticksDelta < 2.0f) {
            return new PowerEffectData(baseIntensity, 0.0f, EffectPhase.FIRST_FLASH);
        }
        if (ticksDelta < 4.0f) {
            return new PowerEffectData(0.0f, baseIntensity, EffectPhase.SECOND_FLASH);
        }
        if (ticksDelta < 4.0f + (float)this.getFadeDuration()) {
            float fadeProgress = (ticksDelta - 4.0f) / (float)this.getFadeDuration();
            fadeProgress = Math.max(0.0f, Math.min(1.0f, fadeProgress));
            float fadeIntensity = baseIntensity * (1.0f - fadeProgress);
            return new PowerEffectData(0.0f, fadeIntensity, EffectPhase.FADE);
        }
        return new PowerEffectData(0.0f, 0.0f, EffectPhase.NONE);
    }

    @OnlyIn(value=Dist.CLIENT)
    public int getFlashColor() {
        return this.getFlashColorValue();
    }

    @OnlyIn(value=Dist.CLIENT)
    public boolean shouldInvertColors() {
        return this.getInvertColors();
    }

    public void lerpMotion() {
        super.lerpMotion();
        int totalDuration = 4 + this.getFadeDuration();
        if (this.getTags > totalDuration) {
            this.discard();
        }
    }

    protected void defineSynchedData() {
        this.makeBoundingBox.assignValue(RADIUS, (Object)Float.valueOf(15.0f));
        this.makeBoundingBox.assignValue(INTENSITY, (Object)Float.valueOf(1.0f));
        this.makeBoundingBox.assignValue(FADE_DURATION, (Object)20);
        this.makeBoundingBox.assignValue(INVERT_COLORS, (Object)true);
        this.makeBoundingBox.assignValue(FLASH_COLOR, (Object)0xF8F8F8);
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

    public int getFadeDuration() {
        return (Integer)this.makeBoundingBox.packDirty(FADE_DURATION);
    }

    public void setFadeDuration(int duration) {
        this.makeBoundingBox.packDirty(FADE_DURATION, (Object)duration);
    }

    public boolean getInvertColors() {
        return (Boolean)this.makeBoundingBox.packDirty(INVERT_COLORS);
    }

    public void setInvertColors(boolean invert) {
        this.makeBoundingBox.packDirty(INVERT_COLORS, (Object)invert);
    }

    public int getFlashColorValue() {
        return (Integer)this.makeBoundingBox.packDirty(FLASH_COLOR);
    }

    public void setFlashColor(int color) {
        this.makeBoundingBox.packDirty(FLASH_COLOR, (Object)color);
    }

    protected void readAdditionalSaveData(CompoundTag compound) {
        this.setRadius(compound.getFloat("radius"));
        this.setIntensity(compound.getFloat("intensity"));
        this.setFadeDuration(compound.copy("fade_duration"));
        this.setInvertColors(compound.getBoolean("invert_colors"));
        this.setFlashColor(compound.copy("flash_color"));
        this.getTags = compound.copy("ticks_existed");
    }

    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.accept("radius", this.getRadius());
        compound.accept("intensity", this.getIntensity());
        compound.accept("fade_duration", this.getFadeDuration());
        compound.accept("invert_colors", this.getInvertColors());
        compound.accept("flash_color", this.getFlashColorValue());
        compound.accept("ticks_existed", this.getTags);
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    public static void createPowerInversion(Level world, Vec3 position, float radius, float intensity, int fadeDuration, boolean invertColors, int flashColor) {
        if (!world.isClientSide) {
            TOPowerInversionEntity effect = new TOPowerInversionEntity(world, position, radius, intensity, fadeDuration, invertColors, flashColor);
            world.addFreshEntity((Entity)effect);
        }
    }

    @OnlyIn(value=Dist.CLIENT)
    public static class PowerEffectData {
        public final float flashIntensity;
        public final float effectIntensity;
        public final EffectPhase phase;

        public PowerEffectData(float flashIntensity, float effectIntensity, EffectPhase phase) {
            this.flashIntensity = flashIntensity;
            this.effectIntensity = effectIntensity;
            this.phase = phase;
        }
    }

    @OnlyIn(value=Dist.CLIENT)
    public static enum EffectPhase {
        NONE,
        FIRST_FLASH,
        SECOND_FLASH,
        FADE;

    }
}

