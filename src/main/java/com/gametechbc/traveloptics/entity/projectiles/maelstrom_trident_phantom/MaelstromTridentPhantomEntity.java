/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.client.particle.Custom_Poof_Particle$PoofData
 *  com.github.L_Ender.cataclysm.client.particle.LightningParticle$OrbData
 *  com.github.L_Ender.cataclysm.client.particle.Not_Spin_TrailParticle$NSTData
 *  com.github.L_Ender.cataclysm.init.ModSounds
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible
 *  io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions
 *  javax.annotation.Nullable
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.network.NetworkHooks
 *  software.bernie.geckolib.animatable.GeoEntity
 *  software.bernie.geckolib.core.animatable.GeoAnimatable
 *  software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
 *  software.bernie.geckolib.core.animation.AnimatableManager$ControllerRegistrar
 *  software.bernie.geckolib.core.animation.AnimationController
 *  software.bernie.geckolib.core.animation.AnimationState
 *  software.bernie.geckolib.core.animation.RawAnimation
 *  software.bernie.geckolib.core.object.PlayState
 *  software.bernie.geckolib.util.GeckoLibUtil
 */
package com.gametechbc.traveloptics.entity.projectiles.maelstrom_trident_phantom;

import com.gametechbc.traveloptics.api.utils.TOGeneralUtils;
import com.gametechbc.traveloptics.entity.misc.TOScreenShakeEntity;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.gametechbc.traveloptics.particle.colorful_bubble.ColorfulBubbleParticleOptions;
import com.github.L_Ender.cataclysm.client.particle.Custom_Poof_Particle;
import com.github.L_Ender.cataclysm.client.particle.LightningParticle;
import com.github.L_Ender.cataclysm.client.particle.Not_Spin_TrailParticle;
import com.github.L_Ender.cataclysm.init.ModSounds;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class MaelstromTridentPhantomEntity
extends Entity
implements GeoEntity,
AntiMagicSusceptible {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
    private UUID summonerUUID;
    private LivingEntity cachedSummoner;
    private static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.assignValue(MaelstromTridentPhantomEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_FIRST_IMPACT_DAMAGE = SynchedEntityData.assignValue(MaelstromTridentPhantomEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_PULSE_DAMAGE = SynchedEntityData.assignValue(MaelstromTridentPhantomEntity.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> DATA_MAX_AGE_TICKS = SynchedEntityData.assignValue(MaelstromTridentPhantomEntity.class, (EntityDataSerializer)EntityDataSerializers.getSerializedId);
    private static final EntityDataAccessor<Boolean> DATA_IS_DROPPING = SynchedEntityData.assignValue(MaelstromTridentPhantomEntity.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private boolean hasDealtImpactDamage = false;
    private int pulseTicker = 0;
    private static final int DROP_DURATION = 14;
    private static final int PULSE_INTERVAL = 40;
    private float customGravity = -1.5f;
    private boolean shouldSpawnDelayedBlastwave = false;
    private int delayedBlastwaveTimer = 0;
    private boolean applyReplenish = false;
    private final RawAnimation TRIDENT_DROP_ANIMATION = RawAnimation.begin().thenPlay("trident_drop");
    private final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("idle");
    private final AnimationController<?> controller = new AnimationController((GeoAnimatable)this, "maelstrom_phantom_controller", 0, this::animationPredicate);

    public MaelstromTridentPhantomEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.getY(true);
    }

    public MaelstromTridentPhantomEntity(Level level, LivingEntity owner, float yRot) {
        this((EntityType)TravelopticsEntities.MAELSTROM_TRIDENT_PHANTOM.get(), level);
        this.setSummoner(owner);
        this.setYRot(yRot);
        this.getY(true);
    }

    public void setSummoner(@Nullable LivingEntity owner) {
        if (owner != null) {
            this.summonerUUID = owner.getUUID();
            this.cachedSummoner = owner;
        }
    }

    public LivingEntity getSummoner() {
        Entity entity;
        if (this.cachedSummoner != null && this.cachedSummoner.isAlive()) {
            return this.cachedSummoner;
        }
        if (this.summonerUUID != null && this.level() instanceof ServerLevel && (entity = ((ServerLevel)this.level()).getRandomSequence(this.summonerUUID)) instanceof LivingEntity) {
            LivingEntity livingEntity;
            this.cachedSummoner = livingEntity = (LivingEntity)entity;
            return this.cachedSummoner;
        }
        return null;
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

    public void setRadius(float radius) {
        this.makeBoundingBox.packDirty(DATA_RADIUS, (Object)Float.valueOf(radius));
    }

    public float getRadius() {
        return ((Float)this.makeBoundingBox.packDirty(DATA_RADIUS)).floatValue();
    }

    public void setFirstImpactDamage(float damage) {
        this.makeBoundingBox.packDirty(DATA_FIRST_IMPACT_DAMAGE, (Object)Float.valueOf(damage));
    }

    public float getFirstImpactDamage() {
        return ((Float)this.makeBoundingBox.packDirty(DATA_FIRST_IMPACT_DAMAGE)).floatValue();
    }

    public void setPulseDamage(float damage) {
        this.makeBoundingBox.packDirty(DATA_PULSE_DAMAGE, (Object)Float.valueOf(damage));
    }

    public float getPulseDamage() {
        return ((Float)this.makeBoundingBox.packDirty(DATA_PULSE_DAMAGE)).floatValue();
    }

    public void setMaxAgeTicks(int ticks) {
        this.makeBoundingBox.packDirty(DATA_MAX_AGE_TICKS, (Object)ticks);
    }

    public int getMaxAgeTicks() {
        return (Integer)this.makeBoundingBox.packDirty(DATA_MAX_AGE_TICKS);
    }

    public void setIsDropping(boolean dropping) {
        this.makeBoundingBox.packDirty(DATA_IS_DROPPING, (Object)dropping);
    }

    public boolean isDropping() {
        return (Boolean)this.makeBoundingBox.packDirty(DATA_IS_DROPPING);
    }

    public void setCustomGravity(float gravity) {
        this.customGravity = gravity;
    }

    public float getCustomGravity() {
        return this.customGravity;
    }

    private void scheduleDelayedBlastwave() {
        this.shouldSpawnDelayedBlastwave = true;
        this.delayedBlastwaveTimer = 3;
    }

    public void setApplyReplenish(boolean applyReplenish) {
        this.applyReplenish = applyReplenish;
    }

    public boolean shouldApplyReplenish() {
        return this.applyReplenish;
    }

    public void lerpMotion() {
        super.lerpMotion();
        if (this.getTags >= this.getMaxAgeTicks()) {
            this.discard();
            return;
        }
        if (!this.onGround()) {
            Vec3 currentMotion = this.getDeltaMovement();
            Vec3 newMotion = new Vec3(currentMotion.z, (double)this.customGravity, currentMotion.reverse);
            this.setDeltaMovement(newMotion);
            this.updateTutorialInventoryAction(MoverType.SELF, this.getDeltaMovement());
        }
        if (this.isDropping() && this.getTags >= 14) {
            this.setIsDropping(false);
            if (!this.hasDealtImpactDamage) {
                this.dealImpactDamage();
                this.hasDealtImpactDamage = true;
            }
        }
        if (!this.isDropping()) {
            ++this.pulseTicker;
            if (this.pulseTicker >= 40) {
                this.dealPulseDamage();
                this.pulseTicker = 0;
            }
            this.spawnPassiveParticles();
        }
        if (this.shouldSpawnDelayedBlastwave) {
            --this.delayedBlastwaveTimer;
            if (this.delayedBlastwaveTimer <= 0) {
                if (!this.level().isClientSide) {
                    MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)new BlastwaveParticleOptions(TOGeneralUtils.hexToVector3f("#57acdd"), this.getRadius()), (double)this.getX(), (double)(this.getY() + (double)0.15f), (double)this.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
                }
                this.shouldSpawnDelayedBlastwave = false;
            }
        }
    }

    private void dealImpactDamage() {
        if (!this.level().isClientSide) {
            float damage = this.getFirstImpactDamage();
            float radius = this.getRadius();
            this.damageEntitiesInRadius(damage, radius, false);
            MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)new BlastwaveParticleOptions(TOGeneralUtils.hexToVector3f("#57acdd"), radius), (double)this.getX(), (double)(this.getY() + (double)0.15f), (double)this.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
            this.scheduleDelayedBlastwave();
            TOScreenShakeEntity.createScreenShake(this.level(), this.position(), this.getRadius() * 2.0f, 0.02f, 10, 0, 5, false);
            this.updateTutorialInventoryAction(SoundEvents.TRIDENT_THUNDER, 3.0f, 0.8f);
            this.updateTutorialInventoryAction((SoundEvent)ModSounds.HEAVY_SMASH.get(), 2.0f, 1.0f);
        }
        if (this.level().isClientSide) {
            this.spawnImpactParticles();
        }
    }

    private void dealPulseDamage() {
        if (!this.level().isClientSide) {
            float damage = this.getPulseDamage();
            float radius = this.getRadius();
            this.damageEntitiesInRadius(damage, radius, true);
            MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)new BlastwaveParticleOptions(TOGeneralUtils.hexToVector3f("#57acdd"), this.getRadius()), (double)this.getX(), (double)(this.getY() + (double)0.15f), (double)this.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
            TOScreenShakeEntity.createScreenShake(this.level(), this.position(), this.getRadius() * 2.0f, 0.008f, 5, 0, 2, false);
            this.updateTutorialInventoryAction((SoundEvent)TravelopticsSounds.AQUA_CAST_2.get(), 1.5f, 0.8f);
            this.updateTutorialInventoryAction(SoundEvents.TRIDENT_THUNDER, 2.0f, 1.0f);
        }
        if (this.level().isClientSide) {
            this.spawnImpactBubblesAndFoam(this.getRadius());
        }
    }

    private void damageEntitiesInRadius(float damage, float radius, boolean applyTidalTorment) {
        LivingEntity owner = this.getSummoner();
        AABB region = new AABB(this.getX() - (double)radius, this.getY() - 1.0, this.getZ() - (double)radius, this.getX() + (double)radius, this.getY() + 2.0, this.getZ() + (double)radius);
        List<LivingEntity> targets = this.level().getNearbyEntities(LivingEntity.class, region).stream().filter(entity -> entity.isAlive() && entity != owner).filter(entity -> owner == null || !this.isAlly(owner, (LivingEntity)entity) && !this.isTamed((LivingEntity)entity)).toList();
        for (LivingEntity target : targets) {
            DamageSources.applyDamage((Entity)target, (float)damage, (DamageSource)((AbstractSpell)TravelopticsSpells.SKYPIERCER_SPELL.get()).getDamageSource((Entity)this, (Entity)owner));
            if (applyTidalTorment) {
                target.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.TIDAL_TORMENT.get(), 60, 1, false, true));
            }
            if (!this.shouldApplyReplenish() || owner == null) continue;
            int currentAmplifier = owner.recreateFromPacket((MobEffect)TravelopticsEffects.REPLENISH_EFFECT.get()) ? owner.getStandingEyeHeight((MobEffect)TravelopticsEffects.REPLENISH_EFFECT.get()).getAmplifier() + 1 : 0;
            int cappedAmplifier = Math.min(currentAmplifier, 7);
            owner.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.REPLENISH_EFFECT.get(), 50, cappedAmplifier, false, false, false));
        }
    }

    private void spawnPassiveParticles() {
        if (this.level().isClientSide) {
            int i;
            float radius = this.getRadius();
            if (this.getTags % 4 == 0) {
                int sparkAmount = 1 + this.getId.nextInt(2);
                for (i = 0; i < sparkAmount; ++i) {
                    float velocity = 1.2f + this.getId.nextFloat() * 0.6f;
                    float yaw = (float)((double)(this.getId.nextFloat() * 2.0f) * Math.PI);
                    float motionY = 0.3f + this.getId.nextFloat() * 0.5f;
                    float motionX = velocity * Mth.randomBetween((float)yaw);
                    float motionZ = velocity * Mth.outFromOrigin((float)yaw);
                    double collidePosX = this.getX();
                    double collidePosY = this.getY();
                    double collidePosZ = this.getZ();
                    this.level().addDestroyBlockEffect((ParticleOptions)new LightningParticle.OrbData(TOGeneralUtils.hexToRed("#57acdd"), TOGeneralUtils.hexToGreen("#57acdd"), TOGeneralUtils.hexToBlue("#57acdd")), collidePosX, collidePosY + 0.1, collidePosZ, (double)motionX, (double)motionY, (double)motionZ);
                }
            }
            int bubbleCount = Math.max(1, Math.round(radius * 0.22f));
            for (i = 0; i < bubbleCount; ++i) {
                double angle = this.getId.nextDouble() * 2.0 * Math.PI;
                double distance = this.getId.nextDouble() * (double)radius;
                double particleX = this.getX() + Math.cos(angle) * distance;
                double particleY = this.getY() + 0.1 + this.getId.nextDouble() * 0.2;
                double particleZ = this.getZ() + Math.sin(angle) * distance;
                double motionX = (this.getId.nextDouble() - 0.5) * 0.05;
                double motionY = 0.15 + this.getId.nextDouble() * 0.1;
                double motionZ = (this.getId.nextDouble() - 0.5) * 0.05;
                this.level().addDestroyBlockEffect((ParticleOptions)new ColorfulBubbleParticleOptions(TOGeneralUtils.hexToVector3f("#57acdd"), 1.0f, 10 + (int)(Math.random() * 5.0), true), particleX, particleY, particleZ, motionX, motionY, motionZ);
            }
            if (this.getTags % 2 == 0) {
                int foamCount = Math.max(1, Math.round(radius * 0.2f));
                for (int i2 = 0; i2 < foamCount; ++i2) {
                    double angle = this.getId.nextDouble() * 2.0 * Math.PI;
                    double distance = this.getId.nextDouble() * (double)radius;
                    double particleX = this.getX() + Math.cos(angle) * distance;
                    double particleY = this.getY() + 0.1 + this.getId.nextDouble() * 0.2;
                    double particleZ = this.getZ() + Math.sin(angle) * distance;
                    int rand = this.getId.nextInt(20) - 10;
                    int r = Math.max(0, Math.min(255, TOGeneralUtils.hexToRed("#57acdd") + rand));
                    int g = Math.max(0, Math.min(255, TOGeneralUtils.hexToGreen("#57acdd") + rand));
                    int b = Math.max(0, Math.min(255, TOGeneralUtils.hexToBlue("#57acdd") + rand));
                    double motionX = (this.getId.nextDouble() - 0.5) * 0.1;
                    double motionY = -0.01 - this.getId.nextDouble() * 0.02;
                    double motionZ = (this.getId.nextDouble() - 0.5) * 0.1;
                    this.level().addDestroyBlockEffect((ParticleOptions)new Custom_Poof_Particle.PoofData(r, g, b, 0.1f), particleX, particleY, particleZ, motionX, motionY, motionZ);
                }
            }
        }
    }

    private void spawnImpactParticles() {
        float radius = this.getRadius();
        for (int i = 0; i < 12; ++i) {
            float angle = (float)((double)i * 0.5235987755982988);
            float velocity = 2.0f + this.getId.nextFloat() * 1.5f;
            double d0 = this.getX();
            double d1 = this.getY() + 0.1;
            double d2 = this.getZ();
            double extraX = d0 + (double)(velocity * Mth.randomBetween((float)angle));
            double extraY = d1 + 1.5 + (double)(this.getId.nextFloat() * 1.5f);
            double extraZ = d2 + (double)(velocity * Mth.outFromOrigin((float)angle));
            float red = (float)TOGeneralUtils.hexToRed("#57acdd") / 255.0f;
            float green = (float)TOGeneralUtils.hexToGreen("#57acdd") / 255.0f;
            float blue = (float)TOGeneralUtils.hexToBlue("#57acdd") / 255.0f;
            this.level().addDestroyBlockEffect((ParticleOptions)new Not_Spin_TrailParticle.NSTData(red, green, blue, 0.05f, 0.75f, 0.5f, 0.0, 80 + this.getId.nextInt(40)), d0, d1, d2, extraX, extraY, extraZ);
        }
        this.spawnImpactBubblesAndFoam(radius);
    }

    private void spawnImpactBubblesAndFoam(float radius) {
        int bubbleCount = Math.round(radius * 2.0f);
        for (int i = 0; i < bubbleCount; ++i) {
            double angle = this.getId.nextDouble() * 2.0 * Math.PI;
            double distance = this.getId.nextDouble() * (double)radius;
            double particleX = this.getX() + Math.cos(angle) * distance;
            double particleY = this.getY() + 0.1 + this.getId.nextDouble() * 0.3;
            double particleZ = this.getZ() + Math.sin(angle) * distance;
            double motionX = (this.getId.nextDouble() - 0.5) * 0.3;
            double motionY = 0.2 + this.getId.nextDouble() * 0.3;
            double motionZ = (this.getId.nextDouble() - 0.5) * 0.3;
            this.level().addDestroyBlockEffect((ParticleOptions)new ColorfulBubbleParticleOptions(TOGeneralUtils.hexToVector3f("#57acdd"), 1.0f, 10 + (int)(Math.random() * 5.0), true), particleX, particleY, particleZ, motionX, motionY, motionZ);
        }
        int foamCount = Math.round(radius);
        for (int i = 0; i < foamCount; ++i) {
            double angle = this.getId.nextDouble() * 2.0 * Math.PI;
            double distance = this.getId.nextDouble() * (double)radius;
            double particleX = this.getX() + Math.cos(angle) * distance;
            double particleY = this.getY() + 0.2 + this.getId.nextDouble() * 0.4;
            double particleZ = this.getZ() + Math.sin(angle) * distance;
            int rand = this.getId.nextInt(30) - 15;
            int r = Math.max(0, Math.min(255, TOGeneralUtils.hexToRed("#57acdd") + rand));
            int g = Math.max(0, Math.min(255, TOGeneralUtils.hexToGreen("#57acdd") + rand));
            int b = Math.max(0, Math.min(255, TOGeneralUtils.hexToBlue("#57acdd") + rand));
            double motionX = (this.getId.nextDouble() - 0.5) * 0.4;
            double motionY = 0.1 + this.getId.nextDouble() * 0.2;
            double motionZ = (this.getId.nextDouble() - 0.5) * 0.4;
            this.level().addDestroyBlockEffect((ParticleOptions)new Custom_Poof_Particle.PoofData(r, g, b, 0.2f), particleX, particleY, particleZ, motionX, motionY, motionZ);
        }
    }

    public void onAntiMagic(MagicData playerMagicData) {
        this.discard();
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

    protected void defineSynchedData() {
        this.makeBoundingBox.assignValue(DATA_RADIUS, (Object)Float.valueOf(12.0f));
        this.makeBoundingBox.assignValue(DATA_FIRST_IMPACT_DAMAGE, (Object)Float.valueOf(5.0f));
        this.makeBoundingBox.assignValue(DATA_PULSE_DAMAGE, (Object)Float.valueOf(3.0f));
        this.makeBoundingBox.assignValue(DATA_MAX_AGE_TICKS, (Object)200);
        this.makeBoundingBox.assignValue(DATA_IS_DROPPING, (Object)true);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        if (this.summonerUUID != null) {
            tag.accept("Summoner", this.summonerUUID);
        }
        tag.accept("Radius", this.getRadius());
        tag.accept("FirstImpactDamage", this.getFirstImpactDamage());
        tag.accept("PulseDamage", this.getPulseDamage());
        tag.accept("MaxAgeTicks", this.getMaxAgeTicks());
        tag.accept("IsDropping", this.isDropping());
        tag.accept("HasDealtImpactDamage", this.hasDealtImpactDamage);
        tag.accept("PulseTicker", this.pulseTicker);
        tag.accept("CustomGravity", this.customGravity);
        tag.accept("ApplyReplenish", this.applyReplenish);
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        if (tag.readNamedTagName("Summoner")) {
            this.summonerUUID = tag.accept("Summoner");
        }
        if (tag.contains("Radius")) {
            this.setRadius(tag.getFloat("Radius"));
        }
        if (tag.contains("FirstImpactDamage")) {
            this.setFirstImpactDamage(tag.getFloat("FirstImpactDamage"));
        }
        if (tag.contains("PulseDamage")) {
            this.setPulseDamage(tag.getFloat("PulseDamage"));
        }
        if (tag.contains("MaxAgeTicks")) {
            this.setMaxAgeTicks(tag.copy("MaxAgeTicks"));
        }
        if (tag.contains("IsDropping")) {
            this.setIsDropping(tag.getBoolean("IsDropping"));
        }
        if (tag.contains("HasDealtImpactDamage")) {
            this.hasDealtImpactDamage = tag.getBoolean("HasDealtImpactDamage");
        }
        if (tag.contains("PulseTicker")) {
            this.pulseTicker = tag.copy("PulseTicker");
        }
        if (tag.contains("CustomGravity")) {
            this.customGravity = tag.getFloat("CustomGravity");
        }
        if (tag.contains("ApplyReplenish")) {
            this.applyReplenish = tag.getBoolean("ApplyReplenish");
        }
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    private PlayState animationPredicate(AnimationState<?> event) {
        if (this.isDropping()) {
            event.getController().setAnimation(this.TRIDENT_DROP_ANIMATION);
        } else {
            event.getController().setAnimation(this.IDLE_ANIMATION);
        }
        return PlayState.CONTINUE;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{this.controller});
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}

