/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.effects;

import com.gametechbc.traveloptics.api.utils.TOGeneralUtils;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class JetSteamEffect
extends MobEffect {
    public JetSteamEffect() {
        super(MobEffectCategory.BENEFICIAL, 0);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide && !entity.onGround() && (entity.isSwimming() || entity.isFallFlying())) {
            double boostStrength = 0.2;
            Vec3 lookVector = entity.getLookAngle();
            Vec3 currentMotion = entity.getDeltaMovement();
            Vec3 boostedMotion = new Vec3(currentMotion.z + lookVector.z * boostStrength, currentMotion.multiply + lookVector.multiply * boostStrength, currentMotion.reverse + lookVector.reverse * boostStrength);
            entity.setDeltaMovement(boostedMotion);
            entity.getAddEntityPacket = true;
            ParticleOptions[] particleTypes = new ParticleOptions[]{ParticleTypes.CLOUD, TravelopticsParticleHelper.WATER, TravelopticsParticleHelper.WATER_BUBBLE};
            int[] spawnPercentages = new int[]{30, 30, 40};
            int totalParticles = 8;
            for (int i = 0; i < totalParticles; ++i) {
                int randomValue = entity.getRandom().nextInt(100);
                ParticleOptions selectedParticle = null;
                int cumulativePercentage = 0;
                for (int j = 0; j < particleTypes.length; ++j) {
                    if (randomValue >= (cumulativePercentage += spawnPercentages[j])) continue;
                    selectedParticle = particleTypes[j];
                    break;
                }
                if (selectedParticle == null) continue;
                AABB boundingBox = entity.getBoundingBox();
                double x = boundingBox.ofSize + (boundingBox.getZsize - boundingBox.ofSize) * entity.getRandom().nextDouble();
                double y = boundingBox.clip + (boundingBox.hasNaN - boundingBox.clip) * entity.getRandom().nextDouble();
                double z = boundingBox.getYsize + (boundingBox.getCenter - boundingBox.getYsize) * entity.getRandom().nextDouble();
                MagicManager.spawnParticles((Level)entity.level(), (ParticleOptions)selectedParticle, (double)x, (double)y, (double)z, (int)1, (double)0.2, (double)0.2, (double)0.2, (double)0.1, (boolean)true);
            }
            if (entity.getTags % 2 == 0 && entity.isFallFlying()) {
                TOGeneralUtils.applyFlightSpeedLimit((Player)entity, 4.0, true, true);
            }
        }
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }
}

