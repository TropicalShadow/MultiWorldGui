package club.tesseract.minecraftplugintemplate.commands;

import club.tesseract.minecraftplugintemplate.MultiWorldGUI;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandIssuer;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Subcommand;

/**
 * An example command.
 */
@CommandAlias("warps")
public class ExampleCommand extends BaseCommand {

    @Dependency
    private MultiWorldGUI plugin;


    @Subcommand("default")
    @Default
    public void onDefault(CommandIssuer sender) {
        sender.sendMessage("Hello, Minecraft!");
    }

}
