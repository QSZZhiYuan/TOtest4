/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package com.gametechbc.traveloptics.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class TravelopticsSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create((IForgeRegistry)ForgeRegistries.SOUND_EVENTS, (String)"traveloptics");
    public static final RegistryObject<SoundEvent> ELDRITCH_ABYSSAMORPH = REGISTRY.register("eldritch_abyssamorph", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "eldritch_abyssamorph")));
    public static final RegistryObject<SoundEvent> ANNIHILATION_CHARGE = REGISTRY.register("annihilation_charge", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "annihilation_charge")));
    public static final RegistryObject<SoundEvent> ORBITAL_VOID_PULSE = REGISTRY.register("orbital_void_pulse", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "orbital_void_pulse")));
    public static final RegistryObject<SoundEvent> ABYSSAL_STRIKE_TRIGGER = REGISTRY.register("abyssal_strike_trigger", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "abyssal_strike_trigger")));
    public static final RegistryObject<SoundEvent> AERIAL_COLLAPSE = REGISTRY.register("aerial_collapse", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "aerial_collapse")));
    public static final RegistryObject<SoundEvent> SPECTRAL_BLINK_FAILED = REGISTRY.register("spectral_blink", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "spectral_blink")));
    public static final RegistryObject<SoundEvent> SPECTRAL_BLINK_CHARGE = REGISTRY.register("spectral_blink_charge", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "spectral_blink_charge")));
    public static final RegistryObject<SoundEvent> SPECTRAL_BLINK_SUCCESS = REGISTRY.register("spectral_blink_success", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "spectral_blink_success")));
    public static final RegistryObject<SoundEvent> REFINED_ABYSSAL_BLAST_CHARGE = REGISTRY.register("refined_abyss_blast_charge", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "refined_abyss_blast_charge")));
    public static final RegistryObject<SoundEvent> REFINED_ABYSSAL_BLAST_SHOOT = REGISTRY.register("refined_abyssal_blast_shoot", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "refined_abyssal_blast_shoot")));
    public static final RegistryObject<SoundEvent> REFINED_DEATH_LASER_SHOOT = REGISTRY.register("refined_death_laser_shoot", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "refined_death_laser_shoot")));
    public static final RegistryObject<SoundEvent> MANIFESTATION_CHANGE = REGISTRY.register("manifestation", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "manifestation")));
    public static final RegistryObject<SoundEvent> NULLFLARE = REGISTRY.register("nullflare", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "nullflare")));
    public static final RegistryObject<SoundEvent> NULLFLARE_TWO = REGISTRY.register("nullflare_two", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "nullflare_two")));
    public static final RegistryObject<SoundEvent> SPIRIT_POINTS_MAX = REGISTRY.register("spirit_points_max", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "spirit_points_max")));
    public static final RegistryObject<SoundEvent> REVERSAL_TRIGGER = REGISTRY.register("reversal_trigger", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "reversal_trigger")));
    public static final RegistryObject<SoundEvent> REVERSAL = REGISTRY.register("reversal", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "reversal")));
    public static final RegistryObject<SoundEvent> REFINED_REVENANT_BREATH = REGISTRY.register("refined_revenant_breath", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "refined_revenant_breath")));
    public static final RegistryObject<SoundEvent> BLAST_STAGE_ONE = REGISTRY.register("blast_stage_one", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "blast_stage_one")));
    public static final RegistryObject<SoundEvent> BLAST_STAGE_TWO = REGISTRY.register("blast_stage_two", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "blast_stage_two")));
    public static final RegistryObject<SoundEvent> BLAST_STAGE_THREE = REGISTRY.register("blast_stage_three", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "blast_stage_three")));
    public static final RegistryObject<SoundEvent> SPIRIT_POINTS_THRESHOLD_ONE = REGISTRY.register("spirit_threshold_one", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "spirit_threshold_one")));
    public static final RegistryObject<SoundEvent> SPIRIT_POINTS_THRESHOLD_TWO = REGISTRY.register("spirit_threshold_two", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "spirit_threshold_two")));
    public static final RegistryObject<SoundEvent> CURSED_BLAST_CHARGE = REGISTRY.register("cursed_blast_charge", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "cursed_blast_charge")));
    public static final RegistryObject<SoundEvent> EEK = REGISTRY.register("eek", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "eek")));
    public static final RegistryObject<SoundEvent> ENSNARE_PREPARE = REGISTRY.register("ensnare_prepare", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "ensnare_prepare")));
    public static final RegistryObject<SoundEvent> ENSNARE_TRIGGER = REGISTRY.register("ensnare_trigger", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "ensnare_trigger")));
    public static final RegistryObject<SoundEvent> BANISH_CHARGE = REGISTRY.register("banish_charge", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "banish_charge")));
    public static final RegistryObject<SoundEvent> BANISH_CAST = REGISTRY.register("banish_cast", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "banish_cast")));
    public static final RegistryObject<SoundEvent> LASER_SHOOT = REGISTRY.register("laser_shoot", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "laser_shoot")));
    public static final RegistryObject<SoundEvent> LASER_CHARGE = REGISTRY.register("laser_charge", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "laser_charge")));
    public static final RegistryObject<SoundEvent> TECTONIC_RIFT_ERUPTION = REGISTRY.register("tectonic_rift_eruption", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "tectonic_rift_eruption")));
    public static final RegistryObject<SoundEvent> EXTINCTION_CAST = REGISTRY.register("extinction_cast", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "extinction_cast")));
    public static final RegistryObject<SoundEvent> EXTINCTION_FINISH = REGISTRY.register("extinction_finish", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "extinction_finish")));
    public static final RegistryObject<SoundEvent> EXTINCTION_START = REGISTRY.register("extinction_start", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "extinction_start")));
    public static final RegistryObject<SoundEvent> REFINED_ABYSS_BLAST_PORTAL = REGISTRY.register("refined_abyss_blast_portal", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "refined_abyss_blast_portal")));
    public static final RegistryObject<SoundEvent> ASTEROID_LOOP = REGISTRY.register("asteroid_loop", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "asteroid_loop")));
    public static final RegistryObject<SoundEvent> DEVASTATOR_CANON_TRANSFORM = REGISTRY.register("devastator_canon_transform", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "devastator_canon_transform")));
    public static final RegistryObject<SoundEvent> DEVASTATOR_CANON_TRANSFORM_OVER = REGISTRY.register("devastator_canon_transformation_over", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "devastator_canon_transformation_over")));
    public static final RegistryObject<SoundEvent> DEVASTATOR_BLADE_TRANSFORM = REGISTRY.register("devastator_blade_transformation", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "devastator_blade_transformation")));
    public static final RegistryObject<SoundEvent> DEVASTATOR_BLADE_TRANSFORM_OVER = REGISTRY.register("devastator_blade_transformation_over", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "devastator_blade_transformation_over")));
    public static final RegistryObject<SoundEvent> AQUA_VORTEX_CAST = REGISTRY.register("aqua_vortex_cast", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "aqua_vortex_cast")));
    public static final RegistryObject<SoundEvent> AQUA_VORTEX_ACTIVE = REGISTRY.register("aqua_vortex_active", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "aqua_vortex_active")));
    public static final RegistryObject<SoundEvent> AQUA_VORTEX_END = REGISTRY.register("aqua_vortex_end", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "aqua_vortex_end")));
    public static final RegistryObject<SoundEvent> HYDROSHOT_IMPACT = REGISTRY.register("hydroshot_impact", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "hydroshot_impact")));
    public static final RegistryObject<SoundEvent> AQUA_CAST_2 = REGISTRY.register("aqua_cast_2", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "aqua_cast_2")));
    public static final RegistryObject<SoundEvent> AQUA_MISSILES_CAST = REGISTRY.register("aqua_missiles_cast", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "aqua_missiles_cast")));
    public static final RegistryObject<SoundEvent> RAINFALL_ACTIVE = REGISTRY.register("rainfall_active", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "rainfall_active")));
    public static final RegistryObject<SoundEvent> ACID_RAIN_ACTIVE = REGISTRY.register("acid_rain_active", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "acid_rain_active")));
    public static final RegistryObject<SoundEvent> TIDAL_GRASP_SMACK = REGISTRY.register("tidal_grasp_smack", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "tidal_grasp_smack")));
    public static final RegistryObject<SoundEvent> TIDAL_GRASP_PULL = REGISTRY.register("tidal_grasp_pull", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "tidal_grasp_pull")));
    public static final RegistryObject<SoundEvent> TSUNAMI_CAST = REGISTRY.register("tsunami_cast", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "tsunami_cast")));
    public static final RegistryObject<SoundEvent> JETPACK_THRUST = REGISTRY.register("jetpack_thrust", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "jetpack_thrust")));
    public static final RegistryObject<SoundEvent> JETPACK_WARNING = REGISTRY.register("jetpack_warning", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "jetpack_warning")));
    public static final RegistryObject<SoundEvent> JETPACK_FUEL_FULL = REGISTRY.register("jetpack_fuel_full", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "jetpack_fuel_full")));
    public static final RegistryObject<SoundEvent> JETPACK_THRUSTER_LOOP = REGISTRY.register("jetpack_thruster_loop", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "jetpack_thruster_loop")));
    public static final RegistryObject<SoundEvent> FORLORN_FLAP = REGISTRY.register("forlorn_flap", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "forlorn_flap")));
    public static final RegistryObject<SoundEvent> NOCTURNAL_UPLIFT = REGISTRY.register("nocturnal_uplift", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "nocturnal_uplift")));
    public static final RegistryObject<SoundEvent> CRIMSON_DESCEND = REGISTRY.register("crimson_descend", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "crimson_descend")));
    public static final RegistryObject<SoundEvent> CRIMSON_DESCEND_IMPACT = REGISTRY.register("crimson_dive_impact", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "crimson_dive_impact")));
    public static final RegistryObject<SoundEvent> MAELSTROM_TRIDENT_THROW = REGISTRY.register("maelstrom_trident_throw", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "maelstrom_trident_throw")));
    public static final RegistryObject<SoundEvent> MAELSTROM_TRIDENT_IMPACT = REGISTRY.register("maelstrom_trident_impact", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "maelstrom_trident_impact")));
    public static final RegistryObject<SoundEvent> MAELSTROM_TRIDENT_RETURN = REGISTRY.register("maelstrom_trident_return", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "maelstrom_trident_return")));
    public static final RegistryObject<SoundEvent> NIGHTWARDEN_MUSIC_START = REGISTRY.register("nightwarden_main_start", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "nightwarden_main_start")));
    public static final RegistryObject<SoundEvent> NIGHTWARDEN_MUSIC_LOOP = REGISTRY.register("nightwarden_main_loop", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "nightwarden_main_loop")));
    public static final RegistryObject<SoundEvent> NIGHTWARDEN_MUSIC_FULL = REGISTRY.register("nightwarden_full", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "nightwarden_full")));
    public static final RegistryObject<SoundEvent> NIGHTWARDEN_SCYTHE_SPIN = REGISTRY.register("nightwarden_scythespin", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "nightwarden_scythespin")));
    public static final RegistryObject<SoundEvent> FLYING_BOOK_PAGE_TURN = REGISTRY.register("flying_book_page_turn", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "flying_book_page_turn")));
    public static final RegistryObject<SoundEvent> FLYING_BOOK_BOOK_CLOSE = REGISTRY.register("flying_book_close", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "flying_book_close")));
    public static final RegistryObject<SoundEvent> FLYING_BOOK_DEATH_FALL = REGISTRY.register("flying_book_death_fall", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "flying_book_death_fall")));
    public static final RegistryObject<SoundEvent> FLYING_BOOK_HURT = REGISTRY.register("flying_book_hurt", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "flying_book_hurt")));
    public static final RegistryObject<SoundEvent> NIGHTWARDEN_WALK = REGISTRY.register("nightwarden_walk", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "nightwarden_walk")));
    public static final RegistryObject<SoundEvent> NIGHTWARDEN_SWING = REGISTRY.register("nightwarden_swing", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "nightwarden_swing")));
    public static final RegistryObject<SoundEvent> NIGHTWARDEN_SWING_1 = REGISTRY.register("nightwarden_swing_1", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "nightwarden_swing_1")));
    public static final RegistryObject<SoundEvent> NIGHTWARDEN_SWING_HEAVY = REGISTRY.register("nightwarden_swing_heavy", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "nightwarden_swing_heavy")));
    public static final RegistryObject<SoundEvent> NIGHTWARDEN_FINGER_SNAP = REGISTRY.register("nightwarden_finger_snap", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "nightwarden_finger_snap")));
    public static final RegistryObject<SoundEvent> DRAGON_SPIRIT_SPAWN = REGISTRY.register("dragon_spirit_spawn", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "dragon_spirit_spawn")));
    public static final RegistryObject<SoundEvent> DRAGON_SPIRIT_AMBIENT = REGISTRY.register("dragon_spirit_ambient", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "dragon_spirit_ambient")));
    public static final RegistryObject<SoundEvent> NIGHTWARDEN_BIG_SLAM = REGISTRY.register("nightwarden_big_slam", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "nightwarden_big_slam")));
    public static final RegistryObject<SoundEvent> NIGHTWARDEN_BIG_SLAM_CLONES = REGISTRY.register("nightwarden_big_slam_clones", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "nightwarden_big_slam_clones")));
    public static final RegistryObject<SoundEvent> NIGHTWARDEN_DEFEATED_DEATH_STARE = REGISTRY.register("nightwarden_defeated_death_stare", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "nightwarden_defeated_death_stare")));
    public static final RegistryObject<SoundEvent> END_ERUPTION_AMBIENT = REGISTRY.register("end_eruption_ambient", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "end_eruption_ambient")));
    public static final RegistryObject<SoundEvent> END_ERUPTION_BLAST = REGISTRY.register("end_eruption_blast", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "end_eruption_blast")));
    public static final RegistryObject<SoundEvent> BOSS_BREAK = REGISTRY.register("boss_break", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "boss_break")));
    public static final RegistryObject<SoundEvent> BOSS_ATTACK_WARNING = REGISTRY.register("boss_attack_warning", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "boss_attack_warning")));
    public static final RegistryObject<SoundEvent> DYING_STAR_AMBIENT = REGISTRY.register("dying_star_ambient", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "dying_star_ambient")));
    public static final RegistryObject<SoundEvent> SUPERNOVA_EXPLODE = REGISTRY.register("supernova_explode", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "supernova_explode")));
    public static final RegistryObject<SoundEvent> NIGHTWARDEN_SUMMON_CLONE = REGISTRY.register("nightwarden_summon_clone", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "nightwarden_summon_clone")));
    public static final RegistryObject<SoundEvent> NIGHTWARDEN_SUMMON_CLONE_1 = REGISTRY.register("nightwarden_summon_clone_1", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "nightwarden_summon_clone_1")));
    public static final RegistryObject<SoundEvent> BLIZZARD_AOE_IDLE = REGISTRY.register("blizzard_aoe_idle", () -> SoundEvent.writeToNetwork((ResourceLocation)new ResourceLocation("traveloptics", "blizzard_aoe_idle")));
}

