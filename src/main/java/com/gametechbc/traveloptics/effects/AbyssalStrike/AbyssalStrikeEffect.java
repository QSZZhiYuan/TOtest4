/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModEffect
 *  com.github.L_Ender.cataclysm.init.ModParticle
 *  dev.shadowsoffire.attributeslib.api.ALObjects$Attributes
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 */
package com.gametechbc.traveloptics.effects.AbyssalStrike;

import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModParticle;
import dev.shadowsoffire.attributeslib.api.ALObjects;
import java.util.Random;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class AbyssalStrikeEffect
extends MobEffect {
    private static final Random RANDOM = new Random();

    public AbyssalStrikeEffect() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.getAttributeModifierValue((Attribute)ALObjects.Attributes.CRIT_CHANCE.get(), "68078724-8653-42D5-A245-9D14A1F54685", 0.06, AttributeModifier.Operation.ADDITION);
        this.getAttributeModifierValue((Attribute)ALObjects.Attributes.CRIT_DAMAGE.get(), "68078724-8653-42D5-A245-9D14A1F54685", 0.04, AttributeModifier.Operation.ADDITION);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        super.applyEffectTick(entity, amplifier);
    }

    public boolean applyEffectTick(int duration, int amplifier) {
        return true;
    }

    public void onAttack(LivingEntity attacker, LivingEntity target, int amplifier) {
        if (attacker instanceof Player) {
            Player player = (Player)attacker;
            MobEffect abyssalCurseEffect = (MobEffect)ModEffect.EFFECTABYSSAL_CURSE.get();
            if (abyssalCurseEffect != null && (double)RANDOM.nextFloat() < 0.5) {
                if (!this.isAlly(player, target)) {
                    target.getStandingEyeHeight(new MobEffectInstance(abyssalCurseEffect, 200, amplifier));
                    this.spawnShockWaveParticle(target);
                    this.playSound(player, (SoundEvent)TravelopticsSounds.ABYSSAL_STRIKE_TRIGGER.get());
                }
                AABB areaOfEffect = player.getBoundingBox().inflate(8.0);
                player.level().getNearbyEntities(LivingEntity.class, areaOfEffect, entity -> entity != player && !this.isAlly(player, (LivingEntity)entity)).forEach(entity -> entity.getStandingEyeHeight(new MobEffectInstance(abyssalCurseEffect, 200, amplifier)));
            }
        }
    }

    private void spawnShockWaveParticle(LivingEntity entity) {
        Level level = entity.level();
        if (level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            serverLevel.getRandomSequence((ParticleOptions)((SimpleParticleType)ModParticle.SHOCK_WAVE.get()), entity.getX(), entity.getY() + 0.7, entity.getZ(), 1, 0.5, 0.5, 0.5, 0.0);
        }
    }

    private void playSound(Player player, SoundEvent soundEvent) {
        if (soundEvent != null && !player.level().isClientSide()) {
            player.updateTutorialInventoryAction(soundEvent, SoundSource.PLAYERS, 1.0f, 1.0f);
        }
    }

    private boolean isAlly(Player player, LivingEntity entity) {
        if (entity instanceof Player) {
            return player.isAlliedTo((Entity)((Player)entity));
        }
        if (entity instanceof TamableAnimal) {
            TamableAnimal tamable = (TamableAnimal)entity;
            return tamable.isTame() && tamable.getOwner() == player;
        }
        return false;
    }
}

