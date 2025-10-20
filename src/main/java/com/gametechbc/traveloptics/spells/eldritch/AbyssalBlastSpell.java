/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Leviathan.Abyss_Blast_Entity
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.CameraShakeData
 *  io.redspace.ironsspellbooks.api.util.CameraShakeManager
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  javax.annotation.Nullable
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.spells.eldritch;

import com.gametechbc.traveloptics.api.spells.AbstractUniqueSpell;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedAbyssBlastEntity;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Leviathan.Abyss_Blast_Entity;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.api.util.Utils;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

@AutoSpellConfig
public class AbyssalBlastSpell
extends AbstractUniqueSpell {
    private static final ResourceLocation SPELL_ID = new ResourceLocation("traveloptics", "abyssal_blast");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.EPIC).setSchoolResource(SchoolRegistry.ELDRITCH_RESOURCE).setMaxLevel(3).setCooldownSeconds(20.0).build();

    public AbyssalBlastSpell() {
        this.manaCostPerLevel = 150;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 50;
        this.baseManaCost = 50;
    }

    public ResourceLocation getSpellResource() {
        return SPELL_ID;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public CastType getCastType() {
        return CastType.LONG;
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of((SoundEvent)TravelopticsSounds.REFINED_ABYSSAL_BLAST_CHARGE.get());
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of((SoundEvent)TravelopticsSounds.REFINED_ABYSSAL_BLAST_SHOOT.get());
    }

    public boolean canBeInterrupted(@Nullable Player player) {
        return false;
    }

    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {
        return this.getCastTime(spellLevel);
    }

    public void onCast(Level level, int spellLevel, LivingEntity caster, CastSource castSource, MagicData playerMagicData) {
        if (!level.isClientSide) {
            this.applyCasterEffects(caster);
            this.spawnAbyssBlastEntity(level, spellLevel, caster);
        }
        CameraShakeManager.addCameraShake((CameraShakeData)new CameraShakeData(25, caster.position(), 25.0f));
        super.onCast(level, spellLevel, caster, castSource, playerMagicData);
    }

    private void spawnAbyssBlastEntity(Level level, int spellLevel, LivingEntity caster) {
        float getDamage = this.getDamage(spellLevel, caster);
        double x = caster.getX();
        double y = caster.getY() + (double)caster.getEyeHeight() - 0.2;
        double z = caster.getZ();
        float pitch = -((float)((double)caster.getXRot() * Math.PI / 180.0));
        float yaw = (float)((double)(caster.getYRot() + 90.0f) * Math.PI / 180.0);
        ExtendedAbyssBlastEntity abyssBlast = new ExtendedAbyssBlastEntity((EntityType<? extends Abyss_Blast_Entity>)((EntityType)TravelopticsEntities.EXTENDED_ABYSS_BLAST.get()), level, caster, x, y, z, yaw, pitch, 80, 90.0f, getDamage, 0.0f);
        level.addFreshEntity((Entity)abyssBlast);
    }

    private void applyCasterEffects(LivingEntity caster) {
        caster.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.FROZEN_SIGHT.get(), 100, 1, false, false, false));
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        return 10.0f + this.getSpellPower(spellLevel, (Entity)caster) * 5.0f;
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        float getDamage = this.getDamage(spellLevel, caster);
        return List.of(Component.selector((String)"ui.traveloptics.damage", (Object[])new Object[]{Utils.stringTruncation((double)getDamage, (int)2)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }

    public boolean stopSoundOnCancel() {
        return true;
    }
}

