package com.mustafa.optibest.mixin;

import net.minecraft.client.render.chunk.ChunkBuilder;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkBuilder.class)
public class ChunkCullMixin {
    @Inject(method = "isEmpty", at = @At("HEAD"), cancellable = true)
    private void skipEmptyChunks(CallbackInfoReturnable<Boolean> cir) {
        // Boş chunk build'leri hızlı döndür
        cir.setReturnValue(true);
    }
}
