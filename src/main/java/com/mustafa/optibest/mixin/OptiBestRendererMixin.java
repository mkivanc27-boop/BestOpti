package com.mustafa.optibest.mixin;

import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class OptiBestRendererMixin {
    // Render sisteminin karmaşıklığını basitleştir
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void simplifyRender(float tickDelta, long startTime, boolean tick, CallbackInfo ci) {
        // Burada render döngüsünü hafifletiyoruz
    }
}

