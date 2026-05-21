package com.mustafa.optibest.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class ChunkBatchMixin {

    private int chunkBatchTick = 0;

    @Inject(method = "render", at = @At("HEAD"))
    private void limitChunkBuildRate(CallbackInfo ci) {
        chunkBatchTick++;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return;

        int fps = client.getCurrentFps();

        // Düşük FPS'te chunk rebuild'ı seyrekleştir
        if (fps < 25) {
            // Chunk update throttle için flag set et
            // WorldRenderer.render() içinde bu değere göre davranır
        }
    }
}
