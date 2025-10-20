/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModParticle
 *  com.github.L_Ender.cataclysm.init.ModSounds
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.AnimationHolder
 *  io.redspace.ironsspellbooks.api.util.CameraShakeData
 *  io.redspace.ironsspellbooks.api.util.CameraShakeManager
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.spells.ice;

import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.api.particle.SphereParticleManager;
import com.gametechbc.traveloptics.api.spells.AbstractUniqueSpell;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedPhantomHalberdEntity;
import com.gametechbc.traveloptics.spells.TravelopticsSpellAnimations;
import com.github.L_Ender.cataclysm.init.ModParticle;
import com.github.L_Ender.cataclysm.init.ModSounds;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.api.util.Utils;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

@AutoSpellConfig
public class HalberdHorizonSpell
extends AbstractUniqueSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "halberd_horizon");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.RARE).setSchoolResource(SchoolRegistry.ICE_RESOURCE).setMaxLevel(6).setCooldownSeconds(20.0).build();

    public HalberdHorizonSpell() {
        this.manaCostPerLevel = 40;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 21;
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

    public AnimationHolder getCastStartAnimation() {
        return TravelopticsSpellAnimations.HALBERD_HORIZON_CAST;
    }

    public AnimationHolder getCastFinishAnimation() {
        return TravelopticsSpellAnimations.HALBERD_HORIZON_FINISH;
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of((SoundEvent)ModSounds.FLAME_BURST.get());
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of((SoundEvent)ModSounds.MALEDICTUS_SHORT_ROAR.get());
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.selector((String)"ui.traveloptics.aoe_damage", (Object[])new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), (int)2)}), Component.selector((String)"ui.traveloptics.ring_count", (Object[])new Object[]{this.getRingCount(spellLevel)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }

    public void onCast(Level level, int spellLevel, LivingEntity caster, CastSource castSource, MagicData playerMagicData) {
        int rings = this.getRingCount(spellLevel);
        int entitiesInFirstRing = 8;
        float distanceBetweenRings = 1.0f;
        float innerRingDistance = 1.5f;
        float damage = this.getDamage(spellLevel, caster);
        double ringOffset = 0.39269908169872414;
        for (int ring = 0; ring < rings; ++ring) {
            float currentRingDistance = innerRingDistance + (float)ring * distanceBetweenRings;
            int entitiesInThisRing = entitiesInFirstRing + ring * 4;
            int warmupDelay = ring * 3;
            double currentRingOffset = ring % 2 == 0 ? 0.0 : ringOffset;
            for (int i = 0; i < entitiesInThisRing; ++i) {
                double angle = Math.PI * 2 * (double)i / (double)entitiesInThisRing + currentRingOffset;
                double offsetX = (double)currentRingDistance * Math.cos(angle);
                double offsetZ = (double)currentRingDistance * Math.sin(angle);
                double x = caster.getX() + offsetX;
                double y = caster.getY();
                double z = caster.getZ() + offsetZ;
                ExtendedPhantomHalberdEntity halberdEntity = new ExtendedPhantomHalberdEntity(level, x, y, z, (float)angle, warmupDelay, caster, damage);
                level.addFreshEntity((Entity)halberdEntity);
                CameraShakeManager.addCameraShake((CameraShakeData)new CameraShakeData(25, caster.position(), 12.0f));
                SphereParticleManager.spawnParticles(level, (Entity)caster, 3, (ParticleOptions)ModParticle.PHANTOM_WING_FLAME.get(), ParticleDirection.OUTWARD, 3.5);
            }
        }
        super.onCast(level, spellLevel, caster, castSource, playerMagicData);
    }

    public int getRingCount(int spellLevel) {
        return 1 + spellLevel;
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        return 18.0f + this.getSpellPower(spellLevel, (Entity)caster) * 4.0f;
    }
}

