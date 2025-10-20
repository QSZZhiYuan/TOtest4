/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.L_Ender.cataclysm.items.Dungeon_Eye.DungeonEyeItem
 *  com.google.common.collect.ImmutableMultimap
 *  com.google.common.collect.ImmutableMultimap$Builder
 *  com.google.common.collect.Multimap
 *  io.redspace.ironsspellbooks.api.registry.AttributeRegistry
 *  io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder
 *  io.redspace.ironsspellbooks.api.registry.SpellRegistry
 *  io.redspace.ironsspellbooks.api.spells.SpellRarity
 *  io.redspace.ironsspellbooks.item.SpellSlotUpgradeItem
 *  io.redspace.ironsspellbooks.item.UpgradeOrbItem
 *  io.redspace.ironsspellbooks.item.armor.UpgradeType
 *  io.redspace.ironsspellbooks.item.curios.CurioBaseItem
 *  io.redspace.ironsspellbooks.item.spell_books.SimpleAttributeSpellBook
 *  io.redspace.ironsspellbooks.util.ItemPropertiesHelper
 *  net.minecraft.ChatFormatting
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.item.ArmorItem$Type
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.Rarity
 *  net.minecraft.world.level.block.Block
 *  net.minecraftforge.common.ForgeSpawnEggItem
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package com.gametechbc.traveloptics.init;

