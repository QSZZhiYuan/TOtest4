/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 */
package com.gametechbc.traveloptics.data_manager;

import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.api.particle.SphereParticleManager;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class DarknessManager {
    public static final int MAX_DARKNESS_POINTS = 300;

    public static int getDarkness(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("Darkness")) {
            return stack.getTag().copy("Darkness");
        }
        return 0;
    }

    public static void setDarkness(ItemStack stack, int points) {
        int cappedPoints = Math.min(points, 300);
        stack.getOrCreateTag().accept("Darkness", cappedPoints);
    }

    public static void addDarkness(ItemStack stack, Player player, int amount) {
        int currentPoints = DarknessManager.getDarkness(stack);
        int newPoints = Math.min(currentPoints + amount, 300);
        DarknessManager.setDarkness(stack, newPoints);
        DarknessManager.playSoundOnThreshold(player, currentPoints, newPoints);
        DarknessManager.spawnParticlesOnThreshold(player, currentPoints, newPoints);
    }

    public static void displayActionBar(Player player, int points) {
        if (player instanceof ServerPlayer) {
            int percentage = (int)((double)points / 300.0 * 100.0);
            MutableComponent message = points >= 300 ? Component.score((String)("\u26a1 Plasma Fuel: " + percentage + "%")).withStyle(style -> style.applyTo(15219515)).withStyle(ChatFormatting.BOLD) : Component.score((String)("\u26a1 Plasma Fuel: " + percentage + "%")).withStyle(style -> style.applyTo(16476957));
            player.updateTutorialInventoryAction((Component)message, true);
        }
    }

    private static void playSoundOnThreshold(Player player, int oldPoints, int newPoints) {
        if (player.level().isClientSide()) {
            return;
        }
        if (newPoints >= 300 && oldPoints < 300) {
            player.level().gameEvent(null, player.blockPosition(), (SoundEvent)SoundEvents.RAVAGER_STEP, SoundSource.PLAYERS, 1.0f, 1.0f);
        }
    }

    private static void spawnParticlesOnThreshold(Player player, int oldPoints, int newPoints) {
        if (newPoints >= 300 && oldPoints < 300) {
            SphereParticleManager.spawnParticles(player.level(), (Entity)player, 25, (ParticleOptions)ParticleTypes.SMOKE, ParticleDirection.OUTWARD, 4.0);
        }
    }
}

