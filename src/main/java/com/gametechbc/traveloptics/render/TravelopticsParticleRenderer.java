/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.particles.ParticleType
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.client.event.RegisterParticleProvidersEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 */
package com.gametechbc.traveloptics.render;

import com.gametechbc.traveloptics.init.TravelopticsParticles;
import com.gametechbc.traveloptics.particle.AbyssSpikeParticle;
import com.gametechbc.traveloptics.particle.AcidDropParticle;
import com.gametechbc.traveloptics.particle.CursedBlastParticle;
import com.gametechbc.traveloptics.particle.GlowingReversePortalParticle;
import com.gametechbc.traveloptics.particle.PurpleStarInwardParticle;
import com.gametechbc.traveloptics.particle.PurpleStarOutwardParticle;
import com.gametechbc.traveloptics.particle.RedStarInwardParticle;
import com.gametechbc.traveloptics.particle.RedStarOutwardParticle;
import com.gametechbc.traveloptics.particle.WaterBubbleParticle;
import com.gametechbc.traveloptics.particle.WaterDropParticle;
import com.gametechbc.traveloptics.particle.WaterParticle;
import com.gametechbc.traveloptics.particle.colorful_bubble.ColorfulBubbleParticle;
import com.gametechbc.traveloptics.particle.coral_bubble.BlueCoralBubbleParticle;
import com.gametechbc.traveloptics.particle.coral_bubble.PinkCoralBubbleParticle;
import com.gametechbc.traveloptics.particle.coral_bubble.RedCoralBubbleParticle;
import com.gametechbc.traveloptics.particle.coral_bubble.YellowCoralBubbleParticle;
import com.gametechbc.traveloptics.particle.glowing_enchantment.GlowingEnchantmentParticle;
import com.gametechbc.traveloptics.particle.reverse_blastwave.ReverseBlastwaveParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid="traveloptics", bus=Mod.EventBusSubscriber.Bus.MOD, value={Dist.CLIENT})
public class TravelopticsParticleRenderer {
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet((ParticleType)TravelopticsParticles.CURSED_BLAST.get(), CursedBlastParticle.Provider::new);
        event.registerSpriteSet((ParticleType)TravelopticsParticles.ABYSS_SPIKE_PARTICLE.get(), AbyssSpikeParticle.Provider::new);
        event.registerSpriteSet((ParticleType)TravelopticsParticles.WATER_PARTICLE.get(), WaterParticle.Provider::new);
        event.registerSpriteSet((ParticleType)TravelopticsParticles.WATER_BUBBLE_PARTICLE.get(), WaterBubbleParticle.Provider::new);
        event.registerSpriteSet((ParticleType)TravelopticsParticles.WATER_DROP_PARTICLE.get(), WaterDropParticle.Provider::new);
        event.registerSpriteSet((ParticleType)TravelopticsParticles.ACID_DROP_PARTICLE.get(), AcidDropParticle.Provider::new);
        event.registerSpriteSet((ParticleType)TravelopticsParticles.BLUE_CORAL_BUBBLE_PARTICLE.get(), BlueCoralBubbleParticle.Provider::new);
        event.registerSpriteSet((ParticleType)TravelopticsParticles.YELLOW_CORAL_BUBBLE_PARTICLE.get(), YellowCoralBubbleParticle.Provider::new);
        event.registerSpriteSet((ParticleType)TravelopticsParticles.RED_CORAL_BUBBLE_PARTICLE.get(), RedCoralBubbleParticle.Provider::new);
        event.registerSpriteSet((ParticleType)TravelopticsParticles.PINK_CORAL_BUBBLE_PARTICLE.get(), PinkCoralBubbleParticle.Provider::new);
        event.registerSpriteSet((ParticleType)TravelopticsParticles.GLOWING_ENCHANT.get(), GlowingEnchantmentParticle.Provider::new);
        event.registerSpriteSet((ParticleType)TravelopticsParticles.GLOWING_REVERSE_PORTAL_PARTICLE.get(), GlowingReversePortalParticle.GlowingReversePortalProvider::new);
        event.registerSpriteSet((ParticleType)TravelopticsParticles.PURPLE_STAR_INWARD_PARTICLE.get(), PurpleStarInwardParticle.Provider::new);
        event.registerSpriteSet((ParticleType)TravelopticsParticles.PURPLE_STAR_OUTWARD_PARTICLE.get(), PurpleStarOutwardParticle.Provider::new);
        event.registerSpriteSet((ParticleType)TravelopticsParticles.RED_STAR_INWARD_PARTICLE.get(), RedStarInwardParticle.Provider::new);
        event.registerSpriteSet((ParticleType)TravelopticsParticles.RED_STAR_OUTWARD_PARTICLE.get(), RedStarOutwardParticle.Provider::new);
        event.registerSpriteSet((ParticleType)TravelopticsParticles.COLORFUL_BUBBLE.get(), ColorfulBubbleParticle.Provider::new);
        event.registerSpriteSet((ParticleType)TravelopticsParticles.REVERSE_BLASTWAVE.get(), ReverseBlastwaveParticle.Provider::new);
    }
}

