/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity
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
 *  io.redspace.ironsspellbooks.damage.SpellDamageSource
 *  io.redspace.ironsspellbooks.util.ParticleHelper
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.spells.fire;

import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.api.particle.SphereParticleManager;
import com.gametechbc.traveloptics.api.spells.AbstractUniqueSpell;
import com.gametechbc.traveloptics.entity.projectiles.gyro_slash.GyroSlashProjectile;
import com.gametechbc.traveloptics.entity.projectiles.gyro_slash.GyroSlashVisual;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.spells.TravelopticsSpellAnimations;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
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
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

@AutoSpellConfig
public class GyroSlashSpell
extends AbstractUniqueSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "gyro_slash");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.LEGENDARY).setSchoolResource(SchoolRegistry.FIRE_RESOURCE).setMaxLevel(1).setCooldownSeconds(30.0).build();

    public GyroSlashSpell() {
        this.manaCostPerLevel = 0;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 25;
        this.baseManaCost = 180;
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

    public AnimationHolder getCastStartAnimation() {
        return TravelopticsSpellAnimations.GYRO_SLASH_CAST;
    }

    public AnimationHolder getCastFinishAnimation() {
        return TravelopticsSpellAnimations.GYRO_SLASH_FINISH;
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of((SoundEvent)TravelopticsSounds.DEVASTATOR_BLADE_TRANSFORM.get());
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of((SoundEvent)ModSounds.SWORD_STOMP.get());
    }

    public boolean canBeInterrupted(@Nullable Player player) {
        return false;
    }

    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {
        return this.getCastTime(spellLevel);
    }

    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        Player player;
        MobEffectInstance overloadedEffect;
        List<Item> allowedWeapons = List.of((Item)TravelopticsItems.INFERNAL_DEVASTATOR.get(), (Item)TravelopticsItems.INFERNAL_DEVASTATOR_LEVEL_ONE.get(), (Item)TravelopticsItems.INFERNAL_DEVASTATOR_LEVEL_TWO.get(), (Item)TravelopticsItems.INFERNAL_DEVASTATOR_LEVEL_THREE.get());
        if (entity instanceof Player && (overloadedEffect = (player = (Player)entity).getStandingEyeHeight((MobEffect)TravelopticsEffects.OVERLOADED_EFFECT.get())) != null) {
            if (!level.isClientSide) {
                player.updateTutorialInventoryAction((Component)Component.translatable((String)"effect.traveloptics.overloaded.warning").withStyle(ChatFormatting.RED), true);
            }
            return false;
        }
        if (allowedWeapons.contains(entity.getMainHandItem().setRepairCost())) {
            return true;
        }
        if (entity instanceof Player) {
            player = (Player)entity;
            if (!level.isClientSide) {
                player.updateTutorialInventoryAction((Component)Component.translatable((String)"spell.traveloptics.gyro_slash.warning").withStyle(ChatFormatting.RED), true);
            }
        }
        return false;
    }

    public void onServerCastTick(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        SphereParticleManager.spawnParticles(level, (Entity)entity, 2, (ParticleOptions)ParticleTypes.FLAME, ParticleDirection.INWARD, 3.0);
    }

    public void onServerPreCast(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        entity.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.OVERLOADED_EFFECT.get(), 55, 0, false, false, false));
        super.onServerPreCast(level, spellLevel, entity, playerMagicData);
    }

    public void onCast(Level level, int spellLevel, LivingEntity caster, CastSource castSource, MagicData playerMagicData) {
        float radius = 3.5f;
        float distance = 2.5f;
        ScreenShake_Entity.ScreenShake((Level)level, (Vec3)caster.position(), (float)5.0f, (float)0.025f, (int)5, (int)10);
        Vec3 forward = caster.getForward();
        Vec3 hitLocation = caster.position().y(0.0, (double)(caster.getBbHeight() * 0.3f), 0.0).reverse(forward.x((double)distance));
        List entities = level.getEntities((Entity)caster, AABB.ofSize((Vec3)hitLocation, (double)(radius * 2.0f), (double)radius, (double)(radius * 2.0f)));
        SpellDamageSource damageSource = this.getDamageSource((Entity)caster);
        for (Entity targetEntity : entities) {
            Vec3 offsetVector;
            if (!(targetEntity instanceof LivingEntity) || !targetEntity.isAlive() || !caster.isPickable() || !(targetEntity.position().multiply(caster.getEyePosition()).y(forward) >= 0.0) || !(caster.getZ(targetEntity) < (double)(radius * radius)) || !Utils.hasLineOfSight((Level)level, (Vec3)caster.getEyePosition(), (Vec3)targetEntity.getBoundingBox().getCenter(), (boolean)true) || !((offsetVector = targetEntity.getBoundingBox().getCenter().multiply(caster.getEyePosition())).y(forward) >= 0.0) || !DamageSources.applyDamage((Entity)targetEntity, (float)this.getDamage(spellLevel, caster), (DamageSource)damageSource)) continue;
            MagicManager.spawnParticles((Level)level, (ParticleOptions)ParticleHelper.EMBERS, (double)targetEntity.getX(), (double)(targetEntity.getY() + (double)(targetEntity.getBbHeight() * 0.5f)), (double)targetEntity.getZ(), (int)50, (double)(targetEntity.getBbWidth() * 0.5f), (double)(targetEntity.getBbHeight() * 0.5f), (double)(targetEntity.getBbWidth() * 0.5f), (double)0.03, (boolean)false);
            EnchantmentHelper.selectEnchantment((LivingEntity)caster, (Entity)targetEntity);
        }
        GyroSlashVisual gyroSlashVisual = new GyroSlashVisual(level, false);
        gyroSlashVisual.getRandomX(hitLocation);
        gyroSlashVisual.setYRot(caster.getYRot());
        gyroSlashVisual.setXRot(caster.getXRot());
        level.addFreshEntity((Entity)gyroSlashVisual);
        List<Item> canCreateProjectile = List.of((Item)TravelopticsItems.INFERNAL_DEVASTATOR_LEVEL_TWO.get(), (Item)TravelopticsItems.INFERNAL_DEVASTATOR_LEVEL_THREE.get());
        if (canCreateProjectile.contains(caster.getMainHandItem().setRepairCost())) {
            GyroSlashProjectile gyroSlashProjectile = new GyroSlashProjectile(level, caster);
            gyroSlashProjectile.setLevel(caster.getEyePosition());
            gyroSlashProjectile.shoot(caster.getLookAngle());
            gyroSlashProjectile.setDamage(this.getDamage(spellLevel, caster));
            gyroSlashProjectile.setEffectAmplifier(this.getFlameJetDamage(spellLevel, caster));
            level.addFreshEntity((Entity)gyroSlashProjectile);
        }
        super.onCast(level, spellLevel, caster, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return 20.0f + this.getSpellPower(spellLevel, (Entity)entity) * 4.0f;
    }

    private int getFlameJetDamage(int spellLevel, LivingEntity entity) {
        return (int)(10.0f + this.getSpellPower(spellLevel, (Entity)entity) * 2.0f);
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.selector((String)"ui.traveloptics.damage", (Object[])new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), (int)2)}), Component.selector((String)"ui.traveloptics.flame_jet_damage", (Object[])new Object[]{Utils.stringTruncation((double)this.getFlameJetDamage(spellLevel, caster), (int)2)}), Component.selector((String)"ui.traveloptics.flame_jet.requirement", (Object[])new Object[]{Utils.stringTruncation((double)this.getFlameJetDamage(spellLevel, caster), (int)2)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }
}

