/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.SchoolType
 *  io.redspace.ironsspellbooks.capabilities.magic.MagicManager
 *  io.redspace.ironsspellbooks.damage.DamageSources
 *  io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.network.chat.Component
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ArmorItem$Type
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  software.bernie.geckolib.renderer.GeoArmorRenderer
 */
package com.gametechbc.traveloptics.item.armor;

import com.gametechbc.traveloptics.api.item.AbstractImbuableArmorItem;
import com.gametechbc.traveloptics.data_manager.CooldownManager;
import com.gametechbc.traveloptics.entity.armor.primordial_crest_armor.PrimordialCrestArmorArmorRenderer;
import com.gametechbc.traveloptics.entity.armor.primordial_crest_armor.PrimordialCrestArmorModel;
import com.gametechbc.traveloptics.item.TravelopticsArmorMaterials;
import com.gametechbc.traveloptics.util.TravelopticsDamageTypes;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class PrimordialCrestArmorItem
extends AbstractImbuableArmorItem {
    private static final double KNOCKBACK_RADIUS = 5.0;
    private static final float KNOCKBACK_FORCE = 2.0f;
    private static final float DAMAGE_AMOUNT = 5.0f;
    private static final int COOLDOWN_TICKS = 400;

    public PrimordialCrestArmorItem(ArmorItem.Type slot, Item.Properties settings) {
        super(TravelopticsArmorMaterials.PRIMORDIAL_CREST, slot, settings);
    }

    @Override
    protected Set<ArmorItem.Type> getImbuableArmorTypes() {
        return Set.of(ArmorItem.Type.CHESTPLATE);
    }

    @Override
    protected Map<ArmorItem.Type, Integer> getMaxSpellSlots() {
        return Map.of(ArmorItem.Type.CHESTPLATE, 1);
    }

    public void useOn(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.useOn(stack, level, entity, slot, selected);
        if (!(entity instanceof Player)) {
            return;
        }
        Player player = (Player)entity;
        ItemStack chestplate = player.shouldRemoveSoulSpeed(EquipmentSlot.CHEST);
        if (stack != chestplate || chestplate.setRepairCost() != this) {
            return;
        }
        if (!level.isClientSide && this.isWearingFullSet(player)) {
            if (CooldownManager.isCooldownActive(chestplate)) {
                CooldownManager.tickCooldown(chestplate);
                return;
            }
            if (player.hurtTime > 0) {
                this.triggerKnockbackAndDamage(player);
                this.setCooldownOnChestplate(player);
            }
        }
    }

    private void triggerKnockbackAndDamage(Player player) {
        Level level = player.level();
        AABB boundingBox = new AABB(player.getX() - 5.0, player.getY() - 5.0, player.getZ() - 5.0, player.getX() + 5.0, player.getY() + 5.0, player.getZ() + 5.0);
        List nearbyEntities = level.getNearbyEntities(LivingEntity.class, boundingBox, entity -> {
            TamableAnimal tamable;
            if (entity == player) {
                return false;
            }
            if (player.isAlliedTo((Entity)entity)) {
                return false;
            }
            return !(entity instanceof TamableAnimal) || !(tamable = (TamableAnimal)entity).isTame() || tamable.getOwner() != player;
        });
        double fireSpellPower = player.getStandingEyeHeight((Attribute)AttributeRegistry.FIRE_SPELL_POWER.get());
        float scaledDamage = (float)(5.0 + fireSpellPower * 3.0);
        for (LivingEntity entity2 : nearbyEntities) {
            DamageSource knockbackDamageSource = new DamageSource(DamageSources.getHolderFromResource((Entity)entity2, TravelopticsDamageTypes.PRIMORDIAL_CREST), (Entity)player, null);
            DamageSources.ignoreNextKnockback((LivingEntity)entity2);
            Vec3 knockbackVector = entity2.position().multiply(player.position()).multiply().x(2.0);
            entity2.setDeltaMovement(knockbackVector);
            entity2.sendSystemMessage(knockbackDamageSource, scaledDamage);
        }
        level.getChunk(null, player.getX(), player.getY(), player.getZ(), (SoundEvent)ACSoundRegistry.LUXTRUCTOSAURUS_STOMP.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
        MagicManager.spawnParticles((Level)level, (ParticleOptions)new BlastwaveParticleOptions(((SchoolType)SchoolRegistry.HOLY.get()).getTargetingColor(), 5.0f), (double)player.getX(), (double)(player.getY() + (double)0.165f), (double)player.getZ(), (int)1, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (boolean)true);
    }

    private void setCooldownOnChestplate(Player player) {
        ItemStack chestplate = player.shouldRemoveSoulSpeed(ArmorItem.Type.CHESTPLATE.getSlot());
        if (chestplate.setRepairCost() instanceof PrimordialCrestArmorItem) {
            CooldownManager.setCooldown(chestplate, 400, 400);
        }
    }

    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        super.resolvePage(stack, world, tooltip, flag);
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.primordial_crest_armor.tooltip").withStyle(ChatFormatting.GREEN));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.primordial_crest_armor.tooltip1"));
        int cooldown = CooldownManager.getCooldown(stack);
        if (cooldown > 0) {
            tooltip.add((Component)Component.selector((String)"item.tooltip.traveloptics.armor_cooldown", (Object[])new Object[]{cooldown / 20}).withStyle(ChatFormatting.GRAY));
        }
        tooltip.add((Component)Component.score((String)""));
    }

    private boolean isWearingFullSet(Player player) {
        return player.shouldRemoveSoulSpeed(ArmorItem.Type.HELMET.getSlot()).setRepairCost() instanceof PrimordialCrestArmorItem && player.shouldRemoveSoulSpeed(ArmorItem.Type.CHESTPLATE.getSlot()).setRepairCost() instanceof PrimordialCrestArmorItem && player.shouldRemoveSoulSpeed(ArmorItem.Type.LEGGINGS.getSlot()).setRepairCost() instanceof PrimordialCrestArmorItem && player.shouldRemoveSoulSpeed(ArmorItem.Type.BOOTS.getSlot()).setRepairCost() instanceof PrimordialCrestArmorItem;
    }

    @Override
    @OnlyIn(value=Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new PrimordialCrestArmorArmorRenderer(new PrimordialCrestArmorModel());
    }
}

