package com.mustafa.optibest.mixin;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class InputOptimizationMixin {

    @Inject(method = "onKey", at = @At("HEAD"))
    private void optimizeKeyInput(long window, int key, int scancode,
                                   int action, int modifiers, CallbackInfo ci) {
        // Key event'leri zaten event-driven çalışır (polling yok)
        // Bu hook ileride key debounce veya macro engelleme için kullanılabilir
        // Şu an performans açısından dokunmaya gerek yok
    }
}
