/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.events.SpellPreCastEvent
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.player.Player
 *  net.minecraftforge.event.TickEvent$PlayerTickEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickItem
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 */
package com.gametechbc.traveloptics.effects.FrozenSight;

import com.gametechbc.traveloptics.init.TravelopticsEffects;
import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FrozenSightHandler {
    @SubscribeEvent
    public static void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player entity = event.getEntity();
        if (entity instanceof ServerPlayer) {
            MobEffectInstance frozenSightEffect;
            ServerPlayer player = (ServerPlayer)entity;
            if (!player.level().isClientSide && (frozenSightEffect = player.getStandingEyeHeight((MobEffect)TravelopticsEffects.FROZEN_SIGHT.get())) != null) {
                event.setCanceled(true);
                player.updateTutorialInventoryAction((Component)Component.score((String)"Wait till it ends!").withStyle(ChatFormatting.RED), true);
                player.level().getChunk(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARMOR_STAND_HIT, SoundSource.PLAYERS, 0.5f, 1.0f);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCast(SpellPreCastEvent event) {
        Player entity = event.getEntity();
        if (entity instanceof ServerPlayer) {
            MobEffectInstance frozenSightEffect;
            ServerPlayer player = (ServerPlayer)entity;
            if (!player.level().isClientSide && (frozenSightEffect = player.getStandingEyeHeight((MobEffect)TravelopticsEffects.FROZEN_SIGHT.get())) != null) {
                event.setCanceled(true);
                player.updateTutorialInventoryAction((Component)Component.score((String)"Wait till it ends!").withStyle(ChatFormatting.RED), true);
                player.level().getChunk(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARMOR_STAND_HIT, SoundSource.PLAYERS, 0.5f, 1.0f);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        MobEffectInstance effectInstance = player.getStandingEyeHeight((MobEffect)TravelopticsEffects.FROZEN_SIGHT.get());
        if (effectInstance != null) {
            player.getX(true);
            player.createAttributes(false);
        }
    }
}

