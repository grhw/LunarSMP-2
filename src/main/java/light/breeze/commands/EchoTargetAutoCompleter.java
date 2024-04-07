package light.breeze.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EchoTargetAutoCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete( CommandSender sender, Command command, String alias, String[] args ) {
        List<String> ac = new ArrayList<>();
        Player player = (Player) sender;
        if ( args.length < 2 ) {
            ac.add( "player" );
            ac.add( "entity" );
            return ac;
        } else if ( args[ 0 ].contains( "entity" ) ) {
            for ( Entity entity : player.getWorld().getEntities() ) {
                String name = entity.getType().getName();
                if ( name.contains( args[ 1 ] ) && ! ac.contains( name ) ) {
                    ac.add( name );
                }
            }
        } else if ( args[ 0 ].contains( "player" ) ) {
            for ( Player plr : player.getServer().getOnlinePlayers() ) {
                if ( plr.getName().contains( args[ 1 ] ) ) {
                    ac.add( plr.getName() );
                }
            }
        }
        if ( args.length > 2 ) {
            return new ArrayList<>();
        }
        return ac;
    }
}