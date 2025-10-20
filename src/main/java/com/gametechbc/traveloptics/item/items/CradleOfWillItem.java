/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.projectile.Eye_Of_Dungeon_Entity
 *  net.minecraft.ChatFormatting
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.stats.Stats
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.gameevent.GameEvent$Context
 *  net.minecraft.world.level.levelgen.structure.Structure
 */
package com.gametechbc.traveloptics.item.items;

import com.gametechbc.traveloptics.api.utils.TOGeneralUtils;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.util.TravelopticsTags;
import com.github.L_Ender.cataclysm.entity.projectile.Eye_Of_Dungeon_Entity;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.structure.Structure;

public class CradleOfWillItem
extends Item {
    private final TagKey<Structure> structureTag = TravelopticsTags.CRADLE_OF_WILL_DESTINATION;
    private final int red = TOGeneralUtils.hexToRed("#8219ff");
    private final int green = TOGeneralUtils.hexToGreen("#8219ff");
    private final int blue = TOGeneralUtils.hexToBlue("#8219ff");

    public CradleOfWillItem() {
        super(new Item.Properties().requiredFeatures(64).requiredFeatures().requiredFeatures(TravelopticsItems.RARITY_VOID));
    }

    public InteractionResultHolder<ItemStack> resolvePage(Level level, Player player, InteractionHand hand) {
        ServerLevel serverLevel;
        BlockPos structurePos;
        ItemStack itemStack = player.getStandingEyeHeight(hand);
        player.vehicleCanSprint(hand);
        if (level instanceof ServerLevel && (structurePos = (serverLevel = (ServerLevel)level).getRandomSequence(this.structureTag, player.blockPosition(), 100, false)) != null) {
            Eye_Of_Dungeon_Entity eyeEntity = new Eye_Of_Dungeon_Entity(level, player.getX(), player.getY(0.5), player.getZ());
            eyeEntity.setItem(itemStack);
            eyeEntity.setR(this.red);
            eyeEntity.setG(this.green);
            eyeEntity.setB(this.blue);
            eyeEntity.signalTo(structurePos);
            level.addDestroyBlockEffect(GameEvent.PROJECTILE_SHOOT, eyeEntity.position(), GameEvent.Context.sourceEntity((Entity)player));
            level.addFreshEntity((Entity)eyeEntity);
            if (player instanceof ServerPlayer) {
                ServerPlayer serverPlayer = (ServerPlayer)player;
                CriteriaTriggers.USED_ENDER_EYE.trigger(serverPlayer, structurePos);
            }
            level.getChunk(null, player.getX(), player.getY(), player.getZ(), (SoundEvent)TravelopticsSounds.NIGHTWARDEN_SUMMON_CLONE_1.get(), SoundSource.NEUTRAL, 0.5f, 1.0f);
            level.addDestroyBlockEffect(null, 1003, player.blockPosition(), 0);
            player.getCooldowns().addCooldown((Item)this, 60);
            player.getStandingEyeHeight(Stats.ITEM_USED.getTranslationKey((Object)this));
            player.recreateFromPacket(hand, true);
            return InteractionResultHolder.sidedSuccess((Object)itemStack);
        }
        return InteractionResultHolder.consume((Object)itemStack);
    }

    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.cradle_of_will.desc").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapon_material.desc").withStyle(ChatFormatting.DARK_GRAY));
        super.resolvePage(stack, world, tooltip, flag);
    }
}

