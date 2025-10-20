/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.init.ModItems
 *  dev.shadowsoffire.attributeslib.api.ALObjects$Attributes
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.registries.ItemRegistry
 *  net.minecraft.Util
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.LazyLoadedValue
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.item.ArmorItem$Type
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 */
package com.gametechbc.traveloptics.item;

import com.gametechbc.traveloptics.api.init.TravelopticsAttributes;
import com.gametechbc.traveloptics.api.item.armor.TravelopticsArmorMaterial;
import com.gametechbc.traveloptics.config.ArmorConfig;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.github.L_Ender.cataclysm.init.ModItems;
import dev.shadowsoffire.attributeslib.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public enum TravelopticsArmorMaterials implements TravelopticsArmorMaterial
{
    ABYSSAL_HIDE("abyssal_hide", 80, TravelopticsArmorMaterials.abyssalHideArmorMap(), 15, SoundEvents.ARMOR_EQUIP_LEATHER, ((Double)ArmorConfig.abyssalHideArmorToughness.get()).floatValue(), 0.05f, () -> Ingredient.valueFromJson((ItemLike[])new ItemLike[]{(ItemLike)TravelopticsItems.ABYSSAL_SPELLWEAVE_INGOT.get()}), Map.of((Attribute)AttributeRegistry.MAX_MANA.get(), new AttributeModifier("Max Mana", 150.0, AttributeModifier.Operation.ADDITION), (Attribute)AttributeRegistry.ELDRITCH_SPELL_POWER.get(), new AttributeModifier("Eldritch Power", 0.1, AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)AttributeRegistry.ENDER_SPELL_POWER.get(), new AttributeModifier("Ender Power", 0.05, AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)AttributeRegistry.SPELL_POWER.get(), new AttributeModifier("Spell Power", 0.05, AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)ALObjects.Attributes.ARMOR_PIERCE.get(), new AttributeModifier("Armor Pierce", 0.05, AttributeModifier.Operation.ADDITION))),
    PRIMORDIAL_CREST("primordial_crest", 40, TravelopticsArmorMaterials.primordialCrestArmorMap(), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, ((Double)ArmorConfig.primordialCrestArmorToughness.get()).floatValue(), 0.0f, () -> Ingredient.valueFromJson((ItemLike[])new ItemLike[]{(ItemLike)ACItemRegistry.HEAVY_BONE.get()}), Map.of((Attribute)AttributeRegistry.MAX_MANA.get(), new AttributeModifier("Max Mana", 125.0, AttributeModifier.Operation.ADDITION), (Attribute)AttributeRegistry.NATURE_SPELL_POWER.get(), new AttributeModifier("Nature Power", 0.1, AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)AttributeRegistry.SPELL_POWER.get(), new AttributeModifier("Spell Power Power", 0.05, AttributeModifier.Operation.MULTIPLY_BASE))),
    TECTONIC_CREST("tectonic_crest", 80, TravelopticsArmorMaterials.tectonicCrestArmorMap(), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, ((Double)ArmorConfig.tectonicCrestArmorToughness.get()).floatValue(), 0.15f, () -> Ingredient.valueFromJson((ItemLike[])new ItemLike[]{(ItemLike)TravelopticsItems.TECTONIC_INGOT.get()}), Map.of((Attribute)AttributeRegistry.MAX_MANA.get(), new AttributeModifier("Max Mana", 150.0, AttributeModifier.Operation.ADDITION), (Attribute)AttributeRegistry.FIRE_SPELL_POWER.get(), new AttributeModifier("Fire Power", 0.1, AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)AttributeRegistry.NATURE_SPELL_POWER.get(), new AttributeModifier("Nature Power", 0.05, AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)AttributeRegistry.SPELL_POWER.get(), new AttributeModifier("Spell Power", 0.05, AttributeModifier.Operation.MULTIPLY_BASE))),
    CURSED_WRAITHGUARD("cursed_wraithguard", 80, TravelopticsArmorMaterials.cursedWraithguardArmorMap(), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, ((Double)ArmorConfig.cursedWraithguardArmorToughness.get()).floatValue(), 0.05f, () -> Ingredient.valueFromJson((ItemLike[])new ItemLike[]{(ItemLike)ModItems.CURSIUM_INGOT.get()}), Map.of((Attribute)AttributeRegistry.MAX_MANA.get(), new AttributeModifier("Max Mana", 150.0, AttributeModifier.Operation.ADDITION), (Attribute)AttributeRegistry.ICE_SPELL_POWER.get(), new AttributeModifier("Ice Power", 0.1, AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)AttributeRegistry.ELDRITCH_SPELL_POWER.get(), new AttributeModifier("Eldritch Power", 0.05, AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)AttributeRegistry.SPELL_POWER.get(), new AttributeModifier("Spell Power", 0.05, AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)ALObjects.Attributes.ARROW_DAMAGE.get(), new AttributeModifier("Arrow Damage", 0.05, AttributeModifier.Operation.MULTIPLY_BASE))),
    DEEPLING_MAGE("deepling_mage", 38, TravelopticsArmorMaterials.deeplingMageArmorMap(), 15, SoundEvents.ARMOR_EQUIP_LEATHER, ((Double)ArmorConfig.deeplingMageArmorToughness.get()).floatValue(), 0.0f, () -> Ingredient.valueFromJson((ItemLike[])new ItemLike[]{(ItemLike)ItemRegistry.MAGIC_CLOTH.get()}), Map.of((Attribute)AttributeRegistry.MAX_MANA.get(), new AttributeModifier("Max Mana", 125.0, AttributeModifier.Operation.ADDITION), (Attribute)TravelopticsAttributes.AQUA_SPELL_POWER.get(), new AttributeModifier("Aqua Power", 0.1, AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)AttributeRegistry.SPELL_POWER.get(), new AttributeModifier("Base Power", 0.05, AttributeModifier.Operation.MULTIPLY_BASE))),
    MECHANIZED_EXOSKELETON("mechanized_exoskeleton", 80, TravelopticsArmorMaterials.mechanizedExoskeletonArmorMap(), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, ((Double)ArmorConfig.mechanizedExoskeletonArmorToughness.get()).floatValue(), 0.1f, () -> Ingredient.valueFromJson((ItemLike[])new ItemLike[]{(ItemLike)ModItems.WITHERITE_INGOT.get()}), Map.of((Attribute)AttributeRegistry.MAX_MANA.get(), new AttributeModifier("Max Mana", 150.0, AttributeModifier.Operation.ADDITION), (Attribute)AttributeRegistry.LIGHTNING_SPELL_POWER.get(), new AttributeModifier("Lightning Power", 0.15, AttributeModifier.Operation.MULTIPLY_BASE), Attributes.MOVEMENT_SPEED, new AttributeModifier("Movement speed", 0.05, AttributeModifier.Operation.MULTIPLY_BASE))),
    FORLORN_HARBINGER("forlorn_harbinger", 80, TravelopticsArmorMaterials.forlornHarbingerArmorMap(), 15, SoundEvents.ARMOR_EQUIP_LEATHER, ((Double)ArmorConfig.forlornHarbingerArmorToughness.get()).floatValue(), 0.05f, () -> Ingredient.valueFromJson((ItemLike[])new ItemLike[]{(ItemLike)ModItems.WITHERITE_INGOT.get()}), Map.of((Attribute)AttributeRegistry.MAX_MANA.get(), new AttributeModifier("Max Mana", 150.0, AttributeModifier.Operation.ADDITION), (Attribute)AttributeRegistry.BLOOD_SPELL_POWER.get(), new AttributeModifier("Blood Power", 0.1, AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)AttributeRegistry.ELDRITCH_SPELL_POWER.get(), new AttributeModifier("Eldritch Power", 0.05, AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)AttributeRegistry.SPELL_POWER.get(), new AttributeModifier("Spell Power", 0.05, AttributeModifier.Operation.MULTIPLY_BASE)));

    private static final int[] HEALTH_PER_SLOT;
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;
    private final Map<Attribute, AttributeModifier> additionalAttributes;
    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE;

    private TravelopticsArmorMaterials(String pName, int pDurabilityMultiplier, EnumMap<ArmorItem.Type, Integer> protectionMap, int pEnchantmentValue, SoundEvent pSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient, Map<Attribute, AttributeModifier> additionalAttributes) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.protectionFunctionForType = protectionMap;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = new LazyLoadedValue(pRepairIngredient);
        this.additionalAttributes = additionalAttributes;
    }

    public static EnumMap<ArmorItem.Type, Integer> createArmorMap(int helmet, int chestplate, int leggings, int boots) {
        return (EnumMap)Util.isWhitespace(new EnumMap(ArmorItem.Type.class), p_266655_ -> {
            p_266655_.put(ArmorItem.Type.BOOTS, boots);
            p_266655_.put(ArmorItem.Type.LEGGINGS, leggings);
            p_266655_.put(ArmorItem.Type.CHESTPLATE, chestplate);
            p_266655_.put(ArmorItem.Type.HELMET, helmet);
        });
    }

    public static EnumMap<ArmorItem.Type, Integer> abyssalHideArmorMap() {
        return TravelopticsArmorMaterials.createArmorMap((Integer)ArmorConfig.abyssalHideHelmetArmorValue.get(), (Integer)ArmorConfig.abyssalHideChestplateArmorValue.get(), (Integer)ArmorConfig.abyssalHideLeggingsArmorValue.get(), (Integer)ArmorConfig.abyssalHideBootsArmorValue.get());
    }

    public static EnumMap<ArmorItem.Type, Integer> primordialCrestArmorMap() {
        return TravelopticsArmorMaterials.createArmorMap((Integer)ArmorConfig.primordialCrestHelmetArmorValue.get(), (Integer)ArmorConfig.primordialCrestChestplateArmorValue.get(), (Integer)ArmorConfig.primordialCrestLeggingsArmorValue.get(), (Integer)ArmorConfig.primordialCrestBootsArmorValue.get());
    }

    public static EnumMap<ArmorItem.Type, Integer> tectonicCrestArmorMap() {
        return TravelopticsArmorMaterials.createArmorMap((Integer)ArmorConfig.tectonicCrestHelmetArmorValue.get(), (Integer)ArmorConfig.tectonicCrestChestplateArmorValue.get(), (Integer)ArmorConfig.tectonicCrestLeggingsArmorValue.get(), (Integer)ArmorConfig.tectonicCrestBootsArmorValue.get());
    }

    public static EnumMap<ArmorItem.Type, Integer> cursedWraithguardArmorMap() {
        return TravelopticsArmorMaterials.createArmorMap((Integer)ArmorConfig.cursedWraithguardHelmetArmorValue.get(), (Integer)ArmorConfig.cursedWraithguardChestplateArmorValue.get(), (Integer)ArmorConfig.cursedWraithguardLeggingsArmorValue.get(), (Integer)ArmorConfig.cursedWraithguardBootsArmorValue.get());
    }

    public static EnumMap<ArmorItem.Type, Integer> mechanizedExoskeletonArmorMap() {
        return TravelopticsArmorMaterials.createArmorMap((Integer)ArmorConfig.mechanizedExoskeletonHelmetArmorValue.get(), (Integer)ArmorConfig.mechanizedExoskeletonChestplateArmorValue.get(), (Integer)ArmorConfig.mechanizedExoskeletonLeggingsArmorValue.get(), (Integer)ArmorConfig.mechanizedExoskeletonBootsArmorValue.get());
    }

    public static EnumMap<ArmorItem.Type, Integer> deeplingMageArmorMap() {
        return TravelopticsArmorMaterials.createArmorMap((Integer)ArmorConfig.deeplingMageHelmetArmorValue.get(), (Integer)ArmorConfig.deeplingMageChestplateArmorValue.get(), (Integer)ArmorConfig.deeplingMageLeggingsArmorValue.get(), (Integer)ArmorConfig.deeplingMageBootsArmorValue.get());
    }

    public static EnumMap<ArmorItem.Type, Integer> forlornHarbingerArmorMap() {
        return TravelopticsArmorMaterials.createArmorMap((Integer)ArmorConfig.forlornHarbingerHelmetArmorValue.get(), (Integer)ArmorConfig.forlornHarbingerChestplateArmorValue.get(), (Integer)ArmorConfig.forlornHarbingerLeggingsArmorValue.get(), (Integer)ArmorConfig.forlornHarbingerBootsArmorValue.get());
    }

    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return HEALTH_PER_SLOT[pSlot.getIndex()] * this.durabilityMultiplier;
    }

    public int getEnchantmentValue(ArmorItem.Type p_266745_) {
        return HEALTH_FUNCTION_FOR_TYPE.get(p_266745_) * this.durabilityMultiplier;
    }

    public int getEquipSound(ArmorItem.Type p_266752_) {
        return this.protectionFunctionForType.get(p_266752_);
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    @Override
    public Map<Attribute, AttributeModifier> getAdditionalAttributes() {
        return this.additionalAttributes;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    static {
        HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
        HEALTH_FUNCTION_FOR_TYPE = (EnumMap)Util.isWhitespace(new EnumMap(ArmorItem.Type.class), p_266653_ -> {
            p_266653_.put(ArmorItem.Type.BOOTS, 13);
            p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
            p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
            p_266653_.put(ArmorItem.Type.HELMET, 11);
        });
    }
}

