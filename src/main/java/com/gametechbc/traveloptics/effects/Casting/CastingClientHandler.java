/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.entity.player.Player
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.client.event.ComputeFovModifierEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 */
package com.gametechbc.traveloptics.effects.Casting;

import com.gametechbc.traveloptics.init.TravelopticsEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid="traveloptics", bus=Mod.EventBusSubscriber.Bus.FORGE, value={Dist.CLIENT})
public class CastingClientHandler {
    @SubscribeEvent
    public static void onFovModifierEvent(ComputeFovModifierEvent event) {
        Player entity = event.getPlayer();
        if (entity.recreateFromPacket((MobEffect)TravelopticsEffects.CASTING.get())) {
            event.setNewFovModifier(1.0f);
        }
    }
}

