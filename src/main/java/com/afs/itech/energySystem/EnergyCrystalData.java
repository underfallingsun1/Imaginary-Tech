package com.afs.itech.energySystem;

import com.afs.itech.utils.LangComponents;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record EnergyCrystalData(
        int durability,
        int maxDurability,
        int baseEnergyRate,
        int maxEnergyRate,
        double naturalDamageRate,
        double workingDamageRate,
        double baseEfficiency
)
{
    public static final Codec<EnergyCrystalData> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                    Codec.INT.fieldOf("durability").forGetter(EnergyCrystalData::durability),
                    Codec.INT.fieldOf("max_durability").forGetter(EnergyCrystalData::maxDurability),
                    Codec.INT.fieldOf("base_energy_rate").forGetter(EnergyCrystalData::baseEnergyRate),
                    Codec.INT.fieldOf("max_energy_rate").forGetter(EnergyCrystalData::maxEnergyRate),
                    Codec.DOUBLE.fieldOf("natural_damage_speed").forGetter(EnergyCrystalData::naturalDamageRate),
                    Codec.DOUBLE.fieldOf("working_damage_speed").forGetter(EnergyCrystalData::workingDamageRate),
                    Codec.DOUBLE.fieldOf("base_efficiency").forGetter(EnergyCrystalData::baseEfficiency)
            ).apply(inst, EnergyCrystalData::new)
    );

    public static final StreamCodec<ByteBuf, EnergyCrystalData> STREAM_CODEC = StreamCodec.ofMember(
            (data, o) -> {
                ByteBufCodecs.INT.encode(o, data.durability);
                ByteBufCodecs.INT.encode(o, data.maxDurability);
                ByteBufCodecs.INT.encode(o, data.baseEnergyRate);
                ByteBufCodecs.INT.encode(o, data.maxEnergyRate);
                ByteBufCodecs.DOUBLE.encode(o, data.naturalDamageRate);
                ByteBufCodecs.DOUBLE.encode(o, data.workingDamageRate);
                ByteBufCodecs.DOUBLE.encode(o, data.baseEfficiency);
            },
            o -> new EnergyCrystalData(
                    ByteBufCodecs.INT.decode(o), ByteBufCodecs.INT.decode(o), ByteBufCodecs.INT.decode(o),
                    ByteBufCodecs.INT.decode(o), ByteBufCodecs.DOUBLE.decode(o), ByteBufCodecs.DOUBLE.decode(o),
                    ByteBufCodecs.DOUBLE.decode(o)
            )
    );
    public Component asComponent(){
        return LangComponents.ENERGY_CRYSTAL_TIPS.apply(durability, (float)durability / maxDurability * 100,
                baseEnergyRate, maxEnergyRate, naturalDamageRate, workingDamageRate, (float)baseEfficiency * 100);
    }
}
