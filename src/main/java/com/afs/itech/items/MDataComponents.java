package com.afs.itech.items;

import com.afs.itech.Meta;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Meta.MODID);
}
