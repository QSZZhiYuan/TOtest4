/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity
 *  com.github.L_Ender.cataclysm.init.ModEffect
 *  com.github.L_Ender.cataclysm.init.ModParticle
 *  com.github.L_Ender.cataclysm.init.ModSounds
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.AnimationHolder
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  io.redspace.ironsspellbooks.spells.eldritch.AbstractEldritchSpell
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.spells.eldritch;

import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.spells.TravelopticsSpellAnimations;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModParticle;
import com.github.L_Ender.cataclysm.init.ModSounds;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.spells.eldritch.AbstractEldritchSpell;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

@AutoSpellConfig
public class ShadowedMiasmaSpell
extends AbstractEldritchSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "shadowed_miasma");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.RARE).setSchoolResource(SchoolRegistry.ELDRITCH_RESOURCE).setMaxLevel(3).setCooldownSeconds(45.0).build();

    public ShadowedMiasmaSpell() {
        this.baseManaCost = 20;
        this.manaCostPerLevel = 30;
        this.baseSpellPower = 2;
        this.spellPowerPerLevel = 1;
        this.castTime = 25;
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
        return Optional.of((SoundEvent)ModSounds.BLACK_HOLE_CLOSING.get());
    }

    public AnimationHolder getCastStartAnimation() {
        return TravelopticsSpellAnimations.MIASMA_START;
    }

    public AnimationHolder getCastFinishAnimation() {
        return TravelopticsSpellAnimations.MIASMA_END;
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        int effectLevel = (int)this.getSpellPower(spellLevel, (Entity)caster);
        return List.of(Component.selector((String)"ui.traveloptics.radius", (Object[])new Object[]{Utils.stringTruncation((double)this.getRadius(spellLevel, caster), (int)2)}), Component.selector((String)"ui.traveloptics.abyssal_strike_level", (Object[])new Object[]{effectLevel}), Component.selector((String)"ui.traveloptics.effect_length", (Object[])new Object[]{Utils.timeFromTicks((float)this.getDuration(spellLevel, caster), (int)2)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = this.getRadius(spellLevel, entity);
        int effectLevel = (int)this.getSpellPower(spellLevel, (Entity)entity);
        int duration = this.getDuration(spellLevel, entity);
        level.getChunk((Entity)entity, entity.getBoundingBox().getYsize((double)radius, 4.0, (double)radius), target -> !DamageSources.isFriendlyFireBetween((Entity)target, (Entity)entity) && Utils.hasLineOfSight((Level)level, (Entity)entity, (Entity)target, (boolean)true)).forEach(target -> {
            LivingEntity livingEntity;
            if (target instanceof LivingEntity && (livingEntity = (LivingEntity)target).getZ((Entity)entity) < (double)(radius * radius)) {
                livingEntity.getStandingEyeHeight(new MobEffectInstance((MobEffect)ModEffect.EFFECTABYSSAL_CURSE.get(), duration, effectLevel - 1));
            }
        });
        entity.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.ABYSSAL_STRIKE.get(), duration, effectLevel - 1));
        MagicManager.spawnParticles((Level)level, (ParticleOptions)((ParticleOptions)ModParticle.SHOCK_WAVE.get()), (double)entity.getX(), (double)entity.getY(), (double)entity.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
        ScreenShake_Entity.ScreenShake((Level)level, (Vec3)entity.position(), (float)radius, (float)0.02f, (int)10, (int)20);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return 190 + spellLevel * 150;
    }

    public float getRadius(int spellLevel, LivingEntity caster) {
        return 6 + spellLevel * 2;
    }

    public boolean stopSoundOnCancel() {
        return true;
    }
}

