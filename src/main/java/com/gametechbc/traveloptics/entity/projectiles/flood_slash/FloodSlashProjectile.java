/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible
 *  io.redspace.ironsspellbooks.entity.spells.AbstractShieldEntity
 *  io.redspace.ironsspellbooks.entity.spells.ShieldPart
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.util.Mth
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.entity.projectile.ProjectileUtil
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.entity.projectiles.flood_slash;

import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.AbstractShieldEntity;
import io.redspace.ironsspellbooks.entity.spells.ShieldPart;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class FloodSlashProjectile
extends Projectile
implements AntiMagicSusceptible {
    private static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.assignValue(FloodSlashProjectile.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final double SPEED = 1.0;
    private static final int EXPIRE_TIME = 80;
    private final float maxRadius;
    public AABB oldBB;
    private int age;
    private float damage;
    private int startingEffectAmplifier = 1;
    private int effectDuration = 60;
    private float manaGain = 15.0f;
    public int animationTime;
    private List<Entity> victims;

    public FloodSlashProjectile(EntityType<? extends FloodSlashProjectile> entityType, Level level) {
        super(entityType, level);
        this.setRadius(0.6f);
        this.maxRadius = 3.0f;
        this.oldBB = this.getBoundingBox();
        this.victims = new ArrayList<Entity>();
        this.getY(true);
    }

    public FloodSlashProjectile(EntityType<? extends FloodSlashProjectile> entityType, Level levelIn, LivingEntity shooter) {
        this(entityType, levelIn);
        this.addAdditionalSaveData((Entity)shooter);
        this.setYRot(shooter.getYRot());
        this.setXRot(shooter.getXRot());
    }

    public FloodSlashProjectile(Level levelIn, LivingEntity shooter) {
        this((EntityType<? extends FloodSlashProjectile>)((EntityType)TravelopticsEntities.FLOOD_SLASH_PROJECTILE.get()), levelIn, shooter);
    }

    public void shoot(Vec3 rotation) {
        this.setDeltaMovement(rotation.x(1.0));
    }

    protected void defineSynchedData() {
        this.getEntityData().assignValue(DATA_RADIUS, (Object)Float.valueOf(0.5f));
    }

    public void setRadius(float newRadius) {
        if (newRadius <= this.maxRadius && !this.level().isClientSide) {
            this.getEntityData().packDirty(DATA_RADIUS, (Object)Float.valueOf(Mth.outFromOrigin((float)newRadius, (float)0.0f, (float)this.maxRadius)));
        }
    }

    public float getRadius() {
        return ((Float)this.getEntityData().packDirty(DATA_RADIUS)).floatValue();
    }

    public void refreshDimensions() {
        double d0 = this.getX();
        double d1 = this.getY();
        double d2 = this.getZ();
        super.refreshDimensions();
        this.setPos(d0, d1, d2);
    }

    public EntityDimensions setLastDeathLocation(Pose p_19721_) {
        this.getBoundingBox();
        return EntityDimensions.scalable((float)(this.getRadius() * 2.0f), (float)0.5f);
    }

    public void updateTutorialInventoryAction(EntityDataAccessor<?> p_19729_) {
        if (DATA_RADIUS.equals(p_19729_)) {
            this.refreshDimensions();
        }
        super.updateTutorialInventoryAction(p_19729_);
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public int getStartingEffectAmplifier() {
        return this.startingEffectAmplifier;
    }

    public void setStartingEffectAmplifier(int startingEffectAmplifier) {
        this.startingEffectAmplifier = startingEffectAmplifier;
    }

    public int getEffectDuration() {
        return this.effectDuration;
    }

    public void setEffectDuration(int effectDuration) {
        this.effectDuration = effectDuration;
    }

    public float getManaGain() {
        return this.manaGain;
    }

    public void setManaGain(float manaGain) {
        this.manaGain = manaGain;
    }

    public void lerpMotion() {
        super.lerpMotion();
        if (++this.age > 80) {
            this.discard();
            return;
        }
        this.oldBB = this.getBoundingBox();
        this.setRadius(this.getRadius() + 0.12f);
        if (!this.level().isClientSide) {
            HitResult hitresult = ProjectileUtil.getMobArrow((Entity)this, this::recreateFromPacket);
            if (hitresult.getType() == HitResult.Type.BLOCK) {
                this.dowseFire((BlockHitResult)hitresult);
            }
            for (Entity entity : this.level().getEntities((Entity)this, this.getBoundingBox()).stream().filter(target -> this.recreateFromPacket((Entity)target) && !this.victims.contains(target)).collect(Collectors.toSet())) {
                this.damageEntity(entity);
                if (!(entity instanceof ShieldPart) && !(entity instanceof AbstractShieldEntity)) continue;
                this.discard();
                return;
            }
        }
        this.setLevel(this.position().reverse(this.getDeltaMovement()));
        this.spawnParticles();
    }

    protected void dowseFire(BlockHitResult blockHitResult) {
        super.dowseFire(blockHitResult);
        this.discard();
    }

    private void damageEntity(Entity entity) {
        if (!this.victims.contains(entity)) {
            Entity magicData;
            LivingEntity owner;
            Entity entity2;
            DamageSources.applyDamage((Entity)entity, (float)this.damage, (DamageSource)((AbstractSpell)TravelopticsSpells.FLOOD_SLASH_SPELL.get()).getDamageSource((Entity)this, this.getOwner()));
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity)entity;
                livingEntity.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.WET.get(), 100, 1, false, false, false));
            }
            if ((entity2 = this.getOwner()) instanceof LivingEntity) {
                owner = (LivingEntity)entity2;
                magicData = MagicData.getPlayerMagicData((LivingEntity)owner);
                magicData.addMana(this.getManaGain());
            }
            if ((magicData = this.getOwner()) instanceof LivingEntity) {
                owner = (LivingEntity)magicData;
                int currentAmplifier = owner.recreateFromPacket((MobEffect)TravelopticsEffects.REPLENISH_EFFECT.get()) ? owner.getStandingEyeHeight((MobEffect)TravelopticsEffects.REPLENISH_EFFECT.get()).getAmplifier() + 1 : this.getStartingEffectAmplifier();
                owner.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.REPLENISH_EFFECT.get(), this.getEffectDuration(), currentAmplifier, false, false, false));
            }
            this.victims.add(entity);
        }
    }

    protected boolean recreateFromPacket(Entity entity) {
        return entity != this.getOwner() && super.recreateFromPacket(entity);
    }

    public void onAntiMagic(MagicData playerMagicData) {
        this.discard();
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.accept("Damage", this.damage);
        pCompound.accept("StartingEffectAmplifier", this.startingEffectAmplifier);
        pCompound.accept("EffectDuration", this.effectDuration);
        pCompound.accept("ManaGain", this.manaGain);
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.damage = pCompound.getFloat("Damage");
        this.startingEffectAmplifier = pCompound.copy("StartingEffectAmplifier");
        this.effectDuration = pCompound.copy("EffectDuration");
        this.manaGain = pCompound.getFloat("ManaGain");
    }

    public void spawnParticles() {
        if (this.level().isClientSide) {
            float width = (float)this.getBoundingBox().clip();
            float step = 0.25f;
            float radians = (float)Math.PI / 180 * this.getYRot();
            float speed = 0.1f;
            double particleTypeOneChance = 0.4;
            int i = 0;
            while ((float)i < width / step) {
                double x = this.getX();
                double y = this.getY();
                double z = this.getZ();
                double offset = step * ((float)i - width / step / 2.0f);
                double rotX = offset * Math.cos(radians);
                double rotZ = -offset * Math.sin(radians);
                double dx = Math.random() * (double)speed * 2.0 - (double)speed;
                double dy = Math.random() * (double)speed * 2.0 - (double)speed;
                double dz = Math.random() * (double)speed * 2.0 - (double)speed;
                if (Math.random() < particleTypeOneChance) {
                    this.level().addDestroyBlockEffect(TravelopticsParticleHelper.WATER_BUBBLE, true, x + rotX + dx, y + dy, z + rotZ + dz, dx, dy, dz);
                } else {
                    this.level().addDestroyBlockEffect((ParticleOptions)ParticleTypes.BUBBLE, true, x + rotX + dx, y + dy, z + rotZ + dz, dx, dy, dz);
                }
                ++i;
            }
        }
    }
}

