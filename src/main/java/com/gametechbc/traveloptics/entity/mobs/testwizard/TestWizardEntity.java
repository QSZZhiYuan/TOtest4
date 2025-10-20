/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModParticle
 *  io.redspace.ironsspellbooks.api.entity.IMagicEntity
 *  io.redspace.ironsspellbooks.api.registry.SpellRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob
 *  io.redspace.ironsspellbooks.entity.mobs.goals.PatrolNearLocationGoal
 *  io.redspace.ironsspellbooks.entity.mobs.goals.SpellBarrageGoal
 *  io.redspace.ironsspellbooks.entity.mobs.goals.WizardAttackGoal
 *  io.redspace.ironsspellbooks.registries.ItemRegistry
 *  io.redspace.ironsspellbooks.util.ParticleHelper
 *  javax.annotation.Nullable
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.WrappedGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.monster.Enemy
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.ServerLevelAccessor
 *  software.bernie.geckolib.core.animatable.GeoAnimatable
 *  software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
 *  software.bernie.geckolib.util.GeckoLibUtil
 */
package com.gametechbc.traveloptics.entity.mobs.testwizard;

import com.gametechbc.traveloptics.api.entity.ai.CastSpellOnLostSightGoal;
import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.api.particle.SphereParticleManager;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.github.L_Ender.cataclysm.init.ModParticle;
import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.goals.PatrolNearLocationGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.SpellBarrageGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.WizardAttackGoal;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;

