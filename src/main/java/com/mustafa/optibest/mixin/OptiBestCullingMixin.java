@Inject(method = "shouldBuild", at = @At("HEAD"), cancellable = true)
private void optimizeChunkRendering(CallbackInfoReturnable<Boolean> cir) {
    if (OptiBestConfig.fpsModu.equals("Extreme")) {
        // En yüksek performans: Çok agresif culling
        // cir.setReturnValue(false); ...
    }
}
