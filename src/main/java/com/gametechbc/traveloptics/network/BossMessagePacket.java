/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.chat.Component
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.gametechbc.traveloptics.network;

import com.gametechbc.traveloptics.overlay.BossMessageOverlay;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

public class BossMessagePacket {
    private final Component message;
    private final int backgroundColor;
    private final int duration;
    private final boolean refresh;
    private final boolean forceIntroAnimation;

    public BossMessagePacket(Component message, int backgroundColor, int duration, boolean refresh, boolean forceIntroAnimation) {
        this.message = message;
        this.backgroundColor = backgroundColor;
        this.duration = duration;
        this.refresh = refresh;
        this.forceIntroAnimation = forceIntroAnimation;
    }

    public BossMessagePacket(FriendlyByteBuf buf) {
        this.message = buf.readComponent();
        this.backgroundColor = buf.readInt();
        this.duration = buf.readInt();
        this.refresh = buf.readBoolean();
        this.forceIntroAnimation = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeProperty(this.message);
        buf.writeInt(this.backgroundColor);
        buf.writeInt(this.duration);
        buf.writeBoolean(this.refresh);
        buf.writeBoolean(this.forceIntroAnimation);
    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> BossMessageOverlay.displayMessage(this.message, this.backgroundColor, this.duration, this.refresh, this.forceIntroAnimation));
        return true;
    }
}

