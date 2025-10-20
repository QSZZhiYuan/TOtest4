/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModParticle
 *  com.github.L_Ender.cataclysm.init.ModSounds
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.data_manager;

import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.api.particle.SphereParticleManager;
import com.github.L_Ender.cataclysm.init.ModParticle;
import com.github.L_Ender.cataclysm.init.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class PhantomRageManager {
    public static final int MAX_RAGE_POINTS = 350;

    public static int getPhantomRage(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("PhantomRage")) {
            return stack.getTag().copy("PhantomRage");
        }
        return 0;
    }

    public static void setPhantomRage(ItemStack stack, int points) {
        int cappedPoints = Math.min(points, 350);
        stack.getOrCreateTag().accept("PhantomRage", cappedPoints);
    }

    public static void addPhantomRage(ItemStack stack, Player player, int amount) {
        int currentPoints = PhantomRageManager.getPhantomRage(stack);
        int newPoints = Math.min(currentPoints + amount, 350);
        PhantomRageManager.setPhantomRage(stack, newPoints);
        PhantomRageManager.playSoundOnThreshold(player, currentPoints, newPoints);
        PhantomRageManager.spawnParticlesOnThreshold(player, currentPoints, newPoints);
    }

    private static void displayActionBar(Player player, int points) {
        if (player instanceof ServerPlayer) {
            MutableComponent message = points >= 350 ? Component.score((String)("\ud83d\udca2 Phantom Rage: " + points)).withStyle(style -> style.applyTo(16770714)).withStyle(ChatFormatting.BOLD) : Component.score((String)("\ud83d\udca2 Phantom Rage: " + points)).withStyle(style -> style.applyTo(7733218));
            player.updateTutorialInventoryAction((Component)message, true);
        }
    }

    private static void playSoundOnThreshold(Player player, int oldPoints, int newPoints) {
        if (player.level().isClientSide()) {
            return;
        }
        if (newPoints >= 350 && oldPoints < 350) {
            player.level().gameEvent(null, player.blockPosition(), (SoundEvent)ModSounds.MALEDICTUS_BATTLE_CRY.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
        } else if (newPoints >= 230 && oldPoints < 230) {
            player.level().gameEvent(null, player.blockPosition(), (SoundEvent)ModSounds.MALEDICTUS_HURT.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
        } else if (newPoints >= 115 && oldPoints < 115) {
            player.level().gameEvent(null, player.blockPosition(), (SoundEvent)ModSounds.MALEDICTUS_HURT.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
        }
    }

    private static void spawnParticlesOnThreshold(Player player, int oldPoints, int newPoints) {
        if (newPoints >= 350 && oldPoints < 350) {
            Vec3 playerPosition = player.position();
            SphereParticleManager.spawnParticles(player.level(), (Entity)player, 50, (ParticleOptions)ModParticle.PHANTOM_WING_FLAME.get(), ParticleDirection.OUTWARD, 4.0);
        }
    }
}

