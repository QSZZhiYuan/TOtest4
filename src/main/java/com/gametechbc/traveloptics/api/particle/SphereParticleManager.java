/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.api.particle;

import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import java.util.Random;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class SphereParticleManager {
    private static final Random RANDOM = new Random();

    public static void spawnParticles(Level level, Entity entity, int particleCount, ParticleOptions particleType, ParticleDirection direction, double parameter) {
        if (!level.isClientSide) {
            double centerY = entity.getY() + (double)entity.getBbHeight() * 0.5;
            for (int i = 0; i < particleCount; ++i) {
                Vec3 directionVector;
                double theta = Math.PI * 2 * RANDOM.nextDouble();
                double phi = Math.acos(2.0 * RANDOM.nextDouble() - 1.0);
                double xOffset = Math.sin(phi) * Math.cos(theta);
                double yOffset = Math.sin(phi) * Math.sin(theta);
                double zOffset = Math.cos(phi);
                if (direction == ParticleDirection.INWARD) {
                    directionVector = new Vec3(entity.getX() - (entity.getX() + (xOffset *= parameter)), centerY - (centerY + (yOffset *= parameter)), entity.getZ() - (entity.getZ() + (zOffset *= parameter))).multiply();
                    MagicManager.spawnParticles((Level)level, (ParticleOptions)particleType, (double)(entity.getX() + xOffset), (double)(centerY + yOffset), (double)(entity.getZ() + zOffset), (int)0, (double)directionVector.z, (double)directionVector.multiply, (double)directionVector.reverse, (double)0.1, (boolean)true);
                    continue;
                }
                directionVector = new Vec3(xOffset, yOffset, zOffset).multiply().x(parameter);
                MagicManager.spawnParticles((Level)level, (ParticleOptions)particleType, (double)entity.getX(), (double)centerY, (double)entity.getZ(), (int)0, (double)directionVector.z, (double)directionVector.multiply, (double)directionVector.reverse, (double)0.1, (boolean)true);
            }
        }
    }
}

