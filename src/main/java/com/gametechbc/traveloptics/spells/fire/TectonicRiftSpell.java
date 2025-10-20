/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModSounds
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.AnimationHolder
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 */
package com.gametechbc.traveloptics.spells.fire;

import com.gametechbc.traveloptics.entity.projectiles.TectonicRiftEntity;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.spells.TravelopticsSpellAnimations;
import com.gametechbc.traveloptics.util.TravelopticsTags;
import com.github.L_Ender.cataclysm.init.ModSounds;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

@AutoSpellConfig
public class TectonicRiftSpell
extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "tectonic_rift");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.LEGENDARY).setSchoolResource(SchoolRegistry.FIRE_RESOURCE).setMaxLevel(1).setCooldownSeconds(30.0).build();

    public TectonicRiftSpell() {
        this.manaCostPerLevel = 20;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 38;
        this.baseManaCost = 60;
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
        return TravelopticsSpellAnimations.TECTONIC_RIFT_CAST;
    }

    public AnimationHolder getCastFinishAnimation() {
        return AnimationHolder.pass();
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of((SoundEvent)ModSounds.MONSTROSITYGROWL.get());
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of((SoundEvent)ModSounds.MONSTROSITYSHOOT.get());
    }

    public boolean canBeInterrupted(@Nullable Player player) {
        return false;
    }

    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {
        return this.getCastTime(spellLevel);
    }

    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        List<Item> allowedWeapons = List.of((Item)TravelopticsItems.GAUNTLET_OF_EXTINCTION.get());
        if (allowedWeapons.contains(entity.getMainHandItem().setRepairCost())) {
            return true;
        }
        if (entity instanceof Player) {
            Player player = (Player)entity;
            if (!level.isClientSide()) {
                player.updateTutorialInventoryAction((Component)Component.translatable((String)"spell.traveloptics.tectonic_rift.warning").withStyle(ChatFormatting.RED), true);
            }
        }
        return false;
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        int radius = this.getRadius(spellLevel);
        int innerRadius = 5;
        int edgeMargin = 2;
        int depth = 3;
        float damage = this.getDamage(spellLevel, entity);
        TectonicRiftEntity tectonicRiftEntity = new TectonicRiftEntity(level);
        tectonicRiftEntity.addAdditionalSaveData((Entity)entity);
        tectonicRiftEntity.setDamage(damage);
        tectonicRiftEntity.setDuration(200);
        tectonicRiftEntity.setRadius(radius);
        tectonicRiftEntity.setCircular();
        tectonicRiftEntity.getRandomX(entity.position().y(0.0, -2.0, 0.0));
        level.addFreshEntity((Entity)tectonicRiftEntity);
        BlockPos entityPos = entity.blockPosition();
        Random random = new Random();
        for (int angle = 0; angle < 360; angle += 10) {
            double radians = Math.toRadians(angle);
            int xOffset = (int)((double)radius * Math.cos(radians));
            int zOffset = (int)((double)radius * Math.sin(radians));
            for (int step = innerRadius; step <= radius - edgeMargin; ++step) {
                for (int thickness = -1; thickness <= 0; ++thickness) {
                    int x = entityPos.setX() + (int)((double)step * Math.cos(radians)) + random.nextInt(3) - 1 + thickness;
                    int z = entityPos.getZ() + (int)((double)step * Math.sin(radians)) + random.nextInt(3) - 1 + thickness;
                    BlockPos targetPos = new BlockPos(x, entityPos.getY() - 1, z);
                    BlockState targetBlockState = level.getBlockState(targetPos);
                    if (!targetBlockState.emissiveRendering() || targetBlockState.isFaceSturdy((Block)Blocks.ORANGE_BED) || !(targetBlockState.getDestroySpeed((BlockGetter)level, targetPos) <= 4.0f) && !targetBlockState.isFaceSturdy(TravelopticsTags.TECTONIC_RIFT_DESTROYABLE)) continue;
                    for (int d = 0; d < depth - 1; ++d) {
                        level.destroyBlock(targetPos.south(d), Blocks.rebuildCache.defaultBlockState(), 3);
                    }
                    boolean isActive = random.nextInt(100) < 60;
                    BlockState primalMagmaBlockState = (BlockState)((BlockState)((Block)Blocks.ORANGE_BED).defaultBlockState().codec((Property)PrimalMagmaBlock.ACTIVE, (Comparable)Boolean.valueOf(isActive))).codec((Property)PrimalMagmaBlock.PERMANENT, (Comparable)Boolean.valueOf(true));
                    level.destroyBlock(targetPos.south(depth - 1), primalMagmaBlockState, 3);
                }
            }
        }
        for (int i = 0; i < 75; ++i) {
            double angle = random.nextDouble() * 2.0 * Math.PI;
            double distance = (double)innerRadius + (double)(radius - innerRadius) * random.nextDouble();
            double x = (double)entityPos.setX() + distance * Math.cos(angle);
            double z = (double)entityPos.getZ() + distance * Math.sin(angle);
            double y = entityPos.getY();
            MagicManager.spawnParticles((Level)level, (ParticleOptions)((ParticleOptions)ParticleTypes.LAVA), (double)x, (double)y, (double)z, (int)1, (double)0.0, (double)0.5, (double)0.0, (double)0.05, (boolean)true);
            MagicManager.spawnParticles((Level)level, (ParticleOptions)((ParticleOptions)ParticleTypes.LAVA), (double)x, (double)y, (double)z, (int)1, (double)0.0, (double)0.5, (double)0.0, (double)0.05, (boolean)true);
        }
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public int getRadius(int spellLevel) {
        return 10 + spellLevel * 2;
    }

    public int getDepth() {
        return 3;
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        return 5.0f + this.getSpellPower(spellLevel, (Entity)caster) * 3.0f;
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        int radius = this.getRadius(spellLevel);
        int depth = this.getDepth();
        float damage = this.getDamage(spellLevel, caster);
        return List.of(Component.selector((String)"ui.traveloptics.radius", (Object[])new Object[]{radius}), Component.selector((String)"ui.traveloptics.depth", (Object[])new Object[]{depth}), Component.selector((String)"ui.traveloptics.aoe_damage", (Object[])new Object[]{Utils.stringTruncation((double)damage, (int)2)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }
}

