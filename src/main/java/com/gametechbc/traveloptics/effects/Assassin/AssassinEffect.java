/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.registries.MobEffectRegistry
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.Attributes
 */
package com.gametechbc.traveloptics.effects.Assassin;

import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class AssassinEffect
extends MobEffect {
    public AssassinEffect() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.getAttributeModifierValue(Attributes.MOVEMENT_SPEED, "68078724-8653-42D5-A245-9D14A1F54685", 0.05, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.getAttributeModifierValue(Attributes.ATTACK_DAMAGE, "68078724-8653-42D5-A245-9D14A1F54685", 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.getStandingEyeHeight(new MobEffectInstance((MobEffect)MobEffectRegistry.TRUE_INVISIBILITY.get(), 10, 0, false, false, false));
        if (!entity.level().isClientSide && entity.getTags % 30 == 0) {
            ServerLevel serverLevel = (ServerLevel)entity.level();
            serverLevel.getRandomSequence((ParticleOptions)((SimpleParticleType)ParticleTypes.FALLING_OBSIDIAN_TEAR), entity.getX(), entity.getY() + (double)entity.getEyeHeight(), entity.getZ(), 1, 0.0, 0.0, 0.0, 0.0);
        }
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }
}

