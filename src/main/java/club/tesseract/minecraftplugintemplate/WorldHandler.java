package club.tesseract.minecraftplugintemplate;

import org.jetbrains.annotations.NotNull;
import org.mvplugins.multiverse.core.MultiverseCoreApi;
import org.mvplugins.multiverse.core.world.MultiverseWorld;

import java.util.List;

public final class WorldHandler {

    private final @NotNull MultiWorldGUI plugin;
    private final @NotNull MultiverseCoreApi multiverseCoreApi;


    public WorldHandler(@NotNull MultiWorldGUI plugin, @NotNull MultiverseCoreApi multiverseCoreApi) {
        this.plugin = plugin;
        this.multiverseCoreApi = multiverseCoreApi;
    }


    public @NotNull List<@NotNull String> getActiveWorlds() {
        return multiverseCoreApi.getWorldManager().getLoadedWorlds().stream().map(MultiverseWorld::getName).toList();
    }

    public @NotNull List<@NotNull String> getWorlds() {
        return multiverseCoreApi.getWorldManager().getPotentialWorlds();
    }


}
