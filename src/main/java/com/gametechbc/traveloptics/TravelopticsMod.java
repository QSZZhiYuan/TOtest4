/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.AddReloadListenerEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.event.TickEvent$ServerTickEvent
 *  net.minecraftforge.eventbus.api.IEventBus
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.DistExecutor
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.event.config.ModConfigEvent$Loading
 *  net.minecraftforge.fml.event.config.ModConfigEvent$Reloading
 *  net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
 *  net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
 *  net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
 *  net.minecraftforge.fml.loading.FMLLoader
 *  net.minecraftforge.fml.loading.FMLPaths
 *  net.minecraftforge.fml.util.thread.SidedThreadGroups
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.jetbrains.annotations.NotNull
 */
package com.gametechbc.traveloptics;

import com.gametechbc.traveloptics.ClientProxy;
import com.gametechbc.traveloptics.CommonProxy;
import com.gametechbc.traveloptics.api.init.TravelopticsAttributes;
import com.gametechbc.traveloptics.api.init.TravelopticsSchools;
import com.gametechbc.traveloptics.config.ArmorConfig;
import com.gametechbc.traveloptics.config.ClientConfig;
import com.gametechbc.traveloptics.config.CommonConfig;
import com.gametechbc.traveloptics.config.DependantItemsAttributeConfig;
import com.gametechbc.traveloptics.config.EntityConfig;
import com.gametechbc.traveloptics.config.SpellsConfig;
import com.gametechbc.traveloptics.config.WeaponConfig;
import com.gametechbc.traveloptics.effects.AbyssalStrike.AttackHandler;
import com.gametechbc.traveloptics.effects.Blackout.BlackoutHandler;
import com.gametechbc.traveloptics.effects.Casting.CastingHandler;
import com.gametechbc.traveloptics.effects.FrozenSight.FrozenSightClientHandler;
import com.gametechbc.traveloptics.effects.FrozenSight.FrozenSightHandler;
import com.gametechbc.traveloptics.effects.LingeringStrain.LingeringStrainHandler;
import com.gametechbc.traveloptics.init.TravelopticsBlocks;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.init.TravelopticsMessages;
import com.gametechbc.traveloptics.init.TravelopticsParticles;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.gametechbc.traveloptics.init.TravelopticsTabs;
import com.gametechbc.traveloptics.loot.TravelopticsLootModifiers;
import com.gametechbc.traveloptics.setup.TremorzillaRewardHandler;
import com.gametechbc.traveloptics.util.SummonTypes;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Mod(value="traveloptics")
public class TravelopticsMod {
    public static final Logger LOGGER = LogManager.getLogger(TravelopticsMod.class);
    public static final String MODID = "traveloptics";
    public static CommonProxy PROXY = (CommonProxy)DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<AbstractMap.SimpleEntry<Runnable, Integer>>();

