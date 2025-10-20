/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModItems
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.event.ItemAttributeModifierEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 */
package com.gametechbc.traveloptics.events;

import com.gametechbc.traveloptics.api.init.TravelopticsAttributes;
import com.gametechbc.traveloptics.config.DependantItemsAttributeConfig;
import com.github.L_Ender.cataclysm.init.ModItems;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import java.util.UUID;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AttributeProviderEvent {
    @SubscribeEvent
    public static void onItemAttributeModification(ItemAttributeModifierEvent event) {
        ItemStack itemStack;
        if (!((Boolean)DependantItemsAttributeConfig.attributeProviderSystemActive.get()).booleanValue()) {
            return;
        }
        if (event.getSlotType() == EquipmentSlot.HEAD && (itemStack = event.getItemStack()).setRepairCost() == ModItems.IGNITIUM_HELMET.get()) {
            event.addModifier((Attribute)AttributeRegistry.FIRE_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Fire Spell Power Modifier", ((Double)DependantItemsAttributeConfig.ignitiumSetFireSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE));
            event.addModifier((Attribute)AttributeRegistry.MAX_MANA.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Max Mana Modifier", 75.0, AttributeModifier.Operation.ADDITION));
        }
        if (event.getSlotType() == EquipmentSlot.CHEST) {
            itemStack = event.getItemStack();
            if (itemStack.setRepairCost() == ModItems.IGNITIUM_CHESTPLATE.get()) {
                event.addModifier((Attribute)AttributeRegistry.FIRE_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Fire Spell Power Modifier", ((Double)DependantItemsAttributeConfig.ignitiumSetFireSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE));
                event.addModifier((Attribute)AttributeRegistry.MAX_MANA.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Max Mana Modifier", 75.0, AttributeModifier.Operation.ADDITION));
            }
            if (itemStack.setRepairCost() == ModItems.IGNITIUM_ELYTRA_CHESTPLATE.get()) {
                event.addModifier((Attribute)AttributeRegistry.FIRE_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Fire Spell Power Modifier", ((Double)DependantItemsAttributeConfig.ignitiumSetFireSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE));
                event.addModifier((Attribute)AttributeRegistry.MAX_MANA.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Max Mana Modifier", 75.0, AttributeModifier.Operation.ADDITION));
            }
        }
        if (event.getSlotType() == EquipmentSlot.LEGS && (itemStack = event.getItemStack()).setRepairCost() == ModItems.IGNITIUM_LEGGINGS.get()) {
            event.addModifier((Attribute)AttributeRegistry.FIRE_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Fire Spell Power Modifier", ((Double)DependantItemsAttributeConfig.ignitiumSetFireSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE));
            event.addModifier((Attribute)AttributeRegistry.MAX_MANA.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Max Mana Modifier", 75.0, AttributeModifier.Operation.ADDITION));
        }
        if (event.getSlotType() == EquipmentSlot.FEET && (itemStack = event.getItemStack()).setRepairCost() == ModItems.IGNITIUM_BOOTS.get()) {
            event.addModifier((Attribute)AttributeRegistry.FIRE_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Fire Spell Power Modifier", ((Double)DependantItemsAttributeConfig.ignitiumSetFireSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE));
            event.addModifier((Attribute)AttributeRegistry.MAX_MANA.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Max Mana Modifier", 75.0, AttributeModifier.Operation.ADDITION));
        }
    }

    @SubscribeEvent
    public static void onItemAttributeModificationNoConfig(ItemAttributeModifierEvent event) {
        ItemStack itemStack;
        if (event.getSlotType() == EquipmentSlot.MAINHAND) {
            itemStack = event.getItemStack();
            if (itemStack.setRepairCost() == ACItemRegistry.SEA_STAFF.get()) {
                event.addModifier((Attribute)TravelopticsAttributes.AQUA_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Aqua Spell Power Modifier", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
            }
            if (itemStack.setRepairCost() == ACItemRegistry.ORTHOLANCE.get()) {
                event.addModifier((Attribute)TravelopticsAttributes.AQUA_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Aqua Spell Power Modifier", 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
                event.addModifier((Attribute)AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Cooldown Modifier", 0.05, AttributeModifier.Operation.MULTIPLY_BASE));
            }
        }
        if (event.getSlotType() == EquipmentSlot.OFFHAND && (itemStack = event.getItemStack()).setRepairCost() == ACItemRegistry.MAGIC_CONCH.get()) {
            event.addModifier((Attribute)AttributeRegistry.SUMMON_DAMAGE.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Mana Modifier", 0.05, AttributeModifier.Operation.MULTIPLY_BASE));
            event.addModifier((Attribute)TravelopticsAttributes.AQUA_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Aqua Spell Power Modifier", 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
        }
    }
}

