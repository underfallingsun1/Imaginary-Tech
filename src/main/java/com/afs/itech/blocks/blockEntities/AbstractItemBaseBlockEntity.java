package com.afs.itech.blocks.blockEntities;

import com.afs.itech.utils.SingleStackHandle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractItemBaseBlockEntity extends BlockEntity {
    public AbstractItemBaseBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        stack = new SingleStackHandle(this::isValid, getCountLimit());
    }

    protected final SingleStackHandle stack;

    protected abstract boolean isValid(ItemStack stack);

    protected abstract int getCountLimit();

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("item", stack.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        stack.deserializeNBT(registries, tag);
    }
}
