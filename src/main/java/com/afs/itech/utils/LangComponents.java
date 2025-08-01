package com.afs.itech.utils;

import net.minecraft.network.chat.Component;

import java.util.Objects;

public class LangComponents {
    public static final LangComponentBase ENERGY_CRYSTAL_TIPS = new LangComponentBase("tips.imaginarytech.energy_crystal");

    public static class LangComponentBase{
        private final String base;

        private LangComponentBase(String base){
            this.base = base;
        }

        public String getText(){
            return base;
        }

        public Component apply(){
            return Component.translatable(base);
        }

        public Component apply(Object... values){
            return Component.translatable(base, values);
        }
    }
}
