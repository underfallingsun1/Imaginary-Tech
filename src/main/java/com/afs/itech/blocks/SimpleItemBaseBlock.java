package com.afs.itech.blocks;

import com.afs.itech.blocks.blockEntities.SimpleItemBaseBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

public class SimpleItemBaseBlock extends Block implements EntityBlock {
    public SimpleItemBaseBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SimpleItemBaseBlockEntity(blockPos, blockState);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level,
                                              BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(hand == InteractionHand.OFF_HAND) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        if(!level.isClientSide){
            BlockEntity be = level.getBlockEntity(pos);
            if(be instanceof SimpleItemBaseBlockEntity sBase){
                IItemHandler stackOnBase = sBase.getItemHandle();
                ItemStack baseStack = stackOnBase.getStackInSlot(0);
                if(baseStack.isEmpty() && !stack.isEmpty()){
                    int j = stackOnBase.insertItem(0, stack, false).getCount();
                    stack.setCount(j);
                }
                else if(stack.isEmpty() && !baseStack.isEmpty()){
                    player.setItemSlot(EquipmentSlot.MAINHAND, stackOnBase.extractItem(0, 64, false));
                }
            }
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }
}
