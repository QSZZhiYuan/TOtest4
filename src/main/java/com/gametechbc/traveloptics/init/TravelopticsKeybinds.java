/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.InputConstants$Type
 *  net.minecraft.client.KeyMapping
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.client.event.RegisterKeyMappingsEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 */
package com.gametechbc.traveloptics.init;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid="traveloptics", bus=Mod.EventBusSubscriber.Bus.MOD, value={Dist.CLIENT})
public class TravelopticsKeybinds {
    public static final KeyMapping KEY_Z = new KeyMapping("key.traveloptics.ability1", InputConstants.Type.KEYSYM, 90, "key.categories.traveloptics");
    public static final KeyMapping KEY_X = new KeyMapping("key.traveloptics.ability2", InputConstants.Type.KEYSYM, 88, "key.categories.traveloptics");
    public static final KeyMapping KEY_C = new KeyMapping("key.traveloptics.ability3", InputConstants.Type.KEYSYM, 67, "key.categories.traveloptics");
    public static final KeyMapping KEY_B = new KeyMapping("key.traveloptics.ability4", InputConstants.Type.KEYSYM, 66, "key.categories.traveloptics");

    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(KEY_Z);
        event.register(KEY_X);
        event.register(KEY_C);
        event.register(KEY_B);
    }
}

