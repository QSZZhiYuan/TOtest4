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

public class EldritchEchoCurio
extends AdvancedEchoCurio {
    public EldritchEchoCurio(Item.Properties properties) {
        super(properties, EldritchEchoCurio.getAttributes());
    }

    @Override
    protected Map<AbstractSpell, AdvancedEchoCurio.SpellAttributes> getSpellAttributes() {
        LinkedHashMap<AbstractSpell, AdvancedEchoCurio.SpellAttributes> spells = new LinkedHashMap<AbstractSpell, AdvancedEchoCurio.SpellAttributes>();
        spells.put((AbstractSpell)SpellRegistry.ABYSSAL_SHROUD_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(2, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.ELDRITCH_BLAST_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(15, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.PLANAR_SIGHT_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(10, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.SCULK_TENTACLES_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(8, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.SONIC_BOOM_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(12, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.TELEKINESIS_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(6, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.REVERSAL_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(3, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.SHADOWED_MIASMA_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(4, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.SPECTRAL_BLINK_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(6, false, 0.1f, 1.0f));
        return spells;
    }

    @Override
    protected Component getUnassignedHoverText() {
        return Component.translatable((String)"item.traveloptics.random_imbue_curio.eldritch_unassigned.tooltip");
    }

    @Override
    protected Component getAssignedHoverText() {
        return Component.translatable((String)"item.traveloptics.random_imbue_curio.eldritch_assigned.tooltip").withStyle(ChatFormatting.DARK_GREEN);
    }

    @Override
    protected SoundEvent getAssignSound() {
        return (SoundEvent)SoundRegistry.LEARN_ELDRITCH_SPELL.get();
    }

    private static Map<Attribute, AttributeModifier> getAttributes() {
        LinkedHashMap<Attribute, AttributeModifier> attributes = new LinkedHashMap<Attribute, AttributeModifier>();
        attributes.put((Attribute)AttributeRegistry.ELDRITCH_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Eldritch spell power", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
        return attributes;
    }
}

