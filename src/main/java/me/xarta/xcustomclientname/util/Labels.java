package me.xarta.xcustomclientname.util;

import me.xarta.xcustomclientname.config.ConfigHandler;
import me.xarta.xcustomclientname.config.SafeSupplier;
import net.minecraft.client.ClientBrandRetriever;
import net.neoforged.fml.ModList;

public final class Labels {
    private Labels() {}

    private static <T> T safeGet(SafeSupplier<T> supplier, T def) {
        try { return supplier.get(); } catch (RuntimeException e) { return def; }
    }

    public static String buildTopLine() {
        String name    = safeGet(ConfigHandler.CLIENT_NAME::get, "Project Name");
        String version = safeGet(ConfigHandler.CLIENT_VERSION::get, "1.0");
        boolean show   = safeGet(ConfigHandler.DISPLAY_MOD_LOADER::get, false);
        if (!show) return name + " " + version;

        String brand  = ClientBrandRetriever.getClientModName();
        String neoVer = ModList.get().getModContainerById("neoforge")
                .map(c -> c.getModInfo().getVersion().toString())
                .orElse("unknown");

        return name + " " + version + " (" + brand + "-" + neoVer + "/" + brand + ")";
    }
}