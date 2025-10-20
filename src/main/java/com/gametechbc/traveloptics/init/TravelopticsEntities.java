/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EntityType$Builder
 *  net.minecraft.world.entity.MobCategory
 *  net.minecraftforge.eventbus.api.IEventBus
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package com.gametechbc.traveloptics.init;

import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedAbyssBlastEntity;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedDeathLaserBeamEntity;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedFlameStrikeEntity;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedFlareBombEntity;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedIgnisFireballEntity;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedPhantomArrowEntity;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedPhantomHalberdEntity;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedSandstormEntity;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedStormSerpentEntity;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedWaterBoltEntity;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedWaterSpearEntity;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedWitherHomingMissileEntity;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedWitherHowitzerEntity;
import com.gametechbc.traveloptics.entity.extended_projectiles.extended_wave.ExtendedWaveEntity;
import com.gametechbc.traveloptics.entity.extended_projectiles.extended_wave.ReturningWaveEntity;
import com.gametechbc.traveloptics.entity.misc.TOFollowingScreenShakeEntity;
import com.gametechbc.traveloptics.entity.misc.TOPowerInversionEntity;
import com.gametechbc.traveloptics.entity.misc.TOScreenFlashEntity;
import com.gametechbc.traveloptics.entity.misc.TOScreenShakeEntity;
import com.gametechbc.traveloptics.entity.misc.stack_entity.StackEntity;
import com.gametechbc.traveloptics.entity.mobs.EnragedDeadKingBoss;
import com.gametechbc.traveloptics.entity.mobs.aqua_grandmaster.AquaGrandmasterEntity;
import com.gametechbc.traveloptics.entity.mobs.aquamancer.AquamancerEntity;
import com.gametechbc.traveloptics.entity.mobs.fading_mage.FadingMageEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenBossEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.misc.EndEruptionEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_drop_slam_clone.NightwardenDropSlamCloneEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_explode_clone.NightwardenExplodeCloneEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_slam_clone.NightwardenSlamCloneEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_slash_clone.NightwardenSlashCloneEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_spin_clone.NightwardenSpinCloneEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_defeated.NightwardenDefeatedEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_slash_visual.NightwardenVisualSlashEntity;
import com.gametechbc.traveloptics.entity.mobs.testwizard.TestWizardEntity;
import com.gametechbc.traveloptics.entity.mobs.void_tome.VoidTomeEntity;
import com.gametechbc.traveloptics.entity.mobs.voidshelf_golem.VoidshelfGolemEntity;
import com.gametechbc.traveloptics.entity.projectiles.AcidRainAoe;
import com.gametechbc.traveloptics.entity.projectiles.AerialCollapseVisualEntity;
import com.gametechbc.traveloptics.entity.projectiles.AshenBreathProjectile;
import com.gametechbc.traveloptics.entity.projectiles.BlackoutAntiMagicField;
import com.gametechbc.traveloptics.entity.projectiles.BlizzardAoe;
import com.gametechbc.traveloptics.entity.projectiles.BubbleSprayProjectile;
import com.gametechbc.traveloptics.entity.projectiles.CursedVolleyEntity;
import com.gametechbc.traveloptics.entity.projectiles.EnsnareEntity;
import com.gametechbc.traveloptics.entity.projectiles.RainfallAoe;
import com.gametechbc.traveloptics.entity.projectiles.StellarTrailAoeEntity;
import com.gametechbc.traveloptics.entity.projectiles.TectonicRiftEntity;
import com.gametechbc.traveloptics.entity.projectiles.WaterFieldEntity;
import com.gametechbc.traveloptics.entity.projectiles.aqua_trident.EternalMaelstromTridentEntity;
import com.gametechbc.traveloptics.entity.projectiles.aqua_vortex.AquaVortexEntity;
import com.gametechbc.traveloptics.entity.projectiles.asteroid.AsteroidEntity;
import com.gametechbc.traveloptics.entity.projectiles.asteroid.AsteroidImpactCraterEntity;
import com.gametechbc.traveloptics.entity.projectiles.coral_bolt.BlueCoralBoltProjectile;
import com.gametechbc.traveloptics.entity.projectiles.coral_bolt.PinkCoralBoltProjectile;
import com.gametechbc.traveloptics.entity.projectiles.coral_bolt.RedCoralBoltProjectile;
import com.gametechbc.traveloptics.entity.projectiles.coral_bolt.YellowCoralBoltProjectile;
import com.gametechbc.traveloptics.entity.projectiles.dimensional_spike.DimensionalSpikeEntity;
import com.gametechbc.traveloptics.entity.projectiles.dragon_spirit.DragonSpiritEntity;
import com.gametechbc.traveloptics.entity.projectiles.dragon_spirit_spell_entity.DragonSpiritSpellEntity;
import com.gametechbc.traveloptics.entity.projectiles.end_eruption_bomb.EruptionBombProjectileEntity;
import com.gametechbc.traveloptics.entity.projectiles.flood_slash.FloodSlashProjectile;
import com.gametechbc.traveloptics.entity.projectiles.gyro_slash.GyroSlashProjectile;
import com.gametechbc.traveloptics.entity.projectiles.gyro_slash.GyroSlashVisual;
import com.gametechbc.traveloptics.entity.projectiles.hydroshot.HydroshotProjectile;
import com.gametechbc.traveloptics.entity.projectiles.maelstrom.MaelstromEntity;
import com.gametechbc.traveloptics.entity.projectiles.maelstrom_trident_phantom.MaelstromTridentPhantomEntity;
import com.gametechbc.traveloptics.entity.projectiles.psychic_bolt.PsychicBoltProjectile;
import com.gametechbc.traveloptics.entity.projectiles.reversal.ReversalEntity;
import com.gametechbc.traveloptics.entity.projectiles.stellothorn_projectile.StellothornProjectileEntity;
import com.gametechbc.traveloptics.entity.projectiles.supernova.dying_star.DyingStarEntity;
import com.gametechbc.traveloptics.entity.projectiles.supernova.supermassive_black_hole.SupermassiveBlackHoleEntity;
import com.gametechbc.traveloptics.entity.projectiles.void_slash.VoidSlashProjectile;
import com.gametechbc.traveloptics.entity.summons.SummonedAptrgangr;
    // REMOVED (AC dependency): import com.gametechbc.traveloptics.entity.summons.SummonedAtlatitan;
