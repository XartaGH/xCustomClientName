package me.xarta.xcustomclientname;

import com.mojang.logging.LogUtils;
import me.xarta.xcustomclientname.config.ConfigHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(XCustomClientName.MODID) // Declare this class as mod's main class
public class XCustomClientName {

    public static final String MODID = "xcustomclientname"; // Define modification's ID
    public static final Logger LOGGER = LogUtils.getLogger(); // Create logger


    public XCustomClientName(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("xCustomClientName is initializing..."); // Print initialization message

        // Register config for the mod
        modContainer.registerConfig(
                ModConfig.Type.CLIENT,
                ConfigHandler.SPEC,
                "xcustomclientname.toml"
        );

        LOGGER.info("xCustomClientName is on."); // Print success message
    }

}