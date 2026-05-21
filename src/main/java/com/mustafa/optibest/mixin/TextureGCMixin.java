package com.mustafa.optibest.mixin;

import net.minecraft.client.texture.TextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TextureManager.class)
public class TextureGCMixin {
    private int gcTick = 0;

    @Inject(method = "tick", at = @At("HEAD"))
    private void periodicGC(CallbackInfo ci) {
        gcTick++;
        if (gcTick % 6000 == 0) { // Her 5 dakikada bir
            System.gc();
        }
    }
}
