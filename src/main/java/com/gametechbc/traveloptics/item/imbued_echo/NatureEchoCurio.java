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

public class NatureEchoCurio
extends AdvancedEchoCurio {
    public NatureEchoCurio(Item.Properties properties) {
        super(properties, NatureEchoCurio.getAttributes());
    }

    @Override
    protected Map<AbstractSpell, AdvancedEchoCurio.SpellAttributes> getSpellAttributes() {
        LinkedHashMap<AbstractSpell, AdvancedEchoCurio.SpellAttributes> spells = new LinkedHashMap<AbstractSpell, AdvancedEchoCurio.SpellAttributes>();
        spells.put((AbstractSpell)SpellRegistry.ACID_ORB_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(7, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.BLIGHT_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(6, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.EARTHQUAKE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(7, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.FIREFLY_SWARM_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(12, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.GLUTTONY_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(5, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.OAKSKIN_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(4, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.POISON_ARROW_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(8, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.POISON_BREATH_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(9, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.POISON_SPLASH_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(8, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.ROOT_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(3, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.SPIDER_ASPECT_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(7, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.STOMP_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(15, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.AERIAL_COLLAPSE.get(), new AdvancedEchoCurio.SpellAttributes(1, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.STELE_CASCADE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(3, false, 0.1f, 1.0f));
        return spells;
    }

    @Override
    protected Component getUnassignedHoverText() {
        return Component.translatable((String)"item.traveloptics.random_imbue_curio.nature_unassigned.tooltip");
    }

    @Override
    protected Component getAssignedHoverText() {
        return Component.translatable((String)"item.traveloptics.random_imbue_curio.nature_assigned.tooltip").withStyle(ChatFormatting.DARK_GREEN);
    }

    @Override
    protected SoundEvent getAssignSound() {
        return (SoundEvent)SoundRegistry.NATURE_CAST.get();
    }

    private static Map<Attribute, AttributeModifier> getAttributes() {
        LinkedHashMap<Attribute, AttributeModifier> attributes = new LinkedHashMap<Attribute, AttributeModifier>();
        attributes.put((Attribute)AttributeRegistry.NATURE_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Nature spell power", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
        return attributes;
    }
}

