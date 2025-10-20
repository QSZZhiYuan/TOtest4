/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.api.camera;

import com.gametechbc.traveloptics.api.camera.CameraAnimationStep;
import java.util.List;
import net.minecraft.world.phys.Vec3;

public class CameraAnimation {
    private final List<CameraAnimationStep> steps;
    private final int totalDuration;

    public CameraAnimation(List<CameraAnimationStep> steps) {
        this.steps = steps;
        this.totalDuration = steps.isEmpty() ? 0 : steps.get((int)(steps.size() - 1)).tick;
    }

    public CameraAnimationStep getCurrentStep(int currentTick) {
        if (this.steps.isEmpty()) {
            return null;
        }
        CameraAnimationStep lastStep = null;
        for (CameraAnimationStep step : this.steps) {
            if (step.tick > currentTick) break;
            lastStep = step;
        }
        return lastStep;
    }

    public CameraAnimationStep getInterpolatedStep(int currentTick, float partialTicks) {
        if (this.steps.isEmpty()) {
            return null;
        }
        CameraAnimationStep currentStep = null;
        CameraAnimationStep nextStep = null;
        for (int i = 0; i < this.steps.size() && this.steps.get((int)i).tick <= currentTick; ++i) {
            currentStep = this.steps.get(i);
            if (i + 1 >= this.steps.size()) continue;
            nextStep = this.steps.get(i + 1);
        }
        if (currentStep == null) {
            return null;
        }
        if (nextStep == null) {
            return currentStep;
        }
        float progress = ((float)currentTick + partialTicks - (float)currentStep.tick) / (float)(nextStep.tick - currentStep.tick);
        progress = Math.max(0.0f, Math.min(1.0f, progress));
        Vec3 interpPos = this.lerpVec3(currentStep.positionOffset, nextStep.positionOffset, progress);
        Vec3 interpRot = this.lerpVec3(currentStep.rotationOffset, nextStep.rotationOffset, progress);
        float interpFov = this.lerp(currentStep.fovOffset, nextStep.fovOffset, progress);
        return new CameraAnimationStep(currentTick, interpPos, interpRot, interpFov);
    }

    private Vec3 lerpVec3(Vec3 start, Vec3 end, float progress) {
        return new Vec3((double)this.lerp(start.x, end.x, progress), (double)this.lerp(start.y, end.y, progress), (double)this.lerp(start.z, end.z, progress));
    }

    private float lerp(double start, double end, float progress) {
        return (float)(start + (end - start) * (double)progress);
    }

    public int getTotalDuration() {
        return this.totalDuration;
    }

    public boolean isFinished(int currentTick) {
        return currentTick > this.totalDuration;
    }
}

