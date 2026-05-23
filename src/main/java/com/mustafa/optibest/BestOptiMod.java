package com.mustafa.optibest;

import net.fabricmc.api.ClientModInitializer;

public class BestOptiMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BestOptiConfig.load();
        System.out.println("[BestOpti] Yüklendi! Config: " +
            "Render=" + BestOptiConfig.get().renderDistance +
            " Entity=" + BestOptiConfig.get().entityDistance);
    }
}
