/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.particle.FogParticleOptions
 *  io.redspace.ironsspellbooks.particle.SparkParticleOptions
 *  net.minecraft.core.particles.ParticleOptions
 *  org.joml.Vector3f
 */
package com.gametechbc.traveloptics.util;

import com.gametechbc.traveloptics.api.utils.TOGeneralUtils;
import com.gametechbc.traveloptics.init.TravelopticsParticles;
import com.gametechbc.traveloptics.particle.glowing_enchantment.GlowingEnchantmentParticleOptions;
import io.redspace.ironsspellbooks.particle.FogParticleOptions;
import io.redspace.ironsspellbooks.particle.SparkParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import org.joml.Vector3f;

public class TravelopticsParticleHelper {
    public static final ParticleOptions CURSED_BLAST = (ParticleOptions)TravelopticsParticles.CURSED_BLAST.get();
    public static final ParticleOptions ABYSS_SPIKE_PARTICLE = (ParticleOptions)TravelopticsParticles.ABYSS_SPIKE_PARTICLE.get();
    public static final ParticleOptions PURPLE_STAR_OUTWARD_PARTICLE = (ParticleOptions)TravelopticsParticles.PURPLE_STAR_OUTWARD_PARTICLE.get();
    public static final ParticleOptions PURPLE_STAR_INWARD_PARTICLE = (ParticleOptions)TravelopticsParticles.PURPLE_STAR_INWARD_PARTICLE.get();
    public static final ParticleOptions WATER_FOG = new FogParticleOptions(new Vector3f(0.2f, 0.592f, 0.82f), 2.0f);
    public static final ParticleOptions SCYLLA_FOG = new FogParticleOptions(TOGeneralUtils.hexToVector3f(5745885), 2.0f);
    public static final ParticleOptions WATER_SPARKS = new SparkParticleOptions(new Vector3f(0.0f, 0.259f, 0.416f));
    public static final ParticleOptions WATER = (ParticleOptions)TravelopticsParticles.WATER_PARTICLE.get();
    public static final ParticleOptions WATER_BUBBLE = (ParticleOptions)TravelopticsParticles.WATER_BUBBLE_PARTICLE.get();
    public static final ParticleOptions WATER_DROP = (ParticleOptions)TravelopticsParticles.WATER_DROP_PARTICLE.get();
    public static final ParticleOptions ACID_DROP = (ParticleOptions)TravelopticsParticles.ACID_DROP_PARTICLE.get();
    public static final ParticleOptions BLUE_CORAL_BUBBLE = (ParticleOptions)TravelopticsParticles.BLUE_CORAL_BUBBLE_PARTICLE.get();
    public static final ParticleOptions PINK_CORAL_BUBBLE = (ParticleOptions)TravelopticsParticles.PINK_CORAL_BUBBLE_PARTICLE.get();
    public static final ParticleOptions RED_CORAL_BUBBLE = (ParticleOptions)TravelopticsParticles.RED_CORAL_BUBBLE_PARTICLE.get();
    public static final ParticleOptions YELLOW_CORAL_BUBBLE = (ParticleOptions)TravelopticsParticles.YELLOW_CORAL_BUBBLE_PARTICLE.get();
    public static final ParticleOptions BLUE_CORAL_SPARKS = new SparkParticleOptions(new Vector3f(0.2471f, 0.4235f, 0.898f));
    public static final ParticleOptions PINK_CORAL_SPARKS = new SparkParticleOptions(new Vector3f(0.8392f, 0.3294f, 0.6078f));
    public static final ParticleOptions RED_CORAL_SPARKS = new SparkParticleOptions(new Vector3f(0.6431f, 0.1333f, 0.1843f));
    public static final ParticleOptions YELLOW_CORAL_SPARKS = new SparkParticleOptions(new Vector3f(0.9294f, 0.9255f, 0.298f));
    public static final ParticleOptions PSYCHIC_SPARKS = new SparkParticleOptions(new Vector3f(0.1608f, 0.8745f, 0.9216f));
    public static final ParticleOptions LIGHT_PURPLE_GLOWING_ENCHANT = new GlowingEnchantmentParticleOptions(new Vector3f(0.631373f, 0.32549f, 0.996078f), 0.06f, false, 30);
    public static final ParticleOptions LIGHT_RED_GLOWING_ENCHANT = new GlowingEnchantmentParticleOptions(new Vector3f(0.996f, 0.325f, 0.325f), 0.06f, false, 30);
    public static final ParticleOptions LIGHT_GOLD_GLOWING_ENCHANT = new GlowingEnchantmentParticleOptions(new Vector3f(0.996f, 0.843f, 0.325f), 0.06f, false, 30);
    public static final ParticleOptions SHORT_LIGHT_PURPLE_GLOWING_ENCHANT = new GlowingEnchantmentParticleOptions(new Vector3f(0.631373f, 0.32549f, 0.996078f), 0.06f, false, 10);
    public static final ParticleOptions SHORT_LIGHT_RED_GLOWING_ENCHANT = new GlowingEnchantmentParticleOptions(new Vector3f(0.996f, 0.325f, 0.325f), 0.06f, false, 10);
    public static final ParticleOptions SHORT_LIGHT_GOLD_GLOWING_ENCHANT = new GlowingEnchantmentParticleOptions(new Vector3f(0.996f, 0.843f, 0.325f), 0.06f, false, 10);
    public static final ParticleOptions VERY_SHORT_LIGHT_PURPLE_GLOWING_ENCHANT = new GlowingEnchantmentParticleOptions(new Vector3f(0.631373f, 0.32549f, 0.996078f), 0.06f, false, 4);
    public static final ParticleOptions VERY_SHORT_LIGHT_GOLD_GLOWING_ENCHANT = new GlowingEnchantmentParticleOptions(new Vector3f(0.996f, 0.843f, 0.325f), 0.06f, false, 4);
    public static final ParticleOptions VERY_SHORT_LIGHT_RED_GLOWING_ENCHANT = new GlowingEnchantmentParticleOptions(new Vector3f(0.996f, 0.325f, 0.325f), 0.06f, false, 4);
    public static final ParticleOptions WARNING_PATH_RED_GLOWING_ENCHANT = new GlowingEnchantmentParticleOptions(new Vector3f(0.996f, 0.325f, 0.325f), 0.085f, false, 10);
    public static final ParticleOptions DRAGON_SPIRIT_LIGHT_PURPLE_GLOWING_ENCHANT = new GlowingEnchantmentParticleOptions(new Vector3f(0.631373f, 0.32549f, 0.996078f), 0.1f, false, 30);
}

