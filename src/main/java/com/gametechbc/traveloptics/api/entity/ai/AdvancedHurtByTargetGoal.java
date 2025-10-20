/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 */
package com.gametechbc.traveloptics.api.entity.ai;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

public class AdvancedHurtByTargetGoal
extends HurtByTargetGoal {
    private final Map<LivingEntity, Integer> attackerMemory = new HashMap<LivingEntity, Integer>();
    private int forcedAggroTime;
    private float intensity;

    public AdvancedHurtByTargetGoal(PathfinderMob mob, Class<?> ... ToIgnoreDamage) {
        super(mob, (Class[])ToIgnoreDamage);
    }

    public void tick() {
        super.tick();
        LivingEntity lastAttacker = this.mob.getLastHurtByMob();
        if (lastAttacker != null) {
            this.attackerMemory.put(lastAttacker, 100);
        }
        Iterator<Map.Entry<LivingEntity, Integer>> iterator = this.attackerMemory.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<LivingEntity, Integer> entry = iterator.next();
            int timeLeft = entry.getValue() - 1;
            if (timeLeft <= 0) {
                iterator.remove();
                continue;
            }
            entry.setValue(timeLeft);
        }
        if (this.timestamp != this.mob.getLastHurtByMobTimestamp()) {
            this.timestamp = this.mob.getLastHurtByMobTimestamp();
            if (lastAttacker != this.targetMob) {
                this.forcedAggroTime -= 20;
            } else {
                this.forcedAggroTime += (int)(20.0f * this.intensity);
                this.intensity *= 0.8f;
            }
            LivingEntity newTarget = this.getMostRelevantAttacker();
            if (newTarget != null && newTarget != this.mob.getTarget()) {
                this.mob.setTarget(newTarget);
            }
        }
    }

    public void start() {
        super.start();
        this.forcedAggroTime = 40 + this.mob.getRandom().nextInt(140);
        this.intensity = 1.0f;
    }

    public boolean canContinueToUse() {
        return --this.forcedAggroTime > 0 && super.canContinueToUse();
    }

    private LivingEntity getMostRelevantAttacker() {
        return this.attackerMemory.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }
}

