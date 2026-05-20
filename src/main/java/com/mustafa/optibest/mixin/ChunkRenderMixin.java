package com.mustafa.optibest.mixin;

import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.chunk.ChunkBuilder;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkBuilder.BuiltChunk.class)
public class ChunkRenderMixin {

    @Inject(
        method = "isVisible",
        at = @At("HEAD"),
        cancellable = true
    )
    private void frustumCullChunk(Frustum frustum, CallbackInfoReturnable<Boolean> cir) {
        ChunkBuilder.BuiltChunk self = (ChunkBuilder.BuiltChunk)(Object)this;

        // Chunk'ın dünya koordinatlarındaki bounding box'ını al
        Box boundingBox = new Box(
            self.getOrigin().getX(),
            self.getOrigin().getY(),
            self.getOrigin().getZ(),
            self.getOrigin().getX() + 16,
            self.getOrigin().getY() + 16,
            self.getOrigin().getZ() + 16
        );

        // Frustum dışındaysa render etme
        if (!frustum.isVisible(boundingBox)) {
            cir.setReturnValue(false); // Görünmüyor, atla
            return;
        }

        // Görünüyorsa normal devam et
        cir.setReturnValue(true);
    }
}
