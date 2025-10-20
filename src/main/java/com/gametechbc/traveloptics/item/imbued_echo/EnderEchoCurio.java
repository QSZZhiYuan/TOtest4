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

public class EnderEchoCurio
extends AdvancedEchoCurio {
    public EnderEchoCurio(Item.Properties properties) {
        super(properties, EnderEchoCurio.getAttributes());
    }

    @Override
    protected Map<AbstractSpell, AdvancedEchoCurio.SpellAttributes> getSpellAttributes() {
        LinkedHashMap<AbstractSpell, AdvancedEchoCurio.SpellAttributes> spells = new LinkedHashMap<AbstractSpell, AdvancedEchoCurio.SpellAttributes>();
        spells.put((AbstractSpell)SpellRegistry.BLACK_HOLE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(1, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.COUNTERSPELL_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(12, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.DRAGON_BREATH_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(13, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.ECHOING_STRIKES_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(4, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.EVASION_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(5, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.MAGIC_ARROW_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(9, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.MAGIC_MISSILE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(8, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.PORTAL_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(2, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.RECALL_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(4, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.STARFALL_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(15, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.SUMMON_ENDER_CHEST_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(6, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.TELEPORT_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(4, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.CURSED_MINEFIELD_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(6, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.ORBITAL_VOID_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(2, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.VOID_ERUPTION_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(4, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.VORTEX_PUNCH_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(1, false, 0.1f, 1.0f));
        return spells;
    }

    @Override
    protected Component getUnassignedHoverText() {
        return Component.translatable((String)"item.traveloptics.random_imbue_curio.ender_unassigned.tooltip");
    }

    @Override
    protected Component getAssignedHoverText() {
        return Component.translatable((String)"item.traveloptics.random_imbue_curio.ender_assigned.tooltip").withStyle(ChatFormatting.DARK_GREEN);
    }

    @Override
    protected SoundEvent getAssignSound() {
        return (SoundEvent)SoundRegistry.ENDER_CAST.get();
    }

    private static Map<Attribute, AttributeModifier> getAttributes() {
        LinkedHashMap<Attribute, AttributeModifier> attributes = new LinkedHashMap<Attribute, AttributeModifier>();
        attributes.put((Attribute)AttributeRegistry.ENDER_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Ender spell power", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
        return attributes;
    }
}

