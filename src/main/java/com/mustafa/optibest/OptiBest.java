package com.mustafa.optibest;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptiBest implements ModInitializer {
    public static final String MOD_ID = "optibest";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // Eski MidnightConfig (OptiBestConfig.init) satırını tamamen sildik!
        
        LOGGER.info("OptiBest Modu basariyla yuklendi! Saf FPS optimizasyonu devrede.");
    }
}
