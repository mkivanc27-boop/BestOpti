package com.mustafa.optibest;

import eu.midnightdust.lib.config.MidnightConfig;

public class OptiBestConfig extends MidnightConfig {
    @Entry(category = "performans")
    public static String fpsModu = "Balanced"; // Low, Balanced, Extreme
    
    @Entry(category = "performans")
    public static boolean agresifCulling = true;
}

