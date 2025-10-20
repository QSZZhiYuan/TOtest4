/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.gametechbc.traveloptics.item;

import com.gametechbc.traveloptics.api.item.GeoMagicSpearItem;
import com.gametechbc.traveloptics.config.WeaponConfig;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class UnbreakableGeoMagicSpear
extends GeoMagicSpearItem {
    public UnbreakableGeoMagicSpear(Item.Properties properties, double damage) {
        super(properties, damage);
    }

    public boolean isDamageable(ItemStack stack) {
        return (Boolean)WeaponConfig.weaponsShouldBeBreakable.get();
    }

    @Override
    @OnlyIn(value=Dist.CLIENT)
    protected abstract BlockEntityWithoutLevelRenderer getRenderer();
}

