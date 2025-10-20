/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.api.particle;

import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import java.util.Random;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class AdvancedCylinderParticleManager {
    private static final Random RANDOM = new Random();

    public static void spawnParticles(Level level, Vec3 position, int particleCount, ParticleOptions particleType, ParticleDirection direction, double radius, double height, double xOffset, double yOffset, double zOffset, double speed, boolean force) {
        if (!level.isClientSide) {
            double baseX = position.x() + xOffset;
            double baseY = position.y() + yOffset;
            double baseZ = position.z() + zOffset;
            for (int i = 0; i < particleCount; ++i) {
                double theta = Math.PI * 2 * RANDOM.nextDouble();
                double yPosition = baseY + RANDOM.nextDouble() * height;
                double xParticleOffset = radius * Math.cos(theta);
                double zParticleOffset = radius * Math.sin(theta);
                Vec3 directionVector = direction == ParticleDirection.UPWARD ? new Vec3(0.0, 1.0, 0.0).multiply() : (direction == ParticleDirection.DOWNWARD ? new Vec3(0.0, -1.0, 0.0).multiply() : (direction == ParticleDirection.INWARD ? new Vec3(-xParticleOffset, 0.0, -zParticleOffset).multiply() : new Vec3(xParticleOffset, 0.0, zParticleOffset).multiply()));
                directionVector = directionVector.x(speed);
                MagicManager.spawnParticles((Level)level, (ParticleOptions)particleType, (double)(baseX + xParticleOffset), (double)yPosition, (double)(baseZ + zParticleOffset), (int)0, (double)directionVector.z, (double)directionVector.multiply, (double)directionVector.reverse, (double)0.1, (boolean)force);
            }
        }
    }
}

