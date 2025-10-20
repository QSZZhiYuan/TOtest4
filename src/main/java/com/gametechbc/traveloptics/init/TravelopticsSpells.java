/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.registry.SpellRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  net.minecraft.resources.ResourceKey
 *  net.minecraftforge.eventbus.api.IEventBus
 *  net.minecraftforge.fml.ModList
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.RegistryObject
 */
package com.gametechbc.traveloptics.init;

import com.gametechbc.traveloptics.compat.spells.eldritch.ViolentSkreechSpell;
import com.gametechbc.traveloptics.spells.aqua.AquaMissilesSpell;
import com.gametechbc.traveloptics.spells.aqua.BubbleSpraySpell;
import com.gametechbc.traveloptics.spells.aqua.CoralBarrageSpell;
// import com.gametechbc.traveloptics.spells.aqua.EchoOfTheAbyssSpell; // REMOVED (AC dependency)
import com.gametechbc.traveloptics.spells.aqua.FloodSlashSpell;
import com.gametechbc.traveloptics.spells.aqua.FloodgateSpell;
import com.gametechbc.traveloptics.spells.aqua.HydroshotSpell;
import com.gametechbc.traveloptics.spells.aqua.JetSteamSpell;
import com.gametechbc.traveloptics.spells.aqua.RainfallSpell;
import com.gametechbc.traveloptics.spells.aqua.SerpentideSpell;
import com.gametechbc.traveloptics.spells.aqua.SkypiercerSpell;
import com.gametechbc.traveloptics.spells.aqua.TheHowlingTempestSpell;
import com.gametechbc.traveloptics.spells.aqua.TidalGraspSpell;
import com.gametechbc.traveloptics.spells.aqua.TsunamiSpell;
import com.gametechbc.traveloptics.spells.aqua.VortexOfTheDeepSpell;
import com.gametechbc.traveloptics.spells.blood.BloodHowlSpell;
import com.gametechbc.traveloptics.spells.blood.CallForthTheDeadKingSpell;
import com.gametechbc.traveloptics.spells.blood.EekSpell;
// import com.gametechbc.traveloptics.spells.blood.NocturnalSwarmSpell; // REMOVED (AC dependency)
import com.gametechbc.traveloptics.spells.blood.VigorSiphonSpell;
import com.gametechbc.traveloptics.spells.eldritch.AbyssalBlastSpell;
import com.gametechbc.traveloptics.spells.eldritch.BlackoutSpell;
import com.gametechbc.traveloptics.spells.eldritch.PsychicBoltSpell;
import com.gametechbc.traveloptics.spells.eldritch.ReversalSpell;
import com.gametechbc.traveloptics.spells.eldritch.ShadowedMiasmaSpell;
import com.gametechbc.traveloptics.spells.eldritch.SpectralBlinkSpell;
// import com.gametechbc.traveloptics.spells.eldritch.TheForgottenBeastSpell; // REMOVED (AC dependency)
import com.gametechbc.traveloptics.spells.ender.AstralSenseSpell;
import com.gametechbc.traveloptics.spells.ender.BlinkSpell;
import com.gametechbc.traveloptics.spells.ender.ConjureVoidTomesSpell;
import com.gametechbc.traveloptics.spells.ender.CursedMinefieldSpell;
import com.gametechbc.traveloptics.spells.ender.EternalSentinelSpell;
import com.gametechbc.traveloptics.spells.ender.OrbitalVoidSpell;
import com.gametechbc.traveloptics.spells.ender.ShearOfTheStarsSpell;
import com.gametechbc.traveloptics.spells.ender.SupernovaSpell;
import com.gametechbc.traveloptics.spells.ender.VoidEruptionSpell;
import com.gametechbc.traveloptics.spells.ender.VortexPunchSpell;
import com.gametechbc.traveloptics.spells.evocation.AshenBreathSpell;
import com.gametechbc.traveloptics.spells.evocation.LingeringStrainSpell;
import com.gametechbc.traveloptics.spells.fire.AnnihilationSpell;
import com.gametechbc.traveloptics.spells.fire.BurningJudgmentSpell;
import com.gametechbc.traveloptics.spells.fire.ExtinctionSpell;
import com.gametechbc.traveloptics.spells.fire.GyroSlashSpell;
import com.gametechbc.traveloptics.spells.fire.IgnitedOnslaughtSpell;
import com.gametechbc.traveloptics.spells.fire.LavaBombSpell;
import com.gametechbc.traveloptics.spells.fire.MeteorStormSpell;
import com.gametechbc.traveloptics.spells.holy.NullflareSpell;
import com.gametechbc.traveloptics.spells.holy.SummonDesertDwellers;
import com.gametechbc.traveloptics.spells.holy.SwordOfTheAncientsSpell;
import com.gametechbc.traveloptics.spells.ice.AxeOfTheDoomedSpell;
import com.gametechbc.traveloptics.spells.ice.CursedBlastSpell;
import com.gametechbc.traveloptics.spells.ice.CursedRevenantsSpell;
import com.gametechbc.traveloptics.spells.ice.DespairSpell;
import com.gametechbc.traveloptics.spells.ice.HalberdHorizonSpell;
// import com.gametechbc.traveloptics.spells.ice.StickySteedSummonSpell; // REMOVED (AC dependency)
import com.gametechbc.traveloptics.spells.lightning.DeathLaserSpell;
import com.gametechbc.traveloptics.spells.lightning.EmPulse;
// import com.gametechbc.traveloptics.spells.lightning.MagnetronDeploymentSpell; // REMOVED (AC dependency)
// import com.gametechbc.traveloptics.spells.lightning.MechanizedPredatorSpell; // REMOVED (AC dependency)
import com.gametechbc.traveloptics.spells.lightning.RapidLaserSpell;
import com.gametechbc.traveloptics.spells.nature.AerialCollapseSpell;
// import com.gametechbc.traveloptics.spells.nature.PrimalPackSpell; // REMOVED (AC dependency)
    // REMOVED (AC dependency): import com.gametechbc.traveloptics.spells.nature.PrimordialSteedSpell;
