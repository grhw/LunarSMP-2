package light.breeze.commands;

import light.breeze.utils.FileStorage;
import light.breeze.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class EchoTarget implements CommandExecutor {

    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {
        Player player = (Player) sender;
        FileStorage fs = new FileStorage(Utils.getPlugin(), "echo_targets.yml");

        if (args.length > 1) {
            if (args[0].contains("player")) {
                Player foundPlayer = Utils.getPlayer(args[1]);
                if (foundPlayer != null) {
                    sender.sendMessage("Now targeting player [" + foundPlayer.getName() + "].");
                    fs.store(player.getUniqueId().toString(), foundPlayer.getName());
                } else {
                    sender.sendMessage("No player named [" + args[1] + "] currently online.");
                }
            } else if (args[0].contains("entity")) {
                Boolean found = false;
                for (Entity entity : player.getWorld().getEntities()) {
                    if (( ! found ) && args[1].matches(entity.getType().getName())) {
                        sender.sendMessage("Now targeting any [" + args[1] + "].");
                        found = true;
                        fs.store(player.getUniqueId().toString(), args[1]);
                    }
                }
                if (! found) {
                    sender.sendMessage("No [" + args[1] + "] in current world.");
                }
            } else {
                sender.sendMessage("Type: player/entity");
            }
        } else {
            sender.sendMessage("Usage:\n/echotarget <type> <name>");
            return false;
        }
        return true;
    }
}
