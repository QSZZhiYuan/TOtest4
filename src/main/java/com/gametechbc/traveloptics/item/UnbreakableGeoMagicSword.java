/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder
 *  net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Tier
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.gametechbc.traveloptics.item;

import com.gametechbc.traveloptics.api.item.GeoMagicSwordItem;
import com.gametechbc.traveloptics.config.WeaponConfig;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import java.util.Map;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class UnbreakableGeoMagicSword
extends GeoMagicSwordItem {
    public UnbreakableGeoMagicSword(Tier tier, double attackDamage, double attackSpeed, SpellDataRegistryHolder[] spellDataRegistryHolders, Map<Attribute, AttributeModifier> additionalAttributes, Item.Properties properties) {
        super(tier, attackDamage, attackSpeed, spellDataRegistryHolders, additionalAttributes, properties);
    }

    public boolean isDamageable(ItemStack stack) {
        return (Boolean)WeaponConfig.weaponsShouldBeBreakable.get();
    }

    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    @OnlyIn(value=Dist.CLIENT)
    protected abstract BlockEntityWithoutLevelRenderer getRenderer();
}

