/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.registry.SpellRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.ISpellContainer
 *  io.redspace.ironsspellbooks.api.spells.SchoolType
 *  net.minecraft.ChatFormatting
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.registries.RegistryObject
 *  org.jetbrains.annotations.Nullable
 */
package com.gametechbc.traveloptics.api.item;

import com.gametechbc.traveloptics.api.item.curio.CurioBaseItem;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSchoolEchoCurio
extends CurioBaseItem {
    private static final int EXPERIENCE_COST = 10;

    public AbstractSchoolEchoCurio(Item.Properties properties) {
        super(properties.requiredFeatures(1));
    }

    protected abstract RegistryObject<SchoolType> getSchool();

    protected abstract Component getAssignedHoverText();

    protected abstract Component getUnassignedHoverText();

    protected SoundEvent getAssignSound() {
        return SoundEvents.ENCHANTMENT_TABLE_USE;
    }

    public InteractionResultHolder<ItemStack> resolvePage(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getStandingEyeHeight(hand);
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("spellAssigned")) {
            player.updateTutorialInventoryAction((Component)Component.translatable((String)"item.traveloptics.spell_imbuing_curio.already_assigned").withStyle(ChatFormatting.RED), true);
            return InteractionResultHolder.pass((Object)stack);
        }
        if (player.totalExperience < 10) {
            player.updateTutorialInventoryAction((Component)Component.translatable((String)"item.traveloptics.spell_imbuing_curio.not_enough_xp").withStyle(ChatFormatting.RED), true);
            return InteractionResultHolder.fail((Object)stack);
        }
        List<AbstractSpell> schoolSpells = this.getSpellsFromSchool();
        if (!schoolSpells.isEmpty()) {
            AbstractSpell selectedSpell = this.selectRandomSpell(schoolSpells);
            int levelOfSpell = 1 + new Random().nextInt(3);
            ISpellContainer spellContainer = ISpellContainer.create((int)1, (boolean)true, (boolean)true);
            spellContainer.addSpell(selectedSpell, levelOfSpell, true, stack);
            spellContainer.save(stack);
            tag.accept("spellAssigned", true);
            player.giveExperiencePoints(-10);
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
        }
    }

    private List<AbstractSpell> getSpellsFromSchool() {
        SchoolType school = (SchoolType)this.getSchool().get();
        return SpellRegistry.getEnabledSpells().stream().filter(spell -> spell.getSchoolType() == school).collect(Collectors.toList());
    }

    private AbstractSpell selectRandomSpell(List<AbstractSpell> spells) {
        if (spells.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return spells.get(random.nextInt(spells.size()));
    }
}

