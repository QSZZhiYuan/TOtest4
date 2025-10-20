/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.entity.IMagicEntity
 *  io.redspace.ironsspellbooks.api.registry.SpellRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.effect.SummonTimer
 *  io.redspace.ironsspellbooks.entity.mobs.MagicSummon
 *  io.redspace.ironsspellbooks.entity.mobs.goals.GenericCopyOwnerTargetGoal
 *  io.redspace.ironsspellbooks.entity.mobs.goals.GenericFollowOwnerGoal
 *  io.redspace.ironsspellbooks.entity.mobs.goals.GenericHurtByTargetGoal
 *  io.redspace.ironsspellbooks.entity.mobs.goals.GenericOwnerHurtByTargetGoal
 *  io.redspace.ironsspellbooks.entity.mobs.goals.GenericOwnerHurtTargetGoal
 *  io.redspace.ironsspellbooks.util.OwnerHelper
 *  javax.annotation.Nullable
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.entity.summons;

import com.gametechbc.traveloptics.entity.mobs.void_tome.VoidTomeComboGoal;
import com.gametechbc.traveloptics.entity.mobs.void_tome.VoidTomeEntity;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.effect.SummonTimer;
import io.redspace.ironsspellbooks.entity.mobs.MagicSummon;
import io.redspace.ironsspellbooks.entity.mobs.goals.GenericCopyOwnerTargetGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.GenericFollowOwnerGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.GenericHurtByTargetGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.GenericOwnerHurtByTargetGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.GenericOwnerHurtTargetGoal;
import io.redspace.ironsspellbooks.util.OwnerHelper;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SummonedVoidTome
extends VoidTomeEntity
implements MagicSummon {
    protected LivingEntity cachedSummoner;
    protected UUID summonerUUID;

    public SummonedVoidTome(EntityType<? extends VoidTomeEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.getArmorSlots = 0;
    }

    public SummonedVoidTome(Level level, LivingEntity owner) {
        this((EntityType<? extends VoidTomeEntity>)((EntityType)TravelopticsEntities.SUMMONED_VOID_TOME.get()), level);
        this.setSummoner(owner);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.setControlFlag(1, (Goal)new VoidTomeComboGoal((IMagicEntity)this, 1.25, 40, 40).addCombo(1, List.of((AbstractSpell)SpellRegistry.MAGIC_MISSILE_SPELL.get(), (AbstractSpell)SpellRegistry.MAGIC_MISSILE_SPELL.get(), (AbstractSpell)SpellRegistry.MAGIC_ARROW_SPELL.get())).setComboCooldown(80, 80).setSpellQuality(0.9f, 1.1f).setSingleUseSpell((AbstractSpell)TravelopticsSpells.ORBITAL_VOID_SPELL.get(), 200, 400, 1, 1));
        this.goalSelector.setControlFlag(3, (Goal)new GenericFollowOwnerGoal((PathfinderMob)this, this::getSummoner, 1.0, 9.0f, 4.0f, true, 20.0f));
        this.goalSelector.setControlFlag(5, (Goal)new WaterAvoidingRandomFlyingGoal((PathfinderMob)this, 0.75));
        this.goalSelector.setControlFlag(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 3.0f, 1.0f));
        this.goalSelector.setControlFlag(10, (Goal)new LookAtPlayerGoal((Mob)this, Mob.class, 8.0f));
        this.targetSelector.setControlFlag(1, (Goal)new GenericOwnerHurtByTargetGoal((Mob)this, this::getSummoner));
        this.targetSelector.setControlFlag(2, (Goal)new GenericOwnerHurtTargetGoal((Mob)this, this::getSummoner));
        this.targetSelector.setControlFlag(3, (Goal)new GenericCopyOwnerTargetGoal((PathfinderMob)this, this::getSummoner));
        this.targetSelector.setControlFlag(4, (Goal)new GenericHurtByTargetGoal((PathfinderMob)this, entity -> entity == this.getSummoner()).setAlertOthers(new Class[0]));
    }

    public LivingEntity getSummoner() {
        return OwnerHelper.getAndCacheOwner((Level)this.level(), (LivingEntity)this.cachedSummoner, (UUID)this.summonerUUID);
    }

    public void setSummoner(@Nullable LivingEntity owner) {
        if (owner != null) {
            this.summonerUUID = owner.getUUID();
            this.cachedSummoner = owner;
        }
    }

    public void setLootTableSeed(Entity.RemovalReason pReason) {
        super.setLootTableSeed(pReason);
    }

    public void finalizeSpawn(DamageSource pDamageSource) {
        this.onDeathHelper();
        super.finalizeSpawn(pDamageSource);
    }

    public void onRemovedFromWorld() {
        this.onRemovedHelper((Entity)this, (SummonTimer)TravelopticsEffects.VOID_TOME_TIMER.get());
        super.onRemovedFromWorld();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.summonerUUID = OwnerHelper.deserializeOwner((CompoundTag)compoundTag);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        OwnerHelper.serializeOwner((CompoundTag)compoundTag, (UUID)this.summonerUUID);
    }

    public boolean doHurtTarget(Entity pEntity) {
        return Utils.doMeleeAttack((Mob)this, (Entity)pEntity, (DamageSource)((AbstractSpell)TravelopticsSpells.CONJURE_VOID_TOMES_SPELL.get()).getDamageSource((Entity)this, (Entity)this.getSummoner()));
    }

    @Override
    public boolean isAlliedTo(Entity pEntity) {
        return super.isAlliedTo(pEntity) || this.isAlliedHelper(pEntity);
    }

    public void onUnSummon() {
        if (!this.level().isClientSide) {
            MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)TravelopticsParticleHelper.LIGHT_RED_GLOWING_ENCHANT, (double)this.getX(), (double)(this.getY() + 1.8), (double)this.getZ(), (int)30, (double)0.4, (double)0.8, (double)0.4, (double)0.03, (boolean)false);
            this.discard();
        }
    }

    @Override
    public boolean sendSystemMessage(DamageSource pSource, float pAmount) {
        if (!pSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY) && this.shouldIgnoreDamage(pSource)) {
            return false;
        }
        return super.sendSystemMessage(pSource, pAmount);
    }

    protected boolean shouldDespawnInPeaceful() {
        return false;
    }
}

