package com.mustafa.optibest.mixin;

import net.minecraft.client.render.chunk.ChunkRendererRegion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkRendererRegion.class)
public class OptiBestCullingMixin {

    @Inject(method = "shouldShow", at = @At("HEAD"), cancellable = true)
    private void optimizeChunkRendering(CallbackInfoReturnable<Boolean> cir) {
        // Şu an boş, sadece çalışıp çalışmadığını test ediyoruz
    }
}
