package com.afs.itech.blocks;

import com.afs.itech.Meta;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(Meta.MODID);

    public static final DeferredHolder<Block, SimpleItemBaseBlock> SIMPLE_ITEM_BASE = BLOCKS.register(
            "simple_item_base", () -> new SimpleItemBaseBlock(BlockBehaviour.Properties.of())
    );

    public static final DeferredRegister<MapCodec<? extends Block>> BLOCK_TYPES = DeferredRegister.create(
            Registries.BLOCK_TYPE, Meta.MODID
    );

    public static final Supplier<MapCodec<SimpleItemBaseBlock>> SIMPLE_ITEM_BASE_TYPE = BLOCK_TYPES.register(
            "simple_item_base", () -> Block.simpleCodec(SimpleItemBaseBlock::new)
    );
}
