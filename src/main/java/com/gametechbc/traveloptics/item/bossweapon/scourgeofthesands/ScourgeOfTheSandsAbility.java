/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.item.bossweapon.scourgeofthesands;

import com.gametechbc.traveloptics.api.item.weapons.BaseWeaponAbility;
import com.gametechbc.traveloptics.data_manager.WeaponFormManager;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ScourgeOfTheSandsAbility
extends BaseWeaponAbility {
    private static final int FORM_SANDSTORM = 0;
    private static final int FORM_BASE = 1;

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker, int type) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand, ItemStack stack, int type) {
        return InteractionResultHolder.pass((Object)stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag, int type) {
        int currentForm;
        if (type == 0) {
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.tooltip").withStyle(ChatFormatting.DARK_GREEN));
            tooltip.add((Component)Component.translatable((String)"ui.traveloptics.weapon.evolution_zero").withStyle(ChatFormatting.YELLOW));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.tooltip1"));
            if (Screen.hasShiftDown()) {
                tooltip.add((Component)Component.score((String)""));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.evo_one.inactive.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.evo_two.inactive.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.evo_three.inactive.tooltip"));
            } else {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution.stars_zero.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.shift_hold.advanced_tooltips"));
            }
            tooltip.add((Component)Component.score((String)""));
        }
        if (type == 1) {
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.tooltip").withStyle(ChatFormatting.DARK_GREEN));
            tooltip.add((Component)Component.translatable((String)"ui.traveloptics.weapon.evolution_one").withStyle(ChatFormatting.YELLOW));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.tooltip2"));
            if (Screen.hasShiftDown()) {
                tooltip.add((Component)Component.score((String)""));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.evo_one.active.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.evo_two.inactive.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.evo_three.inactive.tooltip"));
            } else {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution.stars_one.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.shift_hold.advanced_tooltips"));
            }
            tooltip.add((Component)Component.score((String)""));
        }
        if (type == 2) {
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.tooltip").withStyle(ChatFormatting.DARK_GREEN));
            tooltip.add((Component)Component.translatable((String)"ui.traveloptics.weapon.evolution_two").withStyle(ChatFormatting.YELLOW));
            currentForm = WeaponFormManager.getForm(stack);
            if (currentForm == 0) {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.tooltip.manifestation0").withStyle(ChatFormatting.LIGHT_PURPLE));
            } else if (currentForm == 1) {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.tooltip.manifestation1").withStyle(ChatFormatting.LIGHT_PURPLE));
            }
            if (currentForm == 0) {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.tooltip5"));
            } else if (currentForm == 1) {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.tooltip3"));
            }
            if (Screen.hasShiftDown()) {
                tooltip.add((Component)Component.score((String)""));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.evo_one.active.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.evo_two.active.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.evo_three.inactive.tooltip"));
            } else {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution.stars_two.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.shift_hold.advanced_tooltips"));
            }
            tooltip.add((Component)Component.score((String)""));
        }
        if (type == 3) {
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.tooltip").withStyle(ChatFormatting.DARK_GREEN));
            tooltip.add((Component)Component.translatable((String)"ui.traveloptics.weapon.evolution_three").withStyle(ChatFormatting.YELLOW));
            currentForm = WeaponFormManager.getForm(stack);
            if (currentForm == 0) {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.tooltip.manifestation0").withStyle(ChatFormatting.LIGHT_PURPLE));
            } else if (currentForm == 1) {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.tooltip.manifestation1").withStyle(ChatFormatting.LIGHT_PURPLE));
            }
            if (currentForm == 0) {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.tooltip6"));
            } else if (currentForm == 1) {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.tooltip4"));
            }
            if (Screen.hasShiftDown()) {
                tooltip.add((Component)Component.score((String)""));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.evo_one.active.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.evo_two.active.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.scourge_of_the_sands.evo_three.active.tooltip"));
            } else {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution.stars_three.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.shift_hold.advanced_tooltips"));
            }
            tooltip.add((Component)Component.score((String)""));
        }
    }
}

