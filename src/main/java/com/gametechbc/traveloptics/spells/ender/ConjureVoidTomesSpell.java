/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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
 *  javax.annotation.Nullable
 *  net.minecraft.core.particles.ParticleOptions
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
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.spells.ender;

import com.gametechbc.traveloptics.api.particle.OrbitingParticleManager;
import com.gametechbc.traveloptics.api.spells.AbstractUniqueSpell;
import com.gametechbc.traveloptics.entity.summons.SummonedVoidTome;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
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
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleOptions;
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
import net.minecraft.world.level.Level;

@AutoSpellConfig
public class ConjureVoidTomesSpell
extends AbstractUniqueSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "conjure_void_tomes");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.UNCOMMON).setSchoolResource(SchoolRegistry.ENDER_RESOURCE).setMaxLevel(5).setCooldownSeconds(150.0).build();

    public ConjureVoidTomesSpell() {
        this.manaCostPerLevel = 30;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 50;
        this.baseManaCost = 30;
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
        return Optional.of((SoundEvent)TravelopticsSounds.FLYING_BOOK_HURT.get());
    }

    public void onServerCastTick(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        OrbitingParticleManager.spawnOrbitingParticle((Entity)entity, TravelopticsParticleHelper.LIGHT_PURPLE_GLOWING_ENCHANT, 8.0f, 1.5, 1.5, OrbitingParticleManager.OrbitDirection.CLOCKWISE, false, true);
        super.onServerCastTick(level, spellLevel, entity, playerMagicData);
    }

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        int summonTime = 12000;
        int tomeCount = this.getTomeCount();
        double radius = 5.0;
        double angleIncrement = Math.PI * 2 / (double)tomeCount;
        for (int i = 0; i < tomeCount; ++i) {
            double angle = (double)i * angleIncrement;
            double xOffset = radius * Math.cos(angle);
            double zOffset = radius * Math.sin(angle);
            SummonedVoidTome voidTome = new SummonedVoidTome(world, entity);
            voidTome.setPos(entity.getX() + xOffset, entity.getY() + 10.0, entity.getZ() + zOffset);
            voidTome.getAttributes().load((Attribute)AttributeRegistry.SPELL_POWER.get()).load((double)this.getTomeDamage(spellLevel, entity));
            voidTome.getAttributes().load(Attributes.register).load((double)this.getTomeHealth(spellLevel));
            voidTome.setHealth(voidTome.getMaxHealth());
            world.addFreshEntity((Entity)voidTome);
            voidTome.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.VOID_TOME_TIMER.get(), summonTime, 0, false, false, false));
            MagicManager.spawnParticles((Level)world, (ParticleOptions)TravelopticsParticleHelper.LIGHT_PURPLE_GLOWING_ENCHANT, (double)(entity.getX() + xOffset), (double)(entity.getY() + 10.0), (double)(entity.getZ() + zOffset), (int)30, (double)0.0, (double)0.0, (double)0.0, (double)0.03, (boolean)false);
        }
        int effectAmplifier = 0;
        if (entity.recreateFromPacket((MobEffect)TravelopticsEffects.VOID_TOME_TIMER.get())) {
            effectAmplifier += entity.getStandingEyeHeight((MobEffect)TravelopticsEffects.VOID_TOME_TIMER.get()).getAmplifier() + 1;
        }
        entity.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.VOID_TOME_TIMER.get(), summonTime, effectAmplifier, false, false, true));
        CameraShakeManager.addCameraShake((CameraShakeData)new CameraShakeData(15, entity.position(), 25.0f));
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private int getTomeCount() {
        return 3;
    }

    private float getTomeHealth(int spellLevel) {
        return 15 + spellLevel * 5;
    }

    private float getTomeDamage(int spellLevel, LivingEntity caster) {
        float baseSpellPower = this.getSpellPower(spellLevel, (Entity)caster) * 0.2f;
        double summonDamageMultiplier = caster.getAttributes().addTransientAttributeModifiers((Attribute)AttributeRegistry.SUMMON_DAMAGE.get()) ? caster.getStandingEyeHeight((Attribute)AttributeRegistry.SUMMON_DAMAGE.get()) : 1.0;
        return (float)((double)baseSpellPower * summonDamageMultiplier);
    }

    private String getTomeDamageText(int spellLevel, @Nullable LivingEntity caster) {
        float spellPower = 0.0f;
        double summonDamageMultiplier = 1.0;
        if (caster != null) {
            spellPower += this.getSpellPower(spellLevel, (Entity)caster) * 0.2f;
            if (caster.getAttributes().addTransientAttributeModifiers((Attribute)AttributeRegistry.SUMMON_DAMAGE.get())) {
                summonDamageMultiplier = caster.getStandingEyeHeight((Attribute)AttributeRegistry.SUMMON_DAMAGE.get());
            }
        }
        float totalScaling = spellPower * (float)summonDamageMultiplier;
        String percent = Utils.stringTruncation((double)(totalScaling * 100.0f), (int)1);
        return percent + "%";
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.selector((String)"ui.traveloptics.summon_count", (Object[])new Object[]{this.getTomeCount()}), Component.selector((String)"ui.traveloptics.hp", (Object[])new Object[]{Float.valueOf(this.getTomeHealth(spellLevel))}), Component.selector((String)"ui.traveloptics.spell_power", (Object[])new Object[]{this.getTomeDamageText(spellLevel, caster)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }
}

