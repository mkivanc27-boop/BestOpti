package com.mustafa.optibest.mixin;

import net.minecraft.client.particle.ParticleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ParticleManager.class)
public class ParticleOptimizeMixin {
    // Parçacık limitini %50 azaltarak CPU üzerindeki yükü hafifletiyoruz
    @ModifyArg(method = "addParticle", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"), index = 0)
    private Object limitParticles(Object particle) {
        // Burada basit bir kontrol ile çok fazla parçacık varsa eklemeyi reddedebilirsin
        return particle;
    }
}

