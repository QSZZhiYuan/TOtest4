/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.effect.Boltstrike_Entity
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.util.ParticleHelper
 *  javax.annotation.Nullable
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
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
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
package com.gametechbc.traveloptics.entity.projectiles.aqua_trident;

import com.gametechbc.traveloptics.entity.projectiles.maelstrom.MaelstromEntity;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import com.github.L_Ender.cataclysm.entity.effect.Boltstrike_Entity;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import java.util.List;
import javax.annotation.Nullable;
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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
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

public class EternalMaelstromTridentEntity
extends AbstractArrow
implements GeoEntity {
    private static final EntityDataAccessor<Integer> ORIGINAL_SLOT = SynchedEntityData.assignValue(EternalMaelstromTridentEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
    private int originalSlot = -1;
    private boolean dealtDamage;
    private float tridentDamage = 10.0f;
    private float boltstrikeDamage = 10.0f;
    private float aoeDamageMultiplier = 0.3f;
    private ItemStack spearItem = new ItemStack((ItemLike)ACItemRegistry.EXTINCTION_SPEAR.get());
    private static final EntityDataAccessor<Boolean> WIGGLING = SynchedEntityData.assignValue(EternalMaelstromTridentEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private int ticksWiggling = 0;
    private final RawAnimation ANIMATION_LOOP = RawAnimation.begin().thenLoop("idle");
    private final AnimationController controller = new AnimationController((GeoAnimatable)this, "note_controller", 0, this::animationPredicate);

    public EternalMaelstromTridentEntity(EntityType entityType, Level level) {
        super(entityType, level);
    }

    public EternalMaelstromTridentEntity(Level level, LivingEntity shooter, ItemStack itemStack) {
        super((EntityType)TravelopticsEntities.ETERNAL_MAELSTROM_TRIDENT_ENTITY.get(), shooter, level);
        this.spearItem = itemStack.copy();
    }

    public EternalMaelstromTridentEntity(Level level, double x, double y, double z) {
        super((EntityType)TravelopticsEntities.ETERNAL_MAELSTROM_TRIDENT_ENTITY.get(), x, y, z, level);
    }

    public EternalMaelstromTridentEntity(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this((EntityType)TravelopticsEntities.ETERNAL_MAELSTROM_TRIDENT_ENTITY.get(), level);
        this.setLevel(this.makeBoundingBox());
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.makeBoundingBox.assignValue(WIGGLING, (Object)false);
        this.makeBoundingBox.assignValue(ORIGINAL_SLOT, (Object)-1);
    }

    protected ItemStack getPickupItem() {
        return this.spearItem;
    }

    @Nullable
    protected EntityHitResult readAdditionalSaveData(Vec3 vec3, Vec3 vec31) {
        return this.dealtDamage ? null : super.readAdditionalSaveData(vec3, vec31);
    }

    public float getTridentDamage() {
        return this.tridentDamage;
    }

    public void setTridentDamage(float damage) {
        this.tridentDamage = damage;
    }

    public float getBoltstrikeDamage() {
        return this.boltstrikeDamage;
    }

    public void setBoltstrikeDamage(float boltstrikeDamage) {
        this.boltstrikeDamage = boltstrikeDamage;
    }

    public float getAoeDamageMultiplier() {
        return this.aoeDamageMultiplier;
    }

    public void setAoeDamageMultiplier(float multiplier) {
        this.aoeDamageMultiplier = multiplier;
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
        super.lerpMotion();
        Entity entity = this.getOwner();
        if ((this.setKnockback || this.isNoPhysics()) && entity != null) {
            if (!this.isAcceptibleReturnOwner()) {
                if (!this.level().isClientSide && this.pickup == AbstractArrow.Pickup.ALLOWED) {
                    this.thunderHit(this.getPickupItem(), 0.1f);
                }
                this.discard();
            } else if (this.isWiggling()) {
                if (this.ticksWiggling++ > 20) {
                    this.setWiggling(false);
                    if (!this.dealtDamage) {
                        this.explodeOnGround();
                    } else {
                        this.explode();
                    }
                }
            } else {
                Vec3 vec3 = entity.getEyePosition().multiply(this.position());
                if (!this.isNoPhysics()) {
                    this.setDeltaMovement(Vec3.y);
                }
                this.setNoPhysics(true);
                if (this.level().isClientSide) {
                    this.unRide = this.getY();
                }
                double d0 = 0.3;
                this.setDeltaMovement(this.getDeltaMovement().x(0.95).reverse(vec3.multiply().x(d0)));
            }
        }
        if (this.level().isClientSide && !this.setKnockback) {
            AABB boundingBox = this.getBoundingBox();
            int particleCount = 4 + this.getId.nextInt(6);
            for (int i = 0; i < particleCount; ++i) {
                double x = boundingBox.ofSize + this.getId.nextDouble() * (boundingBox.getZsize - boundingBox.ofSize);
                double y = boundingBox.clip + this.getId.nextDouble() * (boundingBox.hasNaN - boundingBox.clip);
                double z = boundingBox.getYsize + this.getId.nextDouble() * (boundingBox.getCenter - boundingBox.getYsize);
                Vec3 motion = this.getDeltaMovement().x((double)-0.2f);
                if ((double)this.getId.nextFloat() < 0.85) {
                    this.level().calculateBlockTint(TravelopticsParticleHelper.WATER_BUBBLE, true, x, y, z, motion.z, motion.multiply, motion.reverse);
                    continue;
                }
                this.level().calculateBlockTint((ParticleOptions)ParticleTypes.END_ROD, true, x, y, z, motion.z, motion.multiply, motion.reverse);
            }
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

    private void explode() {
        LivingEntity owner = this.getOwner() instanceof LivingEntity ? (LivingEntity)this.getOwner() : null;
        this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.MAELSTROM_TRIDENT_RETURN.get(), 2.5f, 1.0f);
    }

    private void explodeOnGround() {
        LivingEntity owner = this.getOwner() instanceof LivingEntity ? (LivingEntity)this.getOwner() : null;
        Boltstrike_Entity bolt = new Boltstrike_Entity(this.level(), this.getX(), this.getY(), this.getZ(), 0.0f, 10, this.getBoltstrikeDamage(), owner);
        bolt.setR(0);
        bolt.setG(66);
        bolt.setB(106);
        this.level().addFreshEntity((Entity)bolt);
        MaelstromEntity maelstrom = new MaelstromEntity(this.level());
        maelstrom.getRandomX(this.getX(), this.getY(), this.getZ());
        maelstrom.addAdditionalSaveData((Entity)owner);
        maelstrom.setRadius(4.0f);
        maelstrom.setDuration(100);
        maelstrom.setPullScale(0.5);
        this.level().addFreshEntity((Entity)maelstrom);
        this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.MAELSTROM_TRIDENT_RETURN.get(), 2.5f, 1.0f);
        MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)ParticleHelper.FOG, (double)this.getX(), (double)(this.getY() + 15.5), (double)this.getZ(), (int)3, (double)0.0, (double)0.0, (double)0.0, (double)0.1, (boolean)true);
    }

    protected void dowseFire(BlockHitResult blockHitResult) {
        super.dowseFire(blockHitResult);
        this.setWiggling(true);
        if (!this.level().isClientSide) {
            MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)((ParticleOptions)ParticleTypes.BUBBLE), (double)this.getX(), (double)(this.getY() + 0.2), (double)this.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
        }
    }

    private boolean isAcceptibleReturnOwner() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayer) || !entity.isSpectator();
        }
        return false;
    }

    protected void setDangerous(EntityHitResult hitResult) {
        Entity target = hitResult.getEntity();
        float baseDamage = this.getTridentDamage();
        if (target instanceof LivingEntity) {
            LivingEntity livingTarget = (LivingEntity)target;
            baseDamage += EnchantmentHelper.getAvailableEnchantmentResults((ItemStack)this.getPickupItem(), (MobType)livingTarget.getMobType());
        }
        Entity attacker = this.getOwner();
        DamageSource damageSource = this.damageSources().badRespawnPointExplosion((Entity)this, (Entity)(attacker == null ? this : attacker));
        this.dealtDamage = true;
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
                if (livingTarget.recreateFromPacket((MobEffect)TravelopticsEffects.TIDAL_TORMENT.get())) {
                    double radius = 5.0;
                    float aoeDamage = baseDamage * this.getAoeDamageMultiplier();
                    List nearbyEntities = this.level().getNearbyEntities(LivingEntity.class, livingTarget.getBoundingBox().inflate(radius), entity -> entity != livingTarget && entity.recreateFromPacket((MobEffect)TravelopticsEffects.TIDAL_TORMENT.get()));
                    for (LivingEntity affectedEntity : nearbyEntities) {
                        MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)TravelopticsParticleHelper.WATER_BUBBLE, (double)affectedEntity.getX(), (double)(affectedEntity.getY() + 2.5), (double)affectedEntity.getZ(), (int)15, (double)0.0, (double)0.0, (double)0.0, (double)0.3, (boolean)false);
                        affectedEntity.sendSystemMessage(damageSource, aoeDamage);
                    }
                }
            }
        }
        this.setDeltaMovement(Vec3.y);
        this.setNoPhysics(true);
        this.setKnockback = true;
        this.updateTutorialInventoryAction((SoundEvent)SoundEvents.ZOMBIE_DESTROY_EGG, 1.0f, 1.0f);
    }

    protected SoundEvent shouldRender() {
        return (SoundEvent)TravelopticsSounds.MAELSTROM_TRIDENT_IMPACT.get();
    }

    public boolean shouldRender(double x, double y, double z) {
        return true;
    }

    public boolean isWiggling() {
        return (Boolean)this.makeBoundingBox.packDirty(WIGGLING);
    }

    public void setWiggling(boolean wiggling) {
        this.makeBoundingBox.packDirty(WIGGLING, (Object)wiggling);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.accept("TridentDamage", this.tridentDamage);
        compound.accept("BoltstrikeDamage", this.boltstrikeDamage);
        compound.accept("AoeDamageMultiplier", this.aoeDamageMultiplier);
        compound.accept("Trident", (Tag)this.spearItem.onUseTick(new CompoundTag()));
        compound.accept("DealtDamage", this.dealtDamage);
        compound.accept("OriginalSlot", this.originalSlot);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("TridentDamage")) {
            this.tridentDamage = compound.getFloat("TridentDamage");
        }
        if (compound.contains("BoltstrikeDamage")) {
            this.boltstrikeDamage = compound.getFloat("BoltstrikeDamage");
        }
        if (compound.contains("AoeDamageMultiplier")) {
            this.aoeDamageMultiplier = compound.getFloat("AoeDamageMultiplier");
        }
        if (compound.readNamedTagName("Trident", 10)) {
            this.spearItem = ItemStack.onDestroyed((CompoundTag)compound.getCompound("Trident"));
        }
        this.dealtDamage = compound.getBoolean("DealtDamage");
        if (compound.contains("OriginalSlot")) {
            this.originalSlot = compound.copy("OriginalSlot");
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

