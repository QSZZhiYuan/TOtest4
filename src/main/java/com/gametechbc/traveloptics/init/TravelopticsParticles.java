/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  net.minecraft.core.particles.ParticleType
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraftforge.eventbus.api.IEventBus
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package com.gametechbc.traveloptics.init;

import com.gametechbc.traveloptics.particle.colorful_bubble.ColorfulBubbleParticleOptions;
import com.gametechbc.traveloptics.particle.glowing_enchantment.GlowingEnchantmentParticleOptions;
import com.gametechbc.traveloptics.particle.reverse_blastwave.ReverseBlastwaveParticleOptions;
import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class TravelopticsParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create((IForgeRegistry)ForgeRegistries.PARTICLE_TYPES, (String)"traveloptics");
    public static final RegistryObject<SimpleParticleType> CURSED_BLAST = PARTICLE_TYPES.register("cursed_blast", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> ABYSS_SPIKE_PARTICLE = PARTICLE_TYPES.register("abyss_spike", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> WATER_PARTICLE = PARTICLE_TYPES.register("water", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> WATER_BUBBLE_PARTICLE = PARTICLE_TYPES.register("water_bubble", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> WATER_DROP_PARTICLE = PARTICLE_TYPES.register("water_drop", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> ACID_DROP_PARTICLE = PARTICLE_TYPES.register("acid_drop", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BLUE_CORAL_BUBBLE_PARTICLE = PARTICLE_TYPES.register("blue_coral_bubble", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> PINK_CORAL_BUBBLE_PARTICLE = PARTICLE_TYPES.register("pink_coral_bubble", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> RED_CORAL_BUBBLE_PARTICLE = PARTICLE_TYPES.register("red_coral_bubble", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> YELLOW_CORAL_BUBBLE_PARTICLE = PARTICLE_TYPES.register("yellow_coral_bubble", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> GLOWING_REVERSE_PORTAL_PARTICLE = PARTICLE_TYPES.register("glowing_reverse_particle", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> PURPLE_STAR_INWARD_PARTICLE = PARTICLE_TYPES.register("purple_star_inward", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> PURPLE_STAR_OUTWARD_PARTICLE = PARTICLE_TYPES.register("purple_star_outward", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> RED_STAR_INWARD_PARTICLE = PARTICLE_TYPES.register("red_star_inward", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> RED_STAR_OUTWARD_PARTICLE = PARTICLE_TYPES.register("red_star_outward", () -> new SimpleParticleType(false));
    public static final RegistryObject<ParticleType<GlowingEnchantmentParticleOptions>> GLOWING_ENCHANT = PARTICLE_TYPES.register("glowing_enchant", () -> new ParticleType<GlowingEnchantmentParticleOptions>(true, GlowingEnchantmentParticleOptions.DESERIALIZER){

        public Codec<GlowingEnchantmentParticleOptions> codec() {
            return GlowingEnchantmentParticleOptions.CODEC;
        }
    });
    public static final RegistryObject<ParticleType<ColorfulBubbleParticleOptions>> COLORFUL_BUBBLE = PARTICLE_TYPES.register("colorful_bubble", () -> new ParticleType<ColorfulBubbleParticleOptions>(true, ColorfulBubbleParticleOptions.DESERIALIZER){

        public Codec<ColorfulBubbleParticleOptions> codec() {
            return ColorfulBubbleParticleOptions.CODEC;
        }
    });
    public static final RegistryObject<ParticleType<ReverseBlastwaveParticleOptions>> REVERSE_BLASTWAVE = PARTICLE_TYPES.register("reverse_blastwave", () -> new ParticleType<ReverseBlastwaveParticleOptions>(true, ReverseBlastwaveParticleOptions.DESERIALIZER){

        public Codec<ReverseBlastwaveParticleOptions> codec() {
            return ReverseBlastwaveParticleOptions.CODEC;
        }
    });

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}

