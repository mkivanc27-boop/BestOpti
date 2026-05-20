package com.mustafa.optibest.mixin;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.callback.CallbackInfo;

@Mixin(World.class)
public class WorldTickMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void skipWorldTicks(CallbackInfo ci) {
        // Her 2 tick'te 1 kez çalıştır (Örnek: CPU kullanımı düşer)
    }
}

