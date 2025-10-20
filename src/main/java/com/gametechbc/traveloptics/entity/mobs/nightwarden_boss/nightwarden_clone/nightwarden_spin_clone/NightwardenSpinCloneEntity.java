/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.SchoolType
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible
 *  io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions
 *  javax.annotation.Nullable
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
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
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_spin_clone;

import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import java.util.Collections;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class NightwardenSpinCloneEntity
extends LivingEntity
implements GeoEntity,
AntiMagicSusceptible {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
    private float hpBasedDamagePercent = 0.0f;
    private static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.assignValue(NightwardenSpinCloneEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_DAMAGE = SynchedEntityData.assignValue(NightwardenSpinCloneEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> MAGIC_DAMAGE_MODE = SynchedEntityData.assignValue(NightwardenSpinCloneEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private int age = 0;
    private int maxAge = 100;
    private boolean playedSqueezeAnimation = false;
    private float forwardSpeed = 0.25f;
    private boolean shouldMoveForward = true;
    private UUID summonerUUID;
    private LivingEntity cachedSummoner;
    private final RawAnimation SPIN_ANIMATION = RawAnimation.begin().thenLoop("nightwarden_clone_spin");
    private final AnimationController<NightwardenSpinCloneEntity> controller = new AnimationController((GeoAnimatable)this, "nightwarden_clone_spin_controller", 0, this::animationPredicate);
    private final RawAnimation SQUEEZE_ANIMATION = RawAnimation.begin().thenPlay("nightwarden_clone_disappear_squeeze");
    private final AnimationController<NightwardenSpinCloneEntity> squeezeController = new AnimationController((GeoAnimatable)this, "nightwarden_clone_squeeze_controller", 0, this::squeezeAnimationPredicate);

    public NightwardenSpinCloneEntity(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public NightwardenSpinCloneEntity(Level level, LivingEntity entityToCopy, float yawOffset) {
        this((EntityType<? extends LivingEntity>)((EntityType)TravelopticsEntities.NIGHTWARDEN_SPIN_CLONE.get()), level);
        float baseYaw = entityToCopy.getYRot();
        float adjustedYaw = baseYaw + yawOffset;
        this.moveTo(entityToCopy.getX(), entityToCopy.getY(), entityToCopy.getZ(), adjustedYaw, entityToCopy.getXRot());
        this.setYBodyRot(adjustedYaw);
        this.yBodyRotO = adjustedYaw;
        this.setYHeadRot(adjustedYaw);
        this.yHeadRotO = adjustedYaw;
        this.setSummoner(entityToCopy);
    }

    public void setMovementSpeed(float speed) {
        this.forwardSpeed = speed;
    }

    public float getMovementSpeed() {
        return this.forwardSpeed;
    }

    public void setShouldMoveForward(boolean shouldMove) {
        this.shouldMoveForward = shouldMove;
    }

    public boolean shouldMoveForward() {
        return this.shouldMoveForward;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMaxAge() {
        return this.maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public void setHpBasedDamagePercent(float percent) {
        this.hpBasedDamagePercent = percent;
    }

    public float getHpBasedDamagePercent() {
        return this.hpBasedDamagePercent;
    }

    public void setRadius(float radius) {
        this.makeBoundingBox.packDirty(DATA_RADIUS, (Object)Float.valueOf(radius));
    }

    public float getRadius() {
        return ((Float)this.makeBoundingBox.packDirty(DATA_RADIUS)).floatValue();
    }

    public boolean isMagicDamageMode() {
        return (Boolean)this.makeBoundingBox.packDirty(MAGIC_DAMAGE_MODE);
    }

    public void setMagicDamageMode(boolean value) {
        this.makeBoundingBox.packDirty(MAGIC_DAMAGE_MODE, (Object)value);
    }

    public void setDamage(float damage) {
        this.makeBoundingBox.packDirty(DATA_DAMAGE, (Object)Float.valueOf(damage));
    }

    public float getDamage() {
        return ((Float)this.makeBoundingBox.packDirty(DATA_DAMAGE)).floatValue();
    }

    public void setSummoner(@Nullable LivingEntity owner) {
        if (owner != null) {
            this.summonerUUID = owner.getUUID();
            this.cachedSummoner = owner;
        }
    }

    public LivingEntity getSummoner() {
        if (this.cachedSummoner != null && this.cachedSummoner.isAlive()) {
            return this.cachedSummoner;
        }
        if (this.summonerUUID != null && this.level() instanceof ServerLevel) {
            Entity entity = ((ServerLevel)this.level()).getRandomSequence(this.summonerUUID);
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity;
                this.cachedSummoner = livingEntity = (LivingEntity)entity;
            }
            return this.cachedSummoner;
        }
        return null;
    }

    public void lerpMotion() {
        super.lerpMotion();
        ++this.age;
        if (this.shouldMoveForward) {
            Vec3 forward = this.getLookAngle().multiply().x((double)this.forwardSpeed);
            this.setIsInPowderSnow(forward.z, this.getDeltaMovement().multiply, forward.reverse);
            this.getPortalWaitTime = true;
        }
        if (this.getTags % 5 == 0) {
            LivingEntity owner = this.getSummoner();
            double radius = this.getRadius();
            AABB region = new AABB(this.getX() - radius, this.getY() - radius, this.getZ() - radius, this.getX() + radius, this.getY() + radius, this.getZ() + radius);
            this.level().getNearbyEntities(LivingEntity.class, region).stream().filter(entity -> entity.isAlive() && entity != this && entity != owner).filter(entity -> owner == null || !this.isAlly(owner, (LivingEntity)entity) && !this.isTamed((LivingEntity)entity)).forEach(entity -> {
                float baseDamage = this.getDamage();
                float bonusDamage = entity.getMaxHealth() * this.getHpBasedDamagePercent();
                float totalDamage = baseDamage + bonusDamage;
                DamageSource source = this.isMagicDamageMode() ? (owner != null ? this.damageSources().indirectMagic((Entity)this, (Entity)owner) : this.damageSources().magic()) : this.damageSources().thrown((LivingEntity)this);
                entity.sendSystemMessage(source, totalDamage);
            });
            if (!this.level().isClientSide) {
                MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)new BlastwaveParticleOptions(((SchoolType)SchoolRegistry.ENDER.get()).getTargetingColor(), this.getRadius()), (double)this.getX(), (double)(this.getY() + (double)0.165f), (double)this.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)false);
            }
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_SWING.get(), 1.0f, 1.0f);
        }
        if (!this.playedSqueezeAnimation && this.age >= this.maxAge - 5) {
            this.playedSqueezeAnimation = true;
        }
        if (this.age >= this.maxAge) {
            this.discard();
        }
    }

    public Iterable<ItemStack> getArmorSlots() {
        return Collections.singleton(ItemStack.onUseTick);
    }

    public ItemStack shouldRemoveSoulSpeed(EquipmentSlot pSlot) {
        return ItemStack.onUseTick;
    }

    public void setLastDeathLocation(EquipmentSlot pSlot, ItemStack pStack) {
    }

    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    public void onAntiMagic(MagicData playerMagicData) {
    }

    public boolean isPushable() {
        return false;
    }

    public boolean canBeCollidedWith() {
        return true;
    }

    public boolean isPickable() {
        return true;
    }

    public boolean canBeAffected(MobEffectInstance effect) {
        return false;
    }

    public boolean shouldShowName() {
        return false;
    }

    @Nullable
    protected SoundEvent giveExperiencePoints(DamageSource pDamageSource) {
        return (SoundEvent)TravelopticsSounds.CRIMSON_DESCEND_IMPACT.get();
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return (SoundEvent)TravelopticsSounds.CRIMSON_DESCEND_IMPACT.get();
    }

    public boolean sendSystemMessage(DamageSource pSource, float pAmount) {
        if (this.level().isClientSide || this.getStandingEyeHeight(pSource)) {
            return false;
        }
        this.discard();
        return true;
    }

    private boolean isAlly(LivingEntity owner, LivingEntity target) {
        return owner.getTeam() != null && owner.getTeam().isAlliedTo(target.getTeam());
    }

    private boolean isTamed(LivingEntity target) {
        if (target instanceof TamableAnimal) {
            TamableAnimal tamableAnimal = (TamableAnimal)target;
            return tamableAnimal.isTame();
        }
        return false;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.makeBoundingBox.assignValue(DATA_RADIUS, (Object)Float.valueOf(4.0f));
        this.makeBoundingBox.assignValue(DATA_DAMAGE, (Object)Float.valueOf(5.0f));
        this.makeBoundingBox.assignValue(MAGIC_DAMAGE_MODE, (Object)false);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.accept("HpBonusPercent", this.hpBasedDamagePercent);
        tag.accept("Radius", this.getRadius());
        tag.accept("Damage", this.getDamage());
        tag.accept("Age", this.getAge());
        tag.accept("MaxAge", this.getMaxAge());
        tag.accept("ForwardSpeed", this.forwardSpeed);
        tag.accept("ShouldMoveForward", this.shouldMoveForward);
        if (this.summonerUUID != null) {
            tag.accept("Summoner", this.summonerUUID);
        }
        tag.accept("MagicDamageMode", this.isMagicDamageMode());
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.hpBasedDamagePercent = tag.getFloat("HpBonusPercent");
        if (tag.contains("Age")) {
            this.age = tag.copy("Age");
        }
        if (tag.contains("MaxAge")) {
            this.maxAge = tag.copy("MaxAge");
        }
        if (tag.contains("ForwardSpeed")) {
            this.forwardSpeed = tag.getFloat("ForwardSpeed");
        }
        if (tag.contains("ShouldMoveForward")) {
            this.shouldMoveForward = tag.getBoolean("ShouldMoveForward");
        }
        if (tag.contains("Radius")) {
            this.setRadius(tag.getFloat("Radius"));
        }
        if (tag.contains("Damage")) {
            this.setDamage(tag.getFloat("Damage"));
        }
        if (tag.readNamedTagName("Summoner")) {
            this.summonerUUID = tag.accept("Summoner");
        }
        if (tag.contains("MagicDamageMode")) {
            this.setMagicDamageMode(tag.getBoolean("MagicDamageMode"));
        }
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes().build(Attributes.ATTACK_DAMAGE, 0.0).build(Attributes.register, 1.0).build(Attributes.FOLLOW_RANGE, 0.0).build(Attributes.KNOCKBACK_RESISTANCE, 100.0).build(Attributes.MOVEMENT_SPEED, 0.0);
    }

    private PlayState animationPredicate(AnimationState<NightwardenSpinCloneEntity> event) {
        event.getController().setAnimation(this.SPIN_ANIMATION);
        return PlayState.CONTINUE;
    }

    private PlayState squeezeAnimationPredicate(AnimationState<NightwardenSpinCloneEntity> event) {
        if (this.playedSqueezeAnimation) {
            event.getController().setAnimation(this.SQUEEZE_ANIMATION);
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{this.controller});
        controllerRegistrar.add(new AnimationController[]{this.squeezeController});
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}

