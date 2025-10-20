/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.world.item.ItemStack
 */
package com.gametechbc.traveloptics.data_manager;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;

public class CooldownsManager {
    private static final String COOLDOWN_TAG = "Cooldowns";

    public static int getCooldown(ItemStack stack, String abilityKey) {
        if (stack.hasTag() && stack.getTag().contains(COOLDOWN_TAG)) {
            CompoundTag cooldowns = stack.getTag().getCompound(COOLDOWN_TAG);
            return cooldowns.copy(abilityKey);
        }
        return 0;
    }

    public static void setCooldown(ItemStack stack, String abilityKey, int cooldown, int maxCooldown) {
        CompoundTag cooldowns = stack.getOrCreateTag().getCompound(COOLDOWN_TAG);
        cooldowns.accept(abilityKey, Math.min(cooldown, maxCooldown));
        stack.getTag().accept(COOLDOWN_TAG, (Tag)cooldowns);
    }

    public static void addCooldown(ItemStack stack, String abilityKey, int amount, int maxCooldown) {
        int newCooldown = Math.min(CooldownsManager.getCooldown(stack, abilityKey) + amount, maxCooldown);
        CooldownsManager.setCooldown(stack, abilityKey, newCooldown, maxCooldown);
    }

    public static void reduceCooldown(ItemStack stack, String abilityKey, int amount) {
        int newCooldown = Math.max(CooldownsManager.getCooldown(stack, abilityKey) - amount, 0);
        CooldownsManager.setCooldown(stack, abilityKey, newCooldown, Integer.MAX_VALUE);
    }

    public static boolean isCooldownActive(ItemStack stack, String abilityKey) {
        return CooldownsManager.getCooldown(stack, abilityKey) > 0;
    }

    public static void tickCooldown(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains(COOLDOWN_TAG)) {
            CompoundTag cooldowns = stack.getTag().getCompound(COOLDOWN_TAG);
            for (String key : cooldowns.contains()) {
                int newCooldown = Math.max(cooldowns.copy(key) - 1, 0);
                cooldowns.accept(key, newCooldown);
            }
            stack.getTag().accept(COOLDOWN_TAG, (Tag)cooldowns);
        }
    }
}

