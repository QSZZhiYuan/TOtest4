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
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 */
package com.gametechbc.traveloptics.effects;

import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class TidalGraspHelperEffect
extends MobEffect {
    public TidalGraspHelperEffect() {
        super(MobEffectCategory.HARMFUL, 9636843);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
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
        }
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }
}

