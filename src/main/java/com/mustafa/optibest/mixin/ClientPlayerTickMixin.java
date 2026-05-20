package com.mustafa.optibest.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerTickMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void fastTick(CallbackInfo ci) {
        if (((ClientPlayerEntity)(Object)this).age % 2 != 0) ci.cancel();
    }
}

