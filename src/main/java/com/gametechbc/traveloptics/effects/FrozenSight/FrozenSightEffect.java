/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraftforge.common.ForgeMod
 */
package com.gametechbc.traveloptics.effects.FrozenSight;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;

public class FrozenSightEffect
extends MobEffect {
    public FrozenSightEffect() {
        super(MobEffectCategory.HARMFUL, 5926017);
        this.getAttributeModifierValue(Attributes.MOVEMENT_SPEED, "69078724-8653-42D5-A245-9D14A1F54685", -1.0, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.getAttributeModifierValue((Attribute)ForgeMod.ENTITY_GRAVITY.get(), "69098724-8653-42D5-A245-9D14A1F54685", 2.0, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }
}