import com.gametechbc.traveloptics.api.init.TravelopticsAttributes;
import com.gametechbc.traveloptics.api.item.armor.TravelopticsUpgradeTypes;
import com.gametechbc.traveloptics.config.SpellsConfig;
import com.gametechbc.traveloptics.config.WeaponConfig;
import com.gametechbc.traveloptics.init.TravelopticsBlocks;
import com.gametechbc.traveloptics.init.TravelopticsEntities;
import com.gametechbc.traveloptics.init.TravelopticsSpells;
import com.gametechbc.traveloptics.item.TravelopticsTemplate;
import com.gametechbc.traveloptics.item.armor.AbyssalHideArmorItem;
import com.gametechbc.traveloptics.item.armor.CursedWraithguardArmorItem;
import com.gametechbc.traveloptics.item.armor.DeeplingMageArmorItem;
import com.gametechbc.traveloptics.item.armor.ForlornHarbingerArmorItem;
import com.gametechbc.traveloptics.item.armor.MechanizedExoskeletonArmorItem;
import com.gametechbc.traveloptics.item.armor.PrimordialCrestArmorItem;
import com.gametechbc.traveloptics.item.armor.TectonicCrestArmorItem;
import com.gametechbc.traveloptics.item.bossweapon.abyssaltidecaller.AbyssalTidecallerItem;
import com.gametechbc.traveloptics.item.bossweapon.abyssaltidecaller.AbyssalTidecallerLevelOneItem;
import com.gametechbc.traveloptics.item.bossweapon.abyssaltidecaller.AbyssalTidecallerLevelThreeItem;
import com.gametechbc.traveloptics.item.bossweapon.abyssaltidecaller.AbyssalTidecallerLevelTwoItem;
import com.gametechbc.traveloptics.item.bossweapon.cursedwraithblade.CursedWraithbladeItem;
import com.gametechbc.traveloptics.item.bossweapon.cursedwraithblade.CursedWraithbladeLevelOneItem;
import com.gametechbc.traveloptics.item.bossweapon.cursedwraithblade.CursedWraithbladeLevelThreeItem;
import com.gametechbc.traveloptics.item.bossweapon.cursedwraithblade.CursedWraithbladeLevelTwoItem;
import com.gametechbc.traveloptics.item.bossweapon.flamesofeldritch.FlamesOfEldritchItem;
import com.gametechbc.traveloptics.item.bossweapon.flamesofeldritch.FlamesOfEldritchLevelOneItem;
import com.gametechbc.traveloptics.item.bossweapon.flamesofeldritch.FlamesOfEldritchLevelThreeItem;
import com.gametechbc.traveloptics.item.bossweapon.flamesofeldritch.FlamesOfEldritchLevelTwoItem;
import com.gametechbc.traveloptics.item.bossweapon.gauntletofextinction.GauntletOfExtinctionItem;
import com.gametechbc.traveloptics.item.bossweapon.gauntletofextinction.GauntletOfExtinctionLevelOneItem;
import com.gametechbc.traveloptics.item.bossweapon.gauntletofextinction.GauntletOfExtinctionLevelThreeItem;
import com.gametechbc.traveloptics.item.bossweapon.gauntletofextinction.GauntletOfExtinctionLevelTwoItem;
import com.gametechbc.traveloptics.item.bossweapon.harbingerwrath.HarbingersWrathItem;
import com.gametechbc.traveloptics.item.bossweapon.harbingerwrath.HarbingersWrathLevelOneItem;
import com.gametechbc.traveloptics.item.bossweapon.harbingerwrath.HarbingersWrathLevelThreeItem;
import com.gametechbc.traveloptics.item.bossweapon.harbingerwrath.HarbingersWrathLevelTwoItem;
import com.gametechbc.traveloptics.item.bossweapon.infernal_devastator.InfernalDevastatorItem;
import com.gametechbc.traveloptics.item.bossweapon.infernal_devastator.InfernalDevastatorLevelOneItem;
import com.gametechbc.traveloptics.item.bossweapon.infernal_devastator.InfernalDevastatorLevelThreeItem;
import com.gametechbc.traveloptics.item.bossweapon.infernal_devastator.InfernalDevastatorLevelTwoItem;
import com.gametechbc.traveloptics.item.bossweapon.mechanized_wraithblade.MechanizedWraithbladeItem;
import com.gametechbc.traveloptics.item.bossweapon.mechanized_wraithblade.MechanizedWraithbladeLevelOneItem;
import com.gametechbc.traveloptics.item.bossweapon.mechanized_wraithblade.MechanizedWraithbladeLevelThreeItem;
import com.gametechbc.traveloptics.item.bossweapon.mechanized_wraithblade.MechanizedWraithbladeLevelTwoItem;
import com.gametechbc.traveloptics.item.bossweapon.scourgeofthesands.ScourgeOfTheSandsItem;
import com.gametechbc.traveloptics.item.bossweapon.scourgeofthesands.ScourgeOfTheSandsLevelOneItem;
import com.gametechbc.traveloptics.item.bossweapon.scourgeofthesands.ScourgeOfTheSandsLevelThreeItem;
import com.gametechbc.traveloptics.item.bossweapon.scourgeofthesands.ScourgeOfTheSandsLevelTwoItem;
import com.gametechbc.traveloptics.item.bossweapon.stellothorn.StellothornItem;
import com.gametechbc.traveloptics.item.bossweapon.stellothorn.StellothornLevelOneItem;
import com.gametechbc.traveloptics.item.bossweapon.stellothorn.StellothornLevelThreeItem;
import com.gametechbc.traveloptics.item.bossweapon.stellothorn.StellothornLevelTwoItem;
import com.gametechbc.traveloptics.item.bossweapon.the_obliterator.TheObliteratorItem;
import com.gametechbc.traveloptics.item.bossweapon.the_obliterator.TheObliteratorLevelOneItem;
import com.gametechbc.traveloptics.item.bossweapon.the_obliterator.TheObliteratorLevelThreeItem;
import com.gametechbc.traveloptics.item.bossweapon.the_obliterator.TheObliteratorLevelTwoItem;
import com.gametechbc.traveloptics.item.bossweapon.thornsofoblivion.ThornsOfOblivionItem;
import com.gametechbc.traveloptics.item.bossweapon.thornsofoblivion.ThornsOfOblivionLevelOneItem;
import com.gametechbc.traveloptics.item.bossweapon.thornsofoblivion.ThornsOfOblivionLevelThreeItem;
import com.gametechbc.traveloptics.item.bossweapon.thornsofoblivion.ThornsOfOblivionLevelTwoItem;
import com.gametechbc.traveloptics.item.bossweapon.trident_of_the_eternal_maelstrom.TridentOfTheEternalMaelstromItem;
import com.gametechbc.traveloptics.item.bossweapon.trident_of_the_eternal_maelstrom.TridentOfTheEternalMaelstromLevelOneItem;
import com.gametechbc.traveloptics.item.bossweapon.trident_of_the_eternal_maelstrom.TridentOfTheEternalMaelstromLevelThreeItem;
import com.gametechbc.traveloptics.item.bossweapon.trident_of_the_eternal_maelstrom.TridentOfTheEternalMaelstromLevelTwoItem;
import com.gametechbc.traveloptics.item.bossweapon.voidstrikereaper.VoidstrikeReaperItem;
import com.gametechbc.traveloptics.item.bossweapon.voidstrikereaper.VoidstrikeReaperLevelOneItem;
import com.gametechbc.traveloptics.item.bossweapon.voidstrikereaper.VoidstrikeReaperLevelThreeItem;
import com.gametechbc.traveloptics.item.bossweapon.voidstrikereaper.VoidstrikeReaperLevelTwoItem;
import com.gametechbc.traveloptics.item.curios.AetherialDespairRingItem;
import com.gametechbc.traveloptics.item.curios.AmuletOfSpectralShiftItem;
import com.gametechbc.traveloptics.item.curios.AzureIgnitionBraceletItem;
import com.gametechbc.traveloptics.item.curios.BottledRaincloudItem;
import com.gametechbc.traveloptics.item.curios.CryostormBraceletItem;
import com.gametechbc.traveloptics.item.curios.ElytraJetpackComponentItem;
import com.gametechbc.traveloptics.item.curios.EnergyUnboundNecklaceItem;
import com.gametechbc.traveloptics.item.curios.FirestormRingItem;
import com.gametechbc.traveloptics.item.curios.HydrochargeBraceletItem;
import com.gametechbc.traveloptics.item.curios.NighstalkersBandItem;
import com.gametechbc.traveloptics.item.curios.PocketBlackHoleBeltItem;
import com.gametechbc.traveloptics.item.curios.SigilOfTheSpiderSorcererItem;
import com.gametechbc.traveloptics.item.imbued_echo.AquaEchoCurio;
import com.gametechbc.traveloptics.item.imbued_echo.BloodEchoCurio;
import com.gametechbc.traveloptics.item.imbued_echo.EldritchEchoCurio;
import com.gametechbc.traveloptics.item.imbued_echo.EnderEchoCurio;
import com.gametechbc.traveloptics.item.imbued_echo.EvocationEchoCurio;
import com.gametechbc.traveloptics.item.imbued_echo.FireEchoCurio;
import com.gametechbc.traveloptics.item.imbued_echo.HolyEchoCurio;
import com.gametechbc.traveloptics.item.imbued_echo.IceEchoCurio;
import com.gametechbc.traveloptics.item.imbued_echo.LightningEchoCurio;
import com.gametechbc.traveloptics.item.imbued_echo.NatureEchoCurio;
import com.gametechbc.traveloptics.item.items.AbyssalSpellweaveIngotItem;
import com.gametechbc.traveloptics.item.items.AbyssalTentacleItem;
import com.gametechbc.traveloptics.item.items.AncientMetalWeaponPartsItem;
import com.gametechbc.traveloptics.item.items.AquaScrollDummyItem;
import com.gametechbc.traveloptics.item.items.CelestialSpellweaveIngotItem;
import com.gametechbc.traveloptics.item.items.CradleOfWillItem;
import com.gametechbc.traveloptics.item.items.CrimsonSpellweaveItem;
import com.gametechbc.traveloptics.item.items.CryoSpellweaveIngotItem;
import com.gametechbc.traveloptics.item.items.DarkGemOfTheLivingVoidItem;
import com.gametechbc.traveloptics.item.items.DarknessClothItem;
import com.gametechbc.traveloptics.item.items.DesertJewelFragmentItem;
import com.gametechbc.traveloptics.item.items.EchoWingletItem;
import com.gametechbc.traveloptics.item.items.EldritchSpellweaveIngotItem;
import com.gametechbc.traveloptics.item.items.EndEruptionBombItem;
import com.gametechbc.traveloptics.item.items.EvokatedSpellweaveIngotItem;
import com.gametechbc.traveloptics.item.items.ExcrucisItem;
import com.gametechbc.traveloptics.item.items.FeiryFeatherItem;
import com.gametechbc.traveloptics.item.items.FlameTemperedHandguardItem;
import com.gametechbc.traveloptics.item.items.FrostedCryostormBraceletItem;
import com.gametechbc.traveloptics.item.items.HullbreakerScalesItem;
import com.gametechbc.traveloptics.item.items.HullbreakerSteelItem;
import com.gametechbc.traveloptics.item.items.KyrexiClawsItem;
import com.gametechbc.traveloptics.item.items.LastGlowItem;
import com.gametechbc.traveloptics.item.items.LightningSpellweaveIngotItem;
import com.gametechbc.traveloptics.item.items.PlasmaPowerCellItem;
import com.gametechbc.traveloptics.item.items.PyroSpellweaveIngotItem;
import com.gametechbc.traveloptics.item.items.ResonantScrapItem;
import com.gametechbc.traveloptics.item.items.ShatteredHydrochargeBraceletItem;
import com.gametechbc.traveloptics.item.items.TectonicIngotItem;
import com.gametechbc.traveloptics.item.items.TremorCoreItem;
import com.gametechbc.traveloptics.item.items.VerdantSpellweaveItem;
import com.gametechbc.traveloptics.item.items.VoidManuscriptItem;
import com.gametechbc.traveloptics.item.items.VoidSpellweaveItem;
import com.gametechbc.traveloptics.item.items.WitheredExcrucisItem;
import com.gametechbc.traveloptics.item.music_disc.MusicDiscEldritchAbyssamorphItem;
import com.gametechbc.traveloptics.item.music_disc.MusicDiscNightwardenItem;
import com.gametechbc.traveloptics.item.spellbook.ArchiveOfAbyssalSecretsSpellbook;
import com.gametechbc.traveloptics.item.spellbook.ChroniclesOfTheFirelordSpellbook;
import com.gametechbc.traveloptics.item.spellbook.CodexOfTheCrushingDepthsSpellbook;
import com.gametechbc.traveloptics.item.spellbook.TheAccusedCodexSpellbook;
import com.gametechbc.traveloptics.item.staff.TitanlordScepterItem;
import com.gametechbc.traveloptics.item.staff.TitanlordScepterRetroItem;
import com.gametechbc.traveloptics.item.staff.TitanlordScepterTectonicItem;
import com.gametechbc.traveloptics.item.staff.WandOfFinalLightItem;
import com.gametechbc.traveloptics.util.TravelopticsTags;
import com.github.L_Ender.cataclysm.items.Dungeon_Eye.DungeonEyeItem;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.item.SpellSlotUpgradeItem;
import io.redspace.ironsspellbooks.item.UpgradeOrbItem;
import io.redspace.ironsspellbooks.item.armor.UpgradeType;
import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import io.redspace.ironsspellbooks.item.spell_books.SimpleAttributeSpellBook;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import java.util.Collection;
import java.util.UUID;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class TravelopticsItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create((IForgeRegistry)ForgeRegistries.ITEMS, (String)"traveloptics");
    public static final Rarity RARITY_LEGENDARY = Rarity.create((String)"traveloptics:legendary", (ChatFormatting)ChatFormatting.GOLD);
    public static final Rarity RARITY_MYTHIC = Rarity.create((String)"traveloptics:mythic", (ChatFormatting)ChatFormatting.RED);
    public static final Rarity RARITY_SIGNATURE = Rarity.create((String)"traveloptics:signature", (ChatFormatting)ChatFormatting.DARK_RED);
    public static final Rarity RARITY_ABYSSAL = Rarity.create((String)"traveloptics:abyssal", style -> style.applyTo(10313471));
    public static final Rarity RARITY_ANCIENT = Rarity.create((String)"traveloptics:ancient", style -> style.applyTo(16770714));
    public static final Rarity RARITY_CURSED = Rarity.create((String)"traveloptics:cursed", style -> style.applyTo(5104836));
    public static final Rarity RARITY_MECHANIZED = Rarity.create((String)"traveloptics:mechanized", style -> style.applyTo(11582917));
    public static final Rarity RARITY_NATURAL = Rarity.create((String)"traveloptics:natural", style -> style.applyTo(7377453));
    public static final Rarity RARITY_IGNIS = Rarity.create((String)"traveloptics:ignis", style -> style.applyTo(16766783));
    public static final Rarity RARITY_AMETHYST = Rarity.create((String)"traveloptics:amethyst", style -> style.applyTo(13144304));
    public static final Rarity RARITY_VOID = Rarity.create((String)"traveloptics:void", style -> style.applyTo(15238655));
    public static final Rarity RARITY_TOXIC = Rarity.create((String)"traveloptics:toxic", style -> style.applyTo(7591680));
    public static final Rarity RARITY_TECTONIC = Rarity.create((String)"traveloptics:tectonic", style -> style.applyTo(16738048));
    public static final Rarity RARITY_RETRO = Rarity.create((String)"traveloptics:retro", style -> style.applyTo(13348095));
    public static final Rarity RARITY_PRIMORDIAL = Rarity.create((String)"traveloptics:primordial", style -> style.applyTo(11709848));
    public static final Rarity RARITY_ELDRITCH = Rarity.create((String)"traveloptics:eldritch", style -> style.applyTo(37525));
    public static final Rarity RARITY_MONSTROUS = Rarity.create((String)"traveloptics:monstrous", style -> style.applyTo(16293444));
    public static final Rarity RARITY_AQUATIC = Rarity.create((String)"traveloptics:aquatic", style -> style.applyTo(7520974));
    public static final Rarity RARITY_FROSTED = Rarity.create((String)"traveloptics:frosted", style -> style.applyTo(12642814));
    public static final RegistryObject<Item> MUSIC_DISC_169 = ITEMS.register("music_disc_169", MusicDiscEldritchAbyssamorphItem::new);
    public static final RegistryObject<Item> MUSIC_DISC_NIGHTWARDEN = ITEMS.register("music_disc_nightwarden", MusicDiscNightwardenItem::new);
    public static final RegistryObject<Item> CHRONICLES_OF_THE_FIRELORD = ITEMS.register("chronicles_of_the_firelord", ChroniclesOfTheFirelordSpellbook::new);
    public static final RegistryObject<Item> ARCHIVE_OF_ABYSSAL_SECRETS = ITEMS.register("archive_of_abyssal_secrets", ArchiveOfAbyssalSecretsSpellbook::new);
    public static final RegistryObject<Item> THE_ACCUSED_CODEX = ITEMS.register("the_accused_codex", TheAccusedCodexSpellbook::new);
    public static final RegistryObject<Item> CODEX_OF_THE_CRUSHING_DEPTHS = ITEMS.register("codex_of_the_crushing_depths", CodexOfTheCrushingDepthsSpellbook::new);
    public static final RegistryObject<Item> TOME_OF_ABYSSAL_FLORA = ITEMS.register("tome_of_abyssal_flora", () -> {
        ImmutableMultimap.Builder builder = ImmutableMultimap.builder();
        builder.put((Object)((Attribute)AttributeRegistry.MAX_MANA.get()), (Object)new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Spellbook modifier", ((Double)SpellsConfig.shellboundMaxMana.get()).doubleValue(), AttributeModifier.Operation.ADDITION));
        builder.put((Object)((Attribute)AttributeRegistry.NATURE_SPELL_POWER.get()), (Object)new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Spellbook modifier", ((Double)SpellsConfig.shellboundNatureSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE));
        builder.put((Object)((Attribute)AttributeRegistry.COOLDOWN_REDUCTION.get()), (Object)new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Spellbook modifier", ((Double)SpellsConfig.shellboundCooldownReduction.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE));
        return new SimpleAttributeSpellBook(12, SpellRarity.LEGENDARY, (Multimap)builder.build());
    });
    public static final RegistryObject<Item> GUIDE_TO_WATERY_WHISPERS = ITEMS.register("guide_to_watery_whispers", () -> {
        ImmutableMultimap.Builder builder = ImmutableMultimap.builder();
        builder.put((Object)((Attribute)AttributeRegistry.MAX_MANA.get()), (Object)new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Spellbook modifier", ((Double)SpellsConfig.wateryWhispersMaxMana.get()).doubleValue(), AttributeModifier.Operation.ADDITION));
        builder.put((Object)((Attribute)TravelopticsAttributes.AQUA_SPELL_POWER.get()), (Object)new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Spellbook modifier", ((Double)SpellsConfig.wateryWhispersAquaSpellPower.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE));
        builder.put((Object)((Attribute)AttributeRegistry.MANA_REGEN.get()), (Object)new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Spellbook modifier", ((Double)SpellsConfig.wateryWhispersManaRegen.get()).doubleValue(), AttributeModifier.Operation.MULTIPLY_BASE));
        return new SimpleAttributeSpellBook(10, SpellRarity.LEGENDARY, (Multimap)builder.build());
    });
    public static final RegistryObject<CurioBaseItem> AETHERIAL_DESPAIR_RING = ITEMS.register("aetherial_despair_ring", AetherialDespairRingItem::new);
    public static final RegistryObject<CurioBaseItem> AMULET_OF_SPECTRAL_SHIFT = ITEMS.register("amulet_of_spectral_shift", AmuletOfSpectralShiftItem::new);
    public static final RegistryObject<CurioBaseItem> NIGHTSTALKERS_BAND = ITEMS.register("nightstalkers_band", NighstalkersBandItem::new);
    public static final RegistryObject<CurioBaseItem> AZURE_IGNITION_BRACELET = ITEMS.register("azure_ignition_bracelet", AzureIgnitionBraceletItem::new);
    public static final RegistryObject<CurioBaseItem> ENERGY_UNBOUND_NECKLACE = ITEMS.register("energy_unbound_necklace", EnergyUnboundNecklaceItem::new);
    public static final RegistryObject<CurioBaseItem> SIGIL_OF_THE_SPIDER_SORCERER = ITEMS.register("sigil_of_the_spider_sorcerer", SigilOfTheSpiderSorcererItem::new);
    public static final RegistryObject<CurioBaseItem> FIRESTORM_RING = ITEMS.register("firestorm_ring", FirestormRingItem::new);
    public static final RegistryObject<CurioBaseItem> BOTTLED_RAINCLOUD = ITEMS.register("bottled_raincloud", BottledRaincloudItem::new);
    public static final RegistryObject<Item> ELYTRA_JETPACK_COMPONENT = ITEMS.register("elytra_jetpack_component", ElytraJetpackComponentItem::new);
    public static final RegistryObject<Item> POCKET_BLACK_HOLE_BELT = ITEMS.register("pocket_black_hole", PocketBlackHoleBeltItem::new);
    public static final RegistryObject<Item> HYDROCHARGE_BRACELET = ITEMS.register("hydrocharge_bracelet", HydrochargeBraceletItem::new);
    public static final RegistryObject<Item> CRYOSTORM_BRACELET = ITEMS.register("cryostorm_bracelet", CryostormBraceletItem::new);
    public static final RegistryObject<Item> BLOOD_ECHO = ITEMS.register("blood_echo", () -> new BloodEchoCurio(new Item.Properties()));
    public static final RegistryObject<Item> LIGHTNING_ECHO = ITEMS.register("lightning_echo", () -> new LightningEchoCurio(new Item.Properties()));
    public static final RegistryObject<Item> ENDER_ECHO = ITEMS.register("ender_echo", () -> new EnderEchoCurio(new Item.Properties()));
    public static final RegistryObject<Item> HOLY_ECHO = ITEMS.register("holy_echo", () -> new HolyEchoCurio(new Item.Properties()));
    public static final RegistryObject<Item> ELDRITCH_ECHO = ITEMS.register("eldritch_echo", () -> new EldritchEchoCurio(new Item.Properties()));
    public static final RegistryObject<Item> EVOCATION_ECHO = ITEMS.register("evocation_echo", () -> new EvocationEchoCurio(new Item.Properties()));
    public static final RegistryObject<Item> FIRE_ECHO = ITEMS.register("fire_echo", () -> new FireEchoCurio(new Item.Properties()));
    public static final RegistryObject<Item> ICE_ECHO = ITEMS.register("ice_echo", () -> new IceEchoCurio(new Item.Properties()));
    public static final RegistryObject<Item> NATURE_ECHO = ITEMS.register("nature_echo", () -> new NatureEchoCurio(new Item.Properties()));
    public static final RegistryObject<Item> AQUA_ECHO = ITEMS.register("aqua_echo", () -> new AquaEchoCurio(new Item.Properties()));
    public static final RegistryObject<Item> ELDRITCH_UPGRADE_ORB = ITEMS.register("eldritch_upgrade_orb", () -> new UpgradeOrbItem((UpgradeType)TravelopticsUpgradeTypes.ELDRITCH_SPELL_POWER, ItemPropertiesHelper.material().requiredFeatures(RARITY_ELDRITCH)));
    public static final RegistryObject<Item> AQUA_UPGRADE_ORB = ITEMS.register("aqua_upgrade_orb", () -> new UpgradeOrbItem((UpgradeType)TravelopticsUpgradeTypes.AQUA_SPELL_POWER, ItemPropertiesHelper.material().requiredFeatures(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> AQUA_RUNE = ITEMS.register("aqua_rune", () -> new Item(ItemPropertiesHelper.material()));
    public static final RegistryObject<Item> TITANLORD_SCEPTER = ITEMS.register("titanlord_scepter", TitanlordScepterItem::new);
    public static final RegistryObject<Item> TITANLORD_SCEPTER_RETRO = ITEMS.register("titanlord_scepter_retro", TitanlordScepterRetroItem::new);
    public static final RegistryObject<Item> TITANLORD_SCEPTER_TECTONIC = ITEMS.register("titanlord_scepter_tectonic", TitanlordScepterTectonicItem::new);
    public static final RegistryObject<Item> WAND_OF_FINAL_LIGHT = ITEMS.register("wand_of_final_light", WandOfFinalLightItem::new);
    public static final RegistryObject<Item> VERDANT_SPELLWEAVE_INGOT = ITEMS.register("verdant_spellweave_ingot", VerdantSpellweaveItem::new);
    public static final RegistryObject<Item> CRIMSON_SPELLWEAVE_INGOT = ITEMS.register("crimson_spellweave_ingot", CrimsonSpellweaveItem::new);
    public static final RegistryObject<Item> ELDRITCH_SPELLWEAVE_INGOT = ITEMS.register("eldritch_spellweave_ingot", EldritchSpellweaveIngotItem::new);
    public static final RegistryObject<Item> ABYSSAL_SPELLWEAVE_INGOT = ITEMS.register("abyssal_spellweave_ingot", AbyssalSpellweaveIngotItem::new);
    public static final RegistryObject<Item> EVOKATED_SPELLWEAVE_INGOT = ITEMS.register("evokated_spellweave_ingot", EvokatedSpellweaveIngotItem::new);
    public static final RegistryObject<Item> CELESTIAL_SPELLWEAVE_INGOT = ITEMS.register("celestial_spellweave_ingot", CelestialSpellweaveIngotItem::new);
    public static final RegistryObject<Item> PYRO_SPELLWEAVE_INGOT = ITEMS.register("pyro_spellweave_ingot", PyroSpellweaveIngotItem::new);
    public static final RegistryObject<Item> CRYO_SPELLWEAVE_INGOT = ITEMS.register("cryo_spellweave_ingot", CryoSpellweaveIngotItem::new);
    public static final RegistryObject<Item> LIGHTNING_SPELLWEAVE_INGOT = ITEMS.register("lightning_spellweave_ingot", LightningSpellweaveIngotItem::new);
    public static final RegistryObject<Item> TECTONIC_INGOT = ITEMS.register("tectonic_ingot", TectonicIngotItem::new);
    public static final RegistryObject<Item> VOID_SPELLWEAVE_INGOT = ITEMS.register("void_spellweave_ingot", VoidSpellweaveItem::new);
    public static final RegistryObject<Item> HULLBREAKER_STEEL = ITEMS.register("hullbreaker_steel", HullbreakerSteelItem::new);
    public static final RegistryObject<Item> VOID_MANUSCRIPT = ITEMS.register("void_manuscript", VoidManuscriptItem::new);
    public static final RegistryObject<Item> CRADLE_OF_WILL = ITEMS.register("cradle_of_will", CradleOfWillItem::new);
    public static final RegistryObject<Item> EXCRUCIS = ITEMS.register("excrucis", ExcrucisItem::new);
    public static final RegistryObject<Item> WITHERED_EXCRUCIS = ITEMS.register("withered_excrucis", WitheredExcrucisItem::new);
    public static final RegistryObject<Item> TREMOR_CORE = ITEMS.register("tremor_core", TremorCoreItem::new);
    public static final RegistryObject<Item> ABYSSAL_TENTACLE = ITEMS.register("abyssal_tentacle", AbyssalTentacleItem::new);
    public static final RegistryObject<Item> DESERT_JEWEL_FRAGMENT = ITEMS.register("desert_jewel_fragment", DesertJewelFragmentItem::new);
    public static final RegistryObject<Item> FIERY_FEATHER = ITEMS.register("fiery_feather", FeiryFeatherItem::new);
    public static final RegistryObject<Item> FLAME_TEMPERED_HANDGUARD = ITEMS.register("flame_tempered_handguard", FlameTemperedHandguardItem::new);
    public static final RegistryObject<Item> KYREXI_CLAWS = ITEMS.register("kyrexi_claws", KyrexiClawsItem::new);
    public static final RegistryObject<Item> LAST_GLOW = ITEMS.register("last_glow", LastGlowItem::new);
    public static final RegistryObject<Item> ECHO_WINGLET = ITEMS.register("echo_winglet", EchoWingletItem::new);
    public static final RegistryObject<Item> PLASMA_POWER_CELL = ITEMS.register("plasma_power_cell", PlasmaPowerCellItem::new);
    public static final RegistryObject<Item> DARKNESS_CLOTH = ITEMS.register("darkness_cloth", DarknessClothItem::new);
    public static final RegistryObject<Item> DARK_GEM_OF_THE_LIVING_VOID = ITEMS.register("dark_gem_of_the_living_void", DarkGemOfTheLivingVoidItem::new);
    public static final RegistryObject<Item> HULLBREAKER_SCALES = ITEMS.register("hullbreaker_scales", HullbreakerScalesItem::new);
    public static final RegistryObject<Item> RESONANT_SCRAP = ITEMS.register("resonant_scrap", ResonantScrapItem::new);
    public static final RegistryObject<Item> END_ERUPTION_BOMB_ITEM = ITEMS.register("end_eruption_bomb", EndEruptionBombItem::new);
    public static final RegistryObject<Item> ANCIENT_METAL_WEAPON_PARTS = ITEMS.register("ancient_metal_weapon_parts", AncientMetalWeaponPartsItem::new);
    public static final RegistryObject<Item> SHATTERED_HYDROCHARGE_BRACELET = ITEMS.register("shattered_hydrocharge_bracelet", ShatteredHydrochargeBraceletItem::new);
    public static final RegistryObject<Item> FROSTED_CRYOSTORM_BRACELET = ITEMS.register("frosted_cryostorm_bracelet", FrostedCryostormBraceletItem::new);
    public static final RegistryObject<Item> SPECTRAL_SPELL_SLOT_UPGRADE = ITEMS.register("spectral_spell_slot_upgrade", () -> new SpellSlotUpgradeItem(15));
    public static final RegistryObject<Item> SANCTIFIED_SPELL_SLOT_UPGRADE = ITEMS.register("sanctified_spell_slot_upgrade", () -> new SpellSlotUpgradeItem(15));
    public static final RegistryObject<Item> ABYSSAL_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("abyssal_upgrade_smithing_template", TravelopticsTemplate::createAbyssalUpgradeTemplate);
    public static final RegistryObject<Item> TECTONIC_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("tectonic_upgrade_smithing_template", TravelopticsTemplate::createTectonicUpgradeTemplate);
    public static final RegistryObject<Item> WITHERITE_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("witherite_upgrade_smithing_template", TravelopticsTemplate::createWitheriteUpgradeTemplate);
    public static final RegistryObject<Item> DARKNESS_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("darkness_upgrade_smithing_template", TravelopticsTemplate::createDarknessUpgradeTemplate);
    public static final RegistryObject<Item> AQUA_SCROLL_DUMMY = ITEMS.register("aqua_scroll_dummy", AquaScrollDummyItem::new);
    public static final RegistryObject<Item> EYE_OF_NOTHINGNESS = ITEMS.register("eye_of_nothingness", () -> new DungeonEyeItem(new Item.Properties().requiredFeatures(), TravelopticsTags.EYE_OF_NOTHINGNESS_DESTINATION, 60, 0, 132));
    public static final RegistryObject<Item> TEST_WIZARD_SPAWN_EGG = ITEMS.register("test_wizard_spawn_egg", () -> new ForgeSpawnEggItem(TravelopticsEntities.TEST_WIZARD, 139, 65535, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> ENRAGED_DEAD_KING_SPAWN_EGG = ITEMS.register("enraged_dead_king_spawn_egg", () -> new ForgeSpawnEggItem(TravelopticsEntities.ENRAGED_DEAD_KING, 0x4D494D, 16024866, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> AQUAMANCER_SPAWN_EGG = ITEMS.register("aquamancer_spawn_egg", () -> new ForgeSpawnEggItem(TravelopticsEntities.AQUAMANCER_ENTITY, 1056815, 0xBCBCBC, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> AQUA_GRANDMASTER_SPAWN_EGG = ITEMS.register("aqua_grandmaster_spawn_egg", () -> new ForgeSpawnEggItem(TravelopticsEntities.AQUA_GRANDMASTER_ENTITY, 3558497, 12250612, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> THE_NIGHTWARDEN_SPAWN_EGG = ITEMS.register("the_nightwarden_spawn_egg", () -> new ForgeSpawnEggItem(TravelopticsEntities.NIGHTWARDEN_DEFEATED, 2360140, 15817471, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> VOID_TOME_SPAWN_EGG = ITEMS.register("void_tome_spawn_egg", () -> new ForgeSpawnEggItem(TravelopticsEntities.VOID_TOME_ENTITY, 13283822, 8526335, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> VOIDSHELF_GOLEM_SPAWN_EGG = ITEMS.register("voidshelf_golem_spawn_egg", () -> new ForgeSpawnEggItem(TravelopticsEntities.VOIDSHELF_GOLEM_ENTITY, 4271207, 9597390, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> FADING_MAGE_SPAWN_EGG = ITEMS.register("fading_mage_spawn_egg", () -> new ForgeSpawnEggItem(TravelopticsEntities.FADING_MAGE, 986895, 0x393939, new Item.Properties()));
    public static final RegistryObject<Item> FLAMES_OF_ELDRITCH = ITEMS.register("flames_of_eldritch", () -> new FlamesOfEldritchItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.BURNING_JUDGMENT, 6)})));
    public static final RegistryObject<Item> FLAMES_OF_ELDRITCH_LEVEL_ONE = ITEMS.register("flames_of_eldritch_level_one", () -> new FlamesOfEldritchLevelOneItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.BURNING_JUDGMENT, 6)})));
    public static final RegistryObject<Item> FLAMES_OF_ELDRITCH_LEVEL_TWO = ITEMS.register("flames_of_eldritch_level_two", () -> new FlamesOfEldritchLevelTwoItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.BURNING_JUDGMENT, 6)})));
    public static final RegistryObject<Item> FLAMES_OF_ELDRITCH_LEVEL_THREE = ITEMS.register("flames_of_eldritch_level_three", () -> new FlamesOfEldritchLevelThreeItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.BURNING_JUDGMENT, 6)})));
    public static final RegistryObject<Item> ABYSSAL_TIDECALLER = ITEMS.register("abyssal_tidecaller", () -> new AbyssalTidecallerItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.SHADOWED_MIASMA_SPELL, 3)})));
    public static final RegistryObject<Item> ABYSSAL_TIDECALLER_LEVEL_ONE = ITEMS.register("abyssal_tidecaller_level_one", () -> new AbyssalTidecallerLevelOneItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.SHADOWED_MIASMA_SPELL, 3)})));
    public static final RegistryObject<Item> ABYSSAL_TIDECALLER_LEVEL_TWO = ITEMS.register("abyssal_tidecaller_level_two", () -> new AbyssalTidecallerLevelTwoItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.SHADOWED_MIASMA_SPELL, 3)})));
    public static final RegistryObject<Item> ABYSSAL_TIDECALLER_LEVEL_THREE = ITEMS.register("abyssal_tidecaller_level_three", () -> new AbyssalTidecallerLevelThreeItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.SHADOWED_MIASMA_SPELL, 3)})));
    public static final RegistryObject<Item> SCOURGE_OF_THE_SANDS = ITEMS.register("scourge_of_the_sands", () -> new ScourgeOfTheSandsItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.STELE_CASCADE_SPELL, 6)})));
    public static final RegistryObject<Item> SCOURGE_OF_THE_SANDS_LEVEL_ONE = ITEMS.register("scourge_of_the_sands_level_one", () -> new ScourgeOfTheSandsLevelOneItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.STELE_CASCADE_SPELL, 6)})));
    public static final RegistryObject<Item> SCOURGE_OF_THE_SANDS_LEVEL_TWO = ITEMS.register("scourge_of_the_sands_level_two", () -> new ScourgeOfTheSandsLevelTwoItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.STELE_CASCADE_SPELL, 6)})));
    public static final RegistryObject<Item> SCOURGE_OF_THE_SANDS_LEVEL_THREE = ITEMS.register("scourge_of_the_sands_level_three", () -> new ScourgeOfTheSandsLevelThreeItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.STELE_CASCADE_SPELL, 6)})));
    public static final RegistryObject<Item> THORNS_OF_OBLIVION = ITEMS.register("thorns_of_oblivion", () -> new ThornsOfOblivionItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(SpellRegistry.BLIGHT_SPELL, 8)})));
    public static final RegistryObject<Item> THORNS_OF_OBLIVION_LEVEL_ONE = ITEMS.register("thorns_of_oblivion_level_one", () -> new ThornsOfOblivionLevelOneItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(SpellRegistry.BLIGHT_SPELL, 8)})));
    public static final RegistryObject<Item> THORNS_OF_OBLIVION_LEVEL_TWO = ITEMS.register("thorns_of_oblivion_level_two", () -> new ThornsOfOblivionLevelTwoItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(SpellRegistry.BLIGHT_SPELL, 8)})));
    public static final RegistryObject<Item> THORNS_OF_OBLIVION_LEVEL_THREE = ITEMS.register("thorns_of_oblivion_level_three", () -> new ThornsOfOblivionLevelThreeItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(SpellRegistry.BLIGHT_SPELL, 8)})));
    public static final RegistryObject<Item> HARBINGERS_WRATH = ITEMS.register("harbingers_wrath", () -> new HarbingersWrathItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.EM_PULSE, 5)})));
    public static final RegistryObject<Item> HARBINGERS_WRATH_LEVEL_ONE = ITEMS.register("harbingers_wrath_level_one", () -> new HarbingersWrathLevelOneItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.EM_PULSE, 5)})));
    public static final RegistryObject<Item> HARBINGERS_WRATH_LEVEL_TWO = ITEMS.register("harbingers_wrath_level_two", () -> new HarbingersWrathLevelTwoItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.EM_PULSE, 5)})));
    public static final RegistryObject<Item> HARBINGERS_WRATH_LEVEL_THREE = ITEMS.register("harbingers_wrath_level_three", () -> new HarbingersWrathLevelThreeItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.EM_PULSE, 5)})));
    public static final RegistryObject<Item> VOIDSTRIKE_REAPER = ITEMS.register("voidstrike_reaper", () -> new VoidstrikeReaperItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.VORTEX_PUNCH_SPELL, 3)})));
    public static final RegistryObject<Item> VOIDSTRIKE_REAPER_LEVEL_ONE = ITEMS.register("voidstrike_reaper_level_one", () -> new VoidstrikeReaperLevelOneItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.VORTEX_PUNCH_SPELL, 3)})));
    public static final RegistryObject<Item> VOIDSTRIKE_REAPER_LEVEL_TWO = ITEMS.register("voidstrike_reaper_level_two", () -> new VoidstrikeReaperLevelTwoItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.VORTEX_PUNCH_SPELL, 3)})));
    public static final RegistryObject<Item> VOIDSTRIKE_REAPER_LEVEL_THREE = ITEMS.register("voidstrike_reaper_level_three", () -> new VoidstrikeReaperLevelThreeItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.VORTEX_PUNCH_SPELL, 3)})));
    public static final RegistryObject<Item> CURSED_WRAITHBLADE = ITEMS.register("cursed_wraithblade", () -> new CursedWraithbladeItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.CURSED_BLAST_SPELL, 1)})));
    public static final RegistryObject<Item> CURSED_WRAITHBLADE_LEVEL_ONE = ITEMS.register("cursed_wraithblade_level_one", () -> new CursedWraithbladeLevelOneItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.CURSED_BLAST_SPELL, 1)})));
    public static final RegistryObject<Item> CURSED_WRAITHBLADE_LEVEL_TWO = ITEMS.register("cursed_wraithblade_level_two", () -> new CursedWraithbladeLevelTwoItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.CURSED_BLAST_SPELL, 1)})));
    public static final RegistryObject<Item> CURSED_WRAITHBLADE_LEVEL_THREE = ITEMS.register("cursed_wraithblade_level_three", () -> new CursedWraithbladeLevelThreeItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.CURSED_BLAST_SPELL, 1)})));
    public static final RegistryObject<Item> GAUNTLET_OF_EXTINCTION = ITEMS.register("gauntlet_of_extinction", () -> new GauntletOfExtinctionItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.EXTINCTION_SPELL, 1)})));
    public static final RegistryObject<Item> GAUNTLET_OF_EXTINCTION_LEVEL_ONE = ITEMS.register("gauntlet_of_extinction_level_one", () -> new GauntletOfExtinctionLevelOneItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.EXTINCTION_SPELL, 1)})));
    public static final RegistryObject<Item> GAUNTLET_OF_EXTINCTION_LEVEL_TWO = ITEMS.register("gauntlet_of_extinction_level_two", () -> new GauntletOfExtinctionLevelTwoItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.EXTINCTION_SPELL, 1)})));
    public static final RegistryObject<Item> GAUNTLET_OF_EXTINCTION_LEVEL_THREE = ITEMS.register("gauntlet_of_extinction_level_three", () -> new GauntletOfExtinctionLevelThreeItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.EXTINCTION_SPELL, 1)})));
    public static final RegistryObject<Item> MECHANIZED_WRAITHBLADE = ITEMS.register("mechanized_wraithblade", () -> new MechanizedWraithbladeItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.RAPID_LASER_SPELL, 5)})));
    public static final RegistryObject<Item> MECHANIZED_WRAITHBLADE_LEVEL_ONE = ITEMS.register("mechanized_wraithblade_level_one", () -> new MechanizedWraithbladeLevelOneItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.RAPID_LASER_SPELL, 5)})));
    public static final RegistryObject<Item> MECHANIZED_WRAITHBLADE_LEVEL_TWO = ITEMS.register("mechanized_wraithblade_level_two", () -> new MechanizedWraithbladeLevelTwoItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.RAPID_LASER_SPELL, 5)})));
    public static final RegistryObject<Item> MECHANIZED_WRAITHBLADE_LEVEL_THREE = ITEMS.register("mechanized_wraithblade_level_three", () -> new MechanizedWraithbladeLevelThreeItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.RAPID_LASER_SPELL, 5)})));
    public static final RegistryObject<Item> THE_OBLITERATOR = ITEMS.register("the_obliterator", () -> new TheObliteratorItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.SHADOWED_MIASMA_SPELL, 3)})));
    public static final RegistryObject<Item> THE_OBLITERATOR_LEVEL_ONE = ITEMS.register("the_obliterator_level_one", () -> new TheObliteratorLevelOneItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.SHADOWED_MIASMA_SPELL, 3)})));
    public static final RegistryObject<Item> THE_OBLITERATOR_LEVEL_TWO = ITEMS.register("the_obliterator_level_two", () -> new TheObliteratorLevelTwoItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.SHADOWED_MIASMA_SPELL, 3)})));
    public static final RegistryObject<Item> THE_OBLITERATOR_LEVEL_THREE = ITEMS.register("the_obliterator_level_three", () -> new TheObliteratorLevelThreeItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.SHADOWED_MIASMA_SPELL, 3)})));
    public static final RegistryObject<Item> INFERNAL_DEVASTATOR = ITEMS.register("infernal_devastator", () -> new InfernalDevastatorItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.GYRO_SLASH_SPELL, 1)})));
    public static final RegistryObject<Item> INFERNAL_DEVASTATOR_LEVEL_ONE = ITEMS.register("infernal_devastator_level_one", () -> new InfernalDevastatorLevelOneItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.GYRO_SLASH_SPELL, 1)})));
    public static final RegistryObject<Item> INFERNAL_DEVASTATOR_LEVEL_TWO = ITEMS.register("infernal_devastator_level_two", () -> new InfernalDevastatorLevelTwoItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.GYRO_SLASH_SPELL, 1)})));
    public static final RegistryObject<Item> INFERNAL_DEVASTATOR_LEVEL_THREE = ITEMS.register("infernal_devastator_level_three", () -> new InfernalDevastatorLevelThreeItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.GYRO_SLASH_SPELL, 1)})));
    public static final RegistryObject<Item> ETERNAL_MAELSTROM_TRIDENT = ITEMS.register("trident_of_the_eternal_maelstrom", () -> new TridentOfTheEternalMaelstromItem(new Item.Properties().durability(((Integer)WeaponConfig.eternalMaelstromDurability.get()).intValue()).requiredFeatures(RARITY_AQUATIC).requiredFeatures()));
    public static final RegistryObject<Item> ETERNAL_MAELSTROM_TRIDENT_LEVEL_ONE = ITEMS.register("trident_of_the_eternal_maelstrom_level_one", () -> new TridentOfTheEternalMaelstromLevelOneItem(new Item.Properties().durability(((Integer)WeaponConfig.eternalMaelstromDurability.get()).intValue()).requiredFeatures(RARITY_AQUATIC).requiredFeatures()));
    public static final RegistryObject<Item> ETERNAL_MAELSTROM_TRIDENT_LEVEL_TWO = ITEMS.register("trident_of_the_eternal_maelstrom_level_two", () -> new TridentOfTheEternalMaelstromLevelTwoItem(new Item.Properties().durability(((Integer)WeaponConfig.eternalMaelstromDurability.get()).intValue()).requiredFeatures(RARITY_AQUATIC).requiredFeatures()));
    public static final RegistryObject<Item> ETERNAL_MAELSTROM_TRIDENT_LEVEL_THREE = ITEMS.register("trident_of_the_eternal_maelstrom_level_three", () -> new TridentOfTheEternalMaelstromLevelThreeItem(new Item.Properties().durability(((Integer)WeaponConfig.eternalMaelstromDurability.get()).intValue()).requiredFeatures(RARITY_AQUATIC).requiredFeatures()));
    public static final RegistryObject<Item> STELLOTHORN = ITEMS.register("stellothorn", () -> new StellothornItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.SHEAR_OF_THE_STARS_SPELL, 1)})));
    public static final RegistryObject<Item> STELLOTHORN_LEVEL_ONE = ITEMS.register("stellothorn_level_one", () -> new StellothornLevelOneItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.SHEAR_OF_THE_STARS_SPELL, 1)})));
    public static final RegistryObject<Item> STELLOTHORN_LEVEL_TWO = ITEMS.register("stellothorn_level_two", () -> new StellothornLevelTwoItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.SHEAR_OF_THE_STARS_SPELL, 1)})));
    public static final RegistryObject<Item> STELLOTHORN_LEVEL_THREE = ITEMS.register("stellothorn_level_three", () -> new StellothornLevelThreeItem(SpellDataRegistryHolder.of((SpellDataRegistryHolder[])new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(TravelopticsSpells.SHEAR_OF_THE_STARS_SPELL, 1)})));
    public static final RegistryObject<AbyssalHideArmorItem> ABYSSAL_HIDE_ARMOR_HAT = ITEMS.register("abyssal_hide_armor_hat", () -> new AbyssalHideArmorItem(ArmorItem.Type.HELMET, new Item.Properties().requiredFeatures(RARITY_ABYSSAL)));
    public static final RegistryObject<AbyssalHideArmorItem> ABYSSAL_HIDE_ARMOR_ROBE = ITEMS.register("abyssal_hide_armor_robe", () -> new AbyssalHideArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().requiredFeatures(RARITY_ABYSSAL)));
    public static final RegistryObject<AbyssalHideArmorItem> ABYSSAL_HIDE_ARMOR_LEGGINGS = ITEMS.register("abyssal_hide_armor_leggings", () -> new AbyssalHideArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties().requiredFeatures(RARITY_ABYSSAL)));
    public static final RegistryObject<AbyssalHideArmorItem> ABYSSAL_HIDE_ARMOR_BOOTS = ITEMS.register("abyssal_hide_armor_boots", () -> new AbyssalHideArmorItem(ArmorItem.Type.BOOTS, new Item.Properties().requiredFeatures(RARITY_ABYSSAL)));
    public static final RegistryObject<PrimordialCrestArmorItem> PRIMORDIAL_CREST_ARMOR_HELMET = ITEMS.register("primordial_crest_armor_helmet", () -> new PrimordialCrestArmorItem(ArmorItem.Type.HELMET, new Item.Properties().requiredFeatures(RARITY_PRIMORDIAL)));
    public static final RegistryObject<PrimordialCrestArmorItem> PRIMORDIAL_CREST_ARMOR_CHESTPLATE = ITEMS.register("primordial_crest_armor_chestplate", () -> new PrimordialCrestArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().requiredFeatures(RARITY_PRIMORDIAL)));
    public static final RegistryObject<PrimordialCrestArmorItem> PRIMORDIAL_CREST_ARMOR_LEGGINGS = ITEMS.register("primordial_crest_armor_leggings", () -> new PrimordialCrestArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties().requiredFeatures(RARITY_PRIMORDIAL)));
    public static final RegistryObject<PrimordialCrestArmorItem> PRIMORDIAL_CREST_ARMOR_BOOTS = ITEMS.register("primordial_crest_armor_boots", () -> new PrimordialCrestArmorItem(ArmorItem.Type.BOOTS, new Item.Properties().requiredFeatures(RARITY_PRIMORDIAL)));
    public static final RegistryObject<TectonicCrestArmorItem> TECTONIC_CREST_ARMOR_HELMET = ITEMS.register("tectonic_crest_armor_helmet", () -> new TectonicCrestArmorItem(ArmorItem.Type.HELMET, new Item.Properties().requiredFeatures(RARITY_TECTONIC)));
    public static final RegistryObject<TectonicCrestArmorItem> TECTONIC_CREST_ARMOR_CHESTPLATE = ITEMS.register("tectonic_crest_armor_chestplate", () -> new TectonicCrestArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().requiredFeatures(RARITY_TECTONIC)));
    public static final RegistryObject<TectonicCrestArmorItem> TECTONIC_CREST_ARMOR_LEGGINGS = ITEMS.register("tectonic_crest_armor_leggings", () -> new TectonicCrestArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties().requiredFeatures(RARITY_TECTONIC)));
    public static final RegistryObject<TectonicCrestArmorItem> TECTONIC_CREST_ARMOR_BOOTS = ITEMS.register("tectonic_crest_armor_boots", () -> new TectonicCrestArmorItem(ArmorItem.Type.BOOTS, new Item.Properties().requiredFeatures(RARITY_TECTONIC)));
    public static final RegistryObject<CursedWraithguardArmorItem> CURSED_WRAITHGUARD_ARMOR_CROWN = ITEMS.register("cursed_wraithguard_crown", () -> new CursedWraithguardArmorItem(ArmorItem.Type.HELMET, new Item.Properties().requiredFeatures(RARITY_CURSED)));
    public static final RegistryObject<CursedWraithguardArmorItem> CURSED_WRAITHGUARD_ARMOR_CHESTPLATE = ITEMS.register("cursed_wraithguard_chestplate", () -> new CursedWraithguardArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().requiredFeatures(RARITY_CURSED)));
    public static final RegistryObject<CursedWraithguardArmorItem> CURSED_WRAITHGUARD_ARMOR_SPECTRAL_WRAPPING = ITEMS.register("cursed_wraithguard_spectral_wrapping", () -> new CursedWraithguardArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties().requiredFeatures(RARITY_CURSED)));
    public static final RegistryObject<CursedWraithguardArmorItem> CURSED_WRAITHGUARD_ARMOR_BOOTS = ITEMS.register("cursed_wraithguard_boots", () -> new CursedWraithguardArmorItem(ArmorItem.Type.BOOTS, new Item.Properties().requiredFeatures(RARITY_CURSED)));
    public static final RegistryObject<DeeplingMageArmorItem> DEEPLING_MAGE_ARMOR_HELMET = ITEMS.register("deepling_mage_armor_helmet", () -> new DeeplingMageArmorItem(ArmorItem.Type.HELMET, new Item.Properties().requiredFeatures(RARITY_AQUATIC)));
    public static final RegistryObject<DeeplingMageArmorItem> DEEPLING_MAGE_ARMOR_ROBE = ITEMS.register("deepling_mage_armor_robe", () -> new DeeplingMageArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().requiredFeatures(RARITY_AQUATIC)));
    public static final RegistryObject<DeeplingMageArmorItem> DEEPLING_MAGE_ARMOR_LEGGINGS = ITEMS.register("deepling_mage_armor_leggings", () -> new DeeplingMageArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties().requiredFeatures(RARITY_AQUATIC)));
    public static final RegistryObject<DeeplingMageArmorItem> DEEPLING_MAGE_ARMOR_BOOTS = ITEMS.register("deepling_mage_armor_boots", () -> new DeeplingMageArmorItem(ArmorItem.Type.BOOTS, new Item.Properties().requiredFeatures(RARITY_AQUATIC)));
    public static final RegistryObject<MechanizedExoskeletonArmorItem> MECHANIZED_EXOSKELETON_ARMOR_HELMET = ITEMS.register("mechanized_exoskeleton_helmet", () -> new MechanizedExoskeletonArmorItem(ArmorItem.Type.HELMET, new Item.Properties().requiredFeatures(RARITY_MECHANIZED)));
    public static final RegistryObject<MechanizedExoskeletonArmorItem> MECHANIZED_EXOSKELETON_ARMOR_CHESTPLATE = ITEMS.register("mechanized_exoskeleton_chestplate", () -> new MechanizedExoskeletonArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().requiredFeatures(RARITY_MECHANIZED)));
    public static final RegistryObject<MechanizedExoskeletonArmorItem> MECHANIZED_EXOSKELETON_ARMOR_LEGGINGS = ITEMS.register("mechanized_exoskeleton_leggings", () -> new MechanizedExoskeletonArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties().requiredFeatures(RARITY_MECHANIZED)));
    public static final RegistryObject<MechanizedExoskeletonArmorItem> MECHANIZED_EXOSKELETON_ARMOR_BOOTS = ITEMS.register("mechanized_exoskeleton_boots", () -> new MechanizedExoskeletonArmorItem(ArmorItem.Type.BOOTS, new Item.Properties().requiredFeatures(RARITY_MECHANIZED)));
    public static final RegistryObject<ForlornHarbingerArmorItem> FORLORN_HARBINGER_ARMOR_HOOD = ITEMS.register("forlorn_harbinger_hood", () -> new ForlornHarbingerArmorItem(ArmorItem.Type.HELMET, new Item.Properties().requiredFeatures(ACItemRegistry.RARITY_DEMONIC)));
    public static final RegistryObject<ForlornHarbingerArmorItem> FORLORN_HARBINGER_ARMOR_ROBE = ITEMS.register("forlorn_harbinger_robe", () -> new ForlornHarbingerArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().requiredFeatures(ACItemRegistry.RARITY_DEMONIC)));
    public static final RegistryObject<ForlornHarbingerArmorItem> FORLORN_HARBINGER_ARMOR_LEGGINGS = ITEMS.register("forlorn_harbinger_leggings", () -> new ForlornHarbingerArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties().requiredFeatures(ACItemRegistry.RARITY_DEMONIC)));
    public static final RegistryObject<ForlornHarbingerArmorItem> FORLORN_HARBINGER_ARMOR_BOOTS = ITEMS.register("forlorn_harbinger_boots", () -> new ForlornHarbingerArmorItem(ArmorItem.Type.BOOTS, new Item.Properties().requiredFeatures(ACItemRegistry.RARITY_DEMONIC)));
    public static final RegistryObject<Item> DUSKWOOD_BOOKSHELF = TravelopticsItems.block(TravelopticsBlocks.DUSKWOOD_BOOKSHELF);
    public static final RegistryObject<Item> OBSIDIAN_BOOKSHELF = TravelopticsItems.blockWithProperties(TravelopticsBlocks.OBSIDIAN_BOOKSHELF, new Item.Properties().requiredFeatures(Rarity.EPIC));
    public static final RegistryObject<Item> RUNED_PURPUR_BLOCK = TravelopticsItems.block(TravelopticsBlocks.RUNED_PURPUR_BLOCK);

    private static RegistryObject<Item> block(RegistryObject<Block> block) {
        return ITEMS.register(block.getId().isAllowedInResourceLocation(), () -> new BlockItem((Block)block.get(), new Item.Properties()));
    }

    private static RegistryObject<Item> blockWithProperties(RegistryObject<Block> block, Item.Properties props) {
        return ITEMS.register(block.getId().isAllowedInResourceLocation(), () -> new BlockItem((Block)block.get(), props));
    }

    public static Collection<RegistryObject<Item>> getTravelopticsItems() {
        return ITEMS.getEntries();
    }
}

