/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockBehaviour
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package com.gametechbc.traveloptics.init;

import com.gametechbc.traveloptics.blocks.DuskwookdBookshelfBlock;
import com.gametechbc.traveloptics.blocks.ObsidianBookshelfBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class TravelopticsBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create((IForgeRegistry)ForgeRegistries.BLOCKS, (String)"traveloptics");
    public static final RegistryObject<Block> DUSKWOOD_BOOKSHELF = BLOCKS.register("duskwood_bookshelf", DuskwookdBookshelfBlock::new);
    public static final RegistryObject<Block> OBSIDIAN_BOOKSHELF = BLOCKS.register("obsidian_bookshelf", ObsidianBookshelfBlock::new);
    public static final RegistryObject<Block> RUNED_PURPUR_BLOCK = BLOCKS.register("runed_purpur_block", () -> new Block(BlockBehaviour.Properties.instrument((BlockBehaviour)Blocks.PURPUR_BLOCK)));
}

