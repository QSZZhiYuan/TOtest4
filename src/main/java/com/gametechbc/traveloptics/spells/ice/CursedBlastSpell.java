/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity
 *  com.github.L_Ender.cataclysm.init.ModParticle
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.AnimationHolder
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.spells.ice;

import com.gametechbc.traveloptics.api.particle.ConeInwardParticleManager;
import com.gametechbc.traveloptics.api.spells.AbstractUniqueSpell;
import com.gametechbc.traveloptics.data_manager.SpiritPointsManager;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.spells.TravelopticsSpellAnimations;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import com.github.L_Ender.cataclysm.init.ModParticle;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

@AutoSpellConfig
public class CursedBlastSpell
extends AbstractUniqueSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "cursed_blast");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.LEGENDARY).setSchoolResource(SchoolRegistry.ICE_RESOURCE).setMaxLevel(1).setCooldownSeconds(3.0).build();

    public CursedBlastSpell() {
        this.manaCostPerLevel = 0;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 0;
        this.castTime = 28;
        this.baseManaCost = 200;
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
        return TravelopticsSpellAnimations.GUNBLADE_CHARGE;
    }

    public AnimationHolder getCastFinishAnimation() {
        return TravelopticsSpellAnimations.GUNBLADE_SHOOT;
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of((SoundEvent)TravelopticsSounds.CURSED_BLAST_CHARGE.get());
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.empty();
    }

    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        List<Item> allowedWeapons = List.of((Item)TravelopticsItems.CURSED_WRAITHBLADE.get(), (Item)TravelopticsItems.CURSED_WRAITHBLADE_LEVEL_ONE.get(), (Item)TravelopticsItems.CURSED_WRAITHBLADE_LEVEL_TWO.get(), (Item)TravelopticsItems.CURSED_WRAITHBLADE_LEVEL_THREE.get());
        if (allowedWeapons.contains(entity.getMainHandItem().setRepairCost())) {
            return true;
        }
        if (entity instanceof Player) {
            Player player = (Player)entity;
            if (!level.isClientSide()) {
                player.updateTutorialInventoryAction((Component)Component.translatable((String)"spell.traveloptics.cursed_blast.warning").withStyle(ChatFormatting.RED), true);
            }
        }
        return false;
    }

    public void onServerCastTick(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        ConeInwardParticleManager.spawnInwardConeParticles(level, entity, 2.0, 1, -0.35, 0.5, 0.8, (ParticleOptions)ModParticle.SMALL_CURSED_FLAME.get());
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        Entity target;
        float soulConversion = this.getSoulConversionRate(spellLevel, entity);
        int spiritPoints = SpiritPointsManager.getSpiritPoints(entity.getMainHandItem());
        HitResult hitResult = Utils.raycastForEntity((Level)level, (Entity)entity, (float)CursedBlastSpell.getRange(), (boolean)true, (float)0.15f);
        if (hitResult.getType() == HitResult.Type.ENTITY && (target = ((EntityHitResult)hitResult).getEntity()) instanceof LivingEntity) {
            LivingEntity livingTarget = (LivingEntity)target;
            float scaledDamage = this.getDamage() + (float)spiritPoints * soulConversion;
            DamageSources.applyDamage((Entity)livingTarget, (float)scaledDamage, (DamageSource)this.getDamageSource((Entity)entity));
            AABB area = new AABB(target.blockPosition()).inflate(3.0);
            List nearbyEntities = level.getNearbyEntities(LivingEntity.class, area, e -> e != entity && e != livingTarget);
            for (LivingEntity nearby : nearbyEntities) {
                DamageSources.applyDamage((Entity)nearby, (float)scaledDamage, (DamageSource)this.getDamageSource((Entity)entity));
                MagicManager.spawnParticles((Level)level, (ParticleOptions)ParticleTypes.SCULK_SOUL, (double)nearby.getX(), (double)nearby.getY(), (double)nearby.getZ(), (int)40, (double)0.0, (double)0.0, (double)0.0, (double)0.3, (boolean)false);
            }
        }
        float knockbackStrength = (float)spiritPoints / 100.0f * 0.5f;
        Vec3 knockbackDirection = entity.getLookAngle().reverse().x((double)knockbackStrength);
        entity.setIsInPowderSnow(knockbackDirection.z, 0.15, knockbackDirection.reverse);
        entity.getAddEntityPacket = true;
        this.doPostEffects(entity, SpiritPointsManager.getSpiritPoints(entity.getMainHandItem()), level);
        SpiritPointsManager.setSpiritPoints(entity.getMainHandItem(), 0);
        if (entity instanceof Player) {
            Player player = (Player)entity;
            player.updateTutorialInventoryAction((Component)Component.score((String)"\u2620 Soul Fragments: 0").withStyle(ChatFormatting.DARK_AQUA), true);
        }
        float distance = (float)hitResult.distanceTo((Entity)entity);
        Vec3 vec3 = entity.getLookAngle().multiply();
        int i = 0;
        while ((float)i < distance) {
            Vec3 vec32 = vec3.x((double)i).reverse(entity.getEyePosition());
            MagicManager.spawnParticles((Level)level, (ParticleOptions)TravelopticsParticleHelper.CURSED_BLAST, (double)vec32.z, (double)vec32.multiply, (double)vec32.reverse, (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)false);
            ++i;
        }
        MagicManager.spawnParticles((Level)level, (ParticleOptions)((ParticleOptions)ModParticle.PHANTOM_WING_FLAME.get()), (double)hitResult.getLocation().z, (double)hitResult.getLocation().multiply, (double)hitResult.getLocation().reverse, (int)50, (double)0.0, (double)0.0, (double)0.0, (double)0.2, (boolean)false);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private void doPostEffects(LivingEntity entity, int spiritPoints, Level level) {
        if (spiritPoints >= 200) {
            level.gameEvent(null, entity.blockPosition(), (SoundEvent)TravelopticsSounds.BLAST_STAGE_THREE.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
            ScreenShake_Entity.ScreenShake((Level)level, (Vec3)entity.position(), (float)30.0f, (float)0.05f, (int)10, (int)20);
        } else if (spiritPoints >= 100) {
            level.gameEvent(null, entity.blockPosition(), (SoundEvent)TravelopticsSounds.BLAST_STAGE_TWO.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
            ScreenShake_Entity.ScreenShake((Level)level, (Vec3)entity.position(), (float)20.0f, (float)0.035f, (int)10, (int)20);
        } else if (spiritPoints >= 0) {
            level.gameEvent(null, entity.blockPosition(), (SoundEvent)TravelopticsSounds.BLAST_STAGE_ONE.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
            ScreenShake_Entity.ScreenShake((Level)level, (Vec3)entity.position(), (float)15.0f, (float)0.02f, (int)10, (int)20);
        }
    }

    public static float getRange() {
        return 30.0f;
    }

    private float getDamage() {
        return 2.0f;
    }

    private float getSoulConversionRate(int spellLevel, LivingEntity caster) {
        return 0.35f + this.getSpellPower(spellLevel, (Entity)caster) * 0.15f;
    }

    private String getDamageText(int spellLevel, LivingEntity caster) {
        float soulConversion = this.getSoulConversionRate(spellLevel, caster);
        if (caster != null) {
            int spiritPoints = SpiritPointsManager.getSpiritPoints(caster.getMainHandItem());
            float baseDamage = this.getDamage();
            String spiritBoost = spiritPoints > 0 ? String.format(" (+%s)", Utils.stringTruncation((double)((float)spiritPoints * soulConversion), (int)1)) : "";
            return Utils.stringTruncation((double)(baseDamage + (float)spiritPoints * soulConversion), (int)1) + spiritBoost;
        }
        return "" + this.getSpellPower(spellLevel, (Entity)caster);
    }

    private String getSoulConversionPercentage(int spellLevel, LivingEntity caster) {
        return String.format("%.1f%%", Float.valueOf(this.getSoulConversionRate(spellLevel, caster) * 100.0f));
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.selector((String)"ui.traveloptics.soul_damage", (Object[])new Object[]{this.getDamageText(spellLevel, caster)}), Component.selector((String)"ui.traveloptics.soul_conversion_rate", (Object[])new Object[]{this.getSoulConversionPercentage(spellLevel, caster)}), Component.selector((String)"ui.traveloptics.range", (Object[])new Object[]{Utils.stringTruncation((double)CursedBlastSpell.getRange(), (int)2)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }
}

