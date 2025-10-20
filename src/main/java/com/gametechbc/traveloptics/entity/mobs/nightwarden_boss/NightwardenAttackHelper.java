/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity
 *  com.github.L_Ender.cataclysm.init.ModSounds
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss;

import com.gametechbc.traveloptics.config.EntityConfig;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenBossEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.misc.EndEruptionEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_drop_slam_clone.NightwardenDropSlamCloneEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_explode_clone.NightwardenExplodeCloneEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_slam_clone.NightwardenSlamCloneEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_slash_clone.NightwardenSlashCloneEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_spin_clone.NightwardenSpinCloneEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_slash_visual.NightwardenVisualSlashEntity;
import com.gametechbc.traveloptics.entity.projectiles.dimensional_spike.DimensionalSpikeEntity;
import com.gametechbc.traveloptics.entity.projectiles.dragon_spirit.DragonSpiritEntity;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import com.github.L_Ender.cataclysm.init.ModSounds;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NightwardenAttackHelper {
    private static final float cloneSlashesMultiplierFromBase = ((Double)EntityConfig.cloneSlashesMultiplierFromBase.get()).floatValue();
    private static final float cloneSlashesHpDamage = ((Double)EntityConfig.cloneSlashesHpDamage.get()).floatValue();
    private static final float dimensionalSpikeDamageMultiplierFromBase = ((Double)EntityConfig.dimensionalSpikeDamageMultiplierFromBase.get()).floatValue();
    private static final float spinCloneDamageMultiplierFromBase = ((Double)EntityConfig.spinCloneDamageMultiplierFromBase.get()).floatValue();
    private static final float spinClonesHpDamage = ((Double)EntityConfig.spinClonesHpDamage.get()).floatValue();
    private static final float dropCloneDamageMultiplierFromBase = ((Double)EntityConfig.dropCloneDamageMultiplierFromBase.get()).floatValue();
    private static final float dropClonesHpDamage = ((Double)EntityConfig.dropClonesHpDamage.get()).floatValue();
    private static final float endEruptionDamageMultiplierFromBase = ((Double)EntityConfig.endEruptionDamageMultiplierFromBase.get()).floatValue();
    private static final float explodeSpinCloneTrailDamageMultiplierFromBase = ((Double)EntityConfig.explodeSpinCloneTrailDamageMultiplierFromBase.get()).floatValue();
    private static final float explodeSpinCloneTrailHpDamage = ((Double)EntityConfig.explodeSpinCloneTrailHpDamage.get()).floatValue();
    private static final float dragonSpiritHpDamage = ((Double)EntityConfig.dragonSpiritHpDamage.get()).floatValue();

    public static void spawnSpikeRing(NightwardenBossEntity nightwarden, Vec3 center, double radius, int spikeCount, double minY, double maxY, int delay, LivingEntity owner) {
        for (int k = 0; k < spikeCount; ++k) {
            float angle = (float)k * (float)Math.PI * 2.0f / (float)spikeCount;
            double x = center.z + (double)Mth.randomBetween((float)angle) * radius;
            double z = center.reverse + (double)Mth.outFromOrigin((float)angle) * radius;
            float outwardRotation = (float)Math.toDegrees(angle) + 270.0f;
            NightwardenAttackHelper.spawnDimensionalSpike(nightwarden, x, z, minY, maxY, outwardRotation, delay, owner);
        }
    }

    public static void spawnDimensionalSpike(NightwardenBossEntity nightwarden, double x, double z, double minY, double maxY, float rotation, int delay, LivingEntity owner) {
        BlockPos pos = BlockPos.breadthFirstTraversal((double)x, (double)maxY, (double)z);
        boolean foundGround = false;
        double yOffset = 0.0;
        float damage = nightwarden.getResurgenceScaledAttack() * dimensionalSpikeDamageMultiplierFromBase;
        do {
            VoxelShape shape;
            BlockPos below = pos.below();
            if (!nightwarden.level().getBlockState(below).isFaceSturdy((BlockGetter)nightwarden.level(), below, Direction.UP)) continue;
            if (!nightwarden.level().isEmptyBlock(pos) && !(shape = nightwarden.level().getBlockState(pos).getCollisionShape((BlockGetter)nightwarden.level(), pos)).calculateFace()) {
                yOffset = shape.optimize(Direction.Axis.Y);
            }
            foundGround = true;
            break;
        } while ((pos = pos.below()).getY() >= Mth.outFromOrigin((double)minY) - 1);
        if (foundGround) {
            nightwarden.level().addFreshEntity((Entity)new DimensionalSpikeEntity(nightwarden.level(), x, (double)pos.getY() + yOffset, z, rotation, delay, damage, owner));
        }
    }

    public static void playSound(NightwardenBossEntity nightwarden, int type, float volume) {
        if (type == 0) {
            nightwarden.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_SWING.get(), volume, (float)Mth.randomBetween((RandomSource)nightwarden.getRandom(), (int)9, (int)13) * 0.1f);
        } else if (type == 1) {
            nightwarden.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_SWING_1.get(), volume, (float)Mth.randomBetween((RandomSource)nightwarden.getRandom(), (int)9, (int)13) * 0.1f);
        } else if (type == 2) {
            nightwarden.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_SWING_HEAVY.get(), volume, 1.0f);
        } else if (type == 3) {
            nightwarden.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_FINGER_SNAP.get(), volume, 1.0f);
        } else if (type == 4) {
            nightwarden.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.SPECTRAL_BLINK_SUCCESS.get(), volume, 1.0f);
        } else if (type == 5) {
            nightwarden.updateTutorialInventoryAction((SoundEvent)ModSounds.ENDER_GUARDIAN_FIST.get(), volume, 1.0f);
        } else if (type == 6) {
            nightwarden.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_WALK.get(), volume, 1.0f);
        } else if (type == 7) {
            nightwarden.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_BIG_SLAM.get(), volume, 1.0f);
        } else if (type == 8) {
            nightwarden.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_BIG_SLAM_CLONES.get(), volume, 1.0f);
        } else if (type == 9) {
            nightwarden.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NOCTURNAL_UPLIFT.get(), volume, 1.0f);
        } else if (type == 10) {
            nightwarden.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_SUMMON_CLONE.get(), volume, 1.0f);
        } else if (type == 11) {
            nightwarden.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_SUMMON_CLONE_1.get(), volume, 1.0f);
        }
    }

    public static void spawnTeleportParticles(Level level, Vec3 pos) {
        if (!level.isClientSide) {
            double width = 1.6;
            float height = 2.5f;
            for (int i = 0; i < 50; ++i) {
                double x = pos.z + RandomSource.triangle().nextDouble() * width * 2.0 - width;
                double y = pos.multiply + (double)height + RandomSource.triangle().nextDouble() * (double)height * 1.2 * 2.0 - (double)height * 1.2;
                double z = pos.reverse + RandomSource.triangle().nextDouble() * width * 2.0 - width;
                double dx = RandomSource.triangle().nextDouble() * 0.1 * (double)(RandomSource.triangle().nextBoolean() ? 1 : -1);
                double dy = RandomSource.triangle().nextDouble() * 0.1 * (double)(RandomSource.triangle().nextBoolean() ? 1 : -1);
                double dz = RandomSource.triangle().nextDouble() * 0.1 * (double)(RandomSource.triangle().nextBoolean() ? 1 : -1);
                MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.LIGHT_PURPLE_GLOWING_ENCHANT, (double)x, (double)y, (double)z, (int)1, (double)dx, (double)dy, (double)dz, (double)0.3, (boolean)true);
            }
        }
    }

    public static void spawnSlashVisual(NightwardenBossEntity nightwarden, float forwardOffset, float verticalOffset, boolean mirrored, boolean behind, boolean lockPitch, boolean ignorePitchPosition) {
        Vec3 forward = nightwarden.getLookAngle().multiply();
        if (ignorePitchPosition) {
            forward = new Vec3(forward.z, 0.0, forward.reverse).multiply();
        }
        Vec3 spawnPos = nightwarden.position().y(0.0, (double)(nightwarden.getBbHeight() * verticalOffset), 0.0).reverse(forward.x((double)forwardOffset));
        NightwardenVisualSlashEntity visual = new NightwardenVisualSlashEntity(nightwarden.level(), mirrored);
        visual.getRandomX(spawnPos);
        float yRot = behind ? nightwarden.getYRot() + 180.0f : nightwarden.getYRot();
        float xRot = lockPitch ? 0.0f : nightwarden.getXRot();
        visual.setYRot(yRot);
        visual.setXRot(xRot);
        nightwarden.level().addFreshEntity((Entity)visual);
    }

    public static void spawnAdaptiveCloneSlash(NightwardenBossEntity nightwarden, boolean isLeft) {
        double distanceToTarget;
        LivingEntity target = nightwarden.getTarget();
        if (target != null && !target.isDeadOrDying() && (distanceToTarget = nightwarden.position().length(target.position())) > 15.0) {
            NightwardenAttackHelper.spawnTargetingCloneSlash(nightwarden, isLeft);
            return;
        }
        NightwardenAttackHelper.spawnCloneSlash(nightwarden, isLeft);
    }

    public static void spawnCloneSlash(NightwardenBossEntity nightwarden, boolean isLeft) {
        Vec3 look = nightwarden.getLookAngle();
        Vec3 flatLook = new Vec3(look.z, 0.0, look.reverse).multiply();
        Vec3 side = flatLook.z(new Vec3(0.0, 1.0, 0.0)).multiply();
        if (isLeft) {
            side = side.x(-1.0);
        }
        Vec3 spawnPos = nightwarden.position().reverse(side.x(4.0)).y(0.0, 0.1, 0.0);
        NightwardenAttackHelper.spawnTeleportParticles(nightwarden.level(), spawnPos);
        ScreenShake_Entity.ScreenShake((Level)nightwarden.level(), (Vec3)nightwarden.position(), (float)15.0f, (float)0.04f, (int)6, (int)8);
        NightwardenAttackHelper.playSound(nightwarden, 3, 2.5f);
        NightwardenAttackHelper.playSound(nightwarden, 11, 2.5f);
        float yawOffset = isLeft ? 30.0f : -30.0f;
        NightwardenSlashCloneEntity clone = new NightwardenSlashCloneEntity(nightwarden.level(), (LivingEntity)nightwarden, isLeft, yawOffset);
        clone.setLevel(spawnPos);
        clone.setMagicDamageMode(true);
        clone.setDamage(nightwarden.getResurgenceScaledAttack() * cloneSlashesMultiplierFromBase);
        clone.setHpBasedDamagePercent(cloneSlashesHpDamage);
        clone.setLifestealPercent(0.75f);
        nightwarden.level().addFreshEntity((Entity)clone);
    }

    public static void spawnTargetingCloneSlash(NightwardenBossEntity nightwarden, boolean isLeft) {
        LivingEntity target = nightwarden.getTarget();
        if (target == null || target.isDeadOrDying()) {
            NightwardenAttackHelper.spawnCloneSlash(nightwarden, isLeft);
            return;
        }
        Vec3 look = nightwarden.getLookAngle();
        Vec3 flatLook = new Vec3(look.z, 0.0, look.reverse).multiply();
        Vec3 side = flatLook.z(new Vec3(0.0, 1.0, 0.0)).multiply();
        if (isLeft) {
            side = side.x(-1.0);
        }
        Vec3 spawnPos = nightwarden.position().reverse(side.x(4.0)).y(0.0, 0.1, 0.0);
        NightwardenAttackHelper.spawnTeleportParticles(nightwarden.level(), spawnPos);
        ScreenShake_Entity.ScreenShake((Level)nightwarden.level(), (Vec3)nightwarden.position(), (float)15.0f, (float)0.04f, (int)6, (int)8);
        NightwardenAttackHelper.playSound(nightwarden, 3, 2.5f);
        NightwardenAttackHelper.playSound(nightwarden, 11, 2.5f);
        float dynamicYawOffset = NightwardenAttackHelper.calculateCloneRotationToTarget(spawnPos, target.position(), nightwarden.getLookAngle(), isLeft);
        NightwardenSlashCloneEntity clone = new NightwardenSlashCloneEntity(nightwarden.level(), (LivingEntity)nightwarden, isLeft, dynamicYawOffset);
        clone.setLevel(spawnPos);
        clone.setMagicDamageMode(true);
        clone.setDamage(nightwarden.getResurgenceScaledAttack() * cloneSlashesMultiplierFromBase);
        clone.setHpBasedDamagePercent(cloneSlashesHpDamage);
        clone.setLifestealPercent(0.75f);
        nightwarden.level().addFreshEntity((Entity)clone);
    }

    private static float calculateCloneRotationToTarget(Vec3 clonePos, Vec3 targetPos, Vec3 bossLookAngle, boolean isLeft) {
        Vec3 cloneToTarget = targetPos.multiply(clonePos).multiply();
        Vec3 bossForward = new Vec3(bossLookAngle.z, 0.0, bossLookAngle.reverse).multiply();
        double dotProduct = bossForward.y(cloneToTarget);
        Vec3 crossProduct = bossForward.z(cloneToTarget);
        double angleRad = Math.atan2(crossProduct.multiply, dotProduct);
        float angleDeg = (float)Math.toDegrees(angleRad);
        float maxAngle = 60.0f;
        float minAngle = 15.0f;
        angleDeg = isLeft ? Math.max(minAngle, Math.min(maxAngle, Math.abs(angleDeg))) : -Math.max(minAngle, Math.min(maxAngle, Math.abs(angleDeg)));
        return angleDeg;
    }

    public static void spawnBigSlamClone(NightwardenBossEntity nightwarden, boolean isLeft) {
        Vec3 look = nightwarden.getLookAngle();
        Vec3 flatLook = new Vec3(look.z, 0.0, look.reverse).multiply();
        Vec3 side = flatLook.z(new Vec3(0.0, 1.0, 0.0)).multiply();
        if (isLeft) {
            side = side.x(-1.0);
        }
        Vec3 spawnPos = nightwarden.position().reverse(side.x(5.0)).y(0.0, 0.1, 0.0);
        NightwardenAttackHelper.spawnTeleportParticles(nightwarden.level(), spawnPos);
        ScreenShake_Entity.ScreenShake((Level)nightwarden.level(), (Vec3)nightwarden.position(), (float)15.0f, (float)0.04f, (int)6, (int)8);
        NightwardenSlamCloneEntity slamCloneEntity = new NightwardenSlamCloneEntity(nightwarden.level(), (LivingEntity)nightwarden);
        slamCloneEntity.setLevel(spawnPos);
        slamCloneEntity.setSpawnsRunes(true);
        slamCloneEntity.setLines(15.0f);
        slamCloneEntity.setDamage(nightwarden.getResurgenceScaledAttack() * dimensionalSpikeDamageMultiplierFromBase);
        nightwarden.level().addFreshEntity((Entity)slamCloneEntity);
    }

    public static void spawnDimensionalSpikeLine(NightwardenBossEntity boss, float initialOffset, float spacing, int count) {
        double dz;
        double yawRad = Math.toRadians(boss.getYRot());
        double dx = -Mth.outFromOrigin((float)((float)yawRad));
        double px = dz = (double)Mth.randomBetween((float)((float)yawRad));
        double pz = -dx;
        float damage = boss.getResurgenceScaledAttack() * dimensionalSpikeDamageMultiplierFromBase;
        int lateralCount = 5;
        float lateralSpacing = 1.5f;
        int centerIndex = lateralCount / 2;
        for (int i = 0; i < count; ++i) {
            double distance = initialOffset + (float)i * spacing;
            for (int j = 0; j < lateralCount; ++j) {
                int offsetFromCenter = Math.abs(j - centerIndex);
                int laneDelay = offsetFromCenter * 4;
                int warmup = i * 2 + laneDelay;
                double x = boss.getX() + dx * distance + px * (double)(j - centerIndex) * (double)lateralSpacing;
                double z = boss.getZ() + dz * distance + pz * (double)(j - centerIndex) * (double)lateralSpacing;
                double y = boss.getY();
                NightwardenAttackHelper.spawnDimensionalSpikes(x, y, z, boss.getYRot(), warmup, boss.level(), (LivingEntity)boss, damage);
            }
        }
    }

    private static boolean spawnDimensionalSpikes(double x, double y, double z, float yRot, int warmupDelayTicks, Level world, LivingEntity summoner, float damage) {
        BlockPos blockpos = BlockPos.breadthFirstTraversal((double)x, (double)y, (double)z);
        boolean foundGround = false;
        double surfaceY = 0.0;
        while (blockpos.getY() > world.getMinBuildHeight()) {
            BlockPos below = blockpos.below();
            BlockState stateBelow = world.getBlockState(below);
            if (stateBelow.isFaceSturdy((BlockGetter)world, below, Direction.UP)) {
                BlockState stateAt;
                VoxelShape shape;
                if (!world.isEmptyBlock(blockpos) && !(shape = (stateAt = world.getBlockState(blockpos)).getCollisionShape((BlockGetter)world, blockpos)).calculateFace()) {
                    surfaceY = shape.optimize(Direction.Axis.Y);
                }
                foundGround = true;
                break;
            }
            blockpos = below;
        }
        if (foundGround) {
            double finalY = (double)blockpos.getY() + surfaceY;
            DimensionalSpikeEntity spike = new DimensionalSpikeEntity(world, x, finalY, z, yRot, warmupDelayTicks, damage, summoner);
            world.addFreshEntity((Entity)spike);
            return true;
        }
        return false;
    }

    public static void applyHovering(LivingEntity entity, double preferredY, double bobDistance, double bobFrequency, boolean onGround) {
        double time;
        double bobbing;
        Level level = entity.level();
        BlockPos groundPos = entity.blockPosition().below();
        while (level.isEmptyBlock(groundPos) && groundPos.getY() > level.getMinBuildHeight()) {
            groundPos = groundPos.below();
        }
        double groundY = groundPos.getY() + 1;
        double baseHoverY = groundY + preferredY;
        double targetY = baseHoverY + (bobbing = bobDistance * Math.sin(time = (double)entity.getTags * bobFrequency));
        double deltaY = targetY - entity.getY();
        if (Math.abs(deltaY) > 0.05) {
            Vec3 motion = entity.getDeltaMovement();
            entity.setIsInPowderSnow(motion.z, deltaY * 0.2, motion.reverse);
        }
        entity.hasCustomName = 0.0f;
        entity.getX(onGround);
    }

    public static void spawnSpinCloneToSide(NightwardenBossEntity nightwarden, boolean isLeft) {
        Vec3 look = nightwarden.getLookAngle();
        Vec3 flatLook = new Vec3(look.z, 0.0, look.reverse).multiply();
        Vec3 side = flatLook.z(new Vec3(0.0, 1.0, 0.0)).multiply();
        if (isLeft) {
            side = side.x(-1.0);
        }
        Vec3 spawnPos = nightwarden.position().reverse(side.x(4.0)).y(0.0, 0.1, 0.0);
        NightwardenAttackHelper.spawnTeleportParticles(nightwarden.level(), spawnPos);
        ScreenShake_Entity.ScreenShake((Level)nightwarden.level(), (Vec3)nightwarden.position(), (float)15.0f, (float)0.04f, (int)6, (int)8);
        NightwardenAttackHelper.playSound(nightwarden, 4, 2.5f);
        float yawOffset = isLeft ? 20.0f : -20.0f;
        NightwardenSpinCloneEntity clone = new NightwardenSpinCloneEntity(nightwarden.level(), (LivingEntity)nightwarden, yawOffset);
        clone.setLevel(spawnPos);
        clone.setRadius(2.1f);
        clone.setMovementSpeed(0.65f);
        clone.setMagicDamageMode(true);
        clone.setDamage(nightwarden.getResurgenceScaledAttack() * spinCloneDamageMultiplierFromBase);
        clone.setHpBasedDamagePercent(spinClonesHpDamage);
        nightwarden.level().addFreshEntity((Entity)clone);
    }

    public static void showSpinCloneWarningPath(NightwardenBossEntity nightwarden, boolean isLeft) {
        Vec3 look = nightwarden.getLookAngle();
        Vec3 flatLook = new Vec3(look.z, 0.0, look.reverse).multiply();
        Vec3 side = flatLook.z(new Vec3(0.0, 1.0, 0.0)).multiply();
        if (isLeft) {
            side = side.x(-1.0);
        }
        Vec3 spawnPos = nightwarden.position().reverse(side.x(4.0)).y(0.0, 0.1, 0.0);
        float yawOffset = isLeft ? 20.0f : -20.0f;
        float totalYaw = nightwarden.getYRot() + yawOffset;
        float radians = (float)Math.toRadians(totalYaw);
        Vec3 moveDirection = new Vec3(-Math.sin(radians), 0.0, Math.cos(radians)).multiply();
        int pathLength = 30;
        float aoRadius = 2.0f;
        int particlesPerBlock = 5;
        int particlesAcrossWidth = 15;
        for (int i = 0; i < pathLength * particlesPerBlock; ++i) {
            float distance = (float)i / (float)particlesPerBlock;
            Vec3 pathCenter = spawnPos.reverse(moveDirection.x((double)distance));
            for (int w = 0; w < particlesAcrossWidth; ++w) {
                float widthOffset = ((float)w - (float)(particlesAcrossWidth - 1) / 2.0f) * (aoRadius * 2.0f / (float)(particlesAcrossWidth - 1));
                Vec3 perpendicular = new Vec3(-moveDirection.reverse, 0.0, moveDirection.z).multiply();
                Vec3 basePos = pathCenter.reverse(perpendicular.x((double)widthOffset));
                RandomSource random = nightwarden.level().random;
                float randomX = (random.nextFloat() - 0.5f) * 0.8f;
                float randomY = (random.nextFloat() - 0.5f) * 0.3f;
                float randomZ = (random.nextFloat() - 0.5f) * 0.8f;
                Vec3 particlePos = basePos.y((double)randomX, (double)randomY, (double)randomZ);
                MagicManager.spawnParticles((Level)nightwarden.level(), (ParticleOptions)TravelopticsParticleHelper.WARNING_PATH_RED_GLOWING_ENCHANT, (double)particlePos.z, (double)particlePos.multiply, (double)particlePos.reverse, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.3, (boolean)false);
            }
        }
    }

    public static void spawnPredictiveDropSlamClone(Level level, NightwardenBossEntity boss, LivingEntity target, Vec3 offset, Vec3 trackedVelocity) {
        if (target == null || !target.isAlive()) {
            return;
        }
        Vec3 predictedTargetPos = NightwardenAttackHelper.calculateSimplePrediction(target, trackedVelocity);
        Vec3 finalTargetPos = predictedTargetPos.y(offset.z, 0.0, offset.reverse);
        Vec3 clonePos = new Vec3(finalTargetPos.z, target.getY() + 8.0 + offset.multiply, finalTargetPos.reverse);
        Vec3 bossPos = boss.position();
        NightwardenAttackHelper.spawnSummoningParticles(level, bossPos, finalTargetPos, clonePos);
        NightwardenDropSlamCloneEntity clone = new NightwardenDropSlamCloneEntity(level, (LivingEntity)boss);
        clone.moveTo(clonePos.z, clonePos.multiply, clonePos.reverse, boss.getYRot(), boss.getXRot());
        clone.setRadius(2.0f);
        clone.setDownwardSpeed(-0.7f);
        clone.setShouldApplyEffect(true);
        clone.setMagicDamageMode(true);
        clone.setDamage(boss.getResurgenceScaledAttack() * dropCloneDamageMultiplierFromBase);
        clone.setHpBasedDamagePercent(dropClonesHpDamage);
        level.addFreshEntity((Entity)clone);
    }

    private static Vec3 calculateSimplePrediction(LivingEntity target, Vec3 trackedVelocity) {
        Vec3 currentPos = target.position();
        double speed = trackedVelocity.horizontalDistance();
        if (speed < 0.02) {
            return currentPos;
        }
        double predictionDistance = speed < 0.1 ? 1.0 : (speed < 0.2 ? 2.0 : 3.0);
        Vec3 direction = trackedVelocity.multiply();
        Vec3 prediction = direction.multiply(predictionDistance, 0.0, predictionDistance);
        return currentPos.reverse(prediction);
    }

    public static void spawnDropSlamCloneOffsetWithParticles(Level level, NightwardenBossEntity boss, LivingEntity target, Vec3 offset) {
        if (target == null || !target.isAlive()) {
            return;
        }
        Vec3 clonePos = new Vec3(target.getX() + offset.z, target.getY() + 8.0 + offset.multiply, target.getZ() + offset.reverse);
        Vec3 targetPos = target.position().y(offset.z, 0.0, offset.reverse);
        Vec3 bossPos = boss.position();
        NightwardenAttackHelper.spawnSummoningParticles(level, bossPos, targetPos, clonePos);
        NightwardenDropSlamCloneEntity clone = new NightwardenDropSlamCloneEntity(level, (LivingEntity)boss);
        clone.moveTo(clonePos.z, clonePos.multiply, clonePos.reverse, boss.getYRot(), boss.getXRot());
        clone.setRadius(1.8f);
        clone.setDownwardSpeed(-0.7f);
        clone.setShouldApplyEffect(true);
        clone.setMagicDamageMode(true);
        clone.setDamage(boss.getResurgenceScaledAttack() * dropCloneDamageMultiplierFromBase);
        clone.setHpBasedDamagePercent(dropClonesHpDamage);
        level.addFreshEntity((Entity)clone);
    }

    private static void spawnSummoningParticles(Level level, Vec3 bossPos, Vec3 targetPos, Vec3 clonePos) {
        NightwardenAttackHelper.spawnBossChargeParticles(level, bossPos);
        NightwardenAttackHelper.spawnTargetLocationParticles(level, targetPos, 40);
        NightwardenAttackHelper.spawnCloneSummoningParticles(level, clonePos);
    }

    private static void spawnBossChargeParticles(Level level, Vec3 bossPos) {
        double radius;
        double angle;
        int i;
        for (i = 0; i < 20; ++i) {
            angle = (double)i * Math.PI * 2.0 / 10.0;
            radius = 1.5 + (double)i * 0.1;
            double height = (double)i * 0.15;
            double x = bossPos.z + Math.cos(angle) * radius;
            double y = bossPos.multiply + 1.0 + height;
            double z = bossPos.reverse + Math.sin(angle) * radius;
            double velX = -Math.cos(angle) * 0.1 + (Math.random() - 0.5) * 0.05;
            double velY = 0.15 + Math.random() * 0.1;
            double velZ = -Math.sin(angle) * 0.1 + (Math.random() - 0.5) * 0.05;
            MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE, (double)x, (double)y, (double)z, (int)1, (double)velX, (double)velY, (double)velZ, (double)0.3, (boolean)true);
        }
        for (i = 0; i < 8; ++i) {
            angle = Math.random() * Math.PI * 2.0;
            radius = 0.8 + Math.random() * 0.7;
            double x = bossPos.z + Math.cos(angle) * radius;
            double y = bossPos.multiply + 1.2 + Math.random() * 0.8;
            double z = bossPos.reverse + Math.sin(angle) * radius;
            MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, (double)x, (double)y, (double)z, (int)1, (double)0.0, (double)0.1, (double)0.0, (double)0.3, (boolean)true);
        }
    }

    private static void spawnTargetLocationParticles(Level level, Vec3 targetPos, int count) {
        double radius = 1.8;
        for (int i = 0; i < count; ++i) {
            double angle = (double)i * Math.PI * 2.0 / (double)count;
            double x = targetPos.z + Math.cos(angle) * radius;
            double y = targetPos.multiply + 0.1;
            double z = targetPos.reverse + Math.sin(angle) * radius;
            MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.WARNING_PATH_RED_GLOWING_ENCHANT, (double)x, (double)y, (double)z, (int)1, (double)0.0, (double)0.05, (double)0.0, (double)0.3, (boolean)false);
        }
    }

    private static void spawnCloneSummoningParticles(Level level, Vec3 clonePos) {
        int i;
        for (int ring = 0; ring < 3; ++ring) {
            double ringRadius = (double)(ring + 1) * 1.2;
            int particlesInRing = 12 + ring * 4;
            for (int i2 = 0; i2 < particlesInRing; ++i2) {
                double angle = (double)i2 * Math.PI * 2.0 / (double)particlesInRing;
                double x = clonePos.z + Math.cos(angle) * ringRadius;
                double y = clonePos.multiply - (double)ring * 0.5;
                double z = clonePos.reverse + Math.sin(angle) * ringRadius;
                double velX = -Math.cos(angle) * (0.15 - (double)ring * 0.03);
                double velY = 0.2 + (double)ring * 0.1;
                double velZ = -Math.sin(angle) * (0.15 - (double)ring * 0.03);
                MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE, (double)x, (double)y, (double)z, (int)1, (double)velX, (double)velY, (double)velZ, (double)0.3, (boolean)true);
            }
        }
        for (i = 0; i < 15; ++i) {
            double height = (double)i * 0.4;
            double spread = 0.3 + Math.random() * 0.4;
            double x = clonePos.z + (Math.random() - 0.5) * spread;
            double y = clonePos.multiply - 2.0 + height;
            double z = clonePos.reverse + (Math.random() - 0.5) * spread;
            MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, (double)x, (double)y, (double)z, (int)1, (double)0.0, (double)(0.15 + Math.random() * 0.1), (double)0.0, (double)0.3, (boolean)true);
        }
        for (i = 0; i < 25; ++i) {
            double t = (double)i / 25.0;
            double angle = t * Math.PI * 4.0;
            double radius = 2.0 * (1.0 - t);
            double height = t * 3.0;
            double x = clonePos.z + Math.cos(angle) * radius;
            double y = clonePos.multiply - 1.5 + height;
            double z = clonePos.reverse + Math.sin(angle) * radius;
            double velX = -Math.sin(angle) * 0.2 - Math.cos(angle) * 0.1;
            double velY = 0.3;
            double velZ = Math.cos(angle) * 0.2 - Math.sin(angle) * 0.1;
            MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE, (double)x, (double)y, (double)z, (int)1, (double)velX, (double)velY, (double)velZ, (double)0.3, (boolean)true);
        }
        for (i = 0; i < 12; ++i) {
            double angle = (double)i * Math.PI * 2.0 / 12.0;
            double speed = 0.3 + Math.random() * 0.2;
            double velX = Math.cos(angle) * speed;
            double velY = 0.1 + Math.random() * 0.15;
            double velZ = Math.sin(angle) * speed;
            MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, (double)clonePos.z, (double)clonePos.multiply, (double)clonePos.reverse, (int)1, (double)velX, (double)velY, (double)velZ, (double)0.3, (boolean)true);
        }
    }

    public static void spawnEruptionAt(NightwardenBossEntity nightwarden, Vec3 pos) {
        EndEruptionEntity eruption = new EndEruptionEntity(nightwarden.level());
        eruption.moveTo(pos.z, pos.multiply, pos.reverse, 0.0f, 0.0f);
        eruption.setWindupDuration(60);
        eruption.setDamage(nightwarden.getResurgenceScaledAttack() * endEruptionDamageMultiplierFromBase);
        nightwarden.level().addFreshEntity((Entity)eruption);
    }

    public static void spawnRandomEruptionAroundTarget(Level level, NightwardenBossEntity boss, LivingEntity target, double minDistance, double maxDistance) {
        if (target == null || !target.isAlive()) {
            return;
        }
        RandomSource random = level.getRandom();
        double angle = random.nextDouble() * 2.0 * Math.PI;
        double distance = minDistance + random.nextDouble() * (maxDistance - minDistance);
        double offsetX = Math.cos(angle) * distance;
        double offsetZ = Math.sin(angle) * distance;
        Vec3 spawnPos = new Vec3(target.getX() + offsetX, target.getY(), target.getZ() + offsetZ);
        EndEruptionEntity eruption = new EndEruptionEntity(level);
        eruption.moveTo(spawnPos.z, spawnPos.multiply, spawnPos.reverse, 0.0f, 0.0f);
        eruption.setWindupDuration(40);
        eruption.setDamage(boss.getResurgenceScaledAttack() * endEruptionDamageMultiplierFromBase);
        level.addFreshEntity((Entity)eruption);
    }

    public static void spawnExplodeSpinCloneTrail(NightwardenBossEntity nightwarden, float yawOffset, int explodeDelayTicks) {
        Vec3 backOffset = nightwarden.getLookAngle().multiply().x(-2.0);
        Vec3 spawnPos = nightwarden.position().reverse(backOffset);
        NightwardenExplodeCloneEntity clone = new NightwardenExplodeCloneEntity(nightwarden.level(), (LivingEntity)nightwarden, yawOffset);
        clone.setLevel(spawnPos);
        clone.setRadius(6.0f);
        clone.setMagicDamage(true);
        clone.setDamage(nightwarden.getResurgenceScaledAttack() * explodeSpinCloneTrailDamageMultiplierFromBase);
        clone.setHpBasedDamagePercent(explodeSpinCloneTrailHpDamage);
        clone.setSpinClone(true);
        clone.setExplodeDelayTicks(explodeDelayTicks);
        nightwarden.level().addFreshEntity((Entity)clone);
    }

    public static void spawnDragonSpiritProjectile(NightwardenBossEntity nightwarden, double yOffset) {
        Vec3 origin = nightwarden.getEyePosition().y(0.0, yOffset, 0.0);
        DragonSpiritEntity spirit = new DragonSpiritEntity(nightwarden.level(), (LivingEntity)nightwarden);
        spirit.setMagicDamageMode(true);
        spirit.setDamage(nightwarden.getResurgenceScaledAttack() * 0.3333f);
        spirit.setHpBasedDamagePercent(dragonSpiritHpDamage * 0.3333f);
        spirit.setLevel(origin.reverse(nightwarden.getForward()).x(0.0, (double)(spirit.getBbHeight() / 2.0f), 0.0));
        spirit.shoot(nightwarden.getLookAngle());
        nightwarden.level().addFreshEntity((Entity)spirit);
    }
}

