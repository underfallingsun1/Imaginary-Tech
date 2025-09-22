package com.afs.itech.blocks.blockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;

public class SimpleItemBaseBlockEntity extends AbstractItemBaseBlockEntity {
    public SimpleItemBaseBlockEntity(BlockPos pos, BlockState blockState) {
        super(MBlockEntityTypes.SIMPLE_ITEM_BASE.get(), pos, blockState);
    }

    public IItemHandler getItemHandle(){
        return stack;
    }

    @Override
    protected boolean isValid(ItemStack stack) {
        return true;
    }

    @Override
    protected int getCountLimit() {
        return 1;
    }
}
