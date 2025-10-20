/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.KeyMapping
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.gametechbc.traveloptics.network;

import com.gametechbc.traveloptics.api.utils.IKeybindArmor;
import com.gametechbc.traveloptics.init.TravelopticsKeybinds;
import java.util.function.Supplier;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

public class MessageArmorKey {
    public int equipmentSlot;
    public int playerId;
    public String keyName;

    public MessageArmorKey(int equipmentSlot, int playerId, String keyName) {
        this.equipmentSlot = equipmentSlot;
        this.playerId = playerId;
        this.keyName = keyName;
    }

    public MessageArmorKey(FriendlyByteBuf buf) {
        this.equipmentSlot = buf.readInt();
        this.playerId = buf.readInt();
        this.keyName = buf.readUtf();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.equipmentSlot);
        buf.writeInt(this.playerId);
        buf.writeProperty(this.keyName);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            EquipmentSlot slot;
            ItemStack stack;
            Item patt1615$temp;
            ServerPlayer player = ((NetworkEvent.Context)context.get()).getSender();
            if (player != null && (patt1615$temp = (stack = player.shouldRemoveSoulSpeed(slot = EquipmentSlot.values()[Mth.outFromOrigin((int)this.equipmentSlot, (int)0, (int)(EquipmentSlot.values().length - 1))])).setRepairCost()) instanceof IKeybindArmor) {
                IKeybindArmor armor = (IKeybindArmor)patt1615$temp;
                KeyMapping key = this.getKeyFromName(this.keyName);
                if (key != null) {
                    armor.onKeyPacket((Player)player, stack, key);
                }
            }
        });
        context.get().setPacketHandled(true);
    }

    private KeyMapping getKeyFromName(String keyName) {
        return switch (keyName) {
            case "helmet" -> TravelopticsKeybinds.KEY_Z;
            case "chestplate" -> TravelopticsKeybinds.KEY_X;
            case "leggings" -> TravelopticsKeybinds.KEY_C;
            case "boots" -> TravelopticsKeybinds.KEY_B;
            default -> null;
        };
    }
}

