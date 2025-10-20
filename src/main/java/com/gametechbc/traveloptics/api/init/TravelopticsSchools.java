/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.registry.SchoolRegistry
 *  io.redspace.ironsspellbooks.api.spells.SchoolType
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraftforge.common.util.LazyOptional
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.RegistryObject
 */
package com.gametechbc.traveloptics.api.init;

import com.gametechbc.traveloptics.api.init.TravelopticsAttributes;
import com.gametechbc.traveloptics.util.TravelopticsDamageTypes;
import com.gametechbc.traveloptics.util.TravelopticsTags;

import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TravelopticsSchools {
    public static final DeferredRegister<SchoolType> TRAVELOPTICS_SCHOOLS = DeferredRegister.create((ResourceKey)SchoolRegistry.SCHOOL_REGISTRY_KEY, (String)"traveloptics");
    public static final ResourceLocation AQUA_RESOURCE = new ResourceLocation("traveloptics", "aqua");
    public static final RegistryObject<SchoolType> AQUA = TravelopticsSchools.registerSchool(new SchoolType(AQUA_RESOURCE, TravelopticsTags.AQUA_FOCUS, (Component)Component.translatable((String)"school.traveloptics.aqua").withStyle(style -> style.applyTo(5745885)), LazyOptional.of(() -> TravelopticsAttributes.AQUA_SPELL_POWER.get()), LazyOptional.of(() -> TravelopticsAttributes.AQUA_MAGIC_RESIST.get()), TravelopticsDamageTypes.AQUA_MAGIC));

    public static RegistryObject<SchoolType> registerSchool(SchoolType schoolType) {
        return TRAVELOPTICS_SCHOOLS.register(schoolType.getId().isAllowedInResourceLocation(), () -> schoolType);
    }
}

