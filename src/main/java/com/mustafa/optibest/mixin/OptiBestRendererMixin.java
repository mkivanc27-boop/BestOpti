package com.mustafa.optibest.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.render.WorldRenderer;

@Mixin(WorldRenderer.class) // Kendi hedef sınıfın neyse onunla değiştir
public class OptiBestRendererMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void simplifyRender(float tickDelta, long startTime, boolean tick, CallbackInfo ci) {
        // Render optimizasyon kodların burada olacak
    }
}
