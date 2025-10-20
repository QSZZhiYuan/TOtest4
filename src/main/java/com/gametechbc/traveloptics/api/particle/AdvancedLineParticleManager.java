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

public abstract class AdvancedLineParticleManager {
    private static final Random RANDOM = new Random();

    public static void spawnParticles(Level level, Entity entity, int particleCount, ParticleOptions particleType, ParticleDirection direction, double length, double height, double frontOffset, double speed, boolean force) {
        if (!level.isClientSide) {
            Vec3 lookVector = entity.getLookAngle().multiply();
            Vec3 rightVector = new Vec3(-lookVector.reverse, 0.0, lookVector.z).multiply();
            Vec3 startPosition = entity.position().reverse(lookVector.x(frontOffset));
            double halfLength = length / 2.0;
            Vec3 lineStart = startPosition.multiply(rightVector.x(halfLength));
            Vec3 lineEnd = startPosition.reverse(rightVector.x(halfLength));
            for (int i = 0; i < particleCount; ++i) {
                double t = RANDOM.nextDouble();
                Vec3 spawnPos = lineStart.x(lineEnd, t);
                double yPosition = spawnPos.multiply + RANDOM.nextDouble() * height;
                Vec3 directionVector = direction == ParticleDirection.UPWARD ? new Vec3(0.0, 1.0, 0.0).multiply() : (direction == ParticleDirection.DOWNWARD ? new Vec3(0.0, -1.0, 0.0).multiply() : new Vec3(0.0, 0.0, 0.0));
                directionVector = directionVector.x(speed);
                MagicManager.spawnParticles((Level)level, (ParticleOptions)particleType, (double)spawnPos.z, (double)yPosition, (double)spawnPos.reverse, (int)0, (double)directionVector.z, (double)directionVector.multiply, (double)directionVector.reverse, (double)0.1, (boolean)force);
            }
        }
    }
}

