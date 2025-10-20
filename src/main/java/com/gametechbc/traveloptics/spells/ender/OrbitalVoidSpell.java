/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Leviathan.Abyss_Orb_Entity
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
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.spells.ender;

import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Leviathan.Abyss_Orb_Entity;
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
import io.redspace.ironsspellbooks.api.util.Utils;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

@AutoSpellConfig
public class OrbitalVoidSpell
extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "orbital_void");
    private int baseEffectLevel = 1;
    private int effectLevelIncreaseRate = 1;
    private final double trackingRange = 20.0;
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.RARE).setSchoolResource(SchoolRegistry.ENDER_RESOURCE).setMaxLevel(5).setCooldownSeconds(25.0).build();

    public OrbitalVoidSpell() {
        this.baseManaCost = 40;
        this.manaCostPerLevel = 40;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 40;
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
        return SpellAnimations.CHARGE_ANIMATION;
    }

    public AnimationHolder getCastFinishAnimation() {
        return SpellAnimations.SPIT_FINISH_ANIMATION;
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(SoundEvents.WARDEN_SONIC_CHARGE);
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of((SoundEvent)ModSounds.LEVIATHAN_STUN_ROAR.get());
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
        if (!level.isClientSide()) {
            float getDamage = this.getDamage(spellLevel, entity);
            int orbCount = this.getOrbCount(spellLevel);
            int effectLevel = this.baseEffectLevel + (spellLevel - 1) * this.effectLevelIncreaseRate;
            if (entity instanceof Player) {
                Player player = (Player)entity;
                player.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.ORBITAL_VOID.get(), 200, effectLevel - 1));
            }
            double angleBetween = Math.PI * 2 / (double)orbCount;
            for (int i = 0; i < orbCount; ++i) {
                Projectile projectile;
                double angle = angleBetween * (double)i;
                double offsetX = Math.sin(angle) * 6.0;
                double offsetZ = Math.cos(angle) * 6.0;
                double motionScale = 3.0;
                Vec3 motion = new Vec3(offsetX, 0.0, offsetZ).multiply().x(motionScale);
                EntityType entityType = EntityType.tryCast((String)"cataclysm:abyss_orb").orElse(null);
                if (entityType == null || (projectile = (Projectile)entityType.tryCast(level)) == null) continue;
                projectile.moveTo(entity.getX(), entity.getY() + 1.5, entity.getZ(), 0.0f, 0.0f);
                projectile.setIsInPowderSnow(motion.x(), motion.y(), motion.z());
                LivingEntity target = this.findNearestTarget(level, entity);
                if (target != null && projectile instanceof Abyss_Orb_Entity) {
                    Abyss_Orb_Entity abyssOrb = (Abyss_Orb_Entity)projectile;
                    abyssOrb.setTracking(true);
                    abyssOrb.setDamage(getDamage);
                    abyssOrb.addAdditionalSaveData((Entity)entity);
                    try {
                        Field finalTargetField = Abyss_Orb_Entity.class.getDeclaredField("finalTarget");
                        finalTargetField.setAccessible(true);
                        finalTargetField.set(abyssOrb, target);
                    }
                    catch (IllegalAccessException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
                level.addFreshEntity((Entity)projectile);
                CameraShakeManager.addCameraShake((CameraShakeData)new CameraShakeData(28, entity.position(), 15.0f));
            }
        }
    }

    private LivingEntity findNearestTarget(Level level, LivingEntity caster) {
        AABB boundingBox = new AABB(caster.getX() - 20.0, caster.getY() - 20.0, caster.getZ() - 20.0, caster.getX() + 20.0, caster.getY() + 20.0, caster.getZ() + 20.0);
        List possibleTargets = level.getNearbyEntities(LivingEntity.class, boundingBox, entity -> {
            TamableAnimal tamable;
            if (entity == caster) {
                return false;
            }
            if (caster.isAlliedTo((Entity)entity)) {
                return false;
            }
            return !(entity instanceof TamableAnimal) || !(tamable = (TamableAnimal)entity).isTame() || tamable.getOwner() != caster;
        });
        if (possibleTargets.isEmpty()) {
            return null;
        }
        return possibleTargets.stream().min(Comparator.comparingDouble(arg_0 -> ((LivingEntity)caster).getZ(arg_0))).orElse(null);
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        return 2.5f + this.getSpellPower(spellLevel, (Entity)caster) * 2.0f;
    }

    private int getOrbCount(int spellLevel) {
        return spellLevel * 3;
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        float getDamage = this.getDamage(spellLevel, caster);
        float getOrbCount = this.getOrbCount(spellLevel);
        return List.of(Component.selector((String)"ui.traveloptics.damage", (Object[])new Object[]{Utils.stringTruncation((double)getDamage, (int)2)}), Component.selector((String)"ui.traveloptics.abyssal_orb_count", (Object[])new Object[]{Utils.stringTruncation((double)getOrbCount, (int)2)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }

    public boolean stopSoundOnCancel() {
        return true;
    }
}

