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
package com.gametechbc.traveloptics.particle.glowing_enchantment;

import com.gametechbc.traveloptics.particle.glowing_enchantment.GlowingEnchantmentParticleOptions;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
public class GlowingEnchantmentParticle
extends TextureSheetParticle {
    private final double xStart;
    private final double yStart;
    private final double zStart;
    private final boolean fallAtEnd;

    public GlowingEnchantmentParticle(ClientLevel level, double x, double y, double z, SpriteSet sprite, double xd, double yd, double zd, GlowingEnchantmentParticleOptions options) {
        super(level, x, y, z);
        this.getLifetime = xd;
        this.remove = yd;
        this.setLocationFromBoundingbox = zd;
        this.xStart = x;
        this.yStart = y;
        this.zStart = z;
        this.scale = x + xd;
        this.setAlpha = y + yd;
        this.zo = z + zd;
        this.x = this.scale;
        this.y = this.setAlpha;
        this.z = this.zo;
        this.quadSize = options.getScale();
        this.rCol = options.getColor().x();
        this.gCol = options.getColor().y();
        this.bCol = options.getColor().z();
        this.fallAtEnd = options.shouldFallAtEnd();
        this.getBoundingBox = false;
        this.lifetime = options.getLifespan();
        this.pickSprite(sprite);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public void tick(double x, double y, double z) {
        this.setBoundingBox(this.getBoundingBox().getZsize(x, y, z));
        this.setLocationFromBoundingbox();
    }

    public int tick(float partialTick) {
        return 0xF000F0;
    }

    public void tick() {
        this.scale = this.x;
        this.setAlpha = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            float f = (float)this.age / (float)this.lifetime;
            f = 1.0f - f;
            float f1 = 1.0f - f;
            f1 *= f1;
            f1 *= f1;
            this.x = this.xStart + this.getLifetime * (double)f;
            this.y = this.fallAtEnd ? this.yStart + this.remove * (double)f - (double)(f1 * 1.2f) : this.yStart + this.remove * (double)f;
            this.z = this.zStart + this.setLocationFromBoundingbox * (double)f;
            this.setPos(this.x, this.y, this.z);
        }
    }

    @OnlyIn(value=Dist.CLIENT)
    public static class Provider
    implements ParticleProvider<GlowingEnchantmentParticleOptions> {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        public Particle createParticle(GlowingEnchantmentParticleOptions options, ClientLevel level, double x, double y, double z, double xd, double yd, double zd) {
            return new GlowingEnchantmentParticle(level, x, y, z, this.sprite, xd, yd, zd, options);
        }
    }
}

