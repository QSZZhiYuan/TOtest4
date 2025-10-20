/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.Rarity
 *  net.minecraft.world.item.RecordItem
 */
package com.gametechbc.traveloptics.item.music_disc;

import com.gametechbc.traveloptics.init.TravelopticsSounds;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;

public class MusicDiscNightwardenItem
extends RecordItem {
    public MusicDiscNightwardenItem() {
        super(15, TravelopticsSounds.NIGHTWARDEN_MUSIC_FULL, new Item.Properties().requiredFeatures(1).requiredFeatures(Rarity.EPIC).requiredFeatures(), 3640);
    }
}

