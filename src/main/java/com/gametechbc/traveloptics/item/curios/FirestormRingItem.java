/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.compat.Curios
 *  io.redspace.ironsspellbooks.item.curios.SimpleDescriptiveCurio
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 */
package com.gametechbc.traveloptics.item.curios;

import com.gametechbc.traveloptics.init.TravelopticsItems;
import io.redspace.ironsspellbooks.compat.Curios;
import io.redspace.ironsspellbooks.item.curios.SimpleDescriptiveCurio;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FirestormRingItem
extends SimpleDescriptiveCurio {
    public FirestormRingItem() {
        super(new Item.Properties().requiredFeatures(1).requiredFeatures(TravelopticsItems.RARITY_MONSTROUS), Curios.RING_SLOT);
    }

    public List<Component> getDescriptionLines(ItemStack stack) {
        return List.of(Component.translatable((String)"item.traveloptics.firestorm_ring.tooltip.desc1").withStyle(ChatFormatting.GREEN), Component.translatable((String)"item.traveloptics.firestorm_ring.tooltip.desc").withStyle(ChatFormatting.BLUE));
    }
}

