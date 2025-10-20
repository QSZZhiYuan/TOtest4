/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.sounds.SoundInstance
 *  net.minecraft.client.sounds.SoundManager
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.event.TickEvent$ClientTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  org.jetbrains.annotations.Nullable
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.misc;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenBossEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.misc.NightwardenSoundInstance;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(value={Dist.CLIENT})
public class NightwardenMusicManager {
    @Nullable
    private static NightwardenMusicManager INSTANCE;
    static final SoundSource SOUND_SOURCE;
    NightwardenBossEntity boss;
    final SoundManager soundManager;
    NightwardenSoundInstance introMusic;
    NightwardenSoundInstance loopMusic;
    private boolean finishing = false;
    private boolean isPlayingLoop = false;
    private long introStartTime;
    private static final int INTRO_LENGTH_MILLIS = 3384;

    private NightwardenMusicManager(NightwardenBossEntity boss) {
        this.boss = boss;
        this.soundManager = Minecraft.getInstance().getSoundManager();
        this.introMusic = new NightwardenSoundInstance((SoundEvent)TravelopticsSounds.NIGHTWARDEN_MUSIC_START.get(), SOUND_SOURCE, false);
        this.loopMusic = new NightwardenSoundInstance((SoundEvent)TravelopticsSounds.NIGHTWARDEN_MUSIC_LOOP.get(), SOUND_SOURCE, true);
        this.startIntro();
    }

    private void startIntro() {
        this.soundManager.apply((SoundInstance)this.introMusic);
        this.introStartTime = System.currentTimeMillis();
    }

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        if (INSTANCE != null && event.phase == TickEvent.Phase.START && !Minecraft.getInstance().isPaused()) {
            INSTANCE.tick();
        }
    }

    public static void createOrResumeInstance(NightwardenBossEntity boss) {
        if (INSTANCE == null || INSTANCE.isDone()) {
            INSTANCE = new NightwardenMusicManager(boss);
        }
    }

    public static void stop(NightwardenBossEntity boss) {
        if (INSTANCE != null && NightwardenMusicManager.INSTANCE.boss.getUUID().equals(boss.getUUID())) {
            INSTANCE.stopMusic();
            NightwardenMusicManager.INSTANCE.finishing = true;
        }
    }

    private void tick() {
        float phaseVolume;
        if (this.isDone() || this.finishing) {
            return;
        }
        if (this.boss.isDeadOrDying() || this.boss.isRemoved()) {
            this.stopMusic();
            this.finishing = true;
            return;
        }
        if (!this.isPlayingLoop && System.currentTimeMillis() >= this.introStartTime + 3384L) {
            this.startLoopMusic();
        }
        float f = phaseVolume = this.boss.isPhaseTransitioning() ? 0.3f : 1.0f;
        if (this.isPlayingLoop) {
            this.loopMusic.setTargetVolume(phaseVolume);
        } else {
            this.introMusic.setTargetVolume(phaseVolume);
        }
    }

    private boolean isDone() {
        return this.isPlayingLoop && (this.loopMusic.isStopped() || !this.soundManager.isActive((SoundInstance)this.loopMusic)) || !this.isPlayingLoop && (this.introMusic.isStopped() || !this.soundManager.isActive((SoundInstance)this.introMusic));
    }

    private void startLoopMusic() {
        this.soundManager.apply((SoundInstance)this.loopMusic);
        this.isPlayingLoop = true;
    }

    public void stopMusic() {
        if (this.isPlayingLoop) {
            this.loopMusic.triggerStop();
        } else {
            this.introMusic.triggerStop();
        }
        this.isPlayingLoop = false;
    }

    public static void hardStop() {
        if (INSTANCE != null) {
            INSTANCE.stopMusic();
            INSTANCE = null;
        }
    }

    static {
        SOUND_SOURCE = SoundSource.RECORDS;
    }
}

