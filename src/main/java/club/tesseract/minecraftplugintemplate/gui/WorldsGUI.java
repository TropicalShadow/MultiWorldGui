package club.tesseract.minecraftplugintemplate.gui;

import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class WorldsGUI extends ChestGui {


    public WorldsGUI() {
        super(1, ComponentHolder.of(Component.text("Warps")));
    }



}
