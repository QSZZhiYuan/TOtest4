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
 *  net.minecraftforge.common.ForgeConfigSpec$ConfigValue
 */
package com.gametechbc.traveloptics.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import java.io.File;
import java.util.Collections;
import java.util.List;
import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public static final ForgeConfigSpec COMMON_SPEC;
    public static ForgeConfigSpec.BooleanValue showWelcomeMessage;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> rainfallImmuneEffects;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> acidRainImmuneEffects;

    public static void loadConfig(ForgeConfigSpec config, String path) {
        CommentedFileConfig file = (CommentedFileConfig)CommentedFileConfig.builder((File)new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig((CommentedConfig)file);
    }

    static {
        ForgeConfigSpec.Builder COMMON_SPEC_BUILDER = new ForgeConfigSpec.Builder();
        COMMON_SPEC_BUILDER.comment("Common Configs || This config file offer controls to many different things on the mod.").comment("Changing something you don't know about, might break your game.").push("common");
        showWelcomeMessage = COMMON_SPEC_BUILDER.comment("Show welcome message || Default: true").define("general.welcome_message", true);
        rainfallImmuneEffects = COMMON_SPEC_BUILDER.comment("List of effect IDs that are immune to Rainfall cleanse || Example: [\"minecraft:blindness\", \"minecraft:darkness\"]").defineList("effect.rainfall_immune_effects", Collections.emptyList(), obj -> obj instanceof String && !((String)obj).isEmpty());
        acidRainImmuneEffects = COMMON_SPEC_BUILDER.comment("List of effect IDs that are immune to Acid Rain cleanse || Example: [\"minecraft:absorption\", \"minecraft:health_boost\"]").defineList("effect.acid_rain_immune_effects", Collections.emptyList(), obj -> obj instanceof String && !((String)obj).isEmpty());
        COMMON_SPEC_BUILDER.pop();
        COMMON_SPEC = COMMON_SPEC_BUILDER.build();
    }
}

