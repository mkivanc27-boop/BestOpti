package com.mustafa.optibest.mixin;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class NametagSkipMixin {
    @Inject(method = "hasLabel", at = @At("HEAD"), cancellable = true)
    private <E extends Entity> void skipFarNametag(E entity, CallbackInfoReturnable<Boolean> cir) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        double distSq = entity.squaredDistanceTo(
            client.player.getX(), client.player.getY(), client.player.getZ());
        if (distSq > 20 * 20) cir.setReturnValue(false);
    }
}
