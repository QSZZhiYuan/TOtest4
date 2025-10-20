/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.util.AnimationHolder
 *  net.minecraft.sounds.SoundEvent
 */
package com.gametechbc.traveloptics.api.spells;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import net.minecraft.sounds.SoundEvent;

public abstract class AdvancedSpell
extends AbstractSpell {
    private static final Random RANDOM = new Random();
    private int startAnimationIndex = 0;
    private int finishAnimationIndex = 0;
    private int startSoundIndex = 0;
    private int finishSoundIndex = 0;

    public List<AnimationHolder> getPossibleCastStartAnimations() {
        return List.of(super.getCastStartAnimation());
    }

    public List<AnimationHolder> getPossibleCastFinishAnimations() {
        return List.of(super.getCastFinishAnimation());
    }

    public List<SoundEvent> getPossibleCastStartSounds() {
        return super.getCastStartSound().map(List::of).orElse(List.of());
    }

    public List<SoundEvent> getPossibleCastFinishSounds() {
        return super.getCastFinishSound().map(List::of).orElse(List.of());
    }

    public boolean isOrderedAnimations() {
        return false;
    }

    public boolean isOrderedSounds() {
        return false;
    }

    public AnimationHolder getCastStartAnimation() {
        List<AnimationHolder> startAnimations = this.getPossibleCastStartAnimations();
        if (startAnimations.isEmpty()) {
            return super.getCastStartAnimation();
        }
        if (this.isOrderedAnimations()) {
            AnimationHolder animation = startAnimations.get(this.startAnimationIndex);
            this.startAnimationIndex = (this.startAnimationIndex + 1) % startAnimations.size();
            return animation;
        }
        return startAnimations.get(RANDOM.nextInt(startAnimations.size()));
    }

    public AnimationHolder getCastFinishAnimation() {
        List<AnimationHolder> finishAnimations = this.getPossibleCastFinishAnimations();
        if (finishAnimations.isEmpty()) {
            return super.getCastFinishAnimation();
        }
        if (this.isOrderedAnimations()) {
            AnimationHolder animation = finishAnimations.get(this.finishAnimationIndex);
            this.finishAnimationIndex = (this.finishAnimationIndex + 1) % finishAnimations.size();
            return animation;
        }
        return finishAnimations.get(RANDOM.nextInt(finishAnimations.size()));
    }

    public Optional<SoundEvent> getCastStartSound() {
        List<SoundEvent> startSounds = this.getPossibleCastStartSounds();
        if (startSounds.isEmpty()) {
            return super.getCastStartSound();
        }
        if (this.isOrderedSounds()) {
            SoundEvent sound = startSounds.get(this.startSoundIndex);
            this.startSoundIndex = (this.startSoundIndex + 1) % startSounds.size();
            return Optional.of(sound);
        }
        return Optional.of(startSounds.get(RANDOM.nextInt(startSounds.size())));
    }

    public Optional<SoundEvent> getCastFinishSound() {
        List<SoundEvent> finishSounds = this.getPossibleCastFinishSounds();
        if (finishSounds.isEmpty()) {
            return super.getCastFinishSound();
        }
        if (this.isOrderedSounds()) {
            SoundEvent sound = finishSounds.get(this.finishSoundIndex);
            this.finishSoundIndex = (this.finishSoundIndex + 1) % finishSounds.size();
            return Optional.of(sound);
        }
        return Optional.of(finishSounds.get(RANDOM.nextInt(finishSounds.size())));
    }
}

