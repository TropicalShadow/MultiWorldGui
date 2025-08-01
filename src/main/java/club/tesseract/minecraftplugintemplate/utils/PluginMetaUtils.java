package club.tesseract.minecraftplugintemplate.utils;

import club.tesseract.minecraftplugintemplate.MultiWorldGUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.jetbrains.annotations.Nullable;

/**
 * Utility class for plugin metadata.
 */
public final class PluginMetaUtils {

    @Nullable
    private static PluginMeta pluginMeta = null;


    /**
     * Get the plugin metadata, if available.
     * If the metadata is not available, an empty metadata object is returned.
     *
     * @return The plugin metadata.
     */
    public static PluginMeta getMeta() {
        if (pluginMeta != null) {
            return pluginMeta;
        }

        pluginMeta = PluginMeta.empty();



        try (InputStream pluginMetaIS = MultiWorldGUI.getPlugin()
                .getResource("plugin-meta.properties")) {
            Map<String, Object> pluginMetaData = new HashMap<>();
            if (pluginMetaIS == null) {
                MultiWorldGUI.getPlugin()
                        .getLogger().warning("Plugin meta file not found.");
                return pluginMeta;
            }
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(pluginMetaIS, Charset.defaultCharset())
            )) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("=");
                    if (parts.length < 2) {
                        continue;
                    }
                    String key = parts[0];
                    String value = String.join("=", parts).substring(key.length() + 1);

                    pluginMetaData.put(key, value);
                }
            }

            pluginMeta = PluginMeta.fromProperties(pluginMetaData);
        } catch (IOException e) {
            MultiWorldGUI.getPlugin()
                    .getLogger().warning("Failed to read plugin meta file.");
            MultiWorldGUI.getPlugin()
                            .getLogger().log(Level.WARNING,
                            e, () -> "Failed to read plugin meta file.");
        }

        return pluginMeta;
    }

    /**
     * Represents the metadata of the plugin.
     */
    public record PluginMeta(
            String pluginName,
            String pluginVersion,
            @Nullable Integer bstatsPluginId
    ) {

        /**
         * Create a PluginMeta object from a map of properties.
         *
         * @param properties Map of key value pairs.
         * @return The PluginMeta object.
         */
        public static PluginMeta fromProperties(Map<String, Object> properties) {
            Integer bstatsPluginId = Integer.parseInt(
                    (String) properties.getOrDefault("bstats_id", -1)
            );
            if (bstatsPluginId == -1) {
                bstatsPluginId = null;
            }
            String pluginVersion = (String) properties.getOrDefault("plugin_version",
                    MultiWorldGUI.getPlugin().getPluginMeta().getVersion());
            String pluginName = (String) properties.getOrDefault("plugin_name",
                    MultiWorldGUI.getPlugin().getPluginMeta().getName());

            return new PluginMeta(
                    pluginName,
                    pluginVersion,
                    bstatsPluginId
            );

        }

        /**
         * Create a default PluginMeta with data filled with default or null.
         *
         * @return The PluginMeta object.
         */
        public static PluginMeta empty() {
            return new PluginMeta(
                    MultiWorldGUI.getPlugin().getPluginMeta().getName(),
                    MultiWorldGUI.getPlugin().getPluginMeta().getVersion(),
                    null);
        }
    }
}


