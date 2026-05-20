package com.mustafa.optibest.mixin;

import net.minecraft.client.render.chunk.ChunkRendererRegionBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkRendererRegionBuilder.class)
public class ChunkBatchMixin {
    @Inject(method = "build", at = @At("HEAD"), cancellable = true)
    private void optimizeBatch(CallbackInfoReturnable<Object> cir) {
        // Chunk oluşturma işlemini seri değil, daha hafif bir kuyrukla yap
    }
}

