/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.entity.mobs.MagicSummon
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 */
package com.gametechbc.traveloptics.effects.vigor_siphon;

import com.gametechbc.traveloptics.api.particle.ConnectedLineAnimatedParticle;
import io.redspace.ironsspellbooks.entity.mobs.MagicSummon;
import java.util.Comparator;
import java.util.List;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class VigorSiphonEffect
extends MobEffect {
    public VigorSiphonEffect() {
        super(MobEffectCategory.BENEFICIAL, 10423267);
    }

    public static double getConnectionRange() {
        return 15.0;
    }

    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        Level level = livingEntity.level();
        if (level.isClientSide) {
            return;
        }
        AABB searchArea = livingEntity.getBoundingBox().inflate(VigorSiphonEffect.getConnectionRange());
        List ownedSummons = level.getNearbyEntities(LivingEntity.class, searchArea, entity -> {
            if (!(entity instanceof MagicSummon)) return false;
            MagicSummon magicSummon = (MagicSummon)entity;
            if (!entity.isAlive()) return false;
            if (magicSummon.getSummoner() == null) return false;
            if (!magicSummon.getSummoner().getUUID().equals(livingEntity.getUUID())) return false;
            return true;
        });
        ownedSummons.stream().min(Comparator.comparingDouble(arg_0 -> ((LivingEntity)livingEntity).getZ(arg_0))).ifPresent(summon -> ConnectedLineAnimatedParticle.createParticleLineTo(level, livingEntity, summon, ConnectedLineAnimatedParticle.AnimationType.SPIRAL_HELIX, livingEntity.getTags, true, ConnectedLineAnimatedParticle.ParticleColor.BLOOD_RED, ConnectedLineAnimatedParticle.ParticleColor.DARK_ORANGE));
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }
}

