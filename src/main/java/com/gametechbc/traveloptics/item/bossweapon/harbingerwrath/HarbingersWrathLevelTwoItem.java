/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModEffect
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder
 *  io.redspace.ironsspellbooks.util.ItemPropertiesHelper
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Tier
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.item.bossweapon.harbingerwrath;

import com.gametechbc.traveloptics.api.item.weapons.MagicClaymoreItem;
import com.gametechbc.traveloptics.config.WeaponConfig;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.github.L_Ender.cataclysm.init.ModEffect;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

public class HarbingersWrathLevelTwoItem
extends MagicClaymoreItem {
    public HarbingersWrathLevelTwoItem(SpellDataRegistryHolder[] imbuedSpells) {
        super(new Tier(){

            public int getUses() {
                return (Integer)WeaponConfig.harbingersWrathDurability.get();
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
                return Ingredient.valueFromJson((ItemStack[])new ItemStack[]{new ItemStack((ItemLike)TravelopticsItems.LIGHTNING_SPELLWEAVE_INGOT.get())});
            }
        }, (Double)WeaponConfig.harbingersWrathDamage.get(), (Double)WeaponConfig.harbingersWrathAttackSpeed.get(), imbuedSpells, Map.of((Attribute)AttributeRegistry.LIGHTNING_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("3c5c8036-eaa9-401b-8c5a-01c8251d1db1"), "Weapon Modifier", ((Double)WeaponConfig.harbingersWrathLightningSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE)), ItemPropertiesHelper.hidden((int)1).requiredFeatures(TravelopticsItems.RARITY_MECHANIZED));
    }

    public boolean canBeDepleted() {
        return (Boolean)WeaponConfig.weaponsShouldBeBreakable.get();
    }

    public boolean isDamageable(ItemStack stack) {
        return (Boolean)WeaponConfig.weaponsShouldBeBreakable.get();
    }

    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    public boolean getDefaultAttributeModifiers(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        MobEffectInstance currentEffect;
        MobEffect stunEffect;
        if (target != null && attacker.getRandom().nextFloat() < 0.25f && (stunEffect = (MobEffect)ModEffect.EFFECTSTUN.get()) != null && (currentEffect = target.getStandingEyeHeight(stunEffect)) == null) {
            target.getStandingEyeHeight(new MobEffectInstance(stunEffect, 70, 0));
        }
        return super.getDefaultAttributeModifiers(stack, target, attacker);
    }

    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add((Component)Component.score((String)"\u00a7ePassive Ability: Mini EM-Pulse"));
        tooltip.add((Component)Component.translatable((String)"ui.traveloptics.weapon.evolution_two").withStyle(ChatFormatting.YELLOW));
        if (Screen.hasShiftDown()) {
            tooltip.add((Component)Component.score((String)"\u00a7fSuccessful hits on enemies has \u00a7b25% \u00a7fchance to stun them with a mini EM-Pulse, for \u00a7b3.5 sec."));
            tooltip.add((Component)Component.score((String)"\u00a7fAdditional procs refresh the duration."));
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.score((String)"\u00a7eEvolution Benefits:"));
            tooltip.add((Component)Component.score((String)"\u00a7e\u2605 \u00a7f[Evo 1] Increases effect duration \u00a7b+1.5 sec."));
            tooltip.add((Component)Component.score((String)"\u00a7e\u2605 \u00a7f[Evo 2] Increases proc chance \u00a7b+10%."));
            tooltip.add((Component)Component.score((String)"\u00a77\u2606 [Evo 3] EM-Pulse is now AoE, But its proc chance & duration is reduced \u00a74-5% \u00a77| \u00a74-0.5 sec."));
        } else {
            tooltip.add((Component)Component.score((String)"\u00a7e\u2605\u2605\u00a77\u2606"));
            tooltip.add((Component)Component.score((String)"\u00a78[Hold Shift for more info]"));
        }
        super.resolvePage(stack, world, tooltip, flag);
    }
}

