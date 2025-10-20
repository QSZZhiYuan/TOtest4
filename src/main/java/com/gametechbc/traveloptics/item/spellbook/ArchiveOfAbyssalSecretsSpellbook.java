/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMultimap
 *  com.google.common.collect.ImmutableMultimap$Builder
 *  io.redspace.ironsspellbooks.api.item.curios.AffinityData
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder
 *  io.redspace.ironsspellbooks.api.registry.SpellRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.item.UniqueSpellBook
 *  io.redspace.ironsspellbooks.util.MinecraftInstanceHelper
 *  io.redspace.ironsspellbooks.util.TooltipsUtils
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.gametechbc.traveloptics.item.spellbook;

import com.gametechbc.traveloptics.config.SpellsConfig;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.google.common.collect.ImmutableMultimap;
import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.item.UniqueSpellBook;
import io.redspace.ironsspellbooks.util.MinecraftInstanceHelper;
import io.redspace.ironsspellbooks.util.TooltipsUtils;
import java.util.List;
import java.util.UUID;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArchiveOfAbyssalSecretsSpellbook
extends UniqueSpellBook {
    public ArchiveOfAbyssalSecretsSpellbook() {
        super(SpellRarity.LEGENDARY, SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.SHADOWED_MIASMA_SPELL, 3), new SpellDataRegistryHolder(TravelopticsSpells.ORBITAL_VOID_SPELL, 5)}), 10, () -> {
            ImmutableMultimap.Builder builder = ImmutableMultimap.builder();
            builder.put((Object)((Attribute)AttributeRegistry.MAX_MANA.get()), (Object)new AttributeModifier(UUID.fromString("b450acae-1cb8-45c1-9a08-e9ae0fdda6db"), "Spellbook modifier", ((Double)SpellsConfig.abyssalSecretsMaxMana.get()).doubleValue(), AttributeModifier.Operation.ADDITION));
            builder.put((Object)((Attribute)AttributeRegistry.ENDER_SPELL_POWER.get()), (Object)new AttributeModifier(UUID.fromString("b450acae-1cb8-45c1-9a08-e9ae0fdda6db"), "Spellbook modifier", ((Double)SpellsConfig.abyssalSecretsEnderSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put((Object)((Attribute)AttributeRegistry.ELDRITCH_SPELL_POWER.get()), (Object)new AttributeModifier(UUID.fromString("b450acae-1cb8-45c1-9a08-e9ae0fdda6db"), "Spellbook modifier", ((Double)SpellsConfig.abyssalSecretsEldritchSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE));
            return builder.build();
        });
    }

    public void resolvePage(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> lines, @NotNull TooltipFlag flag) {
        super.resolvePage(itemStack, level, lines, flag);
        AffinityData affinityData = AffinityData.getAffinityData((ItemStack)itemStack);
        AbstractSpell spell = affinityData.getSpell();
        if (spell != SpellRegistry.none()) {
            int i = TooltipsUtils.indexOfComponent(lines, (String)"tooltip.irons_spellbooks.spellbook_spell_count");
            lines.add(i < 0 ? lines.size() : i + 1, (Component)Component.selector((String)"tooltip.irons_spellbooks.enhance_spell_level", (Object[])new Object[]{spell.getDisplayName(MinecraftInstanceHelper.instance.player()).withStyle(spell.getSchoolType().getDisplayName().withStyle())}).withStyle(ChatFormatting.RED));
        }
    }

    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }
        super.initializeSpellContainer(itemStack);
        AffinityData.setAffinityData((ItemStack)itemStack, (AbstractSpell)((AbstractSpell)TravelopticsSpells.CURSED_MINEFIELD_SPELL.get()));
    }
}

