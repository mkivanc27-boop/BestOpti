package com.mustafa.optibest.mixin;

import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightmapTextureManager.class)
public class OptiBestLightMixin {

    private int lightUpdateTick = 0;
    private boolean lightDirty = true;

    @Inject(method = "update", at = @At("HEAD"), cancellable = true)
    private void throttleLightmapUpdate(float delta, CallbackInfo ci) {
        lightUpdateTick++;

        // FPS düşükse lightmap'i daha seyrek güncelle
        MinecraftClient client = MinecraftClient.getInstance();
        int fps = client != null ? client.getCurrentFps() : 60;

        if (fps < 30) {
            // Düşük FPS: her 4 tick'te bir güncelle
            if (lightUpdateTick % 4 != 0) {
                ci.cancel();
                return;
            }
        } else if (fps < 60) {
            // Orta FPS: her 2 tick'te bir
            if (lightUpdateTick % 2 != 0) {
                ci.cancel();
                return;
            }
        }
        // Yüksek FPS: her tick normal çalış
    }
}
