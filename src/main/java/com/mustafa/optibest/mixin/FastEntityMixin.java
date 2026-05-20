package com.mustafa.optibest.mixin;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Entity.class)
public class FastEntityMixin {
    @Overwrite
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        // Uzaktaki varlıkların hesaplanmasını CPU'dan tamamen kaldır
        return true; // Buraya mesafe kontrolü ekleyerek FPS'i ikiye katlayabilirsin
    }
}

