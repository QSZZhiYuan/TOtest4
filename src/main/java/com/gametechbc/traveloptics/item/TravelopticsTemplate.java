/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.Util
 *  net.minecraft.network.chat.CommonComponents
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.item;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class TravelopticsTemplate
extends Item {
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final String DESCRIPTION_ID = Util.isWhitespace((String)"item", (ResourceLocation)new ResourceLocation("smithing_template"));
    private static final Component INGREDIENTS_TITLE = Component.translatable((String)Util.isWhitespace((String)"item", (ResourceLocation)new ResourceLocation("smithing_template.ingredients"))).withStyle(TITLE_FORMAT);
    private static final Component APPLIES_TO_TITLE = Component.translatable((String)Util.isWhitespace((String)"item", (ResourceLocation)new ResourceLocation("smithing_template.applies_to"))).withStyle(TITLE_FORMAT);
    private static final Component ABYSSAL_UPGRADE = Component.translatable((String)"item.traveloptics.abyssal_upgrade.desc").withStyle(TITLE_FORMAT);
    private static final Component ABYSSAL_UPGRADE_APPLIES_TO = Component.translatable((String)"item.traveloptics.abyssal_upgrade.applies_to.desc").withStyle(DESCRIPTION_FORMAT);
    private static final Component ABYSSAL_UPGRADE_INGREDIENTS = Component.translatable((String)"item.traveloptics.abyssal_upgrade.ingredients.desc").withStyle(DESCRIPTION_FORMAT);
    private static final Component TECTONIC_UPGRADE = Component.translatable((String)"item.traveloptics.tectonic_upgrade.desc").withStyle(TITLE_FORMAT);
    private static final Component TECTONIC_UPGRADE_APPLIES_TO = Component.translatable((String)"item.traveloptics.tectonic_upgrade.applies_to.desc").withStyle(DESCRIPTION_FORMAT);
    private static final Component TECTONIC_UPGRADE_INGREDIENTS = Component.translatable((String)"item.traveloptics.tectonic_upgrade.ingredients.desc").withStyle(DESCRIPTION_FORMAT);
    private static final Component WITHERITE_UPGRADE = Component.translatable((String)"item.traveloptics.witherite_upgrade.desc").withStyle(TITLE_FORMAT);
    private static final Component WITHERITE_UPGRADE_APPLIES_TO = Component.translatable((String)"item.traveloptics.witherite_upgrade.applies_to.desc").withStyle(DESCRIPTION_FORMAT);
    private static final Component WITHERITE_UPGRADE_INGREDIENTS = Component.translatable((String)"item.traveloptics.witherite_upgrade.ingredients.desc").withStyle(DESCRIPTION_FORMAT);
    private static final Component DARKNESS_UPGRADE = Component.translatable((String)"item.traveloptics.darkness_upgrade.desc").withStyle(TITLE_FORMAT);
    private static final Component DARKNESS_UPGRADE_APPLIES_TO = Component.translatable((String)"item.traveloptics.darkness_upgrade.applies_to.desc").withStyle(DESCRIPTION_FORMAT);
    private static final Component DARKNESS_UPGRADE_INGREDIENTS = Component.translatable((String)"item.traveloptics.darkness_upgrade.ingredients.desc").withStyle(DESCRIPTION_FORMAT);
    private static final Component ABYSSAL_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable((String)"item.traveloptics.abyssal_upgrade.base_slot.desc");
    private static final Component ABYSSAL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable((String)"item.traveloptics.abyssal_upgrade.additions_slot.desc");
    private static final ResourceLocation EMPTY_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_INGOT = new ResourceLocation("item/empty_slot_ingot");
    private final Component appliesTo;
    private final Component ingredients;
    private final Component upgradeDescription;
    private final Component baseSlotDescription;
    private final Component additionsSlotDescription;
    private final List<ResourceLocation> baseSlotEmptyIcons;
    private final List<ResourceLocation> additionalSlotEmptyIcons;

    public TravelopticsTemplate(Component p_266834_, Component p_267043_, Component p_267048_, Component p_267278_, Component p_267090_, List<ResourceLocation> p_266755_, List<ResourceLocation> p_267060_) {
        super(new Item.Properties().requiredFeatures());
        this.appliesTo = p_266834_;
        this.ingredients = p_267043_;
        this.upgradeDescription = p_267048_;
        this.baseSlotDescription = p_267278_;
        this.additionsSlotDescription = p_267090_;
        this.baseSlotEmptyIcons = p_266755_;
        this.additionalSlotEmptyIcons = p_267060_;
    }

    public static TravelopticsTemplate createAbyssalUpgradeTemplate() {
        return new TravelopticsTemplate(ABYSSAL_UPGRADE_APPLIES_TO, ABYSSAL_UPGRADE_INGREDIENTS, ABYSSAL_UPGRADE, ABYSSAL_UPGRADE_BASE_SLOT_DESCRIPTION, ABYSSAL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, TravelopticsTemplate.createUpgradeIconList(), TravelopticsTemplate.createUpgradeMaterialList());
    }

    public static TravelopticsTemplate createTectonicUpgradeTemplate() {
        return new TravelopticsTemplate(TECTONIC_UPGRADE_APPLIES_TO, TECTONIC_UPGRADE_INGREDIENTS, TECTONIC_UPGRADE, ABYSSAL_UPGRADE_BASE_SLOT_DESCRIPTION, ABYSSAL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, TravelopticsTemplate.createUpgradeIconList(), TravelopticsTemplate.createUpgradeMaterialList());
    }

    public static TravelopticsTemplate createWitheriteUpgradeTemplate() {
        return new TravelopticsTemplate(WITHERITE_UPGRADE_APPLIES_TO, WITHERITE_UPGRADE_INGREDIENTS, WITHERITE_UPGRADE, ABYSSAL_UPGRADE_BASE_SLOT_DESCRIPTION, ABYSSAL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, TravelopticsTemplate.createUpgradeIconList(), TravelopticsTemplate.createUpgradeMaterialList());
    }

    public static TravelopticsTemplate createDarknessUpgradeTemplate() {
        return new TravelopticsTemplate(DARKNESS_UPGRADE_APPLIES_TO, DARKNESS_UPGRADE_INGREDIENTS, DARKNESS_UPGRADE, ABYSSAL_UPGRADE_BASE_SLOT_DESCRIPTION, ABYSSAL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, TravelopticsTemplate.createUpgradeIconList(), TravelopticsTemplate.createUpgradeMaterialList());
    }

    private static List<ResourceLocation> createUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_BOOTS);
    }

    private static List<ResourceLocation> createUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }

    public void resolvePage(ItemStack p_267313_, @Nullable Level p_266896_, List<Component> p_266820_, TooltipFlag p_266857_) {
        super.resolvePage(p_267313_, p_266896_, p_266820_, p_266857_);
        p_266820_.add(this.upgradeDescription);
        p_266820_.add(CommonComponents.joinLines);
        p_266820_.add(APPLIES_TO_TITLE);
        p_266820_.add((Component)CommonComponents.joinLines().append(this.appliesTo));
        p_266820_.add(INGREDIENTS_TITLE);
        p_266820_.add((Component)CommonComponents.joinLines().append(this.ingredients));
    }

    public Component getBaseSlotDescription() {
        return this.baseSlotDescription;
    }

    public Component getAdditionSlotDescription() {
        return this.additionsSlotDescription;
    }

    public List<ResourceLocation> getBaseSlotEmptyIcons() {
        return this.baseSlotEmptyIcons;
    }

    public List<ResourceLocation> getAdditionalSlotEmptyIcons() {
        return this.additionalSlotEmptyIcons;
    }

    public String emptyContents() {
        return DESCRIPTION_ID;
    }
}

