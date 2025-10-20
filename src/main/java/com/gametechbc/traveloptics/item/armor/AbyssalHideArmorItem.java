/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Leviathan.Abyss_Orb_Entity
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.Projectile
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

import com.gametechbc.traveloptics.data_manager.CooldownManager;
import com.gametechbc.traveloptics.entity.armor.abyssal_hide_armor.AbyssalHideArmorModel;
import com.gametechbc.traveloptics.entity.armor.abyssal_hide_armor.AbyssalHideArmorRenderer;
import com.gametechbc.traveloptics.init.TravelopticsSounds;
import com.gametechbc.traveloptics.item.TravelopticsArmorMaterials;
import com.gametechbc.traveloptics.item.UnbreakableImbueableArmor;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Leviathan.Abyss_Orb_Entity;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
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

public class AbyssalHideArmorItem
extends UnbreakableImbueableArmor {
    private static final int COOLDOWN_TICKS = 400;
    private static final double TRACKING_RANGE = 15.0;

    public AbyssalHideArmorItem(ArmorItem.Type slot, Item.Properties settings) {
        super(TravelopticsArmorMaterials.ABYSSAL_HIDE, slot, settings);
    }

    @Override
    protected Set<ArmorItem.Type> getImbuableArmorTypes() {
        return Set.of(ArmorItem.Type.CHESTPLATE);
    }

    @Override
    protected Map<ArmorItem.Type, Integer> getMaxSpellSlots() {
        return Map.of(ArmorItem.Type.CHESTPLATE, 1);
    }

    public void useOn(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        super.useOn(stack, world, entity, slot, selected);
        if (!(entity instanceof Player)) {
            return;
        }
        Player player = (Player)entity;
        if (!this.isWearingFullSet(player)) {
            return;
        }
        ItemStack chestplate = player.shouldRemoveSoulSpeed(ArmorItem.Type.CHESTPLATE.getSlot());
        if (stack != chestplate || chestplate.setRepairCost() != this) {
            return;
        }
        if (!world.isClientSide) {
            if (CooldownManager.isCooldownActive(chestplate)) {
                CooldownManager.tickCooldown(chestplate);
                return;
            }
            if ((double)player.getHealth() <= (double)player.getMaxHealth() * 0.75) {
                this.shootAbyssOrbs(world, (LivingEntity)player);
                world.gameEvent(null, player.blockPosition(), (SoundEvent)TravelopticsSounds.ORBITAL_VOID_PULSE.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
                CooldownManager.setCooldown(chestplate, 400, 400);
            }
        }
    }

    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        super.resolvePage(stack, world, tooltip, flag);
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_hide_armor.tooltip").withStyle(ChatFormatting.GREEN));
        int cooldown = CooldownManager.getCooldown(stack);
        if (cooldown > 0) {
            tooltip.add((Component)Component.selector((String)"item.tooltip.traveloptics.armor_cooldown", (Object[])new Object[]{cooldown / 20}).withStyle(ChatFormatting.GRAY));
        }
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.abyssal_hide_armor.tooltip1"));
        tooltip.add((Component)Component.score((String)""));
    }

    private float calculateAbyssOrbDamage(LivingEntity entity) {
        float enderSpellPower = (float)entity.getStandingEyeHeight((Attribute)AttributeRegistry.ENDER_SPELL_POWER.get());
        float eldritchSpellPower = (float)entity.getStandingEyeHeight((Attribute)AttributeRegistry.ELDRITCH_SPELL_POWER.get());
        return (enderSpellPower + eldritchSpellPower) * 3.0f;
    }

    private void shootAbyssOrbs(Level level, LivingEntity entity) {
        int orbCount = 6;
        float getDamage = this.calculateAbyssOrbDamage(entity);
        double angleBetween = Math.PI * 2 / (double)orbCount;
        for (int i = 0; i < orbCount; ++i) {
            Projectile projectile;
            double angle = angleBetween * (double)i;
            double offsetX = Math.sin(angle) * 6.0;
            double offsetZ = Math.cos(angle) * 6.0;
            double motionScale = 3.0;
            Vec3 motion = new Vec3(offsetX, 0.0, offsetZ).multiply().x(motionScale);
            EntityType entityType = EntityType.tryCast((String)"cataclysm:abyss_orb").orElse(null);
            if (entityType == null || (projectile = (Projectile)entityType.tryCast(level)) == null) continue;
            projectile.moveTo(entity.getX(), entity.getY() + 1.5, entity.getZ(), 0.0f, 0.0f);
            projectile.setIsInPowderSnow(motion.x(), motion.y(), motion.z());
            LivingEntity target = this.findNearestTarget(level, entity);
            if (target != null && projectile instanceof Abyss_Orb_Entity) {
                Abyss_Orb_Entity abyssOrb = (Abyss_Orb_Entity)projectile;
                abyssOrb.setTracking(true);
                abyssOrb.addAdditionalSaveData((Entity)entity);
                abyssOrb.setDamage(getDamage);
                try {
                    Field finalTargetField = Abyss_Orb_Entity.class.getDeclaredField("finalTarget");
                    finalTargetField.setAccessible(true);
                    finalTargetField.set(abyssOrb, target);
                }
                catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
            level.addFreshEntity((Entity)projectile);
        }
    }

    private LivingEntity findNearestTarget(Level level, LivingEntity caster) {
        AABB boundingBox = new AABB(caster.getX() - 15.0, caster.getY() - 15.0, caster.getZ() - 15.0, caster.getX() + 15.0, caster.getY() + 15.0, caster.getZ() + 15.0);
        List possibleTargets = level.getNearbyEntities(LivingEntity.class, boundingBox, entity -> {
            TamableAnimal tamable;
            if (entity == caster) {
                return false;
            }
            if (caster.isAlliedTo((Entity)entity)) {
                return false;
            }
            return !(entity instanceof TamableAnimal) || !(tamable = (TamableAnimal)entity).isTame() || tamable.getOwner() != caster;
        });
        if (possibleTargets.isEmpty()) {
            return null;
        }
        return possibleTargets.stream().min(Comparator.comparingDouble(arg_0 -> ((LivingEntity)caster).getZ(arg_0))).orElse(null);
    }

    private boolean isWearingFullSet(Player player) {
        return player.shouldRemoveSoulSpeed(ArmorItem.Type.HELMET.getSlot()).setRepairCost() instanceof AbyssalHideArmorItem && player.shouldRemoveSoulSpeed(ArmorItem.Type.CHESTPLATE.getSlot()).setRepairCost() instanceof AbyssalHideArmorItem && player.shouldRemoveSoulSpeed(ArmorItem.Type.LEGGINGS.getSlot()).setRepairCost() instanceof AbyssalHideArmorItem && player.shouldRemoveSoulSpeed(ArmorItem.Type.BOOTS.getSlot()).setRepairCost() instanceof AbyssalHideArmorItem;
    }

    @Override
    @OnlyIn(value=Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new AbyssalHideArmorRenderer(new AbyssalHideArmorModel());
    }
}

