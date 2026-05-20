package com.mustafa.optibest.mixin;

import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntityRenderDispatcher.class)
public class BlockEntityRenderMixin {
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void stopInvisibleRender(CallbackInfo ci) {
        // Gözle görülmeyen blokları render sırasından çıkart
    }
}

