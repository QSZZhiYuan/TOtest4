/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem
 *  io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder
 *  io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer
 *  io.redspace.ironsspellbooks.api.spells.ISpellContainer
 *  io.redspace.ironsspellbooks.api.spells.SpellData
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Tier
 */
package com.gametechbc.traveloptics.api.item.weapons;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class MagicMultiSpellItem
extends ExtendedSwordItem
implements IPresetSpellContainer {
    private List<SpellData> spellDataList;
    private final SpellDataRegistryHolder[] spellDataRegistryHolders;

    public MagicMultiSpellItem(Tier tier, double attackDamage, double attackSpeed, SpellDataRegistryHolder[] spellDataRegistryHolders, Map<Attribute, AttributeModifier> additionalAttributes, Item.Properties properties) {
        super(tier, attackDamage, attackSpeed, additionalAttributes, properties);
        this.spellDataRegistryHolders = spellDataRegistryHolders;
        this.spellDataList = null;
    }

    public List<SpellData> getSpells() {
        if (this.spellDataList == null) {
            this.spellDataList = new ArrayList<SpellData>();
            for (SpellDataRegistryHolder holder : this.spellDataRegistryHolders) {
                this.spellDataList.add(holder.getSpellData());
            }
        }
        return this.spellDataList;
    }

    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }
        if (!ISpellContainer.isSpellContainer((ItemStack)itemStack)) {
            List<SpellData> spells = this.getSpells();
            ISpellContainer spellContainer = ISpellContainer.create((int)spells.size(), (boolean)true, (boolean)false);
            for (SpellData spellData : spells) {
                spellContainer.addSpell(spellData.getSpell(), spellData.getLevel(), true, null);
            }
            spellContainer.save(itemStack);
        }
    }
}

