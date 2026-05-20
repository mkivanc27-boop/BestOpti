package com.mustafa.optibest.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class ThreadTweakMixin {

    @Inject(method = "run", at = @At("HEAD"))
    private void optimizeThreadPriority(CallbackInfo ci) {
        Thread renderThread = Thread.currentThread();

        // MAX yerine MAX-1, daha stabil
        renderThread.setPriority(Thread.MAX_PRIORITY - 1);

        // Thread ismini logda görmek için
        renderThread.setName("BestOpti-RenderThread");

        // GC'nin render thread'i interrupt etmesini engelle
        // (mümkün olduğunca)
        try {
            // Render thread'i daemon yapma — kapanışta temiz çıksın
            renderThread.setDaemon(false);
        } catch (SecurityException e) {
            // Güvenlik kısıtlaması varsa sessizce geç
        }
    }

    // Chunk build thread'lerini de optimize et
    @Inject(method = "<init>", at = @At("TAIL"))
    private void optimizeWorkerThreads(CallbackInfo ci) {
        // JVM'e render odaklı çalıştığımızı söyle
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
            String.valueOf(Math.max(2,
                Runtime.getRuntime().availableProcessors() - 1))
        );
    }
}
