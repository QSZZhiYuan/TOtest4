/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.ImmutableMultimap
 *  com.google.common.collect.ImmutableMultimap$Builder
 *  com.google.common.collect.Multimap
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ArmorItem
 *  net.minecraft.world.item.ArmorItem$Type
 *  net.minecraft.world.item.ArmorMaterial
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.client.extensions.common.IClientItemExtensions
 *  org.jetbrains.annotations.NotNull
 *  software.bernie.geckolib.animatable.GeoItem
 *  software.bernie.geckolib.core.animatable.GeoAnimatable
 *  software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
 *  software.bernie.geckolib.core.animation.AnimatableManager$ControllerRegistrar
 *  software.bernie.geckolib.core.animation.AnimationController
 *  software.bernie.geckolib.core.animation.AnimationState
 *  software.bernie.geckolib.core.animation.RawAnimation
 *  software.bernie.geckolib.core.object.PlayState
 *  software.bernie.geckolib.renderer.GeoArmorRenderer
 *  software.bernie.geckolib.util.GeckoLibUtil
 */
package com.gametechbc.traveloptics.api.item;

import com.gametechbc.traveloptics.api.item.armor.TravelopticsArmorMaterial;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class GeoArmorItem
extends ArmorItem
implements GeoItem {
    private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{UUID.fromString("674a93d4-a8d3-11ef-a83e-325096b39f47"), UUID.fromString("7326ac88-a8d3-11ef-b1d4-325096b39f47"), UUID.fromString("7743fa8c-a8d3-11ef-8e38-325096b39f47"), UUID.fromString("7b936974-a8d3-11ef-9507-325096b39f47")};
    private final Multimap<Attribute, AttributeModifier> ARMOR_ATTRIBUTES;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP = new ImmutableMap.Builder().build();

    public GeoArmorItem(TravelopticsArmorMaterial material, ArmorItem.Type type, Item.Properties settings) {
        super((ArmorMaterial)material, type, settings);
        ImmutableMultimap.Builder builder = ImmutableMultimap.builder();
        float defense = material.getEquipSound(type);
        float toughness = material.getToughness();
        float knockbackResistance = material.getKnockbackResistance();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[type.getSlot().getIndex()];
        builder.put((Object)Attributes.ARMOR, (Object)new AttributeModifier(uuid, "Armor modifier", (double)defense, AttributeModifier.Operation.ADDITION));
        builder.put((Object)Attributes.ARMOR_TOUGHNESS, (Object)new AttributeModifier(uuid, "Armor toughness", (double)toughness, AttributeModifier.Operation.ADDITION));
        if (knockbackResistance > 0.0f) {
            builder.put((Object)Attributes.KNOCKBACK_RESISTANCE, (Object)new AttributeModifier(uuid, "Armor knockback resistance", (double)knockbackResistance, AttributeModifier.Operation.ADDITION));
        }
        for (Map.Entry<Attribute, AttributeModifier> modifierEntry : material.getAdditionalAttributes().entrySet()) {
            AttributeModifier atr = modifierEntry.getValue();
            atr = new AttributeModifier(uuid, atr.getName(), atr.getAmount(), atr.getOperation());
            builder.put((Object)modifierEntry.getKey(), (Object)atr);
        }
        this.ARMOR_ATTRIBUTES = builder.build();
    }

    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        if (pEquipmentSlot == this.getType.getSlot()) {
            return this.ARMOR_ATTRIBUTES;
        }
        return ImmutableMultimap.of();
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{new AnimationController((GeoAnimatable)this, "controller", 20, this::predicate)});
    }

    private PlayState predicate(AnimationState<GeoArmorItem> TravelopticsArmorItemAnimationState) {
        TravelopticsArmorItemAnimationState.getController().setAnimation(RawAnimation.begin().thenLoop("idle"));
        return PlayState.CONTINUE;
    }

    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (!world.isClientSide() && this.hasFullSuitOfArmorOn(player)) {
            this.evaluateArmorEffects(player);
        }
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();
            if (!this.hasCorrectArmorOn(mapArmorMaterial, player)) continue;
            this.addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial, MobEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.recreateFromPacket(mapStatusEffect.compareTo());
        if (this.hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            player.getStandingEyeHeight(new MobEffectInstance(mapStatusEffect.compareTo(), mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));
        }
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().setChanged(0);
        ItemStack leggings = player.getInventory().setChanged(1);
        ItemStack breastplate = player.getInventory().setChanged(2);
        ItemStack helmet = player.getInventory().setChanged(3);
        return !helmet.onUseTick() && !breastplate.onUseTick() && !leggings.onUseTick() && !boots.onUseTick();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        for (ItemStack armorStack : player.getInventory().tick) {
            if (armorStack.setRepairCost() instanceof ArmorItem) continue;
            return false;
        }
        ArmorItem boots = (ArmorItem)player.getInventory().setChanged(0).setRepairCost();
        ArmorItem leggings = (ArmorItem)player.getInventory().setChanged(1).setRepairCost();
        ArmorItem breastplate = (ArmorItem)player.getInventory().setChanged(2).setRepairCost();
        ArmorItem helmet = (ArmorItem)player.getInventory().setChanged(3).setRepairCost();
        return helmet.getMaterial() == material && breastplate.getMaterial() == material && leggings.getMaterial() == material && boots.getMaterial() == material;
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions(){
            private GeoArmorRenderer<?> renderer;

            @NotNull
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null) {
                    this.renderer = GeoArmorItem.this.supplyRenderer();
                }
                this.renderer.prepForRender((Entity)livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }

    @OnlyIn(value=Dist.CLIENT)
    public abstract GeoArmorRenderer<?> supplyRenderer();
}

