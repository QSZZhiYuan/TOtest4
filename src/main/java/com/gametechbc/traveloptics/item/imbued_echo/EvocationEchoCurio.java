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

public class EvocationEchoCurio
extends AdvancedEchoCurio {
    public EvocationEchoCurio(Item.Properties properties) {
        super(properties, EvocationEchoCurio.getAttributes());
    }

    @Override
    protected Map<AbstractSpell, AdvancedEchoCurio.SpellAttributes> getSpellAttributes() {
        LinkedHashMap<AbstractSpell, AdvancedEchoCurio.SpellAttributes> spells = new LinkedHashMap<AbstractSpell, AdvancedEchoCurio.SpellAttributes>();
        spells.put((AbstractSpell)SpellRegistry.ARROW_VOLLEY_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(14, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.CHAIN_CREEPER_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(13, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.FANG_STRIKE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(11, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.FANG_WARD_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(12, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.FIRECRACKER_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(10, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.GUST_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(8, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.INVISIBILITY_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(2, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.LOB_CREEPER_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(12, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.SHIELD_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(15, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.SLOW_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(3, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.SPECTRAL_HAMMER_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(7, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.SUMMON_HORSE_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(7, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.SUMMON_VEX_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(6, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)SpellRegistry.WOLOLO_SPELL.get(), new AdvancedEchoCurio.SpellAttributes(1, false, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.ASHEN_BREATH.get(), new AdvancedEchoCurio.SpellAttributes(5, true, 0.1f, 1.0f));
        spells.put((AbstractSpell)TravelopticsSpells.LINGERING_STRAIN.get(), new AdvancedEchoCurio.SpellAttributes(2, false, 0.1f, 1.0f));
        return spells;
    }

    @Override
    protected Component getUnassignedHoverText() {
        return Component.translatable((String)"item.traveloptics.random_imbue_curio.evocation_unassigned.tooltip");
    }

    @Override
    protected Component getAssignedHoverText() {
        return Component.translatable((String)"item.traveloptics.random_imbue_curio.evocation_assigned.tooltip").withStyle(ChatFormatting.DARK_GREEN);
    }

    @Override
    protected SoundEvent getAssignSound() {
        return (SoundEvent)SoundRegistry.EVOCATION_CAST.get();
    }

    private static Map<Attribute, AttributeModifier> getAttributes() {
        LinkedHashMap<Attribute, AttributeModifier> attributes = new LinkedHashMap<Attribute, AttributeModifier>();
        attributes.put((Attribute)AttributeRegistry.EVOCATION_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Evocation spell power", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
        return attributes;
    }
}

