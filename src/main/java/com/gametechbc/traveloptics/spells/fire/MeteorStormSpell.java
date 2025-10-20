/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity
 *  com.github.L_Ender.cataclysm.init.ModSounds
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.SchoolType
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.AnimationHolder
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions
 *  io.redspace.ironsspellbooks.util.ParticleHelper
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
package com.gametechbc.traveloptics.spells.fire;

import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.api.particle.SphereParticleManager;
import com.gametechbc.traveloptics.effects.MeteorStormEffect;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.spells.TravelopticsSpellAnimations;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import com.github.L_Ender.cataclysm.init.ModSounds;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.util.ParticleHelper;
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
public class MeteorStormSpell
extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "meteor_storm");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.EPIC).setSchoolResource(SchoolRegistry.FIRE_RESOURCE).setMaxLevel(6).setCooldownSeconds(90.0).build();

    public MeteorStormSpell() {
        this.manaCostPerLevel = 30;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 15;
        this.baseManaCost = 50;
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

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        MobEffect mobEffect;
        MobEffectInstance effectInstance;
        double radius = this.getRadius(spellLevel);
        int duration = this.getDuration(spellLevel);
        int damage = this.getDamage(entity, spellLevel);
        if (!entity.recreateFromPacket((MobEffect)TravelopticsEffects.METEOR_STORM.get())) {
            entity.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.METEOR_STORM.get(), duration, damage));
        }
        if ((effectInstance = entity.getStandingEyeHeight((MobEffect)TravelopticsEffects.METEOR_STORM.get())) != null && (mobEffect = effectInstance.compareTo()) instanceof MeteorStormEffect) {
            MeteorStormEffect stormEffect = (MeteorStormEffect)mobEffect;
            stormEffect.setOuterRadius(radius);
        }
        MagicManager.spawnParticles((Level)level, (ParticleOptions)new BlastwaveParticleOptions(((SchoolType)SchoolRegistry.FIRE.get()).getTargetingColor(), (float)radius), (double)entity.getX(), (double)(entity.getY() + (double)0.165f), (double)entity.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
        SphereParticleManager.spawnParticles(level, (Entity)entity, 60, ParticleHelper.FIRE, ParticleDirection.OUTWARD, 3.5);
        ScreenShake_Entity.ScreenShake((Level)level, (Vec3)entity.position(), (float)((float)radius), (float)0.03f, (int)10, (int)20);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public int getDuration(int spellLevel) {
        return 160 + spellLevel * 60;
    }

    public double getRadius(int spellLevel) {
        return 6.0f + (float)spellLevel * 3.0f;
    }

    public int getDamage(LivingEntity caster, int spellLevel) {
        return (int)(4.0 + (double)(this.getSpellPower(spellLevel, (Entity)caster) * 2.0f));
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of((SoundEvent)ModSounds.FLAME_BURST.get());
    }

    public AnimationHolder getCastStartAnimation() {
        return TravelopticsSpellAnimations.METEOR_STORM_CAST;
    }

    public AnimationHolder getCastFinishAnimation() {
        return AnimationHolder.pass();
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        double radius = this.getRadius(spellLevel);
        int damage = this.getDamage(caster, spellLevel);
        int duration = this.getDuration(spellLevel);
        return List.of(Component.selector((String)"ui.traveloptics.damage", (Object[])new Object[]{Utils.stringTruncation((double)damage, (int)2)}), Component.selector((String)"ui.traveloptics.radius", (Object[])new Object[]{Utils.stringTruncation((double)radius, (int)2)}), Component.selector((String)"ui.traveloptics.meteor_storm_duration", (Object[])new Object[]{Utils.timeFromTicks((float)duration, (int)2)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }
}

