/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.config.DefaultConfig
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.AutoSpellConfig
 *  io.redspace.ironsspellbooks.api.spells.CastSource
 *  io.redspace.ironsspellbooks.api.spells.CastType
 *  io.redspace.ironsspellbooks.api.spells.ICastData
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.api.util.Utils
 *  io.redspace.ironsspellbooks.entity.spells.AbstractConeProjectile
 *  io.redspace.ironsspellbooks.registries.SoundRegistry
 *  io.redspace.ironsspellbooks.spells.EntityCastData
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.spells.aqua;

import com.gametechbc.traveloptics.api.init.TravelopticsSchools;
import com.gametechbc.traveloptics.api.utils.TOGeneralUtils;
import com.gametechbc.traveloptics.entity.projectiles.BubbleSprayProjectile;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.ICastData;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.spells.AbstractConeProjectile;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.spells.EntityCastData;
import java.util.List;
import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

@AutoSpellConfig
public class BubbleSpraySpell
extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation("traveloptics", "bubble_spray");
    private final DefaultConfig defaultConfig = new DefaultConfig().setMinRarity(SpellRarity.COMMON).setSchoolResource(TravelopticsSchools.AQUA_RESOURCE).setMaxLevel(10).setCooldownSeconds(13.0).build();

    public BubbleSpraySpell() {
        this.manaCostPerLevel = 1;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 100;
        this.baseManaCost = 5;
    }

    public CastType getCastType() {
        return CastType.CONTINUOUS;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.empty();
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of((SoundEvent)SoundRegistry.POISON_BREATH_LOOP.get());
    }

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        EntityCastData entityCastData;
        ICastData iCastData;
        if (playerMagicData.isCasting() && playerMagicData.getCastingSpellId().equals(this.getSpellId()) && (iCastData = playerMagicData.getAdditionalCastData()) instanceof EntityCastData && (iCastData = (entityCastData = (EntityCastData)iCastData).getCastingEntity()) instanceof AbstractConeProjectile) {
            AbstractConeProjectile cone = (AbstractConeProjectile)iCastData;
            cone.setDealDamageActive();
        } else {
            BubbleSprayProjectile bubble = new BubbleSprayProjectile(world, entity);
            bubble.setLevel(entity.position().y(0.0, (double)entity.getEyeHeight() * 0.7, 0.0));
            bubble.setDamage(this.getDamage(spellLevel, entity));
            world.addFreshEntity((Entity)bubble);
            playerMagicData.setAdditionalCastData((ICastData)new EntityCastData((Entity)bubble));
            super.onCast(world, spellLevel, entity, castSource, playerMagicData);
        }
    }

    public float getDamage(int spellLevel, LivingEntity caster) {
        return 1.0f + this.getSpellPower(spellLevel, (Entity)caster) * 0.8f;
    }

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return TOGeneralUtils.buildAquaSpellInfo(3, true, Component.selector((String)"ui.traveloptics.damage", (Object[])new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), (int)2)}));
    }

    public boolean shouldAIStopCasting(int spellLevel, Mob mob, LivingEntity target) {
        return mob.getZ((Entity)target) > 120.0;
    }
}

