/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.api.util.CameraShakeData
 *  io.redspace.ironsspellbooks.api.util.CameraShakeManager
 *  io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.phys.Vec3
 *  software.bernie.geckolib.core.animatable.GeoAnimatable
 *  software.bernie.geckolib.core.animation.AnimatableManager$ControllerRegistrar
 *  software.bernie.geckolib.core.animation.AnimationController
 *  software.bernie.geckolib.core.animation.AnimationState
 *  software.bernie.geckolib.core.animation.RawAnimation
 *  software.bernie.geckolib.core.object.PlayState
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.nightwarden_defeated;

import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenBossEntity;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.animated_particle.DefeatedEntityRespawnCooldown;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.animated_particle.DefeatedEntitySpawnTrigger;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class NightwardenDefeatedEntity
extends AbstractSpellCastingMob {
    private static final EntityDataAccessor<Boolean> TRIGGERED = SynchedEntityData.assignValue(NightwardenDefeatedEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> COOLDOWN_TICKS = SynchedEntityData.assignValue(NightwardenDefeatedEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private int currentAnimTime;
    private static final int ANIM_DURATION = 105;
    private int maxCooldown = 0;
    private final RawAnimation idle = RawAnimation.begin().thenLoop("defeated_base_pose");
    private final RawAnimation rise = RawAnimation.begin().thenPlay("begin_again");

    public NightwardenDefeatedEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.setPersistenceRequired();
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.accept("MaxCooldown", this.maxCooldown);
        tag.accept("CooldownTicks", this.getCooldown());
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.maxCooldown = tag.copy("MaxCooldown");
        if (tag.contains("CooldownTicks")) {
            this.setCooldown(tag.copy("CooldownTicks"));
        }
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.makeBoundingBox.assignValue(TRIGGERED, (Object)false);
        this.makeBoundingBox.assignValue(COOLDOWN_TICKS, (Object)0);
    }

    public boolean triggered() {
        return (Boolean)this.makeBoundingBox.packDirty(TRIGGERED);
    }

    private void trigger() {
        if (!this.triggered()) {
            if (!this.level().isClientSide) {
                CameraShakeManager.addCameraShake((CameraShakeData)new CameraShakeData(120, this.position(), 32.0f));
                ScreenShake_Entity.ScreenShake((Level)this.level(), (Vec3)this.position(), (float)15.0f, (float)0.1f, (int)15, (int)20);
            }
            this.makeBoundingBox.packDirty(TRIGGERED, (Object)true);
        }
    }

    public int getCooldown() {
        return (Integer)this.makeBoundingBox.packDirty(COOLDOWN_TICKS);
    }

    public void setCooldown(int ticks) {
        this.makeBoundingBox.packDirty(COOLDOWN_TICKS, (Object)ticks);
        if (ticks > this.maxCooldown) {
            this.maxCooldown = ticks;
        }
    }

    public boolean isOnCooldown() {
        return this.getCooldown() > 0;
    }

    public int getMaxCooldown() {
        return this.maxCooldown > 0 ? this.maxCooldown : 20;
    }

    private void tickCooldown() {
        if (this.getCooldown() > 0) {
            this.setCooldown(this.getCooldown() - 1);
        }
    }

    public boolean sendSystemMessage(DamageSource source, float amount) {
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            this.discard();
            return true;
        }
        return false;
    }

    protected InteractionResult rewardTradeXp(Player player, InteractionHand hand) {
        if (!this.triggered()) {
            if (this.getCooldown() > 0) {
                player.updateTutorialInventoryAction((Component)Component.translatable((String)"entity.traveloptics.message.defeated_cooldown_on"), true);
                return InteractionResult.SUCCESS;
            }
            if (hand == InteractionHand.MAIN_HAND && player.getMainHandItem().onDestroyed((Item)TravelopticsItems.EXCRUCIS.get())) {
                this.trigger();
                player.getMainHandItem().shrink(1);
                return InteractionResult.SUCCESS;
            }
            player.updateTutorialInventoryAction((Component)Component.translatable((String)"entity.traveloptics.message.defeated_no_item"), true);
            return InteractionResult.SUCCESS;
        }
        return super.rewardTradeXp(player, hand);
    }

    public void lerpMotion() {
        super.lerpMotion();
        this.tickCooldown();
        if (this.triggered()) {
            ++this.currentAnimTime;
            if (this.currentAnimTime == 1) {
                this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_DEFEATED_DEATH_STARE.get(), 1.5f, 1.0f);
            }
            if (this.currentAnimTime == 100) {
                this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.NIGHTWARDEN_BIG_SLAM_CLONES.get(), 1.5f, 1.2f);
                if (!this.level().isClientSide) {
                    ScreenShake_Entity.ScreenShake((Level)this.level(), (Vec3)this.position(), (float)15.0f, (float)0.1f, (int)15, (int)20);
                }
            }
            if (!this.level().isClientSide && this.currentAnimTime > 105) {
                NightwardenBossEntity boss = new NightwardenBossEntity((EntityType<? extends AbstractSpellCastingMob>)((EntityType)TravelopticsEntities.NIGHTWARDEN_BOSS.get()), this.level());
                boss.getRandomX(this.position().y(0.0, 1.0, 0.0));
                boss.finalizeSpawn((ServerLevelAccessor)((ServerLevel)this.level()), this.level().getCurrentDifficultyAt(boss.getOnPos()), MobSpawnType.TRIGGERED, null, null);
                int playerCount = Math.max(this.level().getNearbyEntities(Player.class, boss.getBoundingBox().inflate(32.0)).size(), 1);
                boss.getAttributes().load(Attributes.register).removePermanentModifier(new AttributeModifier("Gank Health Bonus", (double)(playerCount - 1) * 0.5, AttributeModifier.Operation.MULTIPLY_BASE));
                boss.setHealth(boss.getMaxHealth());
                boss.getAttributes().load(Attributes.ATTACK_DAMAGE).removePermanentModifier(new AttributeModifier("Gank Damage Bonus", (double)(playerCount - 1) * 0.25, AttributeModifier.Operation.MULTIPLY_BASE));
                boss.getAttributes().load((Attribute)AttributeRegistry.SPELL_RESIST.get()).removePermanentModifier(new AttributeModifier("Gank Spell Resist Bonus", (double)(playerCount - 1) * 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
                boss.setPersistenceRequired();
                this.level().addFreshEntity((Entity)boss);
                this.discard();
            }
        }
        if (this.level().isClientSide) {
            if (!this.triggered()) {
                int particleCount = 5;
                boolean cooldown = this.isOnCooldown();
                for (int i = 0; i < particleCount; ++i) {
                    double offsetX = (this.getId.nextDouble() - 0.5) * (double)this.getBbWidth();
                    double offsetY = this.getId.nextDouble() * (double)this.getBbHeight();
                    double offsetZ = (this.getId.nextDouble() - 0.5) * (double)this.getBbWidth();
                    double velocityX = (this.getId.nextDouble() - 0.5) * 0.04;
                    double velocityZ = (this.getId.nextDouble() - 0.5) * 0.04;
                    double velocityY = 0.1 + this.getId.nextDouble() * 0.1;
                    SimpleParticleType particle = cooldown ? ParticleTypes.SMOKE : (this.getId.nextFloat() < 0.94f ? ParticleTypes.SMOKE : TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE);
                    this.level().addDestroyBlockEffect((ParticleOptions)particle, this.getX() + offsetX, this.getY() + offsetY, this.getZ() + offsetZ, velocityX, velocityY, velocityZ);
                }
                DefeatedEntityRespawnCooldown.drawCooldownRuneCircle(this, this.isOnCooldown(), this.getCooldown(), this.getMaxCooldown());
            } else {
                float animProgress = (float)this.currentAnimTime / 105.0f;
                animProgress = Mth.outFromOrigin((float)animProgress, (float)0.0f, (float)1.0f);
                DefeatedEntitySpawnTrigger.handleBeginAgainAnimatedParticles(this, animProgress, this.currentAnimTime);
            }
        }
    }

    public boolean isPickable() {
        return true;
    }

    public boolean isPushable() {
        return false;
    }

    public boolean canBeCollidedWith() {
        return true;
    }

    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    public boolean isPersistenceRequired() {
        return true;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{new AnimationController((GeoAnimatable)this, "idle", 0, this::animationPredicate)});
    }

    private PlayState animationPredicate(AnimationState<?> event) {
        if (this.triggered()) {
            event.getController().setAnimation(this.rise);
        } else {
            event.getController().setAnimation(this.idle);
        }
        return PlayState.CONTINUE;
    }

    public boolean shouldBeExtraAnimated() {
        return false;
    }

    public boolean shouldAlwaysAnimateHead() {
        return false;
    }
}

