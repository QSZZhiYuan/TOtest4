/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.item.bossweapon.cursedwraithblade;

import com.gametechbc.traveloptics.api.item.weapons.BaseWeaponAbility;
import com.gametechbc.traveloptics.data_manager.SpiritPointsManager;
import com.gametechbc.traveloptics.data_manager.SwingCounterManager;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class CursedWraithbladeAbility
extends BaseWeaponAbility {
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker, int type) {
        int pointsToAdd;
        int swingCounter;
        Player player;
        if (type == 0 && attacker instanceof Player) {
            player = (Player)attacker;
            swingCounter = SwingCounterManager.getSwingCount(stack);
            switch (swingCounter) {
                case 1: {
                    pointsToAdd = 5;
                    break;
                }
                case 2: {
                    pointsToAdd = 10;
                    break;
                }
                case 3: {
                    pointsToAdd = 15;
                    break;
                }
                default: {
                    pointsToAdd = 5;
                }
            }
            SpiritPointsManager.addSpiritPoints(stack, player, pointsToAdd);
            SwingCounterManager.incrementSwingCount(stack, 3);
            MagicManager.spawnParticles((Level)target.level(), (ParticleOptions)ParticleTypes.SCULK_SOUL, (double)target.getX(), (double)target.getY(), (double)target.getZ(), (int)30, (double)0.0, (double)0.0, (double)0.0, (double)0.3, (boolean)false);
        }
        if (type == 1 && attacker instanceof Player) {
            player = (Player)attacker;
            swingCounter = SwingCounterManager.getSwingCount(stack);
            switch (swingCounter) {
                case 1: {
                    pointsToAdd = 5;
                    break;
                }
                case 2: {
                    pointsToAdd = 10;
                    break;
                }
                case 3: {
                    pointsToAdd = 15;
                    break;
                }
                default: {
                    pointsToAdd = 5;
                }
            }
            SpiritPointsManager.addSpiritPoints(stack, player, pointsToAdd);
            SwingCounterManager.incrementSwingCount(stack, 3);
            MagicManager.spawnParticles((Level)target.level(), (ParticleOptions)ParticleTypes.SCULK_SOUL, (double)target.getX(), (double)target.getY(), (double)target.getZ(), (int)30, (double)0.0, (double)0.0, (double)0.0, (double)0.3, (boolean)false);
        }
        if (type == 2 && attacker instanceof Player) {
            player = (Player)attacker;
            swingCounter = SwingCounterManager.getSwingCount(stack);
            switch (swingCounter) {
                case 1: {
                    pointsToAdd = 5;
                    break;
                }
                case 2: {
                    pointsToAdd = 10;
                    break;
                }
                case 3: {
                    pointsToAdd = 15;
                    break;
                }
                default: {
                    pointsToAdd = 5;
                }
            }
            SpiritPointsManager.addSpiritPoints(stack, player, pointsToAdd);
            SwingCounterManager.incrementSwingCount(stack, 3);
            MagicManager.spawnParticles((Level)target.level(), (ParticleOptions)ParticleTypes.SCULK_SOUL, (double)target.getX(), (double)target.getY(), (double)target.getZ(), (int)30, (double)0.0, (double)0.0, (double)0.0, (double)0.3, (boolean)false);
        }
        if (type == 3 && attacker instanceof Player) {
            player = (Player)attacker;
            swingCounter = SwingCounterManager.getSwingCount(stack);
            switch (swingCounter) {
                case 1: {
                    pointsToAdd = 8;
                    break;
                }
                case 2: {
                    pointsToAdd = 15;
                    break;
                }
                case 3: {
                    pointsToAdd = 22;
                    break;
                }
                default: {
                    pointsToAdd = 8;
                }
            }
            SpiritPointsManager.addSpiritPoints(stack, player, pointsToAdd);
            SwingCounterManager.incrementSwingCount(stack, 3);
            MagicManager.spawnParticles((Level)target.level(), (ParticleOptions)ParticleTypes.SCULK_SOUL, (double)target.getX(), (double)target.getY(), (double)target.getZ(), (int)30, (double)0.0, (double)0.0, (double)0.0, (double)0.3, (boolean)false);
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
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.tooltip").withStyle(ChatFormatting.GREEN));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.tooltip1"));
            tooltip.add((Component)Component.score((String)("\u2620 Soul Fragments: " + SpiritPointsManager.getSpiritPoints(stack))).withStyle(ChatFormatting.GOLD));
            if (Screen.hasShiftDown()) {
                tooltip.add((Component)Component.score((String)""));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.evo_one.inactive.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.evo_two.inactive.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.evo_three.inactive.tooltip"));
            } else {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution.stars_zero.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.shift_hold.advanced_tooltips"));
            }
            tooltip.add((Component)Component.score((String)""));
        }
        if (type == 1) {
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.tooltip").withStyle(ChatFormatting.GREEN));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.tooltip2"));
            tooltip.add((Component)Component.score((String)("\u2620 Soul Fragments: " + SpiritPointsManager.getSpiritPoints(stack))).withStyle(ChatFormatting.GOLD));
            if (Screen.hasShiftDown()) {
                tooltip.add((Component)Component.score((String)""));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.evo_one.active.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.evo_two.inactive.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.evo_three.inactive.tooltip"));
            } else {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution.stars_one.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.shift_hold.advanced_tooltips"));
            }
            tooltip.add((Component)Component.score((String)""));
        }
        if (type == 2) {
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.tooltip").withStyle(ChatFormatting.GREEN));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.tooltip3"));
            tooltip.add((Component)Component.score((String)("\u2620 Soul Fragments: " + SpiritPointsManager.getSpiritPoints(stack))).withStyle(ChatFormatting.GOLD));
            if (Screen.hasShiftDown()) {
                tooltip.add((Component)Component.score((String)""));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.evo_one.active.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.evo_two.active.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.evo_three.inactive.tooltip"));
            } else {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution.stars_two.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.shift_hold.advanced_tooltips"));
            }
            tooltip.add((Component)Component.score((String)""));
        }
        if (type == 3) {
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.tooltip").withStyle(ChatFormatting.GREEN));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.tooltip4"));
            tooltip.add((Component)Component.score((String)("\u2620 Soul Fragments: " + SpiritPointsManager.getSpiritPoints(stack))).withStyle(ChatFormatting.GOLD));
            if (Screen.hasShiftDown()) {
                tooltip.add((Component)Component.score((String)""));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.evo_one.active.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.evo_two.active.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithblade.evo_three.active.tooltip"));
            } else {
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution.stars_three.tooltip"));
                tooltip.add((Component)Component.translatable((String)"item.traveloptics.shift_hold.advanced_tooltips"));
            }
            tooltip.add((Component)Component.score((String)""));
        }
    }
}

