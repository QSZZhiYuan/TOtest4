/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.effect.SummonTimer
 *  io.redspace.ironsspellbooks.entity.mobs.MagicSummon
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.LivingEntity
 */
package com.gametechbc.traveloptics.api.entity.mobs;

import io.redspace.ironsspellbooks.effect.SummonTimer;
import io.redspace.ironsspellbooks.entity.mobs.MagicSummon;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public interface MagicMagneticSummon
extends MagicSummon {
    default public void onRemovedHelper(Entity entity, SummonTimer timer) {
        LivingEntity livingEntity;
        Entity.RemovalReason reason = entity.getRemovalReason();
        if (reason != null && (livingEntity = this.getSummoner()) instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer)livingEntity;
            if (reason.shouldDestroy()) {
                MobEffectInstance effect = player.getStandingEyeHeight((MobEffect)timer);
                if (effect != null) {
                    MobEffectInstance decrement = new MobEffectInstance((MobEffect)timer, effect.getDuration(), effect.getAmplifier() - 1, false, false, true);
                    if (decrement.getAmplifier() >= 0) {
                        player.getActiveEffectsMap().put(timer, decrement);
                        player.loadGameTypes.send((Packet)new ClientboundUpdateMobEffectPacket(player.getId(), decrement));
                    } else {
                        player.broadcastBreakEvent((MobEffect)timer);
                    }
                }
                if (reason.equals((Object)Entity.RemovalReason.DISCARDED)) {
                    player.sendSystemMessage((Component)Component.selector((String)"ui.traveloptics.magnetic_summon_despawn_message", (Object[])new Object[]{((Entity)this).getDisplayName()}));
                }
            }
        }
    }
}

