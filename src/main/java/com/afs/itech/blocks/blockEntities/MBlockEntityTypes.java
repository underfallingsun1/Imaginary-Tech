package com.afs.itech.blocks.blockEntities;

import com.afs.itech.Meta;
import com.afs.itech.blocks.MBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BE_TYPES = DeferredRegister.create(
            Registries.BLOCK_ENTITY_TYPE, Meta.MODID
    );

    public static final Supplier<BlockEntityType<SimpleItemBaseBlockEntity>> SIMPLE_ITEM_BASE =
            BE_TYPES.register("simple_item_base", () -> BlockEntityType.Builder.of(
                    SimpleItemBaseBlockEntity::new, MBlocks.SIMPLE_ITEM_BASE.get()).build(null));
}
