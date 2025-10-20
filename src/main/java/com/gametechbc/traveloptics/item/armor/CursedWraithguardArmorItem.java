/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ArmorItem$Type
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  software.bernie.geckolib.renderer.GeoArmorRenderer
 */
package com.gametechbc.traveloptics.item.armor;

import com.gametechbc.traveloptics.data_manager.CooldownManager;
import com.gametechbc.traveloptics.data_manager.PhantomRageManager;
import com.gametechbc.traveloptics.entity.armor.cursed_wraithguard_armor.CursedWraithguardArmorModel;
import com.gametechbc.traveloptics.entity.armor.cursed_wraithguard_armor.CursedWraithguardArmorRenderer;
import com.gametechbc.traveloptics.init.TravelopticsEffects;
import com.gametechbc.traveloptics.item.TravelopticsArmorMaterials;
import com.gametechbc.traveloptics.item.UnbreakableImbueableArmor;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class CursedWraithguardArmorItem
extends UnbreakableImbueableArmor {
    private static final int COOLDOWN_TICKS = 500;

    public CursedWraithguardArmorItem(ArmorItem.Type slot, Item.Properties settings) {
        super(TravelopticsArmorMaterials.CURSED_WRAITHGUARD, slot, settings);
    }

    @Override
    protected Set<ArmorItem.Type> getImbuableArmorTypes() {
        return Set.of(ArmorItem.Type.CHESTPLATE);
    }

    @Override
    protected Map<ArmorItem.Type, Integer> getMaxSpellSlots() {
        return Map.of(ArmorItem.Type.CHESTPLATE, 1);
    }

    public void useOn(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        super.useOn(stack, world, entity, slot, selected);
        if (!(entity instanceof Player)) {
            return;
        }
        Player player = (Player)entity;
        ItemStack chestplate = player.shouldRemoveSoulSpeed(ArmorItem.Type.CHESTPLATE.getSlot());
        if (stack != chestplate || chestplate.setRepairCost() != this || !this.isWearingFullSet(player)) {
            return;
        }
        if (!world.isClientSide) {
            if (CooldownManager.isCooldownActive(chestplate)) {
                CooldownManager.tickCooldown(chestplate);
                return;
            }
            if (player.hurtTime > 0) {
                int currentPoints = PhantomRageManager.getPhantomRage(chestplate);
                if (currentPoints < 350) {
                    int rageAmount = world.random.nextFloat() < 0.6f ? 3 : 1;
                    PhantomRageManager.addPhantomRage(chestplate, player, rageAmount);
                }
                if (PhantomRageManager.getPhantomRage(chestplate) >= 350) {
                    PhantomRageManager.setPhantomRage(chestplate, 0);
                    this.applyRageEffect(player);
                    CooldownManager.setCooldown(chestplate, 500, 500);
                }
            }
        }
    }

    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        CursedWraithguardArmorItem chestplate;
        super.resolvePage(stack, world, tooltip, flag);
        tooltip.add((Component)Component.score((String)""));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithguard_armor.tooltip").withStyle(ChatFormatting.GREEN));
        Item item = stack.setRepairCost();
        if (item instanceof CursedWraithguardArmorItem && (chestplate = (CursedWraithguardArmorItem)item).getType() == ArmorItem.Type.CHESTPLATE) {
            int currentPhantomRage = PhantomRageManager.getPhantomRage(stack);
            tooltip.add((Component)Component.score((String)"\ud83d\udca2 ").append((Component)Component.selector((String)"item.tooltip.traveloptics.phantom_rage", (Object[])new Object[]{currentPhantomRage, 350}).withStyle(ChatFormatting.WHITE)));
        }
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.cursed_wraithguard_armor.tooltip1"));
        int cooldown = CooldownManager.getCooldown(stack);
        if (cooldown > 0) {
            tooltip.add((Component)Component.selector((String)"item.tooltip.traveloptics.armor_cooldown", (Object[])new Object[]{cooldown / 20}).withStyle(ChatFormatting.GRAY));
        }
        tooltip.add((Component)Component.score((String)""));
    }

    private void applyRageEffect(Player player) {
        int amplifier = (double)(player.getHealth() / player.getMaxHealth()) <= 0.5 ? 3 : 1;
        player.getStandingEyeHeight(new MobEffectInstance((MobEffect)TravelopticsEffects.PHANTOM_RAGE.get(), 300, amplifier, false, false, false));
    }

    private boolean isWearingFullSet(Player player) {
        return player.shouldRemoveSoulSpeed(ArmorItem.Type.HELMET.getSlot()).setRepairCost() instanceof CursedWraithguardArmorItem && player.shouldRemoveSoulSpeed(ArmorItem.Type.CHESTPLATE.getSlot()).setRepairCost() instanceof CursedWraithguardArmorItem && player.shouldRemoveSoulSpeed(ArmorItem.Type.LEGGINGS.getSlot()).setRepairCost() instanceof CursedWraithguardArmorItem && player.shouldRemoveSoulSpeed(ArmorItem.Type.BOOTS.getSlot()).setRepairCost() instanceof CursedWraithguardArmorItem;
    }

    @Override
    @OnlyIn(value=Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new CursedWraithguardArmorRenderer(new CursedWraithguardArmorModel());
    }
}

