package com.afs.itech.utils;

import net.minecraft.network.chat.Component;

import java.util.Objects;

public class LangComponents {
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

    public static final LangComponentBase TAB_TITLE = new LangComponentBase("title.tab.imaginarytech");
}
