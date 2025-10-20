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
 */
package com.gametechbc.traveloptics.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import java.io.File;
import net.minecraftforge.common.ForgeConfigSpec;

public class SpellsConfig {
    public static final ForgeConfigSpec SPELLS_SPEC;
    public static ForgeConfigSpec.BooleanValue annihilationCauseExplode;
    public static ForgeConfigSpec.BooleanValue annihilationCauseFire;
    public static ForgeConfigSpec.DoubleValue aerialCollapseDamageReductionModifier;
    public static ForgeConfigSpec.BooleanValue abyssalBlastBlockBreaking;
    public static ForgeConfigSpec.BooleanValue abyssalBlastDealMagicDamage;
    public static ForgeConfigSpec.BooleanValue deathLaserBlockBreaking;
    public static ForgeConfigSpec.BooleanValue deathLaserDealMagicDamage;
    public static ForgeConfigSpec.BooleanValue limitGroupSummons;
    public static ForgeConfigSpec.BooleanValue limitKaijuSummons;
    public static ForgeConfigSpec.BooleanValue limitMinibossSummons;
    public static ForgeConfigSpec.DoubleValue titanlordScepterDamage;
    public static ForgeConfigSpec.DoubleValue titanlordScepterAttackSpeed;
    public static ForgeConfigSpec.DoubleValue titanlordScepterSpellPower;
    public static ForgeConfigSpec.DoubleValue titanlordScepterCooldownReduction;
    public static ForgeConfigSpec.DoubleValue titanlordScepterManaRegen;
    public static ForgeConfigSpec.BooleanValue titanlordScepterGrantRecast;
    public static ForgeConfigSpec.DoubleValue wandOfFinalLightDamage;
    public static ForgeConfigSpec.DoubleValue wandOfFinalLightAttackSpeed;
    public static ForgeConfigSpec.DoubleValue wandOfFinalLightEnderSpellPower;
    public static ForgeConfigSpec.DoubleValue wandOfFinalLightCooldownReduction;
    public static ForgeConfigSpec.DoubleValue wandOfFinalLightCastTimeReduction;
    public static ForgeConfigSpec.DoubleValue abyssalSecretsMaxMana;
    public static ForgeConfigSpec.DoubleValue abyssalSecretsEnderSpellPower;
    public static ForgeConfigSpec.DoubleValue abyssalSecretsEldritchSpellPower;
    public static ForgeConfigSpec.DoubleValue chroniclesFirelordMaxMana;
    public static ForgeConfigSpec.DoubleValue chroniclesFirelordFireSpellPower;
    public static ForgeConfigSpec.DoubleValue chroniclesFirelordCooldownReduction;
    public static ForgeConfigSpec.DoubleValue shellboundMaxMana;
    public static ForgeConfigSpec.DoubleValue shellboundCooldownReduction;
    public static ForgeConfigSpec.DoubleValue shellboundNatureSpellPower;
    public static ForgeConfigSpec.DoubleValue theAccusedCodexMaxMana;
    public static ForgeConfigSpec.DoubleValue theAccusedCodexIceSpellPower;
    public static ForgeConfigSpec.DoubleValue theAccusedCodexCooldownReduction;
    public static ForgeConfigSpec.DoubleValue crushingDepthMaxMana;
    public static ForgeConfigSpec.DoubleValue crushingDepthAquaSpellPower;
    public static ForgeConfigSpec.DoubleValue crushingDepthCooldownReduction;
    public static ForgeConfigSpec.DoubleValue wateryWhispersMaxMana;
    public static ForgeConfigSpec.DoubleValue wateryWhispersAquaSpellPower;
    public static ForgeConfigSpec.DoubleValue wateryWhispersManaRegen;

