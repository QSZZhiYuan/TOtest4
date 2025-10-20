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

public class ArmorConfig {
    public static final ForgeConfigSpec ARMOR_SPEC;
    public static ForgeConfigSpec.BooleanValue armorsShouldBeBreakable;
    public static ForgeConfigSpec.IntValue abyssalHideHelmetArmorValue;
    public static ForgeConfigSpec.IntValue abyssalHideChestplateArmorValue;
    public static ForgeConfigSpec.IntValue abyssalHideLeggingsArmorValue;
    public static ForgeConfigSpec.IntValue abyssalHideBootsArmorValue;
    public static ForgeConfigSpec.DoubleValue abyssalHideArmorToughness;
    public static ForgeConfigSpec.IntValue primordialCrestHelmetArmorValue;
    public static ForgeConfigSpec.IntValue primordialCrestChestplateArmorValue;
    public static ForgeConfigSpec.IntValue primordialCrestLeggingsArmorValue;
    public static ForgeConfigSpec.IntValue primordialCrestBootsArmorValue;
    public static ForgeConfigSpec.DoubleValue primordialCrestArmorToughness;
    public static ForgeConfigSpec.IntValue tectonicCrestHelmetArmorValue;
    public static ForgeConfigSpec.IntValue tectonicCrestChestplateArmorValue;
    public static ForgeConfigSpec.IntValue tectonicCrestLeggingsArmorValue;
    public static ForgeConfigSpec.IntValue tectonicCrestBootsArmorValue;
    public static ForgeConfigSpec.DoubleValue tectonicCrestArmorToughness;
    public static ForgeConfigSpec.IntValue cursedWraithguardHelmetArmorValue;
    public static ForgeConfigSpec.IntValue cursedWraithguardChestplateArmorValue;
    public static ForgeConfigSpec.IntValue cursedWraithguardLeggingsArmorValue;
    public static ForgeConfigSpec.IntValue cursedWraithguardBootsArmorValue;
    public static ForgeConfigSpec.DoubleValue cursedWraithguardArmorToughness;
    public static ForgeConfigSpec.IntValue mechanizedExoskeletonHelmetArmorValue;
    public static ForgeConfigSpec.IntValue mechanizedExoskeletonChestplateArmorValue;
    public static ForgeConfigSpec.IntValue mechanizedExoskeletonLeggingsArmorValue;
    public static ForgeConfigSpec.IntValue mechanizedExoskeletonBootsArmorValue;
    public static ForgeConfigSpec.DoubleValue mechanizedExoskeletonArmorToughness;
    public static ForgeConfigSpec.IntValue deeplingMageHelmetArmorValue;
    public static ForgeConfigSpec.IntValue deeplingMageChestplateArmorValue;
    public static ForgeConfigSpec.IntValue deeplingMageLeggingsArmorValue;
    public static ForgeConfigSpec.IntValue deeplingMageBootsArmorValue;
    public static ForgeConfigSpec.DoubleValue deeplingMageArmorToughness;
    public static ForgeConfigSpec.IntValue forlornHarbingerHelmetArmorValue;
    public static ForgeConfigSpec.IntValue forlornHarbingerChestplateArmorValue;
    public static ForgeConfigSpec.IntValue forlornHarbingerLeggingsArmorValue;
    public static ForgeConfigSpec.IntValue forlornHarbingerBootsArmorValue;
    public static ForgeConfigSpec.DoubleValue forlornHarbingerArmorToughness;

