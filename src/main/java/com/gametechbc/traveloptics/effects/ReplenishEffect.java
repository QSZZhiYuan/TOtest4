/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 */
package com.gametechbc.traveloptics.effects;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class ReplenishEffect
extends MobEffect {
    public ReplenishEffect() {
        super(MobEffectCategory.BENEFICIAL, 9636843);
        this.getAttributeModifierValue((Attribute)AttributeRegistry.MANA_REGEN.get(), "68078724-8653-42D5-A245-9D14A1F54685", 0.1, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    public void applyEffectTick(LivingEntity LivingEntityIn, int amplifier) {
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }
}

