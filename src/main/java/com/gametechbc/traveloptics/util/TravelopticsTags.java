/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.util.LazyTagLookup
 *  io.redspace.ironsspellbooks.api.registry.SpellRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.damagesource.DamageType
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.levelgen.structure.Structure
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 */
package com.gametechbc.traveloptics.util;

import com.github.L_Ender.cataclysm.util.LazyTagLookup;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class TravelopticsTags {
    public static final TagKey<Item> CAN_CAST_REVERSAL = TagKey.registry((ResourceKey)Registries.ITEM, (ResourceLocation)new ResourceLocation("traveloptics", "can_cast_reversal"));
    public static final TagKey<Item> SPELL_IMBUED_CURIO = TagKey.registry((ResourceKey)Registries.ITEM, (ResourceLocation)new ResourceLocation("traveloptics", "spell_imbued_curio"));
    public static final TagKey<Item> ECHO_CURIO = TagKey.registry((ResourceKey)Registries.ITEM, (ResourceLocation)new ResourceLocation("traveloptics", "echo_curio"));
    public static final TagKey<Item> AQUA_FOCUS = TagKey.registry((ResourceKey)Registries.ITEM, (ResourceLocation)new ResourceLocation("traveloptics", "aqua_focus"));
    public static final TagKey<Block> TECTONIC_RIFT_DESTROYABLE = TagKey.registry((ResourceKey)Registries.BLOCK, (ResourceLocation)new ResourceLocation("traveloptics", "tectonic_rift_destroyable"));
    public static final TagKey<Block> EXTINCTION_BLOCK_BLACKLIST = TagKey.registry((ResourceKey)Registries.BLOCK, (ResourceLocation)new ResourceLocation("traveloptics", "extinction_block_blacklist"));
    public static final TagKey<Block> EXTREMELY_VALUABLES = TagKey.registry((ResourceKey)Registries.BLOCK, (ResourceLocation)new ResourceLocation("traveloptics", "extremely_valuables"));
    public static final TagKey<Block> IS_ENDSTONE_CATEGORY = TagKey.registry((ResourceKey)Registries.BLOCK, (ResourceLocation)new ResourceLocation("traveloptics", "is_endstone_category"));
    public static final TagKey<EntityType<?>> AERIAL_COLLAPSE_DR = TagKey.registry((ResourceKey)Registries.ENTITY_TYPE, (ResourceLocation)new ResourceLocation("traveloptics", "aerial_collapse_dr"));
    public static final TagKey<EntityType<?>> SPECTRAL_SHIFT_BLACKLIST = TagKey.registry((ResourceKey)Registries.ENTITY_TYPE, (ResourceLocation)new ResourceLocation("traveloptics", "spectral_shift_blacklist"));
    public static final TagKey<EntityType<?>> SPECTRAL_BLINK_BLACKLIST = TagKey.registry((ResourceKey)Registries.ENTITY_TYPE, (ResourceLocation)new ResourceLocation("traveloptics", "spectral_blink_blacklist"));
    public static final TagKey<EntityType<?>> ELEMENT_FIRE = TagKey.registry((ResourceKey)Registries.ENTITY_TYPE, (ResourceLocation)new ResourceLocation("traveloptics", "element_fire"));
    public static final TagKey<EntityType<?>> SENSED_ENTITIES = TagKey.registry((ResourceKey)Registries.ENTITY_TYPE, (ResourceLocation)new ResourceLocation("traveloptics", "sensed_entities"));
    public static final TagKey<EntityType<?>> APPROACH_ENTITY_BLACKLIST = TagKey.registry((ResourceKey)Registries.ENTITY_TYPE, (ResourceLocation)new ResourceLocation("traveloptics", "approach_entity_blacklist"));
    public static final TagKey<EntityType<?>> TEAM_THE_NIGHTWARDEN = TagKey.registry((ResourceKey)Registries.ENTITY_TYPE, (ResourceLocation)new ResourceLocation("traveloptics", "team_the_nightwarden"));
    public static final TagKey<EntityType<?>> SUPERMASSIVE_BLACKHOLE_PULL_BLACKLIST = TagKey.registry((ResourceKey)Registries.ENTITY_TYPE, (ResourceLocation)new ResourceLocation("traveloptics", "supermassive_blackhole_pull_blacklist"));
    public static final TagKey<EntityType<?>> TEAM_AQUA_GRANDMASTER = TagKey.registry((ResourceKey)Registries.ENTITY_TYPE, (ResourceLocation)new ResourceLocation("traveloptics", "team_aqua_grandmaster"));
    public static final TagKey<DamageType> NIGHTWARDEN_MAGIC_ADAPT = TagKey.registry((ResourceKey)Registries.DAMAGE_TYPE, (ResourceLocation)new ResourceLocation("traveloptics", "nightwarden_magic_adapt"));
    public static final TagKey<DamageType> NIGHTWARDEN_MELEE_PHASE_ACCEPT = TagKey.registry((ResourceKey)Registries.DAMAGE_TYPE, (ResourceLocation)new ResourceLocation("traveloptics", "nightwarden_melee_phase_accept"));
    public static final TagKey<DamageType> BYPASSES_ADAPTATION = TagKey.registry((ResourceKey)Registries.DAMAGE_TYPE, (ResourceLocation)new ResourceLocation("traveloptics", "bypasses_adaptation"));
    public static final TagKey<DamageType> IGNORES_ZAEVORATH_SHIELDS = TagKey.registry((ResourceKey)Registries.DAMAGE_TYPE, (ResourceLocation)new ResourceLocation("traveloptics", "ignores_zaevorath_shields"));
    public static final TagKey<AbstractSpell> AFFECTED_BY_GLOBAL_SUMMON_COUNT = TagKey.registry((ResourceKey)SpellRegistry.SPELL_REGISTRY_KEY, (ResourceLocation)new ResourceLocation("traveloptics", "affected_by_global_summon_count"));
    public static final TagKey<Structure> EYE_OF_NOTHINGNESS_DESTINATION = TagKey.registry((ResourceKey)Registries.STRUCTURE, (ResourceLocation)new ResourceLocation("traveloptics", "eye_of_nothingness_destination"));
    public static final TagKey<Structure> CRADLE_OF_WILL_DESTINATION = TagKey.registry((ResourceKey)Registries.STRUCTURE, (ResourceLocation)new ResourceLocation("traveloptics", "cradle_of_will_destination"));
    public static final TagKey<MobEffect> EFFECTIVE_FOR_NIGHTWARDEN = TravelopticsTags.registerEffectTag("effective_for_nightwarden");
    public static final LazyTagLookup<MobEffect> EFFECTIVE_FOR_NIGHTWARDEN_LOOKUP = LazyTagLookup.create((IForgeRegistry)ForgeRegistries.MOB_EFFECTS, EFFECTIVE_FOR_NIGHTWARDEN);

    private static TagKey<MobEffect> registerEffectTag(String name) {
        return TagKey.registry((ResourceKey)Registries.MOB_EFFECT, (ResourceLocation)new ResourceLocation("traveloptics", name));
    }

    public static void init() {
    }
}

