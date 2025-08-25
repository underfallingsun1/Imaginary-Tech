package com.afs.itech.energySystem;

import com.afs.itech.utils.Utils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.List;

public record EnergyCrystalType(
        int maxDurability,
        int baseProductionRate,
        int capacity,
        CrystalWorkingEnvironment.Environment workingEnvironment,
        List<Double> DamageChance
) {
    public static final Codec<EnergyCrystalType> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                    Codec.INT.fieldOf("durability").forGetter(EnergyCrystalType::maxDurability),
                    Codec.INT.fieldOf("base_produce_rate").forGetter(EnergyCrystalType::baseProductionRate),
                    Codec.INT.fieldOf("capacity").forGetter(EnergyCrystalType::capacity),
                    CrystalWorkingEnvironment.CODEC.optionalFieldOf("environment", CrystalWorkingEnvironment.None.NONE).forGetter(EnergyCrystalType::workingEnvironment),
                    Codec.DOUBLE.listOf().fieldOf("damageChance").forGetter(EnergyCrystalType::DamageChance)
            ).apply(inst, EnergyCrystalType::new)
    );

    public static final ResourceKey<Registry<EnergyCrystalType>> REGISTRY_KEY = ResourceKey.createRegistryKey(Utils.modLoc("energy_crystal_type"));
}
