/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.Rarity
 */
package com.gametechbc.traveloptics.item.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class EvokatedSpellweaveIngotItem
extends Item {
    public EvokatedSpellweaveIngotItem() {
        super(new Item.Properties().requiredFeatures(64).requiredFeatures(Rarity.EPIC));
    }
}

