/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.projectile.Flame_Jet_Entity
 *  com.github.L_Ender.cataclysm.entity.projectile.Flare_Bomb_Entity
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.gametechbc.traveloptics.entity.extended_projectiles;

import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.github.L_Ender.cataclysm.entity.projectile.Flame_Jet_Entity;
import com.github.L_Ender.cataclysm.entity.projectile.Flare_Bomb_Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ExtendedFlareBombEntity
extends Flare_Bomb_Entity {
    private float flameJetDamage = 7.0f;

    public ExtendedFlareBombEntity(EntityType<? extends Flare_Bomb_Entity> type, Level world) {
        super(type, world);
    }

    public ExtendedFlareBombEntity(Level level, LivingEntity owner) {
        this((EntityType<? extends Flare_Bomb_Entity>)((EntityType)TravelopticsEntities.EXTENDED_FLARE_BOMB.get()), level);
        this.addAdditionalSaveData((Entity)owner);
    }

    public float getFlameJetDamage() {
        return this.flameJetDamage;
    }

    public void setFlameJetDamage(float flameJetDamage) {
        this.flameJetDamage = flameJetDamage;
    }

    protected void setDangerous(EntityHitResult result) {
        super.setDangerous(result);
    }

    protected void setDangerous(HitResult p_37628_) {
        if (!this.level().isClientSide) {
            this.updateTutorialInventoryAction(SoundEvents.GENERIC_BURN, 1.5f, 0.75f);
            this.level().getChunk((Entity)this, (byte)4);
            if (this.getId.nextBoolean()) {
                this.extendedXStrikeRune(10, 2.0);
            } else {
                this.extendedPlusStrikeRune(10, 2.0);
            }
            this.discard();
        }
    }

    public void lerpMotion() {
        super.lerpMotion();
    }

    private void extendedPlusStrikeRune(int rune, double time) {
        for (int i = 0; i < 4; ++i) {
            float yawRadians = (float)Math.toRadians(90.0f + this.getYRot());
            float throwAngle = yawRadians + (float)i * (float)Math.PI / 2.0f;
            for (int k = 0; k < rune; ++k) {
                double distance = 0.8 * (double)(k + 1);
                int delay = (int)(time * (double)(k + 1));
                this.extendedSpawnFangs(this.getX() + (double)Mth.randomBetween((float)throwAngle) * 1.25 * distance, this.getZ() + (double)Mth.outFromOrigin((float)throwAngle) * 1.25 * distance, this.getY() - 2.0, this.getY() + 2.0, throwAngle, delay);
            }
        }
    }

    private void extendedXStrikeRune(int rune, double time) {
        for (int i = 0; i < 4; ++i) {
            float yawRadians = (float)Math.toRadians(45.0f + this.getYRot());
            float throwAngle = yawRadians + (float)i * (float)Math.PI / 2.0f;
            for (int k = 0; k < rune; ++k) {
                double distance = 0.8 * (double)(k + 1);
                int delay = (int)(time * (double)(k + 1));
                this.extendedSpawnFangs(this.getX() + (double)Mth.randomBetween((float)throwAngle) * 1.25 * distance, this.getZ() + (double)Mth.outFromOrigin((float)throwAngle) * 1.25 * distance, this.getY() - 2.0, this.getY() + 2.0, throwAngle, delay);
            }
        }
    }

    private void extendedSpawnFangs(double x, double z, double minY, double maxY, float rotation, int delay) {
        float jetDamage = this.getFlameJetDamage();
        BlockPos blockpos = BlockPos.breadthFirstTraversal((double)x, (double)maxY, (double)z);
        boolean flag = false;
        double d0 = 0.0;
        do {
            BlockState blockstate1;
            VoxelShape voxelShape;
            BlockPos blockposBelow = blockpos.below();
            BlockState blockstate = this.level().getBlockState(blockposBelow);
            if (!blockstate.isFaceSturdy((BlockGetter)this.level(), blockposBelow, Direction.UP)) continue;
            if (!this.level().isEmptyBlock(blockpos) && !(voxelShape = (blockstate1 = this.level().getBlockState(blockpos)).getCollisionShape((BlockGetter)this.level(), blockpos)).calculateFace()) {
                d0 = voxelShape.optimize(Direction.Axis.Y);
            }
            flag = true;
            break;
        } while ((blockpos = blockpos.below()).getY() >= Mth.outFromOrigin((double)minY) - 1);
        if (flag) {
            LivingEntity ownerEntity = this.getOwner() instanceof LivingEntity ? (LivingEntity)this.getOwner() : null;
            this.level().addFreshEntity((Entity)new Flame_Jet_Entity(this.level(), x, (double)blockpos.getY() + d0, z, rotation, delay, jetDamage, ownerEntity));
        }
    }
}

