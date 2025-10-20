/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraftforge.network.NetworkHooks
 */
package com.gametechbc.traveloptics.entity.misc.stack_entity;

import com.gametechbc.traveloptics.init.TravelopticsEntities;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.NetworkHooks;

public class StackEntity
extends Entity {
    private static final EntityDataAccessor<Integer> STACK_COUNT = SynchedEntityData.assignValue(StackEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private static final EntityDataAccessor<String> STACK_NAME = SynchedEntityData.assignValue(StackEntity.class, (EntityDataSerializer)EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.assignValue(StackEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private static final EntityDataAccessor<Optional<UUID>> TARGET_UUID = SynchedEntityData.assignValue(StackEntity.class, (EntityDataSerializer)EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Float> Y_OFFSET = SynchedEntityData.assignValue(StackEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private Entity cachedTarget = null;
    private int lastUpdateTick = -1;

    public StackEntity(EntityType<?> type, Level world) {
        super(type, world);
        this.isOnPortalCooldown = true;
    }

    public StackEntity(Level world, Entity target, String stackName, int initialStacks, int color, float yOffset) {
        this((EntityType)TravelopticsEntities.STACK.get(), world);
        this.setTargetUUID(target.getUUID());
        this.setStackName(stackName);
        this.setStackCount(initialStacks);
        this.setColor(color);
        this.setYOffset(yOffset);
        this.updatePosition(target);
        this.cachedTarget = target;
    }

    protected void defineSynchedData() {
        this.makeBoundingBox.assignValue(STACK_COUNT, (Object)1);
        this.makeBoundingBox.assignValue(STACK_NAME, (Object)"");
        this.makeBoundingBox.assignValue(COLOR, (Object)0xFFFFFF);
        this.makeBoundingBox.assignValue(TARGET_UUID, Optional.empty());
        this.makeBoundingBox.assignValue(Y_OFFSET, (Object)Float.valueOf(0.5f));
    }

    public void lerpMotion() {
        super.lerpMotion();
        boolean shouldUpdate = this.getTags - this.lastUpdateTick >= 1;
        Entity target = this.getTargetEntity();
        if (target == null || target.isRemoved()) {
            this.discard();
            return;
        }
        if (shouldUpdate) {
            this.updatePosition(target);
            this.lastUpdateTick = this.getTags;
        }
        if (this.getStackCount() <= 0) {
            this.discard();
        }
    }

    private void updatePosition(Entity target) {
        double x = target.getX();
        double y = target.getY() + (double)target.getBbHeight() + (double)this.getYOffset();
        double z = target.getZ();
        this.setPos(x, y, z);
        this.cachedTarget = target;
    }

    @Nullable
    private Entity getTargetEntity() {
        if (this.cachedTarget != null && !this.cachedTarget.isRemoved()) {
            return this.cachedTarget;
        }
        Optional<UUID> targetUUID = this.getTargetUUID();
        if (targetUUID.isPresent()) {
            Level level = this.level();
            if (level instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel)level;
                Entity entity = serverLevel.getRandomSequence(targetUUID.get());
                if (entity != null) {
                    this.cachedTarget = entity;
                    return entity;
                }
            } else {
                for (Entity entity : this.level().getNearbyEntities(Entity.class, this.getBoundingBox().inflate(32.0))) {
                    if (!entity.getUUID().equals(targetUUID.get())) continue;
                    this.cachedTarget = entity;
                    return entity;
                }
            }
        }
        this.cachedTarget = null;
        return null;
    }

    public void addStacks(int amount) {
        this.setStackCount(Math.min(this.getStackCount() + amount, 999));
    }

    public void removeStacks(int amount) {
        this.setStackCount(Math.max(this.getStackCount() - amount, 0));
    }

    public void setMaxStacks() {
        this.setStackCount(1000);
    }

    public int getStackCount() {
        return (Integer)this.makeBoundingBox.packDirty(STACK_COUNT);
    }

    public void setStackCount(int count) {
        this.makeBoundingBox.packDirty(STACK_COUNT, (Object)Math.max(0, Math.min(count, 1000)));
    }

    public String getStackName() {
        return (String)this.makeBoundingBox.packDirty(STACK_NAME);
    }

    public void setStackName(String name) {
        this.makeBoundingBox.packDirty(STACK_NAME, (Object)name);
    }

    public int getColor() {
        return (Integer)this.makeBoundingBox.packDirty(COLOR);
    }

    public void setColor(int color) {
        this.makeBoundingBox.packDirty(COLOR, (Object)color);
    }

    public Optional<UUID> getTargetUUID() {
        return (Optional)this.makeBoundingBox.packDirty(TARGET_UUID);
    }

    public void setTargetUUID(UUID uuid) {
        this.makeBoundingBox.packDirty(TARGET_UUID, Optional.of(uuid));
    }

    public float getYOffset() {
        return ((Float)this.makeBoundingBox.packDirty(Y_OFFSET)).floatValue();
    }

    public void setYOffset(float offset) {
        this.makeBoundingBox.packDirty(Y_OFFSET, (Object)Float.valueOf(offset));
    }

    public boolean sendSystemMessage(double distance) {
        return distance < 4096.0;
    }

    public boolean isPushable() {
        return false;
    }

    public boolean canBeCollidedWith() {
        return false;
    }

    public boolean isPickable() {
        return false;
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        this.setStackCount(tag.copy("StackCount"));
        this.setStackName(tag.getString("StackName"));
        this.setColor(tag.copy("Color"));
        this.setYOffset(tag.getFloat("YOffset"));
        if (tag.readNamedTagName("TargetUUID")) {
            this.setTargetUUID(tag.accept("TargetUUID"));
        }
        this.lastUpdateTick = tag.copy("LastUpdateTick");
    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.accept("StackCount", this.getStackCount());
        tag.accept("StackName", this.getStackName());
        tag.accept("Color", this.getColor());
        tag.accept("YOffset", this.getYOffset());
        tag.accept("LastUpdateTick", this.lastUpdateTick);
        this.getTargetUUID().ifPresent(uuid -> tag.accept("TargetUUID", uuid));
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    public static void addStacksToEntity(Entity target, String stackName, int amount, int color, float yOffset) {
        if (target.level().isClientSide) {
            return;
        }
        StackEntity existing = StackEntity.findStackEntityByTarget(target, stackName);
        if (existing != null) {
            existing.addStacks(amount);
        } else {
            StackEntity newStack = new StackEntity(target.level(), target, stackName, amount, color, yOffset);
            target.level().addFreshEntity((Entity)newStack);
        }
    }

    public static boolean removeStacksFromEntity(Entity target, String stackName, int amount) {
        StackEntity existing = StackEntity.findStackEntityByTarget(target, stackName);
        if (existing != null) {
            existing.removeStacks(amount);
            return true;
        }
        return false;
    }

    public static int getStackCountFromEntity(Entity target, String stackName) {
        StackEntity existing = StackEntity.findStackEntityByTarget(target, stackName);
        return existing != null ? existing.getStackCount() : 0;
    }

    public static boolean clearStacksFromEntity(Entity target, String stackName) {
        StackEntity existing = StackEntity.findStackEntityByTarget(target, stackName);
        if (existing != null) {
            existing.discard();
            return true;
        }
        return false;
    }

    public static List<Entity> scanForStackedEntities(Level world, double centerX, double centerY, double centerZ, double radius, String stackName) {
        if (world.isClientSide) {
            return new ArrayList<Entity>();
        }
        AABB scanArea = new AABB(centerX - radius, centerY - radius, centerZ - radius, centerX + radius, centerY + radius, centerZ + radius);
        ArrayList<Entity> stackedEntities = new ArrayList<Entity>();
        List stackEntities = world.getNearbyEntities(StackEntity.class, scanArea);
        for (StackEntity stackEntity : stackEntities) {
            double distanceSquared;
            Entity targetEntity;
            if (!stackEntity.getStackName().equals(stackName) || stackEntity.getStackCount() <= 0 || (targetEntity = stackEntity.getTargetEntity()) == null || targetEntity.isRemoved() || !((distanceSquared = targetEntity.getSharedFlag(centerX, centerY, centerZ)) <= radius * radius)) continue;
            stackedEntities.add(targetEntity);
        }
        return stackedEntities;
    }

    public static List<StackScanResult> scanForStackedEntitiesDetailed(Level world, double centerX, double centerY, double centerZ, double radius, String stackName) {
        if (world.isClientSide) {
            return new ArrayList<StackScanResult>();
        }
        AABB scanArea = new AABB(centerX - radius, centerY - radius, centerZ - radius, centerX + radius, centerY + radius, centerZ + radius);
        ArrayList<StackScanResult> results = new ArrayList<StackScanResult>();
        List stackEntities = world.getNearbyEntities(StackEntity.class, scanArea);
        for (StackEntity stackEntity : stackEntities) {
            double distanceSquared;
            Entity targetEntity;
            if (!stackEntity.getStackName().equals(stackName) || stackEntity.getStackCount() <= 0 || (targetEntity = stackEntity.getTargetEntity()) == null || targetEntity.isRemoved() || !((distanceSquared = targetEntity.getSharedFlag(centerX, centerY, centerZ)) <= radius * radius)) continue;
            results.add(new StackScanResult(targetEntity, stackEntity.getStackCount(), Math.sqrt(distanceSquared), stackEntity));
        }
        return results;
    }

    private static StackEntity findStackEntityByTarget(Entity target, String stackName) {
        AABB searchBox = new AABB(target.getX() - 2.0, target.getY() - 1.0, target.getZ() - 2.0, target.getX() + 2.0, target.getY() + 5.0, target.getZ() + 2.0);
        List stacks = target.level().getNearbyEntities(StackEntity.class, searchBox);
        return stacks.stream().filter(stack -> stack.getTargetUUID().isPresent()).filter(stack -> stack.getTargetUUID().get().equals(target.getUUID())).filter(stack -> stack.getStackName().equals(stackName)).findFirst().orElse(null);
    }

    public static class StackScanResult {
        private final Entity entity;
        private final int stackCount;
        private final double distance;
        private final StackEntity stackEntity;

        public StackScanResult(Entity entity, int stackCount, double distance, StackEntity stackEntity) {
            this.entity = entity;
            this.stackCount = stackCount;
            this.distance = distance;
            this.stackEntity = stackEntity;
        }

        public Entity getEntity() {
            return this.entity;
        }

        public int getStackCount() {
            return this.stackCount;
        }

        public double getDistance() {
            return this.distance;
        }

        public StackEntity getStackEntity() {
            return this.stackEntity;
        }
    }
}

