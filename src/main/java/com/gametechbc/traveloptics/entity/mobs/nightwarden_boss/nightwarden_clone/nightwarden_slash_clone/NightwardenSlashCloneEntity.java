/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
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
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.nightwarden_slash_clone;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone.NightwardenCloneBase;
import com.gametechbc.traveloptics.entity.projectiles.void_slash.VoidSlashProjectile;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
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

public class NightwardenSlashCloneEntity
extends NightwardenCloneBase
implements GeoEntity,
AntiMagicSusceptible {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
    private float hpBasedDamagePercent = 0.0f;
    private float lifestealPercent = 1.0f;
    private static final EntityDataAccessor<Float> DATA_DAMAGE = SynchedEntityData.assignValue(NightwardenSlashCloneEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> IS_LEFT_SLASH = SynchedEntityData.assignValue(NightwardenSlashCloneEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> MAGIC_DAMAGE_MODE = SynchedEntityData.assignValue(NightwardenSlashCloneEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private final RawAnimation LEFT_ANIMATION = RawAnimation.begin().thenPlay("nightwarden_clone_left_swing");
    private final RawAnimation RIGHT_ANIMATION = RawAnimation.begin().thenPlay("nightwarden_clone_right_swing");
    private final AnimationController<NightwardenSlashCloneEntity> controller = new AnimationController((GeoAnimatable)this, "nightwarden_slash_controller", 0, this::animationPredicate);

    public NightwardenSlashCloneEntity(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }

    public NightwardenSlashCloneEntity(Level level, LivingEntity entityToCopy, boolean isLeftSlash, float yawOffset) {
        this((EntityType<? extends LivingEntity>)((EntityType)TravelopticsEntities.NIGHTWARDEN_SLASH_CLONE.get()), level);
        float baseYaw = entityToCopy.getYRot();
        float adjustedYaw = baseYaw + yawOffset;
        this.moveTo(entityToCopy.getX(), entityToCopy.getY(), entityToCopy.getZ(), adjustedYaw, entityToCopy.getXRot());
        this.setYBodyRot(adjustedYaw);
        this.yBodyRotO = adjustedYaw;
        this.setYHeadRot(adjustedYaw);
        this.yHeadRotO = adjustedYaw;
        this.setSummoner(entityToCopy);
        this.setLeftSlash(isLeftSlash);
    }

    public void setHpBasedDamagePercent(float percent) {
        this.hpBasedDamagePercent = percent;
    }

    public float getHpBasedDamagePercent() {
        return this.hpBasedDamagePercent;
    }

    public void setDamage(float damage) {
        this.makeBoundingBox.packDirty(DATA_DAMAGE, (Object)Float.valueOf(damage));
    }

    public float getDamage() {
        return ((Float)this.makeBoundingBox.packDirty(DATA_DAMAGE)).floatValue();
    }

    public void setLifestealPercent(float percent) {
        this.lifestealPercent = percent;
    }

    public float getLifestealPercent() {
        return this.lifestealPercent;
    }

    public void setLeftSlash(boolean left) {
        this.makeBoundingBox.packDirty(IS_LEFT_SLASH, (Object)left);
    }

    public boolean isLeftSlash() {
        return (Boolean)this.makeBoundingBox.packDirty(IS_LEFT_SLASH);
    }

    public boolean isMagicDamageMode() {
        return (Boolean)this.makeBoundingBox.packDirty(MAGIC_DAMAGE_MODE);
    }

    public void setMagicDamageMode(boolean value) {
        this.makeBoundingBox.packDirty(MAGIC_DAMAGE_MODE, (Object)value);
    }

    public void lerpMotion() {
        LivingEntity summoner;
        super.lerpMotion();
        if (this.getTags == 2 && (summoner = this.getSummoner()) != null) {
            VoidSlashProjectile slash = new VoidSlashProjectile(this.level(), summoner);
            Vec3 origin = this.getEyePosition();
            slash.setLevel(origin);
            Vec3 look = this.getLookAngle();
            Vec3 modifiedLook = new Vec3(look.z, look.multiply - 0.05, look.reverse).multiply();
            slash.shoot(modifiedLook.z, modifiedLook.multiply, modifiedLook.reverse, 1.2f, 0.0f);
            slash.setMagicDamageMode(this.isMagicDamageMode());
            slash.setDamage(this.getDamage());
            slash.setLifestealPercent(this.getLifestealPercent());
            this.level().addFreshEntity((Entity)slash);
        }
        if (this.getTags >= 17) {
            this.discard();
        }
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

    public boolean sendSystemMessage(DamageSource pSource, float pAmount) {
        return false;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.makeBoundingBox.assignValue(DATA_DAMAGE, (Object)Float.valueOf(5.0f));
        this.makeBoundingBox.assignValue(IS_LEFT_SLASH, (Object)false);
        this.makeBoundingBox.assignValue(MAGIC_DAMAGE_MODE, (Object)false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.accept("HpBonusPercent", this.hpBasedDamagePercent);
        tag.accept("Damage", this.getDamage());
        tag.accept("IsLeftSlash", this.isLeftSlash());
        tag.accept("MagicDamageMode", this.isMagicDamageMode());
        tag.accept("LifestealPercent", this.lifestealPercent);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.hpBasedDamagePercent = tag.getFloat("HpBonusPercent");
        if (tag.contains("Damage")) {
            this.setDamage(tag.getFloat("Damage"));
        }
        if (tag.contains("IsLeftSlash")) {
            this.setLeftSlash(tag.getBoolean("IsLeftSlash"));
        }
        if (tag.contains("MagicDamageMode")) {
            this.setMagicDamageMode(tag.getBoolean("MagicDamageMode"));
        }
        if (tag.contains("LifestealPercent")) {
            this.lifestealPercent = tag.getFloat("LifestealPercent");
        }
    }

    private PlayState animationPredicate(AnimationState<NightwardenSlashCloneEntity> event) {
        event.getController().setAnimation(this.isLeftSlash() ? this.LEFT_ANIMATION : this.RIGHT_ANIMATION);
        return PlayState.CONTINUE;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar registrar) {
        registrar.add(new AnimationController[]{this.controller});
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}

