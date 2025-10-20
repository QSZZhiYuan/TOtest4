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
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.gametechbc.traveloptics.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RedStarOutwardParticle
extends TextureSheetParticle {
    private final SpriteSet sprites;

    public RedStarOutwardParticle(ClientLevel level, double xCoord, double yCoord, double zCoord, SpriteSet spriteSet, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);
        this.getLifetime = xd;
        this.remove = yd;
        this.setLocationFromBoundingbox = zd;
        this.getU1(this.random.nextFloat() * 1.75f + 1.0f);
        this.lifetime = 10 + (int)(Math.random() * 10.0);
        this.quadSize = 1.0f;
        this.sprites = spriteSet;
        this.setSpriteFromAge(spriteSet);
        this.getBoundingBox = false;
    }

    public void tick() {
        super.tick();
        this.getLifetime += (double)(this.random.nextFloat() / 100.0f * (float)(this.random.nextBoolean() ? 1 : -1));
        this.remove += (double)(this.random.nextFloat() / 100.0f);
        this.setLocationFromBoundingbox += (double)(this.random.nextFloat() / 100.0f * (float)(this.random.nextBoolean() ? 1 : -1));
        this.setSpriteFromAge(this.sprites);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public int tick(float p_107564_) {
        return 0xF000F0;
    }

    @OnlyIn(value=Dist.CLIENT)
    public static class Provider
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double dx, double dy, double dz) {
            return new RedStarOutwardParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}

