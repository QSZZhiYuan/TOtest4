/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Suppliers
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  com.mojang.serialization.codecs.RecordCodecBuilder$Instance
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  it.unimi.dsi.fastutil.objects.ObjectList
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.storage.loot.LootContext
 *  net.minecraft.world.level.storage.loot.LootTable
 *  net.minecraft.world.level.storage.loot.predicates.LootItemCondition
 *  net.minecraftforge.common.loot.IGlobalLootModifier
 *  net.minecraftforge.common.loot.LootModifier
 *  org.jetbrains.annotations.NotNull
 */
package com.gametechbc.traveloptics.loot;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class UniversalLootModifier
extends LootModifier {
    public static final Supplier<Codec<UniversalLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> UniversalLootModifier.codecStart((RecordCodecBuilder.Instance)inst).and((App)Codec.STRING.fieldOf("loot_table").forGetter(m -> m.lootTable)).apply((Applicative)inst, UniversalLootModifier::new)));
    private final String lootTable;

    protected UniversalLootModifier(LootItemCondition[] conditionsIn, String lootTable) {
        super(conditionsIn);
        this.lootTable = lootTable;
    }

    @NotNull
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (!context.getQueriedLootTableId().isAllowedInResourceLocation().startsWith("chests/")) {
            return generatedLoot;
        }
        ResourceLocation lootTablePath = new ResourceLocation(this.lootTable);
        LootTable lootTable = context.getLevel().getServer().reloadableRegistries().getLootTable(lootTablePath);
        ObjectArrayList additionalLoot = new ObjectArrayList();
        lootTable.getAvailableSlots(context, arg_0 -> ((ObjectArrayList)additionalLoot).add(arg_0));
        generatedLoot.addAll((ObjectList)additionalLoot);
        return generatedLoot;
    }

    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}

