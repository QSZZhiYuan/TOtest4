/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.ImmutableMultimap$Builder
 *  com.google.common.collect.Multimap
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
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

public class HeavyAttributeCurio
extends CurioBaseItem {
    private final Multimap<Attribute, AttributeModifier> attributeMap = HashMultimap.create();

    public HeavyAttributeCurio(Item.Properties properties, Map<Attribute, AttributeModifier> attributes) {
        super(properties);
        for (Map.Entry<Attribute, AttributeModifier> entry : attributes.entrySet()) {
            this.attributeMap.put((Object)entry.getKey(), (Object)entry.getValue());
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
}