    public TravelopticsMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register((Object)this);
        TravelopticsSounds.REGISTRY.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::onLoad);
        modEventBus.addListener(this::onReload);
        TravelopticsSpells.register(modEventBus);
        TravelopticsEffects.register(modEventBus);
        TravelopticsEntities.register(modEventBus);
        TravelopticsSchools.TRAVELOPTICS_SCHOOLS.register(modEventBus);
        TravelopticsAttributes.ATTRIBUTES.register(modEventBus);
        TravelopticsParticles.register(modEventBus);
        TravelopticsLootModifiers.register(modEventBus);
        TravelopticsTabs.REGISTRY.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(AttackHandler.class);
        MinecraftForge.EVENT_BUS.register(TremorzillaRewardHandler.class);
        MinecraftForge.EVENT_BUS.register(FrozenSightHandler.class);
        MinecraftForge.EVENT_BUS.register(BlackoutHandler.class);
        MinecraftForge.EVENT_BUS.register(LingeringStrainHandler.class);
        MinecraftForge.EVENT_BUS.register(CastingHandler.class);
        if (FMLLoader.getDist() == Dist.CLIENT) {
            MinecraftForge.EVENT_BUS.register(FrozenSightClientHandler.class);
        }
        TravelopticsItems.ITEMS.register(modEventBus);
        TravelopticsBlocks.BLOCKS.register(modEventBus);
        MinecraftForge.EVENT_BUS.addListener(this::onAddReloadListeners);
        Path configPath = FMLPaths.CONFIGDIR.get().resolve(MODID);
        try {
            Files.createDirectories(configPath, new FileAttribute[0]);
        }
        catch (Exception e) {
            LOGGER.error("Failed to create config directory: " + configPath, (Throwable)e);
        }
        WeaponConfig.loadConfig(WeaponConfig.WEAPON, configPath.resolve("traveloptics-weapons.toml").toString());
        SpellsConfig.loadConfig(SpellsConfig.SPELLS_SPEC, configPath.resolve("traveloptics-spells.toml").toString());
        CommonConfig.loadConfig(CommonConfig.COMMON_SPEC, configPath.resolve("traveloptics-common.toml").toString());
        DependantItemsAttributeConfig.loadConfig(DependantItemsAttributeConfig.DEPENDANT_CONFIG_SPEC, configPath.resolve("traveloptics-dependency_items_attribute.toml").toString());
        ArmorConfig.loadConfig(ArmorConfig.ARMOR_SPEC, configPath.resolve("traveloptics-armors.toml").toString());
        EntityConfig.loadConfig(EntityConfig.ENTITY_SPEC, configPath.resolve("traveloptics-entity.toml").toString());
        DistExecutor.unsafeRunWhenOn((Dist)Dist.CLIENT, () -> () -> ClientConfig.loadConfig(ClientConfig.CLIENT_SPEC, configPath.resolve("traveloptics-client.toml").toString()));
    }

    public static void queueServerWork(int tick, Runnable action) {
        if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
            workQueue.add(new AbstractMap.SimpleEntry<Runnable, Integer>(action, tick));
        }
    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            ArrayList actions = new ArrayList();
            workQueue.forEach(work -> {
                work.setValue((Integer)work.getValue() - 1);
                if ((Integer)work.getValue() == 0) {
                    actions.add(work);
                }
            });
            actions.forEach(e -> ((Runnable)e.getKey()).run());
            workQueue.removeAll(actions);
        }
    }

    public static ResourceLocation id(@NotNull String path) {
        return new ResourceLocation(MODID, path);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        SummonTypes.registerKaijuSummons();
        SummonTypes.registerMinibossSummons();
        SummonTypes.registerGroupSummons();
        TravelopticsMessages.register();
    }

    private void clientSetup(FMLClientSetupEvent event) {
    }

    private void onAddReloadListeners(AddReloadListenerEvent event) {
    }

    private void onLoad(ModConfigEvent.Loading configEvent) {
        if (configEvent.getConfig().getSpec() == WeaponConfig.WEAPON) {
            // empty if block
        }
        if (configEvent.getConfig().getSpec() == SpellsConfig.SPELLS_SPEC) {
            // empty if block
        }
        if (configEvent.getConfig().getSpec() == CommonConfig.COMMON_SPEC) {
            // empty if block
        }
        if (configEvent.getConfig().getSpec() == DependantItemsAttributeConfig.DEPENDANT_CONFIG_SPEC) {
            // empty if block
        }
        if (configEvent.getConfig().getSpec() == ArmorConfig.ARMOR_SPEC) {
            // empty if block
        }
        if (configEvent.getConfig().getSpec() == EntityConfig.ENTITY_SPEC) {
            // empty if block
        }
    }

    private void onReload(ModConfigEvent.Reloading configEvent) {
        if (configEvent.getConfig().getSpec() == WeaponConfig.WEAPON) {
            // empty if block
        }
        if (configEvent.getConfig().getSpec() == SpellsConfig.SPELLS_SPEC) {
            // empty if block
        }
        if (configEvent.getConfig().getSpec() == CommonConfig.COMMON_SPEC) {
            // empty if block
        }
        if (configEvent.getConfig().getSpec() == DependantItemsAttributeConfig.DEPENDANT_CONFIG_SPEC) {
            // empty if block
        }
        if (configEvent.getConfig().getSpec() == ArmorConfig.ARMOR_SPEC) {
            // empty if block
        }
        if (configEvent.getConfig().getSpec() == EntityConfig.ENTITY_SPEC) {
            // empty if block
        }
    }
}

