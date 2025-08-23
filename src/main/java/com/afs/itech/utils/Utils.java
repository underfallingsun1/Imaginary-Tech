package com.afs.itech.utils;

import com.afs.itech.Meta;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class Utils {
    public static ResourceLocation modLoc(String path){
        return ResourceLocation.fromNamespaceAndPath(Meta.MODID, path);
    }
}
