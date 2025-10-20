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

public class DependantItemsAttributeConfig {
    public static final ForgeConfigSpec DEPENDANT_CONFIG_SPEC;
    public static ForgeConfigSpec.BooleanValue attributeProviderSystemActive;
    public static ForgeConfigSpec.DoubleValue ignitiumSetFireSpellPower;
    public static ForgeConfigSpec.DoubleValue ignitiumSetMaxMana;

    public static void loadConfig(ForgeConfigSpec config, String path) {
        CommentedFileConfig file = (CommentedFileConfig)CommentedFileConfig.builder((File)new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig((CommentedConfig)file);
    }

    static {
        ForgeConfigSpec.Builder DEPENDANT_CONFIG_SPEC_BUILDER = new ForgeConfigSpec.Builder();
        DEPENDANT_CONFIG_SPEC_BUILDER.comment("W.I.P").comment("This file is for the magic attributes that T.O Tweaks provided to its dependency mods' equipments!").comment("If you're already overriding the same attributes with mods like KubeJS or CIA (Custom Item Attributes), things might not work as intended.").comment("Make sure to disable one either KubeJS & CIA or T.O Tweaks default attribute provider").push("attribute_provider");
        attributeProviderSystemActive = DEPENDANT_CONFIG_SPEC_BUILDER.comment("If Attribute Provider system should be enabled or not || Disabling this will entirely disable Attribute Provider to items below || Default: false").define("general.attribute_provider_active", false);
        ignitiumSetFireSpellPower = DEPENDANT_CONFIG_SPEC_BUILDER.comment("Ignitium items Fire Spell Power || Default 0.05").defineInRange("ignitium_set.fire_spell_power", 0.05, 0.0, 1.0);
        ignitiumSetMaxMana = DEPENDANT_CONFIG_SPEC_BUILDER.comment("Ignitium items Max Mana || Default 75").defineInRange("ignitium_set.max_mana", 75.0, 0.0, 1000.0);
        DEPENDANT_CONFIG_SPEC_BUILDER.pop();
        DEPENDANT_CONFIG_SPEC = DEPENDANT_CONFIG_SPEC_BUILDER.build();
    }
}

