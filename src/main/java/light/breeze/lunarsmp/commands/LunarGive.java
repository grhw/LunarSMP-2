package light.breeze.lunarsmp.commands;

import light.breeze.lunarsmp.utils.ItemBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LunarGive implements CommandExecutor {
    @Override
    public boolean onCommand( @NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args ) {
        Player targetplayer;
        if ( args.length == 1 ) {
            targetplayer = (Player) commandSender;
            String key = args[ 0 ].toLowerCase();
            if ( ItemBuilder.items.containsKey( key ) ) {
                targetplayer.getInventory().addItem( ItemBuilder.items.get( key ) );
                commandSender.sendMessage( "Gave yourself " + args[ 0 ] );
                return true;
            } else {
                commandSender.sendMessage( args[ 0 ] + " is not an item" );
                return true;
            }
        }
        return false;
    }
}
