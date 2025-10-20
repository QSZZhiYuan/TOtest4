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
 *  net.minecraftforge.common.ForgeConfigSpec$IntValue
 */
package com.gametechbc.traveloptics.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import java.io.File;
import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static ForgeConfigSpec.BooleanValue activateScreenShake;
    public static ForgeConfigSpec.BooleanValue activateScreenFlash;
    public static ForgeConfigSpec.BooleanValue armorReplaceXPBars;
    public static ForgeConfigSpec.IntValue plasmaFuelXOffset;
    public static ForgeConfigSpec.IntValue plasmaFuelYOffset;
    public static ForgeConfigSpec.IntValue cursedWraithguardXOffset;
    public static ForgeConfigSpec.IntValue cursedWraithguardYOffset;
    public static ForgeConfigSpec.IntValue abyssalHideXOffset;
    public static ForgeConfigSpec.IntValue abyssalHideYOffset;
    public static ForgeConfigSpec.IntValue primordialCrestXOffset;
    public static ForgeConfigSpec.IntValue primordialCrestYOffset;
    public static ForgeConfigSpec.IntValue tectonicCrestXOffset;
    public static ForgeConfigSpec.IntValue tectonicCrestYOffset;
    public static ForgeConfigSpec.IntValue darknessXOffset;
    public static ForgeConfigSpec.IntValue darknessYOffset;

    public static void loadConfig(ForgeConfigSpec config, String path) {
        CommentedFileConfig file = (CommentedFileConfig)CommentedFileConfig.builder((File)new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig((CommentedConfig)file);
    }

    static {
        ForgeConfigSpec.Builder CLIENT_SPEC_BUILDER = new ForgeConfigSpec.Builder();
        CLIENT_SPEC_BUILDER.push("client");
        activateScreenShake = CLIENT_SPEC_BUILDER.comment("Activates this mod's screen shake implementation || Default: true").comment("NOTE: This mod also uses screen shake from other dependencies and I haven't ported all of them to use my new method. So disabling this only disable the screen shake happens cause of this mod. Other mod screen shake that T.O uses will still be active.").define("general.activateScreenShake", true);
        activateScreenFlash = CLIENT_SPEC_BUILDER.comment("Activates this mod's screen flashing implementation || Default: true").comment("NOTE: This mod also uses screen flash from other dependencies. So disabling this only disable the screen flash happens cause of this mod. Other mod screen flash that T.O uses will still be active, such as Annihilation flash effect.").define("general.activateScreenFlash", true);
        armorReplaceXPBars = CLIENT_SPEC_BUILDER.comment("Whether the armor info overlay should replace XP bars || Default: false").define("general.armor_overlay_replace_xp_bars", false);
        plasmaFuelXOffset = CLIENT_SPEC_BUILDER.comment("X Offset || Default 0").defineInRange("plasma_fuel.x_offset", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        plasmaFuelYOffset = CLIENT_SPEC_BUILDER.comment("Y Offset || Default 0").defineInRange("plasma_fuel.y_offset", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        cursedWraithguardXOffset = CLIENT_SPEC_BUILDER.comment("X Offset || Default 0").defineInRange("cursed_wraithguard.x_offset", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        cursedWraithguardYOffset = CLIENT_SPEC_BUILDER.comment("Y Offset || Default 0").defineInRange("cursed_wraithguard.y_offset", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        abyssalHideXOffset = CLIENT_SPEC_BUILDER.comment("X Offset || Default 0").defineInRange("abyssal_hide.x_offset", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        abyssalHideYOffset = CLIENT_SPEC_BUILDER.comment("Y Offset || Default 0").defineInRange("abyssal_hide.y_offset", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        tectonicCrestXOffset = CLIENT_SPEC_BUILDER.comment("X Offset || Default 0").defineInRange("tectonic_crest.x_offset", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        tectonicCrestYOffset = CLIENT_SPEC_BUILDER.comment("Y Offset || Default 0").defineInRange("tectonic_crest.y_offset", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        primordialCrestXOffset = CLIENT_SPEC_BUILDER.comment("X Offset || Default 0").defineInRange("primordial_crest.x_offset", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        primordialCrestYOffset = CLIENT_SPEC_BUILDER.comment("Y Offset || Default 0").defineInRange("primordial_crest.y_offset", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        darknessXOffset = CLIENT_SPEC_BUILDER.comment("X Offset || Default 0").defineInRange("darkness.x_offset", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        darknessYOffset = CLIENT_SPEC_BUILDER.comment("Y Offset || Default 0").defineInRange("darkness.y_offset", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        CLIENT_SPEC_BUILDER.pop();
        CLIENT_SPEC = CLIENT_SPEC_BUILDER.build();
    }
}

