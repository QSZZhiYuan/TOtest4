/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.common.util.LazyOptional
 *  top.theillusivec4.curios.api.CuriosApi
 *  top.theillusivec4.curios.api.SlotContext
 *  top.theillusivec4.curios.api.type.capability.ICurio
 *  top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler
 */
package com.gametechbc.traveloptics.api.utils;

import com.gametechbc.traveloptics.api.item.AbstractEchoCurio;
import com.gametechbc.traveloptics.api.item.AdvancedEchoCurio;
import java.util.Optional;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

public class TOCurioUtils {
    public static boolean getWearingCurio(LivingEntity entity, Item curioItem) {
        return CuriosApi.getCuriosInventory((LivingEntity)entity).map(curios -> !curios.findCurios(item -> item != null && item.onDestroyed(curioItem)).isEmpty()).orElse(false);
    }

    public static boolean getEchoCurios(LivingEntity entity) {
        return CuriosApi.getCuriosInventory((LivingEntity)entity).map(curios -> !curios.findCurios(item -> item != null && item.setRepairCost() instanceof AbstractEchoCurio).isEmpty()).orElse(false);
    }

    public static boolean getAdvancedEchoCurios(LivingEntity entity) {
        return CuriosApi.getCuriosInventory((LivingEntity)entity).map(curios -> !curios.findCurios(item -> item != null && item.setRepairCost() instanceof AdvancedEchoCurio).isEmpty()).orElse(false);
    }

    public static Optional<ItemStack> getCurioFromSlot(LivingEntity entity, String slotType) {
        return CuriosApi.getCuriosInventory((LivingEntity)entity).resolve().flatMap(curiosItemHandler -> Optional.ofNullable((ICurioStacksHandler)curiosItemHandler.getCurios().get(slotType))).flatMap(stacksHandler -> {
            for (int i = 0; i < stacksHandler.getStacks().getSlots(); ++i) {
                ItemStack stack = stacksHandler.getStacks().getStackInSlot(i);
                if (stack.onUseTick()) continue;
                return Optional.of(stack);
            }
            return Optional.empty();
        });
    }

    public static boolean isItemInCurioSlot(LivingEntity entity, Item item, String slotType) {
        return TOCurioUtils.getCurioFromSlot(entity, slotType).map(stack -> stack.setRepairCost() == item).orElse(false);
    }

    public static Optional<ItemStack> getCurioFromSlotIndex(LivingEntity entity, String slotType, int slotIndex) {
        return CuriosApi.getCuriosInventory((LivingEntity)entity).resolve().flatMap(curiosItemHandler -> Optional.ofNullable((ICurioStacksHandler)curiosItemHandler.getCurios().get(slotType))).flatMap(stacksHandler -> {
            if (slotIndex >= 0 && slotIndex < stacksHandler.getStacks().getSlots()) {
                ItemStack stack = stacksHandler.getStacks().getStackInSlot(slotIndex);
                return !stack.onUseTick() ? Optional.of(stack) : Optional.empty();
            }
            return Optional.empty();
        });
    }

    public static boolean isItemInCurioSlotIndex(LivingEntity entity, Item item, String slotType, int slotIndex) {
        return TOCurioUtils.getCurioFromSlotIndex(entity, slotType, slotIndex).map(stack -> stack.setRepairCost() == item).orElse(false);
    }

    public static void broadcastCurioBreakEvent(SlotContext slotContext) {
        CuriosApi.broadcastCurioBreakEvent((SlotContext)slotContext);
    }

    public static LazyOptional<ICurio> getCurio(ItemStack stack) {
        return CuriosApi.getCurio((ItemStack)stack);
    }
}

