/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.api.particle;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class JetpackParticleManager {
    private static final double OFFSET_X = 0.45;
    private static final double OFFSET_Y = 1.35;
    private static final double OFFSET_Z = -0.58;
    private static final double FIRE_SPEED = -0.4;
    private static final double SPREAD = 0.15;
    private static final double SPEED_VARIATION = 0.05;

    public static void spawnParticles(Player player, ParticleOptions particleOptions, int particleCount, boolean useSendParticles) {
        Level level = player.level();
        Vec3 lookVec = player.getLookAngle();
        Vec3 horizontalLookVec = new Vec3(lookVec.z, 0.0, lookVec.reverse).multiply();
        Vec3 rightThruster = player.position().y(-horizontalLookVec.reverse * 0.45 + horizontalLookVec.z * -0.58, 1.35, horizontalLookVec.z * 0.45 + horizontalLookVec.reverse * -0.58);
        Vec3 leftThruster = player.position().y(horizontalLookVec.reverse * 0.45 + horizontalLookVec.z * -0.58, 1.35, -horizontalLookVec.z * 0.45 + horizontalLookVec.reverse * -0.58);
        for (int i = 0; i < particleCount; ++i) {
            double spreadX = (Math.random() - 0.5) * 0.15;
            double spreadZ = (Math.random() - 0.5) * 0.15;
            double speedVariation = Math.random() * 0.05;
            Vec3 fireMotion = new Vec3(spreadX, -0.4 - speedVariation, spreadZ);
            if (useSendParticles) {
                if (level.isClientSide || !(level instanceof ServerLevel)) continue;
                ServerLevel serverLevel = (ServerLevel)level;
                serverLevel.getRandomSequence(particleOptions, rightThruster.z, rightThruster.multiply, rightThruster.reverse, 1, fireMotion.z, fireMotion.multiply, fireMotion.reverse, 0.05);
                serverLevel.getRandomSequence(particleOptions, leftThruster.z, leftThruster.multiply, leftThruster.reverse, 1, fireMotion.z, fireMotion.multiply, fireMotion.reverse, 0.05);
                continue;
            }
            level.addDestroyBlockEffect(particleOptions, rightThruster.z, rightThruster.multiply, rightThruster.reverse, fireMotion.z, fireMotion.multiply, fireMotion.reverse);
            level.addDestroyBlockEffect(particleOptions, leftThruster.z, leftThruster.multiply, leftThruster.reverse, fireMotion.z, fireMotion.multiply, fireMotion.reverse);
        }
    }
}

