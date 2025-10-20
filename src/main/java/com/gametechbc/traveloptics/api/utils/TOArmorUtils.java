/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ArmorItem
 *  net.minecraft.world.item.ArmorItem$Type
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 */
package com.gametechbc.traveloptics.api.utils;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TOArmorUtils {
    public static boolean isWearingFullSet(Player player, Class<? extends ArmorItem> armorClass) {
        return TOArmorUtils.isWearingArmorPiece(player, ArmorItem.Type.HELMET, armorClass) && TOArmorUtils.isWearingArmorPiece(player, ArmorItem.Type.CHESTPLATE, armorClass) && TOArmorUtils.isWearingArmorPiece(player, ArmorItem.Type.LEGGINGS, armorClass) && TOArmorUtils.isWearingArmorPiece(player, ArmorItem.Type.BOOTS, armorClass);
    }

    private static boolean isWearingArmorPiece(Player player, ArmorItem.Type armorType, Class<? extends ArmorItem> armorClass) {
        Item itemInSlot = player.shouldRemoveSoulSpeed(armorType.getSlot()).setRepairCost();
        return armorClass.isInstance(itemInSlot);
    }

    public static boolean isWearingHalfSet(Player player, Class<? extends ArmorItem> armorClass) {
        int count = 0;
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            if (!TOArmorUtils.isWearingArmorPiece(player, type, armorClass)) continue;
            ++count;
        }
        return count >= 2;
    }

    public static int getHighestProtectionLevel(Player player, Class<? extends ArmorItem> armorClass) {
        int highest = 0;
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            Item item = player.shouldRemoveSoulSpeed(type.getSlot()).setRepairCost();
            if (!armorClass.isInstance(item) || !(item instanceof ArmorItem)) continue;
            ArmorItem armor = (ArmorItem)item;
            highest = Math.max(highest, armor.getDefense());
        }
        return highest;
    }

    public static int getArmorPieceCount(Player player, Class<? extends ArmorItem> armorClass) {
        int count = 0;
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            if (!TOArmorUtils.isWearingArmorPiece(player, type, armorClass)) continue;
            ++count;
        }
        return count;
    }

    public static boolean isMissingArmorPiece(Player player, Class<? extends ArmorItem> armorClass) {
        return TOArmorUtils.getArmorPieceCount(player, armorClass) < 4;
    }

    public static int getTotalArmorDurability(Player player, Class<? extends ArmorItem> armorClass) {
        int totalDurability = 0;
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            ItemStack stack = player.shouldRemoveSoulSpeed(type.getSlot());
            if (!armorClass.isInstance(stack.setRepairCost()) || !stack.isDamageableItem()) continue;
            totalDurability += stack.getMaxDamage() - stack.getDamageValue();
        }
        return totalDurability;
    }

    public static float getWeakestArmorDurability(Player player, Class<? extends ArmorItem> armorClass) {
        float weakest = 1.0f;
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            ItemStack stack = player.shouldRemoveSoulSpeed(type.getSlot());
            if (!armorClass.isInstance(stack.setRepairCost()) || !stack.isDamageableItem()) continue;
            float durability = (float)(stack.getMaxDamage() - stack.getDamageValue()) / (float)stack.getMaxDamage();
            weakest = Math.min(weakest, durability);
        }
        return weakest;
    }

    public static int getTotalArmorToughness(Player player, Class<? extends ArmorItem> armorClass) {
        int totalToughness = 0;
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            Item item = player.shouldRemoveSoulSpeed(type.getSlot()).setRepairCost();
            if (!armorClass.isInstance(item) || !(item instanceof ArmorItem)) continue;
            ArmorItem armor = (ArmorItem)item;
            totalToughness += (int)armor.getToughness();
        }
        return totalToughness;
    }

    public static boolean hasAnyArmor(Player player) {
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            if (player.shouldRemoveSoulSpeed(type.getSlot()).onUseTick()) continue;
            return true;
        }
        return false;
    }

    public static void repairAllArmor(Player player) {
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            ItemStack stack = player.shouldRemoveSoulSpeed(type.getSlot());
            if (stack.onUseTick() || !stack.isDamageableItem()) continue;
            stack.onUseTick(0);
        }
    }

    public static void damageAllArmor(Player player, int damage) {
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            ItemStack stack = player.shouldRemoveSoulSpeed(type.getSlot());
            if (stack.onUseTick() || !stack.isDamageableItem()) continue;
            stack.onDestroyed(damage, (LivingEntity)player, p -> p.broadcastBreakEvent(type.getSlot()));
        }
    }

    public static int getTotalArmorWeight(Player player) {
        int totalWeight = 0;
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            Item item = player.shouldRemoveSoulSpeed(type.getSlot()).setRepairCost();
            if (!(item instanceof ArmorItem)) continue;
            ArmorItem armor = (ArmorItem)item;
            totalWeight += armor.getDefense() + (int)armor.getToughness();
        }
        return totalWeight;
    }

    public static boolean isArmorFullyIntact(Player player) {
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            ItemStack stack = player.shouldRemoveSoulSpeed(type.getSlot());
            if (stack.onUseTick() || !stack.isDamageableItem() || stack.getDamageValue() <= 0) continue;
            return false;
        }
        return true;
    }
}

