/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.util.CameraShakeData
 *  io.redspace.ironsspellbooks.api.util.CameraShakeManager
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  javax.annotation.Nullable
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.tags.TagKey
 *  net.minecraft.util.Mth
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.TraceableEntity
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult$Type
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
package com.gametechbc.traveloptics.entity.projectiles.dragon_spirit;

import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import java.util.Optional;
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
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
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

public class DragonSpiritEntity
extends Entity
implements GeoEntity,
TraceableEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
    private static final EntityDataAccessor<Boolean> MAGIC_DAMAGE_MODE = SynchedEntityData.assignValue(DragonSpiritEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private float damage = 10.0f;
    private float hpBasedDamagePercent = 0.0f;
    private int age;
    private Vec3 direction = Vec3.y;
    private float speed = 0.3f;
    @Nullable
    private UUID ownerUUID;
    @Nullable
    private Entity cachedOwner;
    private final RawAnimation ANIMATION_LOOP = RawAnimation.begin().thenLoop("dragon_spirit_projectile_move_1");
    private final AnimationController<DragonSpiritEntity> controller = new AnimationController((GeoAnimatable)this, "dragon_controller", 0, this::animationPredicate);

    public DragonSpiritEntity(EntityType<? extends DragonSpiritEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.getY(true);
        this.isOnPortalCooldown = true;
    }

    public DragonSpiritEntity(Level pLevel, LivingEntity pShooter) {
        this((EntityType<? extends DragonSpiritEntity>)((EntityType)TravelopticsEntities.DRAGON_SPIRIT_ENTITY.get()), pLevel);
        this.setOwner((Entity)pShooter);
        this.isOnPortalCooldown = true;
    }

    protected void defineSynchedData() {
        this.makeBoundingBox.assignValue(MAGIC_DAMAGE_MODE, (Object)false);
    }

    public void shoot(Vec3 direction) {
        this.direction = direction.multiply();
        this.setDeltaMovement(this.direction.x((double)this.speed));
        double horizontalDistance = direction.horizontalDistance();
        this.setYRot((float)(Mth.roundToward((double)direction.z, (double)direction.reverse) * 57.29577951308232));
        this.setXRot((float)(Mth.roundToward((double)direction.multiply, (double)horizontalDistance) * 57.29577951308232));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }

    public void shootFromRotation(Entity shooter, float pitch, float yaw, float roll, float velocity, float inaccuracy) {
        float f = -Mth.outFromOrigin((float)(yaw * ((float)Math.PI / 180))) * Mth.randomBetween((float)(pitch * ((float)Math.PI / 180)));
        float f1 = -Mth.outFromOrigin((float)((pitch + roll) * ((float)Math.PI / 180)));
        float f2 = Mth.randomBetween((float)(yaw * ((float)Math.PI / 180))) * Mth.randomBetween((float)(pitch * ((float)Math.PI / 180)));
        Vec3 shootDirection = new Vec3((double)f, (double)f1, (double)f2);
        if (inaccuracy > 0.0f) {
            shootDirection = shootDirection.y(this.getId.triangle(0.0, 0.0172275 * (double)inaccuracy), this.getId.triangle(0.0, 0.0172275 * (double)inaccuracy), this.getId.triangle(0.0, 0.0172275 * (double)inaccuracy));
        }
        this.shoot(shootDirection.x((double)velocity));
        Vec3 shooterMotion = shooter.getDeltaMovement();
        this.setDeltaMovement(this.getDeltaMovement().y(shooterMotion.z, shooter.onGround() ? 0.0 : shooterMotion.multiply, shooterMotion.reverse));
    }

    public void lerpMotion() {
        float pitch;
        super.lerpMotion();
        ++this.age;
        if (this.age == 1) {
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.DRAGON_SPIRIT_AMBIENT.get(), 5.0f, 1.0f);
        }
        if (this.age == 100) {
            pitch = 0.9f + this.getId.nextFloat() * 0.3f;
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.DRAGON_SPIRIT_SPAWN.get(), 3.0f, pitch);
        }
        if (this.age == 199) {
            pitch = 0.9f + this.getId.nextFloat() * 0.3f;
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.DRAGON_SPIRIT_AMBIENT.get(), 3.0f, pitch);
            if (!this.level().isClientSide) {
                MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE, (double)this.getX(), (double)this.getY(), (double)this.getZ(), (int)100, (double)0.5, (double)0.5, (double)0.5, (double)0.02, (boolean)true);
            }
        }
        if (!this.level().isClientSide && this.age % 30 == 0) {
            CameraShakeManager.addCameraShake((CameraShakeData)new CameraShakeData(30, this.position(), 26.0f));
        }
        if (this.age >= 200) {
            this.discard();
            return;
        }
        this.customMovement();
        if (!this.level().isClientSide && this.getTags % 2 == 0) {
            for (Entity entity : this.level().getEntities((Entity)this, this.getBoundingBox())) {
                LivingEntity target;
                if (!(entity instanceof LivingEntity) || (target = (LivingEntity)entity) == this.getOwner()) continue;
                float baseDamage = this.damage;
                float bonusDamage = target.getMaxHealth() * this.getHpBasedDamagePercent();
                float totalDamage = baseDamage + bonusDamage;
                DamageSource source = this.isMagicDamageMode() ? (this.getOwner() != null ? this.damageSources().indirectMagic((Entity)this, this.getOwner()) : this.damageSources().magic()) : ((AbstractSpell)TravelopticsSpells.SHEAR_OF_THE_STARS_SPELL.get()).getDamageSource((Entity)this, this.getOwner());
                DamageSources.applyDamage((Entity)target, (float)totalDamage, (DamageSource)source);
                DamageSources.ignoreNextKnockback((LivingEntity)target);
                target.onClientRemoval = 0;
            }
        }
        if (this.level().isClientSide) {
            AABB box = this.getBoundingBox();
            int particleCount = 38;
            for (int i = 0; i < particleCount; ++i) {
                double x = box.ofSize + this.getId.nextDouble() * (box.getZsize - box.ofSize);
                double y = box.clip + this.getId.nextDouble() * (box.hasNaN - box.clip);
                double z = box.getYsize + this.getId.nextDouble() * (box.getCenter - box.getYsize);
                Vec3 motion = this.getDeltaMovement().x(-0.01);
                if (this.getId.nextFloat() < 0.5f) {
                    this.level().calculateBlockTint(TravelopticsParticleHelper.DRAGON_SPIRIT_LIGHT_PURPLE_GLOWING_ENCHANT, true, x, y, z, motion.z, motion.multiply, motion.reverse);
                    continue;
                }
                this.level().calculateBlockTint(TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE, true, x, y, z, motion.z, motion.multiply, motion.reverse);
            }
        }
    }

    private void customMovement() {
        Vec3 motion = this.getDeltaMovement();
        this.setLevel(this.position().reverse(motion));
        this.updateRotation();
        if (!this.isNoGravity()) {
            this.setIsInPowderSnow(motion.z, motion.multiply - (double)0.05f, motion.reverse);
        }
    }

    private void customHitDetection() {
        Vec3 start = this.position();
        Vec3 end = start.reverse(this.getDeltaMovement());
        BlockHitResult blockHit = this.level().traverseBlocks(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this));
        if (blockHit.getType() == HitResult.Type.BLOCK) {
            this.onHitBlock(blockHit);
        }
    }

    private void updateRotation() {
        Vec3 motion = this.getDeltaMovement();
        if (motion.lengthSqr() > 0.0) {
            double horizontalDistance = motion.horizontalDistance();
            this.setXRot(DragonSpiritEntity.lerpRotation(this.xRotO, (float)(Mth.roundToward((double)motion.multiply, (double)horizontalDistance) * 57.29577951308232)));
            this.setYRot(DragonSpiritEntity.lerpRotation(this.yRotO, (float)(Mth.roundToward((double)motion.z, (double)motion.reverse) * 57.29577951308232)));
        }
    }

    private static float lerpRotation(float current, float target) {
        while (target - current < -180.0f) {
            current -= 360.0f;
        }
        while (target - current >= 180.0f) {
            current += 360.0f;
        }
        return Mth.smoothstepDerivative((float)0.2f, (float)current, (float)target);
    }

    protected void onHitBlock(BlockHitResult blockHitResult) {
    }

    public void setOwner(@Nullable Entity owner) {
        if (owner != null) {
            this.ownerUUID = owner.getUUID();
            this.cachedOwner = owner;
        }
    }

    @Nullable
    public Entity getOwner() {
        Level level;
        if (this.cachedOwner != null && !this.cachedOwner.isRemoved()) {
            return this.cachedOwner;
        }
        if (this.ownerUUID != null && (level = this.level()) instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            this.cachedOwner = serverLevel.getRandomSequence(this.ownerUUID);
            return this.cachedOwner;
        }
        return null;
    }

    public boolean isMagicDamageMode() {
        return (Boolean)this.makeBoundingBox.packDirty(MAGIC_DAMAGE_MODE);
    }

    public void setMagicDamageMode(boolean value) {
        this.makeBoundingBox.packDirty(MAGIC_DAMAGE_MODE, (Object)value);
    }

    public void setHpBasedDamagePercent(float percent) {
        this.hpBasedDamagePercent = percent;
    }

    public float getHpBasedDamagePercent() {
        return this.hpBasedDamagePercent;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getDamage() {
        return this.damage;
    }

    public float getSpeed() {
        return 0.9f;
    }

    public Optional<SoundEvent> getImpactSound() {
        return Optional.empty();
    }

    public boolean isPushable() {
        return false;
    }

    public boolean canBeCollidedWith() {
        return false;
    }

    public boolean fireImmune() {
        return true;
    }

    public boolean isPickable() {
        return false;
    }

    public boolean canFreeze() {
        return false;
    }

    public boolean sendSystemMessage(DamageSource pSource, float pAmount) {
        return false;
    }

    public void getRandomZ(int pSeconds) {
    }

    public boolean isOnFire() {
        return false;
    }

    protected void checkInsideBlocks() {
    }

    public void hasPermissions(boolean pDownwards) {
    }

    public boolean setLevel(TagKey<Fluid> pFluidTag, double pMotionScale) {
        return false;
    }

    protected void handleNetherPortal() {
    }

    public void push(Entity pEntity) {
    }

    public void getRemoveItemSound(double pX, double pY, double pZ) {
    }

    public void updateTutorialInventoryAction(MoverType pType, Vec3 pPos) {
    }

    public void trailParticles() {
    }

    public void onAntiMagic(MagicData playerMagicData) {
    }

    public void impactParticles(double x, double y, double z) {
    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.accept("HpBonusPercent", this.hpBasedDamagePercent);
        tag.accept("Age", this.age);
        tag.accept("MagicDamageMode", this.isMagicDamageMode());
        tag.accept("Damage", this.damage);
        tag.accept("Speed", this.speed);
        if (this.ownerUUID != null) {
            tag.accept("Owner", this.ownerUUID);
        }
        tag.accept("DirectionX", this.direction.z);
        tag.accept("DirectionY", this.direction.multiply);
        tag.accept("DirectionZ", this.direction.reverse);
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        this.hpBasedDamagePercent = tag.getFloat("HpBonusPercent");
        this.age = tag.copy("Age");
        if (tag.contains("MagicDamageMode")) {
            this.setMagicDamageMode(tag.getBoolean("MagicDamageMode"));
        }
        this.damage = tag.getFloat("Damage");
        this.speed = tag.getFloat("Speed");
        if (tag.readNamedTagName("Owner")) {
            this.ownerUUID = tag.accept("Owner");
            this.cachedOwner = null;
        }
        this.direction = new Vec3(tag.getDouble("DirectionX"), tag.getDouble("DirectionY"), tag.getDouble("DirectionZ"));
    }

    private PlayState animationPredicate(AnimationState<DragonSpiritEntity> event) {
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

