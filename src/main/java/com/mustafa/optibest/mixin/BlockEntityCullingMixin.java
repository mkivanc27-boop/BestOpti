package com.mustafa.optibest.mixin;

import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;

// Artık bir interface değil, somut bir sınıf hedefliyoruz
@Mixin(BlockEntityRenderDispatcher.class)
public class BlockEntityCullingMixin {
    // Render işlemini burada culling (görünmeyenleri eleme) mantığıyla bağlayacağız.
}
