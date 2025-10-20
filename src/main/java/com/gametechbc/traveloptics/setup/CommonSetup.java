/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.SpawnPlacements$Type
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraftforge.event.entity.EntityAttributeCreationEvent
 *  net.minecraftforge.event.entity.SpawnPlacementRegisterEvent
 *  net.minecraftforge.event.entity.SpawnPlacementRegisterEvent$Operation
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 */
package com.gametechbc.traveloptics.setup;

import com.gametechbc.traveloptics.entity.mobs.EnragedDeadKingBoss;
import com.gametechbc.traveloptics.entity.mobs.aqua_grandmaster.AquaGrandmasterEntity;
import com.gametechbc.traveloptics.entity.mobs.aquamancer.AquamancerEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenBossEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_drop_slam_clone.NightwardenDropSlamCloneEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_explode_clone.NightwardenExplodeCloneEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_slam_clone.NightwardenSlamCloneEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_slash_clone.NightwardenSlashCloneEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_spin_clone.NightwardenSpinCloneEntity;
import com.gametechbc.traveloptics.entity.mobs.testwizard.TestWizardEntity;
import com.gametechbc.traveloptics.entity.mobs.void_tome.VoidTomeEntity;
import com.gametechbc.traveloptics.entity.mobs.voidshelf_golem.VoidshelfGolemEntity;
import com.gametechbc.traveloptics.entity.summons.SummonedAptrgangr;
// import com.gametechbc.traveloptics.entity.summons.SummonedAtlatitan; // REMOVED (AC dependency)
import com.gametechbc.traveloptics.entity.summons.SummonedDeepling;
import com.gametechbc.traveloptics.entity.summons.SummonedDraugr;
import com.gametechbc.traveloptics.entity.summons.SummonedEliteDraugr;
import com.gametechbc.traveloptics.entity.summons.SummonedEnderGolem;
// import com.gametechbc.traveloptics.entity.summons.SummonedForsaken; // REMOVED (AC dependency)
// import com.gametechbc.traveloptics.entity.summons.SummonedHullbreaker; // REMOVED (AC dependency)
import com.gametechbc.traveloptics.entity.summons.SummonedIgnitedBerserker;
import com.gametechbc.traveloptics.entity.summons.SummonedIgnitedRevenant;
import com.gametechbc.traveloptics.entity.summons.SummonedKobolediator;
import com.gametechbc.traveloptics.entity.summons.SummonedKoboleton;
// import com.gametechbc.traveloptics.entity.summons.SummonedMagnetron; // REMOVED (AC dependency)
import com.gametechbc.traveloptics.entity.summons.SummonedRoyalDraugr;
// import com.gametechbc.traveloptics.entity.summons.SummonedTheProwler; // REMOVED (AC dependency)
import com.gametechbc.traveloptics.entity.summons.SummonedTheWatcher;
// import com.gametechbc.traveloptics.entity.summons.SummonedTremorsaurus; // REMOVED (AC dependency)
// import com.gametechbc.traveloptics.entity.summons.SummonedVallumraptor; // REMOVED (AC dependency)
// import com.gametechbc.traveloptics.entity.summons.SummonedVesper; // REMOVED (AC dependency)
import com.gametechbc.traveloptics.entity.summons.SummonedVoidTome;
import com.gametechbc.traveloptics.entity.summons.SummonedWadjet;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid="traveloptics", bus=Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetup {
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put((EntityType)TravelopticsEntities.TEST_WIZARD.get(), TestWizardEntity.prepareAttributes().build());
        event.put((EntityType)TravelopticsEntities.NIGHTWARDEN_BOSS.get(), NightwardenBossEntity.prepareAttributes().build());
        event.put((EntityType)TravelopticsEntities.SUMMONED_KOBOLETON.get(), SummonedKoboleton.koboleton().build());
        event.put((EntityType)TravelopticsEntities.SUMMONED_DEEPLING.get(), SummonedDeepling.deepling().build());
        event.put((EntityType)TravelopticsEntities.SUMMONED_KOBOLEDIATOR.get(), SummonedKobolediator.kobolediator().build());
        event.put((EntityType)TravelopticsEntities.SUMMONED_WADJET.get(), SummonedWadjet.wadjet().build());
        // event.put((EntityType)TravelopticsEntities.SUMMONED_MAGNETRON.get(), SummonedMagnetron.createAttributes().build()); // REMOVED (AC dependency)
        // event.put((EntityType)TravelopticsEntities.SUMMONED_THE_PROWLER.get(), SummonedTheProwler.the_prowler().build()); // REMOVED (AC dependency)
        event.put((EntityType)TravelopticsEntities.SUMMONED_THE_WATCHER.get(), SummonedTheWatcher.the_watcher().build());
        // event.put((EntityType)TravelopticsEntities.SUMMONED_GUM_WORM.get(), GumWormEntity.createAttributes().build()); // REMOVED (AC dependency)
        // event.put((EntityType)TravelopticsEntities.SUMMONED_FORSAKEN.get(), SummonedForsaken.createAttributes().build()); // REMOVED (AC dependency)
        // event.put((EntityType)TravelopticsEntities.SUMMONED_VALLUMRAPTOR.get(), SummonedVallumraptor.createAttributes().build()); // REMOVED (AC dependency)
        // event.put((EntityType)TravelopticsEntities.SUMMONED_TREMORSAURUS.get(), SummonedTremorsaurus.createAttributes().build()); // REMOVED (AC dependency)
        // event.put((EntityType)TravelopticsEntities.SUMMONED_ATLATITAN.get(), SummonedAtlatitan.createAttributes().build()); // REMOVED (AC dependency)
        // event.put((EntityType)TravelopticsEntities.SUMMONED_VESPER.get(), SummonedVesper.createAttributes().build()); // REMOVED (AC dependency)
        event.put((EntityType)TravelopticsEntities.SUMMONED_IGNITED_BERSERKER.get(), SummonedIgnitedBerserker.ignited_berserker().build());
        event.put((EntityType)TravelopticsEntities.SUMMONED_IGNITED_REVENANT.get(), SummonedIgnitedRevenant.ignited_revenant().build());
        event.put((EntityType)TravelopticsEntities.SUMMONED_ENDER_GOLEM.get(), SummonedEnderGolem.ender_golem().build());
        event.put((EntityType)TravelopticsEntities.SUMMONED_DRAUGR.get(), SummonedDraugr.draugr().build());
        event.put((EntityType)TravelopticsEntities.SUMMONED_ROYAL_DRAUGR.get(), SummonedRoyalDraugr.royal_draugr().build());
        event.put((EntityType)TravelopticsEntities.SUMMONED_ELITE_DRAUGR.get(), SummonedEliteDraugr.elite_draugr().build());
        event.put((EntityType)TravelopticsEntities.SUMMONED_APTRGANGR.get(), SummonedAptrgangr.aptrgangr().build());
        event.put((EntityType)TravelopticsEntities.ENRAGED_DEAD_KING.get(), EnragedDeadKingBoss.deadkingAttributes().build());
        // event.put((EntityType)TravelopticsEntities.SUMMONED_HULLBREAKER.get(), SummonedHullbreaker.createAttributes().build()); // REMOVED (AC dependency)
        event.put((EntityType)TravelopticsEntities.AQUAMANCER_ENTITY.get(), AquamancerEntity.prepareAttributes().build());
        event.put((EntityType)TravelopticsEntities.AQUA_GRANDMASTER_ENTITY.get(), AquaGrandmasterEntity.prepareAttributes().build());
        event.put((EntityType)TravelopticsEntities.VOID_TOME_ENTITY.get(), VoidTomeEntity.prepareAttributes().build());
        event.put((EntityType)TravelopticsEntities.SUMMONED_VOID_TOME.get(), SummonedVoidTome.prepareAttributes().build());
        event.put((EntityType)TravelopticsEntities.NIGHTWARDEN_EXPLODE_CLONE.get(), NightwardenExplodeCloneEntity.prepareAttributes().build());
        event.put((EntityType)TravelopticsEntities.NIGHTWARDEN_SLASH_CLONE.get(), NightwardenSlashCloneEntity.prepareAttributes().build());
        event.put((EntityType)TravelopticsEntities.VOIDSHELF_GOLEM_ENTITY.get(), VoidshelfGolemEntity.prepareAttributes().build());
        event.put((EntityType)TravelopticsEntities.NIGHTWARDEN_DEFEATED.get(), NightwardenBossEntity.prepareAttributes().build());
        event.put((EntityType)TravelopticsEntities.FADING_MAGE.get(), NightwardenBossEntity.prepareAttributes().build());
        event.put((EntityType)TravelopticsEntities.NIGHTWARDEN_SLAM_CLONE.get(), NightwardenSlamCloneEntity.prepareAttributes().build());
        event.put((EntityType)TravelopticsEntities.NIGHTWARDEN_SPIN_CLONE.get(), NightwardenSpinCloneEntity.prepareAttributes().build());
        event.put((EntityType)TravelopticsEntities.NIGHTWARDEN_DROP_SLAM_CLONE.get(), NightwardenDropSlamCloneEntity.prepareAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register((EntityType)TravelopticsEntities.AQUA_GRANDMASTER_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::maybeDisableShield, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}

