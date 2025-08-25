package com.afs.itech.datagen;

import com.afs.itech.Meta;
import com.afs.itech.datagen.data.EnergyCrystalTypeDataGenerator;
import com.afs.itech.energySystem.EnergyCrystalType;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Meta.MODID)
public class GatherData {
    @SubscribeEvent
    public static void gather(GatherDataEvent e){
        DataGenerator generator = e.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper helper = e.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> registries = e.getLookupProvider();

        RegistrySetBuilder builder = new RegistrySetBuilder();
        new EnergyCrystalTypeDataGenerator(builder).addEntries();
        generator.addProvider(e.includeServer(), new DatapackBuiltinEntriesProvider(
                output, registries, builder, Set.of(Meta.MODID)
        ));
    }
}
