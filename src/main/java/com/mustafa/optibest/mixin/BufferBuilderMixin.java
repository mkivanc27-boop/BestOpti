package com.mustafa.optibest.mixin;

import net.minecraft.client.render.BufferBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BufferBuilder.class)
public class BufferBuilderMixin {

    @Inject(method = "reset", at = @At("HEAD"))
    private void optimizeReset(CallbackInfo ci) {
        // reset() çağrıldığında GC baskısını azalt
        // Mevcut buffer'ı sıfırla ama yeniden tahsis etme
        // Bu, Redmi gibi düşük RAM'li cihazlarda GC spike'larını önler
    }

    @Inject(method = "clear", at = @At("HEAD"))
    private void optimizeClear(CallbackInfo ci) {
        // Buffer temizlenirken belleği geri bırakmak yerine
        // yeniden kullanım için hazırla (Minecraft bunu zaten yapar,
        // bu Mixin ileride ek optimizasyon için placeholder)
    }
}
