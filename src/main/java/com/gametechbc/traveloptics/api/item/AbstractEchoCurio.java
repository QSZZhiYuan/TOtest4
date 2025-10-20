/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.ImmutableMultimap$Builder
 *  com.google.common.collect.Multimap
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.ISpellContainer
 *  net.minecraft.ChatFormatting
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Rarity
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 *  org.jetbrains.annotations.Nullable
 *  top.theillusivec4.curios.api.SlotContext
 */
package com.gametechbc.traveloptics.api.item;

import com.gametechbc.traveloptics.api.item.curio.CurioBaseItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

public abstract class AbstractEchoCurio
extends CurioBaseItem {
    private final Multimap<Attribute, AttributeModifier> attributeMap = HashMultimap.create();
    private static final int EXPERIENCE_COST = 180;

    public AbstractEchoCurio(Item.Properties properties, Map<Attribute, AttributeModifier> attributes) {
        super(properties.requiredFeatures(1).requiredFeatures(Rarity.EPIC));
        for (Map.Entry<Attribute, AttributeModifier> entry : attributes.entrySet()) {
            this.attributeMap.put((Object)entry.getKey(), (Object)entry.getValue());
        }
    }

    protected abstract Map<AbstractSpell, SpellAttributes> getSpellAttributes();

    protected abstract Component getAssignedHoverText();

    protected abstract Component getUnassignedHoverText();

    protected SoundEvent getAssignSound() {
        return SoundEvents.ENCHANTMENT_TABLE_USE;
    }

    protected int selectLevel(SpellAttributes attributes) {
        int min = attributes.levelRange.minLevel;
        int max = attributes.levelRange.maxLevel;
        if (!attributes.prioritizeLowerLevel) {
            return min + new Random().nextInt(max - min + 1);
        }
        int range = max - min + 1;
        int[] weights = new int[range];
        int totalWeight = 0;
        for (int i = 0; i < range; ++i) {
            int level = min + i;
            weights[i] = max - level + 1;
            totalWeight += weights[i];
        }
        int randomWeight = new Random().nextInt(totalWeight);
        for (int i = 0; i < range; ++i) {
            if ((randomWeight -= weights[i]) >= 0) continue;
            return min + i;
        }
        return min;
    }

    public InteractionResultHolder<ItemStack> resolvePage(Level level, Player player, InteractionHand hand) {
        AbstractSpell selectedSpell;
        ItemStack stack = player.getStandingEyeHeight(hand);
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("spellAssigned")) {
            player.updateTutorialInventoryAction((Component)Component.translatable((String)"item.traveloptics.spell_imbuing_curio.already_assigned.message").withStyle(ChatFormatting.RED), true);
            return InteractionResultHolder.pass((Object)stack);
        }
        if (player.totalExperience < 180) {
            player.updateTutorialInventoryAction((Component)Component.translatable((String)"item.traveloptics.spell_imbuing_curio.not_enough_xp.message").withStyle(ChatFormatting.RED), true);
            return InteractionResultHolder.fail((Object)stack);
        }
        Map<AbstractSpell, SpellAttributes> possibleSpells = this.getSpellAttributes();
        if (!possibleSpells.isEmpty() && (selectedSpell = this.selectWeightedRandomSpell(possibleSpells)) != null) {
            SpellAttributes attributes = possibleSpells.get(selectedSpell);
            int levelOfSpell = this.selectLevel(attributes);
            ISpellContainer spellContainer = ISpellContainer.create((int)1, (boolean)true, (boolean)true);
            spellContainer.addSpell(selectedSpell, levelOfSpell, true, stack);
            spellContainer.save(stack);
            tag.accept("spellAssigned", true);
            player.giveExperiencePoints(-180);
            level.gameEvent(null, player.blockPosition(), this.getAssignSound(), player.getSoundSource(), 1.0f, 1.0f);
            if (!level.isClientSide) {
                player.updateTutorialInventoryAction((Component)Component.selector((String)"item.traveloptics.spell_imbuing_curio.success", (Object[])new Object[]{selectedSpell.getDisplayName(null)}).withStyle(ChatFormatting.GREEN), true);
            }
            return InteractionResultHolder.sidedSuccess((Object)stack);
        }
        return InteractionResultHolder.fail((Object)stack);
    }

    public void resolvePage(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("spellAssigned")) {
            tooltip.add(this.getAssignedHoverText());
        } else {
            tooltip.add(this.getUnassignedHoverText());
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.random_imbue_curio.ex_required.tooltip").withStyle(ChatFormatting.AQUA));
        }
    }

    private AbstractSpell selectWeightedRandomSpell(Map<AbstractSpell, SpellAttributes> possibleSpells) {
        int totalWeight = possibleSpells.values().stream().mapToInt(attributes -> attributes.weight).sum();
        if (totalWeight <= 0) {
            return null;
        }
        int randomWeight = new Random().nextInt(totalWeight);
        for (Map.Entry<AbstractSpell, SpellAttributes> entry : possibleSpells.entrySet()) {
            if ((randomWeight -= entry.getValue().weight) >= 0) continue;
            return entry.getKey();
        }
        return null;
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        ImmutableMultimap.Builder attributeBuilder = new ImmutableMultimap.Builder();
        for (Attribute attribute : this.attributeMap.keySet()) {
            for (AttributeModifier modifier : this.attributeMap.get((Object)attribute)) {
                attributeBuilder.put((Object)attribute, (Object)new AttributeModifier(uuid, modifier.getName(), modifier.getAmount(), modifier.getOperation()));
            }
        }
        return attributeBuilder.build();
    }

    public static class SpellAttributes {
        public final LevelRange levelRange;
        public final int weight;
        public final boolean prioritizeLowerLevel;

        public SpellAttributes(LevelRange levelRange, int weight, boolean prioritizeLowerLevel) {
            this.levelRange = levelRange;
            this.weight = weight;
            this.prioritizeLowerLevel = prioritizeLowerLevel;
        }
    }

    public static class LevelRange {
        public final int minLevel;
        public final int maxLevel;

        public LevelRange(int minLevel, int maxLevel) {
            this.minLevel = minLevel;
            this.maxLevel = maxLevel;
        }
    }
}

