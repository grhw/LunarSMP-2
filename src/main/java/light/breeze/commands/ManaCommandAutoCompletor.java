package light.breeze.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ManaCommandAutoCompletor implements TabCompleter {
    public List<String> onTabComplete( CommandSender sender, Command command, String alias, String[] args ) {
        List<String> ac = new ArrayList<>();
        if (args.length > 1) {
            return new ArrayList<>();
        }
        ac.add("sacrifice");
        return ac;
    }
}