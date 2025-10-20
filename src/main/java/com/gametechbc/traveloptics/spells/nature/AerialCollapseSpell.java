/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.ICastData
 *  io.redspace.ironsspellbooks.api.spells.SchoolType
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.AnimationHolder
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  io.redspace.ironsspellbooks.entity.spells.target_area.TargetedAreaEntity
 *  io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions
 *  io.redspace.ironsspellbooks.spells.TargetedTargetAreaCastData
 *  javax.annotation.Nullable
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  org.joml.Vector3f
 */
package com.gametechbc.traveloptics.spells.nature;

import com.gametechbc.traveloptics.api.particle.CylinderParticleManager;
import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.entity.projectiles.AerialCollapseVisualEntity;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.spells.TravelopticsSpellAnimations;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.ICastData;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.target_area.TargetedAreaEntity;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.spells.TargetedTargetAreaCastData;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

@AutoSpellConfig
public class AerialCollapseSpell
extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "aerial_collapse");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.UNCOMMON).setSchoolResource(SchoolRegistry.NATURE_RESOURCE).setMaxLevel(5).setCooldownSeconds(50.0).build();

    public AerialCollapseSpell() {
        this.manaCostPerLevel = 80;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 10;
        this.baseManaCost = 80;
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
        return Optional.of((SoundEvent)TravelopticsSounds.AERIAL_COLLAPSE.get());
    }

    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {
        return this.getCastTime(spellLevel);
    }

    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity caster, MagicData playerMagicData) {
        if (!Utils.preCastTargetHelper((Level)level, (LivingEntity)caster, (MagicData)playerMagicData, (AbstractSpell)this, (int)32, (float)0.35f, (boolean)true)) {
            return false;
        }
        LivingEntity target = ((TargetEntityCastData)playerMagicData.getAdditionalCastData()).getTarget((ServerLevel)level);
        float radius = this.getRadius(spellLevel, caster);
        TargetedAreaEntity area = TargetedAreaEntity.createTargetAreaEntity((Level)level, (Vec3)target.position(), (float)radius, (int)Utils.packRGB((Vector3f)this.getTargetingColor()), (Entity)target);
        playerMagicData.setAdditionalCastData((ICastData)new TargetedTargetAreaCastData(target, area));
        return true;
    }

    public void onServerCastTick(Level level, int spellLevel, LivingEntity caster, @Nullable MagicData playerMagicData) {
        caster.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.AERIAL_COLLAPSE_HELPER.get(), 3, 1, false, false, false));
    }

    public void onCast(Level level, int spellLevel, LivingEntity caster, CastSource castSource, MagicData playerMagicData) {
        TargetEntityCastData targetData;
        LivingEntity targetEntity;
        ICastData iCastData = playerMagicData.getAdditionalCastData();
        if (iCastData instanceof TargetEntityCastData && (targetEntity = (targetData = (TargetEntityCastData)iCastData).getTarget((ServerLevel)level)) instanceof LivingEntity) {
            LivingEntity livingTarget = targetEntity;
            float radius = this.getRadius(spellLevel, livingTarget);
            int effectLevel = (int)this.getDamage(spellLevel, caster);
            level.getNearbyEntities(LivingEntity.class, livingTarget.getBoundingBox().getYsize((double)radius, 4.0, (double)radius), t -> !DamageSources.isFriendlyFireBetween((Entity)t, (Entity)caster) && Utils.hasLineOfSight((Level)level, (Entity)livingTarget, (Entity)t, (boolean)true)).forEach(t -> {
                if (t.getZ((Entity)livingTarget) < (double)(radius * radius)) {
                    t.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.AERIAL_COLLAPSE.get(), 55, effectLevel - 1));
                }
            });
            if (!livingTarget.recreateFromPacket((MobEffect)TravelopticsEffects.AERIAL_COLLAPSE.get())) {
                livingTarget.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.AERIAL_COLLAPSE.get(), 55, effectLevel - 1));
            }
            AerialCollapseVisualEntity aerialCollapseVisual = new AerialCollapseVisualEntity(level);
            aerialCollapseVisual.addAdditionalSaveData((Entity)caster);
            aerialCollapseVisual.setRadius(this.getRadius(spellLevel, caster));
            aerialCollapseVisual.setDuration(55);
            aerialCollapseVisual.setCircular();
            aerialCollapseVisual.setLevel(targetEntity.position());
            level.addFreshEntity((Entity)aerialCollapseVisual);
            CylinderParticleManager.spawnParticles(level, (Entity)livingTarget, 40 * spellLevel, (ParticleOptions)ParticleTypes.DOLPHIN, ParticleDirection.UPWARD, radius, 17.0, 0.0);
            MagicManager.spawnParticles((Level)level, (ParticleOptions)new BlastwaveParticleOptions(((SchoolType)SchoolRegistry.NATURE.get()).getTargetingColor(), radius), (double)livingTarget.getX(), (double)(livingTarget.getY() + (double)0.165f), (double)livingTarget.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
        }
        caster.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.CASTING.get(), 55, 2, false, false, false));
        caster.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.AERIAL_COLLAPSE_HELPER.get(), 55, 5, false, false, false));
        caster.getStandingEyeHeight(new MobEffectInstance(MobEffects.SLOW_FALLING, 70, 0, false, false, false));
        super.onCast(level, spellLevel, caster, castSource, playerMagicData);
    }

    public float getRadius(int spellLevel, LivingEntity caster) {
        return 2 + spellLevel * 2;
    }

    public float getDamage(int spellLevel, LivingEntity caster) {
        return Math.min(12.0f + this.getSpellPower(spellLevel, (Entity)caster) * 2.0f, 50.0f);
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        int effectLevel = (int)this.getDamage(spellLevel, caster);
        return List.of(Component.selector((String)"ui.traveloptics.radius", (Object[])new Object[]{Utils.stringTruncation((double)this.getRadius(spellLevel, caster), (int)2)}), Component.selector((String)"ui.traveloptics.aerial_collapse_level.new", (Object[])new Object[]{effectLevel}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }

    public AnimationHolder getCastStartAnimation() {
        return TravelopticsSpellAnimations.AERIAL_COLLAPSE;
    }

    public AnimationHolder getCastFinishAnimation() {
        return AnimationHolder.pass();
    }

    public boolean stopSoundOnCancel() {
        return true;
    }
}

