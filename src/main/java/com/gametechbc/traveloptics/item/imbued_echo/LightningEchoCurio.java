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

public class LightningEchoCurio
extends AdvancedEchoCurio {
    public LightningEchoCurio(Item.Properties properties) {
        super(properties, LightningEchoCurio.getAttributes());
    }

    @Override
    protected Map<AbstractSpell, AdvancedEchoCurio.SpellAttributes> getSpellAttributes() {
        LinkedHashMap<AbstractSpell, AdvancedEchoCurio.SpellAttributes> spells = new LinkedHashMap<AbstractSpell, AdvancedEchoCurio.SpellAttributes>();
        spells.put((AbstractSpell)SpellRegistry.ASCENSION_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(13, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.BALL_LIGHTNING_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(12, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.CHAIN_LIGHTNING_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(15, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.CHARGE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(4, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.ELECTROCUTE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(10, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.LIGHTNING_BOLT_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(7, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.LIGHTNING_LANCE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(6, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.SHOCKWAVE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(8, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.THUNDERSTORM_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(3, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.DEATH_LASER_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(1, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.EM_PULSE.get(), new AdvancedEchoCurio.SpellAttributes(1, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.RAPID_LASER_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(4, false, 0.1f, 1.0f));
        return spells;
    }

    @Override
    protected Component getUnassignedHoverText() {
        return Component.translatable((String)"item.traveloptics.random_imbue_curio.lightning_unassigned.tooltip");
    }

    @Override
    protected Component getAssignedHoverText() {
        return Component.translatable((String)"item.traveloptics.random_imbue_curio.lightning_assigned.tooltip").withStyle(ChatFormatting.DARK_GREEN);
    }

    @Override
    protected SoundEvent getAssignSound() {
        return (SoundEvent)SoundRegistry.LIGHTNING_CAST.get();
    }

    private static Map<Attribute, AttributeModifier> getAttributes() {
        LinkedHashMap<Attribute, AttributeModifier> attributes = new LinkedHashMap<Attribute, AttributeModifier>();
        attributes.put((Attribute)AttributeRegistry.LIGHTNING_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Lightning spell power", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
        return attributes;
    }
}

