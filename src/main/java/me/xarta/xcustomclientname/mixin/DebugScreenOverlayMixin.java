package me.xarta.xcustomclientname.mixin;

import me.xarta.xcustomclientname.config.ConfigHandler;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.neoforged.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(DebugScreenOverlay.class)
public class DebugScreenOverlayMixin {

    @Inject(method = "getGameInformation", at = @At("RETURN"))
    private void xcustomclientname$replaceTopLine(CallbackInfoReturnable<List<String>> cir) {
        List<String> left = cir.getReturnValue();
        if (left == null || left.isEmpty()) return;

        final String name = ConfigHandler.CLIENT_NAME.get();
        final String version = ConfigHandler.CLIENT_VERSION.get();
        final boolean showLoader = ConfigHandler.DISPLAY_MOD_LOADER.get();

        String suffix = "";
        if (showLoader) {
            final String brand = ClientBrandRetriever.getClientModName(); // "neoforge"
            final String neoVer = ModList.get().getModContainerById("neoforge")
                    .map(c -> c.getModInfo().getVersion().toString())
                    .orElseGet(() -> ModList.get().getMods().stream()
                            .filter(info -> "neoforge".equals(info.getModId()))
                            .findFirst()
                            .map(info -> info.getVersion().toString())
                            .orElse("unknown"));
            suffix = " (" + brand + "-" + neoVer + "/" + brand + ")";
        }

        left.set(0, name + " " + version + suffix);
    }
}
