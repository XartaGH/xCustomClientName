package me.xarta.xcustomclientname.mixin.compat.betterf3;

import me.xarta.xcustomclientname.util.Labels;
import me.cominixo.betterf3.modules.MinecraftModule;
import me.cominixo.betterf3.utils.DebugLine;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.util.List;

@Mixin(value = MinecraftModule.class, remap = false)
public abstract class MinecraftModuleMixin {

    @Inject(method = "update", at = @At("HEAD"), cancellable = true)
    private void xcustomclientname$overrideBetterF3Title(Minecraft client, CallbackInfo ci) {
        try {
            Class<?> c = this.getClass();
            Field linesField = null;
            while (c != null) {
                try {
                    linesField = c.getDeclaredField("lines");
                    break;
                } catch (NoSuchFieldException e) {
                    c = c.getSuperclass();
                }
            }
            if (linesField == null) {
                ci.cancel();
                return;
            }

            linesField.setAccessible(true);
            Object rawLines = linesField.get(this);
            if (rawLines instanceof List<?> list && !list.isEmpty()) {
                @SuppressWarnings("unchecked")
                DebugLine first = ((List<DebugLine>) list).getFirst();
                first.value(Labels.buildTopLine());
            }
        } catch (ReflectiveOperationException ignored) {
        }
        ci.cancel();
    }
}