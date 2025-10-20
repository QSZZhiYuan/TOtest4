/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.events.SpellPreCastEvent
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingTickEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickBlock
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickItem
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 */
package com.gametechbc.traveloptics.effects.Casting;

import com.gametechbc.traveloptics.init.TravelopticsEffects;
import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CastingHandler {
    @SubscribeEvent
    public static void onPlayerCast(SpellPreCastEvent event) {
        Player entity = event.getEntity();
        if (entity instanceof ServerPlayer) {
            MobEffectInstance blackoutEffect;
            ServerPlayer player = (ServerPlayer)entity;
            if (!player.level().isClientSide && (blackoutEffect = player.getStandingEyeHeight((MobEffect)TravelopticsEffects.CASTING.get())) != null) {
                event.setCanceled(true);
                player.updateTutorialInventoryAction((Component)Component.translatable((String)"effect.traveloptics.casting.warning").withStyle(ChatFormatting.RED), true);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityCast(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        boolean blackoutEffect = entity.recreateFromPacket((MobEffect)TravelopticsEffects.CASTING.get());
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
            if (!player.level().isClientSide && (blackoutEffect = player.getStandingEyeHeight((MobEffect)TravelopticsEffects.CASTING.get())) != null) {
                event.setCanceled(true);
                player.updateTutorialInventoryAction((Component)Component.translatable((String)"effect.traveloptics.casting.warning").withStyle(ChatFormatting.RED), true);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player entity = event.getEntity();
        if (entity instanceof ServerPlayer) {
            MobEffectInstance blackoutEffect;
            ServerPlayer player = (ServerPlayer)entity;
            if (!player.level().isClientSide && (blackoutEffect = player.getStandingEyeHeight((MobEffect)TravelopticsEffects.CASTING.get())) != null) {
                event.setCanceled(true);
                player.updateTutorialInventoryAction((Component)Component.translatable((String)"effect.traveloptics.casting.warning").withStyle(ChatFormatting.RED), true);
            }
        }
    }
}

