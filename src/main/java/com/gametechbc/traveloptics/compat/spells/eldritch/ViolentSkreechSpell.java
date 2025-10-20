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
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  io.redspace.ironsspellbooks.damage.SpellDamageSource
 *  io.redspace.ironsspellbooks.spells.eldritch.AbstractEldritchSpell
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.jetbrains.annotations.Nullable
 */
package com.gametechbc.traveloptics.compat.spells.eldritch;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.spells.eldritch.AbstractEldritchSpell;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

@AutoSpellConfig
public class ViolentSkreechSpell
extends AbstractEldritchSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "violent_skreech");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.UNCOMMON).setSchoolResource(SchoolRegistry.ELDRITCH_RESOURCE).setMaxLevel(8).setCooldownSeconds(25.0).build();

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.selector((String)"ui.traveloptics.direct_damage", (Object[])new Object[]{Utils.stringTruncation((double)this.getTickDamage(spellLevel, caster), (int)2)}), Component.selector((String)"ui.traveloptics.aoe_damage", (Object[])new Object[]{Utils.stringTruncation((double)this.getHalfTickDamage(spellLevel, caster), (int)2)}), Component.selector((String)"ui.traveloptics.sundering_effect_duration", (Object[])new Object[]{5}), Component.selector((String)"ui.traveloptics.chilled_effect_duration", (Object[])new Object[]{2.5}), Component.selector((String)"ui.traveloptics.lifesteal", (Object[])new Object[]{2}));
    }

    public ViolentSkreechSpell() {
        this.manaCostPerLevel = 2;
        this.baseSpellPower = 2;
        this.spellPowerPerLevel = 2;
        this.castTime = 100;
        this.baseManaCost = 10;
    }

    public CastType getCastType() {
        return CastType.CONTINUOUS;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public Optional<SoundEvent> getCastStartSound() {
        ResourceLocation soundLocation = new ResourceLocation("alexsmobs", "skreecher_call");
        return Optional.ofNullable((SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(soundLocation));
    }

    public Optional<SoundEvent> getCastFinishSound() {
        ResourceLocation soundLocation = new ResourceLocation("alexsmobs", "skreecher_clap");
        return Optional.ofNullable((SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(soundLocation));
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        LivingEntity livingTarget;
        Entity target;
        MagicManager.spawnParticles((Level)level, (ParticleOptions)((ParticleOptions)BuiltInRegistries.PARTICLE_TYPE.bindTags(new ResourceLocation("alexsmobs:skulk_boom"))), (double)entity.getX(), (double)entity.getY(), (double)entity.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
        HitResult hitResult = Utils.raycastForEntity((Level)level, (Entity)entity, (float)ViolentSkreechSpell.getRange(), (boolean)true, (float)0.15f);
        if (hitResult.getType() == HitResult.Type.ENTITY && (target = ((EntityHitResult)hitResult).getEntity()) instanceof LivingEntity && DamageSources.applyDamage((Entity)(livingTarget = (LivingEntity)target), (float)this.getTickDamage(spellLevel, entity), (DamageSource)this.getDamageSource((Entity)entity))) {
            livingTarget.getStandingEyeHeight(new MobEffectInstance((MobEffect)BuiltInRegistries.MOB_EFFECT.bindTags(new ResourceLocation("attributeslib:sundering")), 100, 0));
        }
        List nearbyEntities = level.getNearbyEntities(LivingEntity.class, entity.getBoundingBox().inflate(6.0));
        for (LivingEntity nearbyEntity : nearbyEntities) {
            if (nearbyEntity == entity || !DamageSources.applyDamage((Entity)nearbyEntity, (float)this.getHalfTickDamage(spellLevel, entity), (DamageSource)this.getAoeDamageSource((Entity)entity))) continue;
            nearbyEntity.getStandingEyeHeight(new MobEffectInstance((MobEffect)BuiltInRegistries.MOB_EFFECT.bindTags(new ResourceLocation("irons_spellbooks:chilled")), 50, 1));
        }
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker);
    }

    public SpellDamageSource getAoeDamageSource(Entity attacker) {
        return super.getDamageSource(attacker, attacker).setLifestealPercent(0.02f);
    }

    public static float getRange() {
        return 8.0f;
    }

    private float getTickDamage(int spellLevel, LivingEntity caster) {
        return this.getSpellPower(spellLevel, (Entity)caster) * 0.5f;
    }

    private float getHalfTickDamage(int spellLevel, LivingEntity caster) {
        return this.getTickDamage(spellLevel, caster) / 2.0f;
    }

    public boolean shouldAIStopCasting(int spellLevel, Mob mob, LivingEntity target) {
        return mob.getZ((Entity)target) > (double)(ViolentSkreechSpell.getRange() * ViolentSkreechSpell.getRange()) * 1.2;
    }
}

