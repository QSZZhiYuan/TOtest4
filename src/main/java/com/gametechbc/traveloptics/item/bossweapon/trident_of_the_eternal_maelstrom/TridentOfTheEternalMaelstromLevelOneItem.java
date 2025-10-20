/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.gametechbc.traveloptics.item.bossweapon.trident_of_the_eternal_maelstrom;

import com.gametechbc.traveloptics.entity.item.trident_of_the_eternal_maelstrom.EternalMaelstromTridentItemRenderer;
import com.gametechbc.traveloptics.item.bossweapon.trident_of_the_eternal_maelstrom.TridentOfTheEternalMaelstromItem;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TridentOfTheEternalMaelstromLevelOneItem
extends TridentOfTheEternalMaelstromItem {
    private static ItemDisplayContext transformType;

    public TridentOfTheEternalMaelstromLevelOneItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    protected float getAoeDamageMultiplier() {
        return 0.75f;
    }

    @Override
    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add((Component)Component.score((String)""));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.trident_of_the_eternal_maelstrom.tooltip").withStyle(ChatFormatting.GREEN));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.trident_of_the_eternal_maelstrom.tooltip2"));
        if (Screen.hasShiftDown()) {
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.trident_of_the_eternal_maelstrom.evo_one.active.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.trident_of_the_eternal_maelstrom.evo_two.inactive.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.trident_of_the_eternal_maelstrom.evo_three.inactive.tooltip"));
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
        return new EternalMaelstromTridentItemRenderer();
    }
}

