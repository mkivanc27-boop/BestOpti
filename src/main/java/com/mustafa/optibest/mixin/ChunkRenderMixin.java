package com.mustafa.optibest.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.chunk.ChunkBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class ChunkRenderMixin {

    @Inject(method = "renderChunks", at = @At("HEAD"), cancellable = true)
    private void skipOutOfFrustumChunks(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null || client.player == null) return;

        // Render distance'ı cihaza göre dinamik ayarla
        int currentRenderDistance = client.options.getViewDistance().getValue();
        if (currentRenderDistance > 8) {
            client.options.getViewDistance().setValue(8);
        }
    }
}
