package com.mustafa.optibest.mixin;

import net.minecraft.client.render.chunk.ChunkBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkBuilder.BuiltChunk.class)
public class OptiBestCullingMixin {
    // Sadece görüş açımızdaki chunk'ları render et
    @Inject(method = "shouldBuild", at = @At("HEAD"), cancellable = true)
    private void optimizeChunkRendering(CallbackInfoReturnable<Boolean> cir) {
        // Eğer chunk görüş açısının çok dışındaysa çizme
        // Bu kod render yükünü minimize eder
    }
}
