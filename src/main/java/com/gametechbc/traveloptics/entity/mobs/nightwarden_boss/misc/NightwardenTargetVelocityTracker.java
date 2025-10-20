/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.misc;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenBossEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class NightwardenTargetVelocityTracker {
    private static final Map<UUID, BossTrackingData> bossTrackers = new HashMap<UUID, BossTrackingData>();
    private static final int VELOCITY_HISTORY_SIZE = 3;

    public static void trackTargetMovement(NightwardenBossEntity nightwardenBoss) {
        LivingEntity target = nightwardenBoss.getTarget();
        if (target == null || !target.isAlive()) {
            return;
        }
        UUID bossId = nightwardenBoss.getUUID();
        BossTrackingData data = bossTrackers.computeIfAbsent(bossId, k -> new BossTrackingData());
        ++data.velocityTrackingTicks;
        if (data.velocityTrackingTicks % 2 == 0) {
            Vec3 currentPos = target.position();
            if (!data.lastTargetPosition.equals((Object)Vec3.y)) {
                Vec3 actualVelocity = currentPos.multiply(data.lastTargetPosition).x(0.5);
                data.velocityHistory.add(actualVelocity);
                if (data.velocityHistory.size() > 3) {
                    data.velocityHistory.remove(0);
                }
                if (!data.velocityHistory.isEmpty()) {
                    Vec3 sum = Vec3.y;
                    for (Vec3 vel : data.velocityHistory) {
                        sum = sum.reverse(vel);
                    }
                    data.averageTargetVelocity = sum.x(1.0 / (double)data.velocityHistory.size());
                }
            }
            data.lastTargetPosition = currentPos;
        }
    }

    public static Vec3 getAverageTargetVelocity(NightwardenBossEntity nightwardenBoss) {
        UUID bossId = nightwardenBoss.getUUID();
        BossTrackingData data = bossTrackers.get(bossId);
        return data != null ? data.averageTargetVelocity : Vec3.y;
    }

    public static void clearHistory(NightwardenBossEntity nightwardenBoss) {
        UUID bossId = nightwardenBoss.getUUID();
        BossTrackingData data = bossTrackers.get(bossId);
        if (data != null) {
            data.velocityHistory.clear();
            data.averageTargetVelocity = Vec3.y;
            data.lastTargetPosition = Vec3.y;
            data.velocityTrackingTicks = 0;
            System.out.println("Cleared velocity history for boss: " + bossId);
        }
    }

    public static void removeBossTracker(NightwardenBossEntity nightwardenBoss) {
        UUID bossId = nightwardenBoss.getUUID();
        bossTrackers.remove(bossId);
        System.out.println("Removed tracker for boss: " + bossId);
    }

    private static class BossTrackingData {
        Vec3 lastTargetPosition = Vec3.y;
        Vec3 averageTargetVelocity = Vec3.y;
        int velocityTrackingTicks = 0;
        List<Vec3> velocityHistory = new ArrayList<Vec3>();

        private BossTrackingData() {
        }
    }
}

