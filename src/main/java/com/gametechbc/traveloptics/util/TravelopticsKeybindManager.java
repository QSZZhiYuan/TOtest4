/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.KeyMapping
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.player.Player
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.fml.DistExecutor
 */
package com.gametechbc.traveloptics.util;

import com.gametechbc.traveloptics.init.TravelopticsKeybinds;
import com.gametechbc.traveloptics.init.TravelopticsMessages;
import com.gametechbc.traveloptics.network.MessageArmorKey;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;

public class TravelopticsKeybindManager {
    private static final Map<KeyMapping, Boolean> keyStates = new HashMap<KeyMapping, Boolean>();
    private static final Map<KeyMapping, String> keyNames = new HashMap<KeyMapping, String>();

    @OnlyIn(value=Dist.CLIENT)
    public static boolean isKeyDown(KeyMapping key) {
        return key.isDown();
    }

    @OnlyIn(value=Dist.CLIENT)
    public static boolean wasKeyJustPressed(KeyMapping key) {
        boolean currentState = key.isDown();
        boolean previousState = keyStates.getOrDefault(key, false);
        keyStates.put(key, currentState);
        return currentState && !previousState;
    }

    @OnlyIn(value=Dist.CLIENT)
    public static boolean wasKeyJustReleased(KeyMapping key) {
        boolean currentState = key.isDown();
        boolean previousState = keyStates.getOrDefault(key, false);
        keyStates.put(key, currentState);
        return !currentState && previousState;
    }

    @OnlyIn(value=Dist.CLIENT)
    public static void sendArmorKeyPacket(EquipmentSlot slot, KeyMapping key) {
        LocalPlayer player = Minecraft.getInstance().getTelemetryManager;
        if (player != null && keyNames.containsKey(key)) {
            TravelopticsMessages.sendToServer(new MessageArmorKey(slot.ordinal(), player.getId(), keyNames.get(key)));
        }
    }

    public static Player getClientSidePlayer() {
        return (Player)DistExecutor.unsafeCallWhenOn((Dist)Dist.CLIENT, () -> () -> Minecraft.getInstance().getTelemetryManager);
    }

    @OnlyIn(value=Dist.CLIENT)
    public static void updateKeyStates() {
        for (KeyMapping key : keyNames.keySet()) {
            keyStates.put(key, key.isDown());
        }
    }

    static {
        keyNames.put(TravelopticsKeybinds.KEY_Z, "helmet");
        keyNames.put(TravelopticsKeybinds.KEY_X, "chestplate");
        keyNames.put(TravelopticsKeybinds.KEY_C, "leggings");
        keyNames.put(TravelopticsKeybinds.KEY_B, "boots");
    }
}