    public static void loadConfig(ForgeConfigSpec config, String path) {
        CommentedFileConfig file = (CommentedFileConfig)CommentedFileConfig.builder((File)new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig((CommentedConfig)file);
    }

    static {
        ForgeConfigSpec.Builder SPELLS_SPEC_BUILDER = new ForgeConfigSpec.Builder();
        SPELLS_SPEC_BUILDER.comment("This config houses all the extra spell and spell mechanics").comment("If you're looking to change the spell stats instead or disable/enable spells... Go to iron's spellbooks server config file").push("spells");
        annihilationCauseExplode = SPELLS_SPEC_BUILDER.comment("If Annihilation spell can cause blocks to break/create explosion || Default: true").define("annihilation.cause_explosion", true);
        annihilationCauseFire = SPELLS_SPEC_BUILDER.comment("If Annihilation spell can cause fire || Automatically disabled if cause_explosion is disabled || Default: true").define("annihilation.cause_fire", true);
        aerialCollapseDamageReductionModifier = SPELLS_SPEC_BUILDER.comment("Should bosses take less percent-based damage from Aerial Collapse? || less than 1.0 will make them take less damage || Default 1.0").defineInRange("aerial_collapse.bosses_take_less_damage", 1.0, 0.1, 1.0);
        deathLaserBlockBreaking = SPELLS_SPEC_BUILDER.comment("Should DeathLaser break blocks || Default: true").define("death_laser.should_break_blocks", true);
        deathLaserDealMagicDamage = SPELLS_SPEC_BUILDER.comment("Should DeathLaser spell deal magic damage (default boss behavior/Ignore armor/less integration with spell power & resist attributes) || Default: false").define("death_laser.should_deal_magic_damage", false);
        abyssalBlastBlockBreaking = SPELLS_SPEC_BUILDER.comment("Should Abyssal Blast break blocks || Default: true").define("abyssal_blast.should_break_blocks", true);
        abyssalBlastDealMagicDamage = SPELLS_SPEC_BUILDER.comment("Should Abyssal Blast spell deal magic damage (default boss behavior/Ignore armor/less integration with spell power & resist attributes) || Default: false").define("abyssal_blast.should_deal_magic_damage", false);
        limitGroupSummons = SPELLS_SPEC_BUILDER.comment("Limit the number of active 'Group' summon spells to one at a time || Default: true").define("summon.limit_group_summon", true);
        limitKaijuSummons = SPELLS_SPEC_BUILDER.comment("Limit the number of active 'Kaiju' summon spells to one at a time || Default: true").define("summon.limit_kaiju_summon", true);
        limitMinibossSummons = SPELLS_SPEC_BUILDER.comment("Limit the number of active 'Miniboss' summon spells to one at a time || Default: true").define("summon.limit_miniboss_summon", true);
        SPELLS_SPEC_BUILDER.pop();
        SPELLS_SPEC_BUILDER.comment("Staff, Spellbooks and other this kind of thing").push("staff_spellbooks");
        titanlordScepterDamage = SPELLS_SPEC_BUILDER.comment("Damage || Default 9.0").defineInRange("titanlord_scepter.damage", 9.0, 0.0, 100.0);
        titanlordScepterAttackSpeed = SPELLS_SPEC_BUILDER.comment("Attack Speed || Default -3.0").defineInRange("titanlord_scepter.attack_speed", -3.0, -5.0, 5.0);
        titanlordScepterSpellPower = SPELLS_SPEC_BUILDER.comment("Variant Spell Power || MULTIPLY_BASE || Default 0.15").defineInRange("titanlord_scepter.variant_spell_power", 0.15, 0.0, 1.0);
        titanlordScepterCooldownReduction = SPELLS_SPEC_BUILDER.comment("Cooldown Reduction || MULTIPLY_BASE || Default 0.15").defineInRange("titanlord_scepter.cooldown_reduction", 0.15, 0.0, 1.0);
        titanlordScepterManaRegen = SPELLS_SPEC_BUILDER.comment("Mana Regen || MULTIPLY_BASE || Default 0.10").defineInRange("titanlord_scepter.mana_regen", 0.1, 0.0, 1.0);
        titanlordScepterGrantRecast = SPELLS_SPEC_BUILDER.comment("If Titanlord Scepter should grant a recast to Annihilation spell || Default: true").define("titanlord_scepter.grant_annihilation_recast", true);
        wandOfFinalLightDamage = SPELLS_SPEC_BUILDER.comment("Damage || Default 11.0").defineInRange("wand_of_final_light.wandOfFinalLightDamage", 11.0, 0.0, 100.0);
        wandOfFinalLightAttackSpeed = SPELLS_SPEC_BUILDER.comment("Attack Speed || Default -3.0").defineInRange("wand_of_final_light.wandOfFinalLightAttackSpeed", -3.0, -5.0, 5.0);
        wandOfFinalLightEnderSpellPower = SPELLS_SPEC_BUILDER.comment("Ender Spell Power || MULTIPLY_BASE || Default 0.2").defineInRange("wand_of_final_light.wandOfFinalLightEnderSpellPower", 0.2, 0.0, 1.0);
        wandOfFinalLightCooldownReduction = SPELLS_SPEC_BUILDER.comment("Cooldown Reduction || MULTIPLY_BASE || Default 0.2").defineInRange("wand_of_final_light.wandOfFinalLightCooldownReduction", 0.2, 0.0, 1.0);
        wandOfFinalLightCastTimeReduction = SPELLS_SPEC_BUILDER.comment("Cast Time Reduction || MULTIPLY_BASE || Default 0.15").defineInRange("wand_of_final_light.wandOfFinalLightCastTimeReduction", 0.15, 0.0, 1.0);
        abyssalSecretsMaxMana = SPELLS_SPEC_BUILDER.comment("Max Mana || ADDITION || Default 300.0").defineInRange("archive_of_abyssal_secrets.max_mana", 300.0, 50.0, 1000.0);
        abyssalSecretsEnderSpellPower = SPELLS_SPEC_BUILDER.comment("Ender Spell Power || MULTIPLY_BASE || Default 0.15").defineInRange("archive_of_abyssal_secrets.ender_spell_power", 0.15, 0.0, 1.0);
        abyssalSecretsEldritchSpellPower = SPELLS_SPEC_BUILDER.comment("Eldritch Spell Power || MULTIPLY_BASE || Default 0.15").defineInRange("archive_of_abyssal_secrets.eldritch_spell_power", 0.15, 0.0, 1.0);
        chroniclesFirelordMaxMana = SPELLS_SPEC_BUILDER.comment("Max Mana || ADDITION || Default 300.0").defineInRange("chronicles_of_the_firelord.chroniclesFirelordMaxMana", 300.0, 50.0, 1000.0);
        chroniclesFirelordFireSpellPower = SPELLS_SPEC_BUILDER.comment("Fire Spell Power || MULTIPLY_BASE || Default 0.20").defineInRange("chronicles_of_the_firelord.chroniclesFirelordFireSpellPower", 0.2, 0.0, 1.0);
        chroniclesFirelordCooldownReduction = SPELLS_SPEC_BUILDER.comment("Spell Eldritch Spell Power || MULTIPLY_BASE || Default 0.10").defineInRange("chronicles_of_the_firelord.chroniclesFirelordCooldownReduction", 0.1, 0.0, 1.0);
        shellboundMaxMana = SPELLS_SPEC_BUILDER.comment("Max Mana || ADDITION || Default 200.0").defineInRange("shellbound.max_mana", 200.0, 50.0, 1000.0);
        shellboundCooldownReduction = SPELLS_SPEC_BUILDER.comment("Cooldown Reduction || MULTIPLY_BASE || Default 0.10").defineInRange("shellbound.cooldown_reduction", 0.1, 0.0, 1.0);
        shellboundNatureSpellPower = SPELLS_SPEC_BUILDER.comment("Nature Spell Power || MULTIPLY_BASE || Default 0.10").defineInRange("shellbound.nature_spell_power", 0.1, 0.0, 1.0);
        theAccusedCodexMaxMana = SPELLS_SPEC_BUILDER.comment("Max Mana || ADDITION || Default 300.0").defineInRange("the_accused_codex.max_mana", 300.0, 50.0, 1000.0);
        theAccusedCodexCooldownReduction = SPELLS_SPEC_BUILDER.comment("Cooldown Reduction || MULTIPLY_BASE || Default 0.10").defineInRange("the_accused_codex.cooldown_reduction", 0.1, 0.0, 1.0);
        theAccusedCodexIceSpellPower = SPELLS_SPEC_BUILDER.comment("Ice Spell Power || MULTIPLY_BASE || Default 0.20").defineInRange("the_accused_codex.ice_spell_power", 0.2, 0.0, 1.0);
        crushingDepthMaxMana = SPELLS_SPEC_BUILDER.comment("Max Mana || ADDITION || Default 300.0").defineInRange("codec_of_crushing_depths.max_mana", 300.0, 50.0, 1000.0);
        crushingDepthCooldownReduction = SPELLS_SPEC_BUILDER.comment("Cooldown Reduction || MULTIPLY_BASE || Default 0.10").defineInRange("codec_of_crushing_depths.crushingDepthCooldownReduction", 0.1, 0.0, 1.0);
        crushingDepthAquaSpellPower = SPELLS_SPEC_BUILDER.comment("Aqua Spell Power || MULTIPLY_BASE || Default 0.20").defineInRange("codec_of_crushing_depths.crushingDepthAquaSpellPower", 0.2, 0.0, 1.0);
        wateryWhispersMaxMana = SPELLS_SPEC_BUILDER.comment("Max Mana || ADDITION || Default 200.0").defineInRange("guide_to_watery_whispers.max_mana", 200.0, 50.0, 1000.0);
        wateryWhispersManaRegen = SPELLS_SPEC_BUILDER.comment("Mana Regen || MULTIPLY_BASE || Default 0.15").defineInRange("guide_to_watery_whispers.wateryWhispersManaRegen", 0.15, 0.0, 1.0);
        wateryWhispersAquaSpellPower = SPELLS_SPEC_BUILDER.comment("Aqua Spell Power || MULTIPLY_BASE || Default 0.10").defineInRange("guide_to_watery_whispers.aqua_spell_power", 0.1, 0.0, 1.0);
        SPELLS_SPEC_BUILDER.pop();
        SPELLS_SPEC = SPELLS_SPEC_BUILDER.build();
    }
}

