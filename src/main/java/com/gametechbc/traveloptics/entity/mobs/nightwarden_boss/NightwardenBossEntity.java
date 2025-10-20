/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.client.particle.RingParticle$EnumRingBehavior
 *  com.github.L_Ender.cataclysm.client.particle.RingParticle$RingData
 *  io.redspace.ironsspellbooks.api.entity.IMagicEntity
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.network.IClientEventEntity
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.registry.SpellRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.SchoolType
 *  io.redspace.ironsspellbooks.api.spells.SpellData
 *  io.redspace.ironsspellbooks.api.util.CameraShakeData
 *  io.redspace.ironsspellbooks.api.util.CameraShakeManager
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible
 *  io.redspace.ironsspellbooks.entity.mobs.IAnimatedAttacker
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob
 *  io.redspace.ironsspellbooks.entity.mobs.goals.PatrolNearLocationGoal
 *  io.redspace.ironsspellbooks.network.ClientboundEntityEvent
 *  io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions
 *  io.redspace.ironsspellbooks.setup.Messages
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.world.BossEvent$BossBarColor
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
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
 *  net.minecraft.world.entity.ai.control.LookControl
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.WrappedGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.AbstractIllager
 *  net.minecraft.world.entity.monster.Enemy
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.storage.loot.LootParams
 *  net.minecraft.world.level.storage.loot.LootParams$Builder
 *  net.minecraft.world.level.storage.loot.LootTable
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParams
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.network.NetworkHooks
 *  net.minecraftforge.network.PlayMessages$SpawnEntity
 *  software.bernie.geckolib.core.animatable.GeoAnimatable
 *  software.bernie.geckolib.core.animation.AnimatableManager$ControllerRegistrar
 *  software.bernie.geckolib.core.animation.AnimationController
 *  software.bernie.geckolib.core.animation.AnimationController$State
 *  software.bernie.geckolib.core.animation.AnimationState
 *  software.bernie.geckolib.core.animation.RawAnimation
 *  software.bernie.geckolib.core.object.PlayState
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss;

