/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemStack
 */
package com.gametechbc.traveloptics.effects;

import java.util.Collections;
import java.util.List;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class OverloadedEffect
extends MobEffect {
    public OverloadedEffect() {
        super(MobEffectCategory.HARMFUL, 0);
    }

    public void applyEffectTick(LivingEntity LivingEntityIn, int amplifier) {
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }

    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}

