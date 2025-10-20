/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 */
package com.gametechbc.traveloptics.api.entity.ai;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

public class HurtByNearestTargetGoal
extends HurtByTargetGoal {
    public HurtByNearestTargetGoal(PathfinderMob creatureIn, Class<?> ... excludeReinforcementTypes) {
        super(creatureIn, (Class[])excludeReinforcementTypes);
    }

    public boolean hasNotVisited() {
        if (!super.hasNotVisited()) {
            LivingEntity lastTarget = this.mob.getLastHurtMob();
            if (lastTarget != null && this.mob.getLastHurtByMob() == null) {
                this.mob.getBreedOffspring(lastTarget);
            }
            return false;
        }
        return true;
    }

    public boolean canContinueToUse() {
        if (!super.canContinueToUse()) {
            return false;
        }
        LivingEntity revengeTarget = this.mob.getLastHurtByMob();
        if (super.hasNotVisited() && revengeTarget != this.targetMob && this.targetMob != null && revengeTarget != null && this.mob.getZ((Entity)revengeTarget) < this.mob.getZ((Entity)this.targetMob)) {
            this.mob.getAttackAnim((Entity)this.targetMob);
            return false;
        }
        return true;
    }
}

