package com.mustafa.optibest.mixin;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldRenderer.class)
public class RenderOptimizedMixin {
    // Gereksiz parçacık render'larını azaltmak için bir örnek
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleManager;renderParticles(Lnet/minecraft/client/render/Camera;F)V"))
    private void stopLaggyParticles(Object manager, Object camera, float tickDelta) {
        // İstersen burada parçacıkları tamamen iptal edebilirsin
    }
}

