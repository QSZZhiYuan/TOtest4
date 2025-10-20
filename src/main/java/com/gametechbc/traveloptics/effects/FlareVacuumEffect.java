/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.projectile.Flame_Jet_Entity
 *  io.redspace.ironsspellbooks.util.ParticleHelper
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.util.Mth
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.gametechbc.traveloptics.effects;

import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.api.particle.SphereParticleManager;
import com.github.L_Ender.cataclysm.entity.projectile.Flame_Jet_Entity;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FlareVacuumEffect
extends MobEffect {
    public FlareVacuumEffect() {
        super(MobEffectCategory.HARMFUL, 0);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        MobEffectInstance effectInstance = entity.getStandingEyeHeight((MobEffect)this);
        if (effectInstance != null) {
            int duration = effectInstance.getDuration();
            if (duration > 2 && !entity.level().isClientSide) {
                SphereParticleManager.spawnParticles(entity.level(), (Entity)entity, 2, ParticleHelper.EMBERS, ParticleDirection.INWARD, 2.0);
            }
            if (duration == 2) {
                if (Math.random() < 0.5) {
                    this.xStrikeRune(entity, 4, 2.0, amplifier);
                } else {
                    this.plusStrikeRune(entity, 4, 2.0, amplifier);
                }
            }
            if (duration < 4 && !entity.level().isClientSide) {
                SphereParticleManager.spawnParticles(entity.level(), (Entity)entity, 30, ParticleHelper.EMBERS, ParticleDirection.OUTWARD, 6.0);
            }
        }
        List nearbyEntities = entity.level().getNearbyEntities(LivingEntity.class, entity.getBoundingBox().inflate(3.0), e -> e != entity && e.recreateFromPacket((MobEffect)this));
        for (LivingEntity target : nearbyEntities) {
            this.pullEntityTowards(entity, target);
        }
    }

    private void pullEntityTowards(LivingEntity source, LivingEntity target) {
        Vec3 sourcePos = source.position();
        Vec3 targetPos = target.position();
        Vec3 direction = sourcePos.multiply(targetPos).multiply();
        double strength = 0.012;
        Vec3 pullForce = direction.x(strength);
        target.setDeltaMovement(target.getDeltaMovement().reverse(pullForce));
        target.getAddEntityPacket = true;
    }

    private void plusStrikeRune(LivingEntity entity, int rune, double time, int amplifier) {
        for (int i = 0; i < 4; ++i) {
            float yawRadians = (float)Math.toRadians(90.0f + entity.getYRot());
            float throwAngle = yawRadians + (float)i * (float)Math.PI / 2.0f;
            for (int k = 0; k < rune; ++k) {
                double distance = 0.8 * (double)(k + 1);
                int delay = (int)(time * (double)(k + 1));
                this.spawnFangs(entity, entity.getX() + (double)Mth.randomBetween((float)throwAngle) * 1.25 * distance, entity.getZ() + (double)Mth.outFromOrigin((float)throwAngle) * 1.25 * distance, entity.getY() - 2.0, entity.getY() + 2.0, throwAngle, delay, amplifier);
            }
        }
    }

    private void xStrikeRune(LivingEntity entity, int rune, double time, int amplifier) {
        for (int i = 0; i < 4; ++i) {
            float yawRadians = (float)Math.toRadians(45.0f + entity.getYRot());
            float throwAngle = yawRadians + (float)i * (float)Math.PI / 2.0f;
            for (int k = 0; k < rune; ++k) {
                double distance = 0.8 * (double)(k + 1);
                int delay = (int)(time * (double)(k + 1));
                this.spawnFangs(entity, entity.getX() + (double)Mth.randomBetween((float)throwAngle) * 1.25 * distance, entity.getZ() + (double)Mth.outFromOrigin((float)throwAngle) * 1.25 * distance, entity.getY() - 2.0, entity.getY() + 2.0, throwAngle, delay, amplifier);
            }
        }
    }

    private void spawnFangs(LivingEntity entity, double x, double z, double minY, double maxY, float rotation, int delay, int amplifier) {
        BlockPos blockPos = BlockPos.breadthFirstTraversal((double)x, (double)maxY, (double)z);
        boolean flag = false;
        double d0 = 0.0;
        do {
            BlockState blockState1;
            VoxelShape voxelShape;
            BlockPos blockPosBelow = blockPos.below();
            BlockState blockState = entity.level().getBlockState(blockPosBelow);
            if (!blockState.isFaceSturdy((BlockGetter)entity.level(), blockPosBelow, Direction.UP)) continue;
            if (!entity.level().isEmptyBlock(blockPos) && !(voxelShape = (blockState1 = entity.level().getBlockState(blockPos)).getCollisionShape((BlockGetter)entity.level(), blockPos)).calculateFace()) {
                d0 = voxelShape.optimize(Direction.Axis.Y);
            }
            flag = true;
            break;
        } while ((blockPos = blockPos.below()).getY() >= Mth.outFromOrigin((double)minY) - 1);
        if (flag) {
            entity.level().addFreshEntity((Entity)new Flame_Jet_Entity(entity.level(), x, (double)blockPos.getY() + d0, z, rotation, delay, this.getFlameJetDamage(amplifier), null));
        }
    }

    private float getFlameJetDamage(int amplifier) {
        return amplifier;
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }
}