import com.gametechbc.traveloptics.api.entity.TravelopticsBossInfo;
import com.gametechbc.traveloptics.api.entity.ai.ComboWizardAttackGoal;
import com.gametechbc.traveloptics.api.entity.ai.HurtByNearestTargetGoal;
import com.gametechbc.traveloptics.api.utils.TOEntityUtils;
import com.gametechbc.traveloptics.api.utils.TOGeneralUtils;
import com.gametechbc.traveloptics.config.EntityConfig;
import com.gametechbc.traveloptics.entity.misc.TOScreenShakeEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenAdaptRegistry;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenAttackGoal;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenReturnHomeHandler;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.animated_particle.NightwardenPhaseThreeSwapAnimatedParticle;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.anti_cheese.NightwardenAntiCCHandler;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.anti_cheese.NightwardenAntiCheeseTeleportHandler;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.anti_cheese.NightwardenAntiCheeseVoidTeleportHandler;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.anti_cheese.NightwardenSummonCheeseHandler;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.misc.NightwardenMusicManager;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.misc.NightwardenTargetVelocityTracker;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_defeated.NightwardenDefeatedEntity;
import com.gametechbc.traveloptics.entity.mobs.void_tome.VoidTomeEntity;
import com.gametechbc.traveloptics.entity.projectiles.supernova.dying_star.DyingStarEntity;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsMessages;
import com.gametechbc.traveloptics.init.TravelopticsParticles;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.gametechbc.traveloptics.util.TOColors;
import com.gametechbc.traveloptics.util.TravelopticsTags;
import com.github.L_Ender.cataclysm.client.particle.RingParticle;
import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.network.IClientEventEntity;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.mobs.IAnimatedAttacker;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.goals.PatrolNearLocationGoal;
import io.redspace.ironsspellbooks.network.ClientboundEntityEvent;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.setup.Messages;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
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
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class NightwardenBossEntity
extends AbstractSpellCastingMob
implements Enemy,
IAnimatedAttacker,
IClientEventEntity,
AntiMagicSusceptible {
    private boolean collectedLoot = false;
    private final List<ItemStack> deathItems = new ArrayList<ItemStack>();
    public static final byte STOP_MUSIC = 0;
    public static final byte START_MUSIC = 1;
    private static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.assignValue(NightwardenBossEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private static final EntityDataAccessor<CompoundTag> DAMAGE_ADAPTATION = SynchedEntityData.assignValue(NightwardenBossEntity.class, (EntityDataSerializer)EntityDataSerializers.COMPOUND_TAG);
    private static final EntityDataAccessor<BlockPos> HOME_POS = SynchedEntityData.assignValue(NightwardenBossEntity.class, (EntityDataSerializer)EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Float> RESURGENCE = SynchedEntityData.assignValue(NightwardenBossEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> SHOULD_SHOW_WINGS = SynchedEntityData.assignValue(NightwardenBossEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SHOULD_SHOW_WEAPON = SynchedEntityData.assignValue(NightwardenBossEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_PENDING_RESURGENCE = SynchedEntityData.assignValue(NightwardenBossEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SHOULD_SPAWN_RING_PARTICLE = SynchedEntityData.assignValue(NightwardenBossEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private final TravelopticsBossInfo bossEvent = new TravelopticsBossInfo(this.getDisplayName(), BossEvent.BossBarColor.PURPLE, true, 0);
    private final TravelopticsBossInfo bossEvent1 = new TravelopticsBossInfo((Component)Component.translatable((String)"entity.cataclysm.rage_meter"), BossEvent.BossBarColor.PURPLE, false, 1);
    private final NightwardenReturnHomeHandler returnHomeHandler = new NightwardenReturnHomeHandler();
    private int transitionElapsed = 0;
    private int transitionTime = 140;
    private static final int TRANSITION_ONE_DURATION = 257;
    private static final int TRANSITION_TWO_DURATION = 410;
    public boolean isMeleeing;
    private int wingVisibilityTimer = 0;
    private int weaponVisibilityTimer = 0;
    private int ringParticleTimer = 0;
    private int stuckCounter = 0;
    private static final int STUCK_TICKS_THRESHOLD = 60;
    private int destroyBlockDelay = 0;
    private int bookshelfSpawnCooldown = 0;
    private int resurgenceCooldown = 0;
    private static final int resurgenceIFrameDuration = 10;
    private boolean hasShownCounterspellHint = false;
    private int counterspellHintTicks = 0;
    private boolean lockRotation = false;
    private static final float ADAPTED_DAMAGE_REDUCTION = 0.2f;
    private static final float IMMUNE_DAMAGE_REDUCTION = 0.1f;
    private static final float PHASE_TWO_INVALID_REDUCTION = 0.1f;
    private final RawAnimation phase_transition_1_animation = RawAnimation.begin().thenPlay("nightwarden_phase_swap_melee_1");
    private final RawAnimation phase_transition_2_animation = RawAnimation.begin().thenPlay("nightwarden_phase_swap_magic");
    private final RawAnimation deathAnimation = RawAnimation.begin().thenPlay("nightwarden_death");
    private final AnimationController<NightwardenBossEntity> meleeController = new AnimationController((GeoAnimatable)this, "nightwarden_melee", 0, this::meleePredicate);
    private static final Set<String> NO_HEAD_ANIMATION_IDS = Set.of("nightwarden_teleport_spin", "nightwarden_scythe_throw", "nightwarden_scythe_right_swing", "nightwarden_scythe_left_swing", "nightwarden_scythe_ten_combo", "nightwarden_scythe_jump_combo", "nightwarden_scythe_big_slam", "nightwarden_scythe_ult", "nightwarden_scythe_spin_forward", "nightwarden_scythe_clone_slashes", "nightwarden_scythe_ground_slam_clone", "nightwarden_scythe_spinning_clone", "nightwarden_scythe_spin_forward_phase_three");
    RawAnimation animationToPlay = null;
    private String lastAnimationId = null;

    public NightwardenBossEntity(Level pLevel) {
        this((EntityType<? extends AbstractSpellCastingMob>)((EntityType)TravelopticsEntities.NIGHTWARDEN_BOSS.get()), pLevel);
        this.setPersistenceRequired();
        this.isOnPortalCooldown = true;
    }

    public NightwardenBossEntity(PlayMessages.SpawnEntity packet, Level world) {
        this((EntityType<? extends AbstractSpellCastingMob>)((EntityType)TravelopticsEntities.NIGHTWARDEN_BOSS.get()), world);
    }

    public NightwardenBossEntity(EntityType<? extends AbstractSpellCastingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPersistenceRequired();
        this.getArmorSlots = 60;
        this.setMaxUpStep(2.0f);
        this.isOnPortalCooldown = true;
        this.lookControl = this.createLookControl();
        this.moveControl = this.createMoveControl();
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.makeBoundingBox.assignValue(PHASE, (Object)Phase.FIRST.value);
        this.makeBoundingBox.assignValue(DAMAGE_ADAPTATION, (Object)new CompoundTag());
        this.makeBoundingBox.assignValue(HOME_POS, (Object)BlockPos.relative);
        this.makeBoundingBox.assignValue(RESURGENCE, (Object)Float.valueOf(0.0f));
        this.makeBoundingBox.assignValue(SHOULD_SHOW_WINGS, (Object)false);
        this.makeBoundingBox.assignValue(SHOULD_SHOW_WEAPON, (Object)false);
        this.makeBoundingBox.assignValue(HAS_PENDING_RESURGENCE, (Object)false);
        this.makeBoundingBox.assignValue(SHOULD_SPAWN_RING_PARTICLE, (Object)false);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.accept("phase", ((Integer)this.makeBoundingBox.packDirty(PHASE)).intValue());
        pCompound.accept("transitionTime", this.transitionTime);
        pCompound.accept("DamageAdaptation", (Tag)this.makeBoundingBox.packDirty(DAMAGE_ADAPTATION));
        pCompound.accept("ResurgenceCounter", this.getResurgenceCounter());
        pCompound.accept("HasPendingResurgence", ((Boolean)this.makeBoundingBox.packDirty(HAS_PENDING_RESURGENCE)).booleanValue());
        BlockPos home = this.getHomePos();
        pCompound.accept("HomePosX", home.setX());
        pCompound.accept("HomePosY", home.getY());
        pCompound.accept("HomePosZ", home.getZ());
        pCompound.accept("BookshelfSpawnCooldown", this.bookshelfSpawnCooldown);
        pCompound.accept("HasShownCounterspellHint", this.hasShownCounterspellHint);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (this.hasCustomName()) {
            this.bossEvent.setDarkenScreen(this.getDisplayName());
        }
        int savedPhase = pCompound.copy("phase");
        this.setPhase(Phase.values()[savedPhase]);
        if (this.isPhaseOneTransitioning() || this.isPhaseTwoTransitioning()) {
            this.transitionTime = pCompound.copy("transitionTime");
        }
        if (this.isPhase(Phase.SECOND)) {
            this.setPhaseTwoGoals();
        } else if (this.isPhase(Phase.THIRD)) {
            this.setPhaseThreeGoals();
        }
        if (pCompound.contains("DamageAdaptation")) {
            this.makeBoundingBox.packDirty(DAMAGE_ADAPTATION, (Object)pCompound.getCompound("DamageAdaptation"));
        }
        if (pCompound.readNamedTagName("ResurgenceCounter", 5)) {
            this.setResurgenceCounter(pCompound.getFloat("ResurgenceCounter"));
        }
        if (pCompound.contains("HasPendingResurgence")) {
            this.makeBoundingBox.packDirty(HAS_PENDING_RESURGENCE, (Object)pCompound.getBoolean("HasPendingResurgence"));
        }
        int x = pCompound.copy("HomePosX");
        int y = pCompound.copy("HomePosY");
        int z = pCompound.copy("HomePosZ");
        this.setHomePos(new BlockPos(x, y, z));
        this.bookshelfSpawnCooldown = pCompound.copy("BookshelfSpawnCooldown");
        if (pCompound.contains("HasShownCounterspellHint")) {
            this.hasShownCounterspellHint = pCompound.getBoolean("HasShownCounterspellHint");
        }
    }

    public boolean isPhaseOneTransitioning() {
        return this.isPhase(Phase.TRANSITION_1);
    }

    public boolean isPhaseTwoTransitioning() {
        return this.isPhase(Phase.TRANSITION_2);
    }

    public boolean isPhaseTransitioning() {
        return this.isPhaseOneTransitioning() || this.isPhaseTwoTransitioning() || this.isPhase(Phase.DEATH);
    }

    private void setPhase(int phase) {
        this.makeBoundingBox.packDirty(PHASE, (Object)phase);
    }

    private void setPhase(Phase phase) {
        this.makeBoundingBox.packDirty(PHASE, (Object)phase.value);
    }

    public boolean isPhase(Phase phase) {
        return (Integer)this.makeBoundingBox.packDirty(PHASE) == phase.value;
    }

    public Phase getCurrentPhase() {
        int phaseValue = (Integer)this.makeBoundingBox.packDirty(PHASE);
        for (Phase phase : Phase.values()) {
            if (phase.value != phaseValue) continue;
            return phase;
        }
        return Phase.FIRST;
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes().build(Attributes.register, 600.0).build(Attributes.ATTACK_DAMAGE, 18.0).build(Attributes.FOLLOW_RANGE, 70.0).build(Attributes.MOVEMENT_SPEED, 0.2).build(Attributes.ARMOR, 10.0).build(Attributes.ATTACK_KNOCKBACK, 0.2).build((Attribute)AttributeRegistry.SPELL_POWER.get(), 1.5).build((Attribute)AttributeRegistry.SPELL_RESIST.get(), 1.0).build(Attributes.KNOCKBACK_RESISTANCE, 1.0);
    }

    public void handleClientEvent(byte eventId) {
        switch (eventId) {
            case 0: {
                NightwardenMusicManager.stop(this);
                break;
            }
            case 1: {
                NightwardenMusicManager.createOrResumeInstance(this);
            }
        }
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag dataTag) {
        this.setHomePos(this.blockPosition());
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData, dataTag);
    }

    public void setHomePos(BlockPos homePos) {
        this.makeBoundingBox.packDirty(HOME_POS, (Object)homePos);
    }

    public BlockPos getHomePos() {
        return (BlockPos)this.makeBoundingBox.packDirty(HOME_POS);
    }

    private float getAdaptiveCap() {
        return this.getMaxHealth() / 12.0f;
    }

    public float getResurgenceCounter() {
        return ((Float)this.makeBoundingBox.packDirty(RESURGENCE)).floatValue();
    }

    private float getMaxResurgenceCounter() {
        return Math.max(50.0f, this.getMaxHealth() * 0.3f);
    }

    public void setResurgenceCounter(float value) {
        this.makeBoundingBox.packDirty(RESURGENCE, (Object)Float.valueOf(value));
    }

    public void addResurgence(float amount, boolean applyCooldown, boolean showDebug) {
        float max = this.getMaxResurgenceCounter();
        float current = this.getResurgenceCounter();
        if (current < max) {
            float updated = Math.min(current + amount, max);
            this.setResurgenceCounter(updated);
            if (updated >= max) {
                this.makeBoundingBox.packDirty(HAS_PENDING_RESURGENCE, (Object)true);
            }
            if (applyCooldown) {
                this.resurgenceCooldown = 10;
            }
            if (showDebug) {
                System.out.printf("[Resurgence Debug] Added: %.2f | New: %.2f / %.2f | ApplyCooldown: %b | Cooldown: %d%n", Float.valueOf(amount), Float.valueOf(updated), Float.valueOf(max), applyCooldown, this.resurgenceCooldown);
            }
        }
    }

    public float getResurgenceScaledAttack() {
        float base = (float)this.getStandingEyeHeight(Attributes.ATTACK_DAMAGE);
        float progress = Mth.outFromOrigin((float)(this.getResurgenceCounter() / this.getMaxResurgenceCounter()), (float)0.0f, (float)1.0f);
        float bonusMultiplier = 1.0f + 0.3f * progress;
        return base * bonusMultiplier;
    }

    public float getResurgenceScaledLifesteal(float baseLifesteal) {
        float progress = Mth.outFromOrigin((float)(this.getResurgenceCounter() / this.getMaxResurgenceCounter()), (float)0.0f, (float)1.0f);
        float bonusMultiplier = 1.0f + 0.25f * progress;
        return baseLifesteal * bonusMultiplier;
    }

    public boolean hasPendingResurgence() {
        return (Boolean)this.makeBoundingBox.packDirty(HAS_PENDING_RESURGENCE);
    }

    public float getDamageCap() {
        return ((Double)EntityConfig.nightwardenDynamicDamageCap.get()).floatValue();
    }

    public void setShouldShowWings(boolean shouldShow, int durationTicks) {
        this.makeBoundingBox.packDirty(SHOULD_SHOW_WINGS, (Object)shouldShow);
        if (shouldShow) {
            this.wingVisibilityTimer = durationTicks;
        }
    }

    public boolean isShowingWings() {
        return (Boolean)this.makeBoundingBox.packDirty(SHOULD_SHOW_WINGS);
    }

    public void setShouldShowWeapon(boolean shouldShow, int durationTicks) {
        this.makeBoundingBox.packDirty(SHOULD_SHOW_WEAPON, (Object)shouldShow);
        if (shouldShow) {
            this.weaponVisibilityTimer = durationTicks;
        }
    }

    public boolean isShowingWeapon() {
        return (Boolean)this.makeBoundingBox.packDirty(SHOULD_SHOW_WEAPON);
    }

    public boolean shouldSpawnRingParticle() {
        return (Boolean)this.makeBoundingBox.packDirty(SHOULD_SPAWN_RING_PARTICLE);
    }

    public void setShouldSpawnRingParticle(boolean shouldSpawn, int durationTicks) {
        this.makeBoundingBox.packDirty(SHOULD_SPAWN_RING_PARTICLE, (Object)shouldSpawn);
        if (shouldSpawn) {
            this.ringParticleTimer = durationTicks;
        }
    }

    public void setLockRotation(boolean dashing) {
        this.lockRotation = dashing;
    }

    public boolean isRotationLocked() {
        return this.lockRotation;
    }

    private ComboWizardAttackGoal getMagicCombatGoal() {
        return new ComboWizardAttackGoal((IMagicEntity)this, 1.25, 40, 40).addCombo(1, List.of((AbstractSpell)SpellRegistry.MAGIC_MISSILE_SPELL.get(), (AbstractSpell)SpellRegistry.MAGIC_MISSILE_SPELL.get(), (AbstractSpell)SpellRegistry.MAGIC_ARROW_SPELL.get())).addCombo(3, List.of((AbstractSpell)SpellRegistry.MAGIC_ARROW_SPELL.get(), (AbstractSpell)TravelopticsSpells.VORTEX_PUNCH_SPELL.get())).addCombo(4, List.of((AbstractSpell)TravelopticsSpells.VORTEX_PUNCH_SPELL.get(), (AbstractSpell)SpellRegistry.STARFALL_SPELL.get())).setComboCooldown(80, 80).setSpellQuality(1.0f, 1.2f).setSingleUseSpell((AbstractSpell)TravelopticsSpells.ORBITAL_VOID_SPELL.get(), 200, 400, 3, 4);
    }

    private NightwardenAttackGoal getMeleeCombatGoal() {
        return (NightwardenAttackGoal)new NightwardenAttackGoal(this, 1.0, 50, 75, 35.0f).setComboChance(1.0f).setMeleeBias(1.0f, 1.0f).setMeleeAttackInverval(20, 30).setAllowFleeing(false).setSpellQuality(0.3f, 0.5f).setSpells(List.of(), List.of(), List.of(), List.of());
    }

    protected void registerGoals() {
        this.setPhaseOneGoals();
        this.targetSelector.setControlFlag(1, (Goal)new HurtByNearestTargetGoal((PathfinderMob)this, new Class[0]));
        this.targetSelector.setControlFlag(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
        this.targetSelector.setControlFlag(5, (Goal)new NearestAttackableTargetGoal((Mob)this, AbstractIllager.class, true));
    }

    private void setPhaseOneGoals() {
        this.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
        this.goalSelector.setControlFlag(x -> true);
        this.goalSelector.setControlFlag(1, (Goal)new FloatGoal((Mob)this));
        this.goalSelector.setControlFlag(3, (Goal)this.getMagicCombatGoal());
        this.goalSelector.setControlFlag(5, (Goal)new PatrolNearLocationGoal((PathfinderMob)this, 32.0f, (double)0.9f));
        this.goalSelector.setControlFlag(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0f));
    }

    private void setPhaseTwoGoals() {
        this.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
        this.goalSelector.setControlFlag(x -> true);
        this.goalSelector.setControlFlag(0, (Goal)new FloatGoal((Mob)this));
        this.goalSelector.setControlFlag(3, (Goal)this.getMeleeCombatGoal());
        this.goalSelector.setControlFlag(5, (Goal)new PatrolNearLocationGoal((PathfinderMob)this, 32.0f, (double)0.9f));
        this.goalSelector.setControlFlag(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0f));
    }

    private void setPhaseThreeGoals() {
        this.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
        this.goalSelector.setControlFlag(x -> true);
        this.goalSelector.setControlFlag(1, (Goal)new FloatGoal((Mob)this));
        this.goalSelector.setControlFlag(3, (Goal)this.getMeleeCombatGoal());
        this.goalSelector.setControlFlag(5, (Goal)new PatrolNearLocationGoal((PathfinderMob)this, 32.0f, (double)0.9f));
        this.goalSelector.setControlFlag(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0f));
        this.hasUsedSingleAttack = false;
    }

    public void setCustomName(@Nullable Component pName) {
        super.setCustomName(pName);
        this.bossEvent.setDarkenScreen(this.getDisplayName());
    }

    protected void triggerBreak(boolean doGoalStuff) {
        this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.BOSS_BREAK.get(), 1.5f, 1.0f);
        if (!this.level().isClientSide) {
            MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)new BlastwaveParticleOptions(((SchoolType)SchoolRegistry.ENDER.get()).getTargetingColor(), 5.0f), (double)this.getX(), (double)(this.getY() + (double)1.8f), (double)this.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)false);
        }
        if (doGoalStuff) {
            this.castComplete();
            this.cancelCast();
            this.getMagicCombatGoal().stop();
        }
    }

    public void lerpMotion() {
        super.lerpMotion();
        this.passiveTick();
        float phaseOneThreshold = this.getMaxHealth() * 0.8f;
        float phaseTwoThreshold = this.getMaxHealth() * 0.4f;
        if (!this.level().isClientSide) {
            if (this.isPhase(Phase.FIRST)) {
                if (this.getHealth() <= phaseOneThreshold) {
                    if (!this.isDeadOrDying()) {
                        this.setHealth(phaseOneThreshold);
                    }
                    this.triggerBreak(true);
                    this.setPhase(Phase.TRANSITION_1);
                    this.transitionTime = 257;
                    this.transitionElapsed = 0;
                    this.setInvulnerable(true);
                    this.triggerAnim("nightwarden_transition", "phase_1_to_2");
                }
            } else if (this.isPhase(Phase.TRANSITION_1)) {
                ++this.transitionElapsed;
                if (this.getTarget() != null) {
                    if (this.transitionElapsed <= 6 && this.transitionElapsed % 2 == 0) {
                        this.forceFaceTarget();
                    } else if (this.transitionElapsed >= 128 && this.transitionElapsed < 134 && this.transitionElapsed % 2 == 0) {
                        this.forceFaceTarget();
                    }
                }
                if (!this.level().isClientSide) {
                    this.createPhaseTransitionParticles(this.transitionElapsed);
                }
                this.handlePhaseOneToTwoTransition();
                if (this.transitionElapsed % 3 == 0 && this.transitionElapsed < 128) {
                    TravelopticsMessages.sendBossMessageToRange((Entity)this, (Component)Component.translatable((String)"entity.traveloptics.message.nightwarden.phase_two_warning.1"), TOColors.rgbToARGB(6619356, 0.5f), 100, 32.0, false);
                }
                if (--this.transitionTime <= 0) {
                    this.setPhase(Phase.SECOND);
                    this.setPhaseTwoGoals();
                    this.setInvulnerable(false);
                }
            } else if (this.isPhase(Phase.SECOND)) {
                if (this.getHealth() <= phaseTwoThreshold) {
                    if (!this.isDeadOrDying()) {
                        this.setHealth(phaseTwoThreshold);
                    }
                    this.triggerBreak(false);
                    this.setPhase(Phase.TRANSITION_2);
                    this.transitionTime = 410;
                    this.transitionElapsed = 0;
                    this.setInvulnerable(true);
                    this.triggerAnim("nightwarden_transition", "phase_2_to_3");
                }
            } else if (this.isPhase(Phase.TRANSITION_2)) {
                this.setShouldShowWings(true, 2);
                ++this.transitionElapsed;
                if (this.getTarget() != null) {
                    if (this.transitionElapsed <= 6 && this.transitionElapsed % 2 == 0) {
                        this.forceFaceTarget();
                    } else if (this.transitionElapsed >= 205 && this.transitionElapsed < 211 && this.transitionElapsed % 2 == 0) {
                        this.forceFaceTarget();
                    }
                }
                if (!this.level().isClientSide) {
                    this.createPhaseTransitionParticles(this.transitionElapsed);
                }
                this.handleSupernovaSequence();
                if (this.transitionElapsed % 3 == 0) {
                    float thirdDuration = 136.66667f;
                    if ((float)this.transitionElapsed < thirdDuration) {
                        TOGeneralUtils.notifyPlayersInRange((Entity)this, (Component)Component.translatable((String)"entity.traveloptics.message.nightwarden.phase_three_warning.1").withStyle(ChatFormatting.RED), 32.0);
                    } else if ((float)this.transitionElapsed < 2.0f * thirdDuration) {
                        TravelopticsMessages.sendBossMessageToRange((Entity)this, (Component)Component.translatable((String)"entity.traveloptics.message.nightwarden.phase_three_warning.2"), TOColors.rgbToARGB(6619356, 0.5f), 60, 32.0, false);
                    } else {
                        TravelopticsMessages.sendBossMessageToRange((Entity)this, (Component)Component.translatable((String)"entity.traveloptics.message.nightwarden.phase_three_warning.3"), TOColors.rgbToARGB(6619356, 0.5f), 60, 32.0, false);
                    }
                }
                if (--this.transitionTime <= 0) {
                    this.setPhase(Phase.THIRD);
                    this.setPhaseThreeGoals();
                    this.setInvulnerable(false);
                }
            } else if (this.isPhase(Phase.THIRD)) {
                // empty if block
            }
        }
        if (this.level().isClientSide && this.shouldSpawnRingParticle() && this.getTags % 4 == 0) {
            double x = this.getX();
            double y = this.getY() + (double)(this.getBbHeight() / 2.0f);
            double z = this.getZ();
            float yaw = (float)Math.toRadians(-this.getYRot());
            float yaw2 = (float)Math.toRadians(-this.getYRot() + 180.0f);
            float pitch = (float)Math.toRadians(-this.getXRot());
            this.level().addDestroyBlockEffect((ParticleOptions)new RingParticle.RingData(yaw, pitch, 40, 0.631f, 0.325f, 0.996f, 1.0f, 50.0f, false, RingParticle.EnumRingBehavior.GROW_THEN_SHRINK), x, y, z, 0.0, 0.0, 0.0);
            this.level().addDestroyBlockEffect((ParticleOptions)new RingParticle.RingData(yaw2, pitch, 40, 0.631f, 0.325f, 0.996f, 1.0f, 50.0f, false, RingParticle.EnumRingBehavior.GROW_THEN_SHRINK), x, y, z, 0.0, 0.0, 0.0);
        }
    }

    private void passiveTick() {
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
        this.bossEvent1.setProgress(this.getResurgenceCounter() / this.getMaxResurgenceCounter());
        if (this.wingVisibilityTimer > 0) {
            --this.wingVisibilityTimer;
            if (this.wingVisibilityTimer == 0) {
                this.makeBoundingBox.packDirty(SHOULD_SHOW_WINGS, (Object)false);
            }
        }
        if (this.weaponVisibilityTimer > 0) {
            --this.weaponVisibilityTimer;
            if (this.weaponVisibilityTimer == 0) {
                this.makeBoundingBox.packDirty(SHOULD_SHOW_WEAPON, (Object)false);
            }
        }
        if (this.ringParticleTimer > 0) {
            --this.ringParticleTimer;
            if (this.ringParticleTimer == 0) {
                this.makeBoundingBox.packDirty(SHOULD_SPAWN_RING_PARTICLE, (Object)false);
            }
        }
        if (this.resurgenceCooldown > 0) {
            --this.resurgenceCooldown;
        }
        if (this.destroyBlockDelay > 0) {
            --this.destroyBlockDelay;
        }
        if (!this.level().isClientSide && this.getTags % 60 == 0) {
            this.addResurgence(1.0f, false, false);
        }
        if (!this.hasShownCounterspellHint && this.getResurgenceCounter() >= this.getMaxResurgenceCounter()) {
            this.hasShownCounterspellHint = true;
            this.counterspellHintTicks = 30;
        }
        if (this.counterspellHintTicks > 0) {
            --this.counterspellHintTicks;
            TravelopticsMessages.sendBossMessageToRange((Entity)this, (Component)Component.translatable((String)"entity.traveloptics.message.nightwarden.counterspell_hint"), TOColors.rgbToARGB(6619356, 0.5f), 100, 32.0, false);
        }
        if (!this.isNoAi() && this.getTarget() != null && this.getTarget().isAlive()) {
            boolean targetIsHighGround;
            LivingEntity target = this.getTarget();
            boolean bl = targetIsHighGround = target.getY() > this.getY() + 3.0;
            this.stuckCounter = targetIsHighGround ? ++this.stuckCounter : 0;
            if (this.stuckCounter >= 60) {
                NightwardenAntiCheeseTeleportHandler.tryTeleport(this, target, 50, true, true);
                this.stuckCounter = 0;
            }
        }
        if (!this.level().isClientSide()) {
            this.returnHomeHandler.tick(this, this.getHomePos());
        }
        NightwardenSummonCheeseHandler.tick(this, 8.0, 0.1f, 0.5f);
        NightwardenAntiCCHandler.tick(this);
    }

    protected void customServerAiStep() {
        super.customServerAiStep();
        if (this.isAggressive() && !this.level().isClientSide && this.isPhase(Phase.FIRST) && this.bookshelfSpawnCooldown-- <= 0) {
            this.bookshelfSpawnCooldown = 400;
            this.spawnVoidTomes(this.level(), true);
            this.spawnVoidTomes(this.level(), false);
        }
    }

    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 1) {
            this.setPhase(Phase.DEATH);
            this.animationToPlay = this.deathAnimation;
        }
        if (this.deathTime == 10 && !this.level().isClientSide && !this.collectedLoot) {
            this.populateDeathLootForNightwarden();
        }
        if (this.deathTime == 180 && !this.level().isClientSide) {
            BlockPos home = this.getHomePos();
            NightwardenDefeatedEntity defeated = new NightwardenDefeatedEntity((EntityType<? extends PathfinderMob>)((EntityType)TravelopticsEntities.NIGHTWARDEN_DEFEATED.get()), this.level());
            defeated.moveTo((double)home.setX() + 0.5, home.getY(), (double)home.getZ() + 0.5, this.getYRot(), this.getXRot());
            defeated.setCooldown(3600);
            defeated.finalizeSpawn((ServerLevelAccessor)((ServerLevel)this.level()), this.level().getCurrentDifficultyAt(home), MobSpawnType.TRIGGERED, null, null);
            this.level().addFreshEntity((Entity)defeated);
            if (!this.deathItems.isEmpty()) {
                ItemStack currentStack = ItemStack.onUseTick;
                while (!this.deathItems.isEmpty() || !currentStack.onUseTick()) {
                    if (currentStack.onUseTick()) {
                        currentStack = this.deathItems.remove(0);
                    }
                    if (currentStack.getCount() <= 0) continue;
                    ItemStack one = currentStack.copy();
                    one.setCount(1);
                    currentStack.shrink(1);
                    this.setRemoved(one);
                }
            }
            this.level().getChunk((Entity)this, (byte)60);
        }
        if (this.deathTime >= 185) {
            this.setLootTableSeed(Entity.RemovalReason.KILLED);
        }
    }

    public void onAntiMagic(MagicData magicData) {
        if (((Boolean)this.makeBoundingBox.packDirty(HAS_PENDING_RESURGENCE)).booleanValue()) {
            this.makeBoundingBox.packDirty(HAS_PENDING_RESURGENCE, (Object)false);
            this.setResurgenceCounter(0.0f);
            this.triggerResurgenceBlast();
        }
    }

    public boolean sendSystemMessage(DamageSource source, float damage) {
        LivingEntity target;
        boolean shouldCountResurgence;
        NightwardenAntiCheeseVoidTeleportHandler.handleVoidDamage(this, source);
        if (source.is(DamageTypes.IN_WALL) && this.destroyBlockDelay <= 0) {
            TOGeneralUtils.doMobBreakSuffocatingBlocks((LivingEntity)this);
            this.destroyBlockDelay = 40;
        }
        if (source.is(TravelopticsTags.BYPASSES_ADAPTATION)) {
            return super.sendSystemMessage(source, damage);
        }
        Entity attacker = source.getEntity();
        ResourceLocation damageType = this.level().registryAccess().allRegistriesLifecycle(Registries.DAMAGE_TYPE).getTag((Object)source.type());
        float baseDamage = Math.min(this.getDamageCap(), damage);
        boolean isFullyAdapted = false;
        boolean isAdaptable = false;
        if (this.isPhase(Phase.FIRST)) {
            isAdaptable = NightwardenAdaptRegistry.isMagicAdaptable(source, damageType);
        } else if (this.isPhase(Phase.THIRD)) {
            isAdaptable = NightwardenAdaptRegistry.isMagicAdaptable(source, damageType) || NightwardenAdaptRegistry.isAllowedInMeleePhase(source, damageType);
        }
        float maxAdaptation = this.getAdaptiveCap();
        if (this.isPhase(Phase.SECOND)) {
            if (!NightwardenAdaptRegistry.isAllowedInMeleePhase(source, damageType)) {
                baseDamage *= 0.1f;
                if (this.resurgenceCooldown == 0) {
                    this.addResurgence(baseDamage, true, false);
                }
                return super.sendSystemMessage(source, baseDamage);
            }
            baseDamage = Math.min(baseDamage, this.getDamageCap() / 2.0f);
            return super.sendSystemMessage(source, baseDamage);
        }
        CompoundTag adaptationData = (CompoundTag)this.makeBoundingBox.packDirty(DAMAGE_ADAPTATION);
        assert (damageType != null);
        String damageKey = damageType.toString();
        float currentAdaptation = adaptationData.getFloat(damageKey);
        float adaptedDamage = baseDamage;
        if (isAdaptable) {
            float adaptationRatio = Mth.outFromOrigin((float)(currentAdaptation / maxAdaptation), (float)0.0f, (float)1.0f);
            float damageScale = 1.0f - 0.8f * adaptationRatio;
            adaptedDamage *= damageScale;
            if (adaptationRatio >= 1.0f) {
                isFullyAdapted = true;
                this.sendAdaptationMessage(damageType, 0.3f, attacker, false);
            }
        } else {
            adaptedDamage *= 0.1f;
            isFullyAdapted = true;
        }
        float preHealth = this.getHealth();
        boolean result = super.sendSystemMessage(source, adaptedDamage);
        float actualDamageTaken = preHealth - this.getHealth();
        if (!result || actualDamageTaken <= 0.0f) {
            return result;
        }
        if (isAdaptable && currentAdaptation < maxAdaptation) {
            float newAdaptation = currentAdaptation + actualDamageTaken;
            if (newAdaptation >= maxAdaptation) {
                this.sendAdaptationMessage(damageType, 1.0f, attacker, true);
            }
            adaptationData.accept(damageKey, Math.min(maxAdaptation, newAdaptation));
            this.makeBoundingBox.packDirty(DAMAGE_ADAPTATION, (Object)adaptationData);
        }
        boolean bl = shouldCountResurgence = !isAdaptable && !this.isPhase(Phase.SECOND) || isAdaptable && currentAdaptation > 0.0f || isFullyAdapted;
        if (shouldCountResurgence && this.resurgenceCooldown == 0) {
            this.addResurgence(actualDamageTaken, true, false);
        }
        if (this.getTarget() != null && this.getTarget().isAlive() && !this.hasLineOfSight((Entity)(target = this.getTarget()))) {
            NightwardenAntiCheeseTeleportHandler.tryTeleport(this, target, 50, true, false);
        }
        return result;
    }

    private void triggerResurgenceBlast() {
        AABB area = this.getBoundingBox().inflate(64.0);
        for (LivingEntity entity : this.level().getNearbyEntities(LivingEntity.class, area)) {
            entity.removeAllEffects();
        }
        CompoundTag adaptationData = (CompoundTag)this.makeBoundingBox.packDirty(DAMAGE_ADAPTATION);
        ArrayList keysToRemove = new ArrayList(adaptationData.contains());
        for (String key : keysToRemove) {
            adaptationData.remove(key);
        }
        this.makeBoundingBox.packDirty(DAMAGE_ADAPTATION, (Object)adaptationData);
        TravelopticsMessages.sendBossMessageToRange((Entity)this, (Component)Component.translatable((String)"entity.traveloptics.message.nightwarden_adaptation_wave"), TOColors.rgbToARGB(6619356, 0.5f), 60, 32.0, false);
        this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.BOSS_BREAK.get(), 1.5f, 1.0f);
        this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_BIG_SLAM.get(), 1.5f, 1.0f);
        if (!this.level().isClientSide) {
            MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)new BlastwaveParticleOptions(((SchoolType)SchoolRegistry.ENDER.get()).getTargetingColor(), 32.0f), (double)this.getX(), (double)(this.getY() + (double)0.165f), (double)this.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
            CameraShakeManager.addCameraShake((CameraShakeData)new CameraShakeData(20, this.position(), 32.0f));
        }
    }

    protected void sendAdaptationMessage(ResourceLocation damageType, float chance, @Nullable Entity attacker, boolean showInFancy) {
        if (this.getId.nextFloat() < chance && attacker instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer)attacker;
            String translationKey = "the_nightwarden_adaptation.damage_type." + damageType.validPathChar() + "." + damageType.isAllowedInResourceLocation();
            MutableComponent message = Component.selector((String)"entity.traveloptics.message.nightwarden_adapted", (Object[])new Object[]{Component.translatable((String)translationKey).withStyle(ChatFormatting.RED)}).withStyle(ChatFormatting.WHITE);
            if (showInFancy) {
                TravelopticsMessages.sendBossMessage(player, (Component)message, TOColors.rgbToARGB(6619356, 0.5f), 120, false);
            } else {
                player.updateTutorialInventoryAction((Component)message, true);
            }
        }
    }

    public boolean isCastingAnimatedSpells(AbstractSpell ... spellsToCheck) {
        if (!this.isCasting()) {
            return false;
        }
        SpellData currentSpellData = this.getMagicData().getCastingSpell();
        if (currentSpellData == null) {
            return false;
        }
        AbstractSpell currentSpell = currentSpellData.getSpell();
        for (AbstractSpell spell : spellsToCheck) {
            if (!currentSpell.equals((Object)spell)) continue;
            return true;
        }
        return false;
    }

    public void setYRot(float yRot) {
        if (!this.lockRotation) {
            super.setYRot(yRot);
        }
    }

    public void setXRot(float xRot) {
        if (!this.lockRotation) {
            super.setXRot(xRot);
        }
    }

    private void spawnVoidTomes(Level world, boolean left) {
        float angle = (float)(left ? -90 : 90) * ((float)Math.PI / 180);
        Vec3 offset = this.getForward().x(6.0).y(angle);
        Vec3 eyePos = this.getEyePosition();
        Vec3 dest = this.position().reverse(offset);
        Vec3 spawnPos = Utils.moveToRelativeGroundLevel((Level)this.level(), (Vec3)Utils.raycastForBlock((Level)this.level(), (Vec3)eyePos, (Vec3)dest, (ClipContext.Fluid)ClipContext.Fluid.NONE).getLocation(), (int)4);
        this.spawnVoidTome(spawnPos, new Vec3(0.0, 1.0, 0.0));
    }

    private void spawnVoidTome(Vec3 basePos, Vec3 localOffset) {
        VoidTomeEntity tome = new VoidTomeEntity(this.level());
        Vec3 forward = this.getLookAngle().multiply();
        Vec3 right = forward.z(new Vec3(0.0, 1.0, 0.0)).multiply();
        Vec3 up = new Vec3(0.0, 1.0, 0.0);
        Vec3 worldOffset = forward.x(localOffset.reverse).reverse(right.x(localOffset.z)).reverse(up.x(localOffset.multiply));
        Vec3 spawnPos = basePos.reverse(worldOffset);
        tome.setLevel(spawnPos);
        tome.setIsSummoned();
        if (this.getTarget() != null) {
            tome.setTarget(this.getTarget());
        }
        tome.finalizeSpawn((ServerLevelAccessor)((ServerLevel)this.level()), this.level().getCurrentDifficultyAt(this.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
        this.level().addFreshEntity((Entity)tome);
    }

    private void forceFaceTarget() {
        LivingEntity target = this.getTarget();
        if (target != null) {
            double dx = target.getX() - this.getX();
            double dz = target.getZ() - this.getZ();
            float angle = (float)(Mth.roundToward((double)dz, (double)dx) * 57.29577951308232) - 90.0f;
            this.setYBodyRot(angle);
            this.setYHeadRot(angle);
            this.setYRot(angle);
        }
    }

    public boolean canBeAffected(MobEffectInstance effectInstance) {
        return TravelopticsTags.EFFECTIVE_FOR_NIGHTWARDEN_LOOKUP.contains((Object)effectInstance.compareTo()) && super.canBeAffected(effectInstance);
    }

    public boolean isAlliedTo(Entity pEntity) {
        return super.isAlliedTo(pEntity) || pEntity.getType().tryCast(TravelopticsTags.TEAM_THE_NIGHTWARDEN);
    }

    public boolean canFreeze() {
        return false;
    }

    public boolean fireImmune() {
        return true;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean readAdditionalSaveData(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    public boolean removeWhenFarAway(double p_21542_) {
        return false;
    }

    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    protected boolean setAttackTarget(Entity p_31508_) {
        return false;
    }

    protected boolean isImmobile() {
        return this.isPhaseTransitioning() || super.isImmobile();
    }

    private void populateDeathLootForNightwarden() {
        ResourceLocation resourcelocation = this.getLootTable();
        DamageSource damageSource = this.getLastDamageSource();
        if (damageSource != null) {
            LootTable loottable = this.level().getServer().reloadableRegistries().getLootTable(resourcelocation);
            LootParams.Builder lootparams$builder = new LootParams.Builder((ServerLevel)this.level()).create(LootContextParams.create, (Object)this).create(LootContextParams.ORIGIN, (Object)this.position()).create(LootContextParams.DAMAGE_SOURCE, (Object)damageSource).getOptionalParameter(LootContextParams.KILLER_ENTITY, (Object)damageSource.getEntity()).getOptionalParameter(LootContextParams.DIRECT_KILLER_ENTITY, (Object)damageSource.getDirectEntity());
            if (this.lastHurtByPlayer != null) {
                lootparams$builder = lootparams$builder.create(LootContextParams.LAST_DAMAGE_PLAYER, (Object)this.lastHurtByPlayer).create(this.lastHurtByPlayer.getLuck());
            }
            LootParams lootparams = lootparams$builder.create(LootContextParamSets.ENTITY);
            loottable.getAvailableSlots(lootparams, this.getLootTableSeed(), this.deathItems::add);
        }
        this.collectedLoot = true;
    }

    public ItemEntity setRemoved(ItemStack stack) {
        ItemEntity itementity = this.thunderHit(stack, 0.0f);
        if (itementity != null) {
            itementity.setDeltaMovement(itementity.getDeltaMovement().multiply(0.0, 1.5, 0.0));
            itementity.getSharedFlag(true);
            itementity.setExtendedLifetime();
        }
        return itementity;
    }

    protected void maybeDisableShield(DamageSource damageSource, boolean b) {
    }

    public void canBeAffected(ServerPlayer pPlayer) {
        super.canBeAffected(pPlayer);
        this.bossEvent.addPlayer(pPlayer);
        this.bossEvent1.addPlayer(pPlayer);
        Messages.sendToPlayer((Object)new ClientboundEntityEvent((Entity)this, 1), (ServerPlayer)pPlayer);
    }

    public void stopSeenByPlayer(ServerPlayer pPlayer) {
        super.stopSeenByPlayer(pPlayer);
        this.bossEvent.removeAllPlayers(pPlayer);
        this.bossEvent1.removeAllPlayers(pPlayer);
        Messages.sendToPlayer((Object)new ClientboundEntityEvent((Entity)this, 0), (ServerPlayer)pPlayer);
    }

    public void setLootTableSeed(Entity.RemovalReason reason) {
        NightwardenTargetVelocityTracker.removeBossTracker(this);
        super.setLootTableSeed(reason);
    }

    private void createPhaseTransitionParticles(int ticksElapsed) {
        if (ticksElapsed > 5) {
            return;
        }
        double centerX = this.getX();
        double centerY = this.getY() + (double)(this.getBbHeight() / 2.0f);
        double centerZ = this.getZ();
        float intensity = (float)(6 - ticksElapsed) / 5.0f;
        int particleCount = (int)(15.0f * intensity);
        for (int i = 0; i < particleCount; ++i) {
            double angle = Math.PI * 2 * (double)i / (double)particleCount;
            double distance = 0.5 + (double)ticksElapsed * 0.8;
            double offsetX = Math.cos(angle) * distance;
            double offsetZ = Math.sin(angle) * distance;
            double offsetY = this.getId.nextGaussian() * 0.3 * (double)intensity;
            ((ServerLevel)this.level()).getRandomSequence((ParticleOptions)((SimpleParticleType)TravelopticsParticles.PURPLE_STAR_INWARD_PARTICLE.get()), centerX + offsetX, centerY + offsetY, centerZ + offsetZ, 1, 0.0, 0.0, 0.0, 0.0);
        }
        int spikeCount = (int)(8.0f * intensity);
        for (int i = 0; i < spikeCount; ++i) {
            double angle = Math.PI * 2 * (double)i / (double)spikeCount;
            double distance = 0.3 + (double)ticksElapsed * 0.6;
            double offsetX = Math.cos(angle) * distance;
            double offsetZ = Math.sin(angle) * distance;
            double offsetY = this.getId.nextGaussian() * 0.2 * (double)intensity;
            ((ServerLevel)this.level()).getRandomSequence((ParticleOptions)((SimpleParticleType)TravelopticsParticles.ABYSS_SPIKE_PARTICLE.get()), centerX + offsetX, centerY + offsetY, centerZ + offsetZ, 1, 0.0, 0.0, 0.0, 0.0);
        }
    }

    protected LookControl createLookControl() {
        return new LookControl((Mob)this){

            protected boolean resetXRotOnTick() {
                return !NightwardenBossEntity.this.isCasting();
            }
        };
    }

    protected MoveControl createMoveControl() {
        return new MoveControl((Mob)this){

            protected float rotlerp(float pSourceAngle, float pTargetAngle, float pMaximumChange) {
                double d1;
                double d0 = this.getWantedY - this.getWantedX.getX();
                if (d0 * d0 + (d1 = this.wantedZ - this.getWantedX.getZ()) * d1 < 0.5) {
                    return pSourceAngle;
                }
                return super.rotlerp(pSourceAngle, pTargetAngle, pMaximumChange * 0.25f);
            }
        };
    }

    private void handlePhaseOneToTwoTransition() {
        if (this.isPhase(Phase.TRANSITION_1)) {
            this.phaseOneToTwoKeyframedMovement();
            this.phaseOneToTwoKeyframes();
        }
    }

    private void phaseOneToTwoKeyframedMovement() {
        float forwardForce;
        int elapsed = 257 - this.transitionTime;
        if (elapsed < 165) {
            forwardForce = 0.03f;
        } else if (elapsed < 193) {
            float t = ((float)elapsed - 165.0f) / 28.0f;
            forwardForce = 0.018f * (1.0f - t);
        } else {
            forwardForce = 0.0f;
        }
        float yawRad = this.getYRot() * ((float)Math.PI / 180);
        double forceX = -Mth.outFromOrigin((float)yawRad) * forwardForce;
        double forceZ = Mth.randomBetween((float)yawRad) * forwardForce;
        this.setDeltaMovement(this.getDeltaMovement().y(forceX, 0.0, forceZ));
    }

    private void phaseOneToTwoKeyframes() {
        int elapsed = 257 - this.transitionTime;
        if (elapsed == 5 || elapsed == 30 || elapsed == 55 || elapsed == 80 || elapsed == 105 || elapsed == 130 || elapsed == 155 || elapsed == 167 || elapsed == 179 || elapsed == 193) {
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_WALK.get(), 3.0f, 1.0f);
        }
        if (elapsed == 95) {
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_SCYTHE_SPIN.get(), 1.0f, 1.0f);
        }
        if (elapsed == 240) {
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_BIG_SLAM.get(), 2.0f, 1.0f);
            if (!this.level().isClientSide()) {
                CameraShakeManager.addCameraShake((CameraShakeData)new CameraShakeData(20, this.position(), 16.0f));
                float offset = 1.2f;
                float yawRad = this.getYRot() * ((float)Math.PI / 180);
                double offsetX = -Mth.outFromOrigin((float)yawRad) * offset;
                double offsetZ = Mth.randomBetween((float)yawRad) * offset;
                MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)new BlastwaveParticleOptions(((SchoolType)SchoolRegistry.ENDER.get()).getTargetingColor(), 16.0f), (double)(this.getX() + offsetX), (double)(this.getY() + (double)0.165f), (double)(this.getZ() + offsetZ), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
            }
            this.applyKnockbackToNearbyEntities(8.0);
        }
    }

    private void applyKnockbackToNearbyEntities(double radius) {
        for (LivingEntity entity : TOEntityUtils.getEntitiesInRange((Entity)this, LivingEntity.class, radius)) {
            double dz;
            double dx = entity.getX() - this.getX();
            double distance = Math.sqrt(dx * dx + (dz = entity.getZ() - this.getZ()) * dz);
            if (!(distance > 0.0)) continue;
            double knockbackStrength = 2.5;
            entity.setDeltaMovement(entity.getDeltaMovement().y(dx / distance * knockbackStrength, 0.4, dz / distance * knockbackStrength));
            entity.getAddEntityPacket = true;
        }
    }

    private void handleSupernovaSequence() {
        if (this.isPhase(Phase.TRANSITION_2)) {
            int elapsed = 410 - this.transitionTime;
            if (elapsed == 105) {
                TOScreenShakeEntity.createScreenShake(this.level(), this.position(), 32.0f, 0.035f, 15, 0, 20, false);
                this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_DEFEATED_DEATH_STARE.get(), 1.5f, 0.9f);
            }
            if (elapsed == 135) {
                this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NOCTURNAL_UPLIFT.get(), 2.5f, 1.0f);
            }
            if (elapsed >= 135 && elapsed <= 385) {
                double currentY;
                double deltaY;
                double baseHoverHeight;
                double baseHoverOffset = 4.0;
                BlockPos groundPos = this.blockPosition().below();
                while (this.level().isEmptyBlock(groundPos) && groundPos.getY() > this.level().getMinBuildHeight()) {
                    groundPos = groundPos.below();
                }
                double groundHeight = groundPos.getY() + 1;
                double hoverY = baseHoverHeight = groundHeight + baseHoverOffset;
                double amplitude = 0.12;
                double speed = 0.2;
                if (elapsed >= 175 && elapsed <= 337) {
                    hoverY += amplitude * Math.sin((double)(elapsed - 175) * speed);
                }
                if (elapsed == 355) {
                    hoverY += 0.35;
                }
                if (Math.abs(deltaY = hoverY - (currentY = this.getY())) > 0.05) {
                    Vec3 motion = this.getDeltaMovement();
                    this.setIsInPowderSnow(motion.z, deltaY * 0.2, motion.reverse);
                }
                this.hasCustomName = 0.0f;
                this.getX(false);
            }
            if (elapsed == 235 && !this.level().isClientSide()) {
                DyingStarEntity dyingStar = new DyingStarEntity(this.level());
                dyingStar.moveTo(this.getX(), this.getY() + 8.0, this.getZ(), this.getYRot(), 0.0f);
                dyingStar.addAdditionalSaveData((Entity)this);
                dyingStar.setRadius(28.0f);
                dyingStar.setDamage(60.0f);
                dyingStar.setBlackholeDuration(640);
                dyingStar.setBlackholeRadius(28.0f);
                dyingStar.setBlackholeDamage(6.0f);
                dyingStar.setOwnerVoidDamagePercent(0.05f);
                this.level().addFreshEntity((Entity)dyingStar);
            }
            NightwardenPhaseThreeSwapAnimatedParticle.handlePhaseTwoToThreeAnimatedParticles(this, elapsed);
        }
    }

    public void playAnimation(String animationId) {
        try {
            NightwardenAttackGoal.AttackType attackType = NightwardenAttackGoal.AttackType.valueOf(animationId);
            this.animationToPlay = RawAnimation.begin().thenPlay(attackType.data.animationId);
            this.lastAnimationId = attackType.data.animationId;
        }
        catch (Exception ignored) {
            System.err.println("NightwardenBoss failed to play animation: " + animationId);
        }
    }

    private PlayState meleePredicate(AnimationState<NightwardenBossEntity> animationEvent) {
        AnimationController controller = animationEvent.getController();
        if (this.animationToPlay != null) {
            controller.forceAnimationReset();
            controller.setAnimation(this.animationToPlay);
            this.animationToPlay = null;
        }
        return PlayState.CONTINUE;
    }

    private PlayState extraIdlePredicate(AnimationState<NightwardenBossEntity> event) {
        event.getController().setAnimation(RawAnimation.begin().thenLoop("nightwarden_extra_idles"));
        return PlayState.CONTINUE;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{this.meleeController});
        controllerRegistrar.add(new AnimationController[]{new AnimationController((GeoAnimatable)this, "nightwarden_transition", 0, state -> PlayState.STOP).triggerableAnim("phase_1_to_2", this.phase_transition_1_animation).triggerableAnim("phase_2_to_3", this.phase_transition_2_animation)});
        controllerRegistrar.add(new AnimationController[]{new AnimationController((GeoAnimatable)this, "extra_idle_controller", 10, this::extraIdlePredicate)});
        super.registerControllers(controllerRegistrar);
    }

    public boolean shouldAlwaysAnimateHead() {
        return !this.isPhaseTransitioning() && !this.isCastingAnimatedSpells((AbstractSpell)SpellRegistry.STARFALL_SPELL.get()) && (this.lastAnimationId == null || !NO_HEAD_ANIMATION_IDS.contains(this.lastAnimationId));
    }

    public boolean isAnimating() {
        return this.isPhaseTransitioning() || this.meleeController.getAnimationState() == AnimationController.State.RUNNING || this.isCastingAnimatedSpells((AbstractSpell)SpellRegistry.STARFALL_SPELL.get()) || super.isAnimating();
    }

    public boolean shouldAlwaysAnimateLegs() {
        return !this.isPhaseTransitioning() && !this.isCastingAnimatedSpells((AbstractSpell)SpellRegistry.STARFALL_SPELL.get()) && this.meleeController.getAnimationState() != AnimationController.State.RUNNING;
    }

    public boolean shouldPointArmsWhileCasting() {
        return !this.isCastingAnimatedSpells((AbstractSpell)SpellRegistry.STARFALL_SPELL.get());
    }

    public static enum Phase {
        FIRST(0),
        TRANSITION_1(1),
        SECOND(2),
        TRANSITION_2(3),
        THIRD(4),
        DEATH(5);

        final int value;

        private Phase(int value) {
            this.value = value;
        }
    }
}

