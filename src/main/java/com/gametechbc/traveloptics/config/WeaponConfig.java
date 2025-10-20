/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.electronwill.nightconfig.core.CommentedConfig
 *  com.electronwill.nightconfig.core.file.CommentedFileConfig
 *  com.electronwill.nightconfig.core.io.WritingMode
 *  net.minecraftforge.common.ForgeConfigSpec
 *  net.minecraftforge.common.ForgeConfigSpec$BooleanValue
 *  net.minecraftforge.common.ForgeConfigSpec$Builder
 *  net.minecraftforge.common.ForgeConfigSpec$DoubleValue
 *  net.minecraftforge.common.ForgeConfigSpec$IntValue
 */
package com.gametechbc.traveloptics.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import java.io.File;
import net.minecraftforge.common.ForgeConfigSpec;

public class WeaponConfig {
    public static final ForgeConfigSpec WEAPON;
    public static ForgeConfigSpec.BooleanValue weaponsShouldBeBreakable;
    public static ForgeConfigSpec.IntValue abyssalTidecallerDurability;
    public static ForgeConfigSpec.DoubleValue abyssalTidecallerDamage;
    public static ForgeConfigSpec.DoubleValue abyssalTidecallerAttackSpeed;
    public static ForgeConfigSpec.DoubleValue abyssalTidecallerEldritchSpellPower;
    public static ForgeConfigSpec.DoubleValue abyssalTidecallerEnderSpellPower;
    public static ForgeConfigSpec.IntValue flamesOfEldritchDurability;
    public static ForgeConfigSpec.DoubleValue flamesOfEldritchDamage;
    public static ForgeConfigSpec.DoubleValue flamesOfEldritchAttackSpeed;
    public static ForgeConfigSpec.DoubleValue flamesOfEldritchEldritchSpellPower;
    public static ForgeConfigSpec.DoubleValue flamesOfEldritchFireSpellPower;
    public static ForgeConfigSpec.IntValue harbingersWrathDurability;
    public static ForgeConfigSpec.DoubleValue harbingersWrathDamage;
    public static ForgeConfigSpec.DoubleValue harbingersWrathAttackSpeed;
    public static ForgeConfigSpec.DoubleValue harbingersWrathLightningSpellPower;
    public static ForgeConfigSpec.IntValue scourgeSandsDurability;
    public static ForgeConfigSpec.DoubleValue scourgeSandsDamage;
    public static ForgeConfigSpec.DoubleValue scourgeSandsAttackSpeed;
    public static ForgeConfigSpec.DoubleValue scourgeSandsEvocationSpellPower;
    public static ForgeConfigSpec.DoubleValue scourgeSandsNatureSpellPower;
    public static ForgeConfigSpec.IntValue thornsOblivionDurability;
    public static ForgeConfigSpec.DoubleValue thornsOblivionDamage;
    public static ForgeConfigSpec.DoubleValue thornsOblivionAttackSpeed;
    public static ForgeConfigSpec.DoubleValue thornsOblivionNatureSpellPower;
    public static ForgeConfigSpec.DoubleValue thornsOblivionEnderSpellPower;
    public static ForgeConfigSpec.IntValue voidstrikeReaperDurability;
    public static ForgeConfigSpec.DoubleValue voidstrikeReaperDamage;
    public static ForgeConfigSpec.DoubleValue voidstrikeReaperAttackSpeed;
    public static ForgeConfigSpec.DoubleValue voidstrikeReaperEnderSpellPower;
    public static ForgeConfigSpec.IntValue cursedWraithbladeDurability;
    public static ForgeConfigSpec.DoubleValue cursedWraithbladeDamage;
    public static ForgeConfigSpec.DoubleValue cursedWraithbladeAttackSpeed;
    public static ForgeConfigSpec.DoubleValue cursedWraithbladeIceSpellPower;
    public static ForgeConfigSpec.DoubleValue cursedWraithbladeEldritchSpellPower;
    public static ForgeConfigSpec.IntValue infernalDevastatorDurability;
    public static ForgeConfigSpec.DoubleValue infernalDevastatorDamage;
    public static ForgeConfigSpec.DoubleValue infernalDevastatorAttackSpeed;
    public static ForgeConfigSpec.DoubleValue infernalDevastatorFireSpellPower;
    public static ForgeConfigSpec.DoubleValue infernalDevastatorBlazingSalvoDamageMultiplier;
    public static ForgeConfigSpec.IntValue gauntletOfExtinctionDurability;
    public static ForgeConfigSpec.DoubleValue gauntletOfExtinctionDamage;
    public static ForgeConfigSpec.DoubleValue gauntletOfExtinctionAttackSpeed;
    public static ForgeConfigSpec.DoubleValue gauntletOfExtinctionFireSpellPower;
    public static ForgeConfigSpec.DoubleValue gauntletOfExtinctionPrimevalDevourDamageMultiplier;
    public static ForgeConfigSpec.IntValue mechanizedWraithbladeDurability;
    public static ForgeConfigSpec.DoubleValue mechanizedWraithbladeDamage;
    public static ForgeConfigSpec.DoubleValue mechanizedWraithbladeAttackSpeed;
    public static ForgeConfigSpec.DoubleValue mechanizedWraithbladeLightningSpellPower;
    public static ForgeConfigSpec.DoubleValue mechanizedWraithbladePlasmaOverdriveDamageMultiplier;
    public static ForgeConfigSpec.IntValue theObliteratorDurability;
    public static ForgeConfigSpec.DoubleValue theObliteratorDamage;
    public static ForgeConfigSpec.DoubleValue theObliteratorAttackSpeed;
    public static ForgeConfigSpec.DoubleValue theObliteratorEldritchSpellPower;
    public static ForgeConfigSpec.DoubleValue theObliteratorEnderSpellPower;
    public static ForgeConfigSpec.DoubleValue theObliteratorOblivionRayDamageMultiplier;
    public static ForgeConfigSpec.IntValue eternalMaelstromDurability;
    public static ForgeConfigSpec.DoubleValue eternalMaelstromDamage;
    public static ForgeConfigSpec.DoubleValue eternalMaelstromAquaSpellPower;
    public static ForgeConfigSpec.DoubleValue eternalMaelstromBoltstrikeDamageMultiplier;
    public static ForgeConfigSpec.IntValue stellothornDurability;
    public static ForgeConfigSpec.DoubleValue stellothornDamage;
    public static ForgeConfigSpec.DoubleValue stellothornAttackSpeed;
    public static ForgeConfigSpec.DoubleValue stellothornEldritchSpellPower;
    public static ForgeConfigSpec.DoubleValue stellothornEnderSpellPower;
    public static ForgeConfigSpec.DoubleValue stellothornCloneDamageMultiplier;

