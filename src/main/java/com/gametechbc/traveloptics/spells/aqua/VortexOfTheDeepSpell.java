/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.ICastData
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData
 *  io.redspace.ironsspellbooks.registries.SoundRegistry
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LightningBolt
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.spells.aqua;

import com.gametechbc.traveloptics.api.init.TravelopticsSchools;
import com.gametechbc.traveloptics.api.utils.TOGeneralUtils;
import com.gametechbc.traveloptics.entity.projectiles.aqua_vortex.AquaVortexEntity;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.ICastData;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import java.util.List;
import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

@AutoSpellConfig
public class VortexOfTheDeepSpell
extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "vortex_of_the_deep");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.LEGENDARY).setSchoolResource(TravelopticsSchools.AQUA_RESOURCE).setMaxLevel(6).setCooldownSeconds(60.0).build();

    public VortexOfTheDeepSpell() {
        this.manaCostPerLevel = 100;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 40;
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
        return Optional.of((SoundEvent)SoundRegistry.VOID_TENTACLES_START.get());
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of((SoundEvent)TravelopticsSounds.AQUA_VORTEX_CAST.get());
    }

    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        Utils.preCastTargetHelper((Level)level, (LivingEntity)entity, (MagicData)playerMagicData, (AbstractSpell)this, (int)32, (float)0.15f, (boolean)false);
        return true;
    }

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        TargetEntityCastData castTargetingData;
        LivingEntity target;
        Vec3 spawn = null;
        ICastData iCastData = playerMagicData.getAdditionalCastData();
        if (iCastData instanceof TargetEntityCastData && (target = (castTargetingData = (TargetEntityCastData)iCastData).getTarget((ServerLevel)world)) != null) {
            spawn = target.position();
        }
        if (spawn == null) {
            spawn = Utils.raycastForEntity((Level)world, (Entity)entity, (float)32.0f, (boolean)true, (float)0.15f).getLocation();
            spawn = Utils.moveToRelativeGroundLevel((Level)world, (Vec3)spawn, (int)6);
        }
        float radius = this.getRadius(spellLevel);
        AquaVortexEntity aquaVortex = new AquaVortexEntity(world);
        aquaVortex.getRandomX(spawn);
        aquaVortex.addAdditionalSaveData((Entity)entity);
        aquaVortex.setCircular();
        aquaVortex.setRadius(radius);
        aquaVortex.setDuration(this.getDuration());
        aquaVortex.setDamage(this.getDamage(spellLevel, entity));
        aquaVortex.setBoltStrikeDamage(this.getDamage(spellLevel, entity) * 2.0f);
        world.addFreshEntity((Entity)aquaVortex);
        LightningBolt lightningBolt = (LightningBolt)EntityType.LIGHTNING_BOLT.tryCast(world);
        assert (lightningBolt != null);
        lightningBolt.readAdditionalSaveData(true);
        lightningBolt.setDamage(0.0f);
        lightningBolt.setLevel(spawn);
        world.addFreshEntity((Entity)lightningBolt);
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return 2.0f + this.getSpellPower(spellLevel, (Entity)entity) * 1.5f;
    }

    private float getRadius(int spellLevel) {
        return 8 + spellLevel * 2;
    }

    private int getDuration() {
        return 400;
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return TOGeneralUtils.buildAquaSpellInfo(1, true, Component.selector((String)"ui.traveloptics.aoe_damage", (Object[])new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), (int)2)}), Component.selector((String)"ui.traveloptics.boltstrike_damage", (Object[])new Object[]{Utils.stringTruncation((double)(this.getDamage(spellLevel, caster) * 2.0f), (int)2)}), Component.selector((String)"ui.traveloptics.radius", (Object[])new Object[]{Utils.stringTruncation((double)this.getRadius(spellLevel), (int)1)}), Component.selector((String)"ui.traveloptics.duration", (Object[])new Object[]{Utils.timeFromTicks((float)this.getDuration(), (int)2)}));
    }
}

