/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dev.shadowsoffire.attributeslib.api.ALObjects$Attributes
 *  io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder
 *  io.redspace.ironsspellbooks.util.ItemPropertiesHelper
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Tier
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.item.dev;

import com.gametechbc.traveloptics.data_manager.SpiritPointsManager;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import dev.shadowsoffire.attributeslib.api.ALObjects;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

public class BlazegripScimitarItem
extends MagicSwordItem {
    private static final String SWING_COUNTER_TAG = "SwingCounter";

    public BlazegripScimitarItem(SpellDataRegistryHolder[] imbuedSpells) {
        super(new Tier(){

            public int getUses() {
                return 2000;
            }

            public float getSpeed() {
                return 0.0f;
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
                return Ingredient.valueFromJson((ItemStack[])new ItemStack[]{new ItemStack((ItemLike)TravelopticsItems.PYRO_SPELLWEAVE_INGOT.get())});
            }
        }, 2.0, -2.8, imbuedSpells, Map.of((Attribute)ALObjects.Attributes.FIRE_DAMAGE.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Weapon Modifier", 0.05, AttributeModifier.Operation.ADDITION), (Attribute)AttributeRegistry.FIRE_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Weapon Modifier", 0.05, AttributeModifier.Operation.MULTIPLY_BASE)), ItemPropertiesHelper.hidden((int)1).requiredFeatures(TravelopticsItems.RARITY_LEGENDARY));
    }

    public boolean getDefaultAttributeModifiers(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player) {
            Player player = (Player)attacker;
            int swingCounter = this.getSwingCounter(stack);
            SpiritPointsManager.addSpiritPoints(stack, player, switch (swingCounter) {
                case 1 -> 10;
                case 2 -> 15;
                case 3 -> 20;
                default -> 10;
            });
            this.setSwingCounter(stack, swingCounter == 3 ? 1 : swingCounter + 1);
        }
        return super.getDefaultAttributeModifiers(stack, target, attacker);
    }

    private int getSwingCounter(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains(SWING_COUNTER_TAG)) {
            return stack.getTag().copy(SWING_COUNTER_TAG);
        }
        return 1;
    }

    private void setSwingCounter(ItemStack stack, int counter) {
        stack.getOrCreateTag().accept(SWING_COUNTER_TAG, counter);
    }

    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add((Component)Component.score((String)("\u2620 Spirit Points: " + SpiritPointsManager.getSpiritPoints(stack))));
        super.resolvePage(stack, world, tooltip, flag);
    }
}

