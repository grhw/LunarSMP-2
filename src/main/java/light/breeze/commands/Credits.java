package light.breeze.commands;

import light.breeze.lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Credits implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(lang.ascii_art + "\nPlugin created by Light Breeze (guhw/gust)");
        return true;
    }
}
