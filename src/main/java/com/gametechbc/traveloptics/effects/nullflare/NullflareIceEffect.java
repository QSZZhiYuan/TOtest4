/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.AttributeMap
 */
package com.gametechbc.traveloptics.effects.nullflare;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class NullflareIceEffect
extends MobEffect {
    public NullflareIceEffect() {
        super(MobEffectCategory.HARMFUL, 65535);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide() && entity.canFreeze()) {
            int freezeIncrease = (amplifier + 1) * 2;
            entity.onAboveBubbleCol(entity.getTicksFrozen() + freezeIncrease);
        }
    }

    public void getAttributeModifierValue(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.getAttributeModifierValue(entity, attributeMap, amplifier);
        if (!entity.level().isClientSide()) {
            entity.onAboveBubbleCol(0);
        }
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }
}

