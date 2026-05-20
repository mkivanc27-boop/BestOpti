package com.mustafa.optibest.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.world.ClientWorld;

@Mixin(ClientWorld.class) // Kendi hedef sınıfın neyse onunla değiştir
public class WorldTickMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void skipWorldTicks(CallbackInfo ci) {
        // Tick atlama kodların burada olacak
    }
}
