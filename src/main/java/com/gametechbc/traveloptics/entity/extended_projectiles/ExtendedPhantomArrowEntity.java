/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.projectile.Phantom_Arrow_Entity
 *  net.minecraft.core.Holder
 *  net.minecraft.core.Holder$Reference
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageType
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.AbstractArrow$Pickup
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraftforge.network.PlayMessages$SpawnEntity
 */
package com.gametechbc.traveloptics.entity.extended_projectiles;

import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.util.TravelopticsDamageTypes;
import com.github.L_Ender.cataclysm.entity.projectile.Phantom_Arrow_Entity;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

public class ExtendedPhantomArrowEntity
extends Phantom_Arrow_Entity {
    private int customLife = 0;
    private Holder<DamageType> customDamageType;
    private float customDamage = -1.0f;

    public ExtendedPhantomArrowEntity(EntityType<? extends ExtendedPhantomArrowEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    public ExtendedPhantomArrowEntity(Level worldIn, LivingEntity shooter) {
        super((EntityType)TravelopticsEntities.EXTENDED_PHANTOM_ARROW.get(), shooter.getX(), shooter.getEyeY() - (double)0.1f, shooter.getZ(), worldIn);
        this.addAdditionalSaveData((Entity)shooter);
        this.pickup = AbstractArrow.Pickup.DISALLOWED;
    }

    public ExtendedPhantomArrowEntity(Level worldIn, LivingEntity shooter, LivingEntity finalTarget) {
        super((EntityType)TravelopticsEntities.EXTENDED_PHANTOM_ARROW.get(), shooter.getX(), shooter.getEyeY() - (double)0.1f, shooter.getZ(), worldIn);
        this.addAdditionalSaveData((Entity)shooter);
        this.pickup = AbstractArrow.Pickup.DISALLOWED;
    }

    public ExtendedPhantomArrowEntity(PlayMessages.SpawnEntity spawnEntity, Level level) {
        super((EntityType)TravelopticsEntities.EXTENDED_PHANTOM_ARROW.get(), level);
    }

    public void setCustomDamageType(Holder<DamageType> damageType) {
        this.customDamageType = damageType;
    }

    public void setCustomDamage(float damage) {
        this.customDamage = damage;
    }

    public float getCustomDamage() {
        return this.customDamage;
    }

    protected void setDangerous(EntityHitResult result) {
        boolean isEnderman;
        Entity hitEntity = result.getEntity();
        Entity owner = this.getOwner();
        Holder.Reference damageTypeHolder = this.customDamageType != null ? this.customDamageType : this.level().registryAccess().allRegistriesLifecycle(Registries.DAMAGE_TYPE).getHolderOrThrow(TravelopticsDamageTypes.NULLFLARE_FIRE);
        DamageSource damageSource = new DamageSource((Holder)damageTypeHolder);
        float damageAmount = this.customDamage >= 0.0f ? this.customDamage : (float)this.setShotFromCrossbow();
        boolean bl = isEnderman = hitEntity.getType() == EntityType.ENDERMAN;
        if (this.isOnFire() && !isEnderman) {
            hitEntity.getRandomZ(5);
        }
        if (hitEntity.sendSystemMessage(damageSource, damageAmount)) {
            if (isEnderman) {
                return;
            }
            if (hitEntity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity)hitEntity;
                if (owner instanceof LivingEntity) {
                    EnchantmentHelper.getAvailableEnchantmentResults((LivingEntity)livingEntity, (Entity)owner);
                    EnchantmentHelper.selectEnchantment((LivingEntity)((LivingEntity)owner), (Entity)livingEntity);
                }
                this.readAdditionalSaveData(livingEntity);
            }
        } else {
            this.setDeltaMovement(this.getDeltaMovement().x(-0.1));
            this.setYRot(this.getYRot() + 180.0f);
            this.yRotO += 180.0f;
            if (!this.level().isClientSide && this.getDeltaMovement().lengthSqr() < 1.0E-7) {
                this.discard();
            }
        }
        this.updateTutorialInventoryAction(SoundEvents.ARROW_HIT, 1.0f, 1.2f / (this.getId.nextFloat() * 0.2f + 0.9f));
    }

    protected ItemStack getPickupItem() {
        return ItemStack.onUseTick;
    }

    protected void tickDespawn() {
        ++this.customLife;
        if (this.customLife >= 200) {
            this.discard();
        }
    }
}

