package com.afs.itech.datagen.data;

import com.afs.itech.utils.Utils;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;


public abstract class AbstractDataRegistry<T> {
    private final ResourceKey<Registry<T>> key;
    private final RegistrySetBuilder builder;

    protected AbstractDataRegistry(RegistrySetBuilder builder, ResourceKey<Registry<T>> registryKey){
        this.builder = builder;
        key = registryKey;
    }

    protected ResourceKey<T> generateKey(ResourceLocation location){
        return ResourceKey.create(key, location);
    }

    protected ResourceKey<T> generateKey(String name){
        return generateKey(Utils.modLoc(name));
    }

    protected void add(String name, T value){
        builder.add(key, bootstrap -> bootstrap.register(generateKey(name), value));
    }

    protected void add(ResourceLocation loc, T value){
        builder.add(key, bootstrap -> bootstrap.register(generateKey(loc), value));
    }

    protected abstract void addEntries();
}
