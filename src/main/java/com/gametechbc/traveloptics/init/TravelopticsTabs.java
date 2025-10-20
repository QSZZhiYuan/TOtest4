/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.redspace.ironsspellbooks.api.registry.SpellRegistry
 *  io.redspace.ironsspellbooks.api.spells.AbstractSpell
 *  io.redspace.ironsspellbooks.api.spells.ISpellContainer
 *  io.redspace.ironsspellbooks.registries.ItemRegistry
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.item.CreativeModeTab
 *  net.minecraft.world.item.CreativeModeTabs
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraftforge.event.BuildCreativeModeTabContentsEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.RegistryObject
 */
package com.gametechbc.traveloptics.init;

import com.gametechbc.traveloptics.init.TravelopticsItems;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid="traveloptics", bus=Mod.EventBusSubscriber.Bus.MOD)
public class TravelopticsTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create((ResourceKey)Registries.CREATIVE_MODE_TAB, (String)"traveloptics");
    public static final RegistryObject<CreativeModeTab> TRAVELOPTICS_ITEMS = REGISTRY.register("traveloptics_items", () -> CreativeModeTab.builder().backgroundSuffix((Component)Component.translatable((String)"item_group.traveloptics.traveloptics")).backgroundSuffix(() -> new ItemStack((ItemLike)TravelopticsItems.CRADLE_OF_WILL.get())).backgroundSuffix((parameters, tabData) -> {
        tabData.acceptAll((ItemLike)TravelopticsItems.RUNED_PURPUR_BLOCK.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.DUSKWOOD_BOOKSHELF.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.OBSIDIAN_BOOKSHELF.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.AQUA_UPGRADE_ORB.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ELDRITCH_UPGRADE_ORB.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.AQUA_RUNE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.HULLBREAKER_STEEL.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.TECTONIC_INGOT.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ABYSSAL_SPELLWEAVE_INGOT.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.PYRO_SPELLWEAVE_INGOT.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.VERDANT_SPELLWEAVE_INGOT.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.VOID_SPELLWEAVE_INGOT.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.TECTONIC_UPGRADE_SMITHING_TEMPLATE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.DARKNESS_UPGRADE_SMITHING_TEMPLATE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ABYSSAL_UPGRADE_SMITHING_TEMPLATE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.WITHERITE_UPGRADE_SMITHING_TEMPLATE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.VOID_MANUSCRIPT.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.CRADLE_OF_WILL.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.SHATTERED_HYDROCHARGE_BRACELET.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.FROSTED_CRYOSTORM_BRACELET.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.RESONANT_SCRAP.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.HULLBREAKER_SCALES.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.TREMOR_CORE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.DARKNESS_CLOTH.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.DARK_GEM_OF_THE_LIVING_VOID.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ABYSSAL_TENTACLE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ECHO_WINGLET.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.FLAME_TEMPERED_HANDGUARD.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.LAST_GLOW.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ELYTRA_JETPACK_COMPONENT.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.PLASMA_POWER_CELL.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ANCIENT_METAL_WEAPON_PARTS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.DESERT_JEWEL_FRAGMENT.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.KYREXI_CLAWS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.SPECTRAL_SPELL_SLOT_UPGRADE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.SANCTIFIED_SPELL_SLOT_UPGRADE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.EYE_OF_NOTHINGNESS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.WITHERED_EXCRUCIS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.EXCRUCIS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.MUSIC_DISC_169.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.MUSIC_DISC_NIGHTWARDEN.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.AQUAMANCER_SPAWN_EGG.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.AQUA_GRANDMASTER_SPAWN_EGG.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ENRAGED_DEAD_KING_SPAWN_EGG.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.VOID_TOME_SPAWN_EGG.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.VOIDSHELF_GOLEM_SPAWN_EGG.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.FADING_MAGE_SPAWN_EGG.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.THE_NIGHTWARDEN_SPAWN_EGG.get());
    }).build());
    public static final RegistryObject<CreativeModeTab> TRAVELOPTICS_EQUIPMENTS = REGISTRY.register("traveloptics_equipments", () -> CreativeModeTab.builder().backgroundSuffix((Component)Component.translatable((String)"item_group.traveloptics.traveloptics_equipment")).backgroundSuffix(() -> new ItemStack((ItemLike)TravelopticsItems.ARCHIVE_OF_ABYSSAL_SECRETS.get())).withTabsBefore(new ResourceKey[]{TRAVELOPTICS_ITEMS.getKey()}).backgroundSuffix((parameters, tabData) -> {
        tabData.acceptAll((ItemLike)TravelopticsItems.END_ERUPTION_BOMB_ITEM.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.GUIDE_TO_WATERY_WHISPERS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.CODEX_OF_THE_CRUSHING_DEPTHS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ARCHIVE_OF_ABYSSAL_SECRETS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.THE_ACCUSED_CODEX.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.CHRONICLES_OF_THE_FIRELORD.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.TOME_OF_ABYSSAL_FLORA.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.WAND_OF_FINAL_LIGHT.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.TITANLORD_SCEPTER.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.TITANLORD_SCEPTER_RETRO.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.TITANLORD_SCEPTER_TECTONIC.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.STELLOTHORN.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ETERNAL_MAELSTROM_TRIDENT.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.GAUNTLET_OF_EXTINCTION.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ABYSSAL_TIDECALLER.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.THE_OBLITERATOR.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.CURSED_WRAITHBLADE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.MECHANIZED_WRAITHBLADE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.FLAMES_OF_ELDRITCH.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.HARBINGERS_WRATH.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.SCOURGE_OF_THE_SANDS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.THORNS_OF_OBLIVION.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.VOIDSTRIKE_REAPER.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.INFERNAL_DEVASTATOR.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.POCKET_BLACK_HOLE_BELT.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.AETHERIAL_DESPAIR_RING.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.FIRESTORM_RING.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.HYDROCHARGE_BRACELET.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.CRYOSTORM_BRACELET.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.AZURE_IGNITION_BRACELET.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.NIGHTSTALKERS_BAND.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ENERGY_UNBOUND_NECKLACE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.SIGIL_OF_THE_SPIDER_SORCERER.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.AMULET_OF_SPECTRAL_SHIFT.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.BOTTLED_RAINCLOUD.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ELYTRA_JETPACK_COMPONENT.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.AQUA_ECHO.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.BLOOD_ECHO.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ELDRITCH_ECHO.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ENDER_ECHO.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.EVOCATION_ECHO.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.FIRE_ECHO.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.HOLY_ECHO.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ICE_ECHO.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.LIGHTNING_ECHO.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.NATURE_ECHO.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.DEEPLING_MAGE_ARMOR_HELMET.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.DEEPLING_MAGE_ARMOR_ROBE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.DEEPLING_MAGE_ARMOR_LEGGINGS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.DEEPLING_MAGE_ARMOR_BOOTS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.FORLORN_HARBINGER_ARMOR_HOOD.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.FORLORN_HARBINGER_ARMOR_ROBE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.FORLORN_HARBINGER_ARMOR_LEGGINGS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.FORLORN_HARBINGER_ARMOR_BOOTS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.PRIMORDIAL_CREST_ARMOR_HELMET.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.PRIMORDIAL_CREST_ARMOR_CHESTPLATE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.PRIMORDIAL_CREST_ARMOR_LEGGINGS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.PRIMORDIAL_CREST_ARMOR_BOOTS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.TECTONIC_CREST_ARMOR_HELMET.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.TECTONIC_CREST_ARMOR_CHESTPLATE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.TECTONIC_CREST_ARMOR_LEGGINGS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.TECTONIC_CREST_ARMOR_BOOTS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ABYSSAL_HIDE_ARMOR_HAT.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ABYSSAL_HIDE_ARMOR_ROBE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ABYSSAL_HIDE_ARMOR_LEGGINGS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.ABYSSAL_HIDE_ARMOR_BOOTS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.CURSED_WRAITHGUARD_ARMOR_CROWN.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.CURSED_WRAITHGUARD_ARMOR_CHESTPLATE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.CURSED_WRAITHGUARD_ARMOR_SPECTRAL_WRAPPING.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.CURSED_WRAITHGUARD_ARMOR_BOOTS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.MECHANIZED_EXOSKELETON_ARMOR_HELMET.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.MECHANIZED_EXOSKELETON_ARMOR_CHESTPLATE.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.MECHANIZED_EXOSKELETON_ARMOR_LEGGINGS.get());
        tabData.acceptAll((ItemLike)TravelopticsItems.MECHANIZED_EXOSKELETON_ARMOR_BOOTS.get());
    }).build());
    public static final RegistryObject<CreativeModeTab> TRAVELOPTICS_SCROLLS = REGISTRY.register("traveloptics_scrolls", () -> CreativeModeTab.builder().backgroundSuffix((Component)Component.translatable((String)"item_group.traveloptics.traveloptics_scrolls")).backgroundSuffix(() -> new ItemStack((ItemLike)TravelopticsItems.AQUA_SCROLL_DUMMY.get())).withTabsAfter(new ResourceKey[]{TRAVELOPTICS_ITEMS.getKey()}).build());

    @SubscribeEvent
    public static void fillCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == CreativeModeTabs.searchTab() || event.getTab() == TRAVELOPTICS_SCROLLS.get()) {
            SpellRegistry.getEnabledSpells().stream().filter(spellType -> spellType != SpellRegistry.none()).filter(spell -> spell.getSpellResource().validPathChar().equals("traveloptics")).forEach(spell -> {
                for (int i = spell.getMinLevel(); i <= spell.getMaxLevel(); ++i) {
                    ItemStack itemstack = new ItemStack((ItemLike)ItemRegistry.SCROLL.get());
                    ISpellContainer spellList = ISpellContainer.createScrollContainer((AbstractSpell)spell, (int)i, (ItemStack)itemstack);
                    spellList.save(itemstack);
                    event.acceptAll(itemstack);
                }
            });
        }
    }
}

