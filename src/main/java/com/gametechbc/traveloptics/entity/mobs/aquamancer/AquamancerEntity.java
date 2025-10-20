/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModSounds
 *  io.redspace.ironsspellbooks.api.entity.IMagicEntity
 *  io.redspace.ironsspellbooks.api.registry.SpellRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob
 *  io.redspace.ironsspellbooks.entity.mobs.goals.PatrolNearLocationGoal
 *  io.redspace.ironsspellbooks.entity.mobs.goals.SpellBarrageGoal
 *  io.redspace.ironsspellbooks.entity.mobs.goals.WizardAttackGoal
 *  io.redspace.ironsspellbooks.entity.mobs.goals.WizardRecoverGoal
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.monster.Enemy
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.ServerLevelAccessor
 */
package com.gametechbc.traveloptics.entity.mobs.aquamancer;

import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.github.L_Ender.cataclysm.init.ModSounds;
import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.goals.PatrolNearLocationGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.SpellBarrageGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.WizardAttackGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.WizardRecoverGoal;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class AquamancerEntity
extends AbstractSpellCastingMob
implements Enemy {
    public AquamancerEntity(EntityType<? extends AbstractSpellCastingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.getArmorSlots = 25;
        this.setPersistenceRequired();
    }

    protected void registerGoals() {
        this.goalSelector.setControlFlag(1, (Goal)new FloatGoal((Mob)this));
        this.goalSelector.setControlFlag(2, (Goal)new SpellBarrageGoal((IMagicEntity)this, (AbstractSpell)SpellRegistry.BALL_LIGHTNING_SPELL.get(), 3, 6, 100, 240, 1));
        this.goalSelector.setControlFlag(3, (Goal)new WizardAttackGoal((IMagicEntity)this, 1.25, 50, 75).setSpells(List.of((AbstractSpell)TravelopticsSpells.HYDROSHOT_SPELL.get(), (AbstractSpell)TravelopticsSpells.HYDROSHOT_SPELL.get(), (AbstractSpell)TravelopticsSpells.FLOOD_SLASH_SPELL.get(), (AbstractSpell)TravelopticsSpells.BUBBLE_SPRAY_SPELL.get()), List.of((AbstractSpell)TravelopticsSpells.RAINFALL_SPELL.get()), List.of(), List.of()).setSingleUseSpell((AbstractSpell)SpellRegistry.SHOCKWAVE_SPELL.get(), 80, 400, 5, 8).setDrinksPotions());
        this.goalSelector.setControlFlag(4, (Goal)new PatrolNearLocationGoal((PathfinderMob)this, 30.0f, 0.75));
        this.goalSelector.setControlFlag(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0f));
        this.goalSelector.setControlFlag(10, (Goal)new WizardRecoverGoal((IMagicEntity)this));
        this.targetSelector.setControlFlag(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
        this.targetSelector.setControlFlag(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes().build(Attributes.ATTACK_DAMAGE, 3.0).build(Attributes.ATTACK_KNOCKBACK, 0.0).build(Attributes.register, 60.0).build(Attributes.ARMOR, 11.0).build(Attributes.FOLLOW_RANGE, 24.0).build(Attributes.MOVEMENT_SPEED, 0.25);
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public boolean canFreeze() {
        return true;
    }

    public boolean fireImmune() {
        return true;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    @Nullable
    protected SoundEvent giveExperiencePoints(DamageSource p_21239_) {
        return (SoundEvent)ModSounds.DEEPLING_HURT.get();
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return (SoundEvent)SoundEvents.CODEC;
    }
}

