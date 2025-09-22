package com.afs.itech;

import com.afs.itech.blocks.MBlocks;
import com.afs.itech.blocks.blockEntities.MBlockEntityTypes;
import com.afs.itech.items.MDataComponents;
import com.afs.itech.items.MItems;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import org.slf4j.Logger;

@Mod(Meta.MODID)
public class ImaginaryTech {
    public static final Logger LOGGER = LogUtils.getLogger();

    public ImaginaryTech(IEventBus bus, ModContainer container) {
        LOGGER.info("mod " + Meta.MODID + " start to load.");
        MBlocks.BLOCKS.register(bus);
        MBlocks.BLOCK_TYPES.register(bus);
        MBlockEntityTypes.BE_TYPES.register(bus);
        MItems.ITEMS.register(bus);
        MItems.TABS.register(bus);
        MDataComponents.DATA_COMPONENTS_TYPES.register(bus);
    }

    @EventBusSubscriber(modid = Meta.MODID)
    private static class LoadEvents{
        @SubscribeEvent
        public static void addNewRegistry(NewRegistryEvent e){
        }

        @SubscribeEvent
        public static void addDataPackRegistry(DataPackRegistryEvent.NewRegistry e){

        }

        @SubscribeEvent
        public static void onCommonSetup(FMLCommonSetupEvent e){

        }

        @SubscribeEvent
        public static void onServerLoad(FMLDedicatedServerSetupEvent e){

        }
    }
}