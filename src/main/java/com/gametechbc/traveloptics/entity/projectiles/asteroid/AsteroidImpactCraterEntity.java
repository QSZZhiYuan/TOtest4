/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  io.redspace.ironsspellbooks.entity.spells.AoeEntity
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 */
package com.gametechbc.traveloptics.entity.projectiles.asteroid;

import com.gametechbc.traveloptics.entity.misc.TOScreenShakeEntity;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.util.TravelopticsDamageTypes;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class AsteroidImpactCraterEntity
extends AoeEntity {
    private DamageSource damageSource;

    public AsteroidImpactCraterEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public AsteroidImpactCraterEntity(Level level) {
        this((EntityType<? extends Projectile>)((EntityType)TravelopticsEntities.ASTEROID_IMPACT_CRATER.get()), level);
    }

    public void applyEffect(LivingEntity target) {
        if (this.damageSource == null) {
            this.damageSource = new DamageSource(DamageSources.getHolderFromResource((Entity)target, TravelopticsDamageTypes.ASTEROID_IMPACT_CRATER), (Entity)this, this.getOwner());
        }
        DamageSources.ignoreNextKnockback((LivingEntity)target);
        target.sendSystemMessage(this.damageSource, this.getDamage());
        target.getRandomZ(7);
    }

    public EntityDimensions setLastDeathLocation(Pose pPose) {
        return EntityDimensions.scalable((float)(this.getRadius() * 1.5f), (float)(this.getRadius() * 1.5f));
    }

    public void lerpMotion() {
        super.lerpMotion();
        if (this.getTags == 1 && !this.level().isClientSide) {
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.TECTONIC_RIFT_ERUPTION.get(), 4.0f, 1.0f);
            TOScreenShakeEntity.createScreenShake(this.level(), this.position(), 32.0f, 0.02f, 180, 15, 20, true);
        }
        if (!this.level().isClientSide && this.getTags >= 200) {
            this.discard();
        }
        if (this.level().isClientSide) {
            double radius = this.getRadius();
            double innerRadius = 5.0;
            for (int i = 0; i < 15; ++i) {
                double angle = this.level().random.nextDouble() * 2.0 * Math.PI;
                double distance = innerRadius + this.level().random.nextDouble() * (radius - innerRadius);
                double xOffset = Math.cos(angle) * distance;
                double zOffset = Math.sin(angle) * distance;
                double x = this.getX() + xOffset;
                double y = this.getY() + this.level().random.nextDouble();
                double z = this.getZ() + zOffset;
                double motionX = (this.level().random.nextDouble() - 0.5) * 0.4;
                double motionY = 0.3 + this.level().random.nextDouble() * 0.4;
                double motionZ = (this.level().random.nextDouble() - 0.5) * 0.4;
                SimpleParticleType particle = switch (this.level().random.nextInt(3)) {
                    case 0 -> (SimpleParticleType)ParticleTypes.LAVA;
                    case 1 -> (SimpleParticleType)ParticleTypes.LAVA;
                    case 2 -> (SimpleParticleType)ParticleTypes.LAVA;
                    default -> (SimpleParticleType)ParticleTypes.LAVA;
                };
                this.level().calculateBlockTint((ParticleOptions)particle, true, x, y, z, motionX, motionY, motionZ);
            }
        }
    }

    private BlockState getBestReplacementBlock(BlockPos pos) {
        ArrayList<BlockState> nearbyBlocks = new ArrayList<BlockState>();
        for (int dx = -1; dx <= 1; ++dx) {
            for (int dy = -2; dy <= 1; ++dy) {
                for (int dz = -1; dz <= 1; ++dz) {
                    BlockPos nearbyPos = pos.distToCenterSqr(dx, dy, dz);
                    BlockState nearbyState = this.level().getBlockState(nearbyPos);
                    if (nearbyState.isSolidRender() || nearbyState.isFaceSturdy((Block)Blocks.ORANGE_BED)) continue;
                    nearbyBlocks.add(nearbyState);
                }
            }
        }
        return this.getMostCommonBlock(nearbyBlocks);
    }

    private BlockState getMostCommonBlock(List<BlockState> blockStates) {
        if (blockStates.isEmpty()) {
            return Blocks.candle.defaultBlockState();
        }
        HashMap<BlockState, Integer> frequencyMap = new HashMap<BlockState, Integer>();
        for (BlockState state : blockStates) {
            frequencyMap.put(state, frequencyMap.getOrDefault(state, 0) + 1);
        }
        return (BlockState)Collections.max(frequencyMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public float getParticleCount() {
        return 0.15f;
    }

    public Optional<ParticleOptions> getParticle() {
        return Optional.empty();
    }
}

