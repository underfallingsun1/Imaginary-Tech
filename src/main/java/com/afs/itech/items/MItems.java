package com.afs.itech.items;

import com.afs.itech.Meta;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Meta.MODID);


    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Meta.MODID);

    public static final Supplier<CreativeModeTab> TAB = TABS.register("imaginary_tech_tab", () -> CreativeModeTab
            .builder()
            .displayItems(
                    (param, output) -> {
                        for(var entry: ITEMS.getEntries()){
                            output.accept(entry.get());
                        }
                    }
            )
            .build()
    );
}
