package com.afs.itech.datagen.data;

import com.afs.itech.utils.Utils;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;


public abstract class AbstractDataRegistry<T> {
    private final ResourceKey<Registry<T>> key;
    private final RegistrySetBuilder builder;
    private final Map<ResourceKey<T>, T> map;

    protected AbstractDataRegistry(RegistrySetBuilder builder, ResourceKey<Registry<T>> registryKey){
        this.builder = builder;
        key = registryKey;
        map = new HashMap<>();
    }

    protected ResourceKey<T> generateKey(ResourceLocation location){
        return ResourceKey.create(key, location);
    }

    protected ResourceKey<T> generateKey(String name){
        return generateKey(Utils.modLoc(name));
    }

    protected void add(String name, T value){
        map.put(generateKey(name), value);
    }

    protected void add(ResourceLocation loc, T value){
        map.put(generateKey(loc), value);
    }

    protected abstract void addEntries();

    public void register(){
        builder.add(key, bootstrap -> {
            for(var pKey: map.keySet()){
                bootstrap.register(pKey, map.get(key));
            }
        });
    }
}
