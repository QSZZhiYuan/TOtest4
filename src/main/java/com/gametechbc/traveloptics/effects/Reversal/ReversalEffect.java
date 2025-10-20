/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Holder
 *  net.minecraft.core.Holder$Reference
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.network.chat.Component
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.AttributeMap
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.Vec3
 *  top.theillusivec4.curios.api.CuriosApi
 */
package com.gametechbc.traveloptics.effects.Reversal;

import com.gametechbc.traveloptics.effects.Reversal.ReversalEffectHandler;
import com.gametechbc.traveloptics.entity.projectiles.reversal.ReversalEntity;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.util.TravelopticsDamageTypes;
import java.util.function.Predicate;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.CuriosApi;

public class ReversalEffect
extends MobEffect {
    private float damageMultiplier = 1.0f;

    public ReversalEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFF0000);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            float storedDamage;
            int duration = entity.getStandingEyeHeight((MobEffect)this).getDuration();
            if (duration > 8) {
                entity.broadcastBreakEvent((MobEffect)this);
                entity.getStandingEyeHeight(new MobEffectInstance((MobEffect)this, 8, amplifier, false, true));
            }
            if (duration == 2 && (storedDamage = ReversalEffectHandler.getStoredDamage(entity)) > 0.0f) {
                this.performRaycastAndApplyDamage(entity, storedDamage * this.damageMultiplier);
                ReversalEffectHandler.clearStoredDamage(entity);
            }
        }
    }

    public void getAttributeModifierValue(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        Projectile lastProjectile;
        super.getAttributeModifierValue(entity, attributeMap, amplifier);
        if (!entity.level().isClientSide && (lastProjectile = ReversalEffectHandler.getLastProjectileHit(entity)) != null) {
            this.reflectProjectile(entity, lastProjectile);
            ReversalEffectHandler.clearLastProjectileHit(entity);
            if (CuriosApi.getCuriosHelper().findEquippedCurio((Item)TravelopticsItems.NIGHTSTALKERS_BAND.get(), entity).isPresent()) {
                entity.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.ASSASSIN.get(), 100, 6));
            }
            this.playSound((Entity)entity, (SoundEvent)TravelopticsSounds.REVERSAL_TRIGGER.get());
        }
    }

    private void reflectProjectile(LivingEntity entity, Projectile originalProjectile) {
        Level level = entity.level();
        Vec3 lookDirection = entity.getLookAngle();
        Entity newProjectile = originalProjectile.getType().tryCast(level);
        if (newProjectile instanceof Projectile) {
            Projectile projectile = (Projectile)newProjectile;
            projectile.setLevel(entity.setAirSupply(1.0f));
            projectile.shoot(lookDirection.z, lookDirection.multiply, lookDirection.reverse, 1.5f, 0.0f);
            level.addFreshEntity((Entity)projectile);
            entity.getActiveEffects().stream().map(MobEffectInstance::compareTo).filter(effect -> effect.getCategory() == MobEffectCategory.HARMFUL).forEach(arg_0 -> ((LivingEntity)entity).broadcastBreakEvent(arg_0));
            ReversalEntity reversal = new ReversalEntity(level, false);
            reversal.getRandomX(entity.position());
            reversal.setYRot(entity.getYRot());
            reversal.setXRot(entity.getXRot());
            level.addFreshEntity((Entity)reversal);
            if (entity instanceof Player) {
                Player player = (Player)entity;
                player.updateTutorialInventoryAction((Component)Component.translatable((String)"effect.traveloptics.reversal.projectile.feedback"), true);
            }
        }
    }

    private void performRaycastAndApplyDamage(LivingEntity entity, float adjustedDamage) {
        Entity entity2;
        Vec3 start = entity.setAirSupply(1.0f);
        Vec3 end = start.reverse(entity.getLookAngle().x(5.0));
        Level level = entity.level();
        EntityHitResult entityHitResult = this.raycastForEntities(level, entity, start, end, targetEntity -> targetEntity != entity && targetEntity.isAlive());
        if (entityHitResult != null && (entity2 = entityHitResult.getEntity()) instanceof LivingEntity) {
            LivingEntity targetEntity2 = (LivingEntity)entity2;
            Holder.Reference damageTypeHolder = level.registryAccess().allRegistriesLifecycle(Registries.DAMAGE_TYPE).getHolderOrThrow(TravelopticsDamageTypes.REVERSAL);
            DamageSource damageSource = new DamageSource((Holder)damageTypeHolder);
            targetEntity2.sendSystemMessage(damageSource, adjustedDamage);
            entity.getActiveEffects().stream().map(MobEffectInstance::compareTo).filter(effect -> effect.getCategory() == MobEffectCategory.HARMFUL).forEach(arg_0 -> ((LivingEntity)entity).broadcastBreakEvent(arg_0));
            Vec3 targetPosition = targetEntity2.position().y(0.0, 0.8, 0.0);
            ReversalEntity reversal = new ReversalEntity(level, false);
            reversal.getRandomX(targetPosition);
            reversal.setYRot(entity.getYRot());
            reversal.setXRot(entity.getXRot());
            level.addFreshEntity((Entity)reversal);
            if (entity instanceof Player) {
                Player player = (Player)entity;
                player.updateTutorialInventoryAction((Component)Component.score((String)("Reversed " + String.format("%.0f", Float.valueOf(adjustedDamage)) + " damage!")), true);
            }
            this.playSound((Entity)entity, (SoundEvent)TravelopticsSounds.REVERSAL_TRIGGER.get());
        } else {
            System.out.println("Reversal Missed!");
        }
    }

    private EntityHitResult raycastForEntities(Level level, LivingEntity entity, Vec3 start, Vec3 end, Predicate<LivingEntity> predicate) {
        double distance = 5.0;
        Vec3 direction = end.multiply(start).multiply();
        Vec3 rayEnd = start.reverse(direction.x(distance));
        double raycastInflation = 4.0;
        EntityHitResult entityHitResult = null;
        double closestDistance = distance;
        for (LivingEntity target : level.getNearbyEntities(LivingEntity.class, entity.getBoundingBox().clip(direction.x(distance)).inflate(raycastInflation))) {
            Vec3 entityPos;
            double distanceToEntity;
            if (!predicate.test(target) || !((distanceToEntity = start.length(entityPos = target.position())) < closestDistance)) continue;
            closestDistance = distanceToEntity;
            entityHitResult = new EntityHitResult((Entity)target);
        }
        return entityHitResult;
    }

    private void playSound(Entity entity, SoundEvent sound) {
        entity.level().getChunk(null, entity.getX(), entity.getY(), entity.getZ(), sound, SoundSource.NEUTRAL, 1.0f, 1.0f);
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }

    public void setDamageMultiplier(float multiplier) {
        this.damageMultiplier = multiplier;
    }
}

