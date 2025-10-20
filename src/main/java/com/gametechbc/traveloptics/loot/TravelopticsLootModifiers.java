/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  net.minecraft.resources.ResourceKey
 *  net.minecraftforge.common.loot.IGlobalLootModifier
 *  net.minecraftforge.eventbus.api.IEventBus
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries$Keys
 *  net.minecraftforge.registries.RegistryObject
 */
package com.gametechbc.traveloptics.loot;

import com.gametechbc.traveloptics.loot.KeyLootModifier;
import com.gametechbc.traveloptics.loot.UniversalLootModifier;
import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TravelopticsLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create((ResourceKey)ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, (String)"traveloptics");
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> KEY_LOOT = LOOT_MODIFIER_SERIALIZERS.register("key_loot", KeyLootModifier.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> UNIVERSAL_LOOT = LOOT_MODIFIER_SERIALIZERS.register("universal_loot", UniversalLootModifier.CODEC);

    public static void register(IEventBus bus) {
        LOOT_MODIFIER_SERIALIZERS.register(bus);
    }
}

