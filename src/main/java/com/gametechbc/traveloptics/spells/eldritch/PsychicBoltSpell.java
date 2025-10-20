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
 *  io.redspace.ironsspellbooks.api.spells.ICastData
 *  io.redspace.ironsspellbooks.api.spells.ICastDataSerializable
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.capabilities.magic.MultiTargetEntityCastData
 *  io.redspace.ironsspellbooks.capabilities.magic.PlayerRecasts
 *  io.redspace.ironsspellbooks.capabilities.magic.RecastInstance
 *  io.redspace.ironsspellbooks.capabilities.magic.RecastResult
 *  io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData
 *  io.redspace.ironsspellbooks.spells.eldritch.AbstractEldritchSpell
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  org.jetbrains.annotations.Nullable
 */
package com.gametechbc.traveloptics.spells.eldritch;

import com.gametechbc.traveloptics.entity.projectiles.psychic_bolt.PsychicBoltProjectile;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.ICastData;
import io.redspace.ironsspellbooks.api.spells.ICastDataSerializable;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MultiTargetEntityCastData;
import io.redspace.ironsspellbooks.capabilities.magic.PlayerRecasts;
import io.redspace.ironsspellbooks.capabilities.magic.RecastInstance;
import io.redspace.ironsspellbooks.capabilities.magic.RecastResult;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.spells.eldritch.AbstractEldritchSpell;
import java.util.List;
import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

@AutoSpellConfig
public class PsychicBoltSpell
extends AbstractEldritchSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "psychic_bolt");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.EPIC).setSchoolResource(SchoolRegistry.ELDRITCH_RESOURCE).setMaxLevel(3).setCooldownSeconds(40.0).build();

    public PsychicBoltSpell() {
        this.manaCostPerLevel = 20;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 50;
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
        return Optional.of(SoundEvents.WARDEN_HEARTBEAT);
    }

    public int getRecastCount(int spellLevel, @Nullable LivingEntity entity) {
        return 5;
    }

    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        return Utils.preCastTargetHelper((Level)level, (LivingEntity)entity, (MagicData)playerMagicData, (AbstractSpell)this, (int)64, (float)0.15f);
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        ICastData iCastData = playerMagicData.getAdditionalCastData();
        if (iCastData instanceof TargetEntityCastData) {
            TargetEntityCastData targetEntityCastData = (TargetEntityCastData)iCastData;
            PlayerRecasts recasts = playerMagicData.getPlayerRecasts();
            if (!recasts.hasRecastForSpell(this.getSpellId())) {
                recasts.addRecast(new RecastInstance(this.getSpellId(), spellLevel, this.getRecastCount(spellLevel, entity), 80, castSource, (ICastDataSerializable)new MultiTargetEntityCastData(new LivingEntity[]{targetEntityCastData.getTarget((ServerLevel)level)})), playerMagicData);
            } else {
                ICastDataSerializable iCastDataSerializable;
                RecastInstance instance = recasts.getRecastInstance(this.getSpellId());
                if (instance != null && (iCastDataSerializable = instance.getCastData()) instanceof MultiTargetEntityCastData) {
                    MultiTargetEntityCastData targetingData = (MultiTargetEntityCastData)iCastDataSerializable;
                    targetingData.addTarget(targetEntityCastData.getTargetUUID());
                }
            }
        }
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public void onRecastFinished(ServerPlayer serverPlayer, RecastInstance recastInstance, RecastResult recastResult, ICastDataSerializable castDataSerializable) {
        super.onRecastFinished(serverPlayer, recastInstance, recastResult, castDataSerializable);
        Level level = serverPlayer.level();
        Vec3 origin = serverPlayer.getEyePosition().reverse(serverPlayer.getForward().multiply().x((double)0.2f));
        ScreenShake_Entity.ScreenShake((Level)level, (Vec3)serverPlayer.position(), (float)4.0f, (float)0.02f, (int)5, (int)10);
        level.getChunk(null, origin.z, origin.multiply, origin.reverse, (SoundEvent)ACSoundRegistry.FORSAKEN_SCREECH.get(), SoundSource.PLAYERS, 1.5f, 1.0f);
        if (castDataSerializable instanceof MultiTargetEntityCastData) {
            MultiTargetEntityCastData targetingData = (MultiTargetEntityCastData)castDataSerializable;
            targetingData.getTargets().forEach(uuid -> {
                LivingEntity target = (LivingEntity)((ServerLevel)serverPlayer.level()).getRandomSequence(uuid);
                if (target != null) {
                    PsychicBoltProjectile boltProjectile = new PsychicBoltProjectile(level, (LivingEntity)serverPlayer);
                    boltProjectile.setLevel(origin.x(0.0, (double)boltProjectile.getBbHeight(), 0.0));
                    Vec3 vec = target.getBoundingBox().getCenter().multiply(serverPlayer.getEyePosition()).multiply();
                    float inaccuracy = (float)Mth.randomBetween((double)0.2f, (double)1.4f, (double)(target.position().lengthSqr(serverPlayer.position()) / 1024.0));
                    boltProjectile.shoot(vec.x(0.75), inaccuracy);
                    boltProjectile.setEffectDuration(this.getEffectDuration(recastInstance.getSpellLevel(), (LivingEntity)serverPlayer));
                    boltProjectile.setHomingTarget(target);
                    level.addFreshEntity((Entity)boltProjectile);
                }
            });
        }
    }

    private int getEffectDuration(int spellLevel, LivingEntity caster) {
        return (int)(100.0f + this.getSpellPower(spellLevel, (Entity)caster) * 100.0f);
    }

    public ICastDataSerializable getEmptyCastData() {
        return new MultiTargetEntityCastData(new LivingEntity[0]);
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.selector((String)"ui.traveloptics.recast", (Object[])new Object[]{Utils.stringTruncation((double)5.0, (int)2)}), Component.selector((String)"ui.traveloptics.duration", (Object[])new Object[]{Utils.timeFromTicks((float)this.getEffectDuration(spellLevel, caster), (int)2)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }
}

