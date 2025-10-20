/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob
 *  io.redspace.ironsspellbooks.util.ParticleHelper
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.monster.Enemy
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.AABB
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
package com.gametechbc.traveloptics.entity.mobs.voidshelf_golem;

import com.gametechbc.traveloptics.entity.mobs.void_tome.VoidTomeEntity;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import com.gametechbc.traveloptics.util.TravelopticsTags;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class VoidshelfGolemEntity
extends AbstractSpellCastingMob
implements Enemy {
    private static final EntityDataAccessor<Boolean> SUMMONED_ENTITY = SynchedEntityData.assignValue(VoidshelfGolemEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> CAMOUFLAGE_ENTITY = SynchedEntityData.assignValue(VoidshelfGolemEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> ACTIVATED = SynchedEntityData.assignValue(VoidshelfGolemEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SHOW_CANDLE = SynchedEntityData.assignValue(VoidshelfGolemEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private int deactivationDelay = 0;
    private boolean hasPlayedFallSound = false;
    private int tomeSpawnCooldown = 200;
    private int tomeSpawnWindup = -1;
    private static final int MAX_VOID_TOMES = 5;
    private static final int VOID_TOME_DETECTION_RADIUS = 28;
    private static final double TOME_LAUNCH_SPEED = 0.4;
    private int totalVoidTomesSpawned = 0;
    private int selfDestructDelay = -1;
    private static final int MAX_VOID_TOMES_SUMMONED = 5;
    private boolean earlySpawnDone = false;
    private int earlySpawnTimer = -1;
    private boolean allowEarlyFirstSpawn = false;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
    private final AnimationController<VoidshelfGolemEntity> animationController = new AnimationController((GeoAnimatable)this, "controller", 10, this::predicate);
    private final RawAnimation idle = RawAnimation.begin().thenLoop("blank");
    private final RawAnimation spawnVoidTome = RawAnimation.begin().thenPlay("spawn_void_tomes");
    private final RawAnimation death = RawAnimation.begin().thenPlay("flying_book_death");
    private RawAnimation animationToPlay = null;

    public VoidshelfGolemEntity(Level pLevel) {
        this((EntityType<? extends AbstractSpellCastingMob>)((EntityType)TravelopticsEntities.VOIDSHELF_GOLEM_ENTITY.get()), pLevel);
        this.setPersistenceRequired();
    }

    public VoidshelfGolemEntity(EntityType<? extends AbstractSpellCastingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPersistenceRequired();
        this.getArmorSlots = 10;
    }

    public void initiateCastSpell(AbstractSpell spell, int spellLevel) {
    }

    public boolean isSummonedEntity() {
        return (Boolean)this.makeBoundingBox.packDirty(SUMMONED_ENTITY);
    }

    public void setIsSummoned() {
        this.makeBoundingBox.packDirty(SUMMONED_ENTITY, (Object)true);
    }

    public boolean isCamouflage() {
        return (Boolean)this.makeBoundingBox.packDirty(CAMOUFLAGE_ENTITY);
    }

    public void setCamouflage(boolean value) {
        this.makeBoundingBox.packDirty(CAMOUFLAGE_ENTITY, (Object)value);
    }

    public boolean isActivated() {
        return (Boolean)this.makeBoundingBox.packDirty(ACTIVATED);
    }

    public void setActivated(boolean value) {
        this.makeBoundingBox.packDirty(ACTIVATED, (Object)value);
    }

    public boolean shouldShowCandle() {
        return (Boolean)this.makeBoundingBox.packDirty(SHOW_CANDLE);
    }

    public void setShouldShowCandle(boolean value) {
        this.makeBoundingBox.packDirty(SHOW_CANDLE, (Object)value);
    }

    public void setAllowEarlyFirstSpawn(boolean value) {
        this.allowEarlyFirstSpawn = value;
    }

    public boolean isEarlyFirstSpawnAllowed() {
        return this.allowEarlyFirstSpawn;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.makeBoundingBox.assignValue(SUMMONED_ENTITY, (Object)false);
        this.makeBoundingBox.assignValue(CAMOUFLAGE_ENTITY, (Object)true);
        this.makeBoundingBox.assignValue(ACTIVATED, (Object)false);
        this.makeBoundingBox.assignValue(SHOW_CANDLE, (Object)true);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.accept("TomeCooldown", this.tomeSpawnCooldown);
        tag.accept("TomeWindup", this.tomeSpawnWindup);
        tag.accept("HasPlayedFallSound", this.hasPlayedFallSound);
        if (this.isSummonedEntity()) {
            tag.accept("summoned", true);
        }
        tag.accept("camouflage", this.isCamouflage());
        tag.accept("ShowCandle", this.shouldShowCandle());
        tag.accept("TotalVoidTomes", this.totalVoidTomesSpawned);
        tag.accept("SelfDestructDelay", this.selfDestructDelay);
        tag.accept("Activated", this.isActivated());
        tag.accept("AllowEarlyFirstSpawn", this.allowEarlyFirstSpawn);
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.tomeSpawnCooldown = tag.copy("TomeCooldown");
        this.tomeSpawnWindup = tag.copy("TomeWindup");
        this.hasPlayedFallSound = tag.getBoolean("HasPlayedFallSound");
        if (tag.getBoolean("summoned")) {
            this.setIsSummoned();
        }
        if (tag.contains("camouflage")) {
            this.setCamouflage(tag.getBoolean("camouflage"));
        }
        if (tag.contains("ShowCandle")) {
            this.setShouldShowCandle(tag.getBoolean("ShowCandle"));
        }
        this.totalVoidTomesSpawned = tag.copy("TotalVoidTomes");
        this.selfDestructDelay = tag.copy("SelfDestructDelay");
        this.setActivated(tag.getBoolean("Activated"));
        if (tag.contains("AllowEarlyFirstSpawn")) {
            this.allowEarlyFirstSpawn = tag.getBoolean("AllowEarlyFirstSpawn");
        }
    }

    protected void registerGoals() {
        this.targetSelector.setControlFlag(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
        this.targetSelector.setControlFlag(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes().build(Attributes.ATTACK_DAMAGE, 3.0).build(Attributes.ATTACK_KNOCKBACK, 0.0).build(Attributes.KNOCKBACK_RESISTANCE, 100.0).build(Attributes.register, 60.0).build(Attributes.FOLLOW_RANGE, 18.0).build(Attributes.MOVEMENT_SPEED, 0.25).build(Attributes.FLYING_SPEED, 0.3).build((Attribute)AttributeRegistry.SPELL_POWER.get(), 1.0).build((Attribute)AttributeRegistry.SPELL_RESIST.get(), 1.0);
    }

    private AABB createVoidTomeDetectionAABB() {
        Vec3 pos = this.position();
        double halfSize = 14.0;
        return new AABB(pos.z - halfSize, pos.multiply - halfSize, pos.reverse - halfSize, pos.z + halfSize, pos.multiply + halfSize, pos.reverse + halfSize);
    }

    public void lerpMotion() {
        LivingEntity target;
        boolean hasValidTarget;
        super.lerpMotion();
        if (this.getTags % 3 == 0) {
            this.spawnCandleFlames();
        }
        boolean bl = hasValidTarget = (target = this.getTarget()) != null && target.isAlive();
        if (hasValidTarget) {
            if (!this.earlySpawnDone) {
                if (this.allowEarlyFirstSpawn) {
                    if (this.earlySpawnTimer < 0) {
                        this.earlySpawnTimer = 60;
                    } else {
                        --this.earlySpawnTimer;
                        if (this.earlySpawnTimer == 0) {
                            this.triggerAnim("shelf_casting", "spawn_void_tomes");
                            this.tomeSpawnWindup = 15;
                            this.earlySpawnDone = true;
                        }
                    }
                } else {
                    this.earlySpawnDone = true;
                }
            }
            long tomeCount = this.level().getNearbyEntities(VoidTomeEntity.class, this.createVoidTomeDetectionAABB()).stream().filter(LivingEntity::isAlive).filter(tome -> ((Object)tome).getClass() == VoidTomeEntity.class).count();
            if (this.isSummonedEntity() && this.totalVoidTomesSpawned >= 5 && this.selfDestructDelay < 0) {
                this.selfDestructDelay = 60;
            }
            if (tomeCount < 5L) {
                if (this.tomeSpawnWindup >= 0) {
                    --this.tomeSpawnWindup;
                    if (this.tomeSpawnWindup == 0) {
                        this.spawnVoidTome(new Vec3(0.0, 1.45, 1.5));
                        if (this.isSummonedEntity()) {
                            ++this.totalVoidTomesSpawned;
                            if (this.totalVoidTomesSpawned >= 5) {
                                this.selfDestructDelay = 60;
                            }
                        }
                    }
                } else if (this.earlySpawnDone) {
                    --this.tomeSpawnCooldown;
                    if (this.tomeSpawnCooldown <= 0) {
                        this.triggerAnim("shelf_casting", "spawn_void_tomes");
                        this.tomeSpawnWindup = 15;
                        int nearbyPlayers = this.getNearbyPlayerCount();
                        int extraPlayers = Math.max(0, nearbyPlayers - 1);
                        int cooldownReduction = extraPlayers * 20;
                        int scaledCooldown = 200 - cooldownReduction;
                        this.tomeSpawnCooldown = Math.max(40, scaledCooldown);
                    }
                }
            }
        }
        if (this.isSummonedEntity() && this.selfDestructDelay >= 0) {
            --this.selfDestructDelay;
            if (this.selfDestructDelay == 0) {
                this.discard();
            }
        }
    }

    protected void customServerAiStep() {
        boolean hasValidTarget;
        super.customServerAiStep();
        LivingEntity target = this.getTarget();
        boolean bl = hasValidTarget = target != null && target.isAlive();
        if (hasValidTarget) {
            this.deactivationDelay = 10;
            if (!this.isActivated()) {
                this.setActivated(true);
            }
        } else if (this.deactivationDelay > 0) {
            --this.deactivationDelay;
        } else if (this.isActivated()) {
            this.setActivated(false);
            this.earlySpawnDone = false;
            this.earlySpawnTimer = -1;
        }
    }

    private void spawnVoidTome(Vec3 localOffset) {
        VoidTomeEntity tome = new VoidTomeEntity(this.level());
        Vec3 forward = this.getLookAngle().multiply();
        Vec3 right = forward.z(new Vec3(0.0, 1.0, 0.0)).multiply();
        Vec3 up = new Vec3(0.0, 1.0, 0.0);
        Vec3 worldOffset = forward.x(localOffset.reverse).reverse(right.x(localOffset.z)).reverse(up.x(localOffset.multiply));
        Vec3 spawnPos = this.position().reverse(worldOffset);
        tome.setLevel(spawnPos);
        tome.setDeltaMovement(forward.x(0.4));
        if (this.isSummonedEntity()) {
            tome.setIsSummoned();
            tome.setTarget(this.getTarget());
        }
        this.level().addFreshEntity((Entity)tome);
    }

    public void spawnCandleFlames() {
        if (!this.shouldShowCandle()) {
            return;
        }
        Vec3 rootOffset = new Vec3(-0.2, 0.0, 0.0);
        double[][] candleOffsets = new double[][]{{0.25, 2.3, -0.1}, {-0.25, 2.33, -0.1}, {0.2, 2.36, 0.25}, {-0.2, 2.39, 0.25}};
        int index = this.getTags / 2 % candleOffsets.length;
        double[] offset = candleOffsets[index];
        Vec3 pos = this.position().reverse(this.getLookAngle().y((float)Math.PI).multiply().x(-0.1)).reverse(rootOffset).y(offset[0], offset[1], offset[2]);
        ParticleOptions particle = this.isCamouflage() ? ParticleHelper.EMBERS : TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE;
        this.level().addDestroyBlockEffect(particle, true, pos.z, pos.multiply, pos.reverse, 0.0, 0.0, 0.0);
    }

    public boolean sendSystemMessage(DamageSource source, float amount) {
        return super.sendSystemMessage(source, amount);
    }

    private int getNearbyPlayerCount() {
        double radius = this.getStandingEyeHeight(Attributes.FOLLOW_RANGE);
        return this.level().getNearbyEntities(Player.class, this.getBoundingBox().inflate(radius), player -> player.isAlive() && !player.isSpectator()).size();
    }

    protected boolean shouldDropLoot() {
        return super.shouldDropLoot() && !this.isSummonedEntity();
    }

    public boolean shouldDropExperience() {
        return super.shouldDropExperience() && !this.isSummonedEntity();
    }

    public boolean isPushable() {
        return false;
    }

    public boolean canBeCollidedWith() {
        return true;
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

    protected void getStandingEyeHeight(BlockPos pos, BlockState state) {
    }

    @Nullable
    protected SoundEvent giveExperiencePoints(DamageSource p_21239_) {
        return SoundEvents.WOOD_HIT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.WOOD_BREAK;
    }

    public boolean isDrinkingPotion() {
        return false;
    }

    public void startDrinkingPotion() {
    }

    public boolean readAdditionalSaveData(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean removeWhenFarAway(double p_21542_) {
        return false;
    }

    public boolean isAlliedTo(Entity pEntity) {
        return super.isAlliedTo(pEntity) || pEntity.getType().tryCast(TravelopticsTags.TEAM_THE_NIGHTWARDEN);
    }

    private PlayState predicate(AnimationState<VoidshelfGolemEntity> event) {
        if (this.animationToPlay != null) {
            event.getController().forceAnimationReset();
            event.getController().setAnimation(this.animationToPlay);
            this.animationToPlay = null;
        }
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(this.death);
        } else {
            event.getController().setAnimation(this.idle);
        }
        return PlayState.CONTINUE;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController[]{this.animationController});
        controllers.add(new AnimationController[]{new AnimationController((GeoAnimatable)this, "shelf_casting", 5, state -> PlayState.STOP).triggerableAnim("spawn_void_tomes", this.spawnVoidTome)});
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}

