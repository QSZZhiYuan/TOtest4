/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.sounds.AbstractTickableSoundInstance
 *  net.minecraft.client.resources.sounds.SoundInstance
 *  net.minecraft.client.resources.sounds.SoundInstance$Attenuation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemStack
 */
package com.gametechbc.traveloptics.sound;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public abstract class ArmorTickableSound
extends AbstractTickableSoundInstance {
    protected final LivingEntity user;

    public ArmorTickableSound(LivingEntity user, SoundEvent soundEvent) {
        super(soundEvent, SoundSource.PLAYERS, SoundInstance.createUnseededRandom());
        this.user = user;
        this.getAttenuation = SoundInstance.Attenuation.LINEAR;
        this.getY = true;
        this.getVolume = user.getX();
        this.getPitch = user.getY();
        this.getX = user.getZ();
        this.getZ = 0;
    }

    public boolean canPlaySound() {
        return !this.user.isSilent() && this.isValidArmor(this.user.shouldRemoveSoulSpeed(EquipmentSlot.CHEST));
    }

    public void tick() {
        ItemStack chestplate = this.user.shouldRemoveSoulSpeed(EquipmentSlot.CHEST);
        if (this.user.isAlive() && this.isValidArmor(chestplate)) {
            this.getVolume = this.user.getX();
            this.getPitch = this.user.getY();
            this.getX = this.user.getZ();
            this.tickVolume(chestplate);
        } else {
            this.stop();
        }
    }

    protected abstract void tickVolume(ItemStack var1);

    public abstract boolean isValidArmor(ItemStack var1);

    public boolean canStartSilent() {
        return true;
    }

    public boolean isSameEntity(LivingEntity user) {
        return this.user.isAlive() && this.user.getId() == user.getId();
    }
}

