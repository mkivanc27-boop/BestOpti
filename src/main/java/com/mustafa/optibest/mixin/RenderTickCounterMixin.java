package com.mustafa.optibest.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class ChunkRenderMixin {

    @Inject(method = "render", at = @At("HEAD"))
    private void optimizeRender(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.world == null) return;

        int fps = client.getCurrentFps();
        int dist = client.options.getViewDistance().getValue();

        if (fps < 20 && dist > 6) {
            client.options.getViewDistance().setValue(6);
        } else if (fps < 30 && dist > 8) {
            client.options.getViewDistance().setValue(8);
        }
    }
}
