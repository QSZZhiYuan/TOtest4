/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.entity.IMagicEntity
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.api.registry.SpellRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.entity.mobs.dead_king_boss.DeadKingAnimatedWarlockAttackGoal
 *  io.redspace.ironsspellbooks.entity.mobs.dead_king_boss.DeadKingBoss
 *  io.redspace.ironsspellbooks.entity.mobs.goals.PatrolNearLocationGoal
 *  io.redspace.ironsspellbooks.entity.mobs.goals.SpellBarrageGoal
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.WrappedGoal
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.entity.mobs;

import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.entity.mobs.dead_king_boss.DeadKingAnimatedWarlockAttackGoal;
import io.redspace.ironsspellbooks.entity.mobs.dead_king_boss.DeadKingBoss;
import io.redspace.ironsspellbooks.entity.mobs.goals.PatrolNearLocationGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.SpellBarrageGoal;
import java.util.List;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class EnragedDeadKingBoss
extends DeadKingBoss {
    public EnragedDeadKingBoss(EntityType<? extends DeadKingBoss> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPersistenceRequired();
        this.getArmorSlots = 60;
    }

    public EnragedDeadKingBoss(Level level) {
        this((EntityType<? extends DeadKingBoss>)((EntityType)TravelopticsEntities.ENRAGED_DEAD_KING.get()), level);
        this.setPersistenceRequired();
    }

    protected void setFirstPhaseGoals() {
        this.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
        this.goalSelector.setControlFlag(x -> true);
        this.goalSelector.setControlFlag(0, (Goal)new FloatGoal((Mob)this));
        this.goalSelector.setControlFlag(1, (Goal)new DeadKingBarrageGoal((IMagicEntity)this, (AbstractSpell)SpellRegistry.WITHER_SKULL_SPELL.get(), 3, 4, 70, 140, 3));
        this.goalSelector.setControlFlag(2, (Goal)new DeadKingBarrageGoal((IMagicEntity)this, (AbstractSpell)TravelopticsSpells.CURSED_REVENANTS_SPELL.get(), 4, 5, 400, 800, 1));
        this.goalSelector.setControlFlag(3, (Goal)new DeadKingBarrageGoal((IMagicEntity)this, (AbstractSpell)SpellRegistry.BLOOD_STEP_SPELL.get(), 1, 1, 100, 180, 1));
        this.goalSelector.setControlFlag(4, (Goal)new DeadKingAnimatedWarlockAttackGoal((DeadKingBoss)this, 1.0, 55, 85, 4.0f).setSpellQuality(0.7f, 0.9f).setSpells(List.of((AbstractSpell)TravelopticsSpells.STELE_CASCADE_SPELL.get(), (AbstractSpell)SpellRegistry.BLOOD_SLASH_SPELL.get(), (AbstractSpell)SpellRegistry.WITHER_SKULL_SPELL.get(), (AbstractSpell)TravelopticsSpells.BLOOD_HOWL_SPELL.get(), (AbstractSpell)TravelopticsSpells.ASHEN_BREATH.get(), (AbstractSpell)SpellRegistry.ACID_ORB_SPELL.get(), (AbstractSpell)SpellRegistry.FANG_STRIKE_SPELL.get()), List.of((AbstractSpell)SpellRegistry.BLOOD_STEP_SPELL.get(), (AbstractSpell)SpellRegistry.ACUPUNCTURE_SPELL.get()), List.of(), List.of()).setSingleUseSpell((AbstractSpell)TravelopticsSpells.BLACKOUT_SPELL.get(), 10, 30, 3, 3).setMeleeBias(0.8f, 0.8f).setAllowFleeing(false));
        this.goalSelector.setControlFlag(5, (Goal)new PatrolNearLocationGoal((PathfinderMob)this, 32.0f, (double)0.9f));
        this.goalSelector.setControlFlag(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0f));
    }

    protected void setFinalPhaseGoals() {
        this.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
        this.goalSelector.setControlFlag(x -> true);
        this.goalSelector.setControlFlag(1, (Goal)new DeadKingBarrageGoal((IMagicEntity)this, (AbstractSpell)TravelopticsSpells.BLOOD_HOWL_SPELL.get(), 3, 3, 60, 140, 4));
        this.goalSelector.setControlFlag(2, (Goal)new DeadKingBarrageGoal((IMagicEntity)this, (AbstractSpell)TravelopticsSpells.NOCTURNAL_SWARM_SPELL.get(), 4, 6, 400, 600, 1));
        this.goalSelector.setControlFlag(3, (Goal)new DeadKingBarrageGoal((IMagicEntity)this, (AbstractSpell)TravelopticsSpells.METEOR_STORM.get(), 4, 4, 600, 800, 1));
        this.goalSelector.setControlFlag(4, (Goal)new DeadKingAnimatedWarlockAttackGoal((DeadKingBoss)this, 1.0, 55, 85, 4.0f).setSpellQuality(1.0f, 1.0f).setSpells(List.of((AbstractSpell)TravelopticsSpells.AERIAL_COLLAPSE.get(), (AbstractSpell)TravelopticsSpells.STELE_CASCADE_SPELL.get(), (AbstractSpell)SpellRegistry.BLOOD_SLASH_SPELL.get(), (AbstractSpell)SpellRegistry.BLOOD_SLASH_SPELL.get(), (AbstractSpell)TravelopticsSpells.RAINFALL_SPELL.get(), (AbstractSpell)TravelopticsSpells.BLOOD_HOWL_SPELL.get(), (AbstractSpell)TravelopticsSpells.BLOOD_HOWL_SPELL.get(), (AbstractSpell)TravelopticsSpells.ASHEN_BREATH.get()), List.of((AbstractSpell)SpellRegistry.ACUPUNCTURE_SPELL.get()), List.of((AbstractSpell)SpellRegistry.BLOOD_STEP_SPELL.get()), List.of()).setIsFlying().setSingleUseSpell((AbstractSpell)TravelopticsSpells.BLACKOUT_SPELL.get(), 10, 30, 5, 5).setMeleeBias(0.8f, 0.8f).setAllowFleeing(false));
        this.goalSelector.setControlFlag(5, (Goal)new PatrolNearLocationGoal((PathfinderMob)this, 32.0f, (double)0.9f));
        this.goalSelector.setControlFlag(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0f));
        this.hasUsedSingleAttack = false;
    }

    public static AttributeSupplier.Builder deadkingAttributes() {
        return LivingEntity.createLivingAttributes().build(Attributes.ATTACK_DAMAGE, 14.0).build((Attribute)AttributeRegistry.SPELL_POWER.get(), 1.5).build(Attributes.ARMOR, 20.0).build((Attribute)AttributeRegistry.SPELL_RESIST.get(), 1.25).build(Attributes.register, 600.0).build(Attributes.KNOCKBACK_RESISTANCE, 1.0).build(Attributes.ATTACK_KNOCKBACK, 0.6).build(Attributes.FOLLOW_RANGE, 64.0).build(Attributes.MOVEMENT_SPEED, 0.155);
    }

    public boolean sendSystemMessage(DamageSource source, float damage) {
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return super.sendSystemMessage(source, damage);
        }
        damage = Math.min(this.DamageCap(), damage);
        return super.sendSystemMessage(source, damage);
    }

    public float DamageCap() {
        return 20.0f;
    }

    private class DeadKingBarrageGoal
    extends SpellBarrageGoal {
        public DeadKingBarrageGoal(IMagicEntity abstractSpellCastingMob, AbstractSpell spell, int minLevel, int maxLevel, int pAttackIntervalMin, int pAttackIntervalMax, int projectileCount) {
            super(abstractSpellCastingMob, spell, minLevel, maxLevel, pAttackIntervalMin, pAttackIntervalMax, projectileCount);
        }

        public boolean hasNotVisited() {
            return !EnragedDeadKingBoss.this.isMeleeing && super.hasNotVisited();
        }
    }
}

