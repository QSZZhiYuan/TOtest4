/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.electronwill.nightconfig.core.CommentedConfig
 *  com.electronwill.nightconfig.core.file.CommentedFileConfig
 *  com.electronwill.nightconfig.core.io.WritingMode
 *  net.minecraftforge.common.ForgeConfigSpec
 *  net.minecraftforge.common.ForgeConfigSpec$Builder
 *  net.minecraftforge.common.ForgeConfigSpec$DoubleValue
 */
package com.gametechbc.traveloptics.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import java.io.File;
import net.minecraftforge.common.ForgeConfigSpec;

public class EntityConfig {
    public static final ForgeConfigSpec ENTITY_SPEC;
    public static ForgeConfigSpec.DoubleValue nightwardenDynamicDamageCap;
    public static ForgeConfigSpec.DoubleValue cloneSlashesMultiplierFromBase;
    public static ForgeConfigSpec.DoubleValue cloneSlashesHpDamage;
    public static ForgeConfigSpec.DoubleValue dimensionalSpikeDamageMultiplierFromBase;
    public static ForgeConfigSpec.DoubleValue spinCloneDamageMultiplierFromBase;
    public static ForgeConfigSpec.DoubleValue spinClonesHpDamage;
    public static ForgeConfigSpec.DoubleValue dropCloneDamageMultiplierFromBase;
    public static ForgeConfigSpec.DoubleValue dropClonesHpDamage;
    public static ForgeConfigSpec.DoubleValue endEruptionDamageMultiplierFromBase;
    public static ForgeConfigSpec.DoubleValue explodeSpinCloneTrailDamageMultiplierFromBase;
    public static ForgeConfigSpec.DoubleValue explodeSpinCloneTrailHpDamage;
    public static ForgeConfigSpec.DoubleValue dragonSpiritHpDamage;

    public static void loadConfig(ForgeConfigSpec config, String path) {
        CommentedFileConfig file = (CommentedFileConfig)CommentedFileConfig.builder((File)new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig((CommentedConfig)file);
    }

    static {
        ForgeConfigSpec.Builder ENTITY_SPEC_BUILDER = new ForgeConfigSpec.Builder();
        ENTITY_SPEC_BUILDER.comment("This config houses entity related extra config").comment("If you want to change attributes like attack or hp. Use In-Control mod").push("entity");
        nightwardenDynamicDamageCap = ENTITY_SPEC_BUILDER.comment("Dynamic damage cap auto calculate required values in all phases, the value here is the highest || Default 40.0").defineInRange("nightwarden.nightwardenDynamicDamageCap", 40.0, 10.0, 100.0);
        cloneSlashesMultiplierFromBase = ENTITY_SPEC_BUILDER.comment("Default 0.5556").defineInRange("nightwarden.cloneSlashesMultiplierFromBase", 0.5556, 0.1, 3.0);
        cloneSlashesHpDamage = ENTITY_SPEC_BUILDER.comment("Default 0.05").defineInRange("nightwarden.cloneSlashesHpDamage", 0.05, 0.0, 3.0);
        dimensionalSpikeDamageMultiplierFromBase = ENTITY_SPEC_BUILDER.comment("Default 0.6667").defineInRange("nightwarden.dimensionalSpikeDamageMultiplierFromBase", 0.6667, 0.1, 3.0);
        spinCloneDamageMultiplierFromBase = ENTITY_SPEC_BUILDER.comment("Default 0.4444").defineInRange("nightwarden.spinCloneDamageMultiplierFromBase", 0.4444, 0.1, 3.0);
        spinClonesHpDamage = ENTITY_SPEC_BUILDER.comment("Default 0.03").defineInRange("nightwarden.spinClonesHpDamage", 0.03, 0.0, 3.0);
        dropCloneDamageMultiplierFromBase = ENTITY_SPEC_BUILDER.comment("Default 0.4444").defineInRange("nightwarden.dropClonesDamageMultiplierFromBase", 0.4444, 0.1, 3.0);
        dropClonesHpDamage = ENTITY_SPEC_BUILDER.comment("Default 0.05").defineInRange("nightwarden.dropClonesHpDamage", 0.05, 0.0, 3.0);
        endEruptionDamageMultiplierFromBase = ENTITY_SPEC_BUILDER.comment("Default 0.2778").defineInRange("nightwarden.endEruptionDamageMultiplierFromBase", 0.2778, 0.1, 3.0);
        explodeSpinCloneTrailDamageMultiplierFromBase = ENTITY_SPEC_BUILDER.comment("Default 0.3611").defineInRange("nightwarden.explodeSpinCloneTrailDamageMultiplierFromBase", 0.3611, 0.1, 3.0);
        explodeSpinCloneTrailHpDamage = ENTITY_SPEC_BUILDER.comment("Default 0.05").defineInRange("nightwarden.explodeSpinCloneTrailHpDamage", 0.05, 0.0, 3.0);
        dragonSpiritHpDamage = ENTITY_SPEC_BUILDER.comment("Default 0.1").defineInRange("nightwarden.dragonSpiritHpDamage", 0.1, 0.0, 3.0);
        ENTITY_SPEC_BUILDER.pop();
        ENTITY_SPEC = ENTITY_SPEC_BUILDER.build();
    }
}

