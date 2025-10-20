/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.client.particle.LightTrailParticle$OrbData
 *  com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.SchoolType
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.damage.SpellDamageSource
 *  io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible
 *  io.redspace.ironsspellbooks.entity.spells.AbstractShieldEntity
 *  io.redspace.ironsspellbooks.entity.spells.ShieldPart
 *  io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions
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
package com.gametechbc.traveloptics.entity.projectiles.void_slash;

import com.gametechbc.traveloptics.api.particle.AdvancedSphereParticleManager;
import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.entity.mobs.nightwarden_boss.NightwardenBossEntity;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsParticles;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import com.github.L_Ender.cataclysm.client.particle.LightTrailParticle;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.AbstractShieldEntity;
import io.redspace.ironsspellbooks.entity.spells.ShieldPart;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.core.particles.ParticleOptions;
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

public class VoidSlashProjectile
extends Projectile
implements AntiMagicSusceptible {
    private static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.assignValue(VoidSlashProjectile.class, (EntityDataSerializer)EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> DATA_CROSS = SynchedEntityData.assignValue(VoidSlashProjectile.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> MAGIC_DAMAGE_MODE = SynchedEntityData.assignValue(VoidSlashProjectile.class, (EntityDataSerializer)EntityDataSerializers.BOOLEAN);
    private float lifestealPercent = 1.0f;
    private static final double SPEED = 1.0;
    private static final int EXPIRE_TIME = 80;
    private final float maxRadius;
    public AABB oldBB;
    private int age;
    private float damage;
    public int animationTime;
    private List<Entity> victims;

    public VoidSlashProjectile(EntityType<? extends VoidSlashProjectile> entityType, Level level) {
        super(entityType, level);
        this.setRadius(0.6f);
        this.maxRadius = 3.0f;
        this.oldBB = this.getBoundingBox();
        this.victims = new ArrayList<Entity>();
        this.getY(true);
    }

    public VoidSlashProjectile(EntityType<? extends VoidSlashProjectile> entityType, Level levelIn, LivingEntity shooter) {
        this(entityType, levelIn);
        this.addAdditionalSaveData((Entity)shooter);
        this.setYRot(shooter.getYRot());
        this.setXRot(shooter.getXRot());
    }

    public VoidSlashProjectile(Level levelIn, LivingEntity shooter) {
        this((EntityType<? extends VoidSlashProjectile>)((EntityType)TravelopticsEntities.VOID_SLASH_PROJECTILE.get()), levelIn, shooter);
    }

    protected void defineSynchedData() {
        this.getEntityData().assignValue(DATA_RADIUS, (Object)Float.valueOf(0.5f));
        this.getEntityData().assignValue(DATA_CROSS, (Object)false);
        this.makeBoundingBox.assignValue(MAGIC_DAMAGE_MODE, (Object)false);
    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.accept("Damage", this.damage);
        tag.accept("Cross", ((Boolean)this.getEntityData().packDirty(DATA_CROSS)).booleanValue());
        tag.accept("MagicDamageMode", this.isMagicDamageMode());
        tag.accept("LifestealPercent", this.lifestealPercent);
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.damage = tag.getFloat("Damage");
        if (tag.contains("Cross")) {
            this.getEntityData().packDirty(DATA_CROSS, (Object)tag.getBoolean("Cross"));
        }
        if (tag.contains("MagicDamageMode")) {
            this.setMagicDamageMode(tag.getBoolean("MagicDamageMode"));
        }
        if (tag.contains("LifestealPercent")) {
            this.lifestealPercent = tag.getFloat("LifestealPercent");
        }
    }

    public void shoot(Vec3 rotation) {
        this.setDeltaMovement(rotation.x(1.0));
    }

    public boolean isCross() {
        return (Boolean)this.getEntityData().packDirty(DATA_CROSS);
    }

    public void setCross(boolean value) {
        this.getEntityData().packDirty(DATA_CROSS, (Object)value);
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setRadius(float newRadius) {
        if (newRadius <= this.maxRadius && !this.level().isClientSide) {
            this.getEntityData().packDirty(DATA_RADIUS, (Object)Float.valueOf(Mth.outFromOrigin((float)newRadius, (float)0.0f, (float)this.maxRadius)));
        }
    }

    public float getRadius() {
        return ((Float)this.getEntityData().packDirty(DATA_RADIUS)).floatValue();
    }

    public boolean isMagicDamageMode() {
        return (Boolean)this.makeBoundingBox.packDirty(MAGIC_DAMAGE_MODE);
    }

    public void setMagicDamageMode(boolean value) {
        this.makeBoundingBox.packDirty(MAGIC_DAMAGE_MODE, (Object)value);
    }

    public void setLifestealPercent(float percent) {
        this.lifestealPercent = percent;
    }

    public float getLifestealPercent() {
        return this.lifestealPercent;
    }

    public void refreshDimensions() {
        double d0 = this.getX();
        double d1 = this.getY();
        double d2 = this.getZ();
        super.refreshDimensions();
        this.setPos(d0, d1, d2);
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
        if (this.level().isClientSide) {
            float variation = 0.01f;
            float r = 0.6313726f + (this.getId.nextFloat() - 0.5f) * variation;
            float g = 0.3254902f + (this.getId.nextFloat() - 0.5f) * variation;
            float b = 0.99607843f + (this.getId.nextFloat() - 0.5f) * variation;
            this.level().addDestroyBlockEffect((ParticleOptions)new LightTrailParticle.OrbData(r, g, b, 2.5f, this.getBbHeight() / 2.0f, this.getId()), this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
        this.setLevel(this.position().reverse(this.getDeltaMovement()));
        this.spawnParticles();
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

    protected void dowseFire(BlockHitResult blockHitResult) {
        super.dowseFire(blockHitResult);
        if (!this.level().isClientSide && this.isCross()) {
            this.doCrossBehavior((Entity)this);
        } else {
            this.discard();
        }
    }

    private void damageEntity(Entity entity) {
        int effectDuration = 60;
        float splashDamage = this.damage * 0.5f;
        if (!this.victims.contains(entity)) {
            Entity entity2;
            SpellDamageSource source;
            if (this.isMagicDamageMode()) {
                Entity entity3 = this.getOwner();
                if (entity3 instanceof LivingEntity) {
                    LivingEntity owner = (LivingEntity)entity3;
                    source = this.damageSources().indirectMagic((Entity)this, (Entity)owner);
                } else {
                    source = this.damageSources().magic();
                }
            } else {
                source = ((AbstractSpell)TravelopticsSpells.GYRO_SLASH_SPELL.get()).getDamageSource((Entity)this, this.getOwner());
            }
            boolean hit = entity.sendSystemMessage((DamageSource)source, this.damage);
            this.victims.add(entity);
            if (hit && (entity2 = this.getOwner()) instanceof NightwardenBossEntity) {
                NightwardenBossEntity nightwarden = (NightwardenBossEntity)entity2;
                float lifestealAmount = this.damage * this.lifestealPercent;
                if (lifestealAmount > 0.0f) {
                    nightwarden.sendRidingJump(lifestealAmount);
                }
            }
            if (this.isCross()) {
                if (entity instanceof LivingEntity) {
                    LivingEntity victim = (LivingEntity)entity;
                    victim.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.VOID_COLLAPSE_EFFECT.get(), effectDuration, (int)splashDamage));
                }
                this.doCrossBehavior(entity);
            }
        }
    }

    private void doCrossBehavior(Entity triggerSource) {
        int effectDuration = 60;
        float splashDamage = this.damage * 0.5f;
        AABB explosionArea = triggerSource.getBoundingBox().inflate(4.0);
        for (LivingEntity nearby : this.level().getNearbyEntities(LivingEntity.class, explosionArea)) {
            Entity entity;
            SpellDamageSource source;
            if (nearby == this.getOwner() || this.victims.contains(nearby)) continue;
            if (this.isMagicDamageMode()) {
                Entity entity2 = this.getOwner();
                if (entity2 instanceof LivingEntity) {
                    LivingEntity owner = (LivingEntity)entity2;
                    source = this.damageSources().indirectMagic((Entity)this, (Entity)owner);
                } else {
                    source = this.damageSources().magic();
                }
            } else {
                source = ((AbstractSpell)TravelopticsSpells.GYRO_SLASH_SPELL.get()).getDamageSource((Entity)this, this.getOwner());
            }
            boolean hit = nearby.sendSystemMessage((DamageSource)source, splashDamage);
            if (hit && (entity = this.getOwner()) instanceof NightwardenBossEntity) {
                NightwardenBossEntity nightwarden = (NightwardenBossEntity)entity;
                float lifestealAmount = splashDamage * this.lifestealPercent;
                if (lifestealAmount > 0.0f) {
                    nightwarden.sendRidingJump(lifestealAmount);
                }
            }
            nearby.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.VOID_COLLAPSE_EFFECT.get(), effectDuration, (int)splashDamage));
            this.victims.add((Entity)nearby);
        }
        ScreenShake_Entity.ScreenShake((Level)this.level(), (Vec3)this.position(), (float)6.0f, (float)0.04f, (int)8, (int)10);
        MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)new BlastwaveParticleOptions(((SchoolType)SchoolRegistry.ENDER.get()).getTargetingColor(), 4.0f), (double)this.getX(), (double)this.getY(), (double)this.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)false);
        AdvancedSphereParticleManager.spawnParticles(this.level(), this.getX(), this.getY(), this.getZ(), 70, (ParticleOptions)TravelopticsParticles.PURPLE_STAR_OUTWARD_PARTICLE.get(), ParticleDirection.OUTWARD, 6.0, false);
        this.discard();
    }

    protected boolean recreateFromPacket(Entity entity) {
        return entity != this.getOwner() && super.recreateFromPacket(entity);
    }

    public void onAntiMagic(MagicData playerMagicData) {
        this.discard();
    }

    public void spawnParticles() {
        if (this.level().isClientSide) {
            float width = (float)this.getBoundingBox().clip();
            float step = 0.25f;
            float radians = (float)Math.PI / 180 * this.getYRot();
            float speed = 0.1f;
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
                this.level().addDestroyBlockEffect(TravelopticsParticleHelper.LIGHT_PURPLE_GLOWING_ENCHANT, x + rotX + dx, y + dy, z + rotZ + dz, dx, dy, dz);
                ++i;
            }
        }
    }
}

