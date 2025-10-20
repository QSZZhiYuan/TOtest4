/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraftforge.network.NetworkDirection
 *  net.minecraftforge.network.NetworkRegistry$ChannelBuilder
 *  net.minecraftforge.network.PacketDistributor
 *  net.minecraftforge.network.simple.SimpleChannel
 */
package com.gametechbc.traveloptics.init;

import com.gametechbc.traveloptics.network.BossMessagePacket;
import com.gametechbc.traveloptics.network.MechanizedWraithbladeChargingMessage;
import com.gametechbc.traveloptics.network.MessageArmorKey;
import com.gametechbc.traveloptics.network.MessageSwingArm;
import com.gametechbc.traveloptics.network.MessageUpdateBossBar;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class TravelopticsMessages {
    private static SimpleChannel INSTANCE;
    private static int packetId;

    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net;
        INSTANCE = net = NetworkRegistry.ChannelBuilder.named((ResourceLocation)new ResourceLocation("traveloptics", "messages")).networkProtocolVersion(() -> "1.0").clientAcceptedVersions(s -> true).serverAcceptedVersions(s -> true).simpleChannel();
        net.messageBuilder(MessageArmorKey.class, TravelopticsMessages.id(), NetworkDirection.PLAY_TO_SERVER).decoder(MessageArmorKey::new).encoder(MessageArmorKey::toBytes).consumerMainThread(MessageArmorKey::handle).add();
        net.messageBuilder(MessageSwingArm.class, TravelopticsMessages.id(), NetworkDirection.PLAY_TO_SERVER).decoder(MessageSwingArm::read).encoder(MessageSwingArm::write).consumerMainThread(MessageSwingArm.Handler::handle).add();
        net.messageBuilder(MessageUpdateBossBar.class, TravelopticsMessages.id(), NetworkDirection.PLAY_TO_CLIENT).decoder(MessageUpdateBossBar::read).encoder(MessageUpdateBossBar::write).consumerMainThread(MessageUpdateBossBar::handle).add();
        net.messageBuilder(MechanizedWraithbladeChargingMessage.class, TravelopticsMessages.id(), NetworkDirection.PLAY_TO_CLIENT).decoder(MechanizedWraithbladeChargingMessage::new).encoder(MechanizedWraithbladeChargingMessage::toBytes).consumerMainThread(MechanizedWraithbladeChargingMessage::handle).add();
        net.messageBuilder(BossMessagePacket.class, TravelopticsMessages.id(), NetworkDirection.PLAY_TO_CLIENT).decoder(BossMessagePacket::new).encoder(BossMessagePacket::toBytes).consumerMainThread(BossMessagePacket::handle).add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToAllPlayers(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }

    public static <MSG> void sendMSGToAll(MSG message) {
        TravelopticsMessages.sendToAllPlayers(message);
    }

    public static <MSG> void sendNonLocal(MSG message, ServerPlayer player) {
        TravelopticsMessages.sendToPlayer(message, player);
    }

    public static <MSG> void sendMSGToServer(MSG message) {
        TravelopticsMessages.sendToServer(message);
    }

    public static void sendMechanizedWraithbladeChargingState(ServerPlayer player, boolean isCharging, int chargeProgress, float damage) {
        MechanizedWraithbladeChargingMessage message = new MechanizedWraithbladeChargingMessage(isCharging, chargeProgress, damage);
        TravelopticsMessages.sendToPlayer(message, player);
    }

    public static void sendBossMessage(ServerPlayer player, Component message, int backgroundColor, int duration, boolean refresh, boolean forceIntroAnimation) {
        BossMessagePacket packet = new BossMessagePacket(message, backgroundColor, duration, refresh, forceIntroAnimation);
        TravelopticsMessages.sendToPlayer(packet, player);
    }

    public static void sendBossMessageToRange(Entity source, Component message, int backgroundColor, int duration, double radius, boolean refresh, boolean forceIntroAnimation) {
        if (source.level().isClientSide) {
            return;
        }
        source.level().getNearbyEntities(ServerPlayer.class, source.getBoundingBox().inflate(radius)).forEach(player -> TravelopticsMessages.sendBossMessage(player, message, backgroundColor, duration, refresh, forceIntroAnimation));
    }

    public static void sendBossMessage(ServerPlayer player, Component message, int backgroundColor, int duration, boolean refresh) {
        TravelopticsMessages.sendBossMessage(player, message, backgroundColor, duration, refresh, false);
    }

    public static void sendBossMessageToRange(Entity source, Component message, int backgroundColor, int duration, double radius, boolean refresh) {
        TravelopticsMessages.sendBossMessageToRange(source, message, backgroundColor, duration, radius, refresh, false);
    }

    static {
        packetId = 0;
    }
}

