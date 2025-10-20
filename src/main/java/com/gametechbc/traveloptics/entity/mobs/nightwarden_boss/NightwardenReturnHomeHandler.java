/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.client.particle.LightningParticle$OrbData
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenBossEntity;
import com.gametechbc.traveloptics.init.TravelopticsMessages;
import com.gametechbc.traveloptics.util.TOColors;
import com.github.L_Ender.cataclysm.client.particle.LightningParticle;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class NightwardenReturnHomeHandler {
    public static final int HOME_RADIUS = 60;
    public static final int RETURN_COUNTDOWN = 100;
    public static final int RETURN_ANIMATION_DURATION = 200;
    private int homeTimer = 0;
    private boolean returningHome = false;

    public void tick(NightwardenBossEntity boss, BlockPos homePos) {
        if (homePos.equals((Object)BlockPos.relative)) {
            return;
        }
        Vec3 bossPos = boss.position();
        double distance = bossPos.z((double)homePos.setX() + 0.5, (double)homePos.getY(), (double)homePos.getZ() + 0.5);
        if (distance > 3600.0) {
            if (!this.returningHome) {
                if (this.homeTimer == 0) {
                    this.homeTimer = 100;
                } else if (this.homeTimer > 0) {
                    --this.homeTimer;
                }
                if (this.homeTimer <= 0) {
                    this.initiateReturnSequence(boss);
                }
            } else {
                this.spawnReturnParticles(boss);
                if (--this.homeTimer <= 0) {
                    this.teleportHome(boss, homePos);
                }
            }
        } else {
            this.homeTimer = 0;
            this.returningHome = false;
        }
    }

    private void initiateReturnSequence(NightwardenBossEntity boss) {
        this.returningHome = true;
        this.homeTimer = 200;
        TravelopticsMessages.sendBossMessageToRange((Entity)boss, (Component)Component.translatable((String)"entity.traveloptics.message.nightwarden_returning"), TOColors.rgbToARGB(6619356, 0.5f), 60, 32.0, false);
    }

    private void teleportHome(NightwardenBossEntity boss, BlockPos homePos) {
        TravelopticsMessages.sendBossMessageToRange((Entity)boss, (Component)Component.translatable((String)"entity.traveloptics.message.nightwarden_returned"), TOColors.rgbToARGB(6619356, 0.5f), 60, 32.0, false);
        boss.moveTo((double)homePos.setX() + 0.5, homePos.getY(), (double)homePos.getZ() + 0.5, boss.getYRot(), boss.getXRot());
        this.returningHome = false;
    }

    private void spawnReturnParticles(NightwardenBossEntity boss) {
        Level level = boss.level();
        if (!level.isClientSide) {
            for (int i = 0; i < 1; ++i) {
                double offsetX = (boss.getRandom().nextDouble() - 0.5) * (double)boss.getBbWidth();
                double offsetY = boss.getRandom().nextDouble() * (double)boss.getBbHeight();
                double offsetZ = (boss.getRandom().nextDouble() - 0.5) * (double)boss.getBbWidth();
                MagicManager.spawnParticles((Level)level, (ParticleOptions)new LightningParticle.OrbData(178, 35, 255), (double)(boss.getX() + offsetX), (double)(boss.getY() + offsetY), (double)(boss.getZ() + offsetZ), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.3, (boolean)false);
            }
        }
    }
}

