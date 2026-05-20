package com.mustafa.optibest.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityLogicMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void optiBest_reduceEntityTick(CallbackInfo ci) {
        Entity self = (Entity)(Object)this;

        // Oyuncuya asla dokunma
        if (self instanceof PlayerEntity) return;

        // Dünyada oyuncu yoksa çalışma
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        // Oyuncuya olan mesafeyi hesapla
        double distanceSq = self.squaredDistanceTo(
            client.player.getX(),
            client.player.getY(),
            client.player.getZ()
        );

        // 32 blok uzaktaysa → her 3 tick'te bir
        if (distanceSq > 32 * 32 && self.age % 3 != 0) {
            ci.cancel();
            return;
        }

        // 64 blok uzaktaysa → her 5 tick'te bir
        if (distanceSq > 64 * 64 && self.age % 5 != 0) {
            ci.cancel();
        }
    }
}
