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
 *  io.redspace.ironsspellbooks.api.spells.SpellAnimations
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.AnimationHolder
 *  io.redspace.ironsspellbooks.api.util.CameraShakeData
 *  io.redspace.ironsspellbooks.api.util.CameraShakeManager
 *  io.redspace.ironsspellbooks.registries.SoundRegistry
 *  io.redspace.ironsspellbooks.util.ParticleHelper
 *  javax.annotation.Nullable
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LightningBolt
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.ServerLevelAccessor
 */
package com.gametechbc.traveloptics.spells.blood;

import com.gametechbc.traveloptics.api.particle.CylinderParticleManager;
import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.api.spells.AbstractUniqueSpell;
import com.gametechbc.traveloptics.entity.mobs.EnragedDeadKingBoss;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellAnimations;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

@AutoSpellConfig
public class CallForthTheDeadKingSpell
extends AbstractUniqueSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "call_forth_the_dead_king");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.LEGENDARY).setSchoolResource(SchoolRegistry.BLOOD_RESOURCE).setMaxLevel(1).setCooldownSeconds(3600.0).build();

    public CallForthTheDeadKingSpell() {
        this.manaCostPerLevel = 0;
        this.baseSpellPower = 0;
        this.spellPowerPerLevel = 0;
        this.castTime = 300;
        this.baseManaCost = 25000;
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
        return Optional.of((SoundEvent)SoundRegistry.DEAD_KING_MUSIC_INTRO.get());
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundEvents.LIGHTNING_BOLT_IMPACT);
    }

    public boolean canBeInterrupted(@Nullable Player player) {
        return false;
    }

    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {
        return this.getCastTime(spellLevel);
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable((String)"ui.traveloptics.call_forth_the_dead_king_guide"), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }

    public void onServerCastTick(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        CylinderParticleManager.spawnParticles(level, (Entity)entity, 6, ParticleHelper.BLOOD, ParticleDirection.UPWARD, 3.0, 2.0, 2.0);
        super.onServerCastTick(level, spellLevel, entity, playerMagicData);
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        EnragedDeadKingBoss enragedDeadKing;
        if (!level.isClientSide && (enragedDeadKing = (EnragedDeadKingBoss)((EntityType)TravelopticsEntities.ENRAGED_DEAD_KING.get()).tryCast(level)) != null) {
            double angle = Math.toRadians(-entity.getYRot());
            double xOffset = 2.0 * Math.cos(angle - 1.5707963267948966);
            double zOffset = 2.0 * Math.sin(angle - 1.5707963267948966);
            double spawnX = entity.getX() + xOffset;
            double spawnY = entity.getY();
            double spawnZ = entity.getZ() + zOffset;
            LightningBolt lightning = (LightningBolt)EntityType.LIGHTNING_BOLT.tryCast(level);
            if (lightning != null) {
                lightning.getRandomX(spawnX, spawnY, spawnZ);
                lightning.readAdditionalSaveData(true);
                lightning.setDamage(0.0f);
                level.addFreshEntity((Entity)lightning);
            }
            enragedDeadKing.moveTo(spawnX, spawnY, spawnZ, entity.getYRot(), entity.getXRot());
            enragedDeadKing.finalizeSpawn((ServerLevelAccessor)((ServerLevel)level), level.getCurrentDifficultyAt(enragedDeadKing.getOnPos()), MobSpawnType.MOB_SUMMONED, null, null);
            level.addFreshEntity((Entity)enragedDeadKing);
        }
        CameraShakeManager.addCameraShake((CameraShakeData)new CameraShakeData(35, entity.position(), 18.0f));
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.ANIMATION_LONG_CAST;
    }

    public boolean stopSoundOnCancel() {
        return true;
    }
}

