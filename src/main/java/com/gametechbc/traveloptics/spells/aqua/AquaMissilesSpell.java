/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.AnimationHolder
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  javax.annotation.Nullable
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.spells.aqua;

import com.gametechbc.traveloptics.api.init.TravelopticsSchools;
import com.gametechbc.traveloptics.api.particle.JetFlamesParticleManager;
import com.gametechbc.traveloptics.api.utils.TOGeneralUtils;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedWaterBoltEntity;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.spells.TravelopticsSpellAnimations;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

@AutoSpellConfig
public class AquaMissilesSpell
extends AbstractSpell {
    private boolean lastOffsetLeft = true;
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "aqua_missiles");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.RARE).setSchoolResource(TravelopticsSchools.AQUA_RESOURCE).setMaxLevel(5).setCooldownSeconds(25.0).build();

    public AquaMissilesSpell() {
        this.baseManaCost = 5;
        this.manaCostPerLevel = 3;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 120;
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
        return Optional.empty();
    }

    public AnimationHolder getCastStartAnimation() {
        return TravelopticsSpellAnimations.AQUA_MISSILES_LOOP;
    }

    public void onCast(Level world, int spellLevel, LivingEntity caster, CastSource castSource, MagicData playerMagicData) {
        super.onCast(world, spellLevel, caster, castSource, playerMagicData);
    }

    public void onServerCastTick(Level level, int spellLevel, LivingEntity caster, @Nullable MagicData playerMagicData) {
        ParticleOptions[] particleTypes = new ParticleOptions[]{ParticleTypes.CLOUD, TravelopticsParticleHelper.WATER, TravelopticsParticleHelper.WATER_BUBBLE};
        int[] spawnPercentages = new int[]{10, 45, 45};
        JetFlamesParticleManager.createJetFlamesBelowDefaulted(caster.level(), (Entity)caster, particleTypes, spawnPercentages, 8, false);
        TOGeneralUtils.applyHovering((Entity)caster, 5.0, 0.2, 0.3, true);
        if (playerMagicData != null && (playerMagicData.getCastDurationRemaining() + 1) % 3 == 0) {
            ScreenShake_Entity.ScreenShake((Level)level, (Vec3)caster.position(), (float)8.0f, (float)0.012f, (int)2, (int)3);
            double dist = this.getSeekingDistance(spellLevel);
            Entity randomlyValid = AquaMissilesSpell.getRandomlyLookingAtEntityFor(level, caster, dist);
            ExtendedWaterBoltEntity bolt = new ExtendedWaterBoltEntity(level, caster, this.getDamage(spellLevel, caster), this.getDamage(spellLevel, caster), this.getAoeRadius());
            double offsetDirection = this.lastOffsetLeft ? -0.72 : 0.72;
            this.lastOffsetLeft = !this.lastOffsetLeft;
            double offsetX = offsetDirection * (double)Mth.randomBetween((float)(caster.getYRot() * ((float)Math.PI / 180)));
            double offsetZ = offsetDirection * (double)Mth.outFromOrigin((float)(caster.getYRot() * ((float)Math.PI / 180)));
            bolt.setPos(caster.getX() + offsetX, caster.getEyeY() - (double)0.4f, caster.getZ() + offsetZ);
            bolt.addAdditionalSaveData((Entity)caster);
            bolt.mayInteract((Entity)caster, caster.getXRot(), caster.getYRot(), -20.0f, 2.0f, 12.0f);
            bolt.seekAmount = 0.6f;
            if (randomlyValid != null) {
                bolt.setArcingTowards(randomlyValid.getUUID());
            }
            bolt.setAoeRadius(this.getAoeRadius());
            bolt.setAoeDamageAmount(this.getDamage(spellLevel, caster));
            level.addFreshEntity((Entity)bolt);
        }
        if (playerMagicData != null && (playerMagicData.getCastDurationRemaining() + 1) % 6 == 0) {
            level.getChunk(null, caster.getX(), caster.getY(), caster.getZ(), (SoundEvent)TravelopticsSounds.AQUA_MISSILES_CAST.get(), SoundSource.NEUTRAL, 1.5f, 1.0f);
        }
    }

    public static Entity getRandomlyLookingAtEntityFor(Level level, LivingEntity caster, double dist) {
        ArrayList<Entity> validEntities = new ArrayList<Entity>();
        Vec3 playerEyes = caster.setAirSupply(1.0f);
        BlockHitResult hitresult = level.traverseBlocks(new ClipContext(playerEyes, playerEyes.reverse(caster.getLookAngle().x(dist)), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, (Entity)caster));
        if (hitresult instanceof EntityHitResult) {
            Entity entity = ((EntityHitResult)hitresult).getEntity();
            if (!entity.equals((Object)caster) && !caster.isAlliedTo(entity) && !entity.isAlliedTo((Entity)caster) && entity instanceof Mob && caster.hasLineOfSight(entity)) {
                validEntities.add(entity);
            }
        } else {
            Vec3 at = hitresult.getLocation();
            AABB searchArea = new AABB(at.y(-0.5, -0.5, -0.5), at.y(0.5, 0.5, 0.5)).inflate(15.0);
            for (Entity entity : level.getNearbyEntities(LivingEntity.class, searchArea.inflate(dist))) {
                if (entity.equals((Object)caster) || caster.isAlliedTo(entity) || entity.isAlliedTo((Entity)caster) || !(entity instanceof Mob) || !caster.hasLineOfSight(entity)) continue;
                validEntities.add(entity);
            }
        }
        validEntities.sort(Comparator.comparingDouble(e -> e.getZ((Entity)caster)));
        if (!validEntities.isEmpty()) {
            int limit = Math.min(5, validEntities.size());
            return (Entity)validEntities.get(level.random.nextInt(limit));
        }
        return null;
    }

    public float getDamage(int spellLevel, LivingEntity caster) {
        return 4.0f + this.getSpellPower(spellLevel, (Entity)caster) * 1.5f;
    }

    public double getSeekingDistance(int spellLevel) {
        return 15 + spellLevel * 5;
    }

    public float getAoeRadius() {
        return 3.0f;
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return TOGeneralUtils.buildAquaSpellInfo(1, true, Component.selector((String)"ui.traveloptics.multi_hit_aoe_damage", (Object[])new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), (int)2)}), Component.selector((String)"ui.traveloptics.seeking_distance", (Object[])new Object[]{Utils.stringTruncation((double)this.getSeekingDistance(spellLevel), (int)2)}), Component.selector((String)"ui.traveloptics.aoe_radius", (Object[])new Object[]{Utils.stringTruncation((double)this.getAoeRadius(), (int)2)}));
    }
}

