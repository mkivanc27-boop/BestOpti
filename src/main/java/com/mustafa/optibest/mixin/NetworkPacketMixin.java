package com.mustafa.optibest.mixin;

import net.minecraft.network.ClientConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class NetworkPacketMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void blockIdlePackets(CallbackInfo ci) {
        // İletişim paketlerini seyrelt
        if (Math.random() > 0.4) ci.cancel();
    }
}

