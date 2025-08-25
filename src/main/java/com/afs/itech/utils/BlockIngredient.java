package com.afs.itech.utils;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BlockIngredient{
    public static Predicate<Block> list(Block... blocks){
        Set<Block> blockSet = Set.of(blocks);
        return blockSet::contains;
    }

    @SuppressWarnings("unchecked")
    public static Predicate<Block> tagList(TagKey<Block>... tags){
        Set<Block> blockSet = new HashSet<>();
        for(var tag: tags){
            blockSet.addAll(BuiltInRegistries.BLOCK.getOrCreateTag(tag).stream().map(Holder::value).collect(Collectors.toSet()));
        }
        return blockSet::contains;
    }


}
