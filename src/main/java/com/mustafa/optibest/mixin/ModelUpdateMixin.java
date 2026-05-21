package com.mustafa.optibest.mixin;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class ModelUpdateMixin {

    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private <E extends Entity> void skipFarModelUpdates(
            E entity, net.minecraft.client.frustum.Frustum frustum,
            double x, double y, double z,
            CallbackInfoReturnable<Boolean> cir) {

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        double distSq = entity.squaredDistanceTo(
            client.player.getX(),
            client.player.getY(),
            client.player.getZ()
        );

        // 96 bloktan uzaktaki entity modelini render etme
        if (distSq > 96 * 96) {
            cir.setReturnValue(false);
        }
    }
}
