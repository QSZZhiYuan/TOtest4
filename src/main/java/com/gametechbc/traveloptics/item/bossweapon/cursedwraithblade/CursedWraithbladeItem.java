/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.item.UniqueItem
 *  io.redspace.ironsspellbooks.util.ItemPropertiesHelper
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Tier
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 */
package com.gametechbc.traveloptics.item.bossweapon.cursedwraithblade;

import com.gametechbc.traveloptics.config.WeaponConfig;
import com.gametechbc.traveloptics.data_manager.SpiritPointsManager;
import com.gametechbc.traveloptics.data_manager.SwingCounterManager;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.item.bossweapon.cursedwraithblade.CursedWraithbladeAbility;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.item.UniqueItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

public class CursedWraithbladeItem
extends MagicSwordItem
implements UniqueItem {
    private static final CursedWraithbladeAbility ABILITY = new CursedWraithbladeAbility();

    public CursedWraithbladeItem(SpellDataRegistryHolder[] imbuedSpells) {
        super(new Tier(){

            public int getUses() {
                return (Integer)WeaponConfig.cursedWraithbladeDurability.get();
            }

            public float getSpeed() {
                return 0.0f;
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
                return Ingredient.valueFromJson((ItemStack[])new ItemStack[]{new ItemStack((ItemLike)TravelopticsItems.PYRO_SPELLWEAVE_INGOT.get())});
            }
        }, ((Double)WeaponConfig.cursedWraithbladeDamage.get()).doubleValue(), ((Double)WeaponConfig.cursedWraithbladeAttackSpeed.get()).doubleValue(), imbuedSpells, Map.of((Attribute)AttributeRegistry.ICE_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Weapon Modifier", ((Double)WeaponConfig.cursedWraithbladeIceSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)AttributeRegistry.ELDRITCH_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("412b5a66-2b43-4c18-ab05-6de0bb4d64d3"), "Weapon Modifier", ((Double)WeaponConfig.cursedWraithbladeEldritchSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE)), ItemPropertiesHelper.hidden((int)1).requiredFeatures(TravelopticsItems.RARITY_CURSED));
    }

    public boolean canBeDepleted() {
        return (Boolean)WeaponConfig.weaponsShouldBeBreakable.get();
    }

    public boolean isDamageable(ItemStack stack) {
        return (Boolean)WeaponConfig.weaponsShouldBeBreakable.get();
    }

    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    public boolean getDefaultAttributeModifiers(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player) {
            Player player = (Player)attacker;
            int swingCounter = SwingCounterManager.getSwingCount(stack);
            SpiritPointsManager.addSpiritPoints(stack, player, switch (swingCounter) {
                case 1 -> 5;
                case 2 -> 10;
                case 3 -> 15;
                default -> 5;
            });
            SwingCounterManager.incrementSwingCount(stack, 3);
            MagicManager.spawnParticles((Level)target.level(), (ParticleOptions)ParticleTypes.SCULK_SOUL, (double)target.getX(), (double)target.getY(), (double)target.getZ(), (int)30, (double)0.0, (double)0.0, (double)0.0, (double)0.3, (boolean)false);
        }
        return super.getDefaultAttributeModifiers(stack, target, attacker);
    }

    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        ABILITY.appendHoverText(stack, world, tooltip, flag, 0);
        super.resolvePage(stack, world, tooltip, flag);
    }
}

