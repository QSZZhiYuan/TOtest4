/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.particle.ParticleProvider
 *  net.minecraft.client.particle.ParticleRenderType
 *  net.minecraft.client.particle.SpriteSet
 *  net.minecraft.client.particle.TextureSheetParticle
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.gametechbc.traveloptics.particle.colorful_bubble;

import com.gametechbc.traveloptics.particle.colorful_bubble.ColorfulBubbleParticleOptions;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
public class ColorfulBubbleParticle
extends TextureSheetParticle {
    private final SpriteSet sprites;
    private final boolean shouldGlow;

    public ColorfulBubbleParticle(ClientLevel level, double xCoord, double yCoord, double zCoord, SpriteSet spriteSet, double xd, double yd, double zd, ColorfulBubbleParticleOptions options) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);
        this.getLifetime = xd;
        this.remove = yd;
        this.setLocationFromBoundingbox = zd;
        this.quadSize *= options.getQuadSize() * 3.0f;
        this.lifetime = options.getLifetime();
        this.rCol = options.getColor().x();
        this.gCol = options.getColor().y();
        this.bCol = options.getColor().z();
        this.shouldGlow = options.shouldGlow();
        this.sprites = spriteSet;
        this.gravity = 0.35f;
        this.setSpriteFromAge(spriteSet);
    }

    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public int tick(float partialTick) {
        if (this.shouldGlow) {
            return 0xF000F0;
        }
        return super.tick(partialTick);
    }

    @OnlyIn(value=Dist.CLIENT)
    public static class Provider
    implements ParticleProvider<ColorfulBubbleParticleOptions> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(ColorfulBubbleParticleOptions options, ClientLevel level, double x, double y, double z, double dx, double dy, double dz) {
            return new ColorfulBubbleParticle(level, x, y, z, this.sprites, dx, dy, dz, options);
        }
    }
}

