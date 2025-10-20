/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.AnimationHolder
 *  io.redspace.ironsspellbooks.api.util.CameraShakeData
 *  io.redspace.ironsspellbooks.api.util.CameraShakeManager
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions
 *  io.redspace.ironsspellbooks.particle.ShockwaveParticleOptions
 *  javax.annotation.Nullable
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  org.joml.Vector3f
 */
package com.gametechbc.traveloptics.spells.eldritch;

import com.gametechbc.traveloptics.api.particle.CylinderParticleManager;
import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.api.spells.AbstractUniqueSpell;
import com.gametechbc.traveloptics.entity.projectiles.BlackoutAntiMagicField;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.spells.TravelopticsSpellAnimations;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.particle.ShockwaveParticleOptions;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

@AutoSpellConfig
public class BlackoutSpell
extends AbstractUniqueSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "blackout");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.LEGENDARY).setSchoolResource(SchoolRegistry.ELDRITCH_RESOURCE).setMaxLevel(3).setCooldownSeconds(120.0).build();

    public BlackoutSpell() {
        this.manaCostPerLevel = 50;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 39;
        this.baseManaCost = 100;
    }

    public CastType getCastType() {
        return CastType.LONG;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of((SoundEvent)TravelopticsSounds.BANISH_CHARGE.get());
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of((SoundEvent)TravelopticsSounds.BANISH_CAST.get());
    }

    public boolean stopSoundOnCancel() {
        return true;
    }

    public AnimationHolder getCastStartAnimation() {
        return TravelopticsSpellAnimations.BANISH_CHARGE;
    }

    public AnimationHolder getCastFinishAnimation() {
        return TravelopticsSpellAnimations.BANISH_FINISH;
    }

    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {
        return this.getCastTime(spellLevel);
    }

    public void onServerPreCast(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        MagicManager.spawnParticles((Level)level, (ParticleOptions)new ShockwaveParticleOptions(new Vector3f(0.1f, 0.1f, 0.1f), -3.0f, true), (double)entity.getX(), (double)entity.getY(), (double)entity.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
        super.onServerPreCast(level, spellLevel, entity, playerMagicData);
    }

    public void onServerCastTick(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        float radius = this.getRadius(spellLevel);
        CylinderParticleManager.spawnParticles(level, (Entity)entity, 8 * spellLevel, (ParticleOptions)ParticleTypes.SMOKE, ParticleDirection.UPWARD, radius, 3 * spellLevel, -1.0);
        CameraShakeManager.addCameraShake((CameraShakeData)new CameraShakeData(15, entity.position(), radius));
        entity.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.THIRD_PERSON_SWITCH.get(), 5, 0, false, false, false));
        super.onServerCastTick(level, spellLevel, entity, playerMagicData);
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = this.getRadius(spellLevel);
        int effectDuration = (int)this.getEffectDuration(spellLevel);
        int antiMagicZoneDuration = (int)this.getAntiMagicZoneDuration(spellLevel);
        level.getChunk((Entity)entity, entity.getBoundingBox().getYsize((double)radius, 4.0, (double)radius), target -> !DamageSources.isFriendlyFireBetween((Entity)target, (Entity)entity) && Utils.hasLineOfSight((Level)level, (Entity)entity, (Entity)target, (boolean)true)).forEach(target -> {
            LivingEntity livingEntity;
            if (target instanceof LivingEntity && (livingEntity = (LivingEntity)target).getZ((Entity)entity) < (double)(radius * radius)) {
                livingEntity.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.BLACKOUT.get(), effectDuration, 0));
            }
        });
        BlackoutAntiMagicField antiMagicField = new BlackoutAntiMagicField(level);
        antiMagicField.addAdditionalSaveData((Entity)entity);
        antiMagicField.setMovementDamage(3.0f);
        antiMagicField.setDuration(antiMagicZoneDuration);
        antiMagicField.setRadius(radius);
        antiMagicField.setCircular();
        antiMagicField.getRandomX(entity.position());
        level.addFreshEntity((Entity)antiMagicField);
        MagicManager.spawnParticles((Level)level, (ParticleOptions)new BlastwaveParticleOptions(new Vector3f(0.1f, 0.1f, 0.1f), radius), (double)entity.getX(), (double)(entity.getY() + (double)0.165f), (double)entity.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public float getRadius(int spellLevel) {
        return 6 + spellLevel * 2;
    }

    public float getEffectDuration(int spellLevel) {
        return 30.0f;
    }

    public float getAntiMagicZoneDuration(int spellLevel) {
        return 120 + spellLevel * 40;
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.selector((String)"ui.traveloptics.radius", (Object[])new Object[]{Utils.stringTruncation((double)this.getRadius(spellLevel), (int)2)}), Component.selector((String)"ui.traveloptics.effect_length", (Object[])new Object[]{Utils.timeFromTicks((float)this.getEffectDuration(spellLevel), (int)2)}), Component.selector((String)"ui.traveloptics.anti_magic_zone_duration", (Object[])new Object[]{Utils.timeFromTicks((float)this.getAntiMagicZoneDuration(spellLevel), (int)2)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }
}

