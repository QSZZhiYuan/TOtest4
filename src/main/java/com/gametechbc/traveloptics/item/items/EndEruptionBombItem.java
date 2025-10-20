/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.stats.Stats
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.gameevent.GameEvent$Context
 *  net.minecraft.world.phys.Vec3
 */
package com.gametechbc.traveloptics.item.items;

import com.gametechbc.traveloptics.entity.projectiles.end_eruption_bomb.EruptionBombProjectileEntity;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class EndEruptionBombItem
extends Item {
    public EndEruptionBombItem() {
        super(new Item.Properties().requiredFeatures(64).requiredFeatures().requiredFeatures(TravelopticsItems.RARITY_VOID));
    }

    public InteractionResultHolder<ItemStack> resolvePage(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getStandingEyeHeight(hand);
        player.vehicleCanSprint(hand);
        if (!level.isClientSide) {
            Vec3 origin = player.getEyePosition();
            Vec3 direction = player.getLookAngle();
            EruptionBombProjectileEntity bomb = new EruptionBombProjectileEntity(level, (LivingEntity)player);
            bomb.setLevel(origin.reverse(direction.x(0.5)).x(0.0, (double)(bomb.getBbHeight() / 2.0f), 0.0));
            bomb.shoot(direction.z, direction.multiply, direction.reverse, 1.1f, 0.0f);
            double enderPower = player.getStandingEyeHeight((Attribute)AttributeRegistry.ENDER_SPELL_POWER.get());
            float scaledDamage = (float)(10.0 * enderPower);
            bomb.setDamage(scaledDamage);
            level.addFreshEntity((Entity)bomb);
            level.addDestroyBlockEffect(GameEvent.PROJECTILE_SHOOT, bomb.position(), GameEvent.Context.sourceEntity((Entity)player));
            level.getChunk(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SHULKER_SHOOT, SoundSource.PLAYERS, 0.6f, 0.4f / (player.getRandom().nextFloat() * 0.4f + 0.8f));
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            player.getCooldowns().addCooldown((Item)this, 60);
            player.getStandingEyeHeight(Stats.ITEM_USED.getTranslationKey((Object)this));
            player.recreateFromPacket(hand, true);
            return InteractionResultHolder.sidedSuccess((Object)stack);
        }
        return InteractionResultHolder.consume((Object)stack);
    }

    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.end_eruption_bomb.tooltip").withStyle(ChatFormatting.GOLD));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.end_eruption_bomb.tooltip1").withStyle(ChatFormatting.GRAY));
    }
}

