/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.ItemStack
 */
package com.gametechbc.traveloptics.data_manager;

import net.minecraft.world.item.ItemStack;

public class CooldownManager {
    public static int getCooldown(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("Cooldown")) {
            return stack.getTag().copy("Cooldown");
        }
        return 0;
    }

    public static void setCooldown(ItemStack stack, int cooldown, int maxCooldown) {
        int cappedCooldown = Math.min(cooldown, maxCooldown);
        stack.getOrCreateTag().accept("Cooldown", cappedCooldown);
    }

    public static void addCooldown(ItemStack stack, int amount, int maxCooldown) {
        int currentCooldown = CooldownManager.getCooldown(stack);
        int newCooldown = Math.min(currentCooldown + amount, maxCooldown);
        CooldownManager.setCooldown(stack, newCooldown, maxCooldown);
    }

    public static void reduceCooldown(ItemStack stack, int amount) {
        int currentCooldown = CooldownManager.getCooldown(stack);
        int newCooldown = Math.max(currentCooldown - amount, 0);
        CooldownManager.setCooldown(stack, newCooldown, Integer.MAX_VALUE);
    }

    public static boolean isCooldownActive(ItemStack stack) {
        return CooldownManager.getCooldown(stack) > 0;
    }

    public static void tickCooldown(ItemStack stack) {
        if (CooldownManager.isCooldownActive(stack)) {
            CooldownManager.reduceCooldown(stack, 1);
        }
    }
}

