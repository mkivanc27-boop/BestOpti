package com.mustafa.optibest.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class ChunkRenderMixin {

    @Inject(method = "render", at = @At("HEAD"))
    private void optimizeRenderCall(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.world == null) return;

        // Render distance'ı dinamik olarak ayarla
        // Düşük FPS'te render mesafesini azalt
        int fps = client.getCurrentFps();
        int currentDistance = client.options.getViewDistance().getValue();

        if (fps < 20 && currentDistance > 6) {
            client.options.getViewDistance().setValue(6);
        } else if (fps < 30 && currentDistance > 8) {
            client.options.getViewDistance().setValue(8);
        }
    }
}
