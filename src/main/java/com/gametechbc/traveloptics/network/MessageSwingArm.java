/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.items.ILeftClick
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.gametechbc.traveloptics.network;

import com.github.L_Ender.cataclysm.items.ILeftClick;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

public class MessageSwingArm {
    public static final MessageSwingArm INSTANCE = new MessageSwingArm();

    private MessageSwingArm() {
    }

    public MessageSwingArm(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public static MessageSwingArm read(FriendlyByteBuf buf) {
        return new MessageSwingArm(buf);
    }

    public static void write(MessageSwingArm message, FriendlyByteBuf buf) {
        message.toBytes(buf);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().setPacketHandled(true);
        context.get().enqueueWork(() -> {
            ServerPlayer player = ((NetworkEvent.Context)context.get()).getSender();
            if (player != null) {
                ItemStack leftItem = player.getStandingEyeHeight(InteractionHand.OFF_HAND);
                ItemStack rightItem = player.getStandingEyeHeight(InteractionHand.MAIN_HAND);
                if (leftItem.setRepairCost() instanceof ILeftClick) {
                    ((ILeftClick)leftItem.setRepairCost()).onLeftClick(leftItem, (LivingEntity)player);
                }
                if (rightItem.setRepairCost() instanceof ILeftClick) {
                    ((ILeftClick)rightItem.setRepairCost()).onLeftClick(rightItem, (LivingEntity)player);
                }
            }
        });
    }

    public static class Handler {
        public static void handle(MessageSwingArm message, Supplier<NetworkEvent.Context> context) {
            message.handle(context);
        }
    }
}

