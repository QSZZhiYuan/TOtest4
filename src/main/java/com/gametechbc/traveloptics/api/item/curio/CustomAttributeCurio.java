/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.ImmutableMultimap$Builder
 *  com.google.common.collect.Multimap
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  top.theillusivec4.curios.api.SlotContext
 */
package com.gametechbc.traveloptics.api.item.curio;

import com.gametechbc.traveloptics.api.item.curio.CurioBaseItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class CustomAttributeCurio
extends CurioBaseItem {
    private final Multimap<Attribute, AttributeModifier> attributeMap = HashMultimap.create();

    public CustomAttributeCurio(Item.Properties properties, Map<Attribute, CustomAttribute> attributes) {
        super(properties);
        for (Map.Entry<Attribute, CustomAttribute> entry : attributes.entrySet()) {
            AttributeModifier.Operation operation = CustomAttributeCurio.parseOperation(entry.getValue().getOperation());
            AttributeModifier modifier = new AttributeModifier(entry.getValue().getName(), entry.getValue().getAmount(), operation);
            this.attributeMap.put((Object)entry.getKey(), (Object)modifier);
        }
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        ImmutableMultimap.Builder attributeBuilder = new ImmutableMultimap.Builder();
        for (Attribute attribute : this.attributeMap.keySet()) {
            for (AttributeModifier modifier : this.attributeMap.get((Object)attribute)) {
                attributeBuilder.put((Object)attribute, (Object)new AttributeModifier(uuid, modifier.getName(), modifier.getAmount(), modifier.getOperation()));
            }
        }
        return attributeBuilder.build();
    }

    private static AttributeModifier.Operation parseOperation(int operationNumber) {
        switch (operationNumber) {
            case 1: {
                return AttributeModifier.Operation.ADDITION;
            }
            case 2: {
                return AttributeModifier.Operation.MULTIPLY_BASE;
            }
            case 3: {
                return AttributeModifier.Operation.MULTIPLY_TOTAL;
            }
        }
        throw new IllegalArgumentException("Invalid operation number: " + operationNumber);
    }

    public static class CustomAttribute {
        private final String name;
        private final double amount;
        private final int operation;

        public CustomAttribute(String name, double amount, int operation) {
            this.name = name;
            this.amount = amount;
            this.operation = operation;
        }

        public String getName() {
            return this.name;
        }

        public double getAmount() {
            return this.amount;
        }

        public int getOperation() {
            return this.operation;
        }
    }
}

