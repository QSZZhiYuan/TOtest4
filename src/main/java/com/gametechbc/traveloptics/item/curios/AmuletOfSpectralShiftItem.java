/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.item.curios.SimpleDescriptiveCurio
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 */
package com.gametechbc.traveloptics.item.curios;

import com.gametechbc.traveloptics.api.compat.Curios;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import io.redspace.ironsspellbooks.item.curios.SimpleDescriptiveCurio;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AmuletOfSpectralShiftItem
extends SimpleDescriptiveCurio {
    public AmuletOfSpectralShiftItem() {
        super(new Item.Properties().requiredFeatures(1).requiredFeatures(TravelopticsItems.RARITY_ELDRITCH), Curios.NECKLACE_SLOT);
    }

    public List<Component> getDescriptionLines(ItemStack stack) {
        return List.of(Component.translatable((String)"item.traveloptics.amulet_of_spectral_shift.tooltip.desc1").withStyle(ChatFormatting.GREEN), Component.translatable((String)"item.traveloptics.amulet_of_spectral_shift.tooltip.desc").withStyle(ChatFormatting.BLUE));
    }
}

