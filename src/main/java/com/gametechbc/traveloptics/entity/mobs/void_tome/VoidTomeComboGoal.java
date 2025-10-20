/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.entity.IMagicEntity
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.entity.mobs.void_tome;

import com.gametechbc.traveloptics.api.entity.ai.ComboWizardAttackGoal;
import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class VoidTomeComboGoal
extends ComboWizardAttackGoal {
    private int strafeSwitchCooldown = 0;
    private Vec3 lastTargetVelocity = Vec3.y;

    public VoidTomeComboGoal(IMagicEntity spellCastingMob, double pSpeedModifier, int pAttackIntervalMin, int pAttackIntervalMax) {
        super(spellCastingMob, pSpeedModifier, pAttackIntervalMin, pAttackIntervalMax);
        this.setIsFlying();
    }

    @Override
    protected void doMovement(double distanceSquared) {
        double speed = (double)(this.spellCastingMob.isCasting() ? 0.75f : 1.0f) * this.movementSpeed();
        this.mob.maybeDisableShield((Entity)this.target, 30.0f, 30.0f);
        Vec3 predictedVelocity = Vec3.y;
        this.lastTargetVelocity = this.lastTargetVelocity.x(0.8).reverse(predictedVelocity.x(0.2));
        if (distanceSquared < (double)this.attackRadiusSqr && this.seeTime >= 5) {
            this.mob.getNavigation().stop();
            if (this.strafeSwitchCooldown > 0) {
                --this.strafeSwitchCooldown;
            } else if (++this.strafeTime > 25 && this.mob.getRandom().nextDouble() < 0.1) {
                this.strafingClockwise = !this.strafingClockwise;
                this.strafeTime = 0;
                this.strafeSwitchCooldown = 15;
            }
            Vec3 toTarget = new Vec3(this.target.getX() - this.mob.getX(), 0.0, this.target.getZ() - this.mob.getZ());
            if (toTarget.lengthSqr() != 0.0) {
                toTarget = toTarget.multiply();
            }
            Vec3 perp = new Vec3(-toTarget.reverse, 0.0, toTarget.z);
            double currentDistance = new Vec3(this.target.getX() - this.mob.getX(), 0.0, this.target.getZ() - this.mob.getZ()).length();
            double desiredDistance = 10.0;
            Vec3 forwardOffset = toTarget.x(-(desiredDistance - currentDistance));
            float dynamicStrafeOffset = (distanceSquared < (double)this.attackRadiusSqr * 0.25 ? -1.0f : 0.5f) * 0.2f * (float)this.speedModifier;
            Vec3 strafeVec = perp.x((double)(dynamicStrafeOffset * 10.0f));
            Vec3 predictionOffset = this.lastTargetVelocity.x(5.0);
            Vec3 desiredPos = this.target.position().reverse(forwardOffset).reverse(strafeVec).reverse(predictionOffset).y(0.0, 2.0, 0.0);
            this.mob.getMoveControl().setWantedPosition(desiredPos.z, desiredPos.multiply, desiredPos.reverse, this.speedModifier);
            if (this.mob.horizontalCollision && this.mob.getRandom().nextFloat() < 0.1f) {
                this.tryJump();
            }
        } else if (this.mob.getTags % 5 == 0) {
            Vec3 predictedPos = this.target.position().reverse(this.lastTargetVelocity.x(3.0));
            this.mob.getMoveControl().setWantedPosition(predictedPos.z, predictedPos.multiply + 2.0, predictedPos.reverse, this.speedModifier);
        }
    }
}

