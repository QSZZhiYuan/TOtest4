/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dev.shadowsoffire.attributeslib.api.ALObjects$Attributes
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.Attributes
 */
package com.gametechbc.traveloptics.effects;

import dev.shadowsoffire.attributeslib.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class PhantomRageEffect
extends MobEffect {
    public PhantomRageEffect() {
        super(MobEffectCategory.BENEFICIAL, 10423267);
        this.getAttributeModifierValue(Attributes.ATTACK_DAMAGE, "68078724-8653-42D5-A245-9D14A1F54685", 0.15, AttributeModifier.Operation.MULTIPLY_BASE);
        this.getAttributeModifierValue((Attribute)ALObjects.Attributes.ARROW_DAMAGE.get(), "68078724-8653-42D5-A245-9D14A1F54685", 0.15, AttributeModifier.Operation.MULTIPLY_BASE);
        this.getAttributeModifierValue((Attribute)AttributeRegistry.SPELL_POWER.get(), "68078724-8653-42D5-A245-9D14A1F54685", 0.15, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    public void applyEffectTick(LivingEntity LivingEntityIn, int amplifier) {
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }
}

