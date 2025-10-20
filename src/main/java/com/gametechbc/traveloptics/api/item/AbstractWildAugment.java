/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMultimap$Builder
 *  com.google.common.collect.Multimap
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.registries.ForgeRegistries
 *  top.theillusivec4.curios.api.SlotContext
 */
package com.gametechbc.traveloptics.api.item;

import com.gametechbc.traveloptics.api.item.curio.HeavyAttributeCurio;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;

public abstract class AbstractWildAugment
extends HeavyAttributeCurio {
    private static final int EXPERIENCE_COST = 280;

    public AbstractWildAugment(Item.Properties properties) {
        super(properties.requiredFeatures(1), Map.of());
    }

    protected abstract List<AttributeEntry> getPositiveAttributeEntries();

    protected abstract List<AttributeEntry> getNegativeAttributeEntries();

    protected abstract List<AttributeEntry> getChanceAttributeEntries();

    protected abstract double getGlobalChance();

    protected abstract SoundEvent getAssignSound();

    protected abstract Component getInitialHoverText();

    protected abstract Component getAssignedHoverText();

    public void resolvePage(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        CompoundTag itemTag = stack.getOrCreateTag();
        if (itemTag.getBoolean("attributesAssigned")) {
            tooltip.add(this.getAssignedHoverText());
        } else {
            tooltip.add(this.getInitialHoverText());
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        CompoundTag itemTag = stack.getOrCreateTag();
        return this.getAttributesFromNBT(itemTag, uuid);
    }

    public InteractionResultHolder<ItemStack> resolvePage(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getStandingEyeHeight(hand);
        CompoundTag itemTag = stack.getOrCreateTag();
        if (!itemTag.getBoolean("attributesAssigned")) {
            if (player.totalExperience >= 280) {
                this.assignRandomAttributes(stack);
                itemTag.accept("attributesAssigned", true);
                player.giveExperiencePoints(-280);
                this.playSound(level, player, this.getAssignSound());
                if (!level.isClientSide) {
                    player.updateTutorialInventoryAction((Component)Component.translatable((String)"item.traveloptics.augment_wild.assigned.success"), true);
                }
                return InteractionResultHolder.sidedSuccess((Object)stack);
            }
            if (!level.isClientSide) {
                player.updateTutorialInventoryAction((Component)Component.translatable((String)"item.traveloptics.augment_wild.assigned.failed"), true);
            }
            return InteractionResultHolder.fail((Object)stack);
        }
        return InteractionResultHolder.pass((Object)stack);
    }

    private void assignRandomAttributes(ItemStack stack) {
        Random random;
        List<AttributeEntry> possiblePositiveAttributes = this.getPositiveAttributeEntries();
        List<AttributeEntry> possibleNegativeAttributes = this.getNegativeAttributeEntries();
        List<AttributeEntry> possibleChanceAttributes = this.getChanceAttributeEntries();
        if (!possiblePositiveAttributes.isEmpty()) {
            AttributeEntry selectedPositiveEntry = this.selectRandomAttributeEntry(possiblePositiveAttributes);
            double positiveValue = this.selectRandomValue(selectedPositiveEntry.getMinValue(), selectedPositiveEntry.getMaxValue());
            this.saveAttributeToNBT(stack, selectedPositiveEntry.getAttribute(), selectedPositiveEntry.getOperation(), positiveValue, "positiveAttribute");
        }
        if (!possibleNegativeAttributes.isEmpty()) {
            AttributeEntry selectedNegativeEntry = this.selectRandomAttributeEntry(possibleNegativeAttributes);
            double negativeValue = this.selectRandomValue(selectedNegativeEntry.getMinValue(), selectedNegativeEntry.getMaxValue());
            this.saveAttributeToNBT(stack, selectedNegativeEntry.getAttribute(), selectedNegativeEntry.getOperation(), negativeValue, "negativeAttribute");
        }
        if ((random = new Random()).nextDouble() <= this.getGlobalChance() && !possibleChanceAttributes.isEmpty()) {
            AttributeEntry selectedChanceEntry = this.selectRandomAttributeEntry(possibleChanceAttributes);
            double chanceValue = this.selectRandomValue(selectedChanceEntry.getMinValue(), selectedChanceEntry.getMaxValue());
            this.saveAttributeToNBT(stack, selectedChanceEntry.getAttribute(), selectedChanceEntry.getOperation(), chanceValue, "chanceAttribute");
        }
    }

    private AttributeEntry selectRandomAttributeEntry(List<AttributeEntry> possibleAttributes) {
        double totalWeight = possibleAttributes.stream().mapToDouble(AttributeEntry::getWeight).sum();
        double randomValue = new Random().nextDouble() * totalWeight;
        for (AttributeEntry entry : possibleAttributes) {
            if (!((randomValue -= entry.getWeight()) <= 0.0)) continue;
            return entry;
        }
        return possibleAttributes.get(possibleAttributes.size() - 1);
    }

    private double selectRandomValue(double minValue, double maxValue) {
        Random random = new Random();
        return minValue + (maxValue - minValue) * random.nextDouble();
    }

    private void saveAttributeToNBT(ItemStack stack, Attribute attribute, AttributeModifier.Operation operation, double value, String tagPrefix) {
        CompoundTag tag = stack.getOrCreateTag();
        CompoundTag attributeTag = new CompoundTag();
        attributeTag.accept("attribute", ForgeRegistries.ATTRIBUTES.getKey((Object)attribute).toString());
        attributeTag.accept("operation", operation.name());
        attributeTag.accept("value", value);
        tag.accept(tagPrefix, (Tag)attributeTag);
    }

    private Multimap<Attribute, AttributeModifier> getAttributesFromNBT(CompoundTag tag, UUID uuid) {
        ImmutableMultimap.Builder attributeBuilder = new ImmutableMultimap.Builder();
        if (tag.contains("positiveAttribute")) {
            CompoundTag positiveTag = tag.getCompound("positiveAttribute");
            this.addAttributeFromNBT((ImmutableMultimap.Builder<Attribute, AttributeModifier>)attributeBuilder, positiveTag, uuid);
        }
        if (tag.contains("negativeAttribute")) {
            CompoundTag negativeTag = tag.getCompound("negativeAttribute");
            this.addAttributeFromNBT((ImmutableMultimap.Builder<Attribute, AttributeModifier>)attributeBuilder, negativeTag, uuid);
        }
        if (tag.contains("chanceAttribute")) {
            CompoundTag chanceTag = tag.getCompound("chanceAttribute");
            this.addAttributeFromNBT((ImmutableMultimap.Builder<Attribute, AttributeModifier>)attributeBuilder, chanceTag, uuid);
        }
        return attributeBuilder.build();
    }

    private void addAttributeFromNBT(ImmutableMultimap.Builder<Attribute, AttributeModifier> attributeBuilder, CompoundTag tag, UUID uuid) {
        Attribute attribute = (Attribute)ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(tag.getString("attribute")));
        try {
            AttributeModifier.Operation operation = AttributeModifier.Operation.valueOf((String)tag.getString("operation"));
            if (attribute != null) {
                AttributeModifier modifier = new AttributeModifier(uuid, attribute.getDescriptionId(), tag.getDouble("value"), operation);
                attributeBuilder.put((Object)attribute, (Object)modifier);
            }
        }
        catch (IllegalArgumentException e) {
            System.err.println("Invalid attribute modifier operation: " + tag.getString("operation"));
        }
    }

    private void playSound(Level level, Player player, SoundEvent soundEvent) {
        if (!level.isClientSide && soundEvent != null) {
            level.gameEvent(null, player.blockPosition(), soundEvent, player.getSoundSource(), 1.0f, 1.0f);
        }
    }

    public Component getName(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.getBoolean("attributesAssigned")) {
            return Component.translatable((String)(stack.setRepairCost().getDescriptionId(stack) + ".assigned"));
        }
        return Component.translatable((String)(stack.setRepairCost().getDescriptionId(stack) + ".unassigned"));
    }

    public static class AttributeEntry {
        private final Attribute attribute;
        private final AttributeModifier.Operation operation;
        private final double minValue;
        private final double maxValue;
        private final double weight;

        public AttributeEntry(Attribute attribute, AttributeModifier.Operation operation, double minValue, double maxValue, double weight) {
            this.attribute = attribute;
            this.operation = operation;
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.weight = weight;
        }

        public Attribute getAttribute() {
            return this.attribute;
        }

        public AttributeModifier.Operation getOperation() {
            return this.operation;
        }

        public double getMinValue() {
            return this.minValue;
        }

        public double getMaxValue() {
            return this.maxValue;
        }

        public double getWeight() {
            return this.weight;
        }
    }
}

