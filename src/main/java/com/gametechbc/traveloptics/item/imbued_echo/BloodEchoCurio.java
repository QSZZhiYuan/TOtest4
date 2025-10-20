/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.api.registry.SpellRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.registries.SoundRegistry
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.item.Item$Properties
 */
package com.gametechbc.traveloptics.item.imbued_echo;

import com.gametechbc.traveloptics.api.item.AdvancedEchoCurio;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;

public class BloodEchoCurio
extends AdvancedEchoCurio {
    public BloodEchoCurio(Item.Properties properties) {
        super(properties, BloodEchoCurio.getAttributes());
    }

    @Override
    protected Map<AbstractSpell, AdvancedEchoCurio.SpellAttributes> getSpellAttributes() {
        LinkedHashMap<AbstractSpell, AdvancedEchoCurio.SpellAttributes> spells = new LinkedHashMap<AbstractSpell, AdvancedEchoCurio.SpellAttributes>();
        spells.put((AbstractSpell)SpellRegistry.ACUPUNCTURE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(10, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.BLOOD_NEEDLES_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(15, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.BLOOD_SLASH_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(8, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.BLOOD_STEP_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(6, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.DEVOUR_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(12, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.HEARTSTOP_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(3, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.RAISE_DEAD_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(7, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.RAY_OF_SIPHONING_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(6, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.SACRIFICE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(8, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.WITHER_SKULL_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(7, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.BLOOD_HOWL_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(4, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.EEK_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(5, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.NOCTURNAL_SWARM_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(2, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.VIGOR_SIPHON_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(1, false, 0.1f, 1.0f));
        return spells;
    }

    @Override
    protected Component getUnassignedHoverText() {
        return Component.translatable((String)"item.traveloptics.random_imbue_curio.blood_unassigned.tooltip");
    }

    @Override
    protected Component getAssignedHoverText() {
        return Component.translatable((String)"item.traveloptics.random_imbue_curio.blood_assigned.tooltip").withStyle(ChatFormatting.DARK_GREEN);
    }

    @Override
    protected SoundEvent getAssignSound() {
        return (SoundEvent)SoundRegistry.BLOOD_CAST.get();
    }

    private static Map<Attribute, AttributeModifier> getAttributes() {
        LinkedHashMap<Attribute, AttributeModifier> attributes = new LinkedHashMap<Attribute, AttributeModifier>();
        attributes.put((Attribute)AttributeRegistry.BLOOD_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Blood spell power", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
        return attributes;
    }
}

