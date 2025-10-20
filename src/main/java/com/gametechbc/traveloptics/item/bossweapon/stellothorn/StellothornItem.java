/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder
 *  io.redspace.ironsspellbooks.item.UniqueItem
 *  io.redspace.ironsspellbooks.util.ItemPropertiesHelper
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
 *  net.minecraft.network.chat.Component
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.stats.Stats
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow$Pickup
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Tier
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.item.UseAnim
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.gametechbc.traveloptics.item.bossweapon.stellothorn;

import com.gametechbc.traveloptics.config.WeaponConfig;
import com.gametechbc.traveloptics.entity.item.stellothorn.StellothornRenderer;
import com.gametechbc.traveloptics.entity.projectiles.stellothorn_projectile.StellothornProjectileEntity;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.item.UnbreakableGeoMagicSword;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.item.UniqueItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class StellothornItem
extends UnbreakableGeoMagicSword
implements UniqueItem {
    private static ItemDisplayContext transformType;

    public StellothornItem(SpellDataRegistryHolder[] imbuedSpells) {
        super(new Tier(){

            public int getUses() {
                return (Integer)WeaponConfig.stellothornDurability.get();
            }

            public float getSpeed() {
                return 2.0f;
            }

            public float getAttackDamageBonus() {
                return 0.0f;
            }

            public int getLevel() {
                return 1;
            }

            public int getEnchantmentValue() {
                return 20;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.valueFromJson((ItemStack[])new ItemStack[]{new ItemStack((ItemLike)TravelopticsItems.ABYSSAL_SPELLWEAVE_INGOT.get())});
            }
        }, (Double)WeaponConfig.stellothornDamage.get(), (Double)WeaponConfig.stellothornAttackSpeed.get(), imbuedSpells, Map.of((Attribute)AttributeRegistry.ENDER_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("8ff095c4-a26d-4d28-bc06-1a908b3577aa"), "Weapon Modifier", ((Double)WeaponConfig.stellothornEnderSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)AttributeRegistry.ELDRITCH_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("8ff095c4-a26d-4d28-bc06-1a908b3577aa"), "Weapon Modifier", ((Double)WeaponConfig.stellothornEldritchSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE)), ItemPropertiesHelper.hidden((int)1).requiredFeatures(TravelopticsItems.RARITY_VOID));
    }

    public boolean getDefaultAttributeModifiers(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return super.getDefaultAttributeModifiers(stack, target, attacker);
    }

    public UseAnim getEnchantmentValue(ItemStack p_77661_1_) {
        return UseAnim.BOW;
    }

    public int getUseDuration(ItemStack p_77626_1_) {
        return 72000;
    }

    public InteractionResultHolder<ItemStack> resolvePage(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getStandingEyeHeight(interactionHand);
        if (!itemstack.isDamageableItem() || itemstack.getDamageValue() < itemstack.getMaxDamage() - 1) {
            player.vehicleCanSprint(interactionHand);
            return InteractionResultHolder.consume((Object)itemstack);
        }
        return InteractionResultHolder.pass((Object)itemstack);
    }

    public void getDefaultAttributeModifiers(ItemStack itemStack, Level level, LivingEntity livingEntity, int i1) {
        if (livingEntity instanceof Player) {
            Player player = (Player)livingEntity;
            int i = this.getUseDuration(itemStack) - i1;
            float f = this.getPowerForTime(i);
            if ((double)f > 0.1) {
                itemStack.onDestroyed(1, livingEntity, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                this.spawnStellothornProjectile(level, player, itemStack, f);
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
                player.getStandingEyeHeight(Stats.ITEM_USED.getTranslationKey((Object)this));
            }
        }
    }

    protected void spawnStellothornProjectile(Level level, Player player, ItemStack stack, float chargePower) {
        if (!level.isClientSide) {
            StellothornProjectileEntity spearEntity = new StellothornProjectileEntity(level, (LivingEntity)player, stack);
            spearEntity.setOriginalSlot(player.getInventory().getTimesChanged);
            spearEntity.mayInteract((Entity)player, player.getXRot(), player.getYRot(), 0.0f, chargePower * 1.8f, 1.0f);
            if (player.getAbilities().instabuild) {
                spearEntity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            }
            spearEntity.setTridentDamage(((Double)WeaponConfig.stellothornDamage.get()).floatValue());
            level.addFreshEntity((Entity)spearEntity);
        }
        level.getChunk(null, (Entity)player, (SoundEvent)TravelopticsSounds.NIGHTWARDEN_SWING_HEAVY.get(), SoundSource.PLAYERS, 0.8f, 1.0f);
    }

    public float getPowerForTime(int i) {
        float f = (float)i / 20.0f;
        if ((f = (f * f + f * 2.0f) / 3.0f) > 1.0f) {
            f = 1.0f;
        }
        return f;
    }

    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }

    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add((Component)Component.score((String)""));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.stellothorn.tooltip").withStyle(ChatFormatting.GREEN));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.stellothorn.tooltip1"));
        if (Screen.hasShiftDown()) {
            tooltip.add((Component)Component.score((String)""));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.weapons.evolution_benefits.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.stellothorn.evo_one.inactive.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.stellothorn.evo_two.inactive.tooltip"));
            tooltip.add((Component)Component.translatable((String)"item.traveloptics.stellothorn.evo_three.inactive.tooltip"));
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
        return new StellothornRenderer();
    }
}

