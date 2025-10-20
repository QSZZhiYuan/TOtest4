/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.item.Item$Properties
 */
package com.gametechbc.traveloptics.item.imbued_echo;

import com.gametechbc.traveloptics.api.init.TravelopticsAttributes;
import com.gametechbc.traveloptics.api.item.AdvancedEchoCurio;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;

public class AquaEchoCurio
extends AdvancedEchoCurio {
    public AquaEchoCurio(Item.Properties properties) {
        super(properties, AquaEchoCurio.getAttributes());
    }

    @Override
    protected Map<AbstractSpell, AdvancedEchoCurio.SpellAttributes> getSpellAttributes() {
        LinkedHashMap<AbstractSpell, AdvancedEchoCurio.SpellAttributes> spells = new LinkedHashMap<AbstractSpell, AdvancedEchoCurio.SpellAttributes>();
        spells.put((AbstractSpell)TravelopticsSpells.AQUA_MISSILES_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(6, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.BUBBLE_SPRAY_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(13, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.CORAL_BARRAGE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(8, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.FLOODGATE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(3, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.FLOOD_SLASH_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(10, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.HYDROSHOT_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(15, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.JET_STREAM_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(14, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.RAINFALL_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(2, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.TIDAL_GRASP_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(4, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.TSUNAMI_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(5, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.VORTEX_OF_THE_DEEP_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(1, false, 0.1f, 1.0f));
        return spells;
    }

    @Override
    protected Component getUnassignedHoverText() {
        return Component.translatable((String)"item.traveloptics.random_imbue_curio.aqua_unassigned.tooltip");
    }

    @Override
    protected Component getAssignedHoverText() {
        return Component.translatable((String)"item.traveloptics.random_imbue_curio.aqua_assigned.tooltip").withStyle(ChatFormatting.DARK_GREEN);
    }

    @Override
    protected SoundEvent getAssignSound() {
        return (SoundEvent)TravelopticsSounds.AQUA_CAST_2.get();
    }

    private static Map<Attribute, AttributeModifier> getAttributes() {
        LinkedHashMap<Attribute, AttributeModifier> attributes = new LinkedHashMap<Attribute, AttributeModifier>();
        attributes.put((Attribute)TravelopticsAttributes.AQUA_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Aqua spell power", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
        return attributes;
    }
}

