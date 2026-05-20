package com.mustafa.optibest.mixin;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class WorldTickMixin {

    private int tickCounter = 0;

    @Inject(method = "tick", at = @At("HEAD"))
    private void trackTicks(CallbackInfo ci) {
        tickCounter++;
    }

    // Ambient ses güncellemesini her 5 tick'te bir yap
    // (normalde her tick çalışır, CPU yutar)
    @Inject(
        method = "tickAmbientSounds",
        at = @At("HEAD"),
        cancellable = true
    )
    private void throttleAmbientSounds(CallbackInfo ci) {
        if (tickCounter % 5 != 0) {
            ci.cancel(); // Bu tick'te ambient sesi atla
        }
    }

    // Random block tick sayısını azalt
    // (ot büyümesi, kar erimesi gibi şeyler)
    @Inject(
        method = "randomBlockDisplayTick",
        at = @At("HEAD"),
        cancellable = true
    )
    private void throttleRandomTicks(CallbackInfo ci) {
        if (tickCounter % 2 != 0) {
            ci.cancel(); // Her 2 tick'te bir random block tick
        }
    }
}
