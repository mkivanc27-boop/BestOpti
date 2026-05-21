package com.mustafa.optibest.mixin;

import net.minecraft.client.render.chunk.ChunkBuilder;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkBuilder.class)
public class ChunkBatchMixin {

    @Inject(method = "getCompletedChunkCount", at = @At("HEAD"), cancellable = true)
    private void limitChunkBuildBatch(CallbackInfoReturnable<Integer> cir) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return;

        // Düşük FPS'te chunk build batch'ini küçült
        // Bu, frame spike'larını önler
        int fps = client.getCurrentFps();
        if (fps < 30) {
            // FPS düşükse aynı anda max 2 chunk build
            cir.setReturnValue(Math.min(cir.getReturnValue(), 2));
        } else if (fps < 60) {
            // Orta FPS'te max 4 chunk build
            cir.setReturnValue(Math.min(cir.getReturnValue(), 4));
        }
        // Yüksek FPS'te normal devam et
    }
}
