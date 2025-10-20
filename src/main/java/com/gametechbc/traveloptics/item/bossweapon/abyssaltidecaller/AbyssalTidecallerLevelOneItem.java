/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModEffect
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder
 *  io.redspace.ironsspellbooks.util.ItemPropertiesHelper
 *  net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Tier
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.gametechbc.traveloptics.item.bossweapon.abyssaltidecaller;

import com.gametechbc.traveloptics.config.WeaponConfig;
import com.gametechbc.traveloptics.entity.item.abyssal_tidecaller.evo_one.AbyssalTidecallerLevelOneRenderer;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.item.UnbreakableGeoMagicSword;
import com.gametechbc.traveloptics.item.bossweapon.abyssaltidecaller.AbyssalTidecallerAbility;
import com.github.L_Ender.cataclysm.init.ModEffect;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AbyssalTidecallerLevelOneItem
extends UnbreakableGeoMagicSword {
    private static ItemDisplayContext transformType;
    private static final AbyssalTidecallerAbility ABILITY;

    public AbyssalTidecallerLevelOneItem(SpellDataRegistryHolder[] imbuedSpells) {
        super(new Tier(){

            public int getUses() {
                return (Integer)WeaponConfig.abyssalTidecallerDurability.get();
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
        }, (Double)WeaponConfig.abyssalTidecallerDamage.get(), (Double)WeaponConfig.abyssalTidecallerAttackSpeed.get(), imbuedSpells, Map.of((Attribute)AttributeRegistry.ELDRITCH_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("57f9a563-4e2c-4c4b-871e-c49c38edf14f"), "Weapon Modifier", ((Double)WeaponConfig.abyssalTidecallerEldritchSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)AttributeRegistry.ENDER_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("57f9a563-4e2c-4c4b-871e-c49c38edf14f"), "Weapon Modifier", ((Double)WeaponConfig.abyssalTidecallerEnderSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE)), ItemPropertiesHelper.hidden((int)1).requiredFeatures(TravelopticsItems.RARITY_ABYSSAL));
    }

    public boolean getDefaultAttributeModifiers(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        MobEffect abyssalCurseEffect;
        if (target != null && attacker.getRandom().nextFloat() < 0.6f && (abyssalCurseEffect = (MobEffect)ModEffect.EFFECTABYSSAL_CURSE.get()) != null) {
            int currentAmplifier;
            MobEffectInstance currentEffect = target.getStandingEyeHeight(abyssalCurseEffect);
            int n = currentAmplifier = currentEffect != null ? currentEffect.getAmplifier() : -1;
            if (currentAmplifier >= 0) {
                int newAmplifier = Math.min(currentAmplifier + 1, 2);
                target.getStandingEyeHeight(new MobEffectInstance(abyssalCurseEffect, 100, newAmplifier));
            } else {
                target.getStandingEyeHeight(new MobEffectInstance(abyssalCurseEffect, 100, 0));
            }
        }
        return super.getDefaultAttributeModifiers(stack, target, attacker);
    }

    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        ABILITY.appendHoverText(stack, world, tooltip, flag, 1);
        super.resolvePage(stack, world, tooltip, flag);
    }

    public void getTransformType(ItemDisplayContext type) {
        transformType = type;
    }

    @Override
    @OnlyIn(value=Dist.CLIENT)
    protected BlockEntityWithoutLevelRenderer getRenderer() {
        return new AbyssalTidecallerLevelOneRenderer();
    }

    static {
        ABILITY = new AbyssalTidecallerAbility();
    }
}

