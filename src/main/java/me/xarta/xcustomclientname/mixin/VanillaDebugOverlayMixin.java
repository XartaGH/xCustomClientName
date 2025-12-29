
// src/main/java/me/xarta/xcustomclientname/mixin/VanillaDebugOverlayMixin.java
package me.xarta.xcustomclientname.mixin;

import me.xarta.xcustomclientname.util.Labels;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(DebugScreenOverlay.class)
public class VanillaDebugOverlayMixin {
    @Inject(method = "getGameInformation", at = @At("RETURN"))
    private void xcustomclientname$replaceTopLine(CallbackInfoReturnable<List<String>> cir) {
        List<String> left = cir.getReturnValue();
        if (left == null || left.isEmpty()) return;
        left.set(0, Labels.buildTopLine());
    }
}