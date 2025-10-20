/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 */
package com.gametechbc.traveloptics.item.items;

import com.gametechbc.traveloptics.init.TravelopticsItems;
import net.minecraft.world.item.Item;

public class HullbreakerSteelItem
extends Item {
    public HullbreakerSteelItem() {
        super(new Item.Properties().requiredFeatures(64).requiredFeatures().requiredFeatures(TravelopticsItems.RARITY_AQUATIC));
    }
}

