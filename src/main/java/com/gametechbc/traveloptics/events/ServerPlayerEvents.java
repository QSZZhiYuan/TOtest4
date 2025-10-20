/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModEffect
 *  io.redspace.ironsspellbooks.api.magic.SpellSelectionManager$SpellSelectionEvent
 *  io.redspace.ironsspellbooks.api.spells.ISpellContainer
 *  io.redspace.ironsspellbooks.api.spells.SpellData
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$LeftClickEmpty
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickItem
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 *  top.theillusivec4.curios.api.CuriosApi
 *  top.theillusivec4.curios.api.SlotResult
 */
package com.gametechbc.traveloptics.events;

import com.gametechbc.traveloptics.api.utils.ILeftClick;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.init.TravelopticsMessages;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.network.MessageSwingArm;
import com.gametechbc.traveloptics.util.TravelopticsTags;
import com.github.L_Ender.cataclysm.init.ModEffect;
import io.redspace.ironsspellbooks.api.magic.SpellSelectionManager;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import java.util.Arrays;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

@Mod.EventBusSubscriber(modid="traveloptics", bus=Mod.EventBusSubscriber.Bus.FORGE)
public class ServerPlayerEvents {
    @SubscribeEvent
    public static void onRightClickFirework(PlayerInteractEvent.RightClickItem event) {
        ItemStack chestplate;
        Player player = event.getEntity();
        ItemStack itemStack = event.getItemStack();
        if (itemStack.onDestroyed(Items.FIREWORK_ROCKET) && player.isFallFlying() && !(chestplate = player.getInventory().setChanged(2)).onUseTick() && chestplate.setRepairCost() == TravelopticsItems.MECHANIZED_EXOSKELETON_ARMOR_CHESTPLATE.get()) {
            event.setCanceled(true);
            player.updateTutorialInventoryAction((Component)Component.translatable((String)"item.traveloptics.mechanized_exoskeleton.jetpack.firework_warning").withStyle(ChatFormatting.RED), true);
            player.level().getChunk(null, player.getX(), player.getY(), player.getZ(), (SoundEvent)TravelopticsSounds.JETPACK_WARNING.get(), SoundSource.PLAYERS, 0.6f, 0.7f);
        }
        if (itemStack.onDestroyed(Items.FIREWORK_ROCKET) && player.isFallFlying() && !(chestplate = player.getInventory().setChanged(2)).onUseTick() && chestplate.setRepairCost() == TravelopticsItems.FORLORN_HARBINGER_ARMOR_ROBE.get()) {
            event.setCanceled(true);
            player.updateTutorialInventoryAction((Component)Component.translatable((String)"item.traveloptics.wings.firework_warning").withStyle(ChatFormatting.RED), true);
            player.level().getChunk(null, player.getX(), player.getY(), player.getZ(), (SoundEvent)ACSoundRegistry.VESPER_IDLE.get(), SoundSource.PLAYERS, 0.6f, 0.8f);
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.LeftClickEmpty event) {
        boolean flag = false;
        ItemStack leftItem = event.getEntity().getOffhandItem();
        ItemStack rightItem = event.getEntity().getMainHandItem();
        if (!event.getEntity().recreateFromPacket((MobEffect)ModEffect.EFFECTSTUN.get())) {
            if (leftItem.setRepairCost() instanceof ILeftClick) {
                ((ILeftClick)leftItem.setRepairCost()).onLeftClick(leftItem, (LivingEntity)event.getEntity());
                flag = true;
            }
            if (rightItem.setRepairCost() instanceof ILeftClick) {
                ((ILeftClick)rightItem.setRepairCost()).onLeftClick(rightItem, (LivingEntity)event.getEntity());
                flag = true;
            }
            if (event.getLevel().isClientSide && flag) {
                TravelopticsMessages.sendMSGToServer(MessageSwingArm.INSTANCE);
            }
        }
    }

    @SubscribeEvent
    public static void applyCurioBasedSpells(SpellSelectionManager.SpellSelectionEvent event) {
        Player player = event.getEntity();
        CuriosApi.getCuriosInventory((LivingEntity)player).ifPresent(a -> {
            List list = a.findCurios(item -> item != null && ISpellContainer.isSpellContainer((ItemStack)item) && item.onDestroyed(TravelopticsTags.SPELL_IMBUED_CURIO));
            for (SlotResult i : list) {
                int initialIndex;
                SpellData[] spells;
                ISpellContainer spellContainer = i.stack() != null ? ISpellContainer.get((ItemStack)i.stack()) : null;
                if (spellContainer == null || (spells = spellContainer.getAllSpells()) == null || Arrays.stream(spells).toList().isEmpty()) continue;
                for (int spellIndex = initialIndex = event.getManager().getSpellCount(); spellIndex < initialIndex + spells.length; ++spellIndex) {
                    SpellData spell = spells[spellIndex - initialIndex];
                    if (spell == null || spell.getSpell() == null) {
                        return;
                    }
                    event.addSelectionOption(new SpellData(spell.getSpell(), spell.getLevel(), true), i.stack().setRepairCost().emptyContents(), spellIndex);
                }
            }
        });
    }
}

