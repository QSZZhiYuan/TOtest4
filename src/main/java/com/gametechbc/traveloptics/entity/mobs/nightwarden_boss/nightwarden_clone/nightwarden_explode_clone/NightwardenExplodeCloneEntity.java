/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModEffect
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.SchoolType
 *  io.redspace.ironsspellbooks.api.util.CameraShakeData
 *  io.redspace.ironsspellbooks.api.util.CameraShakeManager
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
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
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
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_explode_clone;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.NightwardenCloneBase;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import com.github.L_Ender.cataclysm.init.ModEffect;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class NightwardenExplodeCloneEntity
extends NightwardenCloneBase
implements GeoEntity,
AntiMagicSusceptible {
    private static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.assignValue(NightwardenExplodeCloneEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_DAMAGE = SynchedEntityData.assignValue(NightwardenExplodeCloneEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> IS_SPIN_CLONE = SynchedEntityData.assignValue(NightwardenExplodeCloneEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> EXPLODE_DELAY_TICKS = SynchedEntityData.assignValue(NightwardenExplodeCloneEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
    private float hpBasedDamagePercent = 0.0f;
    private int disappearAnimTick = -1;
    private boolean useMagicDamage = false;
    private final RawAnimation BASIC_ANIMATION = RawAnimation.begin().thenPlay("nightwarden_clone_basic");
    private final RawAnimation SPIN_ANIMATION = RawAnimation.begin().thenPlay("nightwarden_clone_spin_pose");
    private final RawAnimation DISAPPEAR_ANIMATION = RawAnimation.begin().thenPlay("nightwarden_clone_disappear_poof");
    private final AnimationController<NightwardenExplodeCloneEntity> controller = new AnimationController((GeoAnimatable)this, "nightwarden_clone_explode_controller", 0, this::animationPredicate);
    private final AnimationController<NightwardenExplodeCloneEntity> squeezeController = new AnimationController((GeoAnimatable)this, "disappear", 0, this::disappearPredicate);

    public NightwardenExplodeCloneEntity(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public NightwardenExplodeCloneEntity(Level level, LivingEntity entityToCopy, float yawOffset) {
        this((EntityType<? extends LivingEntity>)((EntityType)TravelopticsEntities.NIGHTWARDEN_EXPLODE_CLONE.get()), level);
        float baseYaw = entityToCopy.getYRot();
        float adjustedYaw = baseYaw + yawOffset;
        this.moveTo(entityToCopy.getX(), entityToCopy.getY(), entityToCopy.getZ(), adjustedYaw, entityToCopy.getXRot());
        this.setYBodyRot(adjustedYaw);
        this.yBodyRotO = adjustedYaw;
        this.setYHeadRot(adjustedYaw);
        this.yHeadRotO = adjustedYaw;
        this.setSummoner(entityToCopy);
    }

    public void setExplodeDelayTicks(int ticks) {
        this.makeBoundingBox.packDirty(EXPLODE_DELAY_TICKS, (Object)ticks);
    }

    public int getExplodeDelayTicks() {
        return (Integer)this.makeBoundingBox.packDirty(EXPLODE_DELAY_TICKS);
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

    public void setSpinClone(boolean spin) {
        this.makeBoundingBox.packDirty(IS_SPIN_CLONE, (Object)spin);
    }

    public boolean isSpinClone() {
        return (Boolean)this.makeBoundingBox.packDirty(IS_SPIN_CLONE);
    }

    public void setMagicDamage(boolean useMagic) {
        this.useMagicDamage = useMagic;
    }

    public boolean isMagicDamage() {
        return this.useMagicDamage;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.makeBoundingBox.assignValue(DATA_RADIUS, (Object)Float.valueOf(2.0f));
        this.makeBoundingBox.assignValue(DATA_DAMAGE, (Object)Float.valueOf(5.0f));
        this.makeBoundingBox.assignValue(IS_SPIN_CLONE, (Object)false);
        this.makeBoundingBox.assignValue(EXPLODE_DELAY_TICKS, (Object)20);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.accept("HpBonusPercent", this.hpBasedDamagePercent);
        tag.accept("Radius", this.getRadius());
        tag.accept("Damage", this.getDamage());
        tag.accept("SpinClone", this.isSpinClone());
        tag.accept("ExplodeDelay", this.getExplodeDelayTicks());
        tag.accept("UseMagicDamage", this.useMagicDamage);
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
        if (tag.contains("SpinClone")) {
            this.setSpinClone(tag.getBoolean("SpinClone"));
        }
        if (tag.contains("ExplodeDelay")) {
            this.setExplodeDelayTicks(tag.copy("ExplodeDelay"));
        }
        if (tag.contains("UseMagicDamage")) {
            this.useMagicDamage = tag.getBoolean("UseMagicDamage");
        }
    }

    public void lerpMotion() {
        super.lerpMotion();
        int explodeDelay = this.getExplodeDelayTicks();
        if (this.getTags == explodeDelay - 5) {
            this.disappearAnimTick = 0;
        }
        if (this.getTags == explodeDelay - 1) {
            LivingEntity owner = this.getSummoner();
            double radius = this.getRadius();
            AABB region = new AABB(this.getX() - radius, this.getY() - radius, this.getZ() - radius, this.getX() + radius, this.getY() + radius, this.getZ() + radius);
            this.level().getNearbyEntities(LivingEntity.class, region).stream().filter(entity -> entity.isAlive() && entity != owner).filter(entity -> owner == null || !this.isAlly(owner, (LivingEntity)entity) && !this.isTamed((LivingEntity)entity)).forEach(entity -> {
                float baseDamage = this.getDamage();
                float bonusDamage = entity.getMaxHealth() * this.getHpBasedDamagePercent();
                float totalDamage = baseDamage + bonusDamage;
                DamageSource source = this.useMagicDamage ? this.damageSources().indirectMagic((Entity)this, (Entity)(this.getSummoner() != null ? this.getSummoner() : this)) : this.damageSources().thrown((LivingEntity)this);
                entity.sendSystemMessage(source, totalDamage);
                entity.getStandingEyeHeight(new MobEffectInstance((MobEffect)ModEffect.EFFECTABYSSAL_FEAR.get(), 200, 0, false, false, true));
            });
            if (!this.level().isClientSide) {
                MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)new BlastwaveParticleOptions(((SchoolType)SchoolRegistry.ENDER.get()).getTargetingColor(), this.getRadius() + 1.5f), (double)this.getX(), (double)(this.getY() + (double)1.2f), (double)this.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)false);
                MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE, (double)this.getX(), (double)this.getY(), (double)this.getZ(), (int)50, (double)0.4, (double)0.8, (double)0.4, (double)0.03, (boolean)false);
                CameraShakeManager.addCameraShake((CameraShakeData)new CameraShakeData(20, this.position(), this.getRadius()));
            }
            this.setLevel((SoundEvent)TravelopticsSounds.ORBITAL_VOID_PULSE.get());
        }
        if (this.getTags >= explodeDelay) {
            this.discard();
        }
    }

    public void onAntiMagic(MagicData playerMagicData) {
        this.discard();
    }

    @Nullable
    protected SoundEvent giveExperiencePoints(DamageSource pDamageSource) {
        return null;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return null;
    }

    public boolean sendSystemMessage(DamageSource pSource, float pAmount) {
        return false;
    }

    private PlayState animationPredicate(AnimationState<NightwardenExplodeCloneEntity> event) {
        event.getController().setAnimation(this.isSpinClone() ? this.SPIN_ANIMATION : this.BASIC_ANIMATION);
        return PlayState.CONTINUE;
    }

    private PlayState disappearPredicate(AnimationState<NightwardenExplodeCloneEntity> event) {
        if (this.disappearAnimTick >= 0) {
            event.getController().setAnimation(this.DISAPPEAR_ANIMATION);
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

