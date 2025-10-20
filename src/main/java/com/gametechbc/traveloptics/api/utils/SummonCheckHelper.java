/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.entity.mobs.MagicSummon
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.api.utils;

import io.redspace.ironsspellbooks.entity.mobs.MagicSummon;
import java.util.List;
import java.util.Set;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SummonCheckHelper {
    public static boolean hasActiveSummons(Player player, double range, Set<Class<? extends MagicSummon>> summonRegistry) {
        Level level = player.level();
        if (!(level instanceof ServerLevel)) {
            return false;
        }
        ServerLevel level2 = (ServerLevel)level;
        List summons = level2.getNearbyEntities(LivingEntity.class, player.getBoundingBox().inflate(range), entity -> {
            MagicSummon magicSummon;
            return SummonCheckHelper.isRegisteredSummon(entity, summonRegistry) && entity instanceof MagicSummon && (magicSummon = (MagicSummon)entity).getSummoner() != null && magicSummon.getSummoner().getUUID().equals(player.getUUID());
        });
        return !summons.isEmpty();
    }

    private static boolean isRegisteredSummon(LivingEntity entity, Set<Class<? extends MagicSummon>> summonRegistry) {
        for (Class<? extends MagicSummon> summonClass : summonRegistry) {
            if (!summonClass.isAssignableFrom(entity.getClass())) continue;
            return true;
        }
        return false;
    }
}

