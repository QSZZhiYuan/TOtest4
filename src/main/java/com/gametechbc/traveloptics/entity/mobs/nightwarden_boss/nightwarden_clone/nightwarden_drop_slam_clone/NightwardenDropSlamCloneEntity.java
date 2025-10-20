/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModEffect
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
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
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
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_drop_slam_clone;

import com.gametechbc.traveloptics.api.particle.AdvancedSphereParticleManager;
import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.NightwardenCloneBase;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsParticles;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.github.L_Ender.cataclysm.init.ModEffect;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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

public class NightwardenDropSlamCloneEntity
extends NightwardenCloneBase
implements GeoEntity,
AntiMagicSusceptible {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
    private float hpBasedDamagePercent = 0.0f;
    private static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.assignValue(NightwardenDropSlamCloneEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_DAMAGE = SynchedEntityData.assignValue(NightwardenDropSlamCloneEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> MAGIC_DAMAGE_MODE = SynchedEntityData.assignValue(NightwardenDropSlamCloneEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private boolean shouldApplyEffect = false;
    private float downwardSpeed = -0.35f;
    private final RawAnimation SPIN_ANIMATION = RawAnimation.begin().thenLoop("nightwarden_clone_ground_slam");
    private final AnimationController<NightwardenDropSlamCloneEntity> controller = new AnimationController((GeoAnimatable)this, "nightwarden_clone_spin_controller", 0, this::animationPredicate);

    public NightwardenDropSlamCloneEntity(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.getY(true);
    }

    public NightwardenDropSlamCloneEntity(Level level, LivingEntity entityToCopy) {
        this((EntityType<? extends LivingEntity>)((EntityType)TravelopticsEntities.NIGHTWARDEN_DROP_SLAM_CLONE.get()), level);
        this.moveTo(entityToCopy.getX(), entityToCopy.getY(), entityToCopy.getZ(), entityToCopy.getYRot(), entityToCopy.getXRot());
        this.setYBodyRot(entityToCopy.yBodyRot);
        this.yBodyRotO = this.yBodyRot;
        this.setYHeadRot(entityToCopy.getYHeadRot());
        this.yHeadRotO = this.yHeadRot;
        this.setSummoner(entityToCopy);
        this.getY(true);
    }

    public void setShouldApplyEffect(boolean applyEffect) {
        this.shouldApplyEffect = applyEffect;
    }

    public boolean shouldApplyEffect() {
        return this.shouldApplyEffect;
    }

    public void setDownwardSpeed(float speed) {
        this.downwardSpeed = speed;
    }

    public float getDownwardSpeed() {
        return this.downwardSpeed;
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

    public void setDamage(float damage) {
        this.makeBoundingBox.packDirty(DATA_DAMAGE, (Object)Float.valueOf(damage));
    }

    public float getDamage() {
        return ((Float)this.makeBoundingBox.packDirty(DATA_DAMAGE)).floatValue();
    }

    public boolean isMagicDamageMode() {
        return (Boolean)this.makeBoundingBox.packDirty(MAGIC_DAMAGE_MODE);
    }

    public void setMagicDamageMode(boolean value) {
        this.makeBoundingBox.packDirty(MAGIC_DAMAGE_MODE, (Object)value);
    }

    public void lerpMotion() {
        super.lerpMotion();
        Vec3 currentMotion = this.getDeltaMovement();
        this.setIsInPowderSnow(currentMotion.z, this.downwardSpeed, currentMotion.reverse);
        this.getPortalWaitTime = true;
        if (this.onGround()) {
            this.triggerImpactLogic();
        }
        if (this.getTags >= 200) {
            this.discard();
        }
    }

    private void triggerImpactLogic() {
        if (!this.level().isClientSide) {
            LivingEntity owner = this.getSummoner();
            double radius = this.getRadius();
            AABB region = new AABB(this.getX() - radius, this.getY() - 1.0, this.getZ() - radius, this.getX() + radius, this.getY() + 2.0, this.getZ() + radius);
            this.level().getNearbyEntities(LivingEntity.class, region).stream().filter(entity -> entity.isAlive() && entity != this && entity != owner).filter(entity -> owner == null || !this.isAlly(owner, (LivingEntity)entity) && !this.isTamed((LivingEntity)entity)).forEach(entity -> {
                float baseDamage = this.getDamage();
                float bonusDamage = entity.getMaxHealth() * this.getHpBasedDamagePercent();
                float totalDamage = baseDamage + bonusDamage;
                DamageSource source = this.isMagicDamageMode() ? (owner != null ? this.damageSources().indirectMagic((Entity)this, (Entity)owner) : this.damageSources().magic()) : this.damageSources().thrown((LivingEntity)this);
                entity.sendSystemMessage(source, totalDamage);
                if (entity instanceof LivingEntity) {
                    boolean cleansed = this.cleanseBeneficialEffects((LivingEntity)entity);
                    if (this.shouldApplyEffect && cleansed) {
                        entity.getStandingEyeHeight(new MobEffectInstance((MobEffect)ModEffect.EFFECTABYSSAL_FEAR.get(), 60, 1));
                    }
                }
            });
            MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)new BlastwaveParticleOptions(((SchoolType)SchoolRegistry.ENDER.get()).getTargetingColor(), this.getRadius()), (double)this.getX(), (double)(this.getY() + (double)0.15f), (double)this.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)false);
            AdvancedSphereParticleManager.spawnParticles(this.level(), this.getX(), this.getY() + 0.5, this.getZ(), 50, (ParticleOptions)TravelopticsParticles.PURPLE_STAR_OUTWARD_PARTICLE.get(), ParticleDirection.OUTWARD, 5.0, false);
        }
        this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.CRIMSON_DESCEND_IMPACT.get(), 0.8f, 1.0f);
        this.discard();
    }

    private boolean cleanseBeneficialEffects(LivingEntity entity) {
        List<MobEffect> beneficialEffects = entity.getActiveEffects().stream().map(MobEffectInstance::compareTo).filter(effect -> effect.getCategory() == MobEffectCategory.BENEFICIAL).toList();
        if (!beneficialEffects.isEmpty()) {
            MobEffect randomEffect = beneficialEffects.get(this.getId.nextInt(beneficialEffects.size()));
            entity.broadcastBreakEvent(randomEffect);
            return true;
        }
        return false;
    }

    public void onAntiMagic(MagicData playerMagicData) {
        this.discard();
    }

    @Nullable
    protected SoundEvent giveExperiencePoints(DamageSource pDamageSource) {
        return (SoundEvent)TravelopticsSounds.CRIMSON_DESCEND_IMPACT.get();
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return (SoundEvent)TravelopticsSounds.CRIMSON_DESCEND_IMPACT.get();
    }

    public boolean sendSystemMessage(DamageSource source, float amount) {
        return false;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.makeBoundingBox.assignValue(DATA_RADIUS, (Object)Float.valueOf(4.0f));
        this.makeBoundingBox.assignValue(DATA_DAMAGE, (Object)Float.valueOf(5.0f));
        this.makeBoundingBox.assignValue(MAGIC_DAMAGE_MODE, (Object)false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.accept("HpBonusPercent", this.hpBasedDamagePercent);
        tag.accept("Radius", this.getRadius());
        tag.accept("Damage", this.getDamage());
        tag.accept("DownwardSpeed", this.downwardSpeed);
        tag.accept("ShouldApplyEffect", this.shouldApplyEffect);
        tag.accept("MagicDamageMode", this.isMagicDamageMode());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.hpBasedDamagePercent = tag.getFloat("HpBonusPercent");
        if (tag.contains("Radius")) {
            this.setRadius(tag.getFloat("Radius"));
        }
        if (tag.contains("Damage")) {
            this.setDamage(tag.getFloat("Damage"));
        }
        if (tag.contains("DownwardSpeed")) {
            this.downwardSpeed = tag.getFloat("DownwardSpeed");
        }
        if (tag.contains("ShouldApplyEffect")) {
            this.shouldApplyEffect = tag.getBoolean("ShouldApplyEffect");
        }
        if (tag.contains("MagicDamageMode")) {
            this.setMagicDamageMode(tag.getBoolean("MagicDamageMode"));
        }
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes().build(Attributes.ATTACK_DAMAGE, 0.0).build(Attributes.register, 1.0).build(Attributes.FOLLOW_RANGE, 0.0).build(Attributes.KNOCKBACK_RESISTANCE, 100.0).build(Attributes.MOVEMENT_SPEED, 0.0);
    }

    private PlayState animationPredicate(AnimationState<NightwardenDropSlamCloneEntity> event) {
        event.getController().setAnimation(this.SPIN_ANIMATION);
        return PlayState.CONTINUE;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{this.controller});
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}

