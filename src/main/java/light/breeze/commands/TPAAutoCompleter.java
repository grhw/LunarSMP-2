package light.breeze.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TPAAutoCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete( CommandSender sender, Command command, String alias, String[] arguments ) {
        if ( arguments.length == 1 ) {
            List<String> playerList = new ArrayList<>();
            for ( OfflinePlayer player : Bukkit.getServer().getOfflinePlayers() ) {
                if (player.getName().contains( arguments[0] )) {
                    playerList.add( player.getName() );
                }
            }
            return playerList;
        }
        return new ArrayList<>();
    }
}
