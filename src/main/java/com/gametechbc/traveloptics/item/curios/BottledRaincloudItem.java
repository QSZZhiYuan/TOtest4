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

public class BottledRaincloudItem
extends SimpleDescriptiveCurio {
    public BottledRaincloudItem() {
        super(new Item.Properties().requiredFeatures(1).requiredFeatures(TravelopticsItems.RARITY_TOXIC), Curios.CHARM_SLOT);
    }

    public List<Component> getDescriptionLines(ItemStack stack) {
        return List.of(Component.translatable((String)"item.traveloptics.bottled_raincloud.tooltip.desc1").withStyle(ChatFormatting.GREEN), Component.translatable((String)"item.traveloptics.bottled_raincloud.tooltip.desc").withStyle(ChatFormatting.BLUE));
    }

    public Component getDescription(ItemStack stack) {
        return Component.translatable((String)"item.traveloptics.bottled_raincloud.tooltip.desc").withStyle(ChatFormatting.BLUE);
    }
}

