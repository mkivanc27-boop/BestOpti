package com.mustafa.optibest.mixin;

import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class WorldTickMixin {

    private int ambientSoundTick = 0;

    @Inject(method = "tickEntities", at = @At("HEAD"), cancellable = true)
    private void throttleEntityTicking(CallbackInfo ci) {
        ambientSoundTick++;
        // Her 2 tick'te bir entity tick'i atla
        // Bu, yüzlerce entity varken CPU'yu yarı yarıya rahatlatır
        if (ambientSoundTick % 2 != 0) {
            ci.cancel();
        }
    }
}
