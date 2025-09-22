package com.afs.itech.utils;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.UnknownNullability;

import java.util.function.Predicate;

public class SingleStackHandle implements IItemHandler, INBTSerializable<CompoundTag> {
    ItemStack stack;
    Predicate<ItemStack> validChecker;
    int countLimit;

    public SingleStackHandle(ItemStack stack, Predicate<ItemStack> stackPredicate, int countLimit){
        this.stack = stack;
        validChecker = stackPredicate;
        this.countLimit = countLimit;
    }

    public SingleStackHandle(Predicate<ItemStack> stackPredicate, int cl){
        this(ItemStack.EMPTY, stackPredicate, cl);
    }

    public SingleStackHandle(int cl){
        this(ItemStack.EMPTY, stack -> true, cl);
    }

    public static SingleStackHandle Single(Predicate<ItemStack> checker){
        return new SingleStackHandle(checker, 1);
    }

    public int getCountLimit(){
        return countLimit > 0? countLimit: 64;
    }

    public ItemStack stack(){
        return stack;
    }

    @Override
    public int getSlots() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        if(i == 0) return stack;
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack insertItem(int i, ItemStack itemStack, boolean simulate) {
        if(i != 0 || !validChecker.test(itemStack) || itemStack.isEmpty() || !isItemValid(0, itemStack)){
            return itemStack;
        }
        if(stack == ItemStack.EMPTY){
            boolean isFullInsert = itemStack.getCount() <= getCountLimit();
            if(!simulate){
                if(isFullInsert) stack = itemStack;
                else stack = itemStack.copyWithCount(getCountLimit());
            }
            if(isFullInsert) return ItemStack.EMPTY;
            else return itemStack.copyWithCount(itemStack.getCount() - getCountLimit());
        }
        if(ItemStack.isSameItemSameComponents(itemStack, stack) && getCountLimit() > 1 && stack.getMaxStackSize() > 1){
            int total = itemStack.getCount() + stack.getCount();
            int limit = Math.min(stack.getMaxStackSize(), getCountLimit());
            boolean isFullInsert = total <= limit;
            if(!simulate){
                if(isFullInsert) stack = stack.copyWithCount(total);
                else stack = itemStack.copyWithCount(limit);
            }
            if(isFullInsert) return ItemStack.EMPTY;
            else return itemStack.copyWithCount(total - limit);
        }
        return itemStack;
    }

    @Override
    public ItemStack extractItem(int i, int count, boolean simulate) {
        if(i != 0 || stack.isEmpty()) return ItemStack.EMPTY;
        boolean fullyExtract = count >= stack.getCount();
        ItemStack result = stack.copy();
        if(!simulate){
            if(fullyExtract) stack = ItemStack.EMPTY;
            else stack = stack.copyWithCount(stack.getCount() - count);
        }
        if(fullyExtract) return result;
        else return result.copyWithCount(count);
    }

    @Override
    public int getSlotLimit(int i) {
        if(i != 0) return 0;
        if(stack.isEmpty()) return getCountLimit();
        else return Math.min(getCountLimit(), stack.getMaxStackSize());
    }

    @Override
    public boolean isItemValid(int i, ItemStack itemStack) {
        return i == 0 && validChecker.test(itemStack);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        if(!stack.isEmpty()){
            tag.put("item", stack.save(provider));
        }
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        if(tag.contains("item")) stack = ItemStack.parse(provider, tag).orElse(ItemStack.EMPTY);
        else stack = ItemStack.EMPTY;
    }
}
