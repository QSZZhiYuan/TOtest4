/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.world.phys.AABB
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.animated_particle;

import com.gametechbc.traveloptics.entity.mobs.fading_mage.FadingMageEntity;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.AABB;

public class FadingMageIdle {
    public static void spawnFadingParticles(FadingMageEntity entity, double particleSpeed, double upwardTilt, double sidewaysTilt, double backwardOffset, int particleCount, int nextInt) {
        AABB boundingBox = entity.getBoundingBox();
        double width = boundingBox.clip();
        double height = boundingBox.getYsize();
        double depth = boundingBox.getZsize();
        float entityYRot = entity.getYRot();
        float flowAngle = entityYRot + (float)backwardOffset;
        double flowRadians = Math.toRadians(flowAngle);
        double baseVelX = -Math.sin(flowRadians) * particleSpeed;
        double baseVelY = upwardTilt;
        double baseVelZ = Math.cos(flowRadians) * particleSpeed;
        if (sidewaysTilt != 0.0) {
            double sidewaysRadians = Math.toRadians((double)entityYRot + 90.0);
            baseVelX += -Math.sin(sidewaysRadians) * sidewaysTilt;
            baseVelZ += Math.cos(sidewaysRadians) * sidewaysTilt;
        }
        int spawnedParticle = entity.getRandom().nextInt(nextInt) + particleCount;
        for (int i = 0; i < spawnedParticle; ++i) {
            double offsetX = (entity.getRandom().nextDouble() - 0.5) * width;
            double offsetY = entity.getRandom().nextDouble() * height;
            double offsetZ = (entity.getRandom().nextDouble() - 0.5) * depth;
            double particleX = entity.getX() + offsetX;
            double particleY = entity.getY() + offsetY;
            double particleZ = entity.getZ() + offsetZ;
            double velX = baseVelX + (entity.getRandom().nextDouble() - 0.5) * 0.08;
            double velY = baseVelY + (entity.getRandom().nextDouble() - 0.5) * 0.06;
            double velZ = baseVelZ + (entity.getRandom().nextDouble() - 0.5) * 0.08;
            entity.level().addDestroyBlockEffect((ParticleOptions)ParticleTypes.SMOKE, particleX, particleY, particleZ, velX, velY, velZ);
            if (!(entity.getRandom().nextFloat() < 0.25f)) continue;
            entity.level().addDestroyBlockEffect((ParticleOptions)ParticleTypes.SCULK_SOUL, particleX + (entity.getRandom().nextDouble() - 0.5) * 0.2, particleY + (entity.getRandom().nextDouble() - 0.5) * 0.2, particleZ + (entity.getRandom().nextDouble() - 0.5) * 0.2, velX * 0.8, velY * 0.9, velZ * 0.8);
        }
    }
}

