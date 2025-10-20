/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.ArmorItem$Type
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 */
package com.gametechbc.traveloptics.item;

import com.gametechbc.traveloptics.api.item.AbstractImbuableArmorItem;
import com.gametechbc.traveloptics.api.item.armor.TravelopticsArmorMaterial;
import com.gametechbc.traveloptics.config.ArmorConfig;
import java.util.Map;
import java.util.Set;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class UnbreakableImbueableArmor
extends AbstractImbuableArmorItem {
    public UnbreakableImbueableArmor(TravelopticsArmorMaterial material, ArmorItem.Type type, Item.Properties settings) {
        super(material, type, settings);
    }

    public boolean isDamageable(ItemStack stack) {
        return (Boolean)ArmorConfig.armorsShouldBeBreakable.get();
    }

    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    protected abstract Set<ArmorItem.Type> getImbuableArmorTypes();

    @Override
    protected abstract Map<ArmorItem.Type, Integer> getMaxSpellSlots();
}

