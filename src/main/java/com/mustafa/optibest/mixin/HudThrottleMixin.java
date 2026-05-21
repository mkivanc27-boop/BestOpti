package com.mustafa.optibest.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class HudThrottleMixin {
    private int hudTick = 0;

    @Inject(method = "renderStatusEffectOverlay", at = @At("HEAD"), cancellable = true)
    private void throttleStatusEffects(CallbackInfo ci) {
        hudTick++;
        if (hudTick % 3 != 0) ci.cancel();
    }
}
