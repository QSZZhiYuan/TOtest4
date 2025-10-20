/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.animated_particle;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenBossEntity;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.Level;

public class NightwardenDragonSurgeAnimatedParticle {
    public static void dragonSurgeChargingAnimation(NightwardenBossEntity nightwarden, double progress, int meleeAnimTimer) {
        ParticleOptions particle = meleeAnimTimer <= 74 ? TravelopticsParticleHelper.LIGHT_RED_GLOWING_ENCHANT : TravelopticsParticleHelper.LIGHT_PURPLE_GLOWING_ENCHANT;
        NightwardenDragonSurgeAnimatedParticle.createEnergyPillars(nightwarden, progress, particle);
        NightwardenDragonSurgeAnimatedParticle.createDragonConstellation(nightwarden, progress, particle);
        NightwardenDragonSurgeAnimatedParticle.createMassiveVortex(nightwarden, progress, particle);
        NightwardenDragonSurgeAnimatedParticle.createEnergyBeams(nightwarden, progress, particle);
        NightwardenDragonSurgeAnimatedParticle.createGroundRunes(nightwarden, progress, particle);
    }

    private static void createEnergyPillars(NightwardenBossEntity nightwarden, double progress, ParticleOptions particle) {
        int pillarCount = 5;
        double pillarRadius = 8.0 + progress * 2.0;
        double pillarHeight = 8.0 + progress * 4.0;
        for (int p = 0; p < pillarCount; ++p) {
            double angle = (double)p / (double)pillarCount * Math.PI * 2.0 + progress * Math.PI * 0.5;
            double pillarX = nightwarden.getX() + pillarRadius * Math.cos(angle);
            double pillarZ = nightwarden.getZ() + pillarRadius * Math.sin(angle);
            int heightSegments = (int)(pillarHeight * progress / 2.5);
            for (int h = 0; h < heightSegments; ++h) {
                double y = nightwarden.getY() + (double)h * 0.9;
                double pillarWidth = 1.0 - (double)h / pillarHeight * 0.7;
                for (int i = 0; i < 5; ++i) {
                    double ringAngle = (double)i * Math.PI * 2.0 / 5.0;
                    double x = pillarX + pillarWidth * Math.cos(ringAngle);
                    double z = pillarZ + pillarWidth * Math.sin(ringAngle);
                    MagicManager.spawnParticles((Level)nightwarden.level(), (ParticleOptions)particle, (double)x, (double)y, (double)z, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.3, (boolean)true);
                }
            }
        }
    }

    private static void createDragonConstellation(NightwardenBossEntity nightwarden, double progress, ParticleOptions particle) {
        double skyHeight = 8.0 + progress * 2.0;
        double dragonLength = 10.0;
        int bodySegments = (int)(progress * 16.0);
        for (int i = 0; i < bodySegments; ++i) {
            double t = (double)i / 16.0;
            double bodyProgress = t * progress;
            double x = nightwarden.getX() + dragonLength * Math.cos(bodyProgress * Math.PI * 1.5) * bodyProgress * 0.8;
            double z = nightwarden.getZ() + dragonLength * Math.sin(bodyProgress * Math.PI * 1.5) * bodyProgress * 0.8;
            double y = nightwarden.getY() + skyHeight + Math.sin(bodyProgress * Math.PI * 3.0) * 1.5;
            for (int layer = 0; layer < 2; ++layer) {
                double layerRadius = (double)(2 - layer) * 0.6;
                for (int ring = 0; ring < 5; ++ring) {
                    double ringAngle = (double)ring * Math.PI * 2.0 / 5.0;
                    double bodyX = x + layerRadius * Math.cos(ringAngle);
                    double bodyZ = z + layerRadius * Math.sin(ringAngle);
                    MagicManager.spawnParticles((Level)nightwarden.level(), (ParticleOptions)particle, (double)bodyX, (double)y, (double)bodyZ, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.4, (boolean)true);
                }
            }
        }
        if (progress > 0.4) {
            double headSize = 2.0 + progress;
            double headX = nightwarden.getX() + dragonLength * progress * 0.7;
            double headZ = nightwarden.getZ() + dragonLength * progress * 0.5;
            double headY = nightwarden.getY() + skyHeight + 1.0;
            for (int i = 0; i < 6; ++i) {
                for (int j = 0; j < 6; ++j) {
                    double u = (double)i / 5.0 - 0.5;
                    double v = (double)j / 5.0 - 0.5;
                    if (!(Math.abs(u) + Math.abs(v) < 0.35)) continue;
                    double x = headX + u * headSize * 2.0;
                    double z = headZ + v * headSize * 2.0;
                    double y = headY + Math.sin(u * Math.PI) * Math.sin(v * Math.PI) * 1.0;
                    MagicManager.spawnParticles((Level)nightwarden.level(), (ParticleOptions)particle, (double)x, (double)y, (double)z, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.5, (boolean)true);
                }
            }
        }
    }