    public static void loadConfig(ForgeConfigSpec config, String path) {
        CommentedFileConfig file = (CommentedFileConfig)CommentedFileConfig.builder((File)new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig((CommentedConfig)file);
    }

    static {
        ForgeConfigSpec.Builder ARMOR_SPEC_BUILDER = new ForgeConfigSpec.Builder();
        ARMOR_SPEC_BUILDER.comment("This config houses all the armor configuration options || This is currently WIP and doesn't have all the values").push("armors");
        armorsShouldBeBreakable = ARMOR_SPEC_BUILDER.comment("Should End-game armors be breakable || Default: false").define("general.breakable_end_game_armors", false);
        abyssalHideHelmetArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Abyssal Hide armor helmet || Default 6").defineInRange("abyssal_hide.helmet_armor_value", 6, 1, Integer.MAX_VALUE);
        abyssalHideChestplateArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Abyssal Hide armor chestplate || Default 11").defineInRange("abyssal_hide.chestplate_armor_value", 11, 1, Integer.MAX_VALUE);
        abyssalHideLeggingsArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Abyssal Hide armor leggings || Default 9").defineInRange("abyssal_hide.leggings_armor_value", 9, 1, Integer.MAX_VALUE);
        abyssalHideBootsArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Abyssal Hide armor boots || Default 6").defineInRange("abyssal_hide.boots_armor_value", 6, 1, Integer.MAX_VALUE);
        abyssalHideArmorToughness = ARMOR_SPEC_BUILDER.comment("Toughness || Default 4.0").defineInRange("abyssal_hide.armor_toughness", 4.0, 0.0, 20.0);
        primordialCrestHelmetArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Primordial Crest armor helmet || Default 4").defineInRange("primordial_crest.helmet_armor_value", 4, 1, Integer.MAX_VALUE);
        primordialCrestChestplateArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Primordial Crest armor chestplate || Default 8").defineInRange("primordial_crest.chestplate_armor_value", 8, 1, Integer.MAX_VALUE);
        primordialCrestLeggingsArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Primordial Crest armor leggings || Default 6").defineInRange("primordial_crest.leggings_armor_value", 6, 1, Integer.MAX_VALUE);
        primordialCrestBootsArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Primordial Crest armor boots || Default 4").defineInRange("primordial_crest.boots_armor_value", 4, 1, Integer.MAX_VALUE);
        primordialCrestArmorToughness = ARMOR_SPEC_BUILDER.comment("Toughness || Default 1.5").defineInRange("primordial_crest.armor_toughness", 1.5, 0.0, 20.0);
        tectonicCrestHelmetArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Tectonic Crest armor helmet || Default 6").defineInRange("tectonic_crest.helmet_armor_value", 6, 1, Integer.MAX_VALUE);
        tectonicCrestChestplateArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Tectonic Crest armor chestplate || Default 11").defineInRange("tectonic_crest.chestplate_armor_value", 11, 1, Integer.MAX_VALUE);
        tectonicCrestLeggingsArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Tectonic Crest armor leggings || Default 9").defineInRange("tectonic_crest.leggings_armor_value", 9, 1, Integer.MAX_VALUE);
        tectonicCrestBootsArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Tectonic Crest armor boots || Default 6").defineInRange("tectonic_crest.boots_armor_value", 6, 1, Integer.MAX_VALUE);
        tectonicCrestArmorToughness = ARMOR_SPEC_BUILDER.comment("Toughness || Default 4.0").defineInRange("tectonic_crest.armor_toughness", 4.0, 0.0, 20.0);
        cursedWraithguardHelmetArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Cursed Wraithguard armor helmet || Default 6").defineInRange("cursed_wraithguard.helmet_armor_value", 6, 1, Integer.MAX_VALUE);
        cursedWraithguardChestplateArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Cursed Wraithguard armor chestplate || Default 11").defineInRange("cursed_wraithguard.chestplate_armor_value", 11, 1, Integer.MAX_VALUE);
        cursedWraithguardLeggingsArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Cursed Wraithguard armor leggings || Default 9").defineInRange("cursed_wraithguard.leggings_armor_value", 9, 1, Integer.MAX_VALUE);
        cursedWraithguardBootsArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Cursed Wraithguard armor boots || Default 6").defineInRange("cursed_wraithguard.boots_armor_value", 6, 1, Integer.MAX_VALUE);
        cursedWraithguardArmorToughness = ARMOR_SPEC_BUILDER.comment("Toughness || Default 4.0").defineInRange("cursed_wraithguard.armor_toughness", 4.0, 0.0, 20.0);
        mechanizedExoskeletonHelmetArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Mechanized Exoskeleton armor helmet || Default 6").defineInRange("mechanized_exoskeleton.helmet_armor_value", 6, 1, Integer.MAX_VALUE);
        mechanizedExoskeletonChestplateArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Mechanized Exoskeleton armor chestplate || Default 11").defineInRange("mechanized_exoskeleton.chestplate_armor_value", 11, 1, Integer.MAX_VALUE);
        mechanizedExoskeletonLeggingsArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Mechanized Exoskeleton armor leggings || Default 9").defineInRange("mechanized_exoskeleton.leggings_armor_value", 9, 1, Integer.MAX_VALUE);
        mechanizedExoskeletonBootsArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Mechanized Exoskeleton armor boots || Default 6").defineInRange("mechanized_exoskeleton.boots_armor_value", 6, 1, Integer.MAX_VALUE);
        mechanizedExoskeletonArmorToughness = ARMOR_SPEC_BUILDER.comment("Toughness || Default 4.0").defineInRange("mechanized_exoskeleton.armor_toughness", 4.0, 0.0, 20.0);
        deeplingMageHelmetArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Deepling Mage armor helmet || Default 3").defineInRange("deepling_mage.helmet_armor_value", 3, 1, Integer.MAX_VALUE);
        deeplingMageChestplateArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Deepling Mage armor chestplate || Default 8").defineInRange("deepling_mage.chestplate_armor_value", 8, 1, Integer.MAX_VALUE);
        deeplingMageLeggingsArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Deepling Mage armor leggings || Default 6").defineInRange("deepling_mage.leggings_armor_value", 6, 1, Integer.MAX_VALUE);
        deeplingMageBootsArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of Deepling Mage armor boots || Default 3").defineInRange("deepling_mage.boots_armor_value", 3, 1, Integer.MAX_VALUE);
        deeplingMageArmorToughness = ARMOR_SPEC_BUILDER.comment("Toughness || Default 1.5").defineInRange("deepling_mage.armor_toughness", 1.5, 0.0, 20.0);
        forlornHarbingerHelmetArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of helmet || Default 6").defineInRange("forlorn_harbinger.helmet_armor_value", 6, 1, Integer.MAX_VALUE);
        forlornHarbingerChestplateArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of chestplate || Default 11").defineInRange("forlorn_harbinger.chestplate_armor_value", 11, 1, Integer.MAX_VALUE);
        forlornHarbingerLeggingsArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of leggings || Default 9").defineInRange("forlorn_harbinger.leggings_armor_value", 9, 1, Integer.MAX_VALUE);
        forlornHarbingerBootsArmorValue = ARMOR_SPEC_BUILDER.comment("Armor value of boots || Default 6").defineInRange("forlorn_harbinger.boots_armor_value", 6, 1, Integer.MAX_VALUE);
        forlornHarbingerArmorToughness = ARMOR_SPEC_BUILDER.comment("Toughness || Default 4.0").defineInRange("forlorn_harbinger.armor_toughness", 4.0, 0.0, 20.0);
        ARMOR_SPEC_BUILDER.pop();
        ARMOR_SPEC = ARMOR_SPEC_BUILDER.build();
    }
}

