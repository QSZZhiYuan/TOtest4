/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.MobEffectEvent$Added
 *  net.minecraftforge.event.entity.living.MobEffectEvent$Expired
 *  net.minecraftforge.event.entity.living.MobEffectEvent$Remove
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package com.gametechbc.traveloptics.api.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public abstract class AdvancedMobEffect
extends MobEffect {
    protected AdvancedMobEffect(MobEffectCategory category, int color) {
        super(category, color);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    protected void onEffectAdded(LivingEntity entity, MobEffectInstance newEffectInstance, MobEffectInstance oldEffectInstance, Entity source) {
    }

    protected void onEffectRemoved(LivingEntity entity, MobEffectInstance effectInstance) {
    }

    protected void onEffectExpired(LivingEntity entity, MobEffectInstance effectInstance) {
    }

    protected void onEffectRemovedOrExpired(LivingEntity entity, MobEffectInstance effectInstance) {
    }

    @SubscribeEvent
    public void handleEffectAdded(MobEffectEvent.Added event) {
        if (event.getEffectInstance().compareTo() == this) {
            this.onEffectAdded(event.getEntity(), event.getEffectInstance(), event.getOldEffectInstance(), event.getEffectSource());
        }
    }

    @SubscribeEvent
    public void handleEffectRemoved(MobEffectEvent.Remove event) {
        if (event.getEffect() == this) {
            LivingEntity entity = event.getEntity();
            MobEffectInstance effectInstance = event.getEffectInstance();
            this.onEffectRemoved(entity, effectInstance);
            this.onEffectExpired(entity, effectInstance);
        }
    }

    @SubscribeEvent
    public void handleEffectExpired(MobEffectEvent.Expired event) {
        if (event.getEffectInstance().compareTo() == this) {
            LivingEntity entity = event.getEntity();
            MobEffectInstance effectInstance = event.getEffectInstance();
            this.onEffectExpired(entity, effectInstance);
            this.onEffectExpired(entity, effectInstance);
        }
    }
}

