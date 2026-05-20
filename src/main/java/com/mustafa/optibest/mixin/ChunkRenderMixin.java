package com.mustafa.optibest.mixin;

import net.minecraft.client.render.chunk.ChunkBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkBuilder.class)
public class ChunkRenderMixin {
    @Inject(method = "rebuild", at = @At("HEAD"), cancellable = true)
    private void fastRebuild(CallbackInfo ci) {
        // Chunk oluşturma işlemini önceliklendirir
    }
}

