/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.animated_particle;

import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class NightwardenCloneGroundSlam {
    public static void updateBossAuraParticles(Level level, LivingEntity boss, int meleeAnimTimer) {
        if (meleeAnimTimer > 210 || meleeAnimTimer < 25) {
            return;
        }
        Vec3 bossPos = boss.position();
        double bossX = bossPos.z;
        double bossY = bossPos.multiply + 1.5;
        double bossZ = bossPos.reverse;
        long worldTime = level.getGameTime();
        double time = (double)(worldTime + (long)meleeAnimTimer) * 0.1;
        if (!level.isClientSide) {
            NightwardenCloneGroundSlam.createFloatingRings(level, bossX, bossY, bossZ, time);
            NightwardenCloneGroundSlam.createAscendingSpiral(level, bossX, bossY, bossZ, time);
            NightwardenCloneGroundSlam.createPulsingCore(level, bossX, bossY, bossZ, time);
            NightwardenCloneGroundSlam.createOrbitingSatellites(level, bossX, bossY, bossZ, time);
        }
    }

    private static void createFloatingRings(Level level, double centerX, double centerY, double centerZ, double time) {
        for (int ring = 0; ring < 3; ++ring) {
            double ringHeight = centerY + (double)ring * 0.8 - 0.4;
            double ringRadius = 1.8 + (double)ring * 0.3;
            double ringSpeed = 1.0 + (double)ring * 0.3;
            int particlesInRing = 8 + ring * 2;
            for (int i = 0; i < particlesInRing; ++i) {
                double angle = time * ringSpeed + (double)i * Math.PI * 2.0 / (double)particlesInRing;
                double waveOffset = Math.sin(time * 2.0 + (double)ring) * 0.2;
                double currentRadius = ringRadius + waveOffset;
                double x = centerX + Math.cos(angle) * currentRadius;
                double y = ringHeight + Math.sin(time * 1.5 + (double)i) * 0.15;
                double z = centerZ + Math.sin(angle) * currentRadius;
                double velX = -Math.sin(angle) * 0.02;
                double velY = Math.cos(time * 2.0 + (double)i) * 0.01;
                double velZ = Math.cos(angle) * 0.02;
                MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, (double)x, (double)y, (double)z, (int)1, (double)velX, (double)velY, (double)velZ, (double)0.1, (boolean)true);
            }
        }
    }

    private static void createAscendingSpiral(Level level, double centerX, double centerY, double centerZ, double time) {
        int spiralParticles = 12;
        double spiralHeight = 2.5;
        for (int i = 0; i < spiralParticles; ++i) {
            double t = (double)i / (double)spiralParticles + time * 0.3;
            double angle1 = t * Math.PI * 4.0;
            double angle2 = angle1 + Math.PI;
            double height = t % 1.0 * spiralHeight;
            double radius = 1.2 + Math.sin(t * Math.PI * 2.0) * 0.3;
            double x1 = centerX + Math.cos(angle1) * radius;
            double y1 = centerY - 0.5 + height;
            double z1 = centerZ + Math.sin(angle1) * radius;
            double x2 = centerX + Math.cos(angle2) * radius;
            double y2 = centerY - 0.5 + height;
            double z2 = centerZ + Math.sin(angle2) * radius;
            double velX1 = -Math.sin(angle1) * 0.03;
            double velY1 = 0.08;
            double velZ1 = Math.cos(angle1) * 0.03;
            double velX2 = -Math.sin(angle2) * 0.03;
            double velY2 = 0.08;
            double velZ2 = Math.cos(angle2) * 0.03;
            MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, (double)x1, (double)y1, (double)z1, (int)1, (double)velX1, (double)velY1, (double)velZ1, (double)0.15, (boolean)true);
            MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, (double)x2, (double)y2, (double)z2, (int)1, (double)velX2, (double)velY2, (double)velZ2, (double)0.15, (boolean)true);
        }
    }

    private static void createPulsingCore(Level level, double centerX, double centerY, double centerZ, double time) {
        double pulseIntensity = 0.5 + Math.sin(time * 3.0) * 0.3;
        int coreParticles = (int)(6.0 + pulseIntensity * 8.0);
        for (int i = 0; i < coreParticles; ++i) {
            double angle = (double)i * Math.PI * 2.0 / (double)coreParticles + time * 0.5;
            double radius = 0.6 + pulseIntensity * 0.4;
            double randomRadius = radius + (Math.random() - 0.5) * 0.2;
            double randomHeight = (Math.random() - 0.5) * 0.4;
            double x = centerX + Math.cos(angle) * randomRadius;
            double y = centerY + randomHeight;
            double z = centerZ + Math.sin(angle) * randomRadius;
            double velX = Math.cos(angle) * pulseIntensity * 0.02;
            double velY = (Math.random() - 0.5) * 0.02;
            double velZ = Math.sin(angle) * pulseIntensity * 0.02;
            MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, (double)x, (double)y, (double)z, (int)1, (double)velX, (double)velY, (double)velZ, (double)0.2, (boolean)true);
        }
    }

    private static void createOrbitingSatellites(Level level, double centerX, double centerY, double centerZ, double time) {
        int satellites = 4;
        for (int sat = 0; sat < satellites; ++sat) {
            double baseAngle = (double)sat * Math.PI * 2.0 / (double)satellites + time * 0.8;
            double orbitRadius = 2.5 + Math.sin(time + (double)sat) * 0.3;
            double orbitHeight = centerY + Math.cos(time * 1.2 + (double)sat) * 0.6;
            for (int p = 0; p < 3; ++p) {
                double clusterAngle = baseAngle + (double)p * Math.PI * 2.0 / 3.0;
                double clusterRadius = 0.3;
                double satX = centerX + Math.cos(baseAngle) * orbitRadius;
                double satZ = centerZ + Math.sin(baseAngle) * orbitRadius;
                double x = satX + Math.cos(clusterAngle) * clusterRadius;
                double y = orbitHeight + Math.sin(time * 2.0 + (double)p) * 0.1;
                double z = satZ + Math.sin(clusterAngle) * clusterRadius;
                double velX = -Math.sin(baseAngle) * 0.04;
                double velY = Math.cos(time * 3.0 + (double)sat + (double)p) * 0.01;
                double velZ = Math.cos(baseAngle) * 0.04;
                MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, (double)x, (double)y, (double)z, (int)1, (double)velX, (double)velY, (double)velZ, (double)0.25, (boolean)true);
            }
        }
    }
}

