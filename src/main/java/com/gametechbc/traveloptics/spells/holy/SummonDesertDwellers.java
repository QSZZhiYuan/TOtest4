/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModItems
 *  com.github.L_Ender.cataclysm.init.ModParticle
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
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.spells.holy;

import com.gametechbc.traveloptics.api.particle.CylinderParticleManager;
import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.api.spells.AbstractUniqueSpell;
import com.gametechbc.traveloptics.api.utils.SummonCheckHelper;
import com.gametechbc.traveloptics.config.SpellsConfig;
import com.gametechbc.traveloptics.entity.summons.SummonedKoboleton;
import com.gametechbc.traveloptics.entity.summons.SummonedWadjet;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.util.SummonTypes;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.L_Ender.cataclysm.init.ModParticle;
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
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

@AutoSpellConfig
public class SummonDesertDwellers
extends AbstractUniqueSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "summon_desert_dwellers");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.RARE).setSchoolResource(SchoolRegistry.HOLY_RESOURCE).setMaxLevel(5).setCooldownSeconds(420.0).build();

    public SummonDesertDwellers() {
        this.manaCostPerLevel = 75;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 60;
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
        return Optional.of((SoundEvent)ModSounds.REMNANT_ROAR.get());
    }

    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        if (entity instanceof Player) {
            Player player = (Player)entity;
            if (((Boolean)SpellsConfig.limitGroupSummons.get()).booleanValue() && SummonCheckHelper.hasActiveSummons(player, 128.0, SummonTypes.getGroupSummons())) {
                player.updateTutorialInventoryAction((Component)Component.translatable((String)"spell.traveloptics.summon_group.warning").withStyle(ChatFormatting.RED), true);
                return false;
            }
        }
        return true;
    }

    public void onServerCastTick(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        CylinderParticleManager.spawnParticles(level, (Entity)entity, 2, (ParticleOptions)ModParticle.SANDSTORM.get(), ParticleDirection.UPWARD, 2.0, 2.0, -1.0);
        super.onServerCastTick(level, spellLevel, entity, playerMagicData);
    }

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        int summonTime = 12000;
        int koboletonCount = (int)this.getKoboletonCount(spellLevel);
        double radius = 3.5;
        double angleIncrement = Math.PI * 2 / (double)koboletonCount;
        for (int i = 0; i < koboletonCount; ++i) {
            double angle = (double)i * angleIncrement;
            double xOffset = radius * Math.cos(angle);
            double zOffset = radius * Math.sin(angle);
            SummonedKoboleton koboleton = new SummonedKoboleton(world, entity);
            koboleton.setPos(entity.getX() + xOffset, entity.getY(), entity.getZ() + zOffset);
            koboleton.getAttributes().load(Attributes.ATTACK_DAMAGE).load((double)this.getKoboletonDamage(spellLevel, entity));
            koboleton.getAttributes().load(Attributes.register).load((double)this.getKoboletonHealth(spellLevel));
            koboleton.setHealth(koboleton.getMaxHealth());
            this.equip((Mob)koboleton);
            world.addFreshEntity((Entity)koboleton);
            koboleton.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.DESERT_DWELLER_TIMER.get(), summonTime, 0, false, false, false));
            MagicManager.spawnParticles((Level)world, (ParticleOptions)((ParticleOptions)ModParticle.SANDSTORM.get()), (double)(entity.getX() + xOffset), (double)(entity.getY() + 1.5), (double)(entity.getZ() + zOffset), (int)20, (double)0.0, (double)0.0, (double)0.0, (double)0.03, (boolean)false);
        }
        SummonedWadjet wadjet = new SummonedWadjet(world, entity);
        wadjet.setLevel(entity.position());
        wadjet.getAttributes().load(Attributes.ATTACK_DAMAGE).load((double)this.getWadjetDamage(spellLevel, entity));
        wadjet.getAttributes().load(Attributes.register).load((double)this.getWadjetHealth(spellLevel));
        wadjet.setHealth(wadjet.getMaxHealth());
        world.addFreshEntity((Entity)wadjet);
        wadjet.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.DESERT_DWELLER_TIMER.get(), summonTime, 0, false, false, false));
        MagicManager.spawnParticles((Level)world, (ParticleOptions)((ParticleOptions)ModParticle.SANDSTORM.get()), (double)entity.getX(), (double)(entity.getY() + 2.5), (double)entity.getZ(), (int)40, (double)0.0, (double)0.0, (double)0.0, (double)0.03, (boolean)false);
        int effectAmplifier = 0;
        if (entity.recreateFromPacket((MobEffect)TravelopticsEffects.DESERT_DWELLER_TIMER.get())) {
            effectAmplifier += entity.getStandingEyeHeight((MobEffect)TravelopticsEffects.DESERT_DWELLER_TIMER.get()).getAmplifier() + 1;
        }
        entity.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.DESERT_DWELLER_TIMER.get(), summonTime, effectAmplifier, false, false, true));
        CameraShakeManager.addCameraShake((CameraShakeData)new CameraShakeData(35, entity.position(), 25.0f));
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private float getKoboletonCount(int spellLevel) {
        return spellLevel;
    }

    private float getKoboletonHealth(int spellLevel) {
        return (float)spellLevel * 5.0f;
    }

    private float getKoboletonDamage(int spellLevel, LivingEntity caster) {
        float baseDamage = this.getSpellPower(spellLevel, (Entity)caster) * 0.6f;
        double summonedDamageMultiplier = 1.0;
        if (caster.getAttributes().addTransientAttributeModifiers((Attribute)AttributeRegistry.SUMMON_DAMAGE.get())) {
            summonedDamageMultiplier = caster.getStandingEyeHeight((Attribute)AttributeRegistry.SUMMON_DAMAGE.get());
        }
        return (float)((double)baseDamage * summonedDamageMultiplier);
    }

    private String getKoboletonDamageText(int spellLevel, LivingEntity caster) {
        if (caster != null) {
            float baseDamage = this.getSpellPower(spellLevel, (Entity)caster) * 0.6f;
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

    private float getWadjetHealth(int spellLevel) {
        return 50.0f + (float)spellLevel * 20.0f;
    }

    private float getWadjetDamage(int spellLevel, LivingEntity caster) {
        float baseDamage = 2.0f + this.getSpellPower(spellLevel, (Entity)caster) * 2.0f;
        double summonedDamageMultiplier = 1.0;
        if (caster.getAttributes().addTransientAttributeModifiers((Attribute)AttributeRegistry.SUMMON_DAMAGE.get())) {
            summonedDamageMultiplier = caster.getStandingEyeHeight((Attribute)AttributeRegistry.SUMMON_DAMAGE.get());
        }
        return (float)((double)baseDamage * summonedDamageMultiplier);
    }

    private String getWadjetDamageText(int spellLevel, LivingEntity caster) {
        if (caster != null) {
            float baseDamage = 2.0f + this.getSpellPower(spellLevel, (Entity)caster) * 2.0f;
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
        return List.of(Component.selector((String)"ui.traveloptics.summon_count", (Object[])new Object[]{Float.valueOf(this.getKoboletonCount(spellLevel) + 1.0f)}), Component.selector((String)"ui.traveloptics.koboleton_hp", (Object[])new Object[]{Float.valueOf(this.getKoboletonHealth(spellLevel))}), Component.selector((String)"ui.traveloptics.koboleton_damage", (Object[])new Object[]{this.getKoboletonDamageText(spellLevel, caster)}), Component.selector((String)"ui.traveloptics.wadjet_hp", (Object[])new Object[]{Float.valueOf(this.getWadjetHealth(spellLevel))}), Component.selector((String)"ui.traveloptics.wadjet_damage", (Object[])new Object[]{this.getWadjetDamageText(spellLevel, caster)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }

    private void equip(Mob mob) {
        mob.setLastDeathLocation(EquipmentSlot.MAINHAND, new ItemStack((ItemLike)ModItems.KHOPESH.get()));
        mob.maybeDisableShield(EquipmentSlot.MAINHAND, 0.0f);
    }
}

