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

public class FireEchoCurio
extends AdvancedEchoCurio {
    public FireEchoCurio(Item.Properties properties) {
        super(properties, FireEchoCurio.getAttributes());
    }

    @Override
    protected Map<AbstractSpell, AdvancedEchoCurio.SpellAttributes> getSpellAttributes() {
        LinkedHashMap<AbstractSpell, AdvancedEchoCurio.SpellAttributes> spells = new LinkedHashMap<AbstractSpell, AdvancedEchoCurio.SpellAttributes>();
        spells.put((AbstractSpell)SpellRegistry.BLAZE_STORM_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(8, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.BURNING_DASH_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(6, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.FIRE_BREATH_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(7, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.FIREBALL_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(6, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.FIREBOLT_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(15, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.FLAMING_BARRAGE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(13, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.FLAMING_STRIKE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(5, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.HEAT_SURGE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(4, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.MAGMA_BOMB_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(10, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.SCORCH_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(8, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.WALL_OF_FIRE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(11, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.LAVA_BOMB_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(3, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.METEOR_STORM.get(), new AdvancedEchoCurio.SpellAttributes(1, false, 0.1f, 1.0f));
        return spells;
    }

    @Override
    protected Component getUnassignedHoverText() {
        return Component.translatable((String)"item.traveloptics.random_imbue_curio.fire_unassigned.tooltip");
    }

    @Override
    protected Component getAssignedHoverText() {
        return Component.translatable((String)"item.traveloptics.random_imbue_curio.fire_assigned.tooltip").withStyle(ChatFormatting.DARK_GREEN);
    }

    @Override
    protected SoundEvent getAssignSound() {
        return (SoundEvent)SoundRegistry.FIRE_CAST.get();
    }

    private static Map<Attribute, AttributeModifier> getAttributes() {
        LinkedHashMap<Attribute, AttributeModifier> attributes = new LinkedHashMap<Attribute, AttributeModifier>();
        attributes.put((Attribute)AttributeRegistry.FIRE_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Fire spell power", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
        return attributes;
    }
}

