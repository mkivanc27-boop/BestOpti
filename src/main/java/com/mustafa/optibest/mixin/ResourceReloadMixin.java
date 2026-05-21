package com.mustafa.optibest.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class ResourceReloadMixin {

    @Inject(method = "render", at = @At("HEAD"))
    private void gcOnLowMemory(CallbackInfo ci) {
        // Her frame'de memory kontrol et
        // Bellek %90 doluysa GC tetikle
        Runtime rt = Runtime.getRuntime();
        long used = rt.totalMemory() - rt.freeMemory();
        long max = rt.maxMemory();

        if ((double) used / max > 0.90) {
            System.gc();
        }
    }
}
