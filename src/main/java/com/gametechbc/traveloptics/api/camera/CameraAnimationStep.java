/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.api.camera;

import net.minecraft.world.phys.Vec3;

public class CameraAnimationStep {
    public final int tick;
    public final Vec3 positionOffset;
    public final Vec3 rotationOffset;
    public final float fovOffset;

    public CameraAnimationStep(int tick, Vec3 positionOffset, Vec3 rotationOffset, float fovOffset) {
        this.tick = tick;
        this.positionOffset = positionOffset;
        this.rotationOffset = rotationOffset;
        this.fovOffset = fovOffset;
    }

    public CameraAnimationStep(int tick, double x, double y, double z, float pitch, float yaw, float roll, float fov) {
        this(tick, new Vec3(x, y, z), new Vec3((double)pitch, (double)yaw, (double)roll), fov);
    }

    public CameraAnimationStep(int tick, Vec3 positionOffset, Vec3 rotationOffset) {
        this(tick, positionOffset, rotationOffset, 0.0f);
    }
}

