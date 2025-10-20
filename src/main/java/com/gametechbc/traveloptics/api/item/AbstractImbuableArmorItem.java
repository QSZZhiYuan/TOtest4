/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer
 *  io.redspace.ironsspellbooks.api.spells.ISpellContainer
 *  net.minecraft.world.item.ArmorItem
 *  net.minecraft.world.item.ArmorItem$Type
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 */
package com.gametechbc.traveloptics.api.item;

import com.gametechbc.traveloptics.api.item.GeoArmorItem;
import com.gametechbc.traveloptics.api.item.armor.TravelopticsArmorMaterial;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class AbstractImbuableArmorItem
extends GeoArmorItem
implements IPresetSpellContainer {
    public AbstractImbuableArmorItem(TravelopticsArmorMaterial material, ArmorItem.Type type, Item.Properties settings) {
        super(material, type, settings);
    }

    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }
        Item item = itemStack.setRepairCost();
        if (item instanceof ArmorItem) {
            ArmorItem armorItem = (ArmorItem)item;
            if (this.getImbuableArmorTypes().contains(armorItem.getType()) && !ISpellContainer.isSpellContainer((ItemStack)itemStack)) {
                int slots = this.getMaxSpellSlots().getOrDefault(armorItem.getType(), 0);
                ISpellContainer spellContainer = ISpellContainer.create((int)slots, (boolean)this.isAddToSpellWheel(), (boolean)this.mustBeEquipped());
                Map spells = this.getSpellsToAdd().getOrDefault(armorItem.getType(), Collections.emptyMap());
                for (Map.Entry entry : spells.entrySet()) {
                    AbstractSpell spell = (AbstractSpell)entry.getKey();
                    SpellData data = (SpellData)entry.getValue();
                    spellContainer.addSpell(spell, data.level, data.unlocked, itemStack);
                }
                spellContainer.save(itemStack);
            }
        }
    }

    protected abstract Set<ArmorItem.Type> getImbuableArmorTypes();

    protected abstract Map<ArmorItem.Type, Integer> getMaxSpellSlots();

    protected Map<ArmorItem.Type, Map<AbstractSpell, SpellData>> getSpellsToAdd() {
        return Collections.emptyMap();
    }

    protected boolean isAddToSpellWheel() {
        return true;
    }

    protected boolean mustBeEquipped() {
        return true;
    }

    public static class SpellData {
        public final int level;
        public final boolean unlocked;

        public SpellData(int level, boolean unlocked) {
            this.level = level;
            this.unlocked = unlocked;
        }
    }
}

