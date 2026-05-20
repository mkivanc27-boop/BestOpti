package com.mustafa.optibest.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.client.render.chunk.ChunkRendererRegion;

@Mixin(ChunkRendererRegion.class)
public class OptiBestCullingMixin {

    @Inject(method = "shouldShow", at = @At("HEAD"), cancellable = true)
    private void optimizeChunkRendering(CallbackInfoReturnable<Boolean> cir) {
        // Culling (gereksiz blokları gizleme) kodların burada olacak
    }
}
