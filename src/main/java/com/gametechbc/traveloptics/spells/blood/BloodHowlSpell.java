/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.projectile.Wither_Howitzer_Entity
 *  com.github.L_Ender.cataclysm.init.ModSounds
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
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.capabilities.magic.RecastInstance
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  org.jetbrains.annotations.Nullable
 */
package com.gametechbc.traveloptics.spells.blood;

import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedWitherHowitzerEntity;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.spells.TravelopticsSpellAnimations;
import com.github.L_Ender.cataclysm.entity.projectile.Wither_Howitzer_Entity;
import com.github.L_Ender.cataclysm.init.ModSounds;
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
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.RecastInstance;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

@AutoSpellConfig
public class BloodHowlSpell
extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "blood_howl");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.UNCOMMON).setSchoolResource(SchoolRegistry.BLOOD_RESOURCE).setMaxLevel(5).setCooldownSeconds(20.0).build();

    public BloodHowlSpell() {
        this.manaCostPerLevel = 20;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 70;
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

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.empty();
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of((SoundEvent)ModSounds.HARBINGER_HURT.get());
    }

    public AnimationHolder getCastStartAnimation() {
        return TravelopticsSpellAnimations.SHOOT;
    }

    public int getRecastCount(int spellLevel, @Nullable LivingEntity entity) {
        return 1 + spellLevel;
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.selector((String)"ui.traveloptics.aoe_damage", (Object[])new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), (int)2)}), Component.selector((String)"ui.traveloptics.shot_count", (Object[])new Object[]{this.getRecastCount(spellLevel, caster)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (!playerMagicData.getPlayerRecasts().hasRecastForSpell(this.getSpellId())) {
            playerMagicData.getPlayerRecasts().addRecast(new RecastInstance(this.getSpellId(), spellLevel, this.getRecastCount(spellLevel, entity), 80, castSource, null), playerMagicData);
        }
        this.spawnWitherHowitzerProjectile(level, entity, spellLevel);
        Vec3 lookVec = entity.getLookAngle();
        double offsetDistance = 1.0;
        Vec3 handPosition = new Vec3(entity.getX(), entity.getY() + (double)entity.getEyeHeight() - 0.5, entity.getZ());
        Vec3 offsetPosition = handPosition.reverse(lookVec.x(offsetDistance));
        ParticleOptions particleType = (ParticleOptions)ParticleTypes.LAVA;
        MagicManager.spawnParticles((Level)level, (ParticleOptions)particleType, (double)offsetPosition.x(), (double)offsetPosition.y(), (double)offsetPosition.z(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        return 4.0f + this.getSpellPower(spellLevel, (Entity)caster) * 2.5f;
    }

    private void spawnWitherHowitzerProjectile(Level level, LivingEntity entity, int spellLevel) {
        float damage = this.getDamage(spellLevel, entity);
        Vec3 lookVec = entity.getLookAngle();
        Vec3 spawnPos = entity.getEyePosition().y(lookVec.z * 2.0, lookVec.multiply * 2.0, lookVec.reverse * 2.0);
        EntityType entityType = (EntityType)TravelopticsEntities.EXTENDED_WITHER_HOWITZER.get();
        ExtendedWitherHowitzerEntity projectile = new ExtendedWitherHowitzerEntity((EntityType<? extends Wither_Howitzer_Entity>)entityType, level, entity, damage, damage / 2.0f);
        projectile.addAdditionalSaveData((Entity)entity);
        projectile.setPos(spawnPos.z, spawnPos.multiply, spawnPos.reverse);
        projectile.shoot(lookVec.z, lookVec.multiply, lookVec.reverse, 1.8f, 1.0f);
        level.addFreshEntity((Entity)projectile);
    }
}

