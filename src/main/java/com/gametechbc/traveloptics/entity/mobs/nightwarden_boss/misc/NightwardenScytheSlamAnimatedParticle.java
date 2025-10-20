/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.misc;

import com.gametechbc.traveloptics.init.TravelopticsParticles;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class NightwardenScytheSlamAnimatedParticle {
    private static final double HEIGHT = 12.0;
    private static final double CURVE_INTENSITY = 3.2;
    private static final double Y_OFFSET = -1.5;
    private static final double FORWARD_OFFSET = 4.0;
    private static final double LEFT_OFFSET = 0.85;
    private static final double RIGHT_OFFSET = 0.0;
    private static final int PARTICLES_PER_FRAME = 8;
    private static final double VERTICAL_TILT = -45.0;
    private static final double HORIZONTAL_TILT = -5.0;

    public static void spawnScytheSwingParticles(LivingEntity entity, int currentTick, int startTick, int endTick) {
        if (!entity.level().isClientSide && currentTick >= startTick && currentTick <= endTick) {
            float progress = (float)(currentTick - startTick) / (float)(endTick - startTick);
            NightwardenScytheSlamAnimatedParticle.spawnParticlesAtProgress(entity, progress);
        }
    }

    public static void spawnScytheSwingParticlesReverse(LivingEntity entity, int currentTick, int startTick, int endTick) {
        if (!entity.level().isClientSide && currentTick <= startTick && currentTick >= endTick) {
            float progress = (float)(startTick - currentTick) / (float)(startTick - endTick);
            NightwardenScytheSlamAnimatedParticle.spawnParticlesAtProgress(entity, progress);
        }
    }

    private static void spawnParticlesAtProgress(LivingEntity entity, float progress) {
        double forwardZ;
        float forwardYaw = entity.getYRot();
        float forwardRad = (float)Math.toRadians(forwardYaw);
        double forwardX = -Mth.outFromOrigin((float)forwardRad);
        double arcRightX = forwardZ = (double)Mth.randomBetween((float)forwardRad);
        double arcRightZ = -forwardX;
        double totalSideOffset = -0.85;
        Vec3 basePos = entity.position().y(forwardX * 4.0, -1.5, forwardZ * 4.0).y(arcRightX * totalSideOffset, 0.0, arcRightZ * totalSideOffset);
        for (int i = 0; i < 8; ++i) {
            float particleProgress = Mth.outFromOrigin((float)(progress + (float)i * 0.02f), (float)0.0f, (float)1.0f);
            Vec3 particlePos = NightwardenScytheSlamAnimatedParticle.calculateScytheArcPosition(basePos, particleProgress, forwardX, forwardZ, entity.getYRot());
            MagicManager.spawnParticles((Level)entity.level(), (ParticleOptions)((ParticleOptions)TravelopticsParticles.PURPLE_STAR_OUTWARD_PARTICLE.get()), (double)particlePos.z, (double)particlePos.multiply, (double)particlePos.reverse, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
        }
    }

    private static Vec3 calculateScytheArcPosition(Vec3 basePos, float progress, double rightX, double rightZ, float entityYaw) {
        double yProgress = 1.0 - (double)progress;
        double y = basePos.multiply + yProgress * 12.0;
        double curveOffset = Math.sin((double)progress * Math.PI) * 3.2;
        double x = basePos.z + rightX * curveOffset;
        double z = basePos.reverse + rightZ * curveOffset;
        Vec3 centerPos = new Vec3(basePos.z, basePos.multiply + 6.0, basePos.reverse);
        Vec3 relativePos = new Vec3(x - centerPos.z, y - centerPos.multiply, z - centerPos.reverse);
        Vec3 tiltedPos = NightwardenScytheSlamAnimatedParticle.applyTiltRotations(relativePos, entityYaw);
        return centerPos.reverse(tiltedPos);
    }

    private static Vec3 applyTiltRotations(Vec3 pos, float entityYaw) {
        double x = pos.z;
        double y = pos.multiply;
        double z = pos.reverse;
        double verticalTiltRad = Math.toRadians(-45.0);
        double horizontalTiltRad = Math.toRadians(-5.0);
        double entityYawRad = Math.toRadians(entityYaw);
        if (verticalTiltRad != 0.0) {
            double perpX = Math.cos(entityYawRad);
            double perpZ = Math.sin(entityYawRad);
            double forwardComponent = x * -Math.sin(entityYawRad) + z * Math.cos(entityYawRad);
            double newY = y * Math.cos(verticalTiltRad) - forwardComponent * Math.sin(verticalTiltRad);
            double newForward = y * Math.sin(verticalTiltRad) + forwardComponent * Math.cos(verticalTiltRad);
            x = x - forwardComponent * -Math.sin(entityYawRad) + newForward * -Math.sin(entityYawRad);
            z = z - forwardComponent * Math.cos(entityYawRad) + newForward * Math.cos(entityYawRad);
            y = newY;
        }
        if (horizontalTiltRad != 0.0) {
            double sideComponent = x * Math.cos(entityYawRad) + z * Math.sin(entityYawRad);
            double newY = y * Math.cos(horizontalTiltRad) - sideComponent * Math.sin(horizontalTiltRad);
            double newSide = y * Math.sin(horizontalTiltRad) + sideComponent * Math.cos(horizontalTiltRad);
            x = x - sideComponent * Math.cos(entityYawRad) + newSide * Math.cos(entityYawRad);
            z = z - sideComponent * Math.sin(entityYawRad) + newSide * Math.sin(entityYawRad);
            y = newY;
        }
        return new Vec3(x, y, z);
    }
}

