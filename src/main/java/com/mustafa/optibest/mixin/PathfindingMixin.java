package com.mustafa.optibest.mixin;

import net.minecraft.entity.mob.MobEntity;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public class PathfindingMixin {

    @Inject(method = "tickMovement", at = @At("HEAD"), cancellable = true)
    private void skipFarMobPathfinding(CallbackInfo ci) {
        MobEntity self = (MobEntity)(Object)this;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        double distSq = self.squaredDistanceTo(
            client.player.getX(),
            client.player.getY(),
            client.player.getZ()
        );

        // 48 bloktan uzaktaki mob'ların hareket hesabını atla
        if (distSq > 48 * 48 && self.age % 4 != 0) {
            ci.cancel();
        }
    }
            }
