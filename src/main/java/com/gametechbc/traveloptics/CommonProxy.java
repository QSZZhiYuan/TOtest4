/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 */
package com.gametechbc.traveloptics;

import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid="traveloptics", bus=Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {
    public void init() {
    }

    public Player getClientSidePlayer() {
        return null;
    }

    public void clearSoundCacheFor(Entity entity) {
    }

    public void clearSoundCacheFor(BlockEntity entity) {
    }

    public void playWorldSound(@Nullable Object soundEmitter, byte type) {
    }

    public void removeBossBarRender(UUID bossBar) {
    }

    public void setBossBarRender(UUID bossBar, int renderType) {
    }
}

