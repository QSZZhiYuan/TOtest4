/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModParticle
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.data_manager;

import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.github.L_Ender.cataclysm.init.ModParticle;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SpiritPointsManager {
    private static final int MAX_SPIRIT_POINTS = 200;

    public static int getSpiritPoints(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("SpiritPoints")) {
            return stack.getTag().copy("SpiritPoints");
        }
        return 0;
    }

    public static void setSpiritPoints(ItemStack stack, int points) {
        int cappedPoints = Math.min(points, 200);
        stack.getOrCreateTag().accept("SpiritPoints", cappedPoints);
    }

    public static void addSpiritPoints(ItemStack stack, Player player, int amount) {
        int currentPoints = SpiritPointsManager.getSpiritPoints(stack);
        int newPoints = Math.min(currentPoints + amount, 200);
        SpiritPointsManager.setSpiritPoints(stack, newPoints);
        SpiritPointsManager.displayActionBar(player, newPoints);
        SpiritPointsManager.playSoundOnThreshold(player, currentPoints, newPoints);
        SpiritPointsManager.spawnParticlesOnThreshold(player, currentPoints, newPoints);
    }

    private static void displayActionBar(Player player, int points) {
        if (player instanceof ServerPlayer) {
            MutableComponent message = points >= 200 ? Component.score((String)("\u2620 Soul Fragments: " + points)).withStyle(style -> style.applyTo(16770714)).withStyle(ChatFormatting.BOLD) : Component.score((String)("\u2620 Soul Fragments: " + points)).withStyle(style -> style.applyTo(7733218));
            player.updateTutorialInventoryAction((Component)message, true);
        }
    }

    private static void playSoundOnThreshold(Player player, int oldPoints, int newPoints) {
        if (player.level().isClientSide()) {
            return;
        }
        if (newPoints >= 200 && oldPoints < 200) {
            player.level().gameEvent(null, player.blockPosition(), (SoundEvent)TravelopticsSounds.SPIRIT_POINTS_MAX.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
        } else if (newPoints >= 100 && oldPoints < 100) {
            player.level().gameEvent(null, player.blockPosition(), (SoundEvent)TravelopticsSounds.SPIRIT_POINTS_THRESHOLD_TWO.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
        } else if (newPoints >= 50 && oldPoints < 50) {
            player.level().gameEvent(null, player.blockPosition(), (SoundEvent)TravelopticsSounds.SPIRIT_POINTS_THRESHOLD_ONE.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
        }
    }

    private static void spawnParticlesOnThreshold(Player player, int oldPoints, int newPoints) {
        if (newPoints >= 200 && oldPoints < 200) {
            Vec3 playerPosition = player.position();
            MagicManager.spawnParticles((Level)player.level(), (ParticleOptions)((ParticleOptions)ModParticle.PHANTOM_WING_FLAME.get()), (double)playerPosition.z, (double)playerPosition.multiply, (double)playerPosition.reverse, (int)20, (double)0.0, (double)1.0, (double)0.0, (double)0.1, (boolean)false);
        }
    }
}

