/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.item.ArmorMaterial
 */
package com.gametechbc.traveloptics.api.item.armor;

import java.util.Map;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorMaterial;

public interface TravelopticsArmorMaterial
extends ArmorMaterial {
    public Map<Attribute, AttributeModifier> getAdditionalAttributes();
}

