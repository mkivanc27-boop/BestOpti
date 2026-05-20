package com.mustafa.optibest.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.world.chunk.light.LightingProvider;

@Mixin(LightingProvider.class) 
public class OptiBestLightMixin {

    @Inject(method = "doLightUpdates", at = @At("HEAD"), cancellable = true)
    private void skipLaggyLightUpdates(CallbackInfoReturnable<Integer> cir) {
        // Işık güncellemelerini iptal edip "0" döndürüyoruz (0 = hiç güncelleme yapılmadı)
        // Bu sayede oyun kasmayacak ve FPS tavan yapacak.
        cir.setReturnValue(0);
    }
}
