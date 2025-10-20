/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.api.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

public class MovementTracker {
    private static final Map<UUID, Vec3> previousPositions = new HashMap<UUID, Vec3>();

    public static void updateLastPositions(ServerPlayer player) {
        previousPositions.put(player.getUUID(), player.position());
    }

    public static Vec3 getLastPosition(ServerPlayer player) {
        return previousPositions.getOrDefault(player.getUUID(), player.position());
    }

    public static void cleanup(ServerPlayer player) {
        previousPositions.remove(player.getUUID());
    }
}

