/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.attribute.MagicRangedAttribute
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraftforge.event.entity.EntityAttributeModificationEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package com.gametechbc.traveloptics.api.init;

import io.redspace.ironsspellbooks.api.attribute.MagicRangedAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid="traveloptics", bus=Mod.EventBusSubscriber.Bus.MOD)
public class TravelopticsAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create((IForgeRegistry)ForgeRegistries.ATTRIBUTES, (String)"traveloptics");
    public static final RegistryObject<Attribute> AQUA_MAGIC_RESIST = TravelopticsAttributes.aquaResistanceAttribute("aqua");
    public static final RegistryObject<Attribute> AQUA_SPELL_POWER = TravelopticsAttributes.aquaPowerAttribute("aqua");

    public static RegistryObject<Attribute> aquaResistanceAttribute(String id) {
        return ATTRIBUTES.register(id + "_magic_resist", () -> new MagicRangedAttribute("attribute.traveloptics." + id + "_magic_resist", 1.0, -100.0, 100.0).sanitizeValue(true));
    }

    public static RegistryObject<Attribute> aquaPowerAttribute(String id) {
        return ATTRIBUTES.register(id + "_spell_power", () -> new MagicRangedAttribute("attribute.traveloptics." + id + "_spell_power", 1.0, -100.0, 100.0).sanitizeValue(true));
    }

    @SubscribeEvent
    public static void onEntityAttributeModification(EntityAttributeModificationEvent event) {
        event.getTypes().forEach(entity -> {
            event.add(entity, (Attribute)AQUA_SPELL_POWER.get());
            event.add(entity, (Attribute)AQUA_MAGIC_RESIST.get());
        });
    }
}

