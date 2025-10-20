/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.client.particle.CircleLightningParticle$CircleData
 *  com.github.L_Ender.cataclysm.client.particle.StormParticle$OrbData
 *  com.github.L_Ender.cataclysm.init.ModParticle
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.SpellAnimations
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.AnimationHolder
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  javax.annotation.Nullable
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.spells.aqua;

import com.gametechbc.traveloptics.api.init.TravelopticsSchools;
import com.gametechbc.traveloptics.api.spells.AbstractUniqueSpell;
import com.gametechbc.traveloptics.api.utils.TOGeneralUtils;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedWaterSpearEntity;
import com.gametechbc.traveloptics.entity.misc.TOFollowingScreenShakeEntity;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.github.L_Ender.cataclysm.client.particle.CircleLightningParticle;
import com.github.L_Ender.cataclysm.client.particle.StormParticle;
import com.github.L_Ender.cataclysm.init.ModParticle;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellAnimations;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

@AutoSpellConfig
public class TheHowlingTempestSpell
extends AbstractUniqueSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "the_howling_tempest");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.EPIC).setSchoolResource(TravelopticsSchools.AQUA_RESOURCE).setMaxLevel(3).setCooldownSeconds(60.0).build();

    public TheHowlingTempestSpell() {
        this.manaCostPerLevel = 3;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 200;
        this.baseManaCost = 5;
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
        return Optional.of(SoundEvents.LIGHTNING_BOLT_THUNDER);
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.empty();
    }

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    public void onServerCastTick(Level world, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        if (playerMagicData != null) {
            Vec3 stormCenter = new Vec3(entity.getX(), entity.getY(), entity.getZ());
            float radius = this.getStormRadius(spellLevel);
            int castTime = this.castTime - playerMagicData.getCastDurationRemaining();
            double stormHeight = 8.0;
            if (castTime >= 15 && castTime < 40) {
                this.spawnChargingLightning(world, stormCenter, stormHeight * 2.0, radius, castTime);
            }
            if (castTime >= 40 && castTime <= 70) {
                this.spawnStormBuildupEffects(world, entity, stormCenter, stormHeight, radius, castTime);
            }
            if (castTime >= 70) {
                this.spawnStormBarrage(world, entity, stormCenter, stormHeight, radius, spellLevel, castTime);
            }
            if (castTime >= 40) {
                this.applyStormKnockback(world, entity, stormCenter, radius);
            }
            this.applyScreenShake(world, entity, castTime);
        }
        super.onServerCastTick(world, spellLevel, entity, playerMagicData);
    }

    private void spawnChargingLightning(Level world, Vec3 center, double height, float radius, int castTime) {
        if (castTime % 6 == 0) {
            for (int i = 0; i < 4; ++i) {
                double angle = 1.5707963267948966 * (double)i + (double)castTime * 0.15;
                double lightningRadius = (double)radius * 8.5;
                double posX = lightningRadius * Math.cos(angle);
                double posY = lightningRadius * 0.8 * Math.sin(angle) + 8.0;
                double posZ = lightningRadius * Math.sin(angle);
                MagicManager.spawnParticles((Level)world, (ParticleOptions)new CircleLightningParticle.CircleData(99, 194, 224), (double)(center.z + posX), (double)(center.multiply + height + posY), (double)(center.reverse + posZ), (int)0, (double)center.z, (double)(center.multiply + height), (double)center.reverse, (double)1.0, (boolean)true);
            }
        }
    }

    private void spawnStormBuildupEffects(Level world, LivingEntity caster, Vec3 center, double height, float radius, int castTime) {
        float r = 0.56078434f;
        float g = 0.94509804f;
        float b = 0.84313726f;
        MagicManager.spawnParticles((Level)world, (ParticleOptions)new StormParticle.OrbData(0.3882353f, 0.7607843f, 0.8784314f, 2.5f + caster.getRandom().nextFloat() * 0.5f, 0.8f, caster.getId()), (double)center.z, (double)center.multiply, (double)center.reverse, (int)2, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
        MagicManager.spawnParticles((Level)world, (ParticleOptions)new StormParticle.OrbData(r, g, b, 2.2f + caster.getRandom().nextFloat() * 0.3f, 0.8f, caster.getId()), (double)center.z, (double)center.multiply, (double)center.reverse, (int)2, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
        if (castTime % 2 == 0) {
            this.spawnRainClouds(world, caster, center, height, radius, 20, 15);
        }
        if (castTime % 5 == 0) {
            this.spawnCircleLightning(world, caster, center, height, radius);
        }
    }

    private void spawnStormBarrage(Level world, LivingEntity caster, Vec3 center, double height, float radius, int spellLevel, int castTime) {
        if (castTime % 5 == 0) {
            world.getChunk(null, caster.getX(), caster.getY(), caster.getZ(), (SoundEvent)TravelopticsSounds.AQUA_CAST_2.get(), SoundSource.NEUTRAL, 2.0f, 0.8f + caster.getRandom().nextFloat() * 0.4f);
        }
        if (castTime % 4 == 0) {
            Entity hitEntity;
            HitResult hitResult = Utils.raycastForEntity((Level)world, (Entity)caster, (float)this.getRange(), (boolean)true, (float)0.15f);
            LivingEntity target = null;
            if (hitResult.getType() == HitResult.Type.ENTITY && (hitEntity = ((EntityHitResult)hitResult).getEntity()) instanceof LivingEntity) {
                LivingEntity livingTarget;
                target = livingTarget = (LivingEntity)hitEntity;
            }
            int spearCount = this.getSpearCount();
            for (int i = 0; i < spearCount; ++i) {
                float angle = caster.getRandom().nextFloat() * (float)Math.PI * 2.0f;
                float distance = Mth.outFromOrigin((float)caster.getRandom().nextFloat()) * radius;
                double spearX = center.z + Math.cos(angle) * (double)distance;
                double spearZ = center.reverse + Math.sin(angle) * (double)distance;
                double spearY = center.multiply + height;
                this.spawnStormSpear(world, caster, spearX, spearY, spearZ, spellLevel, target, hitResult.getLocation());
            }
        }
        if (castTime % 2 == 0) {
            this.spawnRainClouds(world, caster, center, height, radius, 25, 20);
        }
    }

    private void spawnStormSpear(Level world, LivingEntity caster, double x, double y, double z, int spellLevel, @Nullable LivingEntity target, Vec3 hitLocation) {
        Vec3 targetPos;
        Vec3 spearPos = new Vec3(x, y, z);
        if (target != null) {
            Vec3 targetVelocity = target.getDeltaMovement();
            double travelTime = spearPos.length(target.position()) / 1.5;
            Vec3 predictedPos = target.position().reverse(targetVelocity.x(travelTime));
            targetPos = predictedPos.y((double)(caster.getRandom().nextFloat() - 0.5f) * 0.8, (double)(caster.getRandom().nextFloat() - 0.5f) * 0.5, (double)(caster.getRandom().nextFloat() - 0.5f) * 0.8);
        } else {
            targetPos = hitLocation.y((double)(caster.getRandom().nextFloat() - 0.5f) * 1.2, (double)(caster.getRandom().nextFloat() - 0.5f) * 0.8, (double)(caster.getRandom().nextFloat() - 0.5f) * 1.2);
        }
        double deltaX = targetPos.z - x;
        double deltaY = targetPos.multiply - y;
        double deltaZ = targetPos.reverse - z;
        Vec3 direction = new Vec3(deltaX, deltaY, deltaZ).multiply();
        float yRot = (float)(Mth.roundToward((double)direction.reverse, (double)direction.z) * 180.0 / Math.PI) + 90.0f;
        float xRot = (float)(-Mth.roundToward((double)direction.multiply, (double)Math.sqrt(direction.z * direction.z + direction.reverse * direction.reverse)) * 180.0 / Math.PI);
        ExtendedWaterSpearEntity water = new ExtendedWaterSpearEntity(caster, direction, world, this.getDamage(spellLevel, caster));
        water.accelerationPower = 0.12 + (double)spellLevel * 0.008;
        water.setYRot(yRot);
        water.setXRot(xRot);
        water.setPosRaw(x, y, z);
        water.setTotalBounces(2 + spellLevel);
        world.addFreshEntity((Entity)water);
    }

    private void spawnRainClouds(Level world, LivingEntity caster, Vec3 center, double height, float radius, int amount, int randomAmount) {
        for (int j = 0; j < amount + caster.getRandom().nextInt(randomAmount); ++j) {
            float angle = caster.getRandom().nextFloat() * (float)Math.PI * 2.0f;
            float distance = caster.getRandom().nextFloat() * radius * 0.9f;
            double cloudX = center.z + Math.cos(angle) * (double)distance;
            double cloudY = center.multiply + height;
            double cloudZ = center.reverse + Math.sin(angle) * (double)distance;
            MagicManager.spawnParticles((Level)world, (ParticleOptions)((ParticleOptions)ModParticle.RAIN_CLOUD.get()), (double)cloudX, (double)cloudY, (double)cloudZ, (int)1, (double)(caster.getRandom().nextGaussian() * 0.02), (double)(caster.getRandom().nextGaussian() * 0.005), (double)(caster.getRandom().nextGaussian() * 0.02), (double)0.0, (boolean)false);
        }
    }

    private void spawnCircleLightning(Level world, LivingEntity caster, Vec3 center, double height, float radius) {
        for (int i = 0; i < 3 + caster.getRandom().nextInt(1); ++i) {
            double theta = caster.getRandom().nextDouble() * 2.0 * Math.PI;
            double phi = caster.getRandom().nextDouble() * Math.PI;
            double lightningRadius = (double)radius * 0.7;
            double posX = lightningRadius * Math.sin(phi) * Math.cos(theta);
            double posY = lightningRadius * Math.cos(phi);
            double posZ = lightningRadius * Math.sin(phi) * Math.sin(theta);
            MagicManager.spawnParticles((Level)world, (ParticleOptions)new CircleLightningParticle.CircleData(143, 241, 215), (double)(center.z + posX), (double)(center.multiply + height + posY), (double)(center.reverse + posZ), (int)0, (double)center.z, (double)(center.multiply + height), (double)center.reverse, (double)1.0, (boolean)true);
        }
    }

    private void applyStormKnockback(Level world, LivingEntity caster, Vec3 center, float radius) {
        List entities = world.getNearbyEntities(Entity.class, caster.getBoundingBox().inflate((double)radius), entity -> entity != caster && entity.getY((Entity)caster) <= radius);
        for (Entity target : entities) {
            double deltaX = target.getX() - center.z;
            double deltaZ = target.getZ() - center.reverse;
            double distance = Math.max(deltaX * deltaX + deltaZ * deltaZ, 0.001);
            double force = target.isShiftKeyDown() ? 0.3 : 0.6;
            target.getRemoveItemSound(deltaX / distance * force, 0.0, deltaZ / distance * force);
        }
    }

    private void applyScreenShake(Level world, LivingEntity caster, int castTime) {
        if (castTime == 25) {
            TOFollowingScreenShakeEntity.createFollowingScreenShake(world, (Entity)caster, 15.0f, 0.005f, 5, 10, 0, true);
        } else if (castTime == 40) {
            TOFollowingScreenShakeEntity.createFollowingScreenShake(world, (Entity)caster, 18.0f, 0.01f, 16, 0, 0, true);
        } else if (castTime == 55) {
            TOFollowingScreenShakeEntity.createFollowingScreenShake(world, (Entity)caster, 22.0f, 0.015f, 16, 0, 0, true);
        } else if (castTime == 70) {
            TOFollowingScreenShakeEntity.createFollowingScreenShake(world, (Entity)caster, 28.0f, 0.02f, 19, 1, 10, true);
        }
    }

    private float getStormRadius(int spellLevel) {
        return 9.0f + (float)spellLevel * 1.0f;
    }

    public float getDamage(int spellLevel, LivingEntity caster) {
        return 5.0f + this.getSpellPower(spellLevel, (Entity)caster) * 5.0f;
    }

    private float getRange() {
        return 32.0f;
    }

    private int getSpearCount() {
        return 3;
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return TOGeneralUtils.buildAquaSpellInfo(1, true, Component.selector((String)"ui.traveloptics.range", (Object[])new Object[]{Utils.stringTruncation((double)this.getRange(), (int)2)}), Component.selector((String)"ui.traveloptics.multi_hit_direct_damage", (Object[])new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), (int)2)}));
    }

    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.ANIMATION_CONTINUOUS_OVERHEAD;
    }
}

