package com.mustafa.optibest.mixin;

import net.minecraft.client.render.BufferBuilder;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BufferBuilder.class)
public class BufferBuilderMixin {
    // 1.21'de BufferBuilder API tamamen değişti
    // Güvenli hook noktası bulunamadı, ileride eklenecek
}
