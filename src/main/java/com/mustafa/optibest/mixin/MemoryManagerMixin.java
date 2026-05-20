package com.mustafa.optibest.mixin;

import net.minecraft.util.SystemDetails;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SystemDetails.class)
public class MemoryManagerMixin {
    // String, String yapısına çevirdik, hata buradan kaynaklanıyordu.
    @Inject(method = "addSection", at = @At("HEAD"), cancellable = true)
    private void skipSystemLogs(String name, String value, CallbackInfo ci) {
        ci.cancel();
    }
}
