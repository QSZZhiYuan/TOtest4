/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.effects;

import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class TidalTormentEffect
extends MobEffect {
    public TidalTormentEffect() {
        super(MobEffectCategory.HARMFUL, 0);
        this.getAttributeModifierValue(Attributes.MOVEMENT_SPEED, "68078724-8654-42D5-A245-9D14A1F54685", -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        Level level;
        if (!entity.level().isClientSide() && (level = entity.level()) instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            int particleCount = 1;
            for (int i = 0; i < particleCount; ++i) {
                double offsetX = (entity.getRandom().nextDouble() - 0.5) * (double)entity.getBbWidth();
                double offsetY = entity.getRandom().nextDouble() * (double)entity.getBbHeight();
                double offsetZ = (entity.getRandom().nextDouble() - 0.5) * (double)entity.getBbWidth();
                double velocityX = (entity.getRandom().nextDouble() - 0.5) * 0.2;
                double velocityY = (entity.getRandom().nextDouble() - 0.5) * 0.2;
                double velocityZ = (entity.getRandom().nextDouble() - 0.5) * 0.2;
                serverLevel.getRandomSequence(TravelopticsParticleHelper.WATER_DROP, entity.getX() + offsetX, entity.getY() + offsetY, entity.getZ() + offsetZ, 1, velocityX, velocityY, velocityZ, 0.0);
            }
        }
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }
}