    public static void loadConfig(ForgeConfigSpec config, String path) {
        CommentedFileConfig file = (CommentedFileConfig)CommentedFileConfig.builder((File)new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig((CommentedConfig)file);
    }

    static {
        ForgeConfigSpec.Builder WEAPON_BUILDER = new ForgeConfigSpec.Builder();
        WEAPON_BUILDER.comment("Configs for weapons that currently mod has to offer and can be found in survival").push("current_weapons");
        weaponsShouldBeBreakable = WEAPON_BUILDER.comment("Should End-game weapons be breakable || Default: false").define("general.breakable_end_game_weapons", false);
        abyssalTidecallerDurability = WEAPON_BUILDER.comment("Durability || Default 2250").defineInRange("abyssal_tidecaller.durability", 2250, 1, Integer.MAX_VALUE);
        abyssalTidecallerDamage = WEAPON_BUILDER.comment("Damage || Default 21.0").defineInRange("abyssal_tidecaller.damage", 21.0, 0.0, 100.0);
        abyssalTidecallerAttackSpeed = WEAPON_BUILDER.comment("Attack Speed || Default -3.0").defineInRange("abyssal_tidecaller.attack_speed", -3.0, -5.0, 5.0);
        abyssalTidecallerEldritchSpellPower = WEAPON_BUILDER.comment("Eldritch Spell Power || MULTIPLY_BASE || Default 0.10").defineInRange("abyssal_tidecaller.eldritch_spell_power", 0.1, 0.0, 1.0);
        abyssalTidecallerEnderSpellPower = WEAPON_BUILDER.comment("Ender Spell Power || MULTIPLY_BASE || Default 0.10").defineInRange("abyssal_tidecaller.ender_spell_power", 0.1, 0.0, 1.0);
        flamesOfEldritchDurability = WEAPON_BUILDER.comment("Durability || Default 2250").defineInRange("flames_of_eldritch.durability", 2250, 1, Integer.MAX_VALUE);
        flamesOfEldritchDamage = WEAPON_BUILDER.comment("Damage || Default 10.0").defineInRange("flames_of_eldritch.damage", 10.0, 0.0, 100.0);
        flamesOfEldritchAttackSpeed = WEAPON_BUILDER.comment("Attack Speed || Default -2.6").defineInRange("flames_of_eldritch.attack_speed", -2.6, -5.0, 5.0);
        flamesOfEldritchEldritchSpellPower = WEAPON_BUILDER.comment("Eldritch Spell Power || MULTIPLY_BASE || Default 0.05").defineInRange("flames_of_eldritch.eldritch_spell_power", 0.05, 0.0, 1.0);
        flamesOfEldritchFireSpellPower = WEAPON_BUILDER.comment("Fire Spell Power || MULTIPLY_BASE || Default 0.15").defineInRange("flames_of_eldritch.fire_spell_power", 0.15, 0.0, 1.0);
        harbingersWrathDurability = WEAPON_BUILDER.comment("Durability || Default 2250").defineInRange("harbingers_wrath.durability", 2250, 1, Integer.MAX_VALUE);
        harbingersWrathDamage = WEAPON_BUILDER.comment("Damage || Default 19.0").defineInRange("harbingers_wrath.damage", 19.0, 0.0, 100.0);
        harbingersWrathAttackSpeed = WEAPON_BUILDER.comment("Attack Speed || Default -2.8").defineInRange("harbingers_wrath.attack_speed", -2.8, -5.0, 5.0);
        harbingersWrathLightningSpellPower = WEAPON_BUILDER.comment("Lightning Spell Power || MULTIPLY_BASE || Default 0.20").defineInRange("harbingers_wrath.lightning_spell_power", 0.2, 0.0, 1.0);
        scourgeSandsDurability = WEAPON_BUILDER.comment("Durability || Default 2250").defineInRange("scourge_of_the_sands.durability", 2250, 1, Integer.MAX_VALUE);
        scourgeSandsDamage = WEAPON_BUILDER.comment("Damage || Default 14.0").defineInRange("scourge_of_the_sands.damage", 14.0, 0.0, 100.0);
        scourgeSandsAttackSpeed = WEAPON_BUILDER.comment("Attack Speed || Default -2.4").defineInRange("scourge_of_the_sands.attack_speed", -2.4, -5.0, 5.0);
        scourgeSandsEvocationSpellPower = WEAPON_BUILDER.comment("Evocation Spell Power || MULTIPLY_BASE || Default 0.10").defineInRange("scourge_of_the_sands.evocation_spell_power", 0.1, 0.0, 1.0);
        scourgeSandsNatureSpellPower = WEAPON_BUILDER.comment("Nature Spell Power || MULTIPLY_BASE || Default 0.10").defineInRange("scourge_of_the_sands.nature_spell_power", 0.1, 0.0, 1.0);
        thornsOblivionDurability = WEAPON_BUILDER.comment("Durability || Default 2250").defineInRange("thorns_of_oblivion.durability", 2250, 1, Integer.MAX_VALUE);
        thornsOblivionDamage = WEAPON_BUILDER.comment("Damage || Default 14.0").defineInRange("thorns_of_oblivion.damage", 14.0, 0.0, 100.0);
        thornsOblivionAttackSpeed = WEAPON_BUILDER.comment("Attack Speed || Default -2.4").defineInRange("thorns_of_oblivion.attack_speed", -2.4, -5.0, 5.0);
        thornsOblivionNatureSpellPower = WEAPON_BUILDER.comment("Nature Spell Power || MULTIPLY_BASE || Default 0.10").defineInRange("thorns_of_oblivion.nature_spell_power", 0.1, 0.0, 1.0);
        thornsOblivionEnderSpellPower = WEAPON_BUILDER.comment("Ender Spell Power || MULTIPLY_BASE || Default 0.10").defineInRange("thorns_of_oblivion.ender_spell_power", 0.1, 0.0, 1.0);
        voidstrikeReaperDurability = WEAPON_BUILDER.comment("Durability || Default 2250").defineInRange("voidstrike_reaper.durability", 2250, 1, Integer.MAX_VALUE);
        voidstrikeReaperDamage = WEAPON_BUILDER.comment("Damage || Default 19.0").defineInRange("voidstrike_reaper.damage", 19.0, 0.0, 100.0);
        voidstrikeReaperAttackSpeed = WEAPON_BUILDER.comment("Attack Speed || Default -3.0").defineInRange("voidstrike_reaper.attack_speed", -3.0, -5.0, 5.0);
        voidstrikeReaperEnderSpellPower = WEAPON_BUILDER.comment("Ender Spell Power || MULTIPLY_BASE || Default 0.20").defineInRange("voidstrike_reaper.ender_spell_power", 0.2, 0.0, 1.0);
        cursedWraithbladeDurability = WEAPON_BUILDER.comment("Durability || Default 2250").defineInRange("cursed_wraithblade.durability", 2250, 1, Integer.MAX_VALUE);
        cursedWraithbladeDamage = WEAPON_BUILDER.comment("Damage || Default 19.0").defineInRange("cursed_wraithblade.damage", 19.0, 0.0, 100.0);
        cursedWraithbladeAttackSpeed = WEAPON_BUILDER.comment("Attack Speed || Default -2.8").defineInRange("cursed_wraithblade.attack_speed", -2.8, -5.0, 5.0);
        cursedWraithbladeIceSpellPower = WEAPON_BUILDER.comment("Ice Spell Power || MULTIPLY_BASE || Default 0.15").defineInRange("cursed_wraithblade.ice_spell_power", 0.15, 0.0, 1.0);
        cursedWraithbladeEldritchSpellPower = WEAPON_BUILDER.comment("Eldritch Spell Power || MULTIPLY_BASE || Default 0.05").defineInRange("cursed_wraithblade.eldritch_spell_power", 0.05, 0.0, 1.0);
        infernalDevastatorDurability = WEAPON_BUILDER.comment("Durability || Default 2250").defineInRange("infernal_devastator.durability", 2250, 1, Integer.MAX_VALUE);
        infernalDevastatorDamage = WEAPON_BUILDER.comment("Damage || Default 16.0").defineInRange("infernal_devastator.damage", 16.0, 0.0, 100.0);
        infernalDevastatorAttackSpeed = WEAPON_BUILDER.comment("Attack Speed || Default -2.6").defineInRange("infernal_devastator.attack_speed", -2.6, -5.0, 5.0);
        infernalDevastatorFireSpellPower = WEAPON_BUILDER.comment("Fire Spell Power || MULTIPLY_BASE || Default 0.2").defineInRange("infernal_devastator.fire_spell_power", 0.2, 0.0, 1.0);
        infernalDevastatorBlazingSalvoDamageMultiplier = WEAPON_BUILDER.comment("Blazing Salvo damage multiplier || Default 1.0").defineInRange("infernal_devastator.blazing_salvo_multiplier", 1.0, 1.0, 5.0);
        gauntletOfExtinctionDurability = WEAPON_BUILDER.comment("Durability || Default 2250").defineInRange("gauntlet_of_extinction.durability", 2250, 1, Integer.MAX_VALUE);
        gauntletOfExtinctionDamage = WEAPON_BUILDER.comment("Damage || Default 16.0").defineInRange("gauntlet_of_extinction.damage", 16.0, 0.0, 100.0);
        gauntletOfExtinctionAttackSpeed = WEAPON_BUILDER.comment("Attack Speed || Default -2.6").defineInRange("gauntlet_of_extinction.attack_speed", -2.6, -5.0, 5.0);
        gauntletOfExtinctionFireSpellPower = WEAPON_BUILDER.comment("Fire Spell Power || MULTIPLY_BASE || Default 0.2").defineInRange("gauntlet_of_extinction.fire_spell_power", 0.2, 0.0, 1.0);
        gauntletOfExtinctionPrimevalDevourDamageMultiplier = WEAPON_BUILDER.comment("Primeval Devour damage multiplier || Default 1 || WARNING: Use Integer Values! values that are not integer like 1.2 will be converted into integer anyways!").defineInRange("gauntlet_of_extinction.primeval_devour_multiplier", 1.0, 1.0, 5.0);
        mechanizedWraithbladeDurability = WEAPON_BUILDER.comment("Durability || Default 2250").defineInRange("mechanized_wraithblade.durability", 2250, 1, Integer.MAX_VALUE);
        mechanizedWraithbladeDamage = WEAPON_BUILDER.comment("Damage || Default 17.0").defineInRange("mechanized_wraithblade.damage", 17.0, 0.0, 100.0);
        mechanizedWraithbladeAttackSpeed = WEAPON_BUILDER.comment("Attack Speed || Default -2.7").defineInRange("mechanized_wraithblade.attack_speed", -2.7, -5.0, 5.0);
        mechanizedWraithbladeLightningSpellPower = WEAPON_BUILDER.comment("Lightning Spell Power || MULTIPLY_BASE || Default 0.2").defineInRange("mechanized_wraithblade.lightning_spell_power", 0.2, 0.0, 1.0);
        mechanizedWraithbladePlasmaOverdriveDamageMultiplier = WEAPON_BUILDER.comment("Plasma Overdrive damage multiplier || Default 1.0").defineInRange("mechanized_wraithblade.plasma_overdrive_multiplier", 1.0, 1.0, 5.0);
        theObliteratorDurability = WEAPON_BUILDER.comment("Durability || Default 2250").defineInRange("the_obliterator.durability", 2250, 1, Integer.MAX_VALUE);
        theObliteratorDamage = WEAPON_BUILDER.comment("Damage || Default 14.0").defineInRange("the_obliterator.damage", 14.0, 0.0, 100.0);
        theObliteratorAttackSpeed = WEAPON_BUILDER.comment("Attack Speed || Default -2.4").defineInRange("the_obliterator.attack_speed", -2.4, -5.0, 5.0);
        theObliteratorEldritchSpellPower = WEAPON_BUILDER.comment("Eldritch Spell Power || MULTIPLY_BASE || Default 0.10").defineInRange("the_obliterator.eldritch_spell_power", 0.1, 0.0, 1.0);
        theObliteratorEnderSpellPower = WEAPON_BUILDER.comment("Ender Spell Power || MULTIPLY_BASE || Default 0.10").defineInRange("the_obliterator.ender_spell_power", 0.1, 0.0, 1.0);
        theObliteratorOblivionRayDamageMultiplier = WEAPON_BUILDER.comment("Oblivion Ray damage multiplier || Default 1.0").defineInRange("the_obliterator.oblivion_ray_multiplier", 1.0, 1.0, 5.0);
        eternalMaelstromDurability = WEAPON_BUILDER.comment("Durability || Default 2250").defineInRange("trident_of_the_eternal_maelstrom.durability", 2250, 1, Integer.MAX_VALUE);
        eternalMaelstromDamage = WEAPON_BUILDER.comment("Damage || Default 14.0").defineInRange("trident_of_the_eternal_maelstrom.damage", 14.0, 0.0, 100.0);
        eternalMaelstromAquaSpellPower = WEAPON_BUILDER.comment("Aqua Spell Power || MULTIPLY_BASE || Default 0.20").defineInRange("trident_of_the_eternal_maelstrom.aqua_spell_power", 0.2, 0.0, 1.0);
        eternalMaelstromBoltstrikeDamageMultiplier = WEAPON_BUILDER.comment("Boltstrike damage multiplier || Default 1.0").defineInRange("trident_of_the_eternal_maelstrom.boltstrike_multiplier", 1.0, 1.0, 5.0);
        stellothornDurability = WEAPON_BUILDER.comment("Durability || Default 3000").defineInRange("stellothorn.durability", 3000, 1, Integer.MAX_VALUE);
        stellothornDamage = WEAPON_BUILDER.comment("Damage || Default 21.0").defineInRange("stellothorn.damage", 21.0, 0.0, 100.0);
        stellothornAttackSpeed = WEAPON_BUILDER.comment("Attack Speed || Default -3.0").defineInRange("stellothorn.attack_speed", -3.0, -5.0, 5.0);
        stellothornEldritchSpellPower = WEAPON_BUILDER.comment("Eldritch Spell Power || MULTIPLY_BASE || Default 0.05").defineInRange("stellothorn.eldritch_spell_power_new", 0.05, 0.0, 1.0);
        stellothornEnderSpellPower = WEAPON_BUILDER.comment("Ender Spell Power || MULTIPLY_BASE || Default 0.15").defineInRange("stellothorn.ender_spell_power_new", 0.15, 0.0, 1.0);
        stellothornCloneDamageMultiplier = WEAPON_BUILDER.comment("Spectral Guillotine damage multiplier || Default 1.0").defineInRange("stellothorn.spectral_guillotine_multiplier", 1.0, 1.0, 5.0);
        WEAPON_BUILDER.pop();
        WEAPON = WEAPON_BUILDER.build();
    }
}

