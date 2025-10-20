/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.gametechbc.traveloptics.network;

import com.gametechbc.traveloptics.overlay.weapon_overlay.MechanizedWraithbladeOverlay;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class MechanizedWraithbladeChargingMessage {
    private final boolean isCharging;
    private final int chargeProgress;
    private final float realTimeDamage;

    public MechanizedWraithbladeChargingMessage(boolean isCharging, int chargeProgress, float realTimeDamage) {
        this.isCharging = isCharging;
        this.chargeProgress = chargeProgress;
        this.realTimeDamage = realTimeDamage;
    }

    public MechanizedWraithbladeChargingMessage(FriendlyByteBuf buf) {
        this.isCharging = buf.readBoolean();
        this.chargeProgress = buf.readInt();
        this.realTimeDamage = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.isCharging);
        buf.writeInt(this.chargeProgress);
        buf.writeFloat(this.realTimeDamage);
    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> MechanizedWraithbladeOverlay.updateWeaponNetworkState(this.isCharging, this.chargeProgress, this.realTimeDamage));
        return true;
    }
}

