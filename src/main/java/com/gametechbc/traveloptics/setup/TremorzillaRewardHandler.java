/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$EntityInteractSpecific
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 */
package com.gametechbc.traveloptics.setup;

import com.gametechbc.traveloptics.init.TravelopticsItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TremorzillaRewardHandler {
    private static final String REWARD_TAG = "TitanlordScepterGiven";

    @SubscribeEvent
    public static void onEntityInteracted(PlayerInteractEvent.EntityInteractSpecific event) {
        Entity entity = event.getTarget();
        if (entity instanceof TamableAnimal) {
            Player player;
            TamableAnimal tameableAnimal = (TamableAnimal)entity;
            Player player2 = player = event.getEntity() instanceof Player ? event.getEntity() : null;
            // REMOVED AC dependency: Tremorzilla check disabled
            if (player == null) {
                return;
            }
            // AC creature check removed - this feature requires Alex's Caves
            return;
            ItemStack heldItem = player.getMainHandItem();
            if (heldItem.setRepairCost() != TravelopticsItems.TREMOR_CORE.get()) {
                return;
            }
            CompoundTag entityData = tameableAnimal.getPersistentData();
            if (tameableAnimal.isBaby() && !tameableAnimal.isTame()) {
                if (player.level().isClientSide()) {
                    player.updateTutorialInventoryAction((Component)Component.score((String)"This Tremorzilla needs to be tamed first!"), true);
                }
                return;
            }
            if (tameableAnimal.isBaby() && entityData.getBoolean(REWARD_TAG)) {
                if (player.level().isClientSide()) {
                    player.updateTutorialInventoryAction((Component)Component.score((String)"The reward has already been given to this Tremorzilla!"), true);
                }
                return;
            }
            if (tameableAnimal.isBaby() && tameableAnimal.isTame() && tameableAnimal.getOwner() instanceof Player && !entityData.getBoolean(REWARD_TAG)) {
                if (!player.level().isClientSide()) {
                    entityData.accept(REWARD_TAG, true);
                    int slotIndex = player.getInventory().getTimesChanged;
                    heldItem.shrink(1);
                    ItemStack rewardItem = new ItemStack((ItemLike)TravelopticsItems.TITANLORD_SCEPTER.get(), 1);
                    if (player.getInventory().setItems(slotIndex).onUseTick()) {
                        player.getInventory().setItems(slotIndex, rewardItem);
                    } else {
                        boolean addedToInventory = player.setShoulderEntityLeft(rewardItem);
                        if (!addedToInventory) {
                            Level level = player.level();
                            level.addFreshEntity((Entity)new ItemEntity(level, player.getX(), player.getY(), player.getZ(), rewardItem));
                        }
                    }
                } else {
                    player.updateTutorialInventoryAction((Component)Component.score((String)"You have been rewarded with the Titanlord Scepter!"), true);
                }
            }
        }
    }
}

