/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer
 *  io.redspace.ironsspellbooks.api.spells.ISpellContainer
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 */
package com.gametechbc.traveloptics.api.item;

import com.gametechbc.traveloptics.api.item.GeoSpearItem;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import java.util.Collections;
import java.util.Map;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class GeoMagicSpearItem
extends GeoSpearItem
implements IPresetSpellContainer {
    public GeoMagicSpearItem(Item.Properties properties, double damage) {
        super(properties, damage);
    }

    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null || !this.isImbuable()) {
            return;
        }
        if (!ISpellContainer.isSpellContainer((ItemStack)itemStack)) {
            int slots = this.getMaxSpellSlots();
            ISpellContainer spellContainer = ISpellContainer.create((int)slots, (boolean)this.isAddToSpellWheel(), (boolean)this.mustBeEquipped());
            Map<AbstractSpell, SpellData> spells = this.getSpellsToAdd();
            for (Map.Entry<AbstractSpell, SpellData> entry : spells.entrySet()) {
                AbstractSpell spell = entry.getKey();
                SpellData data = entry.getValue();
                spellContainer.addSpell(spell, data.level, data.unlocked, itemStack);
            }
            spellContainer.save(itemStack);
        }
    }

    protected boolean isImbuable() {
        return true;
    }

    protected abstract int getMaxSpellSlots();

    protected Map<AbstractSpell, SpellData> getSpellsToAdd() {
        return Collections.emptyMap();
    }

    protected boolean isAddToSpellWheel() {
        return true;
    }

    protected boolean mustBeEquipped() {
        return false;
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

