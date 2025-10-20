/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.SchoolType
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.projectile.AbstractArrow$Pickup
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.network.NetworkHooks
 *  net.minecraftforge.network.PlayMessages$SpawnEntity
 *  software.bernie.geckolib.animatable.GeoEntity
 *  software.bernie.geckolib.core.animatable.GeoAnimatable
 *  software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
 *  software.bernie.geckolib.core.animation.AnimatableManager$ControllerRegistrar
 *  software.bernie.geckolib.core.animation.AnimationController
 *  software.bernie.geckolib.core.animation.AnimationState
 *  software.bernie.geckolib.core.animation.RawAnimation
 *  software.bernie.geckolib.core.object.PlayState
 *  software.bernie.geckolib.util.GeckoLibUtil
 */
package com.gametechbc.traveloptics.entity.projectiles.stellothorn_projectile;

import com.gametechbc.traveloptics.api.particle.AdvancedSphereParticleManager;
import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_drop_slam_clone.NightwardenDropSlamCloneEntity;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class StellothornProjectileEntity
extends AbstractArrow
implements GeoEntity {
    private static final EntityDataAccessor<Boolean> RETURNING = SynchedEntityData.assignValue(StellothornProjectileEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> ENABLE_HEAL_ON_RETURN = SynchedEntityData.assignValue(StellothornProjectileEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> ORIGINAL_SLOT = SynchedEntityData.assignValue(StellothornProjectileEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
    private int originalSlot = -1;
    private boolean healed = false;
    private final List<UUID> victims = new ArrayList<UUID>();
    private float tridentDamage = 10.0f;
    private boolean triggeredEruption = false;
    private float cloneDamage = 6.0f;
    private boolean triggerAoEOnReturn = false;
    private boolean aoeTriggered = false;
    private ItemStack spearItem = new ItemStack((ItemLike)TravelopticsItems.STELLOTHORN.get());
    private final RawAnimation ANIMATION_LOOP = RawAnimation.begin().thenLoop("stellothorn_projectile_idle");
    private final AnimationController controller = new AnimationController((GeoAnimatable)this, "scythe_controller", 0, this::animationPredicate);

    public StellothornProjectileEntity(EntityType entityType, Level level) {
        super(entityType, level);
    }

    public StellothornProjectileEntity(Level level, LivingEntity shooter, ItemStack itemStack) {
        super((EntityType)TravelopticsEntities.STELLOTHORN_PROJECTILE.get(), shooter, level);
        this.spearItem = itemStack.copy();
    }

    public StellothornProjectileEntity(Level level, double x, double y, double z) {
        super((EntityType)TravelopticsEntities.STELLOTHORN_PROJECTILE.get(), x, y, z, level);
    }

    public StellothornProjectileEntity(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this((EntityType)TravelopticsEntities.STELLOTHORN_PROJECTILE.get(), level);
        this.setLevel(this.makeBoundingBox());
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.makeBoundingBox.assignValue(RETURNING, (Object)false);
        this.makeBoundingBox.assignValue(ENABLE_HEAL_ON_RETURN, (Object)false);
        this.makeBoundingBox.assignValue(ORIGINAL_SLOT, (Object)-1);
    }

    protected ItemStack getPickupItem() {
        return this.spearItem;
    }

    public boolean isReturning() {
        return (Boolean)this.makeBoundingBox.packDirty(RETURNING);
    }

    public void setReturning(boolean returning) {
        this.makeBoundingBox.packDirty(RETURNING, (Object)returning);
    }

    public boolean isHealOnReturnEnabled() {
        return (Boolean)this.makeBoundingBox.packDirty(ENABLE_HEAL_ON_RETURN);
    }

    public void setHealOnReturnEnabled(boolean enabled) {
        this.makeBoundingBox.packDirty(ENABLE_HEAL_ON_RETURN, (Object)enabled);
    }

    public float getTridentDamage() {
        return this.tridentDamage;
    }

    public void setTridentDamage(float damage) {
        this.tridentDamage = damage;
    }

    public float getCloneDamage() {
        return this.cloneDamage;
    }

    public void setCloneDamage(float cloneDamage) {
        this.cloneDamage = cloneDamage;
    }

    public void setTriggerAoEOnReturn(boolean trigger) {
        this.triggerAoEOnReturn = trigger;
    }

    public boolean isTriggerAoEOnReturn() {
        return this.triggerAoEOnReturn;
    }

    public void setOriginalSlot(int slot) {
        this.originalSlot = slot;
        this.makeBoundingBox.packDirty(ORIGINAL_SLOT, (Object)slot);
    }

    public int getOriginalSlot() {
        return (Integer)this.makeBoundingBox.packDirty(ORIGINAL_SLOT);
    }

    protected float getWaterInertia() {
        return 0.99f;
    }

    public void lerpMotion() {
        Entity owner;
        super.lerpMotion();
        if (!this.isReturning() && this.getTags >= 15) {
            this.beginReturn();
        }
        Entity entity = this.getOwner();
        if ((this.setKnockback || this.isNoPhysics()) && entity != null) {
            if (!this.isAcceptibleReturnOwner()) {
                if (!this.level().isClientSide && this.pickup == AbstractArrow.Pickup.ALLOWED) {
                    this.thunderHit(this.getPickupItem(), 0.1f);
                }
                this.discard();
            } else if (!this.isReturning()) {
                this.beginReturn();
            }
        }
        if (this.isReturning() && (owner = this.getOwner()) != null && this.isAcceptibleReturnOwner()) {
            Vec3 vec3 = owner.getEyePosition().multiply(this.position()).multiply();
            this.setNoPhysics(true);
            this.setDeltaMovement(this.getDeltaMovement().x(0.95).reverse(vec3.x(0.3)));
            if (!this.healed && this.isHealOnReturnEnabled() && !this.victims.isEmpty() && owner instanceof LivingEntity) {
                LivingEntity livingOwner = (LivingEntity)owner;
                float healAmount = livingOwner.getMaxHealth() * 0.15f;
                livingOwner.sendRidingJump(healAmount);
                this.healed = true;
            }
        }
        if (!this.level().isClientSide) {
            AABB boundingBox = this.getBoundingBox();
            for (Entity target : this.level().getEntities((Entity)this, boundingBox)) {
                if (!this.recreateFromPacket(target) || this.victims.contains(target.getUUID())) continue;
                this.damageEntity(target);
            }
        }
        if (this.getTags % 5 == 0) {
            float pitch = 0.9f + this.getId.nextFloat() * 0.2f;
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_SWING.get(), 0.7f, pitch);
        }
        this.spawnParticles();
    }

    private void beginReturn() {
        this.setReturning(true);
        this.setNoPhysics(true);
        this.setKnockback = false;
        if (!this.aoeTriggered && this.triggerAoEOnReturn && !this.level().isClientSide) {
            this.aoeTriggered = true;
            float aoeRadius = 6.0f;
            float aoeDamage = this.getTridentDamage() * 0.5f;
            AABB area = new AABB(this.getX() - (double)aoeRadius, this.getY() - (double)aoeRadius, this.getZ() - (double)aoeRadius, this.getX() + (double)aoeRadius, this.getY() + (double)aoeRadius, this.getZ() + (double)aoeRadius);
            List targets = this.level().getNearbyEntities(LivingEntity.class, area, e -> e.isAlive() && e != this.getOwner());
            for (LivingEntity target : targets) {
                target.sendSystemMessage(this.damageSources().badRespawnPointExplosion((Entity)this, this.getOwner()), aoeDamage);
            }
            if (this.getOwner() != null) {
                this.level().getChunk(null, this.getOwner().getX(), this.getOwner().getY(), this.getOwner().getZ(), (SoundEvent)TravelopticsSounds.END_ERUPTION_BLAST.get(), SoundSource.PLAYERS, 0.8f, 1.0f);
            }
            AdvancedSphereParticleManager.spawnParticles(this.level(), this.getX(), this.getY(), this.getZ(), 70, TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE, ParticleDirection.OUTWARD, 8.0, true);
            MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)new BlastwaveParticleOptions(((SchoolType)SchoolRegistry.ENDER.get()).getTargetingColor(), aoeRadius), (double)this.getX(), (double)(this.getY() + (double)0.165f), (double)this.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
        }
    }

    protected boolean readAdditionalSaveData(Player player) {
        Entity entity = this.getOwner();
        if (entity == null || entity.equals((Object)player)) {
            ItemStack currentItem;
            int originalSlot;
            if (this.pickup == AbstractArrow.Pickup.CREATIVE_ONLY && (originalSlot = this.getOriginalSlot()) >= 0 && originalSlot < 9 && ItemStack.onUseTick((ItemStack)(currentItem = player.getInventory().setItems(originalSlot)), (ItemStack)this.getPickupItem())) {
                player.recreateFromPacket((Entity)this, 1);
                this.discard();
                return true;
            }
            originalSlot = this.getOriginalSlot();
            if (originalSlot >= 0 && originalSlot < 9) {
                currentItem = player.getInventory().setItems(originalSlot);
                if (currentItem.onUseTick()) {
                    player.getInventory().setItems(originalSlot, this.getPickupItem());
                    player.recreateFromPacket((Entity)this, 1);
                    this.discard();
                    return true;
                }
                if (ItemStack.onUseTick((ItemStack)currentItem, (ItemStack)this.getPickupItem()) && currentItem.getCount() < currentItem.grow()) {
                    currentItem.grow(1);
                    player.recreateFromPacket((Entity)this, 1);
                    this.discard();
                    return true;
                }
                ItemStack tridentItem = this.getPickupItem();
                boolean movedItem = false;
                for (int i = 0; i < player.getInventory().removeItemNoUpdate(); ++i) {
                    if (i == originalSlot) continue;
                    ItemStack slotItem = player.getInventory().setItems(i);
                    if (slotItem.onUseTick()) {
                        player.getInventory().setItems(i, currentItem.copy());
                        player.getInventory().setItems(originalSlot, tridentItem);
                        player.recreateFromPacket((Entity)this, 1);
                        this.discard();
                        movedItem = true;
                        break;
                    }
                    if (!ItemStack.onUseTick((ItemStack)slotItem, (ItemStack)currentItem) || slotItem.getCount() + currentItem.getCount() > slotItem.grow()) continue;
                    slotItem.grow(currentItem.getCount());
                    player.getInventory().setItems(originalSlot, tridentItem);
                    player.recreateFromPacket((Entity)this, 1);
                    this.discard();
                    movedItem = true;
                    break;
                }
                if (!movedItem) {
                    player.setLastDeathLocation(currentItem, false);
                    player.getInventory().setItems(originalSlot, tridentItem);
                    player.recreateFromPacket((Entity)this, 1);
                    this.discard();
                }
                return true;
            }
            return super.readAdditionalSaveData(player);
        }
        return false;
    }

    protected void dowseFire(BlockHitResult blockHitResult) {
        super.dowseFire(blockHitResult);
        if (!this.level().isClientSide) {
            // empty if block
        }
    }

    private boolean isAcceptibleReturnOwner() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayer) || !entity.isSpectator();
        }
        return false;
    }

    protected boolean recreateFromPacket(Entity entity) {
        return entity != this.getOwner() && super.recreateFromPacket(entity);
    }

    private void damageEntity(Entity target) {
        if (this.victims.contains(target.getUUID())) {
            return;
        }
        float baseDamage = this.getTridentDamage();
        if (target instanceof LivingEntity) {
            LivingEntity livingTarget = (LivingEntity)target;
            baseDamage += EnchantmentHelper.getAvailableEnchantmentResults((ItemStack)this.getPickupItem(), (MobType)livingTarget.getMobType());
        }
        Entity attacker = this.getOwner();
        DamageSource damageSource = this.damageSources().badRespawnPointExplosion((Entity)this, (Entity)(attacker == null ? this : attacker));
        if (target.sendSystemMessage(damageSource, baseDamage)) {
            if (target.getType() == EntityType.ENDERMAN) {
                return;
            }
            if (target instanceof LivingEntity) {
                LivingEntity livingTarget = (LivingEntity)target;
                if (attacker instanceof LivingEntity) {
                    LivingEntity livingAttacker = (LivingEntity)attacker;
                    EnchantmentHelper.getAvailableEnchantmentResults((LivingEntity)livingTarget, (Entity)livingAttacker);
                    EnchantmentHelper.selectEnchantment((LivingEntity)livingAttacker, (Entity)livingTarget);
                }
                this.readAdditionalSaveData(livingTarget);
            }
            if (!this.triggeredEruption && attacker instanceof LivingEntity) {
                LivingEntity livingAttacker = (LivingEntity)attacker;
                this.triggeredEruption = true;
                NightwardenDropSlamCloneEntity clone = new NightwardenDropSlamCloneEntity(this.level(), livingAttacker);
                clone.moveTo(target.getX(), target.getY() + 6.5, target.getZ(), target.getYRot(), 0.0f);
                clone.setRadius(3.5f);
                clone.setDamage(this.getCloneDamage());
                clone.setHpBasedDamagePercent(0.0f);
                clone.setDownwardSpeed(-0.5f);
                clone.setShouldApplyEffect(true);
                this.level().addFreshEntity((Entity)clone);
            }
            this.victims.add(target.getUUID());
        }
        this.updateTutorialInventoryAction((SoundEvent)SoundEvents.ZOMBIE_DESTROY_EGG, 1.0f, 1.0f);
    }

    protected void setDangerous(EntityHitResult hitResult) {
    }

    public boolean isNoGravity() {
        return true;
    }

    public void spawnParticles() {
        if (this.level().isClientSide) {
            float width = (float)this.getBoundingBox().clip();
            float step = 0.25f;
            float radians = (float)Math.PI / 180 * this.getYRot();
            float speed = 0.1f;
            int totalParticles = (int)(width / step);
            float rareChance = 0.2f;
            for (int i = 0; i < totalParticles; ++i) {
                double x = this.getX();
                double y = this.getY();
                double z = this.getZ();
                double offset = (double)step * ((double)i - (double)totalParticles / 2.0);
                double rotX = offset * Math.cos(radians);
                double rotZ = -offset * Math.sin(radians);
                double dx = Math.random() * (double)speed * 2.0 - (double)speed;
                double dy = Math.random() * (double)speed * 2.0 - (double)speed;
                double dz = Math.random() * (double)speed * 2.0 - (double)speed;
                if (this.getId.nextFloat() < rareChance) {
                    this.level().addDestroyBlockEffect((ParticleOptions)ParticleTypes.END_ROD, x + rotX + dx, y + dy, z + rotZ + dz, dx, dy, dz);
                    continue;
                }
                this.level().addDestroyBlockEffect(TravelopticsParticleHelper.LIGHT_PURPLE_GLOWING_ENCHANT, x + rotX + dx, y + dy, z + rotZ + dz, dx, dy, dz);
            }
        }
    }

    protected SoundEvent shouldRender() {
        return SoundEvents.EMPTY;
    }

    public boolean shouldRender(double x, double y, double z) {
        return true;
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.accept("TridentDamage", this.tridentDamage);
        compound.accept("Trident", (Tag)this.spearItem.onUseTick(new CompoundTag()));
        compound.accept("CloneDamage", this.cloneDamage);
        compound.accept("HealOnReturn", this.isHealOnReturnEnabled());
        compound.accept("HealedOnce", this.healed);
        compound.accept("OriginalSlot", this.originalSlot);
        CompoundTag victimsTag = new CompoundTag();
        for (int i = 0; i < this.victims.size(); ++i) {
            victimsTag.accept("victim_" + i, this.victims.get(i));
        }
        victimsTag.accept("victimCount", this.victims.size());
        compound.accept("Victims", (Tag)victimsTag);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("TridentDamage")) {
            this.tridentDamage = compound.getFloat("TridentDamage");
        }
        if (compound.readNamedTagName("Trident", 10)) {
            this.spearItem = ItemStack.onDestroyed((CompoundTag)compound.getCompound("Trident"));
        }
        if (compound.contains("CloneDamage")) {
            this.cloneDamage = compound.getFloat("CloneDamage");
        }
        if (compound.contains("HealOnReturn")) {
            this.setHealOnReturnEnabled(compound.getBoolean("HealOnReturn"));
        }
        if (compound.contains("HealedOnce")) {
            this.healed = compound.getBoolean("HealedOnce");
        }
        if (compound.contains("OriginalSlot")) {
            this.originalSlot = compound.copy("OriginalSlot");
        }
        if (compound.contains("Victims")) {
            CompoundTag victimsTag = compound.getCompound("Victims");
            int victimCount = victimsTag.copy("victimCount");
            this.victims.clear();
            for (int i = 0; i < victimCount; ++i) {
                if (!victimsTag.readNamedTagName("victim_" + i)) continue;
                this.victims.add(victimsTag.accept("victim_" + i));
            }
        }
    }

    private PlayState animationPredicate(AnimationState<?> event) {
        AnimationController controller = event.getController();
        controller.setAnimation(this.ANIMATION_LOOP);
        return PlayState.CONTINUE;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController[]{this.controller});
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}

