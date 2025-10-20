/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.animated_particle;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenBossEntity;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class NightwardenPhaseThreeSwapAnimatedParticle {
    public static void handlePhaseTwoToThreeAnimatedParticles(NightwardenBossEntity boss, int elapsed) {
        if (!boss.level().isClientSide) {
            double yDir;
            double phi;
            Vec3 forward = boss.getLookAngle().multiply();
            Vec3 particleCenter = boss.position().reverse(forward.x(1.4)).y(0.0, 3.0, 0.0);
            RandomSource random = boss.getRandom();
            if (elapsed >= 165 && elapsed <= 220) {
                int i;
                for (i = 0; i < 8; ++i) {
                    double angle = (double)i * Math.PI / 4.0 + (double)(elapsed - 165) * 0.02;
                    double radius = 7.0 - (double)(elapsed - 165) * 0.06;
                    if (radius < 1.0) {
                        radius = 1.0;
                    }
                    double xPos = boss.getX() + Math.cos(angle) * radius;
                    double zPos = boss.getZ() + Math.sin(angle) * radius;
                    BlockPos groundPos = new BlockPos((int)xPos, (int)boss.getY() - 1, (int)zPos);
                    while (boss.level().isEmptyBlock(groundPos) && groundPos.getY() > boss.level().getMinBuildHeight()) {
                        groundPos = groundPos.below();
                    }
                    double startY = (double)groundPos.getY() + 1.0;
                    double yOffset = Math.min(3.0, (double)(elapsed - 165) * 0.15);
                    Vec3 particlePos = new Vec3(xPos, startY + yOffset, zPos);
                    Vec3 direction = particleCenter.multiply(particlePos).multiply().x(0.18);
                    if (i % 3 == 0) {
                        MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_GOLD_GLOWING_ENCHANT, (double)particlePos.z, (double)particlePos.multiply, (double)particlePos.reverse, (int)0, (double)direction.z, (double)direction.multiply, (double)direction.reverse, (double)0.02, (boolean)true);
                        continue;
                    }
                    if (i % 3 == 1) {
                        MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, (double)particlePos.z, (double)particlePos.multiply, (double)particlePos.reverse, (int)0, (double)direction.z, (double)direction.multiply, (double)direction.reverse, (double)0.02, (boolean)true);
                        continue;
                    }
                    MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_RED_GLOWING_ENCHANT, (double)particlePos.z, (double)particlePos.multiply, (double)particlePos.reverse, (int)0, (double)direction.z, (double)direction.multiply, (double)direction.reverse, (double)0.02, (boolean)true);
                }
                for (i = 0; i < 2; ++i) {
                    double offsetX = random.nextDouble() * 0.6 - 0.3;
                    double offsetY = random.nextDouble() * 0.6 - 0.3;
                    double offsetZ = random.nextDouble() * 0.6 - 0.3;
                    MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_GOLD_GLOWING_ENCHANT, (double)(particleCenter.z + offsetX), (double)(particleCenter.multiply + offsetY), (double)(particleCenter.reverse + offsetZ), (int)0, (double)0.0, (double)0.0, (double)0.0, (double)0.01, (boolean)true);
                }
            }
            if (elapsed >= 221 && elapsed <= 290) {
                int spiralCount = 5;
                for (int spiral = 0; spiral < spiralCount; ++spiral) {
                    double spiralOffset = Math.PI * 2 / (double)spiralCount * (double)spiral;
                    double theta = (double)(elapsed - 221) * 0.22 + spiralOffset;
                    double progressFactor = (double)(elapsed - 221) / 70.0;
                    double radius = 4.0 - progressFactor * 2.0;
                    if (radius < 0.5) {
                        radius = 0.5;
                    }
                    double heightOffset = Math.sin((double)(elapsed - 221) * 0.15) * 0.7;
                    double xPos = particleCenter.z + Math.cos(theta) * radius;
                    double yPos = particleCenter.multiply + heightOffset;
                    double zPos = particleCenter.reverse + Math.sin(theta) * radius;
                    Vec3 particlePos = new Vec3(xPos, yPos, zPos);
                    Vec3 direction = particleCenter.multiply(particlePos).multiply().x(0.1);
                    if (spiral % 3 == 0) {
                        MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.LIGHT_PURPLE_GLOWING_ENCHANT, (double)xPos, (double)yPos, (double)zPos, (int)0, (double)direction.z, (double)direction.multiply, (double)direction.reverse, (double)0.015, (boolean)true);
                    } else if (spiral % 3 == 1) {
                        MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.LIGHT_GOLD_GLOWING_ENCHANT, (double)xPos, (double)yPos, (double)zPos, (int)0, (double)direction.z, (double)direction.multiply, (double)direction.reverse, (double)0.015, (boolean)true);
                    } else {
                        MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.LIGHT_RED_GLOWING_ENCHANT, (double)xPos, (double)yPos, (double)zPos, (int)0, (double)direction.z, (double)direction.multiply, (double)direction.reverse, (double)0.015, (boolean)true);
                    }
                    MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_GOLD_GLOWING_ENCHANT, (double)xPos, (double)yPos, (double)zPos, (int)0, (double)0.0, (double)0.0, (double)0.0, (double)0.01, (boolean)true);
                }
                if (random.nextInt(5) < Math.min(4, (elapsed - 221) / 8)) {
                    double offset = 0.8;
                    double sparkX = particleCenter.z + (random.nextDouble() * offset * 2.0 - offset);
                    double sparkY = particleCenter.multiply + (random.nextDouble() * offset * 2.0 - offset);
                    double sparkZ = particleCenter.reverse + (random.nextDouble() * offset * 2.0 - offset);
                    MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.LIGHT_RED_GLOWING_ENCHANT, (double)sparkX, (double)sparkY, (double)sparkZ, (int)0, (double)0.0, (double)0.0, (double)0.0, (double)0.02, (boolean)true);
                }
            }
            if (elapsed >= 291 && elapsed <= 345) {
                int i;
                double pulsePhase = (double)(elapsed - 291) / 4.0;
                double pulseSize = 0.4 + 0.3 * Math.sin(pulsePhase);
                int particleCount = 12 + (elapsed - 291) / 8;
                for (i = 0; i < particleCount; ++i) {
                    double orbitSpeed = 0.4 + (double)(elapsed - 291) / 70.0;
                    double angle = (double)i * (Math.PI * 2 / (double)particleCount) + (double)elapsed * orbitSpeed * 0.06;
                    double shrinkFactor = Math.max(0.5, 1.0 - (double)(elapsed - 291) / 54.0 * 0.5);
                    double orbitRadius = 1.5 * shrinkFactor;
                    double xPos = particleCenter.z + Math.cos(angle) * orbitRadius;
                    double yPos = particleCenter.multiply + Math.sin(angle) * orbitRadius * 0.5;
                    double zPos = particleCenter.reverse + Math.sin(angle) * orbitRadius;
                    double tangentX = -Math.sin(angle) * 0.07 * orbitSpeed;
                    double tangentY = Math.cos(angle) * 0.035 * orbitSpeed;
                    double tangentZ = Math.cos(angle) * 0.07 * orbitSpeed;
                    if (i % 3 == 0) {
                        MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.LIGHT_PURPLE_GLOWING_ENCHANT, (double)xPos, (double)yPos, (double)zPos, (int)0, (double)tangentX, (double)tangentY, (double)tangentZ, (double)0.015, (boolean)true);
                        continue;
                    }
                    if (i % 3 == 1) {
                        MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.LIGHT_GOLD_GLOWING_ENCHANT, (double)xPos, (double)yPos, (double)zPos, (int)0, (double)tangentX, (double)tangentY, (double)tangentZ, (double)0.015, (boolean)true);
                        continue;
                    }
                    MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.LIGHT_RED_GLOWING_ENCHANT, (double)xPos, (double)yPos, (double)zPos, (int)0, (double)tangentX, (double)tangentY, (double)tangentZ, (double)0.015, (boolean)true);
                }
                for (i = 0; i < 3; ++i) {
                    double coreX = particleCenter.z + (random.nextDouble() * pulseSize * 2.0 - pulseSize);
                    double coreY = particleCenter.multiply + (random.nextDouble() * pulseSize * 2.0 - pulseSize);
                    double coreZ = particleCenter.reverse + (random.nextDouble() * pulseSize * 2.0 - pulseSize);
                    if (i % 3 == 0) {
                        MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, (double)coreX, (double)coreY, (double)coreZ, (int)0, (double)0.0, (double)0.0, (double)0.0, (double)0.015, (boolean)true);
                        continue;
                    }
                    if (i % 3 == 1) {
                        MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_GOLD_GLOWING_ENCHANT, (double)coreX, (double)coreY, (double)coreZ, (int)0, (double)0.0, (double)0.0, (double)0.0, (double)0.015, (boolean)true);
                        continue;
                    }
                    MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_RED_GLOWING_ENCHANT, (double)coreX, (double)coreY, (double)coreZ, (int)0, (double)0.0, (double)0.0, (double)0.0, (double)0.015, (boolean)true);
                }
                if (elapsed >= 310) {
                    int tendrilCount = Math.min(8, (elapsed - 310) / 4);
                    for (int i2 = 0; i2 < tendrilCount; ++i2) {
                        double theta = Math.PI * 2 * random.nextDouble();
                        double phi2 = Math.acos(2.0 * random.nextDouble() - 1.0);
                        double tendrilLength = 0.8 + random.nextDouble() * 0.6;
                        double xDir = Math.sin(phi2) * Math.cos(theta) * tendrilLength;
                        double yDir2 = Math.sin(phi2) * Math.sin(theta) * tendrilLength;
                        double zDir = Math.cos(phi2) * tendrilLength;
                        int segments = 6;
                        for (int seg = 0; seg < segments; ++seg) {
                            double factor = (double)seg / (double)segments;
                            double xPos = particleCenter.z + xDir * factor;
                            double yPos = particleCenter.multiply + yDir2 * factor;
                            double zPos = particleCenter.reverse + zDir * factor;
                            if (i2 % 3 == 0) {
                                MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, (double)xPos, (double)yPos, (double)zPos, (int)0, (double)0.0, (double)0.0, (double)0.0, (double)0.01, (boolean)true);
                                continue;
                            }
                            if (i2 % 3 == 1) {
                                MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_GOLD_GLOWING_ENCHANT, (double)xPos, (double)yPos, (double)zPos, (int)0, (double)0.0, (double)0.0, (double)0.0, (double)0.01, (boolean)true);
                                continue;
                            }
                            MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_RED_GLOWING_ENCHANT, (double)xPos, (double)yPos, (double)zPos, (int)0, (double)0.0, (double)0.0, (double)0.0, (double)0.01, (boolean)true);
                        }
                    }
                }
            }
            if (elapsed == 346) {
                for (int i = 0; i < 80; ++i) {
                    double theta = Math.PI * 2 * random.nextDouble();
                    phi = Math.acos(2.0 * random.nextDouble() - 1.0);
                    double xDir = Math.sin(phi) * Math.cos(theta);
                    yDir = Math.sin(phi) * Math.sin(theta);
                    double zDir = Math.cos(phi);
                    Vec3 dir = new Vec3(xDir, yDir, zDir).multiply().x(0.3);
                    if (i % 3 == 0) {
                        MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, (double)particleCenter.z, (double)particleCenter.multiply, (double)particleCenter.reverse, (int)0, (double)dir.z, (double)dir.multiply, (double)dir.reverse, (double)0.05, (boolean)true);
                        continue;
                    }
                    if (i % 3 == 1) {
                        MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_GOLD_GLOWING_ENCHANT, (double)particleCenter.z, (double)particleCenter.multiply, (double)particleCenter.reverse, (int)0, (double)dir.z, (double)dir.multiply, (double)dir.reverse, (double)0.05, (boolean)true);
                        continue;
                    }
                    MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_RED_GLOWING_ENCHANT, (double)particleCenter.z, (double)particleCenter.multiply, (double)particleCenter.reverse, (int)0, (double)dir.z, (double)dir.multiply, (double)dir.reverse, (double)0.05, (boolean)true);
                }
            }
            if (elapsed == 355) {
                for (int i = 0; i < 100; ++i) {
                    double theta = Math.PI * 2 * random.nextDouble();
                    phi = Math.acos(2.0 * random.nextDouble() - 1.0);
                    double xDir = Math.sin(phi) * Math.cos(theta);
                    yDir = Math.sin(phi) * Math.sin(theta);
                    double zDir = Math.cos(phi);
                    Vec3 dir = new Vec3(xDir, yDir, zDir).multiply().x(0.4);
                    if (i % 3 == 0) {
                        MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_GOLD_GLOWING_ENCHANT, (double)particleCenter.z, (double)particleCenter.multiply, (double)particleCenter.reverse, (int)0, (double)dir.z, (double)dir.multiply, (double)dir.reverse, (double)0.05, (boolean)true);
                        continue;
                    }
                    if (i % 3 == 1) {
                        MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, (double)particleCenter.z, (double)particleCenter.multiply, (double)particleCenter.reverse, (int)0, (double)dir.z, (double)dir.multiply, (double)dir.reverse, (double)0.05, (boolean)true);
                        continue;
                    }
                    MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_RED_GLOWING_ENCHANT, (double)particleCenter.z, (double)particleCenter.multiply, (double)particleCenter.reverse, (int)0, (double)dir.z, (double)dir.multiply, (double)dir.reverse, (double)0.05, (boolean)true);
                }
                int ringParticles = 50;
                for (int i = 0; i < ringParticles; ++i) {
                    double angle = (double)(i * 2) * Math.PI / (double)ringParticles;
                    double xDir = Math.cos(angle) * 0.45;
                    double yDir3 = 0.05;
                    double zDir = Math.sin(angle) * 0.45;
                    MagicManager.spawnParticles((Level)boss.level(), (ParticleOptions)TravelopticsParticleHelper.LIGHT_RED_GLOWING_ENCHANT, (double)particleCenter.z, (double)particleCenter.multiply, (double)particleCenter.reverse, (int)0, (double)xDir, (double)yDir3, (double)zDir, (double)0.05, (boolean)true);
                }
            }
        }
    }
}

