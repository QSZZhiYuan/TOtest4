/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
 *  net.minecraft.network.chat.Component
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
package com.gametechbc.traveloptics.item.bossweapon.mechanized_wraithblade;

import com.gametechbc.traveloptics.data_manager.PlasmaCoreManager;
import com.gametechbc.traveloptics.entity.item.mechanized_wraithblade.MechanizedWraithbladeItemRenderer;
import com.gametechbc.traveloptics.item.bossweapon.mechanized_wraithblade.MechanizedWraithbladeItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;

public class MechanizedWraithbladeLevelOneItem
extends MechanizedWraithbladeItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);

    public MechanizedWraithbladeLevelOneItem(SpellDataRegistryHolder[] imbuedSpells) {
        super(imbuedSpells);
        SingletonGeoAnimatable.registerSyncedAnimatable((GeoAnimatable)this);
    }

    @Override
    protected int getPlasmaPointsForSwing(int swingCounter) {
        return switch (swingCounter) {
            case 1 -> 8;
            case 2 -> 15;
            case 3 -> 22;
            default -> 8;
        };
    }

    @Override
    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add((Component)Component.score((String)""));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.mechanized_wraithblade.tooltip").withStyle(ChatFormatting.GREEN));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.mechanized_wraithblade.tooltip1"));
        int plasmaCoreValue = PlasmaCoreManager.getPlasmaCore(stack);
        tooltip.add((Component)Component.score((String)("\u26a1 Plasma Core: " + plasmaCoreValue)).withStyle(ChatFormatting.GOLD));
        if (Screen.hasShiftDown()) {
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.mechanized_wraithblade.evo_one.active.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.mechanized_wraithblade.evo_two.inactive.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.mechanized_wraithblade.evo_three.inactive.tooltip"));
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.mechanized_wraithblade.tooltip5"));
        } else {
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution.stars_one.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.shift_hold.advanced_tooltips"));
        }
        tooltip.add((Component)Component.score((String)""));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    @OnlyIn(value=Dist.CLIENT)
    protected BlockEntityWithoutLevelRenderer getRenderer() {
        return new MechanizedWraithbladeItemRenderer();
    }
}

