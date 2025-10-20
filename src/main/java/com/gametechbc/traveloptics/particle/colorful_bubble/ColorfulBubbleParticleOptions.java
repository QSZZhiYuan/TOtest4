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
package com.gametechbc.traveloptics.particle.colorful_bubble;

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

public class ColorfulBubbleParticleOptions
extends DustParticleOptionsBase {
    private final int lifetime;
    private final float quadSize;
    private final boolean shouldGlow;
    public static final Codec<ColorfulBubbleParticleOptions> CODEC = RecordCodecBuilder.create(instance -> instance.group((App)ExtraCodecs.VECTOR3F.fieldOf("color").forGetter(o -> o.color), (App)Codec.FLOAT.fieldOf("quad_size").forGetter(o -> Float.valueOf(o.quadSize)), (App)Codec.INT.fieldOf("lifetime").forGetter(o -> o.lifetime), (App)Codec.BOOL.fieldOf("should_glow").forGetter(o -> o.shouldGlow)).apply((Applicative)instance, ColorfulBubbleParticleOptions::new));
    public static final ParticleOptions.Deserializer<ColorfulBubbleParticleOptions> DESERIALIZER = new ParticleOptions.Deserializer<ColorfulBubbleParticleOptions>(){

        @NotNull
        public ColorfulBubbleParticleOptions fromCommand(@NotNull ParticleType<ColorfulBubbleParticleOptions> type, @NotNull StringReader reader) throws CommandSyntaxException {
            Vector3f color = DustParticleOptionsBase.writeToString((StringReader)reader);
            reader.expect(' ');
            float quadSize = reader.readFloat();
            reader.expect(' ');
            int lifetime = reader.readInt();
            reader.expect(' ');
            boolean shouldGlow = reader.readBoolean();
            return new ColorfulBubbleParticleOptions(color, quadSize, lifetime, shouldGlow);
        }

        @NotNull
        public ColorfulBubbleParticleOptions fromNetwork(@NotNull ParticleType<ColorfulBubbleParticleOptions> type, @NotNull FriendlyByteBuf buffer) {
            return new ColorfulBubbleParticleOptions(DustParticleOptionsBase.readVector3f((FriendlyByteBuf)buffer), buffer.readFloat(), buffer.readInt(), buffer.readBoolean());
        }
    };

    public ColorfulBubbleParticleOptions(Vector3f color, float quadSize, int lifetime, boolean shouldGlow) {
        super(color, quadSize);
        this.lifetime = Math.max(1, lifetime);
        this.quadSize = quadSize;
        this.shouldGlow = shouldGlow;
    }

    public int getLifetime() {
        return this.lifetime;
    }

    public float getScale() {
        return this.quadSize;
    }

    public float getQuadSize() {
        return this.quadSize;
    }

    public boolean shouldGlow() {
        return this.shouldGlow;
    }

    public void writeToString(FriendlyByteBuf buffer) {
        buffer.writeFloat(this.color.x());
        buffer.writeFloat(this.color.y());
        buffer.writeFloat(this.color.z());
        buffer.writeFloat(this.quadSize);
        buffer.writeInt(this.lifetime);
        buffer.writeBoolean(this.shouldGlow);
    }

    @NotNull
    public ParticleType<ColorfulBubbleParticleOptions> getType() {
        return (ParticleType)TravelopticsParticles.COLORFUL_BUBBLE.get();
    }
}

