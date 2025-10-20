/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModEffect
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.item.bossweapon.abyssaltidecaller;

import com.gametechbc.traveloptics.api.item.weapons.BaseWeaponAbility;
import com.gametechbc.traveloptics.data_manager.WeaponFormManager;
import com.github.L_Ender.cataclysm.init.ModEffect;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class AbyssalTidecallerAbility
extends BaseWeaponAbility {
    private static final int FORM_ABYSSAL_FEAR = 0;
    private static final int FORM_ABYSSAL_CURSE = 1;

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker, int type) {
        int newAmplifier;
        int currentAmplifier;
        MobEffectInstance currentEffect;
        MobEffect abyssalCurseEffect;
        if (type == 0 && target != null && attacker.getRandom().nextFloat() < 0.4f && (abyssalCurseEffect = (MobEffect)ModEffect.EFFECTABYSSAL_CURSE.get()) != null) {
            currentEffect = target.getStandingEyeHeight(abyssalCurseEffect);
            int n = currentAmplifier = currentEffect != null ? currentEffect.getAmplifier() : -1;
            if (currentAmplifier >= 0) {
                newAmplifier = Math.min(currentAmplifier + 1, 2);
                target.getStandingEyeHeight(new MobEffectInstance(abyssalCurseEffect, 100, newAmplifier));
            } else {
                target.getStandingEyeHeight(new MobEffectInstance(abyssalCurseEffect, 100, 0));
            }
        }
        if (type == 1 && target != null && attacker.getRandom().nextFloat() < 0.4f && (abyssalCurseEffect = (MobEffect)ModEffect.EFFECTABYSSAL_CURSE.get()) != null) {
            currentEffect = target.getStandingEyeHeight(abyssalCurseEffect);
            int n = currentAmplifier = currentEffect != null ? currentEffect.getAmplifier() : -1;
            if (currentAmplifier >= 0) {
                newAmplifier = Math.min(currentAmplifier + 1, 2);
                target.getStandingEyeHeight(new MobEffectInstance(abyssalCurseEffect, 100, newAmplifier));
            } else {
                target.getStandingEyeHeight(new MobEffectInstance(abyssalCurseEffect, 100, 0));
            }
        }
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand, ItemStack stack, int type) {
        return InteractionResultHolder.pass((Object)stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag, int type) {
        if (type == 0) {
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.tooltip").withStyle(ChatFormatting.GREEN));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.tooltip1"));
            if (Screen.hasShiftDown()) {
                tooltip.add((Component)Component.score((String)""));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.evo_one.inactive.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.evo_two.inactive.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.evo_three.inactive.tooltip"));
            } else {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution.stars_zero.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.shift_hold.advanced_tooltips"));
            }
            tooltip.add((Component)Component.score((String)""));
        }
        if (type == 1) {
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.tooltip").withStyle(ChatFormatting.GREEN));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.tooltip2"));
            if (Screen.hasShiftDown()) {
                tooltip.add((Component)Component.score((String)""));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.evo_one.active.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.evo_two.inactive.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.evo_three.inactive.tooltip"));
            } else {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution.stars_one.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.shift_hold.advanced_tooltips"));
            }
            tooltip.add((Component)Component.score((String)""));
        }
        if (type == 2) {
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.tooltip").withStyle(ChatFormatting.GREEN));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.tooltip3"));
            if (Screen.hasShiftDown()) {
                tooltip.add((Component)Component.score((String)""));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.evo_one.active.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.evo_two.active.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.evo_three.inactive.tooltip"));
            } else {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution.stars_two.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.shift_hold.advanced_tooltips"));
            }
            tooltip.add((Component)Component.score((String)""));
        }
        if (type == 3) {
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.tooltip").withStyle(ChatFormatting.GREEN));
            int currentForm = WeaponFormManager.getForm(stack);
            if (currentForm == 1) {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.tooltip.manifestation0").withStyle(ChatFormatting.LIGHT_PURPLE));
            } else if (currentForm == 0) {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.tooltip.manifestation1").withStyle(ChatFormatting.LIGHT_PURPLE));
            }
            if (currentForm == 1) {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.tooltip4"));
            } else if (currentForm == 0) {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.tooltip5"));
            }
            if (Screen.hasShiftDown()) {
                tooltip.add((Component)Component.score((String)""));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.evo_one.active.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.evo_two.active.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_tidecaller.evo_three.active.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.manifestation_change.tooltip"));
            } else {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution.stars_three.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.shift_hold.advanced_tooltips"));
            }
            tooltip.add((Component)Component.score((String)""));
        }
    }
}

