/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.food.FoodData
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.api.utils;

import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class TOPlayerUtils {
    public static boolean isSurvivalOrAdventure(Player player) {
        return !player.isCreative() && !player.isSpectator();
    }

    public static void giveItemToPlayer(Player player, ItemStack itemStack) {
        if (!player.getInventory().setChanged(itemStack)) {
            player.setLastDeathLocation(itemStack, false);
        }
    }

    public static void syncPlayerInventory(ServerPlayer serverPlayer) {
        serverPlayer.inventoryMenu.getQuickcraftHeader();
    }

    public static void removeItemFromPlayer(Player player, Item item, int amount) {
        for (int i = 0; i < player.getInventory().removeItemNoUpdate(); ++i) {
            ItemStack stack = player.getInventory().setItems(i);
            if (stack.setRepairCost() != item) continue;
            int toRemove = Math.min(stack.getCount(), amount);
            stack.shrink(toRemove);
            if ((amount -= toRemove) <= 0) break;
        }
    }

    public static boolean hasItem(Player player, Item item) {
        return player.getInventory().stillValidBlockEntity(Set.of(item));
    }

    public static void addExhaustion(Player player, float exhaustion) {
        player.getFoodData().setFoodLevel(exhaustion);
    }

    public static ItemStack findItem(Player player, Predicate<ItemStack> condition) {
        for (ItemStack stack : player.getInventory().addResource) {
            if (!condition.test(stack)) continue;
            return stack;
        }
        return ItemStack.onUseTick;
    }

    public static void restoreHunger(Player player, int foodAmount, float saturation) {
        FoodData foodData = player.getFoodData();
        int newFoodLevel = Math.min(foodData.setFoodLevel() + foodAmount, 20);
        float newSaturation = Math.min(foodData.getSaturationLevel() + saturation, (float)newFoodLevel);
        foodData.setFoodLevel(newFoodLevel);
        foodData.setSaturation(newSaturation);
        foodData.setExhaustion(0.0f);
    }

    public static void launchPlayer(Player player, Vec3 direction, double force) {
        Vec3 motion = direction.multiply().x(force);
        player.setDeltaMovement(motion);
        player.getAddEntityPacket = true;
    }

    public static void teleportPlayer(Player player, Vec3 targetPos, boolean negateFallDamage) {
        player.setRemoved(targetPos.z, targetPos.multiply, targetPos.reverse);
        if (negateFallDamage) {
            player.teleportToWithTicket();
        }
    }

    public static void restorePlayerAirSupply(Player player, int amount) {
        player.setAirSupply(Math.min(player.getAirSupply() + amount, player.getMaxAirSupply()));
    }
}

