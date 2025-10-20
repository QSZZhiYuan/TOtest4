/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.Rarity
 *  net.minecraft.world.item.RecordItem
 *  net.minecraftforge.registries.ForgeRegistries
 */
package com.gametechbc.traveloptics.item.music_disc;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.registries.ForgeRegistries;

public class MusicDiscEldritchAbyssamorphItem
extends RecordItem {
    public MusicDiscEldritchAbyssamorphItem() {
        super(15, () -> (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("traveloptics:eldritch_abyssamorph")), new Item.Properties().requiredFeatures(1).requiredFeatures(Rarity.EPIC), 2020);
    }
}

