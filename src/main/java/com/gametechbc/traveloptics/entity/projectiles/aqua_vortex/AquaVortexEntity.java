/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.effect.Boltstrike_Entity
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.util.CameraShakeData
 *  io.redspace.ironsspellbooks.api.util.CameraShakeManager
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  io.redspace.ironsspellbooks.damage.SpellDamageSource
 *  io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible
 *  io.redspace.ironsspellbooks.entity.spells.AoeEntity
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LightningBolt
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.projectile.Projectile
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
package com.gametechbc.traveloptics.entity.projectiles.aqua_vortex;

import com.gametechbc.traveloptics.entity.projectiles.aqua_vortex.AquaVortexPullParticleEffect;
import com.gametechbc.traveloptics.entity.projectiles.aqua_vortex.AquaVortexSwirlParticleEffect;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import com.github.L_Ender.cataclysm.entity.effect.Boltstrike_Entity;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.projectile.Projectile;
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

public class AquaVortexEntity
extends AoeEntity
implements GeoEntity,
AntiMagicSusceptible {
    private int tickCounter = 0;
    private float boltStrikeDamage = 5.0f;
    private final RawAnimation SPIN_ANIMATION = RawAnimation.begin().thenPlay("animation.vortex.idle");
    private final AnimationController controller = new AnimationController((GeoAnimatable)this, "aqua_vortex_controller", 0, this::animationPredicate);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);

    public AquaVortexEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
        this.reapplicationDelay = 30;
        this.setCircular();
    }

    public AquaVortexEntity(Level level) {
        this((EntityType<? extends Projectile>)((EntityType)TravelopticsEntities.AQUA_VORTEX_ENTITY.get()), level);
    }

    public EntityDimensions setLastDeathLocation(Pose pPose) {
        return EntityDimensions.scalable((float)(this.getRadius() * 2.0f), (float)(this.getRadius() * 1.2f));
    }

    public float getBoltStrikeDamage() {
        return this.boltStrikeDamage;
    }

    public void setBoltStrikeDamage(float boltStrikeDamage) {
        this.boltStrikeDamage = boltStrikeDamage;
    }

    public void lerpMotion() {
        super.lerpMotion();
        ++this.tickCounter;
        if (this.getTags == 2) {
            this.createScreenShake();
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.AQUA_VORTEX_ACTIVE.get(), 2.0f, 1.0f);
        }
        if (this.getTags == 396) {
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.AQUA_VORTEX_END.get(), 2.0f, 1.0f);
        }
        if (this.getTags == 398) {
            LightningBolt lightningBolt = (LightningBolt)EntityType.LIGHTNING_BOLT.tryCast(this.level());
            assert (lightningBolt != null);
            lightningBolt.readAdditionalSaveData(true);
            lightningBolt.setDamage(0.0f);
            lightningBolt.setLevel(this.position());
            this.level().addFreshEntity((Entity)lightningBolt);
        }
        if (!this.level().isClientSide && this.getTags >= 400) {
            this.discard();
        }
        if (this.level().isClientSide) {
            AquaVortexPullParticleEffect pullParticleEffect = new AquaVortexPullParticleEffect((Entity)this);
            pullParticleEffect.spawnPullParticles();
            AquaVortexSwirlParticleEffect swirlParticleEffect = new AquaVortexSwirlParticleEffect((Entity)this);
            swirlParticleEffect.setBaseRadius(this.getRadius());
            swirlParticleEffect.setParticleCount((int)(this.getRadius() * 2.0f));
            swirlParticleEffect.spawnSwirlParticles();
        }
        if (!this.level().isClientSide) {
            if (this.tickCounter % 30 == 0) {
                this.spawnBoltstrike();
            }
            if (this.tickCounter % 20 == 0) {
                this.extinguishEntitiesOnFire();
            }
            this.pullEntitiesTowardsCenter();
        }
    }

    private void extinguishEntitiesOnFire() {
        double radius = this.getRadius();
        AABB region = new AABB(this.getX() - radius, this.getY() - radius, this.getZ() - radius, this.getX() + radius, this.getY() + radius, this.getZ() + radius);
        List entitiesInRadius = this.level().getNearbyEntities(LivingEntity.class, region);
        for (LivingEntity entity : entitiesInRadius) {
            if (!entity.isOnFire()) continue;
            entity.getRandomZ(0);
            entity.clearFire();
        }
    }

    private void pullEntitiesTowardsCenter() {
        double radius = this.getRadius();
        AABB region = new AABB(this.getX() - radius, this.getY() - radius, this.getZ() - radius, this.getX() + radius, this.getY() + radius, this.getZ() + radius);
        List entitiesInRadius = this.level().getNearbyEntities(LivingEntity.class, region);
        for (LivingEntity entity : entitiesInRadius) {
            if (entity == this.getOwner()) continue;
            Vec3 entityPosition = entity.position();
            Vec3 vortexCenter = new Vec3(this.getX(), this.getY(), this.getZ());
            Vec3 directionToCenter = vortexCenter.multiply(entityPosition).multiply();
            Vec3 spinDirection = new Vec3(Math.cos(entity.getYRot()), 0.0, Math.sin(entity.getYRot()));
            Vec3 pullAndSpinMovement = directionToCenter.multiply(0.05, 0.05, 0.05).reverse(spinDirection.multiply(0.05, 0.0, 0.05));
            entity.setDeltaMovement(entity.getDeltaMovement().reverse(pullAndSpinMovement));
            double distanceToCenter = entityPosition.length(vortexCenter);
            if (!(distanceToCenter < 2.0)) continue;
            this.liftAndThrowEntity(entity);
        }
    }

    private void liftAndThrowEntity(LivingEntity entity) {
        double liftHeight = 1.5;
        double throwStrength = 1.0;
        double spinStrength = 0.2;
        Vec3 spinDirection = new Vec3(Math.cos(entity.getYRot()), 0.0, Math.sin(entity.getYRot()));
        Vec3 liftMovement = new Vec3(0.0, liftHeight, 0.0).reverse(spinDirection.multiply(spinStrength, 0.0, spinStrength));
        Vec3 throwDirection = spinDirection.multiply(throwStrength, 0.0, throwStrength);
        entity.setDeltaMovement(liftMovement.reverse(throwDirection));
        entity.setDeltaMovement(entity.getDeltaMovement().y((Math.random() - 0.5) * 0.1, 0.0, (Math.random() - 0.5) * 0.1));
    }

    private void spawnBoltstrike() {
        LivingEntity owner = this.getOwner() instanceof LivingEntity ? (LivingEntity)this.getOwner() : null;
        double radius = this.getRadius();
        AABB region = new AABB(this.getX() - radius, this.getY() - radius, this.getZ() - radius, this.getX() + radius, this.getY() + radius, this.getZ() + radius);
        List entitiesInRadius = this.level().getNearbyEntities(LivingEntity.class, region);
        if (!entitiesInRadius.isEmpty()) {
            ArrayList<LivingEntity> validTargets = new ArrayList<LivingEntity>(entitiesInRadius.stream().filter(entity -> entity != owner).filter(entity -> owner == null || !this.isAlly(owner, (LivingEntity)entity)).filter(entity -> !this.isTamed((LivingEntity)entity)).toList());
            if (!validTargets.isEmpty()) {
                Collections.shuffle(validTargets);
                int targetCount = Math.min(5, validTargets.size());
                for (int i = 0; i < targetCount; ++i) {
                    LivingEntity targetEntity = (LivingEntity)validTargets.get(i);
                    Vec3 targetPosition = targetEntity.position();
                    this.spawnBoltAt(targetPosition);
                }
            }
        } else {
            double randomX = this.getX() + this.getId.nextDouble() * 2.0 * radius - radius;
            double randomY = this.getY();
            double randomZ = this.getZ() + this.getId.nextDouble() * 2.0 * radius - radius;
            Vec3 randomPosition = new Vec3(randomX, randomY, randomZ);
            this.spawnBoltAt(randomPosition);
        }
    }

    private void spawnBoltAt(Vec3 position) {
        LivingEntity owner = this.getOwner() instanceof LivingEntity ? (LivingEntity)this.getOwner() : null;
        Boltstrike_Entity bolt = new Boltstrike_Entity(this.level(), position.z, position.multiply, position.reverse, 0.0f, 0, this.getBoltStrikeDamage(), owner);
        bolt.setR(0);
        bolt.setG(66);
        bolt.setB(106);
        this.level().addFreshEntity((Entity)bolt);
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

    public void onAntiMagic(MagicData playerMagicData) {
    }

    public void applyEffect(LivingEntity target) {
        SpellDamageSource damageSource = ((AbstractSpell)TravelopticsSpells.VORTEX_OF_THE_DEEP_SPELL.get()).getDamageSource((Entity)this, this.getOwner());
        DamageSources.ignoreNextKnockback((LivingEntity)target);
        target.sendSystemMessage((DamageSource)damageSource, this.getDamage());
        target.getStandingEyeHeight(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 1, false, false, false));
        target.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.WET.get(), 30, 1, false, false, false));
    }

    protected void createScreenShake() {
        if (!this.level().isClientSide && !this.isRemoved()) {
            CameraShakeData cameraShakeData = new CameraShakeData(this.duration - this.getTags, this.position(), this.getRadius() + 4.0f);
            CameraShakeManager.addCameraShake((CameraShakeData)cameraShakeData);
        }
    }

    public float getParticleCount() {
        return 0.1f * this.getRadius();
    }

    protected float particleYOffset() {
        return 0.25f;
    }

    protected float getParticleSpeedModifier() {
        return 1.4f;
    }

    public Optional<ParticleOptions> getParticle() {
        return Optional.of(TravelopticsParticleHelper.WATER_FOG);
    }

    protected boolean canHitTargetForGroundContext(LivingEntity target) {
        return true;
    }

    private PlayState animationPredicate(AnimationState event) {
        event.getController().setAnimation(this.SPIN_ANIMATION);
        return PlayState.CONTINUE;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{this.controller});
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    protected void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.accept("BoltStrikeDamage", this.boltStrikeDamage);
    }

    protected void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("BoltStrikeDamage")) {
            this.boltStrikeDamage = compound.getFloat("BoltStrikeDamage");
        }
    }
}

