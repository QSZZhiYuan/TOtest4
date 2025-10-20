/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 */
package com.gametechbc.traveloptics.api.particle;

import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class JetFlamesParticleManager {
    public static void createJetFlamesBelow(Level level, Entity entity, ParticleOptions[] particleOptions, int[] spawnPercentages, int totalParticles, double baseMotionY, double motionYVariation, boolean force) {
        if (!level.isClientSide) {
            if (particleOptions.length != spawnPercentages.length) {
                throw new IllegalArgumentException("Particle options and spawn percentages arrays must have same length");
            }
            if (particleOptions.length < 1 || particleOptions.length > 3) {
                throw new IllegalArgumentException("Must have 1-3 particle types");
            }
            RandomSource random = level.getRandom();
            for (int i = 0; i < totalParticles; ++i) {
                int randomValue = random.nextInt(100);
                ParticleOptions selectedParticle = null;
                int cumulativePercentage = 0;
                for (int j = 0; j < particleOptions.length; ++j) {
                    if (randomValue >= (cumulativePercentage += spawnPercentages[j])) continue;
                    selectedParticle = particleOptions[j];
                    break;
                }
                if (selectedParticle == null) continue;
                AABB boundingBox = entity.getBoundingBox();
                double x = boundingBox.ofSize + (boundingBox.getZsize - boundingBox.ofSize) * random.nextDouble();
                double y = boundingBox.clip + (boundingBox.hasNaN - boundingBox.clip) * random.nextDouble();
                double z = boundingBox.getYsize + (boundingBox.getCenter - boundingBox.getYsize) * random.nextDouble();
                double motionX = 0.0;
                double motionY = baseMotionY - random.nextDouble() * motionYVariation;
                double motionZ = 0.0;
                MagicManager.spawnParticles((Level)level, (ParticleOptions)selectedParticle, (double)entity.getX(), (double)entity.getY(), (double)entity.getZ(), (int)1, (double)motionX, (double)motionY, (double)motionZ, (double)0.03, (boolean)force);
            }
        }
    }

    public static void createJetFlamesBelowDefaulted(Level level, Entity entity, ParticleOptions[] particleOptions, int[] spawnPercentages, int totalParticles, boolean force) {
        JetFlamesParticleManager.createJetFlamesBelow(level, entity, particleOptions, spawnPercentages, totalParticles, -0.4, 0.2, force);
    }
}

