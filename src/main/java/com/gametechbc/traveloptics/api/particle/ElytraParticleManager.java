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

public class ElytraParticleManager {
    private static final double OFFSET_X = 0.4;
    private static final double OFFSET_Y = 0.7;
    private static final double OFFSET_Z = 0.2;
    private static final double PARTICLE_SPEED = -0.3;

    public static void spawnParticles(Player player, ParticleOptions particleOptions, int particleCount, boolean syncParticles) {
        Level level = player.level();
        Vec3 lookVec = player.getLookAngle().multiply();
        Vec3 rightThruster = player.position().y(-lookVec.reverse * 0.4 + lookVec.z * 0.2, 0.7 + lookVec.multiply * 0.2, lookVec.z * 0.4 + lookVec.reverse * 0.2);
        Vec3 leftThruster = player.position().y(lookVec.reverse * 0.4 + lookVec.z * 0.2, 0.7 + lookVec.multiply * 0.2, -lookVec.z * 0.4 + lookVec.reverse * 0.2);
        for (int i = 0; i < particleCount; ++i) {
            double spreadX = (Math.random() - 0.5) * 0.4;
            double spreadY = (Math.random() - 0.5) * 0.15;
            double spreadZ = (Math.random() - 0.5) * 0.4;
            Vec3 motion = new Vec3(lookVec.z * -0.3 + spreadX, lookVec.multiply * -0.3 + spreadY, lookVec.reverse * -0.3 + spreadZ);
            if (syncParticles) {
                if (level.isClientSide || !(level instanceof ServerLevel)) continue;
                ServerLevel serverLevel = (ServerLevel)level;
                serverLevel.getRandomSequence(particleOptions, rightThruster.z, rightThruster.multiply, rightThruster.reverse, 1, motion.z, motion.multiply, motion.reverse, 0.05);
                serverLevel.getRandomSequence(particleOptions, leftThruster.z, leftThruster.multiply, leftThruster.reverse, 1, motion.z, motion.multiply, motion.reverse, 0.05);
                continue;
            }
            level.addDestroyBlockEffect(particleOptions, rightThruster.z, rightThruster.multiply, rightThruster.reverse, motion.z, motion.multiply, motion.reverse);
            level.addDestroyBlockEffect(particleOptions, leftThruster.z, leftThruster.multiply, leftThruster.reverse, motion.z, motion.multiply, motion.reverse);
        }
    }
}

