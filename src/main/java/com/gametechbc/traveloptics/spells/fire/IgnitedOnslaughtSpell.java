/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModSounds
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.CameraShakeData
 *  io.redspace.ironsspellbooks.api.util.CameraShakeManager
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.util.ParticleHelper
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
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
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.spells.fire;

import com.gametechbc.traveloptics.api.particle.CylinderParticleManager;
import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.api.spells.AbstractUniqueSpell;
import com.gametechbc.traveloptics.api.utils.SummonCheckHelper;
import com.gametechbc.traveloptics.config.SpellsConfig;
import com.gametechbc.traveloptics.entity.summons.SummonedIgnitedBerserker;
import com.gametechbc.traveloptics.entity.summons.SummonedIgnitedRevenant;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.util.SummonTypes;
import com.github.L_Ender.cataclysm.init.ModSounds;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
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
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

@AutoSpellConfig
public class IgnitedOnslaughtSpell
extends AbstractUniqueSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "ignited_onslaught");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.RARE).setSchoolResource(SchoolRegistry.FIRE_RESOURCE).setMaxLevel(5).setCooldownSeconds(360.0).build();

    public IgnitedOnslaughtSpell() {
        this.manaCostPerLevel = 75;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 60;
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

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of((SoundEvent)ModSounds.REVENANT_IDLE.get());
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of((SoundEvent)ModSounds.REVENANT_HURT.get());
    }

    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        if (entity instanceof Player) {
            Player player = (Player)entity;
            if (((Boolean)SpellsConfig.limitMinibossSummons.get()).booleanValue() && SummonCheckHelper.hasActiveSummons(player, 128.0, SummonTypes.getMinibossSummons())) {
                player.updateTutorialInventoryAction((Component)Component.translatable((String)"spell.traveloptics.summon_miniboss.warning").withStyle(ChatFormatting.RED), true);
                return false;
            }
        }
        return true;
    }

    public void onServerCastTick(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        CylinderParticleManager.spawnParticles(level, (Entity)entity, 2, ParticleHelper.FIRE, ParticleDirection.UPWARD, 2.0, 2.0, -1.0);
        CylinderParticleManager.spawnParticles(level, (Entity)entity, 7, (ParticleOptions)ParticleTypes.SMOKE, ParticleDirection.UPWARD, 2.0, 2.0, -1.0);
        super.onServerCastTick(level, spellLevel, entity, playerMagicData);
    }

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        int summonTime = 12000;
        double radius = 4.5;
        double leftAngle = 1.5707963267948966;
        double rightAngle = -1.5707963267948966;
        double berserkerXOffset = radius * Math.cos(leftAngle);
        double berserkerZOffset = radius * Math.sin(leftAngle);
        SummonedIgnitedBerserker ignitedBerserker = new SummonedIgnitedBerserker(world, entity);
        ignitedBerserker.setPos(entity.getX() + berserkerXOffset, entity.getY(), entity.getZ() + berserkerZOffset);
        ignitedBerserker.getAttributes().load(Attributes.ATTACK_DAMAGE).load((double)this.getBerserkerDamage(spellLevel, entity));
        ignitedBerserker.getAttributes().load(Attributes.register).load((double)this.getBerserkerHealth(spellLevel));
        ignitedBerserker.setHealth(ignitedBerserker.getMaxHealth());
        world.addFreshEntity((Entity)ignitedBerserker);
        ignitedBerserker.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.IGNITED_ONSLAUGHT_TIMER.get(), summonTime, 0, false, false, false));
        MagicManager.spawnParticles((Level)world, (ParticleOptions)ParticleHelper.FIRE, (double)(entity.getX() + berserkerXOffset), (double)(entity.getY() + 2.0), (double)(entity.getZ() + berserkerZOffset), (int)20, (double)0.0, (double)0.0, (double)0.0, (double)0.03, (boolean)false);
        MagicManager.spawnParticles((Level)world, (ParticleOptions)ParticleTypes.SMOKE, (double)(entity.getX() + berserkerXOffset), (double)(entity.getY() + 2.0), (double)(entity.getZ() + berserkerZOffset), (int)45, (double)0.0, (double)0.0, (double)0.0, (double)0.03, (boolean)false);
        double revenantXOffset = radius * Math.cos(rightAngle);
        double revenantZOffset = radius * Math.sin(rightAngle);
        SummonedIgnitedRevenant ignitedRevenant = new SummonedIgnitedRevenant(world, entity);
        ignitedRevenant.setPos(entity.getX() + revenantXOffset, entity.getY(), entity.getZ() + revenantZOffset);
        ignitedRevenant.getAttributes().load(Attributes.ATTACK_DAMAGE).load((double)this.getRevenantDamage(spellLevel, entity));
        ignitedRevenant.getAttributes().load(Attributes.register).load((double)this.getRevenantHealth(spellLevel));
        ignitedRevenant.setHealth(ignitedRevenant.getMaxHealth());
        world.addFreshEntity((Entity)ignitedRevenant);
        ignitedRevenant.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.IGNITED_ONSLAUGHT_TIMER.get(), summonTime, 0, false, false, false));
        MagicManager.spawnParticles((Level)world, (ParticleOptions)ParticleHelper.FIRE, (double)(entity.getX() + revenantXOffset), (double)(entity.getY() + 2.0), (double)(entity.getZ() + revenantZOffset), (int)20, (double)0.0, (double)0.0, (double)0.0, (double)0.03, (boolean)false);
        MagicManager.spawnParticles((Level)world, (ParticleOptions)ParticleTypes.SMOKE, (double)(entity.getX() + revenantXOffset), (double)(entity.getY() + 2.0), (double)(entity.getZ() + revenantZOffset), (int)45, (double)0.0, (double)0.0, (double)0.0, (double)0.03, (boolean)false);
        int effectAmplifier = 0;
        if (entity.recreateFromPacket((MobEffect)TravelopticsEffects.IGNITED_ONSLAUGHT_TIMER.get())) {
            effectAmplifier += entity.getStandingEyeHeight((MobEffect)TravelopticsEffects.IGNITED_ONSLAUGHT_TIMER.get()).getAmplifier() + 1;
        }
        entity.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.IGNITED_ONSLAUGHT_TIMER.get(), summonTime, effectAmplifier, false, false, true));
        CameraShakeManager.addCameraShake((CameraShakeData)new CameraShakeData(30, entity.position(), 25.0f));
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private float getBerserkerHealth(int spellLevel) {
        return 25.0f + (float)spellLevel * 8.0f;
    }

    private float getBerserkerDamage(int spellLevel, LivingEntity caster) {
        float baseDamage = 2.0f + this.getSpellPower(spellLevel, (Entity)caster) * 1.2f;
        double summonedDamageMultiplier = 1.0;
        if (caster.getAttributes().addTransientAttributeModifiers((Attribute)AttributeRegistry.SUMMON_DAMAGE.get())) {
            summonedDamageMultiplier = caster.getStandingEyeHeight((Attribute)AttributeRegistry.SUMMON_DAMAGE.get());
        }
        return (float)((double)baseDamage * summonedDamageMultiplier);
    }

    private String getBerserkerDamageText(int spellLevel, LivingEntity caster) {
        if (caster != null) {
            float baseDamage = 2.0f + this.getSpellPower(spellLevel, (Entity)caster) * 1.2f;
            double summonedDamageMultiplier = caster.getAttributes().addTransientAttributeModifiers((Attribute)AttributeRegistry.SUMMON_DAMAGE.get()) ? caster.getStandingEyeHeight((Attribute)AttributeRegistry.SUMMON_DAMAGE.get()) : 1.0;
            float finalDamage = (float)((double)baseDamage * summonedDamageMultiplier);
            String baseDamageText = Utils.stringTruncation((double)baseDamage, (int)1);
            String finalDamageText = Utils.stringTruncation((double)finalDamage, (int)1);
            if (summonedDamageMultiplier > 1.0) {
                return String.format("%s -> %s", baseDamageText, finalDamageText);
            }
            return baseDamageText;
        }
        return "" + this.getSpellPower(spellLevel, (Entity)caster);
    }

    private float getRevenantHealth(int spellLevel) {
        return 30.0f + (float)spellLevel * 10.0f;
    }

    private float getRevenantDamage(int spellLevel, LivingEntity caster) {
        float baseDamage = 2.0f + this.getSpellPower(spellLevel, (Entity)caster);
        double summonedDamageMultiplier = 1.0;
        if (caster.getAttributes().addTransientAttributeModifiers((Attribute)AttributeRegistry.SUMMON_DAMAGE.get())) {
            summonedDamageMultiplier = caster.getStandingEyeHeight((Attribute)AttributeRegistry.SUMMON_DAMAGE.get());
        }
        return (float)((double)baseDamage * summonedDamageMultiplier);
    }

    private String getRevenantDamageText(int spellLevel, LivingEntity caster) {
        if (caster != null) {
            float baseDamage = 2.0f + this.getSpellPower(spellLevel, (Entity)caster);
            double summonedDamageMultiplier = caster.getAttributes().addTransientAttributeModifiers((Attribute)AttributeRegistry.SUMMON_DAMAGE.get()) ? caster.getStandingEyeHeight((Attribute)AttributeRegistry.SUMMON_DAMAGE.get()) : 1.0;
            float finalDamage = (float)((double)baseDamage * summonedDamageMultiplier);
            String baseDamageText = Utils.stringTruncation((double)baseDamage, (int)1);
            String finalDamageText = Utils.stringTruncation((double)finalDamage, (int)1);
            if (summonedDamageMultiplier > 1.0) {
                return String.format("%s -> %s", baseDamageText, finalDamageText);
            }
            return baseDamageText;
        }
        return "" + this.getSpellPower(spellLevel, (Entity)caster);
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.selector((String)"ui.traveloptics.summon_count", (Object[])new Object[]{2}), Component.selector((String)"ui.traveloptics.berserker_hp", (Object[])new Object[]{Float.valueOf(this.getBerserkerHealth(spellLevel))}), Component.selector((String)"ui.traveloptics.berserker_damage", (Object[])new Object[]{this.getBerserkerDamageText(spellLevel, caster)}), Component.selector((String)"ui.traveloptics.revenant_hp", (Object[])new Object[]{Float.valueOf(this.getRevenantHealth(spellLevel))}), Component.selector((String)"ui.traveloptics.revenant_damage", (Object[])new Object[]{this.getRevenantDamageText(spellLevel, caster)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }
}

