/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.client.particle.LightningParticle$OrbData
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
import com.github.L_Ender.cataclysm.client.particle.LightningParticle;
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

public class PlasmaCoreManager {
    public static final int MAX_PLASMA_POINTS = 250;

    public static int getPlasmaCore(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("PlasmaCore")) {
            return stack.getTag().copy("PlasmaCore");
        }
        return 0;
    }

    public static void setPlasmaCore(ItemStack stack, int points) {
        int cappedPoints = Math.min(points, 250);
        stack.getOrCreateTag().accept("PlasmaCore", cappedPoints);
    }

    public static void addPlasmaCore(ItemStack stack, Player player, int amount) {
        int currentPoints = PlasmaCoreManager.getPlasmaCore(stack);
        int newPoints = Math.min(currentPoints + amount, 250);
        PlasmaCoreManager.setPlasmaCore(stack, newPoints);
        PlasmaCoreManager.displayActionBar(player, newPoints);
        PlasmaCoreManager.playSoundOnThreshold(player, currentPoints, newPoints);
        PlasmaCoreManager.spawnParticlesOnThreshold(player, currentPoints, newPoints);
    }

    private static void displayActionBar(Player player, int points) {
        if (player instanceof ServerPlayer) {
            MutableComponent message = points >= 250 ? Component.score((String)("\u26a1 Plasma Core: " + points)).withStyle(style -> style.applyTo(15219515)).withStyle(ChatFormatting.BOLD) : Component.score((String)("\u26a1 Plasma Core: " + points)).withStyle(style -> style.applyTo(16476957));
            player.updateTutorialInventoryAction((Component)message, true);
        }
    }

    private static void playSoundOnThreshold(Player player, int oldPoints, int newPoints) {
        if (player.level().isClientSide()) {
            return;
        }
        if (newPoints >= 250 && oldPoints < 250) {
            player.level().gameEvent(null, player.blockPosition(), (SoundEvent)ModSounds.HARBINGER_CHARGE_PREPARE.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
        } else if (newPoints >= 200 && oldPoints < 200) {
            player.level().gameEvent(null, player.blockPosition(), (SoundEvent)ModSounds.HARBINGER_STUN.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
        } else if (newPoints >= 100 && oldPoints < 100) {
            player.level().gameEvent(null, player.blockPosition(), (SoundEvent)ModSounds.HARBINGER_STUN.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
        }
    }

    private static void spawnParticlesOnThreshold(Player player, int oldPoints, int newPoints) {
        if (newPoints >= 250 && oldPoints < 250) {
            Vec3 playerPosition = player.position();
            SphereParticleManager.spawnParticles(player.level(), (Entity)player, 25, (ParticleOptions)new LightningParticle.OrbData(255, 26, 0), ParticleDirection.OUTWARD, 4.0);
        }
    }
}

