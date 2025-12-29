package me.xarta.xcustomclientname.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ConfigHandler {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.ConfigValue<String> CLIENT_NAME;
    public static final ModConfigSpec.ConfigValue<String> CLIENT_VERSION;
    public static final ModConfigSpec.ConfigValue<Boolean> DISPLAY_MOD_LOADER;

    static {
        BUILDER.push("xCustomClientName Configuration");
        BUILDER.comment("You can change some of client's labels there.");

        CLIENT_NAME = BUILDER
                .comment("Client's name")
                .define("client-name", "Project Name");

        CLIENT_VERSION = BUILDER
                .comment("Client's version")
                .define("client-version", "1.0");

        DISPLAY_MOD_LOADER = BUILDER
                .comment("Whether mod loader should be displayed near client's name and version")
                .define("display-mod-loader", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
