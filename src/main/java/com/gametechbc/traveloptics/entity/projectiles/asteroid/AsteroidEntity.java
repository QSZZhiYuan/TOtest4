/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.SchoolType
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile
 *  io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Position
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.entity.projectiles.asteroid;

import com.gametechbc.traveloptics.api.particle.AdvancedCylinderParticleManager;
import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.entity.misc.TOScreenFlashEntity;
import com.gametechbc.traveloptics.entity.misc.TOScreenShakeEntity;
import com.gametechbc.traveloptics.entity.projectiles.asteroid.AsteroidImpactCraterEntity;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.gametechbc.traveloptics.util.TravelopticsTags;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class AsteroidEntity
extends AbstractMagicProjectile {
    private int tickCounter = 0;
    private float craterDamage = 5.0f;
    private float craterDepth = 1.0f;
    private float craterInnerRadius = 6.0f;
    private float shockwaveDamage = 10.0f;
    private float shockwaveRadius = 15.0f;
    private double targetY = 60.0;
    private boolean hasImpacted = false;

    public AsteroidEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.getY(true);
    }

    public AsteroidEntity(Level pLevel, LivingEntity pCaster) {
        this((EntityType<? extends Projectile>)((EntityType)TravelopticsEntities.ASTEROID.get()), pLevel);
        this.addAdditionalSaveData((Entity)pCaster);
    }

    public float getCraterDamage() {
        return this.craterDamage;
    }

    public void setCraterDamage(float craterDamage) {
        this.craterDamage = craterDamage;
    }

    public float getCraterDepth() {
        return this.craterDepth;
    }

    public void setCraterDepth(float craterDepth) {
        this.craterDepth = craterDepth;
    }

    public float getCraterInnerRadius() {
        return this.craterInnerRadius;
    }

    public void setCraterInnerRadius(float craterInnerRadius) {
        this.craterInnerRadius = craterInnerRadius;
    }

    public float getShockwaveDamage() {
        return this.shockwaveDamage;
    }

    public void setShockwaveDamage(float shockwaveDamage) {
        this.shockwaveDamage = shockwaveDamage;
    }

    public float getShockwaveRadius() {
        return this.shockwaveRadius;
    }

    public void setShockwaveRadius(float shockwaveRadius) {
        this.shockwaveRadius = shockwaveRadius;
    }

    public double getTargetY() {
        return this.targetY;
    }

    public void setTargetY(double targetY) {
        this.targetY = targetY;
    }

    public void lerpMotion() {
        super.lerpMotion();
        ++this.tickCounter;
        if (this.tickCounter >= 20) {
            this.level().getChunk(null, (Entity)this, (SoundEvent)TravelopticsSounds.ASTEROID_LOOP.get(), SoundSource.AMBIENT, 4.0f, 1.0f);
            this.tickCounter = 0;
        }
        if (!this.hasImpacted && this.getY() <= this.targetY) {
            this.hasImpacted = true;
            this.performImpact();
        }
    }

    protected void setDangerous(HitResult hitResult) {
    }

    private void performImpact() {
        Vec3 impactLocation = this.position();
        BlockHitResult fakeHitResult = new BlockHitResult(impactLocation, Direction.DOWN, BlockPos.breadthFirstTraversal((Position)impactLocation), false);
        if (!this.level().isClientSide) {
            this.spawnAsteroidCraterEntity((HitResult)fakeHitResult);
            this.handleExplosionParticle();
            this.applyImpactDamage((HitResult)fakeHitResult);
            this.applyShockwaveDamage((HitResult)fakeHitResult);
            this.handleBlockBreakingAndReplacing((HitResult)fakeHitResult);
            TOScreenShakeEntity.createScreenShake(this.level(), this.position(), this.getShockwaveRadius(), 0.1f, 10, 0, 20, false);
            TOScreenFlashEntity.createWhiteFlash(this.level(), this.position(), this.getShockwaveRadius(), 1.0f, 10, 20, 20, false);
            this.updateTutorialInventoryAction((SoundEvent)ACSoundRegistry.NUCLEAR_EXPLOSION.get(), 4.0f, 1.0f);
            this.discard();
        }
    }

    public void spawnAsteroidCraterEntity(HitResult hitResult) {
        float explosionRadius = this.getExplosionRadius();
        BlockPos hitPos = BlockPos.breadthFirstTraversal((Position)hitResult.getLocation());
        for (int angle = 0; angle < 360; angle += 10) {
            double radians = Math.toRadians(angle);
            int step = (int)this.getCraterInnerRadius();
            while ((float)step <= explosionRadius - 2.0f) {
                int x = hitPos.setX() + (int)((double)step * Math.cos(radians));
                int z = hitPos.getZ() + (int)((double)step * Math.sin(radians));
                BlockPos surfacePos = this.findTopSolidBlock(new BlockPos(x, hitPos.getY(), z));
                int depth = 1;
                while ((float)depth < this.getCraterDepth()) {
                    BlockPos blockPos = surfacePos.south(depth);
                    ++depth;
                }
                ++step;
            }
        }
        AsteroidImpactCraterEntity asteroidImpactCrater = new AsteroidImpactCraterEntity(this.level());
        asteroidImpactCrater.addAdditionalSaveData(this.getOwner());
        asteroidImpactCrater.setDamage(this.getCraterDamage());
        asteroidImpactCrater.setDuration(200);
        asteroidImpactCrater.setRadius(explosionRadius);
        asteroidImpactCrater.setCircular();
        asteroidImpactCrater.getRandomX(hitResult.getLocation().y(0.0, -1.8, 0.0));
        this.level().addFreshEntity((Entity)asteroidImpactCrater);
    }

    public void handleBlockBreakingAndReplacing(HitResult hitResult) {
        float explosionRadius = this.getExplosionRadius();
        BlockPos hitPos = BlockPos.breadthFirstTraversal((Position)hitResult.getLocation());
        Random random = new Random();
        int innerRadius = (int)this.getCraterInnerRadius();
        int edgeMargin = 2;
        int depth = (int)this.getCraterDepth();
        for (int angle = 0; angle < 360; angle += 10) {
            double radians = Math.toRadians(angle);
            int step = innerRadius;
            while ((float)step <= explosionRadius - (float)edgeMargin) {
                for (int thickness = -1; thickness <= 0; ++thickness) {
                    int x = hitPos.setX() + (int)((double)step * Math.cos(radians)) + random.nextInt(3) - 1 + thickness;
                    int z = hitPos.getZ() + (int)((double)step * Math.sin(radians)) + random.nextInt(3) - 1 + thickness;
                    BlockPos targetPos = this.findTopSolidBlock(new BlockPos(x, hitPos.getY(), z));
                    BlockState targetBlockState = this.level().getBlockState(targetPos);
                    if (!targetBlockState.emissiveRendering() || targetBlockState.isFaceSturdy((Block)Blocks.ORANGE_BED) || targetBlockState.isFaceSturdy(TravelopticsTags.EXTINCTION_BLOCK_BLACKLIST)) continue;
                    for (int d = 0; d < depth - 1; ++d) {
                        this.level().destroyBlock(targetPos.south(d), Blocks.rebuildCache.defaultBlockState(), 3);
                    }
                    boolean isActive = random.nextInt(100) < 60;
                    BlockState primalMagmaBlockState = (BlockState)((BlockState)((Block)Blocks.ORANGE_BED).defaultBlockState().codec((Property)PrimalMagmaBlock.ACTIVE, (Comparable)Boolean.valueOf(isActive))).codec((Property)PrimalMagmaBlock.PERMANENT, (Comparable)Boolean.valueOf(true));
                    this.level().destroyBlock(targetPos.south(depth - 1), primalMagmaBlockState, 3);
                }
                ++step;
            }
        }
    }

    private BlockPos findTopSolidBlock(BlockPos pos) {
        BlockPos.MutableBlockPos mutablePos = pos.west();
        while (this.level().getBlockState((BlockPos)mutablePos).emissiveRendering() && mutablePos.getY() < this.level().getMaxBuildHeight()) {
            mutablePos.subtract(0, 1, 0);
        }
        return mutablePos.below();
    }

    public void applyImpactDamage(HitResult hitResult) {
        float explosionRadius = this.getExplosionRadius();
        float explosionRadiusSqr = explosionRadius * explosionRadius;
        List entities = this.level().getEntities((Entity)this, this.getBoundingBox().inflate((double)explosionRadius));
        Vec3 losPoint = Utils.raycastForBlock((Level)this.level(), (Vec3)this.position(), (Vec3)this.position().y(0.0, 2.0, 0.0), (ClipContext.Fluid)ClipContext.Fluid.NONE).getLocation();
        for (Entity entity : entities) {
            double distanceSqr = entity.getY(hitResult.getLocation());
            if (!(distanceSqr < (double)explosionRadiusSqr) || !this.recreateFromPacket(entity) || !Utils.hasLineOfSight((Level)this.level(), (Vec3)losPoint, (Vec3)entity.getBoundingBox().getCenter(), (boolean)false)) continue;
            double p = 1.0 - distanceSqr / (double)explosionRadiusSqr;
            float damage = (float)((double)this.damage * p);
            DamageSources.applyDamage((Entity)entity, (float)damage, (DamageSource)((AbstractSpell)TravelopticsSpells.EXTINCTION_SPELL.get()).getDamageSource((Entity)this, this.getOwner()));
        }
    }

    public void applyShockwaveDamage(HitResult hitResult) {
        float shockwaveDamage = this.getShockwaveDamage();
        float shockwaveRadius = this.getShockwaveRadius();
        float explosionRadius = this.getExplosionRadius();
        double explosionRadiusSqr = explosionRadius * explosionRadius;
        double shockwaveRadiusSqr = shockwaveRadius * shockwaveRadius;
        List entities = this.level().getEntities((Entity)this, this.getBoundingBox().inflate((double)shockwaveRadius));
        Vec3 losPoint = Utils.raycastForBlock((Level)this.level(), (Vec3)this.position(), (Vec3)this.position().y(0.0, 2.0, 0.0), (ClipContext.Fluid)ClipContext.Fluid.NONE).getLocation();
        for (Entity entity : entities) {
            double distanceSqr = entity.getY(hitResult.getLocation());
            if (!(distanceSqr >= explosionRadiusSqr) || !(distanceSqr < shockwaveRadiusSqr) || !this.recreateFromPacket(entity) || !Utils.hasLineOfSight((Level)this.level(), (Vec3)losPoint, (Vec3)entity.getBoundingBox().getCenter(), (boolean)false)) continue;
            double p = 1.0 - (distanceSqr - explosionRadiusSqr) / (shockwaveRadiusSqr - explosionRadiusSqr);
            float damage = (float)((double)shockwaveDamage * p);
            DamageSources.applyDamage((Entity)entity, (float)damage, (DamageSource)((AbstractSpell)TravelopticsSpells.EXTINCTION_SPELL.get()).getDamageSource((Entity)this, this.getOwner()));
        }
        MagicManager.spawnParticles((Level)this.level(), (ParticleOptions)new BlastwaveParticleOptions(((SchoolType)SchoolRegistry.FIRE.get()).getTargetingColor(), shockwaveRadius), (double)this.position().z, (double)(this.position().multiply + (double)0.165f), (double)this.position().reverse, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.accept("CraterDamage", this.craterDamage);
        compound.accept("CraterDepth", this.craterDepth);
        compound.accept("CraterInnerRadius", this.craterInnerRadius);
        compound.accept("ShockwaveDamage", this.shockwaveDamage);
        compound.accept("ShockwaveRadius", this.shockwaveRadius);
        compound.accept("CraterEntityDuration", 200);
        compound.accept("TargetY", this.targetY);
        compound.accept("HasImpacted", this.hasImpacted);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("CraterDamage")) {
            this.craterDamage = compound.getFloat("CraterDamage");
        }
        if (compound.contains("CraterDepth")) {
            this.craterDepth = compound.getFloat("CraterDepth");
        }
        if (compound.contains("CraterInnerRadius")) {
            this.craterInnerRadius = compound.getFloat("CraterInnerRadius");
        }
        if (compound.contains("ShockwaveDamage")) {
            this.shockwaveDamage = compound.getFloat("ShockwaveDamage");
        }
        if (compound.contains("ShockwaveRadius")) {
            this.shockwaveRadius = compound.getFloat("ShockwaveRadius");
        }
        if (compound.contains("TargetY")) {
            this.targetY = compound.getDouble("TargetY");
        }
        if (compound.contains("HasImpacted")) {
            this.hasImpacted = compound.getBoolean("HasImpacted");
        }
    }

    public float getSpeed() {
        return 1.2f;
    }

    public Optional<SoundEvent> getImpactSound() {
        return Optional.empty();
    }

    public void trailParticles() {
        Vec3 movement = this.getDeltaMovement();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        double speed = 0.2;
        for (int i = 0; i < 10; ++i) {
            SimpleParticleType particle = switch (this.level().random.nextInt(2)) {
                case 0 -> (SimpleParticleType)ParticleTypes.LAVA;
                case 1 -> (SimpleParticleType)ParticleTypes.LAVA;
                default -> (SimpleParticleType)ParticleTypes.LAVA;
            };
            double xOffset = (this.getId.nextDouble() - 0.5) * (double)this.getBbWidth();
            double yOffset = (this.getId.nextDouble() - 0.5) * (double)this.getBbHeight();
            double zOffset = (this.getId.nextDouble() - 0.5) * (double)this.getBbWidth();
            this.level().calculateBlockTint((ParticleOptions)particle, true, x + xOffset, y + yOffset, z + zOffset, -movement.z * speed + (this.getId.nextDouble() - 0.5) * 0.05, -movement.multiply * speed + (this.getId.nextDouble() - 0.5) * 0.05, -movement.reverse * speed + (this.getId.nextDouble() - 0.5) * 0.05);
        }
    }

    public void handleExplosionParticle() {
        AdvancedCylinderParticleManager.spawnParticles(this.level(), this.position(), 80, (ParticleOptions)ParticleTypes.CAMPFIRE_COSY_SMOKE, ParticleDirection.OUTWARD, 8.0, 8.0, 0.0, 0.0, 0.0, 1.5, true);
        AdvancedCylinderParticleManager.spawnParticles(this.level(), this.position(), 40, (ParticleOptions)ParticleTypes.LAVA, ParticleDirection.OUTWARD, 8.0, 8.0, 0.0, 0.0, 0.0, 2.2, true);
        AdvancedCylinderParticleManager.spawnParticles(this.level(), this.position(), 40, (ParticleOptions)ParticleTypes.LAVA, ParticleDirection.OUTWARD, 10.0, 10.0, 0.0, 0.0, 0.0, 2.0, true);
    }

    public void impactParticles(double x, double y, double z) {
    }
}