public class TestWizardEntity
extends AbstractSpellCastingMob
implements Enemy {
    private static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.assignValue(TestWizardEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
    private int transitionTime = 10;
    private Phase currentPhase = Phase.PHASE_1;

    public TestWizardEntity(Level pLevel) {
        this((EntityType<? extends AbstractSpellCastingMob>)((EntityType)TravelopticsEntities.TEST_WIZARD.get()), pLevel);
        this.setPersistenceRequired();
    }

    public TestWizardEntity(EntityType<? extends AbstractSpellCastingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPersistenceRequired();
        this.moveControl = this.createMoveControl();
        this.getArmorSlots = 25;
    }

    protected void registerGoals() {
        this.setPhaseOneGoals();
        this.targetSelector.setControlFlag(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
        this.targetSelector.setControlFlag(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
    }

    protected void setPhaseOneGoals() {
        this.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
        this.goalSelector.setControlFlag(x -> true);
        this.goalSelector.setControlFlag(1, (Goal)new FloatGoal((Mob)this));
        this.goalSelector.setControlFlag(2, (Goal)new SpellBarrageGoal((IMagicEntity)this, (AbstractSpell)TravelopticsSpells.VORTEX_PUNCH_SPELL.get(), 3, 6, 100, 250, 1));
        this.goalSelector.setControlFlag(4, (Goal)new WizardAttackGoal((IMagicEntity)this, 1.25, 50, 75).setSpells(List.of((AbstractSpell)SpellRegistry.ICICLE_SPELL.get(), (AbstractSpell)SpellRegistry.ICICLE_SPELL.get(), (AbstractSpell)TravelopticsSpells.BLOOD_HOWL_SPELL.get(), (AbstractSpell)TravelopticsSpells.STELE_CASCADE_SPELL.get()), List.of((AbstractSpell)TravelopticsSpells.ORBITAL_VOID_SPELL.get(), (AbstractSpell)TravelopticsSpells.AERIAL_COLLAPSE.get()), List.of((AbstractSpell)SpellRegistry.BURNING_DASH_SPELL.get()), List.of()).setSingleUseSpell((AbstractSpell)TravelopticsSpells.ABYSSAL_BLAST_SPELL.get(), 80, 400, 3, 6).setDrinksPotions().setSpellQuality(0.8f, 1.0f));
        this.goalSelector.setControlFlag(6, (Goal)new PatrolNearLocationGoal((PathfinderMob)this, 30.0f, 0.75));
        this.goalSelector.setControlFlag(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0f));
    }

    protected void setPhaseTwoGoals() {
        this.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
        this.goalSelector.setControlFlag(x -> true);
        this.goalSelector.setControlFlag(3, (Goal)new SpellBarrageGoal((IMagicEntity)this, (AbstractSpell)TravelopticsSpells.CURSED_MINEFIELD_SPELL.get(), 5, 7, 200, 400, 1));
        this.goalSelector.setControlFlag(3, (Goal)new CastSpellOnLostSightGoal((IMagicEntity)this, 1.25, 100, 120).setSpells(List.of(), List.of(), List.of((AbstractSpell)TravelopticsSpells.VOID_ERUPTION_SPELL.get(), (AbstractSpell)TravelopticsSpells.SPECTRAL_BLINK_SPELL.get()), List.of()).setSpellQuality(0.8f, 1.0f).setIsFlying());
        this.goalSelector.setControlFlag(4, (Goal)new WizardAttackGoal((IMagicEntity)this, 1.5, 40, 60).setSpells(List.of((AbstractSpell)SpellRegistry.FIREBALL_SPELL.get(), (AbstractSpell)TravelopticsSpells.AERIAL_COLLAPSE.get(), (AbstractSpell)TravelopticsSpells.AQUA_MISSILES_SPELL.get(), (AbstractSpell)TravelopticsSpells.HALBERD_HORIZON.get(), (AbstractSpell)TravelopticsSpells.TIDAL_GRASP_SPELL.get()), List.of((AbstractSpell)TravelopticsSpells.ORBITAL_VOID_SPELL.get()), List.of((AbstractSpell)SpellRegistry.BLOOD_STEP_SPELL.get(), (AbstractSpell)TravelopticsSpells.SPECTRAL_BLINK_SPELL.get()), List.of()).setSingleUseSpell((AbstractSpell)TravelopticsSpells.TIDAL_GRASP_SPELL.get(), 80, 200, 3, 6).setDrinksPotions().setSpellQuality(1.2f, 1.3f).setIsFlying());
        this.goalSelector.setControlFlag(6, (Goal)new PatrolNearLocationGoal((PathfinderMob)this, 30.0f, (double)0.85f));
        this.goalSelector.setControlFlag(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0f));
    }

    public void lerpMotion() {
        this.currentPhase = Phase.values()[(Integer)this.makeBoundingBox.packDirty(PHASE)];
        if (this.currentPhase == Phase.TRANSITIONING) {
            --this.transitionTime;
            this.getNavigation().stop();
            this.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
            SphereParticleManager.spawnParticles(this.level(), (Entity)this, 15, ParticleHelper.FIRE, ParticleDirection.INWARD, 4.0);
            SphereParticleManager.spawnParticles(this.level(), (Entity)this, 20, ParticleHelper.UNSTABLE_ENDER, ParticleDirection.OUTWARD, 6.0);
            SphereParticleManager.spawnParticles(this.level(), (Entity)this, 10, (ParticleOptions)ModParticle.PHANTOM_WING_FLAME.get(), ParticleDirection.INWARD, 4.0);
            if (this.transitionTime <= 0) {
                this.currentPhase = Phase.PHASE_2;
                this.setHealth(this.getMaxHealth());
                this.setPhaseTwoGoals();
                this.makeBoundingBox.packDirty(PHASE, (Object)Phase.PHASE_2.value);
            }
            return;
        }
        if (this.currentPhase == Phase.PHASE_1 && (double)this.getHealth() < (double)this.getMaxHealth() * 0.5) {
            this.currentPhase = Phase.TRANSITIONING;
            this.transitionTime = 10;
            this.makeBoundingBox.packDirty(PHASE, (Object)Phase.TRANSITIONING.value);
            return;
        }
        super.lerpMotion();
    }

    protected boolean isImmobile() {
        return this.currentPhase == Phase.TRANSITIONING || super.isImmobile();
    }

    public boolean isPhaseTransitioning() {
        return this.currentPhase == Phase.TRANSITIONING;
    }

    private void setPhase(int phase) {
        this.makeBoundingBox.packDirty(PHASE, (Object)phase);
    }

    public int getPhase() {
        return (Integer)this.makeBoundingBox.packDirty(PHASE);
    }

    public boolean isPhase(Phase phase) {
        return phase.value == this.getPhase();
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.accept("phase", this.getPhase());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setPhase(pCompound.copy("phase"));
        if (this.isPhase(Phase.PHASE_2)) {
            this.setPhaseTwoGoals();
        }
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.makeBoundingBox.assignValue(PHASE, (Object)0);
    }

    public boolean isTargetingPlayer() {
        LivingEntity target = this.getTarget();
        return target instanceof Player;
    }

    public boolean readAdditionalSaveData(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean isPushable() {
        return !this.isPhaseTransitioning();
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        RandomSource randomsource = Utils.random;
        this.hurt(randomsource, pDifficulty);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    protected void hurt(RandomSource pRandom, DifficultyInstance pDifficulty) {
        this.setLastDeathLocation(EquipmentSlot.HEAD, new ItemStack((ItemLike)ItemRegistry.SHADOWWALKER_HELMET.get()));
        this.setLastDeathLocation(EquipmentSlot.CHEST, new ItemStack((ItemLike)ItemRegistry.SHADOWWALKER_CHESTPLATE.get()));
        this.setLastDeathLocation(EquipmentSlot.LEGS, new ItemStack((ItemLike)ItemRegistry.SHADOWWALKER_LEGGINGS.get()));
        this.setLastDeathLocation(EquipmentSlot.FEET, new ItemStack((ItemLike)ItemRegistry.SHADOWWALKER_BOOTS.get()));
        this.maybeDisableShield(EquipmentSlot.HEAD, 0.0f);
        this.maybeDisableShield(EquipmentSlot.CHEST, 0.0f);
        this.maybeDisableShield(EquipmentSlot.LEGS, 0.0f);
        this.maybeDisableShield(EquipmentSlot.FEET, 0.0f);
    }

    public boolean canFreeze() {
        return false;
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes().build(Attributes.ATTACK_DAMAGE, 3.0).build(Attributes.ATTACK_KNOCKBACK, 0.0).build(Attributes.register, 300.0).build(Attributes.FOLLOW_RANGE, 24.0).build(Attributes.MOVEMENT_SPEED, 0.25);
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
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

    public static enum Phase {
        PHASE_1(0),
        TRANSITIONING(1),
        PHASE_2(2);

        final int value;

        private Phase(int value) {
            this.value = value;
        }
    }
}

