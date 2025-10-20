/*
 * Decompiled with CFR 0.152.
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.animated_particle;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_defeated.NightwardenDefeatedEntity;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;

public class DefeatedEntityRespawnCooldown {
    public static void drawCooldownRuneCircle(NightwardenDefeatedEntity boss, boolean shouldShowParticle, int getCooldown, int getMaxCooldown) {
        if (!shouldShowParticle) {
            return;
        }
        float cooldownProgress = (float)getCooldown / (float)getMaxCooldown;
        float radius = boss.getBbWidth() * 1.5f;
        float height = 0.1f;
        int totalPoints = 36;
        int visiblePoints = Math.round((float)totalPoints * cooldownProgress);
        double timeOffset = (double)boss.level().getGameTime() / 80.0;
        for (int i = 0; i < visiblePoints; ++i) {
            double angle = Math.PI * 2 * (double)i / (double)totalPoints + timeOffset;
            double x = boss.getX() + (double)radius * Math.cos(angle);
            double z = boss.getZ() + (double)radius * Math.sin(angle);
            double y = boss.getY() + (double)height;
            if (i % 4 == 0) {
                for (int j = 0; j < 3; ++j) {
                    double runeHeight = (double)height + (double)j * 0.15;
                    boss.level().addDestroyBlockEffect(TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, false, x, boss.getY() + runeHeight, z, 0.0, 0.0, 0.0);
                }
                if (i % 8 == 0) {
                    double runeLength = 0.3;
                    double outwardX = x + runeLength * Math.cos(angle);
                    double outwardZ = z + runeLength * Math.sin(angle);
                    boss.level().addDestroyBlockEffect(TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, false, outwardX, y, outwardZ, 0.0, 0.0, 0.0);
                    double glyphAngle = angle + 1.5707963267948966;
                    double glyphX1 = outwardX + 0.1 * Math.cos(glyphAngle);
                    double glyphZ1 = outwardZ + 0.1 * Math.sin(glyphAngle);
                    double glyphX2 = outwardX - 0.1 * Math.cos(glyphAngle);
                    double glyphZ2 = outwardZ - 0.1 * Math.sin(glyphAngle);
                    boss.level().addDestroyBlockEffect(TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, false, glyphX1, y, glyphZ1, 0.0, 0.0, 0.0);
                    boss.level().addDestroyBlockEffect(TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, false, glyphX2, y, glyphZ2, 0.0, 0.0, 0.0);
                }
            }
            double particleY = y + Math.sin((double)(boss.level().getGameTime() + (long)(i * 2)) / 10.0) * 0.03;
            boss.level().addDestroyBlockEffect(TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, false, x, particleY, z, 0.0, 0.0, 0.0);
        }
        if ((double)cooldownProgress < 0.5) {
            float innerRadius = radius * 0.6f;
            int innerPoints = 12;
            double innerTimeOffset = (double)(-boss.level().getGameTime()) / 60.0;
            for (int i = 0; i < innerPoints; ++i) {
                double angle = Math.PI * 2 * (double)i / (double)innerPoints + innerTimeOffset;
                double x = boss.getX() + (double)innerRadius * Math.cos(angle);
                double z = boss.getZ() + (double)innerRadius * Math.sin(angle);
                double y = boss.getY() + (double)height * 1.2;
                boss.level().addDestroyBlockEffect(TravelopticsParticleHelper.SHORT_LIGHT_PURPLE_GLOWING_ENCHANT, false, x, y, z, 0.0, 0.0, 0.0);
                if (i % 3 != 0 || !((double)cooldownProgress < 0.3)) continue;
                double outerX = boss.getX() + (double)radius * Math.cos(angle);
                double outerZ = boss.getZ() + (double)radius * Math.sin(angle);
                int connectionPoints = 3;
                for (int j = 1; j < connectionPoints; ++j) {
                    double lerpFactor = (double)j / (double)connectionPoints;
                    double connectionX = x + (outerX - x) * lerpFactor;
                    double connectionZ = z + (outerZ - z) * lerpFactor;
                    double connectionY = y + (boss.getY() + (double)height - y) * lerpFactor;
                    boss.level().addDestroyBlockEffect(TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE, false, connectionX, connectionY, connectionZ, 0.0, 0.0, 0.0);
                }
            }
        }
        if ((double)cooldownProgress < 0.1) {
            int surgeParticles = 8;
            double surgeHeight = 1.0;
            for (int i = 0; i < surgeParticles; ++i) {
                double angle = Math.PI * 2 * (double)i / (double)surgeParticles;
                double x = boss.getX() + (double)radius * 0.5 * Math.cos(angle);
                double z = boss.getZ() + (double)radius * 0.5 * Math.sin(angle);
                boss.level().addDestroyBlockEffect(TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE, false, x, boss.getY() + (double)height, z, 0.0, surgeHeight * 0.05, 0.0);
            }
        }
    }
}

