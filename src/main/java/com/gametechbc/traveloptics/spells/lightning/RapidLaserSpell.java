/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.projectile.Laser_Beam_Entity
 *  com.github.L_Ender.cataclysm.init.ModEntities
 *  com.github.L_Ender.cataclysm.init.ModSounds
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.spells.lightning;

import com.github.L_Ender.cataclysm.entity.projectile.Laser_Beam_Entity;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.L_Ender.cataclysm.init.ModSounds;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import java.util.List;
import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

@AutoSpellConfig
public class RapidLaserSpell
extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "rapid_laser");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.COMMON).setSchoolResource(SchoolRegistry.LIGHTNING_RESOURCE).setMaxLevel(10).setCooldownSeconds(12.0).build();

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.selector((String)"ui.traveloptics.direct_damage", (Object[])new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), (int)2)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }

    public RapidLaserSpell() {
        this.manaCostPerLevel = 1;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 100;
        this.baseManaCost = 4;
    }

    public CastType getCastType() {
        return CastType.CONTINUOUS;
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
        return Optional.of((SoundEvent)ModSounds.HARBINGER_LASER.get());
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        this.spawnLaserBeam(level, entity, this.getDamage(spellLevel, entity), 1.5f);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private void spawnLaserBeam(Level level, LivingEntity caster, float damage, float speed) {
        Vec3 lookVec = caster.getZ(1.0f).multiply();
        Vec3 spawnPos = new Vec3(caster.getX(), caster.getY() + (double)(caster.getEyeHeight() * 0.8f), caster.getZ());
        float yRot = (float)(Mth.roundToward((double)lookVec.reverse, (double)lookVec.z) * 57.29577951308232) + 90.0f;
        float xRot = (float)(-(Mth.roundToward((double)lookVec.multiply, (double)Math.sqrt(lookVec.z * lookVec.z + lookVec.reverse * lookVec.reverse)) * 57.29577951308232));
        Laser_Beam_Entity laserBeam = new Laser_Beam_Entity((EntityType)ModEntities.LASER_BEAM.get(), caster, spawnPos.z, spawnPos.multiply, spawnPos.reverse, lookVec.z * (double)speed, lookVec.multiply * (double)speed, lookVec.reverse * (double)speed, damage, level);
        laserBeam.setYRot(yRot);
        laserBeam.setXRot(xRot);
        level.addFreshEntity((Entity)laserBeam);
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        return 5.0f + this.getSpellPower(spellLevel, (Entity)caster);
    }
}

