/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.AnimationHolder
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  javax.annotation.Nullable
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.spells.blood;

import com.gametechbc.traveloptics.effects.vigor_siphon.VigorSiphonEffect;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.spells.TravelopticsSpellAnimations;
import com.gametechbc.traveloptics.spells.VigorSiphonParticleAnimation;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

@AutoSpellConfig
public class VigorSiphonSpell
extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "vigor_siphon");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.EPIC).setSchoolResource(SchoolRegistry.BLOOD_RESOURCE).setMaxLevel(3).setCooldownSeconds(300.0).build();

    public VigorSiphonSpell() {
        this.manaCostPerLevel = 50;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 30;
        this.baseManaCost = 60;
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

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.selector((String)"ui.traveloptics.vigor_connection_range", (Object[])new Object[]{VigorSiphonEffect.getConnectionRange()}), Component.selector((String)"ui.traveloptics.vigor_siphon_strength", (Object[])new Object[]{Utils.stringTruncation((double)this.getEffectLevel(spellLevel, caster), (int)1)}), Component.selector((String)"ui.traveloptics.effect_length", (Object[])new Object[]{Utils.timeFromTicks((float)this.getDuration(spellLevel), (int)1)}), Component.selector((String)"ui.traveloptics.vigor_siphon_quirk", (Object[])new Object[]{Utils.stringTruncation((double)this.getEffectLevel(spellLevel, caster), (int)1)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }

    public void onServerCastTick(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        VigorSiphonParticleAnimation.playChannelingAnimation(level, entity);
        super.onServerCastTick(level, spellLevel, entity, playerMagicData);
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        VigorSiphonParticleAnimation.playCastCompleteAnimation(level, entity);
        int duration = this.getDuration(spellLevel);
        int effectLevel = this.getEffectLevel(spellLevel, entity);
        entity.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.VIGOR_SIPHON.get(), duration, effectLevel - 1, false, false, true));
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public int getDuration(int spellLevel) {
        return spellLevel * 600;
    }

    public int getEffectLevel(int spellLevel, LivingEntity caster) {
        return Math.min(30, (int)(6.0 + (double)this.getSpellPower(spellLevel, (Entity)caster) * 3.5));
    }

    public AnimationHolder getCastStartAnimation() {
        return TravelopticsSpellAnimations.VIGOR_SIPHON;
    }

    public AnimationHolder getCastFinishAnimation() {
        return AnimationHolder.pass();
    }
}

