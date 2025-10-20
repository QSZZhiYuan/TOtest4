/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.SchoolType
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible
 *  io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.projectile.Projectile
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
package com.gametechbc.traveloptics.entity.projectiles.supernova.dying_star;

import com.gametechbc.traveloptics.api.particle.AdvancedSphereParticleManager;
import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.entity.misc.TOScreenFlashEntity;
import com.gametechbc.traveloptics.entity.misc.TOScreenShakeEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenBossEntity;
import com.gametechbc.traveloptics.entity.projectiles.supernova.dying_star.DyingStarParticleManager;
import com.gametechbc.traveloptics.entity.projectiles.supernova.supermassive_black_hole.SupermassiveBlackHoleEntity;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsParticles;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import java.util.List;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.projectile.Projectile;
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

public class DyingStarEntity
extends Projectile
implements GeoEntity,
AntiMagicSusceptible {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
    private static final EntityDataAccessor<Float> DATA_DAMAGE = SynchedEntityData.assignValue(DyingStarEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.assignValue(DyingStarEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> DATA_ANTIMAGIC_VULNERABLE = SynchedEntityData.assignValue(DyingStarEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private float blackholeRadius = 20.0f;
    private float blackholeDamage = 10.0f;
    private float ownerVoidDamagePercent = 0.25f;
    private int blackholeDuration = 400;
    private final RawAnimation SUPERNOVA_ANIMATION = RawAnimation.begin().thenPlay("supernova");
    private final AnimationController<?> controller = new AnimationController((GeoAnimatable)this, "supernova_controller", 0, this::animationPredicate);

    public DyingStarEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    public DyingStarEntity(Level level) {
        this((EntityType<? extends Projectile>)((EntityType)TravelopticsEntities.DYING_STAR.get()), level);
    }

    protected void defineSynchedData() {
        this.makeBoundingBox.assignValue(DATA_DAMAGE, (Object)Float.valueOf(4.0f));
        this.makeBoundingBox.assignValue(DATA_RADIUS, (Object)Float.valueOf(20.0f));
        this.makeBoundingBox.assignValue(DATA_ANTIMAGIC_VULNERABLE, (Object)false);
    }

    protected void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.accept("Damage", this.getDamage());
        compound.accept("Radius", this.getRadius());
        compound.accept("AntiMagicVulnerable", ((Boolean)this.makeBoundingBox.packDirty(DATA_ANTIMAGIC_VULNERABLE)).booleanValue());
        compound.accept("BlackholeRadius", this.blackholeRadius);
        compound.accept("BlackholeDamage", this.blackholeDamage);
        compound.accept("OwnerVoidDamagePercent", this.ownerVoidDamagePercent);
        compound.accept("BlackholeDuration", this.blackholeDuration);
    }

    protected void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Damage")) {
            this.setDamage(compound.getFloat("Damage"));
        }
        if (compound.contains("Radius")) {
            this.setRadius(compound.getFloat("Radius"));
        }
        if (compound.contains("AntiMagicVulnerable")) {
            this.makeBoundingBox.packDirty(DATA_ANTIMAGIC_VULNERABLE, (Object)compound.getBoolean("AntiMagicVulnerable"));
        }
        if (compound.contains("BlackholeRadius")) {
            this.blackholeRadius = compound.getFloat("BlackholeRadius");
        }
        if (compound.contains("BlackholeDamage")) {
            this.blackholeDamage = compound.getFloat("BlackholeDamage");
        }
        if (compound.contains("OwnerVoidDamagePercent")) {
            this.ownerVoidDamagePercent = compound.getFloat("OwnerVoidDamagePercent");
        }
        if (compound.contains("BlackholeDuration")) {
            this.blackholeDuration = compound.copy("BlackholeDuration");
        }
    }

    public int getBlackholeDuration() {
        return this.blackholeDuration;
    }

    public void setBlackholeDuration(int duration) {
        this.blackholeDuration = duration;
    }

    public float getOwnerVoidDamagePercent() {
        return this.ownerVoidDamagePercent;
    }

    public void setOwnerVoidDamagePercent(float percent) {
        this.ownerVoidDamagePercent = percent;
    }

    public float getBlackholeDamage() {
        return this.blackholeDamage;
    }

    public void setBlackholeDamage(float damage) {
        this.blackholeDamage = damage;
    }

    public void setBlackholeRadius(float radius) {
        this.blackholeRadius = radius;
    }

    public float getBlackholeRadius() {
        return this.blackholeRadius;
    }

    public void setDamage(float damage) {
        this.makeBoundingBox.packDirty(DATA_DAMAGE, (Object)Float.valueOf(damage));
    }

    public float getDamage() {
        return ((Float)this.makeBoundingBox.packDirty(DATA_DAMAGE)).floatValue();
    }

    public float getRadius() {
        return ((Float)this.makeBoundingBox.packDirty(DATA_RADIUS)).floatValue();
    }

    public void setRadius(float radius) {
        this.makeBoundingBox.packDirty(DATA_RADIUS, (Object)Float.valueOf(radius));
    }

    public boolean isAntiMagicVulnerable() {
        return (Boolean)this.makeBoundingBox.packDirty(DATA_ANTIMAGIC_VULNERABLE);
    }

    public void lerpMotion() {
        super.lerpMotion();
        if (this.getTags == 1) {
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.DYING_STAR_AMBIENT.get(), 3.0f, 1.0f);
        }
        if (this.getTags == 190) {
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.BOSS_ATTACK_WARNING.get(), 3.0f, 1.0f);
        }
        if (!this.level().isClientSide) {
            LivingEntity living;
            LivingEntity owner;
            boolean vulnerable = this.getTags >= 190 && this.getTags <= 214;
            this.makeBoundingBox.packDirty(DATA_ANTIMAGIC_VULNERABLE, (Object)vulnerable);
            float progress = (float)this.getTags / 170.0f;
            progress = Mth.outFromOrigin((float)progress, (float)0.0f, (float)1.0f);
            double chargeUpRadius = Mth.roundToward((double)progress, (double)8.0, (double)11.0);
            ParticleOptions particle = vulnerable ? (ParticleOptions)TravelopticsParticles.RED_STAR_INWARD_PARTICLE.get() : (ParticleOptions)TravelopticsParticles.PURPLE_STAR_INWARD_PARTICLE.get();
            AdvancedSphereParticleManager.spawnParticles(this.level(), this.getX(), this.getY() + 2.0, this.getZ(), 5, particle, ParticleDirection.INWARD, chargeUpRadius, true);
            Entity entity = this.getOwner();
            LivingEntity livingEntity = owner = entity instanceof LivingEntity ? (living = (LivingEntity)entity) : null;
            if (owner instanceof NightwardenBossEntity) {
                DyingStarParticleManager.spawnAOEWarningParticles(this);
            }
            if (this.getTags == 214) {
                MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)new BlastwaveParticleOptions(((SchoolType)SchoolRegistry.ENDER.get()).getTargetingColor(), this.getRadius()), (double)this.getX(), (double)(this.getY() + 2.0), (double)this.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
                AdvancedSphereParticleManager.spawnParticles(this.level(), this.getX(), this.getY() + 2.0, this.getZ(), 250, (ParticleOptions)TravelopticsParticles.PURPLE_STAR_OUTWARD_PARTICLE.get(), ParticleDirection.OUTWARD, this.getRadius(), true);
                float blastRadius = this.getRadius();
                AABB area = new AABB(this.position().y((double)(-blastRadius), (double)(-blastRadius), (double)(-blastRadius)), this.position().y((double)blastRadius, (double)blastRadius, (double)blastRadius));
                List targets = this.level().getNearbyEntities(LivingEntity.class, area, e -> e.isAlive() && e.getZ((Entity)this) <= (double)(blastRadius * blastRadius));
                for (LivingEntity target : targets) {
                    if (target == owner || owner != null && this.isAlly(owner, target) || this.isTamed(target)) continue;
                    DamageSources.applyDamage((Entity)target, (float)this.getDamage(), (DamageSource)((AbstractSpell)TravelopticsSpells.SUPERNOVA_SPELL.get()).getDamageSource((Entity)this, (Entity)owner));
                }
                SupermassiveBlackHoleEntity supermassiveBlackHole = new SupermassiveBlackHoleEntity(this.level());
                supermassiveBlackHole.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                supermassiveBlackHole.setRadius(this.getBlackholeRadius());
                supermassiveBlackHole.setDamage(this.getBlackholeDamage());
                supermassiveBlackHole.setMaxAge(this.getBlackholeDuration());
                if (owner != null) {
                    supermassiveBlackHole.addAdditionalSaveData((Entity)owner);
                }
                this.level().addFreshEntity((Entity)supermassiveBlackHole);
            }
        }
        if (this.getTags >= 215) {
            TOScreenShakeEntity.createScreenShake(this.level(), this.position(), this.getBlackholeRadius(), 0.1f, 15, 0, 5, false);
            TOScreenFlashEntity.createWhiteFlash(this.level(), this.position(), this.getBlackholeRadius(), 0.75f, 5, 15, 5, false);
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.SUPERNOVA_EXPLODE.get(), 3.0f, 1.0f);
            this.discard();
        }
    }

    public void onAntiMagic(MagicData playerMagicData) {
        if (this.isAntiMagicVulnerable() && !this.level().isClientSide) {
            LivingEntity owner;
            AdvancedSphereParticleManager.spawnParticles(this.level(), this.getX(), this.getY() + 2.0, this.getZ(), 250, (ParticleOptions)TravelopticsParticles.RED_STAR_OUTWARD_PARTICLE.get(), ParticleDirection.OUTWARD, this.getRadius(), true);
            Entity entity = this.getOwner();
            if (entity instanceof LivingEntity && (owner = (LivingEntity)entity).isAlive()) {
                float percent = Mth.outFromOrigin((float)this.getOwnerVoidDamagePercent(), (float)0.0f, (float)1.0f);
                float damage = owner.getMaxHealth() * percent;
                DamageSource voidDamage = this.level().damageSources().magic();
                owner.sendSystemMessage(voidDamage, damage);
            }
            this.updateTutorialInventoryAction(SoundEvents.LAVA_EXTINGUISH, 2.5f, 1.0f);
            this.discard();
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

    private PlayState animationPredicate(AnimationState<?> event) {
        event.getController().setAnimation(this.SUPERNOVA_ANIMATION);
        return PlayState.CONTINUE;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{this.controller});
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}