import com.gametechbc.traveloptics.entity.summons.SummonedDeepling;
import com.gametechbc.traveloptics.entity.summons.SummonedDraugr;
import com.gametechbc.traveloptics.entity.summons.SummonedEliteDraugr;
import com.gametechbc.traveloptics.entity.summons.SummonedEnderGolem;
    // REMOVED (AC dependency): import com.gametechbc.traveloptics.entity.summons.SummonedForsaken;
    // REMOVED (AC dependency): import com.gametechbc.traveloptics.entity.summons.SummonedGumWorm;
    // REMOVED (AC dependency): import com.gametechbc.traveloptics.entity.summons.SummonedHullbreaker;
import com.gametechbc.traveloptics.entity.summons.SummonedIgnitedBerserker;
import com.gametechbc.traveloptics.entity.summons.SummonedIgnitedRevenant;
import com.gametechbc.traveloptics.entity.summons.SummonedKobolediator;
import com.gametechbc.traveloptics.entity.summons.SummonedKoboleton;
    // REMOVED (AC dependency): import com.gametechbc.traveloptics.entity.summons.SummonedMagnetron;
import com.gametechbc.traveloptics.entity.summons.SummonedRoyalDraugr;
    // REMOVED (AC dependency): import com.gametechbc.traveloptics.entity.summons.SummonedTheProwler;
import com.gametechbc.traveloptics.entity.summons.SummonedTheWatcher;
    // REMOVED (AC dependency): import com.gametechbc.traveloptics.entity.summons.SummonedTremorsaurus;
    // REMOVED (AC dependency): import com.gametechbc.traveloptics.entity.summons.SummonedVallumraptor;
    // REMOVED (AC dependency): import com.gametechbc.traveloptics.entity.summons.SummonedVesper;
