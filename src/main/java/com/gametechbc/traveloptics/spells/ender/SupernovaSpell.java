/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.SpellAnimations
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.AnimationHolder
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.registries.SoundRegistry
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.Direction
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.spells.ender;

import com.gametechbc.traveloptics.api.spells.AbstractUniqueSpell;
import com.gametechbc.traveloptics.entity.projectiles.supernova.dying_star.DyingStarEntity;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.spells.TravelopticsSpellAnimations;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellAnimations;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

@AutoSpellConfig
public class SupernovaSpell
extends AbstractUniqueSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "supernova");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.LEGENDARY).setSchoolResource(SchoolRegistry.ENDER_RESOURCE).setMaxLevel(1).setCooldownSeconds(180.0).build();

    public SupernovaSpell() {
        this.manaCostPerLevel = 0;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 0;
        this.castTime = 100;
        this.baseManaCost = 600;
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
        return TravelopticsSpellAnimations.SUPERNOVA_CHARGE;
    }

    public AnimationHolder getCastFinishAnimation() {
        return SpellAnimations.FINISH_ANIMATION;
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of((SoundEvent)SoundRegistry.BLACK_HOLE_CHARGE.get());
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of((SoundEvent)SoundRegistry.BLACK_HOLE_CAST.get());
    }

    public boolean stopSoundOnCancel() {
        return true;
    }

    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {
        return this.getCastTime(spellLevel);
    }

    public boolean canBeInterrupted(@Nullable Player player) {
        return false;
    }

    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        List<Item> allowedWeapons = List.of((Item)TravelopticsItems.WAND_OF_FINAL_LIGHT.get());
        if (allowedWeapons.contains(entity.getMainHandItem().setRepairCost())) {
            return true;
        }
        if (entity instanceof Player) {
            Player player = (Player)entity;
            if (!level.isClientSide()) {
                player.updateTutorialInventoryAction((Component)Component.translatable((String)"spell.traveloptics.supernova.warning").withStyle(ChatFormatting.RED), true);
            }
        }
        return false;
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float dyingStarRadius = this.getDyingStarRadius(spellLevel, entity);
        float dyingStarDamage = this.getDyingStarDamage(spellLevel, entity);
        float supermassiveBlackholeRadius = this.getSupermassiveBlackholeRadius(spellLevel, entity);
        float supermassiveBlackholeDamage = this.getSupermassiveBlackholeDamage(spellLevel, entity);
        HitResult raycast = Utils.raycastForEntity((Level)level, (Entity)entity, (float)32.0f, (boolean)true);
        Vec3 center = raycast.getLocation();
        if (raycast instanceof BlockHitResult) {
            BlockHitResult blockHitResult = (BlockHitResult)raycast;
            Direction face = blockHitResult.getDirection();
            if (face == Direction.UP) {
                center = center.y(0.0, 10.0, 0.0);
            } else if (face == Direction.DOWN) {
                center = center.x(0.0, 6.0, 0.0);
            }
        }
        level.getChunk(null, center.z, center.multiply, center.reverse, (SoundEvent)SoundRegistry.BLACK_HOLE_CAST.get(), SoundSource.AMBIENT, 4.0f, 1.0f);
        DyingStarEntity dyingStar = new DyingStarEntity(level);
        dyingStar.addAdditionalSaveData((Entity)entity);
        dyingStar.setRadius(dyingStarRadius);
        dyingStar.setDamage(dyingStarDamage);
        dyingStar.setBlackholeDuration(640);
        dyingStar.setBlackholeRadius(supermassiveBlackholeRadius);
        dyingStar.setBlackholeDamage(supermassiveBlackholeDamage);
        dyingStar.setPos(center.z, center.multiply, center.reverse);
        level.addFreshEntity((Entity)dyingStar);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDyingStarDamage(int spellLevel, LivingEntity entity) {
        return 60.0f + this.getSpellPower(spellLevel, (Entity)entity) * 20.0f;
    }

    private float getDyingStarRadius(int spellLevel, LivingEntity entity) {
        return 20.0f + this.getSpellPower(spellLevel, (Entity)entity) * 2.0f;
    }

    private float getSupermassiveBlackholeDamage(int spellLevel, LivingEntity entity) {
        return this.getSpellPower(spellLevel, (Entity)entity) * 2.25f;
    }

    private float getSupermassiveBlackholeRadius(int spellLevel, LivingEntity entity) {
        return 28.0f + this.getSpellPower(spellLevel, (Entity)entity) * 4.0f;
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.selector((String)"ui.traveloptics.dying_star_aoe_damage", (Object[])new Object[]{Utils.stringTruncation((double)this.getDyingStarDamage(spellLevel, caster), (int)1)}), Component.selector((String)"ui.traveloptics.dying_star_radius", (Object[])new Object[]{Utils.stringTruncation((double)this.getDyingStarRadius(spellLevel, caster), (int)1)}), Component.selector((String)"ui.traveloptics.supermassive_blackhole_aoe_damage", (Object[])new Object[]{Utils.stringTruncation((double)this.getSupermassiveBlackholeDamage(spellLevel, caster), (int)1)}), Component.selector((String)"ui.traveloptics.supermassive_blackhole_radius", (Object[])new Object[]{Utils.stringTruncation((double)this.getSupermassiveBlackholeRadius(spellLevel, caster), (int)1)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }
}

