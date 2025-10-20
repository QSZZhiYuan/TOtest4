/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.entity.IMagicEntity
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.api.registry.SpellRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.FlyingMoveControl
 *  net.minecraft.world.entity.ai.control.LookControl
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.ai.navigation.FlyingPathNavigation
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.monster.Enemy
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 *  software.bernie.geckolib.core.animatable.GeoAnimatable
 *  software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
 *  software.bernie.geckolib.core.animation.AnimatableManager$ControllerRegistrar
 *  software.bernie.geckolib.core.animation.AnimationController
 *  software.bernie.geckolib.core.animation.AnimationState
 *  software.bernie.geckolib.core.animation.RawAnimation
 *  software.bernie.geckolib.core.object.PlayState
 *  software.bernie.geckolib.util.GeckoLibUtil
 */
package com.gametechbc.traveloptics.entity.mobs.void_tome;

import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.api.particle.SphereParticleManager;
import com.gametechbc.traveloptics.entity.mobs.void_tome.VoidTomeComboGoal;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import com.gametechbc.traveloptics.util.TravelopticsTags;
import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class VoidTomeEntity
extends AbstractSpellCastingMob
implements Enemy {
    private int castingAnimationTimer = 0;
    private boolean hasPlayedFallSound = false;
    private static final EntityDataAccessor<Boolean> SUMMONED_ENTITY = SynchedEntityData.assignValue(VoidTomeEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
    private final AnimationController<VoidTomeEntity> animationController = new AnimationController((GeoAnimatable)this, "controller", 10, this::predicate);
    private final RawAnimation casting = RawAnimation.begin().thenPlay("flying_book_cast");
    private final RawAnimation casting_1 = RawAnimation.begin().thenPlay("flying_book_cast_1");
    private final RawAnimation casting_2 = RawAnimation.begin().thenPlay("flying_book_cast_2");
    private final RawAnimation casting_3 = RawAnimation.begin().thenPlay("flying_book_cast_3");
    private final RawAnimation death = RawAnimation.begin().thenPlay("flying_book_death");
    private RawAnimation animationToPlay = null;

    public VoidTomeEntity(Level pLevel) {
        this((EntityType<? extends AbstractSpellCastingMob>)((EntityType)TravelopticsEntities.VOID_TOME_ENTITY.get()), pLevel);
    }

    public VoidTomeEntity(EntityType<? extends AbstractSpellCastingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.getArmorSlots = 10;
        this.lookControl = this.createLookControl();
        this.moveControl = this.createMoveControl();
    }

    protected void registerGoals() {
        this.goalSelector.setControlFlag(1, (Goal)new VoidTomeComboGoal((IMagicEntity)this, 1.25, 40, 40).addCombo(1, List.of((AbstractSpell)SpellRegistry.MAGIC_MISSILE_SPELL.get(), (AbstractSpell)SpellRegistry.MAGIC_MISSILE_SPELL.get(), (AbstractSpell)SpellRegistry.MAGIC_ARROW_SPELL.get())).setComboCooldown(80, 80).setSpellQuality(0.6f, 0.8f).setSingleUseSpell((AbstractSpell)TravelopticsSpells.ORBITAL_VOID_SPELL.get(), 200, 400, 1, 1));
        this.goalSelector.setControlFlag(5, (Goal)new WaterAvoidingRandomFlyingGoal((PathfinderMob)this, 0.75));
        this.goalSelector.setControlFlag(9, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 3.0f, 1.0f));
        this.goalSelector.setControlFlag(10, (Goal)new LookAtPlayerGoal((Mob)this, Mob.class, 8.0f));
        this.targetSelector.setControlFlag(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
        this.targetSelector.setControlFlag(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes().build(Attributes.ATTACK_DAMAGE, 3.0).build(Attributes.ATTACK_KNOCKBACK, 0.0).build(Attributes.KNOCKBACK_RESISTANCE, 0.8).build(Attributes.register, 40.0).build(Attributes.FOLLOW_RANGE, 32.0).build(Attributes.MOVEMENT_SPEED, 0.25).build(Attributes.FLYING_SPEED, 0.3).build((Attribute)AttributeRegistry.SPELL_POWER.get(), 1.0).build((Attribute)AttributeRegistry.SPELL_RESIST.get(), 1.0);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        this.getY(true);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public void lerpMotion() {
        super.lerpMotion();
        if (this.castingAnimationTimer > 0) {
            --this.castingAnimationTimer;
        }
        if (this.level().isClientSide) {
            if (this.isDeadOrDying()) {
                this.spawnAdditionalParticles(0);
            } else if (this.isCasting()) {
                this.spawnAdditionalParticles(1);
            } else {
                this.spawnPassiveMagicParticles();
            }
        }
    }

    private void spawnPassiveMagicParticles() {
        float time = this.level().getGameTime() % 360L;
        float rotationSpeed = 6.0f;
        float radius = 1.5f;
        double extraYOffset = 0.5;
        float angle = time * rotationSpeed * ((float)Math.PI / 180);
        double offsetX = Math.cos(angle) * (double)radius;
        double offsetZ = Math.sin(angle) * (double)radius;
        double posY = this.getY() + extraYOffset;
        this.level().addDestroyBlockEffect(TravelopticsParticleHelper.LIGHT_PURPLE_GLOWING_ENCHANT, this.getX() + offsetX, posY, this.getZ() + offsetZ, 0.0, 0.0, 0.0);
    }

    private void spawnAdditionalParticles(int type) {
        double radius = 2.5;
        double height = 1.2;
        double angle = Math.random() * 2.0 * Math.PI;
        double distance = Math.random() * radius;
        double xOffset = Math.cos(angle) * distance;
        double zOffset = Math.sin(angle) * distance;
        double yOffset = (Math.random() - 0.5) * height;
        double fromX = this.getX() + xOffset;
        double fromY = this.getY() + 0.5 + yOffset;
        double fromZ = this.getZ() + zOffset;
        double toX = this.getX() - fromX;
        double toY = this.getY() + 0.5 - fromY;
        double toZ = this.getZ() - fromZ;
        double length = Math.sqrt(toX * toX + toY * toY + toZ * toZ);
        double speed = 0.15;
        toX = toX / length * speed;
        toY = toY / length * speed;
        toZ = toZ / length * speed;
        if (type == 0) {
            this.level().addDestroyBlockEffect(TravelopticsParticleHelper.LIGHT_PURPLE_GLOWING_ENCHANT, fromX, fromY, fromZ, toX, toY, toZ);
        }
        if (type == 1) {
            this.level().addDestroyBlockEffect(TravelopticsParticleHelper.LIGHT_RED_GLOWING_ENCHANT, fromX, fromY, fromZ, toX, toY, toZ);
        }
    }

    protected void customServerAiStep() {
        super.customServerAiStep();
        LivingEntity target = this.getTarget();
        if (target != null) {
            Vec3 direction = target.position().multiply(this.position()).multiply();
            float targetYaw = (float)(Mth.roundToward((double)direction.reverse, (double)direction.z) * 57.29577951308232) - 90.0f;
            this.setYRot(Mth.lengthSquared((float)this.getYRot(), (float)targetYaw, (float)15.0f));
            this.yBodyRot = this.getYRot();
            this.yHeadRot = this.getYRot();
        }
        if (!this.isInWaterOrBubble()) {
            BlockPos groundPos = this.blockPosition().below();
            while (this.level().isEmptyBlock(groundPos) && groundPos.getY() > this.level().getMinBuildHeight()) {
                groundPos = groundPos.below();
            }
            double groundY = groundPos.getY() + 1;
            double baseHoverOffset = 3.5;
            double hoverTargetY = groundY + baseHoverOffset;
            double amplitude = 0.25;
            double speed = 0.05;
            double phaseOffset = (double)this.getId() * 7.3;
            double deltaY = (hoverTargetY += amplitude * Math.sin(((double)this.getTags + phaseOffset) * speed)) - this.getY();
            if (Math.abs(deltaY) > 0.05) {
                Vec3 motion = this.getDeltaMovement();
                this.setIsInPowderSnow(motion.z, deltaY * 0.2, motion.reverse);
            }
            double maxVerticalSpeed = 0.08;
            this.setIsInPowderSnow(this.getDeltaMovement().z, Mth.outFromOrigin((double)this.getDeltaMovement().multiply, (double)(-maxVerticalSpeed), (double)maxVerticalSpeed), this.getDeltaMovement().reverse);
        }
    }

    protected void tickDeath() {
        ++this.deathTime;
        if (!this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.9, 1.0).y(0.0, -0.4, 0.0));
        } else if (!this.hasPlayedFallSound) {
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.FLYING_BOOK_DEATH_FALL.get(), 0.6f, 0.8f + this.getId.nextFloat() * 0.3f);
            this.hasPlayedFallSound = true;
        }
        switch (this.deathTime) {
            case 50: {
                this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.FLYING_BOOK_PAGE_TURN.get(), 0.4f, 0.8f + this.getId.nextFloat() * 0.3f);
                break;
            }
            case 56: {
                this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.FLYING_BOOK_PAGE_TURN.get(), 0.4f, 0.8f + this.getId.nextFloat() * 0.3f);
                break;
            }
            case 61: {
                this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.FLYING_BOOK_PAGE_TURN.get(), 0.4f, 0.8f + this.getId.nextFloat() * 0.3f);
                break;
            }
            case 68: {
                this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.FLYING_BOOK_BOOK_CLOSE.get(), 0.7f, 0.8f + this.getId.nextFloat() * 0.3f);
            }
        }
        if (this.deathTime == 78) {
            this.setLootTableSeed(Entity.RemovalReason.KILLED);
        }
    }

    public boolean sendSystemMessage(DamageSource source, float amount) {
        boolean result = super.sendSystemMessage(source, amount);
        if (result && this.isAlive() && Math.random() < 0.4) {
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.FLYING_BOOK_HURT.get(), 0.6f, 0.8f + this.getId.nextFloat() * 0.3f);
        }
        return result;
    }

    protected PathNavigation createNavigation(Level pLevel) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation((Mob)this, pLevel);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    protected LookControl createLookControl() {
        return new LookControl((Mob)this){

            protected float rotateTowards(float pFrom, float pTo, float pMaxDelta) {
                return super.rotateTowards(pFrom, pTo, pMaxDelta * 2.5f);
            }

            protected boolean resetXRotOnTick() {
                return !VoidTomeEntity.this.isCasting();
            }
        };
    }

    protected FlyingMoveControl createMoveControl() {
        return new FlyingMoveControl((Mob)this, 20, true){

            protected float rotlerp(float sourceAngle, float targetAngle, float maxChange) {
                double dz;
                double dx = this.getWantedY - this.getWantedX.getX();
                if (dx * dx + (dz = this.wantedZ - this.getWantedX.getZ()) * dz < 0.5) {
                    return sourceAngle;
                }
                return super.rotlerp(sourceAngle, targetAngle, maxChange * 0.25f);
            }
        };
    }

    public void castComplete() {
        super.castComplete();
        int animIndex = this.getRandom().nextInt(4);
        String spellAnim = switch (animIndex) {
            default -> "casting";
            case 1 -> "casting_1";
            case 2 -> "casting_2";
            case 3 -> "casting_3";
        };
        this.triggerAnim("book_casting", spellAnim);
        this.castingAnimationTimer = 10;
        SphereParticleManager.spawnParticles(this.level(), (Entity)this, 10, (ParticleOptions)ParticleTypes.END_ROD, ParticleDirection.OUTWARD, 3.5);
    }

    public boolean isSummonedEntity() {
        return (Boolean)this.makeBoundingBox.packDirty(SUMMONED_ENTITY);
    }

    public void setIsSummoned() {
        this.makeBoundingBox.packDirty(SUMMONED_ENTITY, (Object)true);
    }

    protected boolean shouldDropLoot() {
        return super.shouldDropLoot() && !this.isSummonedEntity();
    }

    public boolean shouldDropExperience() {
        return super.shouldDropExperience() && !this.isSummonedEntity();
    }

    public boolean canFreeze() {
        return false;
    }

    public boolean fireImmune() {
        return false;
    }

    public boolean canBeAffected(MobEffectInstance pEffectInstance) {
        return false;
    }

    public boolean dampensVibrations() {
        return true;
    }

    protected void setVariant(double y, boolean onGround, BlockState state, BlockPos pos) {
    }

    protected void getStandingEyeHeight(BlockPos pos, BlockState state) {
    }

    public boolean isDrinkingPotion() {
        return false;
    }

    public void startDrinkingPotion() {
    }

    public boolean isAlliedTo(Entity entityIn) {
        if (entityIn == this) {
            return true;
        }
        if (super.isAlliedTo(entityIn)) {
            return true;
        }
        if (entityIn.getType().tryCast(TravelopticsTags.TEAM_THE_NIGHTWARDEN)) {
            return this.getTeam() == null && entityIn.getTeam() == null;
        }
        return false;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.makeBoundingBox.assignValue(SUMMONED_ENTITY, (Object)false);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.accept("HasPlayedFallSound", this.hasPlayedFallSound);
        if (this.isSummonedEntity()) {
            tag.accept("summoned", true);
        }
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.hasPlayedFallSound = tag.getBoolean("HasPlayedFallSound");
        if (tag.getBoolean("summoned")) {
            this.setIsSummoned();
        }
    }

    private PlayState predicate(AnimationState<VoidTomeEntity> event) {
        if (this.animationToPlay != null) {
            event.getController().forceAnimationReset();
            event.getController().setAnimation(this.animationToPlay);
            this.animationToPlay = null;
        }
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(this.death);
        } else if (event.isMoving()) {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("flying_book_move"));
        } else {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("flying_book_idle"));
        }
        return PlayState.CONTINUE;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController[]{this.animationController});
        controllers.add(new AnimationController[]{new AnimationController((GeoAnimatable)this, "book_casting", 0, state -> PlayState.STOP).triggerableAnim("casting", this.casting).triggerableAnim("casting_1", this.casting_1).triggerableAnim("casting_2", this.casting_2).triggerableAnim("casting_3", this.casting_3)});
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}