import com.gametechbc.traveloptics.entity.summons.SummonedVoidTome;
import com.gametechbc.traveloptics.entity.summons.SummonedWadjet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid="traveloptics", bus=Mod.EventBusSubscriber.Bus.MOD)
public class TravelopticsEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create((IForgeRegistry)ForgeRegistries.ENTITY_TYPES, (String)"traveloptics");
    public static final RegistryObject<EntityType<TestWizardEntity>> TEST_WIZARD = ENTITIES.register("test_wizard", () -> EntityType.Builder.build(TestWizardEntity::new, (MobCategory)MobCategory.MONSTER).build(0.6f, 1.8f).build(64).build(new ResourceLocation("traveloptics", "test_wizard").toString()));
    public static final RegistryObject<EntityType<NightwardenBossEntity>> NIGHTWARDEN_BOSS = ENTITIES.register("the_nightwarden", () -> EntityType.Builder.build(NightwardenBossEntity::new, (MobCategory)MobCategory.MONSTER).build(1.6f, 3.2f).build(64).setCustomClientFactory(NightwardenBossEntity::new).build(new ResourceLocation("traveloptics", "the_nightwarden").toString()));
    public static final RegistryObject<EntityType<NightwardenDefeatedEntity>> NIGHTWARDEN_DEFEATED = ENTITIES.register("the_nightwarden_defeated", () -> EntityType.Builder.build(NightwardenDefeatedEntity::new, (MobCategory)MobCategory.MISC).build(1.7f, 2.1f).build(64).build(new ResourceLocation("traveloptics", "the_nightwarden_defeated").toString()));
    public static final RegistryObject<EntityType<FadingMageEntity>> FADING_MAGE = ENTITIES.register("fading_mage", () -> EntityType.Builder.build(FadingMageEntity::new, (MobCategory)MobCategory.MISC).build(0.8f, 2.4f).build(64).build(new ResourceLocation("traveloptics", "fading_mage").toString()));
    public static final RegistryObject<EntityType<AquamancerEntity>> AQUAMANCER_ENTITY = ENTITIES.register("aquamancer", () -> EntityType.Builder.build(AquamancerEntity::new, (MobCategory)MobCategory.MONSTER).build(0.6f, 2.0f).build(64).build(new ResourceLocation("traveloptics", "aquamancer").toString()));
    public static final RegistryObject<EntityType<VoidTomeEntity>> VOID_TOME_ENTITY = ENTITIES.register("void_tome", () -> EntityType.Builder.build(VoidTomeEntity::new, (MobCategory)MobCategory.CREATURE).build(0.8f, 0.8f).build(64).build(new ResourceLocation("traveloptics", "void_tome").toString()));
    public static final RegistryObject<EntityType<VoidshelfGolemEntity>> VOIDSHELF_GOLEM_ENTITY = ENTITIES.register("voidshelf_golem", () -> EntityType.Builder.build(VoidshelfGolemEntity::new, (MobCategory)MobCategory.MISC).build(1.0f, 2.2f).build(64).build(new ResourceLocation("traveloptics", "voidshelf_golem").toString()));
    public static final RegistryObject<EntityType<AquaGrandmasterEntity>> AQUA_GRANDMASTER_ENTITY = ENTITIES.register("aqua_grandmaster", () -> EntityType.Builder.build(AquaGrandmasterEntity::new, (MobCategory)MobCategory.MONSTER).build(0.6f, 2.0f).build(64).build(new ResourceLocation("traveloptics", "aqua_grandmaster").toString()));
    public static final RegistryObject<EntityType<NightwardenExplodeCloneEntity>> NIGHTWARDEN_EXPLODE_CLONE = ENTITIES.register("nightwarden_explode_clone_entity", () -> EntityType.Builder.build(NightwardenExplodeCloneEntity::new, (MobCategory)MobCategory.MISC).build(1.6f, 3.2f).build(64).build(new ResourceLocation("traveloptics", "nightwarden_explode_clone_entity").toString()));
    public static final RegistryObject<EntityType<NightwardenSlashCloneEntity>> NIGHTWARDEN_SLASH_CLONE = ENTITIES.register("nightwarden_slash_clone_entity", () -> EntityType.Builder.build(NightwardenSlashCloneEntity::new, (MobCategory)MobCategory.MISC).build(1.6f, 3.2f).build(64).build(new ResourceLocation("traveloptics", "nightwarden_slash_clone_entity").toString()));
    public static final RegistryObject<EntityType<NightwardenSlamCloneEntity>> NIGHTWARDEN_SLAM_CLONE = ENTITIES.register("nightwarden_slam_clone_entity", () -> EntityType.Builder.build(NightwardenSlamCloneEntity::new, (MobCategory)MobCategory.MISC).build(1.6f, 3.2f).build(64).build(new ResourceLocation("traveloptics", "nightwarden_slam_clone_entity").toString()));
    public static final RegistryObject<EntityType<NightwardenSpinCloneEntity>> NIGHTWARDEN_SPIN_CLONE = ENTITIES.register("nightwarden_spin_clone_entity", () -> EntityType.Builder.build(NightwardenSpinCloneEntity::new, (MobCategory)MobCategory.MISC).build(1.6f, 3.2f).build(64).build(new ResourceLocation("traveloptics", "nightwarden_spin_clone_entity").toString()));
    public static final RegistryObject<EntityType<NightwardenDropSlamCloneEntity>> NIGHTWARDEN_DROP_SLAM_CLONE = ENTITIES.register("nightwarden_drop_slam_clone_entity", () -> EntityType.Builder.build(NightwardenDropSlamCloneEntity::new, (MobCategory)MobCategory.MISC).build(1.6f, 3.2f).build(64).build(new ResourceLocation("traveloptics", "nightwarden_drop_slam_clone_entity").toString()));
    public static final RegistryObject<EntityType<EternalMaelstromTridentEntity>> ETERNAL_MAELSTROM_TRIDENT_ENTITY = ENTITIES.register("eternal_maelstrom_trident_entity", () -> EntityType.Builder.build(EternalMaelstromTridentEntity::new, (MobCategory)MobCategory.MISC).build(0.5f, 0.5f).setCustomClientFactory(EternalMaelstromTridentEntity::new).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).fireImmune().build("eternal_maelstrom_trident_entity"));
    public static final RegistryObject<EntityType<StellothornProjectileEntity>> STELLOTHORN_PROJECTILE = ENTITIES.register("stellothorn_projectile", () -> EntityType.Builder.build(StellothornProjectileEntity::new, (MobCategory)MobCategory.MISC).build(2.2f, 0.4f).setCustomClientFactory(StellothornProjectileEntity::new).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).fireImmune().build("stellothorn_projectile"));
    public static final RegistryObject<EntityType<EruptionBombProjectileEntity>> END_ERUPTION_BOMB = ENTITIES.register("end_eruption_bomb", () -> EntityType.Builder.build(EruptionBombProjectileEntity::new, (MobCategory)MobCategory.MISC).build(0.5f, 0.5f).build(4).build(new ResourceLocation("traveloptics", "end_eruption_bomb").toString()));
    public static final RegistryObject<EntityType<MaelstromEntity>> MAELSTROM_ENTITY = ENTITIES.register("maelstrom_entity", () -> EntityType.Builder.build(MaelstromEntity::new, (MobCategory)MobCategory.MISC).build(4.0f, 0.8f).build(64).build(new ResourceLocation("traveloptics", "maelstrom_entity").toString()));
    public static final RegistryObject<EntityType<ReversalEntity>> REVERSAL = ENTITIES.register("reversal", () -> EntityType.Builder.build(ReversalEntity::new, (MobCategory)MobCategory.MISC).build(5.0f, 1.0f).build(64).build(new ResourceLocation("traveloptics", "reversal").toString()));
    public static final RegistryObject<EntityType<SupermassiveBlackHoleEntity>> SUPERMASSIVE_BLACK_HOLE = ENTITIES.register("supermassive_black_hole", () -> EntityType.Builder.build(SupermassiveBlackHoleEntity::new, (MobCategory)MobCategory.MISC).build(12.0f, 6.0f).build(64).build(new ResourceLocation("traveloptics", "supermassive_black_hole").toString()));
    public static final RegistryObject<EntityType<DyingStarEntity>> DYING_STAR = ENTITIES.register("dying_star", () -> EntityType.Builder.build(DyingStarEntity::new, (MobCategory)MobCategory.MISC).build(8.0f, 8.0f).build(64).build(new ResourceLocation("traveloptics", "dying_star").toString()));
    public static final RegistryObject<EntityType<NightwardenVisualSlashEntity>> NIGHTWARDEN_SLASH_VISUAL_ENTITY = ENTITIES.register("nightwarden_slash_visual_entity", () -> EntityType.Builder.build(NightwardenVisualSlashEntity::new, (MobCategory)MobCategory.MISC).build(5.0f, 1.0f).build(64).build(new ResourceLocation("traveloptics", "nightwarden_slash_visual_entity").toString()));
    public static final RegistryObject<EntityType<GyroSlashVisual>> GYRO_SLASH_VISUAL = ENTITIES.register("gyro_slash_visual", () -> EntityType.Builder.build(GyroSlashVisual::new, (MobCategory)MobCategory.MISC).build(5.0f, 1.0f).build(64).build(new ResourceLocation("traveloptics", "gyro_slash_visual").toString()));
    public static final RegistryObject<EntityType<AshenBreathProjectile>> ASHEN_BREATH_PROJECTILE = ENTITIES.register("ashen_breath", () -> EntityType.Builder.build(AshenBreathProjectile::new, (MobCategory)MobCategory.MISC).build(1.0f, 1.0f).build(64).build(new ResourceLocation("traveloptics", "ashen_breath").toString()));
    public static final RegistryObject<EntityType<BubbleSprayProjectile>> BUBBLE_SPRAY_PROJECTILE = ENTITIES.register("bubble_spray", () -> EntityType.Builder.build(BubbleSprayProjectile::new, (MobCategory)MobCategory.MISC).build(1.0f, 1.0f).build(64).build(new ResourceLocation("traveloptics", "bubble_spray").toString()));
    public static final RegistryObject<EntityType<CursedVolleyEntity>> CURSED_VOLLEY_ENTITY = ENTITIES.register("cursed_volley", () -> EntityType.Builder.build(CursedVolleyEntity::new, (MobCategory)MobCategory.MISC).build(1.0f, 1.0f).build(64).build(new ResourceLocation("traveloptics", "cursed_volley").toString()));
    public static final RegistryObject<EntityType<ExtendedPhantomArrowEntity>> EXTENDED_PHANTOM_ARROW = ENTITIES.register("extended_phantom_arrow", () -> EntityType.Builder.build(ExtendedPhantomArrowEntity::new, (MobCategory)MobCategory.MISC).build(0.5f, 0.5f).setCustomClientFactory(ExtendedPhantomArrowEntity::new).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).build("traveloptics:extended_phantom_arrow"));
    public static final RegistryObject<EntityType<ExtendedSandstormEntity>> EXTENDED_SANDSTORM = ENTITIES.register("extended_sandstorm", () -> EntityType.Builder.build(ExtendedSandstormEntity::new, (MobCategory)MobCategory.MISC).build(2.5f, 4.5f).fireImmune().build(10).updateInterval(Integer.MAX_VALUE).build("traveloptics:extended_sandstorm"));
    public static final RegistryObject<EntityType<HydroshotProjectile>> HYDROSHOT_PROJECTILE = ENTITIES.register("hydroshot", () -> EntityType.Builder.build(HydroshotProjectile::new, (MobCategory)MobCategory.MISC).build(0.5f, 0.5f).build(64).build(new ResourceLocation("traveloptics", "hydroshot").toString()));
    public static final RegistryObject<EntityType<ExtendedDeathLaserBeamEntity>> EXTENDED_DEATH_LASER = ENTITIES.register("extended_death_laser", () -> EntityType.Builder.build(ExtendedDeathLaserBeamEntity::new, (MobCategory)MobCategory.MISC).build(0.1f, 0.1f).fireImmune().build("traveloptics:extended_death_laser"));
    public static final RegistryObject<EntityType<WaterFieldEntity>> WATER_FIELD = ENTITIES.register("water_field", () -> EntityType.Builder.build(WaterFieldEntity::new, (MobCategory)MobCategory.MISC).build(4.0f, 0.8f).build(64).build(new ResourceLocation("traveloptics", "water_field").toString()));
    public static final RegistryObject<EntityType<ExtendedWaterBoltEntity>> EXTENDED_WATER_BOLT = ENTITIES.register("extended_water_bolt", () -> EntityType.Builder.build(ExtendedWaterBoltEntity::new, (MobCategory)MobCategory.MISC).build(0.6f, 0.6f).setCustomClientFactory(ExtendedWaterBoltEntity::new).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).build("extended_water_bolt"));
    public static final RegistryObject<EntityType<ExtendedWitherHowitzerEntity>> EXTENDED_WITHER_HOWITZER = ENTITIES.register("extended_wither_howitzer", () -> EntityType.Builder.build(ExtendedWitherHowitzerEntity::new, (MobCategory)MobCategory.MISC).build(0.5f, 0.5f).fireImmune().setShouldReceiveVelocityUpdates(true).setUpdateInterval(20).build("traveloptics:extended_wither_howitzer"));
    public static final RegistryObject<EntityType<ExtendedAbyssBlastEntity>> EXTENDED_ABYSS_BLAST = ENTITIES.register("extended_abyss_blast", () -> EntityType.Builder.build(ExtendedAbyssBlastEntity::new, (MobCategory)MobCategory.MISC).build(0.1f, 0.1f).fireImmune().build("traveloptics:extended_abyss_blast"));
    public static final RegistryObject<EntityType<ExtendedIgnisFireballEntity>> EXTENDED_IGNIS_FIREBALL = ENTITIES.register("extended_ignis_fireball", () -> EntityType.Builder.build(ExtendedIgnisFireballEntity::new, (MobCategory)MobCategory.MISC).build(0.6f, 0.6f).setUpdateInterval(1).setTrackingRange(20).setShouldReceiveVelocityUpdates(true).build("traveloptics:extended_ignis_fireball"));
    public static final RegistryObject<EntityType<ExtendedFlareBombEntity>> EXTENDED_FLARE_BOMB = ENTITIES.register("extended_flare_bomb", () -> EntityType.Builder.build(ExtendedFlareBombEntity::new, (MobCategory)MobCategory.MISC).build(0.5f, 0.5f).fireImmune().setShouldReceiveVelocityUpdates(true).setUpdateInterval(20).build("traveloptics:extended_flare_bomb"));
    public static final RegistryObject<EntityType<ExtendedWaterSpearEntity>> EXTENDED_WATER_SPEAR = ENTITIES.register("extended_water_spear", () -> EntityType.Builder.build(ExtendedWaterSpearEntity::new, (MobCategory)MobCategory.MISC).build(0.5f, 0.5f).build(4).build(new ResourceLocation("traveloptics", "extended_water_spear").toString()));
    public static final RegistryObject<EntityType<ExtendedStormSerpentEntity>> EXTENDED_STORM_SERPENT = ENTITIES.register("extended_storm_serpent", () -> EntityType.Builder.build(ExtendedStormSerpentEntity::new, (MobCategory)MobCategory.MISC).build(2.0f, 9.0f).build(6).updateInterval(2).fireImmune().build(new ResourceLocation("traveloptics", "extended_storm_serpent").toString()));
    public static final RegistryObject<EntityType<ExtendedFlameStrikeEntity>> EXTENDED_FLAME_STRIKE = ENTITIES.register("extended_flame_strike", () -> EntityType.Builder.build(ExtendedFlameStrikeEntity::new, (MobCategory)MobCategory.MISC).build(6.0f, 0.5f).fireImmune().build(10).updateInterval(Integer.MAX_VALUE).build(new ResourceLocation("traveloptics", "extended_flame_strike").toString()));
    public static final RegistryObject<EntityType<GyroSlashProjectile>> GYRO_SLASH_PROJECTILE = ENTITIES.register("gyro_slash_projectile", () -> EntityType.Builder.build(GyroSlashProjectile::new, (MobCategory)MobCategory.MISC).build(2.0f, 0.5f).build(64).build(new ResourceLocation("traveloptics", "gyro_slash_projectile").toString()));
    public static final RegistryObject<EntityType<VoidSlashProjectile>> VOID_SLASH_PROJECTILE = ENTITIES.register("void_slash_projectile", () -> EntityType.Builder.build(VoidSlashProjectile::new, (MobCategory)MobCategory.MISC).build(2.0f, 0.5f).build(64).build(new ResourceLocation("traveloptics", "void_slash_projectile").toString()));
    public static final RegistryObject<EntityType<FloodSlashProjectile>> FLOOD_SLASH_PROJECTILE = ENTITIES.register("flood_slash_projectile", () -> EntityType.Builder.build(FloodSlashProjectile::new, (MobCategory)MobCategory.MISC).build(2.0f, 0.5f).build(64).build(new ResourceLocation("traveloptics", "flood_slash_projectile").toString()));
    public static final RegistryObject<EntityType<ExtendedWaveEntity>> EXTENDED_WAVE = ENTITIES.register("extended_wave", () -> EntityType.Builder.build(ExtendedWaveEntity::new, (MobCategory)MobCategory.MISC).build(0.9f, 0.9f).setCustomClientFactory(ExtendedWaveEntity::new).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).build("extended_wave"));
    public static final RegistryObject<EntityType<ReturningWaveEntity>> RETURNING_WAVE = ENTITIES.register("returning_wave", () -> EntityType.Builder.build(ReturningWaveEntity::new, (MobCategory)MobCategory.MISC).build(0.9f, 0.9f).setCustomClientFactory(ReturningWaveEntity::new).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).build("returning_wave"));
    public static final RegistryObject<EntityType<ExtendedWitherHomingMissileEntity>> EXTENDED_WITHER_HOMING_MISSILE = ENTITIES.register("extended_wither_homing_missile", () -> EntityType.Builder.build(ExtendedWitherHomingMissileEntity::new, (MobCategory)MobCategory.MISC).build(0.25f, 0.25f).setUpdateInterval(1).setTrackingRange(20).setShouldReceiveVelocityUpdates(true).build("traveloptics:extended_wither_homing_missile"));
    public static final RegistryObject<EntityType<BlueCoralBoltProjectile>> BLUE_CORAL_BOLT_PROJECTILE = ENTITIES.register("blue_coral_bolt_projectile", () -> EntityType.Builder.build(BlueCoralBoltProjectile::new, (MobCategory)MobCategory.MISC).build(0.5f, 0.5f).build(64).build(new ResourceLocation("traveloptics", "blue_coral_bolt_projectile").toString()));
    public static final RegistryObject<EntityType<RedCoralBoltProjectile>> RED_CORAL_BOLT_PROJECTILE = ENTITIES.register("red_coral_bolt_projectile", () -> EntityType.Builder.build(RedCoralBoltProjectile::new, (MobCategory)MobCategory.MISC).build(0.5f, 0.5f).build(64).build(new ResourceLocation("traveloptics", "red_coral_bolt_projectile").toString()));
    public static final RegistryObject<EntityType<PinkCoralBoltProjectile>> PINK_CORAL_BOLT_PROJECTILE = ENTITIES.register("pink_coral_bolt_projectile", () -> EntityType.Builder.build(PinkCoralBoltProjectile::new, (MobCategory)MobCategory.MISC).build(0.5f, 0.5f).build(64).build(new ResourceLocation("traveloptics", "pink_coral_bolt_projectile").toString()));
    public static final RegistryObject<EntityType<YellowCoralBoltProjectile>> YELLOW_CORAL_BOLT_PROJECTILE = ENTITIES.register("yellow_coral_bolt_projectile", () -> EntityType.Builder.build(YellowCoralBoltProjectile::new, (MobCategory)MobCategory.MISC).build(0.5f, 0.5f).build(64).build(new ResourceLocation("traveloptics", "yellow_coral_bolt_projectile").toString()));
    public static final RegistryObject<EntityType<PsychicBoltProjectile>> PSYCHIC_BOLT_PROJECTILE = ENTITIES.register("psychic_bolt_projectile", () -> EntityType.Builder.build(PsychicBoltProjectile::new, (MobCategory)MobCategory.MISC).build(0.5f, 0.5f).build(64).build(new ResourceLocation("traveloptics", "psychic_bolt_projectile").toString()));
    public static final RegistryObject<EntityType<EnsnareEntity>> ENSNARE = ENTITIES.register("ensnare", () -> EntityType.Builder.build(EnsnareEntity::new, (MobCategory)MobCategory.MISC).build(4.0f, 0.8f).build(64).build(new ResourceLocation("traveloptics", "ensnare").toString()));
    public static final RegistryObject<EntityType<RainfallAoe>> RAINFALL_ENTITY = ENTITIES.register("rainfall_aoe", () -> EntityType.Builder.build(RainfallAoe::new, (MobCategory)MobCategory.MISC).build(4.0f, 0.8f).build(64).build(new ResourceLocation("traveloptics", "rainfall_aoe").toString()));
    public static final RegistryObject<EntityType<BlizzardAoe>> BLIZZARD_ENTITY = ENTITIES.register("blizzard_aoe", () -> EntityType.Builder.build(BlizzardAoe::new, (MobCategory)MobCategory.MISC).build(4.0f, 0.8f).build(64).build(new ResourceLocation("traveloptics", "blizzard_aoe").toString()));
    public static final RegistryObject<EntityType<AcidRainAoe>> ACID_RAIN_ENTITY = ENTITIES.register("acid_rain_aoe", () -> EntityType.Builder.build(AcidRainAoe::new, (MobCategory)MobCategory.MISC).build(4.0f, 0.8f).build(64).build(new ResourceLocation("traveloptics", "acid_rain_aoe").toString()));
    public static final RegistryObject<EntityType<AerialCollapseVisualEntity>> AERIAL_COLLAPSE_VISUAL_ENTITY = ENTITIES.register("aerial_collapse_visual_entity", () -> EntityType.Builder.build(AerialCollapseVisualEntity::new, (MobCategory)MobCategory.MISC).build(4.0f, 0.8f).build(64).build(new ResourceLocation("traveloptics", "aerial_collapse_visual_entity").toString()));
    public static final RegistryObject<EntityType<AquaVortexEntity>> AQUA_VORTEX_ENTITY = ENTITIES.register("aqua_vortex_entity", () -> EntityType.Builder.build(AquaVortexEntity::new, (MobCategory)MobCategory.MISC).build(4.0f, 0.8f).build(64).build(new ResourceLocation("traveloptics", "aqua_vortex_entity").toString()));
    public static final RegistryObject<EntityType<BlackoutAntiMagicField>> BLACKOUT_ANTI_MAGIC_FIELD = ENTITIES.register("blackout_anti_magic_field", () -> EntityType.Builder.build(BlackoutAntiMagicField::new, (MobCategory)MobCategory.MISC).build(4.0f, 0.8f).build(64).build(new ResourceLocation("traveloptics", "blackout_anti_magic_field").toString()));
    public static final RegistryObject<EntityType<StellarTrailAoeEntity>> STELLAR_TRAIL_AOE = ENTITIES.register("stellar_trail_aoe", () -> EntityType.Builder.build(StellarTrailAoeEntity::new, (MobCategory)MobCategory.MISC).build(4.0f, 4.0f).build(64).build(new ResourceLocation("traveloptics", "stellar_trail_aoe").toString()));
    public static final RegistryObject<EntityType<TectonicRiftEntity>> TECTONIC_RIFT_FIELD = ENTITIES.register("tectonic_rift_field", () -> EntityType.Builder.build(TectonicRiftEntity::new, (MobCategory)MobCategory.MISC).build(4.0f, 0.8f).build(64).build(new ResourceLocation("traveloptics", "tectonic_rift_field").toString()));
    public static final RegistryObject<EntityType<EndEruptionEntity>> END_ERUPTION = ENTITIES.register("end_eruption", () -> EntityType.Builder.build(EndEruptionEntity::new, (MobCategory)MobCategory.MISC).build(4.0f, 0.8f).build(64).build(new ResourceLocation("traveloptics", "end_eruption").toString()));
    public static final RegistryObject<EntityType<AsteroidImpactCraterEntity>> ASTEROID_IMPACT_CRATER = ENTITIES.register("asteroid_impact_crater", () -> EntityType.Builder.build(AsteroidImpactCraterEntity::new, (MobCategory)MobCategory.MISC).build(4.0f, 0.8f).build(64).build(new ResourceLocation("traveloptics", "asteroid_impact_crater").toString()));
    public static final RegistryObject<EntityType<AsteroidEntity>> ASTEROID = ENTITIES.register("asteroid", () -> EntityType.Builder.build(AsteroidEntity::new, (MobCategory)MobCategory.MISC).build(12.0f, 12.0f).build(4).build(new ResourceLocation("traveloptics", "asteroid").toString()));
    public static final RegistryObject<EntityType<DragonSpiritEntity>> DRAGON_SPIRIT_ENTITY = ENTITIES.register("dragon_spirit", () -> EntityType.Builder.build(DragonSpiritEntity::new, (MobCategory)MobCategory.MISC).build(20.0f, 20.0f).build(4).build(new ResourceLocation("traveloptics", "dragon_spirit").toString()));
    public static final RegistryObject<EntityType<DragonSpiritSpellEntity>> DRAGON_SPIRIT_SPELL_ENTITY = ENTITIES.register("dragon_spirit_spell", () -> EntityType.Builder.build(DragonSpiritSpellEntity::new, (MobCategory)MobCategory.MISC).build(12.0f, 12.0f).build(4).build(new ResourceLocation("traveloptics", "dragon_spirit_spell").toString()));
    public static final RegistryObject<EntityType<DimensionalSpikeEntity>> DIMENSIONAL_SPIKE = ENTITIES.register("dimensional_spike", () -> EntityType.Builder.build(DimensionalSpikeEntity::new, (MobCategory)MobCategory.MISC).build(0.6f, 1.9f).fireImmune().build("traveloptics:dimensional_spike"));
    public static final RegistryObject<EntityType<ExtendedPhantomHalberdEntity>> EXTENDED_PHANTOM_HALBERD = ENTITIES.register("extended_phantom_halberd", () -> EntityType.Builder.build(ExtendedPhantomHalberdEntity::new, (MobCategory)MobCategory.MISC).build(0.6f, 1.95f).build(6).updateInterval(2).fireImmune().build(new ResourceLocation("traveloptics", "extended_phantom_halberd").toString()));
    public static final RegistryObject<EntityType<MaelstromTridentPhantomEntity>> MAELSTROM_TRIDENT_PHANTOM = ENTITIES.register("maelstrom_trident_phantom", () -> EntityType.Builder.build(MaelstromTridentPhantomEntity::new, (MobCategory)MobCategory.MISC).build(1.5f, 5.0f).build(64).build(new ResourceLocation("traveloptics", "maelstrom_trident_phantom").toString()));
    // REMOVED (AC dependency):     public static final RegistryObject<EntityType<SummonedHullbreaker>> SUMMONED_HULLBREAKER = ENTITIES.register("summoned_hullbreaker", () -> EntityType.Builder.build(SummonedHullbreaker::new, (MobCategory)MobCategory.UNDERGROUND_WATER_CREATURE).build(4.65f, 4.5f).setShouldReceiveVelocityUpdates(true).build(20).build(new ResourceLocation("traveloptics", "summoned_hullbreaker").toString()));
    public static final RegistryObject<EntityType<SummonedKoboleton>> SUMMONED_KOBOLETON = ENTITIES.register("summoned_koboleton", () -> EntityType.Builder.build(SummonedKoboleton::new, (MobCategory)MobCategory.MONSTER).build(0.85f, 1.6f).build(64).build(new ResourceLocation("traveloptics", "summoned_koboleton").toString()));
    public static final RegistryObject<EntityType<SummonedDeepling>> SUMMONED_DEEPLING = ENTITIES.register("summoned_deepling", () -> EntityType.Builder.build(SummonedDeepling::new, (MobCategory)MobCategory.MONSTER).build(0.6f, 2.3f).build(8).build("traveloptics:summoned_deepling"));
    public static final RegistryObject<EntityType<SummonedKobolediator>> SUMMONED_KOBOLEDIATOR = ENTITIES.register("summoned_kobolediator", () -> EntityType.Builder.build(SummonedKobolediator::new, (MobCategory)MobCategory.MONSTER).build(2.4f, 4.4f).build(8).build("traveloptics:summoned_kobolediator"));
    public static final RegistryObject<EntityType<SummonedWadjet>> SUMMONED_WADJET = ENTITIES.register("summoned_wadjet", () -> EntityType.Builder.build(SummonedWadjet::new, (MobCategory)MobCategory.MONSTER).build(0.85f, 3.4f).build(8).build("traveloptics:summoned_wadjet"));
    // REMOVED (AC dependency):     public static final RegistryObject<EntityType<SummonedMagnetron>> SUMMONED_MAGNETRON = ENTITIES.register("summoned_magnetron", () -> EntityType.Builder.build(SummonedMagnetron::new, (MobCategory)MobCategory.MONSTER).build(0.8f, 2.0f).build("traveloptics:summoned_magnetron"));
    // REMOVED (AC dependency):     public static final RegistryObject<EntityType<SummonedTheProwler>> SUMMONED_THE_PROWLER = ENTITIES.register("summoned_the_prowler", () -> EntityType.Builder.build(SummonedTheProwler::new, (MobCategory)MobCategory.MONSTER).build(2.5f, 2.75f).fireImmune().build(8).build("traveloptics:summoned_the_prowler"));
    public static final RegistryObject<EntityType<SummonedTheWatcher>> SUMMONED_THE_WATCHER = ENTITIES.register("summoned_the_watcher", () -> EntityType.Builder.build(SummonedTheWatcher::new, (MobCategory)MobCategory.MONSTER).build(0.85f, 0.85f).fireImmune().build("traveloptics:summoned_the_watcher"));
    // REMOVED (AC dependency):     public static final RegistryObject<EntityType<SummonedGumWorm>> SUMMONED_GUM_WORM = ENTITIES.register("summoned_gum_worm", () -> EntityType.Builder.build(SummonedGumWorm::new, (MobCategory)MobCategory.CREATURE).build(3.5f, 2.7f).setTrackingRange(14).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).fireImmune().build(new ResourceLocation("traveloptics", "summoned_gum_worm").toString()));
    // REMOVED (AC dependency):     public static final RegistryObject<EntityType<SummonedForsaken>> SUMMONED_FORSAKEN = ENTITIES.register("summoned_forsaken", () -> EntityType.Builder.build(SummonedForsaken::new, (MobCategory)MobCategory.CREATURE).build(3.0f, 4.0f).setTrackingRange(12).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).build("traveloptics:summoned_forsaken"));
    // REMOVED (AC dependency):     public static final RegistryObject<EntityType<SummonedVallumraptor>> SUMMONED_VALLUMRAPTOR = ENTITIES.register("summoned_vallumraptor", () -> EntityType.Builder.build(SummonedVallumraptor::new, (MobCategory)MobCategory.CREATURE).build(0.8f, 1.5f).setTrackingRange(8).build("traveloptics:summoned_vallumraptor"));
    // REMOVED (AC dependency):     public static final RegistryObject<EntityType<SummonedTremorsaurus>> SUMMONED_TREMORSAURUS = ENTITIES.register("summoned_tremorsaurus", () -> EntityType.Builder.build(SummonedTremorsaurus::new, (MobCategory)MobCategory.CREATURE).build(2.5f, 3.85f).setTrackingRange(8).build("traveloptics:summoned_tremorsaurus"));
    // REMOVED (AC dependency):     public static final RegistryObject<EntityType<SummonedAtlatitan>> SUMMONED_ATLATITAN = ENTITIES.register("summoned_atlatitan", () -> EntityType.Builder.build(SummonedAtlatitan::new, (MobCategory)MobCategory.CREATURE).build(5.0f, 8.0f).setTrackingRange(11).build("traveloptics:summoned_atlatitan"));
    // REMOVED (AC dependency):     public static final RegistryObject<EntityType<SummonedVesper>> SUMMONED_VESPER = ENTITIES.register("summoned_vesper", () -> EntityType.Builder.build(SummonedVesper::new, (MobCategory)MobCategory.CREATURE).build(1.2f, 1.65f).setTrackingRange(12).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).build("traveloptics:summoned_vesper"));
    public static final RegistryObject<EntityType<SummonedIgnitedRevenant>> SUMMONED_IGNITED_REVENANT = ENTITIES.register("summoned_ignited_revenant", () -> EntityType.Builder.build(SummonedIgnitedRevenant::new, (MobCategory)MobCategory.MONSTER).build(1.6f, 2.8f).fireImmune().build("traveloptics:summoned_ignited_revenant"));
    public static final RegistryObject<EntityType<SummonedIgnitedBerserker>> SUMMONED_IGNITED_BERSERKER = ENTITIES.register("summoned_ignited_berserker", () -> EntityType.Builder.build(SummonedIgnitedBerserker::new, (MobCategory)MobCategory.MONSTER).build(1.0f, 2.4f).fireImmune().build("traveloptics:summoned_ignited_berserker"));
    public static final RegistryObject<EntityType<SummonedEnderGolem>> SUMMONED_ENDER_GOLEM = ENTITIES.register("summoned_ender_golem", () -> EntityType.Builder.build(SummonedEnderGolem::new, (MobCategory)MobCategory.MONSTER).build(2.5f, 3.5f).fireImmune().build("traveloptics:summoned_ender_golem"));
    public static final RegistryObject<EntityType<SummonedDraugr>> SUMMONED_DRAUGR = ENTITIES.register("summoned_draugr", () -> EntityType.Builder.build(SummonedDraugr::new, (MobCategory)MobCategory.MONSTER).build(0.6f, 1.95f).build(10).build("traveloptics:summoned_draugr"));
    public static final RegistryObject<EntityType<SummonedRoyalDraugr>> SUMMONED_ROYAL_DRAUGR = ENTITIES.register("summoned_royal_draugr", () -> EntityType.Builder.build(SummonedRoyalDraugr::new, (MobCategory)MobCategory.MONSTER).build(0.6f, 1.95f).build(10).build("traveloptics:summoned_royal_draugr"));
    public static final RegistryObject<EntityType<SummonedEliteDraugr>> SUMMONED_ELITE_DRAUGR = ENTITIES.register("summoned_elite_draugr", () -> EntityType.Builder.build(SummonedEliteDraugr::new, (MobCategory)MobCategory.MONSTER).build(0.8f, 2.6f).build(10).build("traveloptics:summoned_elite_draugr"));
    public static final RegistryObject<EntityType<SummonedAptrgangr>> SUMMONED_APTRGANGR = ENTITIES.register("summoned_aptrgangr", () -> EntityType.Builder.build(SummonedAptrgangr::new, (MobCategory)MobCategory.MONSTER).build(2.4f, 4.0f).build(8).build("traveloptics:summoned_aptrgangr"));
    public static final RegistryObject<EntityType<EnragedDeadKingBoss>> ENRAGED_DEAD_KING = ENTITIES.register("enraged_dead_king", () -> EntityType.Builder.build(EnragedDeadKingBoss::new, (MobCategory)MobCategory.MONSTER).build(0.9f, 3.5f).build(64).build(new ResourceLocation("traveloptics", "enraged_dead_king").toString()));
    public static final RegistryObject<EntityType<SummonedVoidTome>> SUMMONED_VOID_TOME = ENTITIES.register("summoned_void_tome", () -> EntityType.Builder.build(SummonedVoidTome::new, (MobCategory)MobCategory.CREATURE).build(0.8f, 0.8f).build(64).build(new ResourceLocation("traveloptics", "summoned_void_tome").toString()));
    public static final RegistryObject<EntityType<TOScreenShakeEntity>> SCREEN_SHAKE = ENTITIES.register("screen_shake", () -> EntityType.Builder.build(TOScreenShakeEntity::new, (MobCategory)MobCategory.MISC).build().build(0.0f, 0.0f).setUpdateInterval(Integer.MAX_VALUE).build(new ResourceLocation("traveloptics", "screen_shake").toString()));
    public static final RegistryObject<EntityType<TOFollowingScreenShakeEntity>> FOLLOWING_SCREEN_SHAKE = ENTITIES.register("following_screen_shake", () -> EntityType.Builder.build(TOFollowingScreenShakeEntity::new, (MobCategory)MobCategory.MISC).build().build(0.0f, 0.0f).setUpdateInterval(Integer.MAX_VALUE).build(new ResourceLocation("traveloptics", "following_screen_shake").toString()));
    public static final RegistryObject<EntityType<TOScreenFlashEntity>> SCREEN_FLASH = ENTITIES.register("screen_flash", () -> EntityType.Builder.build(TOScreenFlashEntity::new, (MobCategory)MobCategory.MISC).build().build(0.0f, 0.0f).setUpdateInterval(Integer.MAX_VALUE).build(new ResourceLocation("traveloptics", "screen_flash").toString()));
    public static final RegistryObject<EntityType<TOPowerInversionEntity>> SCREEN_POWER_INVERSION = ENTITIES.register("screen_power_inversion", () -> EntityType.Builder.build(TOPowerInversionEntity::new, (MobCategory)MobCategory.MISC).build().build(0.0f, 0.0f).setUpdateInterval(Integer.MAX_VALUE).build(new ResourceLocation("traveloptics", "screen_power_inversion").toString()));
    public static final RegistryObject<EntityType<StackEntity>> STACK = ENTITIES.register("stack_entity", () -> EntityType.Builder.build(StackEntity::new, (MobCategory)MobCategory.MISC).build().build(1.0f, 1.0f).build(64).setUpdateInterval(2).build(new ResourceLocation("traveloptics", "stack_entity").toString()));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
        return ENTITIES.register(registryname, () -> entityTypeBuilder.build(registryname));
    }
}