    private static void createMassiveVortex(NightwardenBossEntity nightwarden, double progress, ParticleOptions particle) {
        int vortexLayers = 6;
        double maxRadius = 9.0;
        double vortexHeight = 10.0;
        for (int layer = 0; layer < vortexLayers; ++layer) {
            double layerHeight = (double)layer / (double)vortexLayers * vortexHeight * progress;
            double layerRadius = maxRadius * (1.0 - (double)layer / (double)vortexLayers) * (0.5 + progress * 0.5);
            int particlesPerLayer = 10 + layer * 2;
            for (int i = 0; i < particlesPerLayer; ++i) {
                double angle = (double)i / (double)particlesPerLayer * Math.PI * 2.0 + progress * Math.PI * 4.0 + (double)layer * 0.5;
                double x = nightwarden.getX() + layerRadius * Math.cos(angle);
                double z = nightwarden.getZ() + layerRadius * Math.sin(angle);
                double y = nightwarden.getY() + layerHeight;
                MagicManager.spawnParticles((Level)nightwarden.level(), (ParticleOptions)particle, (double)x, (double)y, (double)z, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.25, (boolean)true);
            }
        }
    }

    private static void createEnergyBeams(NightwardenBossEntity nightwarden, double progress, ParticleOptions particle) {
        int beamCount = 5;
        double beamLength = 10.0 + progress;
        for (int b = 0; b < beamCount; ++b) {
            double beamAngle = (double)b / (double)beamCount * Math.PI * 2.0 + progress * Math.PI;
            double beamSegments = beamLength * progress;
            int s = 0;
            while ((double)s < beamSegments) {
                if (s % 3 != 0) {
                    double distance = (double)s * 0.8;
                    double x = nightwarden.getX() + distance * Math.cos(beamAngle);
                    double z = nightwarden.getZ() + distance * Math.sin(beamAngle);
                    double y = nightwarden.getY() + 1.0 + Math.sin(distance * 0.4) * 1.5;
                    for (int thickness = 0; thickness < 2; ++thickness) {
                        double offset = (double)thickness * 0.4;
                        MagicManager.spawnParticles((Level)nightwarden.level(), (ParticleOptions)particle, (double)x, (double)(y + offset), (double)z, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.3, (boolean)true);
                    }
                }
                ++s;
            }
        }
    }

    private static void createGroundRunes(NightwardenBossEntity nightwarden, double progress, ParticleOptions particle) {
        double runeRadius = 8.0 + progress * 3.0;
        int runeCount = 6;
        for (int r = 0; r < runeCount; ++r) {
            double runeAngle = (double)r / (double)runeCount * Math.PI * 2.0;
            double runeX = nightwarden.getX() + runeRadius * Math.cos(runeAngle);
            double runeZ = nightwarden.getZ() + runeRadius * Math.sin(runeAngle);
            double runeY = nightwarden.getY() - 1.0;
            for (int i = 0; i < 5; ++i) {
                double patternAngle = (double)i * Math.PI * 2.0 / 5.0 + progress * Math.PI * 2.0;
                double patternRadius = 1.5 + Math.sin(progress * Math.PI * 2.0) * 0.3;
                double x = runeX + patternRadius * Math.cos(patternAngle);
                double z = runeZ + patternRadius * Math.sin(patternAngle);
                MagicManager.spawnParticles((Level)nightwarden.level(), (ParticleOptions)particle, (double)x, (double)runeY, (double)z, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.4, (boolean)true);
            }
        }
    }
}

