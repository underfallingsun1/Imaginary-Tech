package com.afs.itech.energySystem;

import com.afs.itech.Meta;
import com.afs.itech.utils.Utils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.*;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.function.Function;
import java.util.function.Supplier;

public class CrystalWorkingEnvironment {
    public static final ResourceKey<Registry<MapCodec<? extends Environment>>> ENVIRONMENT_KEY =
            ResourceKey.createRegistryKey(Utils.modLoc("environment"));
    public static final Registry<MapCodec<? extends Environment>> ENVIRONMENT = new RegistryBuilder<>(ENVIRONMENT_KEY)
            .sync(true).defaultKey(Utils.modLoc("none")).create();

    public static final DeferredRegister<MapCodec<? extends Environment>> ENVIRONMENTS = DeferredRegister.create(
            ENVIRONMENT_KEY, Meta.MODID
    );

    public static abstract class Environment{
        public abstract MapCodec<? extends Environment> type();

        public static final Codec<Environment> CODEC = ENVIRONMENT.byNameCodec()
                .dispatch(Environment::type, Function.identity());
    }

    public static class None extends Environment{
        public static final None NONE = new None();
        private None(){}

        @Override
        public MapCodec<? extends Environment> type() {
            return MapCodec.unit(NONE);
        }
    }

    public static final Supplier<MapCodec<? extends Environment>> NONE = ENVIRONMENTS.register(
            "none", None.NONE::type
    );
}
