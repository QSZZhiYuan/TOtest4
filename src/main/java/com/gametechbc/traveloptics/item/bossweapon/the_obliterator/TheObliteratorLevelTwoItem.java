/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Leviathan.Abyss_Blast_Portal_Entity
 *  com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity
 *  com.github.L_Ender.cataclysm.init.ModEffect
 *  com.github.L_Ender.cataclysm.init.ModSounds
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder
 *  io.redspace.ironsspellbooks.registries.SoundRegistry
 *  io.redspace.ironsspellbooks.util.ItemPropertiesHelper
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
 *  net.minecraft.network.chat.Component
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.Mth
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Tier
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.item.UseAnim
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.gametechbc.traveloptics.item.bossweapon.the_obliterator;

import com.gametechbc.traveloptics.config.WeaponConfig;
import com.gametechbc.traveloptics.entity.item.the_obliterator.evo_two.TheObliteratorLevelTwoRenderer;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.item.UnbreakableGeoMagicSword;
import com.gametechbc.traveloptics.util.TravelopticsParticleHelper;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Leviathan.Abyss_Blast_Portal_Entity;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModSounds;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TheObliteratorLevelTwoItem
extends UnbreakableGeoMagicSword {
    private static ItemDisplayContext transformType;

    public TheObliteratorLevelTwoItem(SpellDataRegistryHolder[] imbuedSpells) {
        super(new Tier(){

            public int getUses() {
                return (Integer)WeaponConfig.theObliteratorDurability.get();
            }

            public float getSpeed() {
                return 2.0f;
            }

            public float getAttackDamageBonus() {
                return 0.0f;
            }

            public int getLevel() {
                return 1;
            }

            public int getEnchantmentValue() {
                return 20;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.valueFromJson((ItemStack[])new ItemStack[]{new ItemStack((ItemLike)TravelopticsItems.ABYSSAL_SPELLWEAVE_INGOT.get())});
            }
        }, (Double)WeaponConfig.theObliteratorDamage.get(), (Double)WeaponConfig.theObliteratorAttackSpeed.get(), imbuedSpells, Map.of((Attribute)AttributeRegistry.ENDER_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("8ff095c4-a26d-4d28-bc06-1a908b3577aa"), "Weapon Modifier", ((Double)WeaponConfig.theObliteratorEnderSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)AttributeRegistry.ELDRITCH_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("8ff095c4-a26d-4d28-bc06-1a908b3577aa"), "Weapon Modifier", ((Double)WeaponConfig.theObliteratorEldritchSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE)), ItemPropertiesHelper.hidden((int)1).requiredFeatures(TravelopticsItems.RARITY_ABYSSAL));
    }

    public boolean getDefaultAttributeModifiers(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        MobEffect abyssalCurseEffect;
        if (target != null && attacker.getRandom().nextFloat() < 0.45f && (abyssalCurseEffect = (MobEffect)ModEffect.EFFECTABYSSAL_CURSE.get()) != null) {
            target.getStandingEyeHeight(new MobEffectInstance(abyssalCurseEffect, 100, 1));
        }
        return super.getDefaultAttributeModifiers(stack, target, attacker);
    }

    public UseAnim getEnchantmentValue(ItemStack p_77661_1_) {
        return UseAnim.SPEAR;
    }

    public int getUseDuration(ItemStack p_77626_1_) {
        return 72000;
    }

    public void getDefaultAttributeModifiers(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (entity instanceof Player) {
            Player player = (Player)entity;
            int chargeTime = this.getUseDuration(stack) - timeLeft;
            if (chargeTime >= 40) {
                this.handleBlastPortalSpawns(level, (LivingEntity)player);
                if (!level.isClientSide) {
                    player.getCooldowns().addCooldown((Item)this, 240);
                }
            }
        }
    }

    public void canBeHurtBy(Level level, LivingEntity caster, ItemStack stack, int count) {
        int chargeTime = this.getUseDuration(stack) - count;
        if (chargeTime == 14) {
            this.spawnChargeParticles(level, caster, 2.0f);
        }
        if (chargeTime == 28) {
            this.spawnChargeParticles(level, caster, 3.5f);
        }
        if (chargeTime == 40) {
            this.spawnChargeParticles(level, caster, 5.0f);
            caster.updateTutorialInventoryAction((SoundEvent)ModSounds.LEVIATHAN_STUN_ROAR.get(), 0.9f, 1.0f);
        }
    }

    private void handleBlastPortalSpawns(Level level, LivingEntity caster) {
        ScreenShake_Entity.ScreenShake((Level)level, (Vec3)caster.position(), (float)30.0f, (float)0.1f, (int)0, (int)30);
        level.getChunk(null, caster.getX(), caster.getY(), caster.getZ(), (SoundEvent)SoundRegistry.EARTHQUAKE_IMPACT.get(), SoundSource.BLOCKS, 1.2f, 1.0f);
        level.getChunk(null, caster.getX(), caster.getY(), caster.getZ(), (SoundEvent)TravelopticsSounds.REFINED_ABYSS_BLAST_PORTAL.get(), SoundSource.BLOCKS, 1.5f, 1.0f);
        this.spawnAbyssBlastPortals(level, caster);
    }

    private void spawnAbyssBlastPortals(Level level, LivingEntity caster) {
        double radius = 5.0;
        int portalCount = 4;
        double angleStep = 360.0 / (double)portalCount;
        for (int i = 0; i < portalCount; ++i) {
            double angle = Math.toRadians((double)i * angleStep);
            double offsetX = radius * Math.cos(angle);
            double offsetZ = radius * Math.sin(angle);
            Vec3 portalPos = caster.position().y(offsetX, 0.0, offsetZ);
            if (level.isClientSide) continue;
            Abyss_Blast_Portal_Entity portal = new Abyss_Blast_Portal_Entity(level, portalPos.z, caster.getY(), portalPos.reverse, caster.getYRot(), 0, (float)(15.0 * (Double)WeaponConfig.theObliteratorOblivionRayDamageMultiplier.get()), 0.0f, caster);
            level.addFreshEntity((Entity)portal);
        }
    }

    private void spawnChargeParticles(Level level, LivingEntity caster, float radius) {
        if (level.isClientSide) {
            for (int j = 0; j < 70; ++j) {
                float angle = (float)(Math.random() * 2.0 * Math.PI);
                double distance = Math.sqrt(Math.random()) * (double)radius;
                double extraX = caster.getX() + distance * (double)Mth.randomBetween((float)angle);
                double extraY = caster.getY() + (double)0.3f;
                double extraZ = caster.getZ() + distance * (double)Mth.outFromOrigin((float)angle);
                level.addDestroyBlockEffect(TravelopticsParticleHelper.ABYSS_SPIKE_PARTICLE, extraX, extraY, extraZ, 0.0, level.random.nextGaussian() * 0.04, 0.0);
            }
        }
    }

    public InteractionResultHolder<ItemStack> resolvePage(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getStandingEyeHeight(hand);
        player.vehicleCanSprint(hand);
        return InteractionResultHolder.consume((Object)itemstack);
    }

    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }

    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add((Component)Component.score((String)""));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.the_obliterator.tooltip").withStyle(ChatFormatting.GREEN));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.the_obliterator.tooltip0"));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.the_obliterator.tooltip3"));
        if (Screen.hasShiftDown()) {
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.the_obliterator.evo_one.active.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.the_obliterator.evo_two.active.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.the_obliterator.evo_three.inactive.tooltip"));
        } else {
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution.stars_two.tooltip"));
            tooltip.add((Component)Component.score((String)"\u00a78[Hold Shift for more info]"));
        }
        tooltip.add((Component)Component.score((String)""));
        super.resolvePage(stack, world, tooltip, flag);
    }

    public void getTransformType(ItemDisplayContext type) {
        transformType = type;
    }

    @Override
    @OnlyIn(value=Dist.CLIENT)
    protected BlockEntityWithoutLevelRenderer getRenderer() {
        return new TheObliteratorLevelTwoRenderer();
    }
}

