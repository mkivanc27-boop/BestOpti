package com.mustafa.optibest.mixin;

import net.minecraft.client.render.entity.ItemFrameEntityRenderer;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrameEntityRenderer.class)
public class ItemFrameSkipMixin {
    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private void skipFarItemFrames(ItemFrameEntity entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        double distSq = entity.squaredDistanceTo(client.player.getX(), client.player.getY(), client.player.getZ());
        if (distSq > 24 * 24) cir.setReturnValue(false);
    }
}
