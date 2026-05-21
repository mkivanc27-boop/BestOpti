package com.mustafa.optibest.mixin;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class OptiBestEntityRendererMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private <E extends Entity> void skipFarEntityRender(
            E entity, double x, double y, double z,
            float yaw, net.minecraft.client.util.math.MatrixStack matrices,
            net.minecraft.client.render.VertexConsumerProvider vertexConsumers,
            int light, CallbackInfo ci) {

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        // Kendi oyuncumuzu asla atlama
        if (entity == client.player) return;

        double distSq = entity.squaredDistanceTo(
            client.player.getX(),
            client.player.getY(),
            client.player.getZ()
        );

        // 80 bloktan uzak entity render etme
        if (distSq > 80 * 80) {
            ci.cancel();
        }
    }
}
