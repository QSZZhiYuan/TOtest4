/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.damagesource.DamageSource
 */
package com.gametechbc.traveloptics.entity.mobs.nightwarden_boss;

import com.gametechbc.traveloptics.util.TravelopticsTags;
import java.util.Set;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;

public class NightwardenAdaptRegistry {
    public static final Set<ResourceLocation> MAGIC_ADAPTATION_TYPES = Set.of(new ResourceLocation("irons_spellbooks", "fire_magic"), new ResourceLocation("irons_spellbooks", "ice_magic"), new ResourceLocation("irons_spellbooks", "lightning_magic"), new ResourceLocation("irons_spellbooks", "holy_magic"), new ResourceLocation("irons_spellbooks", "ender_magic"), new ResourceLocation("irons_spellbooks", "blood_magic"), new ResourceLocation("irons_spellbooks", "evocation_magic"), new ResourceLocation("irons_spellbooks", "eldritch_magic"), new ResourceLocation("irons_spellbooks", "nature_magic"), new ResourceLocation("traveloptics", "aqua_magic"));
    public static final Set<ResourceLocation> MELEE_PHASE_DAMAGE_TYPES = Set.of(new ResourceLocation("minecraft", "player_attack"));

    public static boolean isMagicAdaptable(DamageSource source, ResourceLocation damageType) {
        return damageType != null && (MAGIC_ADAPTATION_TYPES.contains(damageType) || source.is(TravelopticsTags.NIGHTWARDEN_MAGIC_ADAPT));
    }

    public static boolean isAllowedInMeleePhase(DamageSource source, ResourceLocation damageType) {
        return damageType != null && (MELEE_PHASE_DAMAGE_TYPES.contains(damageType) || source.is(TravelopticsTags.NIGHTWARDEN_MELEE_PHASE_ACCEPT));
    }
}

