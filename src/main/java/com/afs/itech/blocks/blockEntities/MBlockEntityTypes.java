package com.afs.itech.blocks.blockEntities;

import com.afs.itech.Meta;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BE_TYPES = DeferredRegister.create(
            Registries.BLOCK_ENTITY_TYPE, Meta.MODID
    );

    
}
