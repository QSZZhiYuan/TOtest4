/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder
 *  io.redspace.ironsspellbooks.item.UniqueItem
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  software.bernie.geckolib.animatable.SingletonGeoAnimatable
 *  software.bernie.geckolib.core.animatable.GeoAnimatable
 *  software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
 *  software.bernie.geckolib.util.GeckoLibUtil
 */
package com.gametechbc.traveloptics.item.bossweapon.gauntletofextinction;

import com.gametechbc.traveloptics.entity.item.gauntlet_of_extinction.GauntletOfExtinctionItemRenderer;
import com.gametechbc.traveloptics.item.bossweapon.gauntletofextinction.GauntletOfExtinctionItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.item.UniqueItem;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;

public class GauntletOfExtinctionLevelOneItem
extends GauntletOfExtinctionItem
implements UniqueItem {
    private static ItemDisplayContext transformType;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);

    public GauntletOfExtinctionLevelOneItem(SpellDataRegistryHolder[] imbuedSpells) {
        super(imbuedSpells);
        SingletonGeoAnimatable.registerSyncedAnimatable((GeoAnimatable)this);
    }

    @Override
    protected float getDinosaurSpiritTriggerChance() {
        return 0.6f;
    }

    @Override
    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add((Component)Component.score((String)""));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.gauntlet_of_extinction.tooltip").withStyle(ChatFormatting.GREEN));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.gauntlet_of_extinction.tooltip2"));
        if (Screen.hasShiftDown()) {
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.gauntlet_of_extinction.evo_one.active.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.gauntlet_of_extinction.evo_two.inactive.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.gauntlet_of_extinction.evo_three.inactive.tooltip"));
        } else {
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution.stars_one.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.shift_hold.advanced_tooltips"));
        }
        tooltip.add((Component)Component.score((String)""));
    }

    @Override
    public void getTransformType(ItemDisplayContext type) {
        transformType = type;
    }

    @Override
    @OnlyIn(value=Dist.CLIENT)
    protected BlockEntityWithoutLevelRenderer getRenderer() {
        return new GauntletOfExtinctionItemRenderer();
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}

