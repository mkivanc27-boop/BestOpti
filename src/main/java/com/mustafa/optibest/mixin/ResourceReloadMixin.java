package com.mustafa.optibest.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class ResourceReloadMixin {

    @Inject(method = "reloadResources()V", at = @At("HEAD"))
    private void beforeReload(CallbackInfo ci) {
        // Reload öncesi GC çalıştır — bellek temizle
        System.gc();
    }

    @Inject(method = "reloadResources()V", at = @At("TAIL"))
    private void afterReload(CallbackInfo ci) {
        // Reload sonrası tekrar GC — eski texture/model nesneleri temizle
        System.gc();
    }
}
