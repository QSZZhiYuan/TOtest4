/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.item.items;

import com.gametechbc.traveloptics.init.TravelopticsItems;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class PlasmaPowerCellItem
extends Item {
    public PlasmaPowerCellItem() {
        super(new Item.Properties().requiredFeatures(64).requiredFeatures(TravelopticsItems.RARITY_MECHANIZED));
    }

    private String getNativeSupportedText() {
        ArrayList<String> deviceNames = new ArrayList<String>();
        this.addItemName(deviceNames, (Item)TravelopticsItems.MECHANIZED_WRAITHBLADE.get());
        return String.join((CharSequence)", ", deviceNames);
    }

    private String getPartiallySupportedText() {
        ArrayList<String> deviceNames = new ArrayList<String>();
        this.addItemName(deviceNames, (Item)TravelopticsItems.MECHANIZED_EXOSKELETON_ARMOR_HELMET.get());
        this.addItemName(deviceNames, (Item)TravelopticsItems.MECHANIZED_EXOSKELETON_ARMOR_CHESTPLATE.get());
        this.addItemName(deviceNames, (Item)TravelopticsItems.MECHANIZED_EXOSKELETON_ARMOR_LEGGINGS.get());
        this.addItemName(deviceNames, (Item)TravelopticsItems.MECHANIZED_EXOSKELETON_ARMOR_BOOTS.get());
        return String.join((CharSequence)", ", deviceNames);
    }

    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        if (!Screen.hasShiftDown()) {
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.plasma_power_cell.desc").withStyle(ChatFormatting.DARK_GREEN));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.plasma_power_cell.desc4").withStyle(ChatFormatting.DARK_GRAY));
            return;
        }
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.plasma_power_cell.desc").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add((Component)Component.score((String)""));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.plasma_power_cell.desc2").withStyle(ChatFormatting.GOLD));
        tooltip.add((Component)Component.score((String)("\u2192 " + this.getNativeSupportedText())).withStyle(ChatFormatting.GRAY));
        tooltip.add((Component)Component.score((String)""));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.plasma_power_cell.desc3").withStyle(ChatFormatting.GOLD));
        tooltip.add((Component)Component.score((String)("\u2192 " + this.getPartiallySupportedText())).withStyle(ChatFormatting.GRAY));
    }

    private void addItemName(List<String> nameList, Item item) {
        if (item != null) {
            try {
                String itemName = item.getDescription().getString();
                nameList.add(itemName);
            }
            catch (Exception e) {
                System.err.println("Error getting item name: " + e.getMessage());
            }
        }
    }
}

