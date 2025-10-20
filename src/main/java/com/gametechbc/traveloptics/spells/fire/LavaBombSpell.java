/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModEntities
 *  com.github.L_Ender.cataclysm.init.ModParticle
 *  com.github.L_Ender.cataclysm.init.ModSounds
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.SpellAnimations
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.AnimationHolder
 *  io.redspace.ironsspellbooks.api.util.CameraShakeData
 *  io.redspace.ironsspellbooks.api.util.CameraShakeManager
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.level.material.Fluids
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.spells.fire;

import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.L_Ender.cataclysm.init.ModParticle;
import com.github.L_Ender.cataclysm.init.ModSounds;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellAnimations;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;

@AutoSpellConfig
public class LavaBombSpell
extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "lava_bomb");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.EPIC).setSchoolResource(SchoolRegistry.FIRE_RESOURCE).setMaxLevel(3).setCooldownSeconds(18.0).build();

    public LavaBombSpell() {
        this.baseManaCost = 40;
        this.manaCostPerLevel = 20;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 50;
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
        return SpellAnimations.CHARGE_SPIT_ANIMATION;
    }

    public AnimationHolder getCastFinishAnimation() {
        return SpellAnimations.SPIT_FINISH_ANIMATION;
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of((SoundEvent)ModSounds.MONSTROSITYGROWL.get());
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of((SoundEvent)ModSounds.MONSTROSITYSHOOT.get());
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        int projectileCount = (int)this.getProjectileCount(spellLevel, caster);
        double motionScale = this.getMotionScale();
        return List.of(Component.selector((String)"ui.traveloptics.lava_bomb_projectile_count", (Object[])new Object[]{projectileCount}), Component.selector((String)"ui.traveloptics.motion_scale", (Object[])new Object[]{motionScale}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
        if (!level.isClientSide()) {
            int projectileCount = (int)this.getProjectileCount(spellLevel, entity);
            double angleBetween = projectileCount > 1 ? 0.7853981633974483 / (double)(projectileCount - 1) : 0.0;
            for (int i = 0; i < projectileCount; ++i) {
                double angle = projectileCount > 1 ? -0.39269908169872414 + angleBetween * (double)i : 0.0;
                Vec3 lookVec = entity.getLookAngle();
                double x = lookVec.z * Math.cos(angle) - lookVec.reverse * Math.sin(angle);
                double z = lookVec.z * Math.sin(angle) + lookVec.reverse * Math.cos(angle);
                double motionScale = this.getMotionScale();
                Vec3 motion = new Vec3(x, lookVec.multiply, z).multiply().x(motionScale);
                EntityType entityType = (EntityType)ModEntities.LAVA_BOMB.get();
                Projectile projectile = (Projectile)entityType.tryCast(level);
                if (projectile == null) continue;
                projectile.moveTo(entity.getX(), entity.getY() + 1.5, entity.getZ(), 0.0f, 0.0f);
                projectile.setIsInPowderSnow(motion.x(), motion.y(), motion.z());
                CameraShakeManager.addCameraShake((CameraShakeData)new CameraShakeData(18, entity.position(), 25.0f));
                level.addFreshEntity((Entity)projectile);
            }
            int radius = 32;
            BlockPos pos = entity.blockPosition();
            int posX = pos.setX();
            int posY = pos.getY();
            int posZ = pos.getZ();
            int absorbedCount = 0;
            int maxAbsorbed = (int)this.getProjectileCount(spellLevel, entity);
            for (int dx = -radius; dx <= radius; ++dx) {
                for (int dy = -radius; dy <= radius; ++dy) {
                    for (int dz = -radius; dz <= radius && absorbedCount < maxAbsorbed; ++dz) {
                        BlockPos blockPos = new BlockPos(posX + dx, posY + dy, posZ + dz);
                        BlockState state = level.getBlockState(blockPos);
                        FluidState fluidState = level.getFluidState(blockPos);
                        Fluid fluid = fluidState.canBeReplacedWith();
                        if (fluid != Fluids.LAVA || !fluidState.is()) continue;
                        level.isFluidAtPosition(blockPos, Blocks.rebuildCache.defaultBlockState());
                        ++absorbedCount;
                    }
                }
                if (absorbedCount >= maxAbsorbed) break;
            }
            Vec3 lookVec = entity.getLookAngle();
            double offsetDistance = 1.0;
            Vec3 handPosition = new Vec3(entity.getX(), entity.getY() + (double)entity.getEyeHeight() - 0.5, entity.getZ());
            Vec3 offsetPosition = handPosition.reverse(lookVec.x(offsetDistance));
            ParticleOptions particleType = (ParticleOptions)ModParticle.TRAP_FLAME.get();
            MagicManager.spawnParticles((Level)level, (ParticleOptions)particleType, (double)offsetPosition.x(), (double)offsetPosition.y(), (double)offsetPosition.z(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
        }
    }

    private float getProjectileCount(int spellLevel, LivingEntity caster) {
        return this.getSpellPower(spellLevel, (Entity)caster);
    }

    private double getMotionScale() {
        return 1.0;
    }
}

