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

public class TremorCoreItem
extends Item {
    public TremorCoreItem() {
        super(new Item.Properties().requiredFeatures(1).requiredFeatures(TravelopticsItems.RARITY_TOXIC));
    }
}

