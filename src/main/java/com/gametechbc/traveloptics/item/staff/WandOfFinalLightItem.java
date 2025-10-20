/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.magic.MagicData
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer
 *  io.redspace.ironsspellbooks.api.spells.ISpellContainer
 *  io.redspace.ironsspellbooks.item.UniqueItem
 *  io.redspace.ironsspellbooks.util.ItemPropertiesHelper
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  software.bernie.geckolib.animatable.GeoItem
 *  software.bernie.geckolib.animatable.SingletonGeoAnimatable
 *  software.bernie.geckolib.core.animatable.GeoAnimatable
 *  software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
 *  software.bernie.geckolib.core.animation.AnimatableManager$ControllerRegistrar
 *  software.bernie.geckolib.core.animation.AnimationController
 *  software.bernie.geckolib.core.animation.RawAnimation
 *  software.bernie.geckolib.core.object.PlayState
 *  software.bernie.geckolib.util.GeckoLibUtil
 */
package com.gametechbc.traveloptics.item.staff;

import com.gametechbc.traveloptics.api.item.GeoStaffItem;
import com.gametechbc.traveloptics.config.SpellsConfig;
import com.gametechbc.traveloptics.entity.item.wand_of_final_light.WandOfFinalLightRenderer;
import com.gametechbc.traveloptics.init.TravelopticsItems;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.item.UniqueItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class WandOfFinalLightItem
extends GeoStaffItem
implements UniqueItem,
IPresetSpellContainer {
    public static ItemDisplayContext transformType;
    private static final RawAnimation SUPERNOVA_ANIMATION;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);

    public WandOfFinalLightItem() {
        super(ItemPropertiesHelper.equipment().requiredFeatures(1).requiredFeatures(TravelopticsItems.RARITY_VOID), (Double)SpellsConfig.wandOfFinalLightDamage.get(), (Double)SpellsConfig.wandOfFinalLightAttackSpeed.get(), Map.of((Attribute)AttributeRegistry.ENDER_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ((Double)SpellsConfig.wandOfFinalLightEnderSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ((Double)SpellsConfig.wandOfFinalLightCooldownReduction.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ((Double)SpellsConfig.wandOfFinalLightCastTimeReduction.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE)));
        SingletonGeoAnimatable.registerSyncedAnimatable((GeoAnimatable)this);
    }

    public void useOn(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.useOn(stack, level, entity, slot, selected);
        if (entity instanceof Player) {
            Player player = (Player)entity;
            if (level instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel)level;
                MagicData magicData = MagicData.getPlayerMagicData((LivingEntity)player);
                if (magicData.isCasting() && "traveloptics:supernova".equals(magicData.getCastingSpellId())) {
                    ItemStack mainHand = player.getMainHandItem();
                    this.triggerAnim((Entity)player, GeoItem.getOrAssignId((ItemStack)mainHand, (ServerLevel)serverLevel), "supernova", "supernova_animation");
                }
            }
        }
    }

    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }
        if (!ISpellContainer.isSpellContainer((ItemStack)itemStack)) {
            ISpellContainer spellContainer = ISpellContainer.create((int)1, (boolean)true, (boolean)false);
            spellContainer.addSpell((AbstractSpell)TravelopticsSpells.SUPERNOVA_SPELL.get(), 1, true, itemStack);
            spellContainer.save(itemStack);
        }
    }

    public void resolvePage(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        super.resolvePage(stack, world, tooltip, flag);
        tooltip.add((Component)Component.score((String)""));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.supernova_staff.tooltip").withStyle(ChatFormatting.GOLD));
        tooltip.add((Component)Component.translatable((String)"item.traveloptics.supernova_staff.tooltip1").withStyle(ChatFormatting.GRAY));
        tooltip.add((Component)Component.score((String)""));
    }

    public void getTransformType(ItemDisplayContext type) {
        transformType = type;
    }

    @Override
    @OnlyIn(value=Dist.CLIENT)
    protected BlockEntityWithoutLevelRenderer getRenderer() {
        return new WandOfFinalLightRenderer();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        super.registerControllers(controllers);
        controllers.add(new AnimationController[]{new AnimationController((GeoAnimatable)this, "supernova", 5, state -> PlayState.STOP).triggerableAnim("supernova_animation", SUPERNOVA_ANIMATION)});
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    static {
        SUPERNOVA_ANIMATION = RawAnimation.begin().thenPlay("supernova_cast");
    }
}

