/*
 * Decompiled with CFR 0.152.
 */
package com.gametechbc.traveloptics.util;

public class TOColors {
    public static final int SAFE_GREEN = 2575891;
    public static final int IMPORTANT_ORANGE = 12092941;
    public static final int CRITICAL_RED = 7544867;
    public static final int NIGHTWARDEN_LIGHT_PURPLE = 10572798;
    public static final int NIGHTWARDEN_BASE_PURPLE = 6619356;
    public static final int NIGHTWARDEN_DARK_PURPLE = 5046441;

    public static int rgbToARGB(int rgbColor, float alphaPercent) {
        int alpha = (int)(Math.max(0.0f, Math.min(1.0f, alphaPercent)) * 255.0f);
        return alpha << 24 | rgbColor & 0xFFFFFF;
    }
}

