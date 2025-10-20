/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ArmorItem$Type
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  software.bernie.geckolib.renderer.GeoArmorRenderer
 */
package com.gametechbc.traveloptics.item.armor;

import com.gametechbc.traveloptics.api.item.AbstractImbuableArmorItem;
import com.gametechbc.traveloptics.entity.armor.deepling_mage_armor.DeeplingMageArmorModel;
import com.gametechbc.traveloptics.entity.armor.deepling_mage_armor.DeeplingMageArmorRenderer;
import com.gametechbc.traveloptics.item.TravelopticsArmorMaterials;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class DeeplingMageArmorItem
extends AbstractImbuableArmorItem {
    public DeeplingMageArmorItem(ArmorItem.Type slot, Item.Properties settings) {
        super(TravelopticsArmorMaterials.DEEPLING_MAGE, slot, settings);
    }

    @Override
    protected Set<ArmorItem.Type> getImbuableArmorTypes() {
        return Set.of(ArmorItem.Type.CHESTPLATE);
    }

    @Override
    protected Map<ArmorItem.Type, Integer> getMaxSpellSlots() {
        return Map.of(ArmorItem.Type.CHESTPLATE, 1);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        super.onArmorTick(stack, world, player);
        if (!world.isClientSide && this.isWearingFullSet(player) && player.isUnderWater()) {
            this.grantWaterBreathing(player);
        }
    }

    private void grantWaterBreathing(Player player) {
        if (!player.recreateFromPacket(MobEffects.WATER_BREATHING)) {
            player.getStandingEyeHeight(new MobEffectInstance(MobEffects.WATER_BREATHING, 200, 0, false, false, true));
        }
    }

    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        super.resolvePage(stack, world, tooltip, flag);
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.deepling_mage_armor.tooltip").withStyle(ChatFormatting.GREEN));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.deepling_mage_armor.tooltip1"));
        tooltip.add((Component)Component.score((String)""));
    }

    private boolean isWearingFullSet(Player player) {
        return player.shouldRemoveSoulSpeed(ArmorItem.Type.HELMET.getSlot()).setRepairCost() instanceof DeeplingMageArmorItem && player.shouldRemoveSoulSpeed(ArmorItem.Type.CHESTPLATE.getSlot()).setRepairCost() instanceof DeeplingMageArmorItem && player.shouldRemoveSoulSpeed(ArmorItem.Type.LEGGINGS.getSlot()).setRepairCost() instanceof DeeplingMageArmorItem && player.shouldRemoveSoulSpeed(ArmorItem.Type.BOOTS.getSlot()).setRepairCost() instanceof DeeplingMageArmorItem;
    }

    @Override
    @OnlyIn(value=Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new DeeplingMageArmorRenderer(new DeeplingMageArmorModel());
    }
}

