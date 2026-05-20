package com.mustafa.optibest;

import net.fabricmc.api.ModInitializer;

public class OptiBest implements ModInitializer {
    @Override
    public void onInitialize() {
        // Ayarlar menüsünü (MidnightLib) başlatır
        OptiBestConfig.init("optibest", OptiBestConfig.class);
        
        System.out.println("OptiBest Modu basariyla aktif edildi! FPS ucurmaya hazir.");
    }
}
