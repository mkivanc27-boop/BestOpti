package com.mustafa.optibest.mixin;

import com.mustafa.optibest.BestOptiConfig;
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
        if (self instanceof PlayerEntity) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        // Config'den entity mesafesini al
        double maxDist = BestOptiConfig.get().entityDistance;

        double distanceSq = self.squaredDistanceTo(
            client.player.getX(),
            client.player.getY(),
            client.player.getZ()
        );

        if (distanceSq > maxDist * maxDist && self.age % 3 != 0) {
            ci.cancel();
            return;
        }

        if (distanceSq > (maxDist * 1.5) * (maxDist * 1.5) && self.age % 5 != 0) {
            ci.cancel();
        }
    }
}