import com.gametechbc.traveloptics.spells.nature.SteleCascadeSpell;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TravelopticsSpells {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create((ResourceKey)SpellRegistry.SPELL_REGISTRY_KEY, (String)"traveloptics");
    public static final RegistryObject<AbstractSpell> VORTEX_OF_THE_DEEP_SPELL = TravelopticsSpells.registerSpell(new VortexOfTheDeepSpell());
    public static final RegistryObject<AbstractSpell> BUBBLE_SPRAY_SPELL = TravelopticsSpells.registerSpell(new BubbleSpraySpell());
    public static final RegistryObject<AbstractSpell> HYDROSHOT_SPELL = TravelopticsSpells.registerSpell(new HydroshotSpell());
    public static final RegistryObject<AbstractSpell> AQUA_MISSILES_SPELL = TravelopticsSpells.registerSpell(new AquaMissilesSpell());
    public static final RegistryObject<AbstractSpell> JET_STREAM_SPELL = TravelopticsSpells.registerSpell(new JetSteamSpell());
    // public static final RegistryObject<AbstractSpell> ECHO_OF_THE_ABYSS_SPELL = TravelopticsSpells.registerSpell(new EchoOfTheAbyssSpell()); // REMOVED (AC dependency)
    public static final RegistryObject<AbstractSpell> RAINFALL_SPELL = TravelopticsSpells.registerSpell(new RainfallSpell());
    public static final RegistryObject<AbstractSpell> FLOOD_SLASH_SPELL = TravelopticsSpells.registerSpell(new FloodSlashSpell());
    public static final RegistryObject<AbstractSpell> CORAL_BARRAGE_SPELL = TravelopticsSpells.registerSpell(new CoralBarrageSpell());
    public static final RegistryObject<AbstractSpell> TIDAL_GRASP_SPELL = TravelopticsSpells.registerSpell(new TidalGraspSpell());
    public static final RegistryObject<AbstractSpell> FLOODGATE_SPELL = TravelopticsSpells.registerSpell(new FloodgateSpell());
    public static final RegistryObject<AbstractSpell> TSUNAMI_SPELL = TravelopticsSpells.registerSpell(new TsunamiSpell());
    public static final RegistryObject<AbstractSpell> SKYPIERCER_SPELL = TravelopticsSpells.registerSpell(new SkypiercerSpell());
    public static final RegistryObject<AbstractSpell> SERPENTIDE_SPELL = TravelopticsSpells.registerSpell(new SerpentideSpell());
    public static final RegistryObject<AbstractSpell> THE_HOWLING_TEMPEST = TravelopticsSpells.registerSpell(new TheHowlingTempestSpell());
    public static final RegistryObject<AbstractSpell> VIGOR_SIPHON_SPELL = TravelopticsSpells.registerSpell(new VigorSiphonSpell());
    public static final RegistryObject<AbstractSpell> BLOOD_HOWL_SPELL = TravelopticsSpells.registerSpell(new BloodHowlSpell());
    public static final RegistryObject<AbstractSpell> EEK_SPELL = TravelopticsSpells.registerSpell(new EekSpell());
    // public static final RegistryObject<AbstractSpell> NOCTURNAL_SWARM_SPELL = TravelopticsSpells.registerSpell(new NocturnalSwarmSpell()); // REMOVED (AC dependency)
    public static final RegistryObject<AbstractSpell> CALL_FORTH_THE_DEAD_KING_SPELL = TravelopticsSpells.registerSpell(new CallForthTheDeadKingSpell());
    public static final RegistryObject<AbstractSpell> ABYSSAL_BLAST_SPELL = TravelopticsSpells.registerSpell(new AbyssalBlastSpell());
    public static final RegistryObject<AbstractSpell> SHADOWED_MIASMA_SPELL = TravelopticsSpells.registerSpell((AbstractSpell)new ShadowedMiasmaSpell());
    public static final RegistryObject<AbstractSpell> SPECTRAL_BLINK_SPELL = TravelopticsSpells.registerSpell((AbstractSpell)new SpectralBlinkSpell());
    public static final RegistryObject<AbstractSpell> REVERSAL_SPELL = TravelopticsSpells.registerSpell((AbstractSpell)new ReversalSpell());
    // public static final RegistryObject<AbstractSpell> THE_FORGOTTEN_BEAST_SPELL = TravelopticsSpells.registerSpell(new TheForgottenBeastSpell()); // REMOVED (AC dependency)
    public static final RegistryObject<AbstractSpell> BLACKOUT_SPELL = TravelopticsSpells.registerSpell(new BlackoutSpell());
    public static final RegistryObject<AbstractSpell> PSYCHIC_BOLT_SPELL = TravelopticsSpells.registerSpell((AbstractSpell)new PsychicBoltSpell());
    public static final RegistryObject<AbstractSpell> VOID_ERUPTION_SPELL = TravelopticsSpells.registerSpell(new VoidEruptionSpell());
    public static final RegistryObject<AbstractSpell> CURSED_MINEFIELD_SPELL = TravelopticsSpells.registerSpell(new CursedMinefieldSpell());
    public static final RegistryObject<AbstractSpell> ORBITAL_VOID_SPELL = TravelopticsSpells.registerSpell(new OrbitalVoidSpell());
    public static final RegistryObject<AbstractSpell> VORTEX_PUNCH_SPELL = TravelopticsSpells.registerSpell(new VortexPunchSpell());
    public static final RegistryObject<AbstractSpell> ETERNAL_SENTINEL_SPELL = TravelopticsSpells.registerSpell(new EternalSentinelSpell());
    public static final RegistryObject<AbstractSpell> ASTRAL_SENSE_SPELL = TravelopticsSpells.registerSpell(new AstralSenseSpell());
    public static final RegistryObject<AbstractSpell> CONJURE_VOID_TOMES_SPELL = TravelopticsSpells.registerSpell(new ConjureVoidTomesSpell());
    public static final RegistryObject<AbstractSpell> BLINK_SPELL = TravelopticsSpells.registerSpell(new BlinkSpell());
    public static final RegistryObject<AbstractSpell> SUPERNOVA_SPELL = TravelopticsSpells.registerSpell(new SupernovaSpell());
    public static final RegistryObject<AbstractSpell> SHEAR_OF_THE_STARS_SPELL = TravelopticsSpells.registerSpell(new ShearOfTheStarsSpell());
    public static final RegistryObject<AbstractSpell> LINGERING_STRAIN = TravelopticsSpells.registerSpell(new LingeringStrainSpell());
    public static final RegistryObject<AbstractSpell> ASHEN_BREATH = TravelopticsSpells.registerSpell(new AshenBreathSpell());
    public static final RegistryObject<AbstractSpell> ANNIHILATION_SPELL = TravelopticsSpells.registerSpell(new AnnihilationSpell());
    public static final RegistryObject<AbstractSpell> LAVA_BOMB_SPELL = TravelopticsSpells.registerSpell(new LavaBombSpell());
    public static final RegistryObject<AbstractSpell> BURNING_JUDGMENT = TravelopticsSpells.registerSpell(new BurningJudgmentSpell());
    public static final RegistryObject<AbstractSpell> METEOR_STORM = TravelopticsSpells.registerSpell(new MeteorStormSpell());
    public static final RegistryObject<AbstractSpell> IGNITED_ONSLAUGHT_SPELL = TravelopticsSpells.registerSpell(new IgnitedOnslaughtSpell());
    public static final RegistryObject<AbstractSpell> EXTINCTION_SPELL = TravelopticsSpells.registerSpell(new ExtinctionSpell());
    public static final RegistryObject<AbstractSpell> GYRO_SLASH_SPELL = TravelopticsSpells.registerSpell(new GyroSlashSpell());
    public static final RegistryObject<AbstractSpell> NULLFLARE_SPELL = TravelopticsSpells.registerSpell(new NullflareSpell());
    public static final RegistryObject<AbstractSpell> SWORD_OF_THE_ANCIENTS_SPELL = TravelopticsSpells.registerSpell(new SwordOfTheAncientsSpell());
    public static final RegistryObject<AbstractSpell> SUMMON_DESERT_DWELLERS = TravelopticsSpells.registerSpell(new SummonDesertDwellers());
    public static final RegistryObject<AbstractSpell> CURSED_BLAST_SPELL = TravelopticsSpells.registerSpell(new CursedBlastSpell());
    public static final RegistryObject<AbstractSpell> HALBERD_HORIZON = TravelopticsSpells.registerSpell(new HalberdHorizonSpell());
    public static final RegistryObject<AbstractSpell> DESPAIR = TravelopticsSpells.registerSpell(new DespairSpell());
    // public static final RegistryObject<AbstractSpell> STICKY_STEED_SUMMON_SPELL = TravelopticsSpells.registerSpell(new StickySteedSummonSpell()); // REMOVED (AC dependency)
    public static final RegistryObject<AbstractSpell> CURSED_REVENANTS_SPELL = TravelopticsSpells.registerSpell(new CursedRevenantsSpell());
    public static final RegistryObject<AbstractSpell> AXE_OF_THE_DOOMED_SPELL = TravelopticsSpells.registerSpell(new AxeOfTheDoomedSpell());
    public static final RegistryObject<AbstractSpell> EM_PULSE = TravelopticsSpells.registerSpell(new EmPulse());
    public static final RegistryObject<AbstractSpell> RAPID_LASER_SPELL = TravelopticsSpells.registerSpell(new RapidLaserSpell());
    public static final RegistryObject<AbstractSpell> DEATH_LASER_SPELL = TravelopticsSpells.registerSpell(new DeathLaserSpell());
    // public static final RegistryObject<AbstractSpell> MAGNETRON_DEPLOYMENT_SPELL = TravelopticsSpells.registerSpell(new MagnetronDeploymentSpell()); // REMOVED (AC dependency)
    // public static final RegistryObject<AbstractSpell> MECHANIZED_PREDATOR_SPELL = TravelopticsSpells.registerSpell(new MechanizedPredatorSpell()); // REMOVED (AC dependency)
    public static final RegistryObject<AbstractSpell> STELE_CASCADE_SPELL = TravelopticsSpells.registerSpell(new SteleCascadeSpell());
    public static final RegistryObject<AbstractSpell> AERIAL_COLLAPSE = TravelopticsSpells.registerSpell(new AerialCollapseSpell());
    // public static final RegistryObject<AbstractSpell> PRIMAL_PACK_SPELL = TravelopticsSpells.registerSpell(new PrimalPackSpell()); // REMOVED (AC dependency)
    // REMOVED (AC dependency):     public static final RegistryObject<AbstractSpell> PRIMORDIAL_STEED_SPELL = TravelopticsSpells.registerSpell(new PrimordialSteedSpell());
    public static final RegistryObject<AbstractSpell> VIOLENT_SKREECH_SPELL = ModList.get().isLoaded("alexsmobs") ? TravelopticsSpells.registerSpell((AbstractSpell)new ViolentSkreechSpell()) : null;

    public static RegistryObject<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    public static void register(IEventBus eventBus) {
        SPELLS.register(eventBus);
    }
}

