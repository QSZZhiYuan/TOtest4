/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.entity.projectiles.aqua_vortex;

import java.util.Random;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class AquaVortexPullParticleEffect {
    private final Entity entity;
    private final Level level;
    private final Random random = new Random();

    public AquaVortexPullParticleEffect(Entity entity) {
        this.entity = entity;
        this.level = entity.level();
    }

    public void spawnPullParticles() {
        double maxRadius = 7.5;
        double verticalPullSpeed = 0.16;
        double inwardPullSpeed = 0.05;
        int particleCount = 13;
        for (int i = 0; i < particleCount; ++i) {
            double spawnRadius = this.random.nextDouble() * maxRadius;
            double angle = this.random.nextDouble() * 2.0 * Math.PI;
            double x = this.entity.getX() + spawnRadius * (double)Mth.randomBetween((float)((float)angle));
            double z = this.entity.getZ() + spawnRadius * (double)Mth.outFromOrigin((float)((float)angle));
            double y = this.entity.getY() + 0.5 + this.random.nextDouble() * 2.0 - 1.0;
            double xVelocity = (this.entity.getX() - x) * inwardPullSpeed;
            double zVelocity = (this.entity.getZ() - z) * inwardPullSpeed;
            double yVelocity = verticalPullSpeed + this.random.nextDouble() * 0.05;
            this.level.addDestroyBlockEffect((ParticleOptions)ParticleTypes.POOF, x, y, z, xVelocity, yVelocity, zVelocity);
        }
    }
}

