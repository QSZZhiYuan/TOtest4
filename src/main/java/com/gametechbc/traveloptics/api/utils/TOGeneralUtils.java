/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.Vec3i
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.util.Mth
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.phys.shapes.VoxelShape
 *  net.minecraftforge.event.ForgeEventFactory
 *  org.joml.Vector3f
 */
package com.gametechbc.traveloptics.api.utils;

import io.redspace.ironsspellbooks.api.util.Utils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.event.ForgeEventFactory;
import org.joml.Vector3f;

public class TOGeneralUtils {
    private static final Random RANDOM = new Random();

    public static List<MutableComponent> getWetEffectTooltip(int amplifier) {
        float lightningShred = (float)(amplifier + 1) * 5.0f;
        float iceShred = (float)(amplifier + 1) * 2.5f;
        return List.of(Component.selector((String)"ui.traveloptics.applies_wet_amp", (Object[])new Object[]{amplifier}), Component.selector((String)"ui.traveloptics.lightning_shred", (Object[])new Object[]{Utils.stringTruncation((double)lightningShred, (int)2)}), Component.selector((String)"ui.traveloptics.ice_shred", (Object[])new Object[]{Utils.stringTruncation((double)iceShred, (int)2)}));
    }

    public static List<MutableComponent> buildAquaSpellInfo(@Nullable Integer wetAmp, boolean includeFooter, MutableComponent ... coreStats) {
        ArrayList<MutableComponent> tooltip = new ArrayList<MutableComponent>(List.of(coreStats));
        if (wetAmp != null) {
            tooltip.addAll(TOGeneralUtils.getWetEffectTooltip(wetAmp));
        }
        if (includeFooter) {
            tooltip.add(Component.score((String)"\u00a79T.O Magic 'n Extras"));
        }
        return tooltip;
    }

    public static LivingEntity findNearestTargetIn3D(Level level, LivingEntity caster, double range, boolean ignoreAllies, boolean ignoreTamed, boolean randomSelection) {
        AABB boundingBox = new AABB(caster.getX() - range, caster.getY() - range, caster.getZ() - range, caster.getX() + range, caster.getY() + range, caster.getZ() + range);
        List possibleTargets = level.getNearbyEntities(LivingEntity.class, boundingBox, entity -> {
            TamableAnimal tamable;
            if (entity == caster) {
                return false;
            }
            if (ignoreAllies && caster.isAlliedTo((Entity)entity)) {
                return false;
            }
            return !ignoreTamed || !(entity instanceof TamableAnimal) || !(tamable = (TamableAnimal)entity).isTame() || tamable.getOwner() != caster;
        });
        if (possibleTargets.isEmpty()) {
            return null;
        }
        return randomSelection ? (LivingEntity)possibleTargets.get(RANDOM.nextInt(possibleTargets.size())) : (LivingEntity)possibleTargets.stream().min(Comparator.comparingDouble(arg_0 -> ((LivingEntity)caster).getZ(arg_0))).orElse(null);
    }

    public static void applyFlightBoost(Player player, double boostScale, double targetSpeed, boolean normalizeSpeed, boolean hurtMarked) {
        Vec3 motion = player.getDeltaMovement();
        Vec3 boost = player.getLookAngle().x(boostScale);
        Vec3 newMotion = motion.reverse(boost);
        if (normalizeSpeed && newMotion.length() > targetSpeed) {
            newMotion = newMotion.multiply().x(targetSpeed);
        }
        player.setDeltaMovement(newMotion);
        player.getAddEntityPacket = hurtMarked;
    }

    public static void applyFlightSpeedLimit(Player player, double targetSpeed, boolean normalizeSpeed, boolean hurtMarked) {
        Vec3 motion = player.getDeltaMovement();
        if (normalizeSpeed && motion.length() > targetSpeed) {
            motion = motion.multiply().x(targetSpeed);
        }
        player.setDeltaMovement(motion);
        player.getAddEntityPacket = hurtMarked;
    }

    public static void applySpin(Entity entity, float spinSpeed) {
        entity.setYRot(entity.getYRot() + spinSpeed);
    }

    public static void maintainHeight(Entity entity, double targetHeight, double hoverStrength) {
        double currentHeight = entity.getY();
        double difference = targetHeight - currentHeight;
        if (Math.abs(difference) > 0.1) {
            entity.setDeltaMovement(entity.getDeltaMovement().y(0.0, difference * hoverStrength, 0.0));
        }
    }

