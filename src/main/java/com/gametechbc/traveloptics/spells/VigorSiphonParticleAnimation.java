/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.util.ParticleHelper
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.spells;

import com.gametechbc.traveloptics.init.TravelopticsParticles;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class VigorSiphonParticleAnimation {
    public static void playChannelingAnimation(Level level, LivingEntity entity) {
        if (level.isClientSide) {
            return;
        }
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        long gameTime = level.getGameTime();
        double time = (double)gameTime * 0.02;
        VigorSiphonParticleAnimation.spawnAdvancedCircle(level, x, y + 0.1 + Math.sin(time) * 0.3, z, 4.0, 18, time * 1.5, 0.2);
        VigorSiphonParticleAnimation.spawnAdvancedCircle(level, x, y + 0.1 + Math.cos(time * 1.2) * 0.25, z, 2.8, 14, -time * 2.0, 0.15);
        VigorSiphonParticleAnimation.spawnAdvancedCircle(level, x, y + 0.1 + Math.sin(time * 0.8) * 0.4, z, 1.5, 10, time * 2.5, 0.1);
        double phi = (1.0 + Math.sqrt(5.0)) / 2.0;
        for (int point = 0; point < 5; ++point) {
            double pentAngle = (double)(point * 2) * Math.PI / 5.0 + time * 0.8;
            for (int i = 0; i < 6; ++i) {
                double spiralRadius = 0.3 + (double)i * 0.25 * Math.pow(phi, (double)i * 0.1);
                double spiralAngle = pentAngle + (double)i * 0.4;
                double spiralX = x + Math.cos(spiralAngle) * spiralRadius;
                double spiralZ = z + Math.sin(spiralAngle) * spiralRadius;
                double spiralY = y + 0.1 + Math.sin(time + (double)i * 0.5) * 0.2;
                if (!(spiralRadius <= 3.5)) continue;
                MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.VERY_SHORT_LIGHT_RED_GLOWING_ENCHANT, (double)spiralX, (double)spiralY, (double)spiralZ, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)false);
            }
        }
        int fibA = 1;
        int fibB = 1;
        for (int fib = 0; fib < 4; ++fib) {
            int fibNext = fibA + fibB;
            double fibAngle = (double)fib * 2.39996 + time * 0.5;
            double fibRadius = Math.sqrt(fibNext) * 0.4;
            double fibX = x + Math.cos(fibAngle) * fibRadius;
            double fibZ = z + Math.sin(fibAngle) * fibRadius;
            double fibY = y + 0.1 + Math.cos(time * 1.5 + (double)fib) * 0.15;
            MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.VERY_SHORT_LIGHT_RED_GLOWING_ENCHANT, (double)fibX, (double)fibY, (double)fibZ, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)false);
            fibA = fibB;
            fibB = fibNext;
        }
        for (int curve = 0; curve < 8; ++curve) {
            double t = (double)(gameTime + (long)(curve * 5)) * 0.03;
            double lissX = x + Math.sin(t * 3.0) * 2.0;
            double lissZ = z + Math.sin(t * 2.0) * 1.5;
            double lissY = y + 0.1 + Math.sin(t * 4.0) * 0.2;
            MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.VERY_SHORT_LIGHT_RED_GLOWING_ENCHANT, (double)lissX, (double)lissY, (double)lissZ, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)false);
        }
        if (gameTime % 3L == 0L) {
            for (int rain = 0; rain < 2; ++rain) {
                double rainAngle = entity.getRandom().nextDouble() * Math.PI * 2.0;
                double rainRadius = entity.getRandom().nextDouble() * 3.5;
                double rainX = x + Math.cos(rainAngle) * rainRadius;
                double rainZ = z + Math.sin(rainAngle) * rainRadius;
                double fallTime = (double)(gameTime % 40L) * 0.1;
                double rainY = y + 6.0 - fallTime * fallTime * 0.5 + Math.sin(fallTime) * 0.3;
                if (!(rainY > y)) continue;
                MagicManager.spawnParticles((Level)level, (ParticleOptions)ParticleHelper.BLOOD, (double)rainX, (double)rainY, (double)rainZ, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)false);
            }
        }
    }

    public static void playCastCompleteAnimation(Level level, LivingEntity entity) {
        double[] branchAngles;
        if (level.isClientSide) {
            return;
        }
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        for (int ring = 0; ring < 4; ++ring) {
            double ringRadius = (double)(ring + 1) * 1.2;
            int particlesInRing = (int)(16.0 + ringRadius * 4.0);
            for (int i = 0; i < particlesInRing; ++i) {
                double shockAngle = (double)(i * 2) * Math.PI / (double)particlesInRing + (double)ring * 0.3;
                double shockX = x + Math.cos(shockAngle) * ringRadius;
                double shockZ = z + Math.sin(shockAngle) * ringRadius;
                double shockY = y + 0.5 + Math.sin(shockAngle * 3.0) * 0.4;
                MagicManager.spawnParticles((Level)level, (ParticleOptions)((ParticleOptions)TravelopticsParticles.RED_STAR_OUTWARD_PARTICLE.get()), (double)shockX, (double)shockY, (double)shockZ, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)false);
            }
        }
        for (int vortex = 0; vortex < 25; ++vortex) {
            double vortexAngle = entity.getRandom().nextDouble() * Math.PI * 2.0;
            double vortexRadius = Math.pow(entity.getRandom().nextDouble(), 0.7) * 3.0;
            double vortexHeight = entity.getRandom().nextGaussian() * 0.8;
            double vortexX = x + Math.cos(vortexAngle + vortexRadius * 0.3) * vortexRadius;
            double vortexZ = z + Math.sin(vortexAngle + vortexRadius * 0.3) * vortexRadius;
            double vortexY = y + 1.0 + vortexHeight;
            MagicManager.spawnParticles((Level)level, (ParticleOptions)((ParticleOptions)TravelopticsParticles.RED_STAR_OUTWARD_PARTICLE.get()), (double)vortexX, (double)vortexY, (double)vortexZ, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)false);
        }
        for (double baseAngle : branchAngles = new double[]{0.0, 1.0471975511965976, 2.0943951023931953, Math.PI, 4.1887902047863905, 5.235987755982989}) {
            for (int branch = 1; branch <= 6; ++branch) {
                double branchRadius = (double)branch * 0.5;
                double branchX = x + Math.cos(baseAngle) * branchRadius;
                double branchZ = z + Math.sin(baseAngle) * branchRadius;
                double branchY = y + 0.3 + entity.getRandom().nextDouble();
                MagicManager.spawnParticles((Level)level, (ParticleOptions)ParticleHelper.BLOOD, (double)branchX, (double)branchY, (double)branchZ, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)false);
                for (int subBranch = 0; subBranch < 2; ++subBranch) {
                    double subAngle = baseAngle + (subBranch == 0 ? 0.5235987755982988 : -0.5235987755982988);
                    double subRadius = branchRadius * 0.7;
                    double subX = x + Math.cos(subAngle) * subRadius;
                    double subZ = z + Math.sin(subAngle) * subRadius;
                    double subY = y + 0.3 + entity.getRandom().nextDouble() * 0.8;
                    if (!((double)entity.getRandom().nextFloat() < 0.6)) continue;
                    MagicManager.spawnParticles((Level)level, (ParticleOptions)ParticleHelper.BLOOD, (double)subX, (double)subY, (double)subZ, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)false);
                }
            }
        }
    }

    private static void spawnAdvancedCircle(Level level, double x, double y, double z, double radius, int count, double rotation, double heightVariation) {
        for (int i = 0; i < count; ++i) {
            double angle = (double)(i * 2) * Math.PI / (double)count + rotation;
            double circleX = x + Math.cos(angle) * radius;
            double circleZ = z + Math.sin(angle) * radius;
            double circleY = y + Math.sin(angle * 3.0 + rotation) * heightVariation;
            MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.VERY_SHORT_LIGHT_RED_GLOWING_ENCHANT, (double)circleX, (double)circleY, (double)circleZ, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)false);
        }
    }
}

