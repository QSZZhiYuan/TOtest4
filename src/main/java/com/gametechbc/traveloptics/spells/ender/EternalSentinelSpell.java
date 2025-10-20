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
 *  io.redspace.ironsspellbooks.util.ParticleHelper
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.ServerLevelAccessor
 */
package com.gametechbc.traveloptics.spells.ender;

import com.gametechbc.traveloptics.api.particle.CylinderParticleManager;
import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.api.spells.AbstractUniqueSpell;
import com.gametechbc.traveloptics.api.utils.SummonCheckHelper;
import com.gametechbc.traveloptics.config.SpellsConfig;
import com.gametechbc.traveloptics.entity.summons.SummonedEnderGolem;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.util.SummonTypes;
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
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

@AutoSpellConfig
public class EternalSentinelSpell
extends AbstractUniqueSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "eternal_sentinel");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.EPIC).setSchoolResource(SchoolRegistry.ENDER_RESOURCE).setMaxLevel(3).setCooldownSeconds(330.0).build();

    public EternalSentinelSpell() {
        this.manaCostPerLevel = 100;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 80;
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
        return Optional.of(SoundEvents.END_PORTAL_SPAWN);
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
        CylinderParticleManager.spawnParticles(level, (Entity)entity, 3, ParticleHelper.UNSTABLE_ENDER, ParticleDirection.UPWARD, 2.0, 2.0, -1.0);
        super.onServerCastTick(level, spellLevel, entity, playerMagicData);
    }

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        int summonTime = 12000;
        double offset = 3.0;
        double angle = Math.toRadians(-entity.getYRot());
        double xOffset = offset * Math.cos(angle);
        double zOffset = offset * Math.sin(angle);
        SummonedEnderGolem enderGolem = new SummonedEnderGolem(world, entity);
        enderGolem.setPos(entity.getX() + xOffset, entity.getY(), entity.getZ() + zOffset);
        enderGolem.getAttributes().load(Attributes.ATTACK_DAMAGE).load((double)this.getGolemDamage(spellLevel, entity));
        enderGolem.getAttributes().load(Attributes.register).load((double)this.getGolemHealth(spellLevel));
        enderGolem.setHealth(enderGolem.getMaxHealth());
        enderGolem.finalizeSpawn((ServerLevelAccessor)((ServerLevel)world), world.getCurrentDifficultyAt(enderGolem.getOnPos()), MobSpawnType.MOB_SUMMONED, null, null);
        world.addFreshEntity((Entity)enderGolem);
        enderGolem.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.ENDER_GOLEM_TIMER.get(), summonTime, 0, false, false, false));
        MagicManager.spawnParticles((Level)world, (ParticleOptions)ParticleTypes.REVERSE_PORTAL, (double)(entity.getX() + xOffset), (double)(entity.getY() + 2.0), (double)(entity.getZ() + zOffset), (int)50, (double)0.0, (double)0.0, (double)0.0, (double)0.03, (boolean)false);
        int effectAmplifier = 0;
        if (entity.recreateFromPacket((MobEffect)TravelopticsEffects.ENDER_GOLEM_TIMER.get())) {
            effectAmplifier += entity.getStandingEyeHeight((MobEffect)TravelopticsEffects.ENDER_GOLEM_TIMER.get()).getAmplifier() + 1;
        }
        entity.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.ENDER_GOLEM_TIMER.get(), summonTime, effectAmplifier, false, false, true));
        CameraShakeManager.addCameraShake((CameraShakeData)new CameraShakeData(30, entity.position(), 25.0f));
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private float getGolemHealth(int spellLevel) {
        return 45.0f + (float)spellLevel * 45.0f;
    }

    private float getGolemDamage(int spellLevel, LivingEntity caster) {
        float baseDamage = 8.0f + this.getSpellPower(spellLevel, (Entity)caster) * 2.0f;
        double summonedDamageMultiplier = 1.0;
        if (caster.getAttributes().addTransientAttributeModifiers((Attribute)AttributeRegistry.SUMMON_DAMAGE.get())) {
            summonedDamageMultiplier = caster.getStandingEyeHeight((Attribute)AttributeRegistry.SUMMON_DAMAGE.get());
        }
        return (float)((double)baseDamage * summonedDamageMultiplier);
    }

    private String getGolemDamageText(int spellLevel, LivingEntity caster) {
        if (caster != null) {
            float baseDamage = 8.0f + this.getSpellPower(spellLevel, (Entity)caster) * 2.0f;
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
        return List.of(Component.selector((String)"ui.traveloptics.hp", (Object[])new Object[]{Float.valueOf(this.getGolemHealth(spellLevel))}), Component.selector((String)"ui.traveloptics.damage", (Object[])new Object[]{this.getGolemDamageText(spellLevel, caster)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }
}

