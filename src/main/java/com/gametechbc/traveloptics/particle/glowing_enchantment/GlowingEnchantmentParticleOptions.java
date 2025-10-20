/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  net.minecraft.core.particles.DustParticleOptionsBase
 *  net.minecraft.core.particles.ParticleOptions$Deserializer
 *  net.minecraft.core.particles.ParticleType
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.util.ExtraCodecs
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Vector3f
 */
package com.gametechbc.traveloptics.particle.glowing_enchantment;

import com.gametechbc.traveloptics.init.TravelopticsParticles;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.DustParticleOptionsBase;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class GlowingEnchantmentParticleOptions
extends DustParticleOptionsBase {
    private final float scale;
    private final boolean fallAtEnd;
    private final int lifespan;
    public static final Codec<GlowingEnchantmentParticleOptions> CODEC = RecordCodecBuilder.create(instance -> instance.group((App)ExtraCodecs.VECTOR3F.fieldOf("color").forGetter(o -> o.color), (App)Codec.FLOAT.fieldOf("scale").forGetter(o -> Float.valueOf(o.scale)), (App)Codec.BOOL.fieldOf("fall_at_end").forGetter(o -> o.fallAtEnd), (App)Codec.INT.fieldOf("lifespan").forGetter(o -> o.lifespan)).apply((Applicative)instance, GlowingEnchantmentParticleOptions::new));
    public static final ParticleOptions.Deserializer<GlowingEnchantmentParticleOptions> DESERIALIZER = new ParticleOptions.Deserializer<GlowingEnchantmentParticleOptions>(){

        @NotNull
        public GlowingEnchantmentParticleOptions fromCommand(@NotNull ParticleType<GlowingEnchantmentParticleOptions> type, @NotNull StringReader reader) throws CommandSyntaxException {
            Vector3f color = DustParticleOptionsBase.writeToString((StringReader)reader);
            reader.expect(' ');
            float scale = reader.readFloat();
            reader.expect(' ');
            boolean fallAtEnd = reader.readBoolean();
            reader.expect(' ');
            int lifespan = reader.readInt();
            return new GlowingEnchantmentParticleOptions(color, scale, fallAtEnd, lifespan);
        }

        @NotNull
        public GlowingEnchantmentParticleOptions fromNetwork(@NotNull ParticleType<GlowingEnchantmentParticleOptions> type, @NotNull FriendlyByteBuf buffer) {
            return new GlowingEnchantmentParticleOptions(DustParticleOptionsBase.readVector3f((FriendlyByteBuf)buffer), buffer.readFloat(), buffer.readBoolean(), buffer.readInt());
        }
    };

    public GlowingEnchantmentParticleOptions(Vector3f color, float scale, boolean fallAtEnd, int lifespan) {
        super(color, scale);
        this.scale = scale;
        this.fallAtEnd = fallAtEnd;
        this.lifespan = Math.max(1, lifespan);
    }

    public float getScale() {
        return this.scale;
    }

    public boolean shouldFallAtEnd() {
        return this.fallAtEnd;
    }

    public int getLifespan() {
        return this.lifespan;
    }

    public void writeToString(FriendlyByteBuf buffer) {
        buffer.writeFloat(this.color.x());
        buffer.writeFloat(this.color.y());
        buffer.writeFloat(this.color.z());
        buffer.writeFloat(this.scale);
        buffer.writeBoolean(this.fallAtEnd);
        buffer.writeInt(this.lifespan);
    }

    @NotNull
    public ParticleType<GlowingEnchantmentParticleOptions> getType() {
        return (ParticleType)TravelopticsParticles.GLOWING_ENCHANT.get();
    }
}

