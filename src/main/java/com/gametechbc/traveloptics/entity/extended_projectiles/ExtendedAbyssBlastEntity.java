/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.client.particle.LightningParticle$OrbData
 *  com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Leviathan.Abyss_Blast_Entity
 *  com.github.L_Ender.cataclysm.init.ModEffect
 *  com.github.L_Ender.cataclysm.init.ModTag
 *  com.github.L_Ender.cataclysm.util.CMDamageTypes
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.util.Mth
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.event.ForgeEventFactory
 */
package com.gametechbc.traveloptics.entity.extended_projectiles;

import com.gametechbc.traveloptics.config.SpellsConfig;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.github.L_Ender.cataclysm.client.particle.LightningParticle;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Leviathan.Abyss_Blast_Entity;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModTag;
import com.github.L_Ender.cataclysm.util.CMDamageTypes;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.damage.DamageSources;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;

public class ExtendedAbyssBlastEntity
extends Abyss_Blast_Entity {
    public ExtendedAbyssBlastEntity(EntityType<? extends Abyss_Blast_Entity> type, Level world) {
        super(type, world);
    }

    public ExtendedAbyssBlastEntity(EntityType<? extends Abyss_Blast_Entity> type, Level world, LivingEntity caster, double x, double y, double z, float yaw, float pitch, int duration, float direction, float damage, float Hpdamage) {
        super(type, world, caster, x, y, z, yaw, pitch, duration, direction, damage, Hpdamage);
    }

    public void lerpMotion() {
        this.prevCollidePosX = this.collidePosX;
        this.prevCollidePosY = this.collidePosY;
        this.prevCollidePosZ = this.collidePosZ;
        this.prevYaw = this.renderYaw;
        this.prevPitch = this.renderPitch;
        this.xo = this.getX();
        this.yo = this.getY();
        this.zo = this.getZ();
        if (this.getTags == 1 && this.level().isClientSide) {
            this.caster = (LivingEntity)this.level().addDestroyBlockEffect(this.getCasterID());
        }
        if (!this.level().isClientSide && this.caster != null) {
            this.updateWithLivingEntity();
        }
        if (this.caster != null) {
            this.renderYaw = (float)((double)(this.caster.yHeadRot + this.getBeamDirection()) * Math.PI / 180.0);
            this.renderPitch = (float)((double)(-this.caster.getXRot()) * Math.PI / 180.0);
        }
        if (!this.on && this.appear.getTimer() == 0) {
            this.discard();
        }
        if (this.on && this.getTags > 20) {
            this.appear.increaseTimer();
        } else {
            this.appear.decreaseTimer();
        }
        if (this.caster != null && !this.caster.isAlive()) {
            this.discard();
        }
        if (this.getTags > 20) {
            this.calculateUpdatedEndPos();
            List<LivingEntity> hit = this.updatedRaytraceEntities((Level)this.level(), (Vec3)new Vec3((double)this.getX(), (double)this.getY(), (double)this.getZ()), (Vec3)new Vec3((double)this.endPosX, (double)this.endPosY, (double)this.endPosZ)).entities;
            if (this.blockSide != null) {
                this.spawnUpdatedExplosionParticles(3);
                if (((Boolean)SpellsConfig.abyssalBlastBlockBreaking.get()).booleanValue() && !this.level().isClientSide) {
                    for (BlockPos pos : BlockPos.relative((int)Mth.outFromOrigin((double)(this.collidePosX - 0.5)), (int)Mth.outFromOrigin((double)(this.collidePosY - 0.5)), (int)Mth.outFromOrigin((double)(this.collidePosZ - 0.5)), (int)Mth.outFromOrigin((double)(this.collidePosX + 0.5)), (int)Mth.outFromOrigin((double)(this.collidePosY + 0.5)), (int)Mth.outFromOrigin((double)(this.collidePosZ + 0.5)))) {
                        BlockState block = this.level().getBlockState(pos);
                        if (block.isSolidRender() || block.isFaceSturdy(ModTag.LEVIATHAN_IMMUNE) || !ForgeEventFactory.getMobGriefingEvent((Level)this.level(), (Entity)this)) continue;
                        this.level().addFreshEntity(pos, false);
                    }
                }
            }
            if (!this.level().isClientSide) {
                for (LivingEntity target : hit) {
                    MobEffectInstance effectinstance;
                    int i;
                    MobEffectInstance effectinstance1;
                    boolean flag;
                    if (((Boolean)SpellsConfig.abyssalBlastDealMagicDamage.get()).booleanValue()) {
                        if (this.caster == null || this.caster.isAlliedTo((Entity)target) || target == this.caster || !(flag = target.sendSystemMessage(CMDamageTypes.causeDeathLaserDamage((Entity)this, (LivingEntity)this.caster), (float)((double)this.getDamage() + Math.min((double)this.getDamage(), (double)(target.getMaxHealth() * this.getHpDamage()) * 0.01))))) continue;
                        effectinstance1 = target.getStandingEyeHeight((MobEffect)ModEffect.EFFECTABYSSAL_BURN.get());
                        i = 1;
                        if (effectinstance1 != null) {
                            i += effectinstance1.getAmplifier();
                            target.vehicleCanSprint((MobEffect)ModEffect.EFFECTABYSSAL_BURN.get());
                        } else {
                            --i;
                        }
                        i = Mth.outFromOrigin((int)i, (int)0, (int)3);
                        effectinstance = new MobEffectInstance((MobEffect)ModEffect.EFFECTABYSSAL_BURN.get(), 160, i, false, true, true);
                        target.getStandingEyeHeight(effectinstance);
                        continue;
                    }
                    if (this.caster == null || this.caster.isAlliedTo((Entity)target) || target == this.caster || !(flag = DamageSources.applyDamage((Entity)target, (float)((float)((double)this.getDamage() + Math.min((double)this.getDamage(), (double)(target.getMaxHealth() * this.getHpDamage()) * 0.01))), (DamageSource)((AbstractSpell)TravelopticsSpells.ABYSSAL_BLAST_SPELL.get()).getDamageSource((Entity)this, (Entity)this.caster)))) continue;
                    effectinstance1 = target.getStandingEyeHeight((MobEffect)ModEffect.EFFECTABYSSAL_BURN.get());
                    i = 1;
                    if (effectinstance1 != null) {
                        i += effectinstance1.getAmplifier();
                        target.vehicleCanSprint((MobEffect)ModEffect.EFFECTABYSSAL_BURN.get());
                    } else {
                        --i;
                    }
                    i = Mth.outFromOrigin((int)i, (int)0, (int)3);
                    effectinstance = new MobEffectInstance((MobEffect)ModEffect.EFFECTABYSSAL_BURN.get(), 160, i, false, true, true);
                    target.getStandingEyeHeight(effectinstance);
                }
            }
        }
        if (this.getTags - 20 > this.getDuration()) {
            this.on = false;
        }
    }

    private void spawnUpdatedExplosionParticles(int amount) {
        for (int i = 0; i < amount; ++i) {
            float velocity = 1.0f;
            float yaw = (float)((double)(this.getId.nextFloat() * 2.0f) * Math.PI);
            float motionY = this.getId.nextFloat() * 0.08f;
            float motionX = 1.0f * Mth.randomBetween((float)yaw);
            float motionZ = 1.0f * Mth.outFromOrigin((float)yaw);
            this.level().addDestroyBlockEffect((ParticleOptions)new LightningParticle.OrbData(102, 26, 204), this.collidePosX, this.collidePosY + 0.1, this.collidePosZ, (double)motionX, (double)motionY, (double)motionZ);
        }
    }

    private void calculateUpdatedEndPos() {
        if (this.level().isClientSide()) {
            this.endPosX = this.getX() + 50.0 * Math.cos(this.renderYaw) * Math.cos(this.renderPitch);
            this.endPosZ = this.getZ() + 50.0 * Math.sin(this.renderYaw) * Math.cos(this.renderPitch);
            this.endPosY = this.getY() + 50.0 * Math.sin(this.renderPitch);
        } else {
            this.endPosX = this.getX() + 50.0 * Math.cos(this.getYaw()) * Math.cos(this.getPitch());
            this.endPosZ = this.getZ() + 50.0 * Math.sin(this.getYaw()) * Math.cos(this.getPitch());
            this.endPosY = this.getY() + 50.0 * Math.sin(this.getPitch());
        }
    }

    private void updateWithLivingEntity() {
        this.setYaw((float)((double)(this.caster.yHeadRot + 90.0f) * Math.PI / 180.0));
        this.setPitch((float)((double)(-this.caster.getXRot()) * Math.PI / 180.0));
        double eyeOffset = 0.06;
        this.setPos(this.caster.getX(), this.caster.getY() + (double)this.caster.getEyeHeight() - eyeOffset, this.caster.getZ());
    }

    public AbyssLaserbeamHitResult updatedRaytraceEntities(Level world, Vec3 from, Vec3 to) {
        AbyssLaserbeamHitResult updatedResult = new AbyssLaserbeamHitResult();
        updatedResult.setBlockHit((HitResult)world.traverseBlocks(new ClipContext(from, to, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this)));
        if (updatedResult.blockHit != null) {
            Vec3 hitVec = updatedResult.blockHit.getLocation();
            this.collidePosX = hitVec.z;
            this.collidePosY = hitVec.multiply;
            this.collidePosZ = hitVec.reverse;
            this.blockSide = updatedResult.blockHit.getDirection();
        } else {
            this.collidePosX = this.endPosX;
            this.collidePosY = this.endPosY;
            this.collidePosZ = this.endPosZ;
            this.blockSide = null;
        }
        List entities = world.getNearbyEntities(LivingEntity.class, new AABB(Math.min(this.getX(), this.collidePosX), Math.min(this.getY(), this.collidePosY), Math.min(this.getZ(), this.collidePosZ), Math.max(this.getX(), this.collidePosX), Math.max(this.getY(), this.collidePosY), Math.max(this.getZ(), this.collidePosZ)).getYsize(2.0, 2.0, 2.0));
        for (LivingEntity entity : entities) {
            if (entity == this.caster) continue;
            float pad = entity.getPickRadius() + 0.5f;
            AABB aabb = entity.getBoundingBox().getYsize((double)pad, (double)pad, (double)pad);
            Optional hit = aabb.clip(from, to);
            if (aabb.getZsize(from)) {
                updatedResult.addEntityHit(entity);
                continue;
            }
            if (!hit.isPresent()) continue;
            updatedResult.addEntityHit(entity);
        }
        return updatedResult;
    }

    public static class AbyssLaserbeamHitResult {
        private BlockHitResult blockHit;
        private final List<LivingEntity> entities = new ArrayList<LivingEntity>();

        public BlockHitResult getBlockHit() {
            return this.blockHit;
        }

        public void setBlockHit(HitResult rayTraceResult) {
            if (rayTraceResult.getType() == HitResult.Type.BLOCK) {
                this.blockHit = (BlockHitResult)rayTraceResult;
            }
        }

        public void addEntityHit(LivingEntity entity) {
            this.entities.add(entity);
        }
    }
}