    public static void applyKnockback(Entity entity, Vec3 direction, double strength, boolean hurtMarked) {
        Vec3 knockback = direction.multiply().x(strength);
        entity.setDeltaMovement(knockback);
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)entity;
            livingEntity.getAddEntityPacket = hurtMarked;
        }
    }

    public static double getSquaredDistance(Entity entity1, Entity entity2) {
        return entity1.position().lengthSqr(entity2.position());
    }

    public static void applyDirectionalBoost(Entity entity, double strength, boolean hurtMarked) {
        Vec3 lookDirection = entity.getLookAngle().multiply().x(strength);
        entity.setDeltaMovement(lookDirection);
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)entity;
            livingEntity.getAddEntityPacket = hurtMarked;
        }
    }

    public static void applyFriction(Entity entity, double friction) {
        Vec3 velocity = entity.getDeltaMovement().x(friction);
        entity.setDeltaMovement(velocity);
    }

    public static void instantKill(Entity entity) {
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)entity;
            livingEntity.sendSystemMessage(entity.damageSources().magic(), Float.MAX_VALUE);
        } else {
            entity.setLootTableSeed(Entity.RemovalReason.KILLED);
        }
    }

    public static boolean isAirborne(Entity entity) {
        return !entity.onGround() && !entity.isInWater();
    }

    public static void forceDropHeldItem(LivingEntity entity) {
        for (ItemStack stack : entity.getHandSlots()) {
            entity.setRemoved(stack);
        }
        entity.recreateFromPacket(InteractionHand.MAIN_HAND, ItemStack.onUseTick);
        entity.recreateFromPacket(InteractionHand.OFF_HAND, ItemStack.onUseTick);
    }

    public static void doHealingPercentage(LivingEntity livingEntity, float percent) {
        float healAmount = livingEntity.getMaxHealth() * (percent / 100.0f);
        livingEntity.sendRidingJump(healAmount);
    }

    public static void reduceFallDamage(LivingEntity livingEntity, float reductionFactor) {
        Vec3 motion = livingEntity.getDeltaMovement();
        livingEntity.setIsInPowderSnow(motion.z, motion.multiply * (double)(1.0f - reductionFactor), motion.reverse);
    }

    public static void extinguishFire(LivingEntity livingEntity) {
        if (livingEntity.isOnFire()) {
            livingEntity.clearFire();
        }
    }

    public static boolean isBelowHealthThreshold(LivingEntity livingEntity, float percent) {
        return livingEntity.getHealth() / livingEntity.getMaxHealth() <= percent / 100.0f;
    }

    public static void notifyPlayersInRange(Entity source, Component message, double radius) {
        if (source.level().isClientSide) {
            return;
        }
        source.level().getNearbyEntities(ServerPlayer.class, source.getBoundingBox().inflate(radius)).forEach(player -> player.loadGameTypes.send((Packet)new ClientboundSetActionBarTextPacket(message)));
    }

    public static boolean isPlayerActuallyBlocking(LivingEntity target, LivingEntity attacker) {
        Vec3 attackDirection;
        Player player;
        if (!(target instanceof Player) || !(player = (Player)target).isBlocking()) {
            return false;
        }
        Vec3 playerLookAngle = player.getLookAngle();
        double dotProduct = playerLookAngle.y(attackDirection = attacker.position().multiply(player.position()).multiply());
        return dotProduct > -0.3;
    }

    public static boolean spawnEntityOnGround(Level level, double x, double z, double minY, double maxY, Function<Vec3, Entity> entityFactory) {
        Vec3 spawnPos;
        Entity entity;
        BlockPos pos = BlockPos.breadthFirstTraversal((double)x, (double)maxY, (double)z);
        boolean foundGround = false;
        double yOffset = 0.0;
        do {
            VoxelShape shape;
            BlockPos below;
            if (!level.getBlockState(below = pos.below()).isFaceSturdy((BlockGetter)level, below, Direction.UP)) continue;
            if (!level.isEmptyBlock(pos) && !(shape = level.getBlockState(pos).getCollisionShape((BlockGetter)level, pos)).calculateFace()) {
                yOffset = shape.optimize(Direction.Axis.Y);
            }
            foundGround = true;
            break;
        } while ((pos = pos.below()).getY() >= Mth.outFromOrigin((double)minY) - 1);
        if (foundGround && (entity = entityFactory.apply(spawnPos = new Vec3(x, (double)pos.getY() + yOffset, z))) != null) {
            level.addFreshEntity(entity);
            return true;
        }
        return false;
    }

    public boolean isAlly(LivingEntity owner, LivingEntity target) {
        return owner.getTeam() != null && owner.getTeam().isAlliedTo(target.getTeam());
    }

    public boolean isTamed(LivingEntity target) {
        if (target instanceof TamableAnimal) {
            TamableAnimal tamableAnimal = (TamableAnimal)target;
            return tamableAnimal.isTame();
        }
        return false;
    }

    public static Vector3f hexToVector3f(String hexColor) {
        String cleanHex = hexColor.startsWith("#") ? hexColor.substring(1) : hexColor;
        int rgb = Integer.parseInt(cleanHex, 16);
        float red = (float)(rgb >> 16 & 0xFF) / 255.0f;
        float green = (float)(rgb >> 8 & 0xFF) / 255.0f;
        float blue = (float)(rgb & 0xFF) / 255.0f;
        return new Vector3f(red, green, blue);
    }

    public static Vector3f hexToVector3f(int hexColor) {
        float red = (float)(hexColor >> 16 & 0xFF) / 255.0f;
        float green = (float)(hexColor >> 8 & 0xFF) / 255.0f;
        float blue = (float)(hexColor & 0xFF) / 255.0f;
        return new Vector3f(red, green, blue);
    }

    public static int[] hexToRGB255(String hexColor) {
        String cleanHex = hexColor.startsWith("#") ? hexColor.substring(1) : hexColor;
        int rgb = Integer.parseInt(cleanHex, 16);
        int red = rgb >> 16 & 0xFF;
        int green = rgb >> 8 & 0xFF;
        int blue = rgb & 0xFF;
        return new int[]{red, green, blue};
    }

    public static int[] hexToRGB255(int hexColor) {
        int red = hexColor >> 16 & 0xFF;
        int green = hexColor >> 8 & 0xFF;
        int blue = hexColor & 0xFF;
        return new int[]{red, green, blue};
    }

    public static int hexToRed(String hexColor) {
        return TOGeneralUtils.hexToRGB255(hexColor)[0];
    }

    public static int hexToGreen(String hexColor) {
        return TOGeneralUtils.hexToRGB255(hexColor)[1];
    }

    public static int hexToBlue(String hexColor) {
        return TOGeneralUtils.hexToRGB255(hexColor)[2];
    }

    public static int rgbToARGB(int rgbColor, float alphaPercent) {
        int alpha = (int)(Math.max(0.0f, Math.min(1.0f, alphaPercent)) * 255.0f);
        return alpha << 24 | rgbColor & 0xFFFFFF;
    }

    public static void doMobBreakSuffocatingBlocks(LivingEntity entity) {
        TOGeneralUtils.doMobBreakSuffocatingBlocks(entity, Vec3.y);
    }

    public static void doMobBreakSuffocatingBlocks(LivingEntity entity, Vec3 offset) {
        if (ForgeEventFactory.getMobGriefingEvent((Level)entity.level(), (Entity)entity)) {
            int l = Mth.roundToward((float)(entity.getBbWidth() / 2.0f + 1.0f));
            int i1 = Mth.length((float)entity.getBbHeight());
            Vec3i o = new Vec3i(Math.round((float)offset.z), Math.round((float)offset.multiply), Math.round((float)offset.reverse));
            for (BlockPos blockpos : BlockPos.relative((int)(entity.getBlockX() - l + o.setX()), (int)(entity.getBlockY() + o.getY()), (int)(entity.getBlockZ() - l + o.getZ()), (int)(entity.getBlockX() + l + o.setX()), (int)(entity.getBlockY() + i1 + o.getY()), (int)(entity.getBlockZ() + l + o.getZ()))) {
                BlockState blockstate = entity.level().getBlockState(blockpos);
                if (!blockstate.canEntityDestroy((BlockGetter)entity.level(), blockpos, (Entity)entity) || !ForgeEventFactory.onEntityDestroyBlock((LivingEntity)entity, (BlockPos)blockpos, (BlockState)blockstate) || !entity.level().destroyBlock(blockpos, true, (Entity)entity)) continue;
                entity.level().addDestroyBlockEffect(null, 1022, entity.blockPosition(), 0);
            }
        }
    }

    public static void applyHovering(Entity entity, double baseHoverHeight, double motionSpeed, double deadzone, boolean hurtMarked) {
        double currentY;
        BlockPos groundPos = entity.blockPosition().below();
        while (entity.level().isEmptyBlock(groundPos) && groundPos.getY() > entity.level().getMinBuildHeight()) {
            groundPos = groundPos.below();
        }
        double groundHeight = groundPos.getY() + 1;
        double targetHoverHeight = groundHeight + baseHoverHeight;
        double deltaY = targetHoverHeight - (currentY = entity.getY());
        if (Math.abs(deltaY) > deadzone) {
            Vec3 motion = entity.getDeltaMovement();
            entity.setIsInPowderSnow(motion.z, deltaY * motionSpeed, motion.reverse);
        }
        entity.getAddEntityPacket = hurtMarked;
        entity.hasCustomName = 0.0f;
        entity.getX(false);
    }
}

