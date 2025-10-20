/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.api.item.weapons;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public interface IWeaponAbility {
    public InteractionResultHolder<ItemStack> use(Level var1, Player var2, InteractionHand var3, ItemStack var4, int var5);

    public boolean hurtEnemy(ItemStack var1, LivingEntity var2, LivingEntity var3, int var4);

    public void appendHoverText(ItemStack var1, Level var2, List<Component> var3, TooltipFlag var4, int var5);

    public void releaseUsing(ItemStack var1, Level var2, LivingEntity var3, int var4, int var5);

    public void onUseTick(Level var1, LivingEntity var2, ItemStack var3, int var4, int var5);
}

