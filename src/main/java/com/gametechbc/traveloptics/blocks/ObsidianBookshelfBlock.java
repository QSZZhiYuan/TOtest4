/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.NoteBlockInstrument
 *  net.minecraft.world.level.material.MapColor
 */
package com.gametechbc.traveloptics.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class ObsidianBookshelfBlock
extends Block {
    public ObsidianBookshelfBlock() {
        super(BlockBehaviour.Properties.instrument().instrument(NoteBlockInstrument.BASEDRUM).instrument(state -> 7).instrument(MapColor.COLOR_PURPLE).instrument(SoundType.MEDIUM_AMETHYST_BUD).instrument(1.0f, 10.0f).requiresCorrectToolForDrops().hasPostProcess((bs, br, bp) -> true).destroyTime((bs, br, bp) -> true));
    }

    public float getEnchantPowerBonus(BlockState state, LevelReader world, BlockPos pos) {
        return 2.0f;
    }
}

