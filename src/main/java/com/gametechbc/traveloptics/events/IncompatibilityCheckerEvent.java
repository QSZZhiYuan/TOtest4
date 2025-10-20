/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.player.Player
 *  net.minecraftforge.event.entity.player.PlayerEvent$PlayerLoggedInEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.ModList
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 */
package com.gametechbc.traveloptics.events;

import com.gametechbc.traveloptics.config.CommonConfig;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid="traveloptics", bus=Mod.EventBusSubscriber.Bus.FORGE)
public class IncompatibilityCheckerEvent {
    private static final String BASE_TAG = "FirstLogin_TOMod_";
    private static final Map<String, String> INCOMPATIBLE_MODS = Map.of("epicfight", "Epic Fight", "optifine", "OptiFine", "armorunlocked", "Armor Unlocked", "canary", "Canary", "doespotatotick", "Does Potato Tick", "doesittick", "Does It Tick");

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (player instanceof ServerPlayer) {
            ServerPlayer player2 = (ServerPlayer)player;
            if (((Boolean)CommonConfig.showWelcomeMessage.get()).booleanValue()) {
                CompoundTag playerData = player2.getPersistentData();
                CompoundTag persistentTag = playerData.getCompound("PlayerPersisted");
                List<String> foundMods = ModList.get().getMods().stream().map(mod -> mod.getModId().toLowerCase()).filter(INCOMPATIBLE_MODS::containsKey).filter(modId -> !persistentTag.getBoolean(BASE_TAG + modId)).toList();
                if (!foundMods.isEmpty()) {
                    String modList = foundMods.stream().map(INCOMPATIBLE_MODS::get).collect(Collectors.joining(", "));
                    String warningMessage = "\u00a7cT.O Magic 'n Extras: Thanks for Downloading! We have found " + modList + (foundMods.size() == 1 ? " mod in your list. We are not fully compatible with this mod, check faq-from-dev channel on our discord for detailed information." : " mods in your list. We are not fully compatible with these mods, check faq-from-dev channel on our discord for detailed information.");
                    player2.sendSystemMessage((Component)Component.score((String)warningMessage));
                    foundMods.forEach(modId -> persistentTag.accept(BASE_TAG + modId, true));
                } else if (!persistentTag.getBoolean("FirstLogin_TOMod_welcome")) {
                    player2.sendSystemMessage((Component)Component.score((String)"\u00a7eThank you for downloading T.O Magic 'n Extras! If you find major bugs, please report them on our Discord."));
                    persistentTag.accept("FirstLogin_TOMod_welcome", true);
                }
                playerData.accept("PlayerPersisted", (Tag)persistentTag);
            }
        }
    }
}

