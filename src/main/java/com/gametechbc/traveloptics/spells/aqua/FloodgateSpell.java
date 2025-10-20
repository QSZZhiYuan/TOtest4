/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.SpellAnimations
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.AnimationHolder
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.spells.aqua;

import com.gametechbc.traveloptics.api.init.TravelopticsSchools;
import com.gametechbc.traveloptics.api.utils.TOGeneralUtils;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellAnimations;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

@AutoSpellConfig
public class FloodgateSpell
extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "floodgate");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.EPIC).setSchoolResource(TravelopticsSchools.AQUA_RESOURCE).setMaxLevel(8).setCooldownSeconds(180.0).build();

    public FloodgateSpell() {
        this.baseManaCost = 45;
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
    }

    public CastType getCastType() {
        return CastType.INSTANT;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        int duration = this.getDuration(spellLevel);
        int damage = this.getDamage(spellLevel, entity);
        entity.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.FLOODGATE_EFFECT.get(), duration, damage, false, false, true));
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public float getDamageCap() {
        return 0.3f;
    }

    private String getDamageCapPercentage() {
        return String.format("%.1f%%", Float.valueOf(this.getDamageCap() * 100.0f));
    }

    public int getDuration(int spellLevel) {
        return 200 + spellLevel * 40;
    }

    public int getDamage(int spellLevel, LivingEntity caster) {
        return (int)(2.0f + this.getSpellPower(spellLevel, (Entity)caster) * 1.0f);
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return TOGeneralUtils.buildAquaSpellInfo(1, true, Component.selector((String)"ui.traveloptics.floodgate_cap", (Object[])new Object[]{this.getDamageCapPercentage()}), Component.selector((String)"ui.traveloptics.damage", (Object[])new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), (int)2)}), Component.selector((String)"ui.traveloptics.effect_length", (Object[])new Object[]{Utils.timeFromTicks((float)this.getDuration(spellLevel), (int)1)}));
    }

    public AnimationHolder getCastFinishAnimation() {
        return SpellAnimations.SELF_CAST_ANIMATION;
    }
}

