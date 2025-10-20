/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.effect.SummonTimer
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraftforge.eventbus.api.IEventBus
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package com.gametechbc.traveloptics.init;

import com.gametechbc.traveloptics.effects.AbyssalStrike.AbyssalStrikeEffect;
import com.gametechbc.traveloptics.effects.AerialCollapseEffect;
import com.gametechbc.traveloptics.effects.AerialCollapseHelperEffect;
import com.gametechbc.traveloptics.effects.Assassin.AssassinEffect;
import com.gametechbc.traveloptics.effects.AstralPathRevealEffect;
// import com.gametechbc.traveloptics.effects.AstralSenseEffect; // REMOVED (FogRenderer access issue)
// import com.gametechbc.traveloptics.effects.AstralSenseTreasureEffect; // REMOVED (FogRenderer access issue)
import com.gametechbc.traveloptics.effects.Blackout.BlackoutEffect;
import com.gametechbc.traveloptics.effects.Casting.CastingEffect;
import com.gametechbc.traveloptics.effects.ConsumeEffect;
import com.gametechbc.traveloptics.effects.CorrodedEffect;
import com.gametechbc.traveloptics.effects.CrimsonDescendEffect;
import com.gametechbc.traveloptics.effects.FlareVacuumEffect;
import com.gametechbc.traveloptics.effects.FloodgateEffect;
import com.gametechbc.traveloptics.effects.FrozenSight.FrozenSightEffect;
import com.gametechbc.traveloptics.effects.JetSteamEffect;
import com.gametechbc.traveloptics.effects.LingeringStrain.LingeringStrainEffect;
import com.gametechbc.traveloptics.effects.MeteorStormEffect;
import com.gametechbc.traveloptics.effects.OrbitalVoidEffect;
import com.gametechbc.traveloptics.effects.OverloadedEffect;
import com.gametechbc.traveloptics.effects.PhantomRageEffect;
import com.gametechbc.traveloptics.effects.PsychicControlEffect;
import com.gametechbc.traveloptics.effects.ReplenishEffect;
import com.gametechbc.traveloptics.effects.Reversal.ReversalEffect;
import com.gametechbc.traveloptics.effects.SpectralBlinkEffect;
import com.gametechbc.traveloptics.effects.SpiritDamageHelperEffect;
import com.gametechbc.traveloptics.effects.ThirdPersonSwitchEffect;
import com.gametechbc.traveloptics.effects.TidalGraspEffect;
import com.gametechbc.traveloptics.effects.TidalGraspHelperEffect;
import com.gametechbc.traveloptics.effects.TidalTormentEffect;
import com.gametechbc.traveloptics.effects.VoidCollapseEffect;
import com.gametechbc.traveloptics.effects.VortexPunchEffect;
import com.gametechbc.traveloptics.effects.WetEffect;
import com.gametechbc.traveloptics.effects.nullflare.NullflareFireEffect;
import com.gametechbc.traveloptics.effects.nullflare.NullflareIceEffect;
import com.gametechbc.traveloptics.effects.vigor_siphon.VigorSiphonEffect;
import io.redspace.ironsspellbooks.effect.SummonTimer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class TravelopticsEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create((IForgeRegistry)ForgeRegistries.MOB_EFFECTS, (String)"traveloptics");
    public static final RegistryObject<MobEffect> FROZEN_SIGHT = MOB_EFFECTS.register("frozen_sight", FrozenSightEffect::new);
    public static final RegistryObject<MobEffect> VIGOR_SIPHON = MOB_EFFECTS.register("vigor_siphon", VigorSiphonEffect::new);
    public static final RegistryObject<MobEffect> ABYSSAL_STRIKE = MOB_EFFECTS.register("abyssal_strike", AbyssalStrikeEffect::new);
    public static final RegistryObject<MobEffect> ORBITAL_VOID = MOB_EFFECTS.register("orbital_void", OrbitalVoidEffect::new);
    public static final RegistryObject<MobEffect> ASSASSIN = MOB_EFFECTS.register("assassin", AssassinEffect::new);
    public static final RegistryObject<MobEffect> LINGERING_STRAIN = MOB_EFFECTS.register("lingering_strain", LingeringStrainEffect::new);
    public static final RegistryObject<MobEffect> AERIAL_COLLAPSE = MOB_EFFECTS.register("aerial_collapse", AerialCollapseEffect::new);
    public static final RegistryObject<MobEffect> AERIAL_COLLAPSE_HELPER = MOB_EFFECTS.register("aerial_collapse_helper", AerialCollapseHelperEffect::new);
    public static final RegistryObject<MobEffect> SPECTRAL_BLINK = MOB_EFFECTS.register("spectral_blink", SpectralBlinkEffect::new);
    public static final RegistryObject<MobEffect> BLACKOUT = MOB_EFFECTS.register("blackout", BlackoutEffect::new);
    public static final RegistryObject<MobEffect> NULLFLARE_ICE = MOB_EFFECTS.register("nullflare_ice", NullflareIceEffect::new);
    public static final RegistryObject<MobEffect> NULLFLARE_FIRE = MOB_EFFECTS.register("nullflare_fire", NullflareFireEffect::new);
    public static final RegistryObject<MobEffect> THIRD_PERSON_SWITCH = MOB_EFFECTS.register("third_person_switch", ThirdPersonSwitchEffect::new);
    public static final RegistryObject<MobEffect> METEOR_STORM = MOB_EFFECTS.register("meteor_storm", MeteorStormEffect::new);
    public static final RegistryObject<MobEffect> REVERSAL = MOB_EFFECTS.register("reversal", ReversalEffect::new);
    public static final RegistryObject<MobEffect> CONSUME = MOB_EFFECTS.register("consume", ConsumeEffect::new);
    public static final RegistryObject<MobEffect> VORTEX_PUNCH = MOB_EFFECTS.register("vortex_punch", VortexPunchEffect::new);
    public static final RegistryObject<MobEffect> CASTING = MOB_EFFECTS.register("casting", CastingEffect::new);
    public static final RegistryObject<MobEffect> PHANTOM_RAGE = MOB_EFFECTS.register("phantom_rage", PhantomRageEffect::new);
    public static final RegistryObject<MobEffect> SPIRIT_DAMAGE_HELPER = MOB_EFFECTS.register("spirit_damage_helper", SpiritDamageHelperEffect::new);
    public static final RegistryObject<MobEffect> OVERLOADED_EFFECT = MOB_EFFECTS.register("overloaded", OverloadedEffect::new);
    public static final RegistryObject<MobEffect> FLARE_VACUUM_EFFECT = MOB_EFFECTS.register("flare_vacuum", FlareVacuumEffect::new);
    public static final RegistryObject<MobEffect> REPLENISH_EFFECT = MOB_EFFECTS.register("replenish", ReplenishEffect::new);
    public static final RegistryObject<MobEffect> JET_STEAM = MOB_EFFECTS.register("jet_steam", JetSteamEffect::new);
    public static final RegistryObject<MobEffect> WET = MOB_EFFECTS.register("wet", WetEffect::new);
    public static final RegistryObject<MobEffect> CORRODED = MOB_EFFECTS.register("corroded", CorrodedEffect::new);
    public static final RegistryObject<MobEffect> TIDAL_GRASP_HELPER = MOB_EFFECTS.register("tidal_grasp_helper", TidalGraspHelperEffect::new);
    public static final RegistryObject<MobEffect> TIDAL_GRASP = MOB_EFFECTS.register("tidal_grasp", TidalGraspEffect::new);
    public static final RegistryObject<MobEffect> FLOODGATE_EFFECT = MOB_EFFECTS.register("floodgate", FloodgateEffect::new);
    public static final RegistryObject<MobEffect> PSYCHIC_CONTROL = MOB_EFFECTS.register("psychic_control", PsychicControlEffect::new);
    public static final RegistryObject<MobEffect> ASTRAL_PATH_REVEAL = MOB_EFFECTS.register("astral_path_reveal", AstralPathRevealEffect::new);
    // public static final RegistryObject<MobEffect> ASTRAL_SENSE = MOB_EFFECTS.register("astral_sense", AstralSenseEffect::new); // REMOVED (FogRenderer access issue)
    // public static final RegistryObject<MobEffect> ASTRAL_SENSE_TREASURE = MOB_EFFECTS.register("astral_sense_treasure", AstralSenseTreasureEffect::new); // REMOVED (FogRenderer access issue)
    public static final RegistryObject<MobEffect> CRIMSON_DESCEND = MOB_EFFECTS.register("crimson_descend", CrimsonDescendEffect::new);
    public static final RegistryObject<MobEffect> TIDAL_TORMENT = MOB_EFFECTS.register("tidal_torment", TidalTormentEffect::new);
    public static final RegistryObject<MobEffect> VOID_COLLAPSE_EFFECT = MOB_EFFECTS.register("void_collapse", VoidCollapseEffect::new);
    public static final RegistryObject<SummonTimer> DESERT_DWELLER_TIMER = MOB_EFFECTS.register("desert_dweller_timer", () -> new SummonTimer(MobEffectCategory.BENEFICIAL, 12495141));
    public static final RegistryObject<SummonTimer> KOBOLEDIATOR_TIMER = MOB_EFFECTS.register("kobolediator_timer", () -> new SummonTimer(MobEffectCategory.BENEFICIAL, 12495141));
    public static final RegistryObject<SummonTimer> MAGNETRON_TIMER = MOB_EFFECTS.register("magnetron_timer", () -> new SummonTimer(MobEffectCategory.BENEFICIAL, 12495141));
    public static final RegistryObject<SummonTimer> MECHANIZED_PREDATOR_TIMER = MOB_EFFECTS.register("mechanized_predator_timer", () -> new SummonTimer(MobEffectCategory.BENEFICIAL, 12495141));
    public static final RegistryObject<SummonTimer> GUM_WORM_TIMER = MOB_EFFECTS.register("gum_worm_timer", () -> new SummonTimer(MobEffectCategory.BENEFICIAL, 12495141));
    public static final RegistryObject<SummonTimer> FORSAKEN_TIMER = MOB_EFFECTS.register("forsaken_timer", () -> new SummonTimer(MobEffectCategory.BENEFICIAL, 12495141));
    public static final RegistryObject<SummonTimer> PRIMAL_PACK_TIMER = MOB_EFFECTS.register("primal_pack_timer", () -> new SummonTimer(MobEffectCategory.BENEFICIAL, 12495141));
    public static final RegistryObject<SummonTimer> ATLATITAN_TIMER = MOB_EFFECTS.register("atlatitan_timer", () -> new SummonTimer(MobEffectCategory.BENEFICIAL, 12495141));
    public static final RegistryObject<SummonTimer> VESPER_TIMER = MOB_EFFECTS.register("vesper_timer", () -> new SummonTimer(MobEffectCategory.BENEFICIAL, 12495141));
    public static final RegistryObject<SummonTimer> IGNITED_ONSLAUGHT_TIMER = MOB_EFFECTS.register("ignited_onslaught_timer", () -> new SummonTimer(MobEffectCategory.BENEFICIAL, 12495141));
    public static final RegistryObject<SummonTimer> ENDER_GOLEM_TIMER = MOB_EFFECTS.register("ender_golem_timer", () -> new SummonTimer(MobEffectCategory.BENEFICIAL, 12495141));
    public static final RegistryObject<SummonTimer> CURSED_REVENANTS_TIMER = MOB_EFFECTS.register("cursed_revenants_timer", () -> new SummonTimer(MobEffectCategory.BENEFICIAL, 12495141));
    public static final RegistryObject<SummonTimer> APTRGANGR_TIMER = MOB_EFFECTS.register("aptrgangr_timer", () -> new SummonTimer(MobEffectCategory.BENEFICIAL, 12495141));
    public static final RegistryObject<SummonTimer> HULLBREAKER_TIMER = MOB_EFFECTS.register("hullbreaker_timer", () -> new SummonTimer(MobEffectCategory.BENEFICIAL, 12495141));
    public static final RegistryObject<SummonTimer> VOID_TOME_TIMER = MOB_EFFECTS.register("void_tome_timer", () -> new SummonTimer(MobEffectCategory.BENEFICIAL, 12495141));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}

