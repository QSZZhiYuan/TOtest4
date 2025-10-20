/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMultimap
 *  com.google.common.collect.ImmutableMultimap$Builder
 *  com.google.common.collect.Multimap
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.item.UniqueItem
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
 *  net.minecraft.network.chat.Component
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.stats.Stats
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow$Pickup
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.item.enchantment.Enchantment
 *  net.minecraft.world.item.enchantment.EnchantmentCategory
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.gametechbc.traveloptics.item.bossweapon.trident_of_the_eternal_maelstrom;

import com.gametechbc.traveloptics.api.init.TravelopticsAttributes;
import com.gametechbc.traveloptics.api.item.GeoMagicSpearItem;
import com.gametechbc.traveloptics.config.WeaponConfig;
import com.gametechbc.traveloptics.entity.item.trident_of_the_eternal_maelstrom.EternalMaelstromTridentItemRenderer;
import com.gametechbc.traveloptics.entity.projectiles.aqua_trident.EternalMaelstromTridentEntity;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.gametechbc.traveloptics.item.UnbreakableGeoMagicSpear;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.item.UniqueItem;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TridentOfTheEternalMaelstromItem
extends UnbreakableGeoMagicSpear
implements UniqueItem {
    private static ItemDisplayContext transformType;

    public TridentOfTheEternalMaelstromItem(Item.Properties properties) {
        super(properties, (Double)WeaponConfig.eternalMaelstromDamage.get());
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        Multimap<Attribute, AttributeModifier> modifiers = super.getDefaultAttributeModifiers(equipmentSlot);
        if (equipmentSlot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder builder = ImmutableMultimap.builder();
            builder.putAll(modifiers);
            builder.put((Object)((Attribute)TravelopticsAttributes.AQUA_SPELL_POWER.get()), (Object)new AttributeModifier(UUID.fromString("fa2337fd-b888-4c1c-8a4e-3b7d4b74b2d9"), "Weapon boost", ((Double)WeaponConfig.eternalMaelstromAquaSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE));
            return builder.build();
        }
        return modifiers;
    }

    @Override
    protected int getMaxSpellSlots() {
        return 1;
    }

    @Override
    protected Map<AbstractSpell, GeoMagicSpearItem.SpellData> getSpellsToAdd() {
        return Map.of((AbstractSpell)TravelopticsSpells.SKYPIERCER_SPELL.get(), new GeoMagicSpearItem.SpellData(1, true));
    }

    public void getDefaultAttributeModifiers(ItemStack itemStack, Level level, LivingEntity livingEntity, int i1) {
        if (livingEntity instanceof Player) {
            Player player = (Player)livingEntity;
            int i = this.getUseDuration(itemStack) - i1;
            float f = this.getPowerForTime(i);
            if ((double)f > 0.1) {
                itemStack.onDestroyed(1, livingEntity, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                if (!level.isClientSide) {
                    EternalMaelstromTridentEntity spearEntity = new EternalMaelstromTridentEntity(level, (LivingEntity)player, itemStack);
                    spearEntity.setOriginalSlot(player.getInventory().getTimesChanged);
                    spearEntity.mayInteract((Entity)player, player.getXRot(), player.getYRot(), 0.0f, f * 3.5f, 1.0f);
                    if (player.getAbilities().instabuild) {
                        spearEntity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }
                    spearEntity.setTridentDamage(((Double)WeaponConfig.eternalMaelstromDamage.get()).floatValue() + 4.0f);
                    spearEntity.setAoeDamageMultiplier(this.getAoeDamageMultiplier());
                    spearEntity.setBoltstrikeDamage(this.calculateBoltstrikeDamage(player));
                    level.addFreshEntity((Entity)spearEntity);
                    if (!player.getAbilities().instabuild) {
                        player.getInventory().removeItem(itemStack);
                    }
                }
                level.getChunk(null, (Entity)player, (SoundEvent)TravelopticsSounds.MAELSTROM_TRIDENT_THROW.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
                player.getStandingEyeHeight(Stats.ITEM_USED.getTranslationKey((Object)this));
            }
        }
    }

    protected float getAoeDamageMultiplier() {
        return 0.5f;
    }

    protected float calculateBoltstrikeDamage(Player player) {
        return 8.0f * ((Double)WeaponConfig.eternalMaelstromBoltstrikeDamageMultiplier.get()).floatValue();
    }

    public int getEnchantmentValue() {
        return 15;
    }

    public boolean isEnchantable(ItemStack stack) {
        return stack.getCount() == 1;
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return super.canApplyAtEnchantingTable(stack, enchantment) || enchantment.getMinLevel == EnchantmentCategory.WEAPON;
    }

    public boolean isValidRepairItem(ItemStack item, ItemStack repairItem) {
        return repairItem.onDestroyed((Item)TravelopticsItems.HULLBREAKER_STEEL.get()) || super.isValidRepairItem(item, repairItem);
    }

    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add((Component)Component.score((String)""));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.trident_of_the_eternal_maelstrom.tooltip").withStyle(ChatFormatting.GREEN));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.trident_of_the_eternal_maelstrom.tooltip1"));
        if (Screen.hasShiftDown()) {
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.trident_of_the_eternal_maelstrom.evo_one.inactive.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.trident_of_the_eternal_maelstrom.evo_two.inactive.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.trident_of_the_eternal_maelstrom.evo_three.inactive.tooltip"));
        } else {
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution.stars_zero.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.shift_hold.advanced_tooltips"));
        }
        tooltip.add((Component)Component.score((String)""));
        super.resolvePage(stack, world, tooltip, flag);
    }

    public void getTransformType(ItemDisplayContext type) {
        transformType = type;
    }

    @Override
    @OnlyIn(value=Dist.CLIENT)
    protected BlockEntityWithoutLevelRenderer getRenderer() {
        return new EternalMaelstromTridentItemRenderer();
    }
}

