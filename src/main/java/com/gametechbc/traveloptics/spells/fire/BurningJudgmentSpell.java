/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity
 *  com.github.L_Ender.cataclysm.init.ModSounds
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.AnimationHolder
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.phys.shapes.VoxelShape
 *  top.theillusivec4.curios.api.CuriosApi
 */
package com.gametechbc.traveloptics.spells.fire;

import com.gametechbc.traveloptics.api.particle.ParticleDirection;
import com.gametechbc.traveloptics.api.particle.SphereParticleManager;
import com.gametechbc.traveloptics.api.spells.AbstractUniqueSpell;
import com.gametechbc.traveloptics.entity.extended_projectiles.ExtendedFlameStrikeEntity;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.spells.TravelopticsSpellAnimations;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import com.github.L_Ender.cataclysm.init.ModSounds;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import top.theillusivec4.curios.api.CuriosApi;

@AutoSpellConfig
public class BurningJudgmentSpell
extends AbstractUniqueSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "burning_judgment");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.COMMON).setSchoolResource(SchoolRegistry.FIRE_RESOURCE).setMaxLevel(6).setCooldownSeconds(18.0).build();

    public BurningJudgmentSpell() {
        this.manaCostPerLevel = 40;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 50;
        this.baseManaCost = 40;
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
        return TravelopticsSpellAnimations.JUDGMENT_START;
    }

    public AnimationHolder getCastFinishAnimation() {
        return TravelopticsSpellAnimations.JUDGMENT_FINISH;
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of((SoundEvent)ModSounds.FLAME_BURST.get());
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of((SoundEvent)ModSounds.SWORD_STOMP.get());
    }

    public void onServerCastTick(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        SphereParticleManager.spawnParticles(level, (Entity)entity, 1, (ParticleOptions)ParticleTypes.FLAME, ParticleDirection.INWARD, 3.0);
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        int flameStrikes = (int)this.getFlameStrikeCount(spellLevel);
        int duration = (int)this.getDuration(spellLevel);
        int damage = (int)this.getDamage(spellLevel, caster);
        return List.of(Component.selector((String)"ui.traveloptics.damage", (Object[])new Object[]{Utils.stringTruncation((double)damage, (int)2)}), Component.selector((String)"ui.traveloptics.flame_strikes", (Object[])new Object[]{flameStrikes}), Component.selector((String)"ui.traveloptics.flame_strike_length", (Object[])new Object[]{Utils.timeFromTicks((float)duration, (int)2)}), Component.score((String)"\u00a79T.O Magic 'n Extras"));
    }

    public void onCast(Level level, int spellLevel, LivingEntity caster, CastSource castSource, MagicData playerMagicData) {
        int flameStrikes = (int)this.getFlameStrikeCount(spellLevel);
        int duration = (int)this.getDuration(spellLevel);
        double casterX = caster.getX();
        double casterZ = caster.getZ();
        double casterHeadY = caster.getY() + 1.0;
        int standOnYPos = Mth.outFromOrigin((double)caster.getY()) - 2;
        float yawRadians = (float)Math.toRadians(90.0f + caster.getYRot());
        boolean isSoulStrike = (double)caster.getHealth() <= (double)caster.getMaxHealth() * 0.5 || CuriosApi.getCuriosInventory((LivingEntity)caster).map(curios -> !curios.findCurios(item -> item != null && item.onDestroyed((Item)TravelopticsItems.AZURE_IGNITION_BRACELET.get())).isEmpty()).orElse(false) != false;
        for (int i = 0; i < flameStrikes; ++i) {
            double d2 = 2.25 * (double)(i + 1);
            int j2 = (int)(1.5f * (float)i);
            double casterXYaw = casterX + (double)Mth.randomBetween((float)yawRadians) * d2;
            double casterZYaw = casterZ + (double)Mth.outFromOrigin((float)yawRadians) * d2;
            this.spawnFlameStrike(casterXYaw, casterZYaw, standOnYPos, casterHeadY, yawRadians, duration, j2, j2, level, 1.0f, isSoulStrike, caster, spellLevel);
        }
        SphereParticleManager.spawnParticles(level, (Entity)caster, 50, (ParticleOptions)ParticleTypes.FLAME, ParticleDirection.OUTWARD, 3.0);
        ScreenShake_Entity.ScreenShake((Level)level, (Vec3)caster.position(), (float)12.0f, (float)0.03f, (int)10, (int)20);
        super.onCast(level, spellLevel, caster, castSource, playerMagicData);
    }

    private void spawnFlameStrike(double x, double z, double minY, double maxY, float rotation, int duration, int wait, int delay, Level level, float radius, boolean isSoul, LivingEntity caster, int spellLevel) {
        BlockPos pos = new BlockPos((int)x, (int)maxY, (int)z);
        boolean flag = false;
        double d0 = 0.0;
        int damage = (int)this.getDamage(spellLevel, caster);
        if (CuriosApi.getCuriosInventory((LivingEntity)caster).map(curios -> !curios.findCurios(item -> item != null && item.onDestroyed((Item)TravelopticsItems.AZURE_IGNITION_BRACELET.get())).isEmpty()).orElse(false).booleanValue()) {
            damage += 2;
        }
        do {
            BlockState blockState1;
            VoxelShape voxelShape;
            BlockPos pos1;
            BlockState blockState;
            if (!(blockState = level.getBlockState(pos1 = pos.below())).isFaceSturdy((BlockGetter)level, pos1, Direction.UP)) continue;
            if (!level.isEmptyBlock(pos) && !(voxelShape = (blockState1 = level.getBlockState(pos)).getCollisionShape((BlockGetter)level, pos)).calculateFace()) {
                d0 = voxelShape.optimize(Direction.Axis.Y);
            }
            flag = true;
            break;
        } while ((pos = pos.below()).getY() >= Mth.outFromOrigin((double)minY) - 1);
        if (flag) {
            level.addFreshEntity((Entity)new ExtendedFlameStrikeEntity(level, x, (double)pos.getY() + d0, z, rotation, duration, wait, delay, radius, damage, 0.0f, isSoul, caster));
        }
    }

    private float getFlameStrikeCount(int spellLevel) {
        return 1 + spellLevel;
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        return this.getSpellPower(spellLevel, (Entity)caster) * 3.0f;
    }

    private float getDuration(int spellLevel) {
        return spellLevel * 40;
    }
}

