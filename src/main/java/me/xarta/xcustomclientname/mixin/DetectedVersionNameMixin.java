package me.xarta.xcustomclientname.mixin;

import me.xarta.xcustomclientname.util.Labels;
import net.minecraft.DetectedVersion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DetectedVersion.class)
public abstract class DetectedVersionNameMixin {
    @Inject(method = "getName", at = @At("HEAD"), cancellable = true)
    private void xcustomclientname$overrideVersionName(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue(Labels.buildTopLine());
    }
}