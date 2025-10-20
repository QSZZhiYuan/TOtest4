/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerBossEvent
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.BossEvent$BossBarColor
 *  net.minecraft.world.BossEvent$BossBarOverlay
 */
package com.gametechbc.traveloptics.api.entity;

import com.gametechbc.traveloptics.init.TravelopticsMessages;
import com.gametechbc.traveloptics.network.MessageUpdateBossBar;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;

public class TravelopticsBossInfo
extends ServerBossEvent {
    private int renderType;

    public TravelopticsBossInfo(Component component, BossEvent.BossBarColor bossBarColor, boolean dark, int renderType) {
        super(component, bossBarColor, BossEvent.BossBarOverlay.PROGRESS);
        this.setDarkenScreen(dark);
        this.renderType = renderType;
    }

    public void setRenderType(int renderType) {
        if (renderType != this.renderType) {
            this.renderType = renderType;
            TravelopticsMessages.sendMSGToAll(new MessageUpdateBossBar(this.getId(), renderType));
        }
    }

    public int getRenderType() {
        return this.renderType;
    }

    public void addPlayer(ServerPlayer serverPlayer) {
        TravelopticsMessages.sendNonLocal(new MessageUpdateBossBar(this.getId(), this.renderType), serverPlayer);
        super.addPlayer(serverPlayer);
    }

    public void removeAllPlayers(ServerPlayer serverPlayer) {
        TravelopticsMessages.sendNonLocal(new MessageUpdateBossBar(this.getId(), -1), serverPlayer);
        super.removeAllPlayers(serverPlayer);
    }
}

