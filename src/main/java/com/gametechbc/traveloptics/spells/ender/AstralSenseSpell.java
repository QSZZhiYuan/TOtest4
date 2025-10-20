/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
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
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.spells.ender;

import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellAnimations;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import java.util.List;
import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

@AutoSpellConfig
public class AstralSenseSpell
extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "astral_sense");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.RARE).setSchoolResource(SchoolRegistry.ENDER_RESOURCE).setMaxLevel(3).setCooldownSeconds(120.0).build();

    public AstralSenseSpell() {
        this.manaCostPerLevel = 50;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 100;
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

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.empty();
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of((SoundEvent)ACSoundRegistry.FORSAKEN_AOE.get());
    }

    public AnimationHolder getCastFinishAnimation() {
        return SpellAnimations.SLASH_ANIMATION;
    }

    public void onCast(Level level, int spellLevel, LivingEntity caster, CastSource castSource, MagicData playerMagicData) {
        int duration = this.getDuration(spellLevel, caster);
        int amplifier = 0;
        AABB searchBox = caster.getBoundingBox().inflate(this.getRadius(spellLevel));
        List nearbyMobs = level.getNearbyEntities(Mob.class, searchBox, Entity::isAlive);
        for (Mob mob : nearbyMobs) {
            mob.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.ASTRAL_PATH_REVEAL.get(), duration, amplifier, false, false, false));
            mob.getStandingEyeHeight(new MobEffectInstance(MobEffects.GLOWING, 10, amplifier, false, false, false));
        }
        if (caster.isCrouching()) {
            caster.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.ASTRAL_SENSE_TREASURE.get(), duration, amplifier, false, false, false));
        } else {
            caster.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.ASTRAL_SENSE.get(), duration, amplifier, false, false, false));
        }
        ScreenShake_Entity.ScreenShake((Level)level, (Vec3)caster.position(), (float)12.0f, (float)0.02f, (int)10, (int)20);
        super.onCast(level, spellLevel, caster, castSource, playerMagicData);
    }

    private int getDuration(int spellLevel, LivingEntity caster) {
        return (int)(800.0f + this.getSpellPower(spellLevel, (Entity)caster) * 100.0f);
    }

    private double getRadius(int spellLevel) {
        return 20 + spellLevel * 5;
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.selector((String)"ui.traveloptics.radius", (Object[])new Object[]{Utils.stringTruncation((double)this.getRadius(spellLevel), (int)2)}), Component.selector((String)"ui.traveloptics.duration", (Object[])new Object[]{Utils.timeFromTicks((float)this.getDuration(spellLevel, caster), (int)2)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }
}

