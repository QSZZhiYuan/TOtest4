/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.animated_particle;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_defeated.NightwardenDefeatedEntity;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

public class DefeatedEntitySpawnTrigger {
    public static void handleBeginAgainAnimatedParticles(NightwardenDefeatedEntity boss, float animProgress, float currentAnimTime) {
        block17: {
            int i;
            double z;
            double x;
            double centerZ;
            double centerY;
            double centerX;
            RandomSource random;
            block19: {
                float angle;
                block18: {
                    block16: {
                        animProgress = Mth.outFromOrigin((float)animProgress, (float)0.0f, (float)1.0f);
                        random = boss.getRandom();
                        centerX = boss.getX();
                        centerY = boss.getY();
                        centerZ = boss.getZ();
                        if (!(animProgress < 0.25f)) break block16;
                        float t = animProgress * 4.0f;
                        float baseRadius = 1.5f * t;
                        int spiralArms = 3;
                        for (int arm = 0; arm < spiralArms; ++arm) {
                            float offset = (float)arm * ((float)Math.PI * 2) / (float)spiralArms;
                            for (int p = 0; p < 14; ++p) {
                                float progress = (float)p / 14.0f;
                                float radius = baseRadius * progress;
                                float angle2 = radius * 3.0f + offset + currentAnimTime * 0.05f;
                                double x2 = centerX + Math.cos(angle2) * (double)radius;
                                double z2 = centerZ + Math.sin(angle2) * (double)radius;
                                double y = centerY + (double)(0.1f * progress);
                                boss.level().addDestroyBlockEffect(TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, x2, y, z2, 0.0, 0.01, 0.0);
                                if (random.nextFloat() < 0.25f) {
                                    boss.level().addDestroyBlockEffect(TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE, x2, y + 0.4, z2, -Math.cos(angle2) * 0.025, -0.045, -Math.sin(angle2) * 0.025);
                                }
                                if (!(random.nextFloat() < 0.1f)) continue;
                                boss.level().addDestroyBlockEffect(TravelopticsParticleHelper.SHORT_LIGHT_RED_GLOWING_ENCHANT, x2, y + 0.3, z2, 0.0, 0.08, 0.0);
                            }
                        }
                        break block17;
                    }
                    if (!(animProgress < 0.5f)) break block18;
                    float t = (animProgress - 0.25f) * 4.0f;
                    float waveLength = 3.0f * t;
                    for (int wave = 0; wave < 5; ++wave) {
                        float angle3 = (float)wave * ((float)Math.PI * 2) / 5.0f;
                        for (int i2 = 0; i2 < 10; ++i2) {
                            float dist = waveLength * (float)i2 / 10.0f;
                            double x3 = centerX + Math.sin(angle3) * (double)dist;
                            double z3 = centerZ + Math.cos(angle3) * (double)dist;
                            double y = centerY + 0.1;
                            boss.level().addDestroyBlockEffect(random.nextFloat() < 0.6f ? TravelopticsParticleHelper.SHORT_LIGHT_RED_GLOWING_ENCHANT : TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE, x3, y, z3, 0.0, 0.015, 0.0);
                            if (!(random.nextFloat() < 0.15f)) continue;
                            boss.level().addDestroyBlockEffect(TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, x3, y + 0.3, z3, (random.nextDouble() - 0.5) * 0.2, 0.1, (random.nextDouble() - 0.5) * 0.2);
                        }
                    }
                    if (!(random.nextFloat() < 0.3f)) break block17;
                    boss.level().addDestroyBlockEffect(TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE, centerX, centerY + 0.3, centerZ, 0.0, 0.035, 0.0);
                    break block17;
                }
                if (!(animProgress < 0.75f)) break block19;
                float t = (animProgress - 0.5f) * 4.0f;
                float spiralRadius = 1.1f - t * 0.5f;
                float columnHeight = 3.2f * t;
                for (int i3 = 0; i3 < 22; ++i3) {
                    float verticalRatio = (float)i3 / 22.0f;
                    angle = verticalRatio * 10.0f + currentAnimTime * 0.1f;
                    float radial = spiralRadius * (1.0f - verticalRatio * 0.6f);
                    double x4 = centerX + Math.cos(angle) * (double)radial;
                    double z4 = centerZ + Math.sin(angle) * (double)radial;
                    double y = centerY + (double)(verticalRatio * columnHeight);
                    ParticleOptions flare = random.nextFloat() < 0.4f ? TravelopticsParticleHelper.SHORT_LIGHT_RED_GLOWING_ENCHANT : TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT;
                    boss.level().addDestroyBlockEffect(flare, x4, y, z4, 0.0, 0.01, 0.0);
                }
                if ((int)currentAnimTime % 5 != 0) break block17;
                float orbitY = (float)(centerY + (double)1.4f + (double)(Mth.outFromOrigin((float)(currentAnimTime * 0.05f)) * 0.3f));
                for (int i4 = 0; i4 < 20; ++i4) {
                    angle = (float)i4 * ((float)Math.PI * 2) / 20.0f;
                    double x5 = centerX + Math.cos(angle) * 1.7;
                    double z5 = centerZ + Math.sin(angle) * 1.7;
                    boss.level().addDestroyBlockEffect(TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, x5, (double)orbitY, z5, 0.0, 0.01, 0.0);
                    if (!(random.nextFloat() < 0.1f)) continue;
                    boss.level().addDestroyBlockEffect(TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE, x5, (double)orbitY + 0.3, z5, 0.0, 0.07, 0.0);
                }
                break block17;
            }
            float t = (animProgress - 0.75f) * 4.0f;
            if (t < 0.5f) {
                float radius = 2.5f * (1.0f - t * 1.2f);
                for (int i5 = 0; i5 < 28; ++i5) {
                    float angle = (float)i5 * ((float)Math.PI * 2) / 28.0f;
                    x = centerX + Math.cos(angle) * (double)radius;
                    z = centerZ + Math.sin(angle) * (double)radius;
                    boss.level().addDestroyBlockEffect(random.nextFloat() < 0.5f ? TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE : TravelopticsParticleHelper.SHORT_LIGHT_RED_GLOWING_ENCHANT, x, centerY + 0.2, z, 0.0, 0.09, 0.0);
                }
            }
            for (i = 0; i < 36; ++i) {
                float angle = random.nextFloat() * ((float)Math.PI * 2);
                float dist = random.nextFloat() * 1.6f;
                x = centerX + Math.cos(angle) * (double)dist;
                z = centerZ + Math.sin(angle) * (double)dist;
                double y = centerY + (double)(random.nextFloat() * 2.8f);
                ParticleOptions particle = switch (random.nextInt(3)) {
                    case 0 -> TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT;
                    case 1 -> TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE;
                    default -> TravelopticsParticleHelper.SHORT_LIGHT_RED_GLOWING_ENCHANT;
                };
                boss.level().addDestroyBlockEffect(particle, x, y, z, 0.0, 0.13 + (double)random.nextFloat() * 0.15, 0.0);
            }
            if (t > 0.85f) {
                for (i = 0; i < 16; ++i) {
                    double x6 = centerX + (random.nextDouble() - 0.5) * 2.4;
                    double y = centerY + 1.0 + random.nextDouble() * 1.8;
                    z = centerZ + (random.nextDouble() - 0.5) * 2.4;
                    boss.level().addDestroyBlockEffect(TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, x6, y, z, 0.0, 0.03, 0.0);
                }
            }
        }
    }
}

