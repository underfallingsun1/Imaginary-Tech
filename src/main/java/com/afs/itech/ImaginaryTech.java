package com.afs.itech;

import com.afs.itech.blocks.IBlocks;
import com.afs.itech.items.IDataComponents;
import com.afs.itech.items.IItems;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import org.checkerframework.checker.signature.qual.SignatureUnknown;
import org.slf4j.Logger;

@Mod(Meta.MODID)
public class ImaginaryTech {
    public static final Logger LOGGER = LogUtils.getLogger();

    public ImaginaryTech(IEventBus bus, ModContainer container) {
        LOGGER.info("mod " + Meta.MODID + " start to load.");
        IBlocks.BLOCKS.register(bus);
        IBlocks.BLOCK_TYPES.register(bus);
        IItems.ITEMS.register(bus);
        IItems.TABS.register(bus);
        IDataComponents.DATA_COMPONENTS_TYPES.register(bus);
    }

    @EventBusSubscriber(modid = Meta.MODID)
    private static class LoadEvents{
        @SubscribeEvent
        public static void onCommonSetup(FMLCommonSetupEvent e){

        }

        @SubscribeEvent
        public static void onServerLoad(FMLDedicatedServerSetupEvent e){

        }
    }
}