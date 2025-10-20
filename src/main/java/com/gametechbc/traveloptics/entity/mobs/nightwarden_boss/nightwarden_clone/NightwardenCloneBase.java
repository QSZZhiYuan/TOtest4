/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_clone;

import java.util.Collections;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NightwardenCloneBase
extends LivingEntity {
    private UUID summonerUUID;
    private LivingEntity cachedSummoner;

    public NightwardenCloneBase(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.teleportToWithTicket(false);
    }

    public void setSummoner(@Nullable LivingEntity owner) {
        if (owner != null) {
            this.summonerUUID = owner.getUUID();
            this.cachedSummoner = owner;
        }
    }

    public LivingEntity getSummoner() {
        if (this.cachedSummoner != null && this.cachedSummoner.isAlive()) {
            return this.cachedSummoner;
        }
        if (this.summonerUUID != null && this.level() instanceof ServerLevel) {
            Entity entity = ((ServerLevel)this.level()).getRandomSequence(this.summonerUUID);
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity;
                this.cachedSummoner = livingEntity = (LivingEntity)entity;
            }
            return this.cachedSummoner;
        }
        return null;
    }

    public Iterable<ItemStack> getArmorSlots() {
        return Collections.singleton(ItemStack.onUseTick);
    }

    public ItemStack shouldRemoveSoulSpeed(EquipmentSlot pSlot) {
        return ItemStack.onUseTick;
    }

    public void setLastDeathLocation(EquipmentSlot pSlot, ItemStack pStack) {
    }

    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    public boolean isPushable() {
        return false;
    }

    public boolean canBeCollidedWith() {
        return true;
    }

    public boolean isPickable() {
        return true;
    }

    public boolean canBeAffected(MobEffectInstance effect) {
        return false;
    }

    public boolean shouldShowName() {
        return false;
    }

    protected boolean isAlly(LivingEntity owner, LivingEntity target) {
        return owner.getTeam() != null && owner.getTeam().isAlliedTo(target.getTeam());
    }

    protected boolean isTamed(LivingEntity target) {
        if (target instanceof TamableAnimal) {
            TamableAnimal tamableAnimal = (TamableAnimal)target;
            return tamableAnimal.isTame();
        }
        return false;
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (this.summonerUUID != null) {
            tag.accept("Summoner", this.summonerUUID);
        }
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.readNamedTagName("Summoner")) {
            this.summonerUUID = tag.accept("Summoner");
        }
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes().build(Attributes.ATTACK_DAMAGE, 0.0).build(Attributes.register, 1.0).build(Attributes.FOLLOW_RANGE, 0.0).build(Attributes.KNOCKBACK_RESISTANCE, 100.0).build(Attributes.MOVEMENT_SPEED, 0.0);
    }
}

