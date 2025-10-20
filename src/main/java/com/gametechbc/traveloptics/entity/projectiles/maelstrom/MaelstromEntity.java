/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModSounds
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible
 *  io.redspace.ironsspellbooks.entity.spells.AoeEntity
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
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
package com.gametechbc.traveloptics.entity.projectiles.maelstrom;

import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.github.L_Ender.cataclysm.init.ModSounds;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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

public class MaelstromEntity
extends AoeEntity
implements GeoEntity,
AntiMagicSusceptible {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
    private final RawAnimation SPIN_ANIMATION = RawAnimation.begin().thenPlay("spin_once");
    private int age = 0;
    private double pullScale = 0.5;
    private final AnimationController controller = new AnimationController((GeoAnimatable)this, "maelstrom_controller", 0, this::animationPredicate);

    public MaelstromEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
        this.reapplicationDelay = 30;
        this.setCircular();
    }

    public MaelstromEntity(Level level) {
        this((EntityType<? extends Projectile>)((EntityType)TravelopticsEntities.MAELSTROM_ENTITY.get()), level);
    }

    public double getPullScale() {
        return this.pullScale;
    }

    public void setPullScale(double pullScale) {
        this.pullScale = pullScale;
    }

    public void lerpMotion() {
        super.lerpMotion();
        ++this.age;
        if (this.level().isClientSide && this.age == 22 && !this.isSilent()) {
            this.level().addDestroyBlockEffect(this.getX(), this.getY(), this.getZ(), (SoundEvent)ModSounds.EMP_ACTIVATED.get(), this.getSoundSource(), 2.5f, this.getId.nextFloat() * 0.2f + 0.85f, false);
        }
        if (!this.level().isClientSide && this.age >= 100) {
            this.discard();
            return;
        }
        if (this.age < 5 || this.age >= 95) {
            return;
        }
        if (!this.level().isClientSide && this.age % 2 == 0) {
            Vec3 center = this.position();
            float radius = this.getRadius();
            LivingEntity owner = this.getOwner() instanceof LivingEntity ? (LivingEntity)this.getOwner() : null;
            List nearbyEntities = this.level().getNearbyEntities(LivingEntity.class, new AABB(center.z - (double)radius, center.multiply - (double)radius, center.reverse - (double)radius, center.z + (double)radius, center.multiply + (double)radius, center.reverse + (double)radius), entity -> {
                if (entity == owner || owner == null) {
                    return false;
                }
                return !this.isAlly(owner, (LivingEntity)entity) && !this.isTamed((LivingEntity)entity);
            });
            for (LivingEntity entity2 : nearbyEntities) {
                Vec3 entityPos = entity2.position();
                Vec3 directionToCenter = center.multiply(entityPos);
                double distance = directionToCenter.length();
                if (!(distance > 0.5)) continue;
                Vec3 pullForce = directionToCenter.multiply().x(this.getPullScale() * (distance / (double)radius));
                entity2.setDeltaMovement(entity2.getDeltaMovement().x(0.8).reverse(pullForce));
                entity2.getAddEntityPacket = true;
            }
        }
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
        target.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.TIDAL_TORMENT.get(), 50, 1, false, false, false));
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
        return Optional.empty();
    }

    protected boolean canHitTargetForGroundContext(LivingEntity target) {
        return true;
    }

    protected void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.accept("PullScale", this.pullScale);
        compound.accept("Age", this.age);
    }

    protected void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("PullScale")) {
            this.pullScale = compound.getDouble("PullScale");
        }
        if (compound.contains("Age")) {
            this.age = compound.copy("Age");
        }
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
}

