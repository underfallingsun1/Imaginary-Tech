package com.afs.itech.datagen.data;

import com.afs.itech.energySystem.CrystalWorkingEnvironment;
import com.afs.itech.energySystem.EnergyCrystalType;
import net.minecraft.core.RegistrySetBuilder;

import java.util.List;

public class EnergyCrystalTypeDataGenerator extends AbstractDataRegistry<EnergyCrystalType>{
    public EnergyCrystalTypeDataGenerator(RegistrySetBuilder builder) {
        super(builder, EnergyCrystalType.REGISTRY_KEY);
    }

    @Override
    public void addEntries() {
        add("base", new EnergyCrystalType(1200, 15, 1200,CrystalWorkingEnvironment.None.NONE, List.of(0.2, 0.8)));
    }
}
