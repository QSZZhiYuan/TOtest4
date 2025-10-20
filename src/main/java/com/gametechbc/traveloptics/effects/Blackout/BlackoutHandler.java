/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.events.ChangeManaEvent
 *  io.redspace.ironsspellbooks.api.events.SpellPreCastEvent
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingTickEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickItem
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 */
package com.gametechbc.traveloptics.effects.Blackout;

import com.gametechbc.traveloptics.init.TravelopticsEffects;
import io.redspace.ironsspellbooks.api.events.ChangeManaEvent;
import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlackoutHandler {
    @SubscribeEvent
    public static void onPlayerCast(SpellPreCastEvent event) {
        Player entity = event.getEntity();
        if (entity instanceof ServerPlayer) {
            MobEffectInstance blackoutEffect;
            ServerPlayer player = (ServerPlayer)entity;
            if (!player.level().isClientSide && (blackoutEffect = player.getStandingEyeHeight((MobEffect)TravelopticsEffects.BLACKOUT.get())) != null) {
                event.setCanceled(true);
                player.updateTutorialInventoryAction((Component)Component.score((String)"Blackout Active: Spells are suppressed!").withStyle(ChatFormatting.RED), true);
                player.level().getChunk(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARMOR_STAND_HIT, SoundSource.PLAYERS, 0.5f, 1.0f);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityCast(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        boolean blackoutEffect = entity.recreateFromPacket((MobEffect)TravelopticsEffects.BLACKOUT.get());
        if (entity instanceof AbstractSpellCastingMob) {
            AbstractSpellCastingMob caster = (AbstractSpellCastingMob)entity;
            if (!caster.level().isClientSide && blackoutEffect) {
                caster.cancelCast();
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player entity = event.getEntity();
        if (entity instanceof ServerPlayer) {
            MobEffectInstance blackoutEffect;
            ServerPlayer player = (ServerPlayer)entity;
            if (!player.level().isClientSide && (blackoutEffect = player.getStandingEyeHeight((MobEffect)TravelopticsEffects.BLACKOUT.get())) != null) {
                event.setCanceled(true);
                player.updateTutorialInventoryAction((Component)Component.score((String)"Blackout Active: Cannot use items!").withStyle(ChatFormatting.RED), true);
                player.level().getChunk(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARMOR_STAND_HIT, SoundSource.PLAYERS, 0.5f, 1.0f);
            }
        }
    }

    @SubscribeEvent
    public static void onManaRegen(ChangeManaEvent event) {
        if (event.getEntity().recreateFromPacket((MobEffect)TravelopticsEffects.BLACKOUT.get())) {
            event.setCanceled(true);
        }
    }
}

