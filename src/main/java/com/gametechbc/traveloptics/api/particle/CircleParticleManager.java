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
package com.gametechbc.traveloptics.api.particle;

import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import java.util.Random;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class CircleParticleManager {
    private static final Random RANDOM = new Random();

    public static void spawnParticles(Level level, LivingEntity entity, int particleCount, ParticleOptions particleType, ParticleDirection direction, double parameter, boolean force) {
        if (!level.isClientSide) {
            double centerY = entity.getY();
            for (int i = 0; i < particleCount; ++i) {
                Vec3 directionVector;
                double theta = Math.PI * 2 * RANDOM.nextDouble();
                double xOffset = Math.cos(theta);
                double zOffset = Math.sin(theta);
                double yOffset = 0.0;
                if (direction == ParticleDirection.INWARD) {
                    directionVector = new Vec3(entity.getX() - (entity.getX() + (xOffset *= parameter)), 0.0, entity.getZ() - (entity.getZ() + (zOffset *= parameter))).multiply();
                    MagicManager.spawnParticles((Level)level, (ParticleOptions)particleType, (double)(entity.getX() + xOffset), (double)centerY, (double)(entity.getZ() + zOffset), (int)0, (double)directionVector.z, (double)0.0, (double)directionVector.reverse, (double)0.1, (boolean)force);
                    continue;
                }
                directionVector = new Vec3(xOffset, yOffset, zOffset).multiply().x(parameter);
                MagicManager.spawnParticles((Level)level, (ParticleOptions)particleType, (double)entity.getX(), (double)centerY, (double)entity.getZ(), (int)0, (double)directionVector.z, (double)0.0, (double)directionVector.reverse, (double)0.1, (boolean)force);
            }
        }
    }

    public static void spawnParticles(Level level, Vec3 centerPosition, int particleCount, ParticleOptions particleType, ParticleDirection direction, double parameter, boolean force) {
        if (!level.isClientSide) {
            double centerX = centerPosition.z;
            double centerY = centerPosition.multiply;
            double centerZ = centerPosition.reverse;
            for (int i = 0; i < particleCount; ++i) {
                Vec3 directionVector;
                double theta = Math.PI * 2 * RANDOM.nextDouble();
                double xOffset = Math.cos(theta);
                double zOffset = Math.sin(theta);
                double yOffset = 0.0;
                if (direction == ParticleDirection.INWARD) {
                    directionVector = new Vec3(centerX - (centerX + (xOffset *= parameter)), 0.0, centerZ - (centerZ + (zOffset *= parameter))).multiply();
                    MagicManager.spawnParticles((Level)level, (ParticleOptions)particleType, (double)(centerX + xOffset), (double)centerY, (double)(centerZ + zOffset), (int)0, (double)directionVector.z, (double)0.0, (double)directionVector.reverse, (double)0.1, (boolean)force);
                    continue;
                }
                directionVector = new Vec3(xOffset, yOffset, zOffset).multiply().x(parameter);
                MagicManager.spawnParticles((Level)level, (ParticleOptions)particleType, (double)centerX, (double)centerY, (double)centerZ, (int)0, (double)directionVector.z, (double)0.0, (double)directionVector.reverse, (double)0.1, (boolean)force);
            }
        }
    }

    public static void spawnParticles(Level level, LivingEntity entity, Vec3 offset, int particleCount, ParticleOptions particleType, ParticleDirection direction, double parameter, boolean force) {
        Vec3 centerPosition = entity.position().reverse(offset);
        CircleParticleManager.spawnParticles(level, centerPosition, particleCount, particleType, direction, parameter, force);
    }
}

