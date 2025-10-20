/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraftforge.fml.LogicalSide
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.gametechbc.traveloptics.network;

import com.gametechbc.traveloptics.TravelopticsMod;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public class MessageUpdateBossBar {
    private UUID bossBar;
    private int renderType;

    public MessageUpdateBossBar(UUID bossBar, int renderType) {
        this.bossBar = bossBar;
        this.renderType = renderType;
    }

    public MessageUpdateBossBar(FriendlyByteBuf buf) {
        this.bossBar = buf.readUUID();
        this.renderType = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeProperty(this.bossBar);
        buf.writeInt(this.renderType);
    }

    public static MessageUpdateBossBar read(FriendlyByteBuf buf) {
        return new MessageUpdateBossBar(buf);
    }

    public static void write(MessageUpdateBossBar message, FriendlyByteBuf buf) {
        message.toBytes(buf);
    }

    public static void handle(MessageUpdateBossBar message, Supplier<NetworkEvent.Context> context) {
        context.get().setPacketHandled(true);
        context.get().enqueueWork(() -> {
            ServerPlayer playerSided = ((NetworkEvent.Context)context.get()).getSender();
            if (((NetworkEvent.Context)context.get()).getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                playerSided = TravelopticsMod.PROXY.getClientSidePlayer();
            }
            if (message.renderType == -1) {
                TravelopticsMod.PROXY.removeBossBarRender(message.bossBar);
            } else {
                TravelopticsMod.PROXY.setBossBarRender(message.bossBar, message.renderType);
            }
        });
    }
}

