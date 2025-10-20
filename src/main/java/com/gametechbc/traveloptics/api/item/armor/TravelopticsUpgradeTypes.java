/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.item.armor.UpgradeType
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.item.Item
 */
package com.gametechbc.traveloptics.api.item.armor;

import com.gametechbc.traveloptics.TravelopticsMod;
import com.gametechbc.traveloptics.api.init.TravelopticsAttributes;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.armor.UpgradeType;
import java.util.Optional;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;

public enum TravelopticsUpgradeTypes implements UpgradeType
{
    AQUA_SPELL_POWER("aqua_power", (Supplier<Item>)TravelopticsItems.AQUA_UPGRADE_ORB, (Attribute)TravelopticsAttributes.AQUA_SPELL_POWER.get(), AttributeModifier.Operation.MULTIPLY_BASE, 0.05f),
    ELDRITCH_SPELL_POWER("eldritch_power", (Supplier<Item>)TravelopticsItems.ELDRITCH_UPGRADE_ORB, (Attribute)AttributeRegistry.ELDRITCH_SPELL_POWER.get(), AttributeModifier.Operation.MULTIPLY_BASE, 0.05f);

    final Attribute attribute;
    final AttributeModifier.Operation operation;
    final float amountPerUpgrade;
    final ResourceLocation id;
    final Optional<Supplier<Item>> containerItem;

    private TravelopticsUpgradeTypes(String key, Supplier<Item> containerItem, Attribute attribute, AttributeModifier.Operation operation, float amountPerUpgrade) {
        this(key, Optional.of(containerItem), attribute, operation, amountPerUpgrade);
    }

    private TravelopticsUpgradeTypes(String key, Optional<Supplier<Item>> containerItem, Attribute attribute, AttributeModifier.Operation operation, float amountPerUpgrade) {
        this.id = TravelopticsMod.id(key);
        this.attribute = attribute;
        this.operation = operation;
        this.amountPerUpgrade = amountPerUpgrade;
        this.containerItem = containerItem;
        UpgradeType.registerUpgrade((UpgradeType)this);
    }

    public Attribute getAttribute() {
        return this.attribute;
    }

    public AttributeModifier.Operation getOperation() {
        return this.operation;
    }

    public float getAmountPerUpgrade() {
        return this.amountPerUpgrade;
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public Optional<Supplier<Item>> getContainerItem() {
        return this.containerItem;
    }
}

