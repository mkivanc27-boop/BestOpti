package com.mustafa.optibest.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityCollisionMixin {

    @Inject(method = "isCollidable", at = @At("HEAD"), cancellable = true)
    private void skipFarEntityCollision(CallbackInfoReturnable<Boolean> cir) {
        Entity self = (Entity)(Object)this;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        // 48 bloktan uzaktaki entity'nin collision'ını kapat
        double distanceSq = self.squaredDistanceTo(
            client.player.getX(),
            client.player.getY(),
            client.player.getZ()
        );

        if (distanceSq > 48 * 48) {
            cir.setReturnValue(false); // Çok uzak, collision hesaplama
        }
    }
}
