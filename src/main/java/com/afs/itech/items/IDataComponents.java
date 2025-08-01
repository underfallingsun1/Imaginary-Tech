package com.afs.itech.items;

import com.afs.itech.Meta;
import com.afs.itech.energySystem.EnergyCrystalData;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class IDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Meta.MODID);

    public static final Supplier<DataComponentType<EnergyCrystalData>> ENERGY_CRYSTAL =
            DATA_COMPONENTS_TYPES.registerComponentType("energy_crystal", builder ->
                    builder.persistent(EnergyCrystalData.CODEC).networkSynchronized(EnergyCrystalData.STREAM_CODEC)
            );
}
