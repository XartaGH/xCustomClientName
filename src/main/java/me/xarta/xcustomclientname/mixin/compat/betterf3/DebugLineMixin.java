package me.xarta.xcustomclientname.mixin.compat.betterf3;

import me.xarta.xcustomclientname.util.Labels;
import me.cominixo.betterf3.utils.DebugLine;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = DebugLine.class, remap = false)
public abstract class DebugLineMixin {
    @Inject(method = "toText", at = @At("HEAD"), cancellable = true)
    private void xcustomclientname$onlyValueForMinecraftLine(TextColor nameColor, TextColor valueColor,
                                                             CallbackInfoReturnable<Component> cir) {
        if (((DebugLine) (Object) this).id().equals("minecraft")) {
            Component c = Component.literal(Labels.buildTopLine())
                    .withStyle(s -> s.withColor(valueColor));
            cir.setReturnValue(c);
        }
    }
}