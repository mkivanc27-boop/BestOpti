package com.mustafa.optibest.mixin;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntityRenderDispatcher.class)
public class BlockEntityCullingMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private <E extends BlockEntity> void cullBlockEntities(
            E blockEntity, float tickDelta,
            MatrixStack matrices, VertexConsumerProvider vertexConsumers,
            CallbackInfo ci) {

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.gameRenderer == null) return;

        // Chunk yüklü değilse render etme
        if (blockEntity.getWorld() == null ||
            !blockEntity.getWorld().isChunkLoaded(blockEntity.getPos())) {
            ci.cancel();
            return;
        }

        // Oyuncudan 64 bloktan uzaktaysa render etme
        double distSq = blockEntity.getPos().getSquaredDistance(
            client.player.getBlockPos()
        );
        if (distSq > 64 * 64) {
            ci.cancel();
        }
    }
}
