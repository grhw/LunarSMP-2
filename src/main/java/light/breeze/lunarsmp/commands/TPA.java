package light.breeze.lunarsmp.commands;

import light.breeze.lunarsmp.utils.ServerUtils;
import light.breeze.lunarsmp.utils.TPARequest;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class TPA implements CommandExecutor {
    Map<Player, Map<Player,TPARequest>> targets = new HashMap<>();

    @Override
    public boolean onCommand( @NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args ) {
        targets.putIfAbsent( (Player) commandSender, new HashMap<>() );
        if (alias.contains( "accept" )||alias.contains( "decline" )) {
            TPARequest request;// = (TPARequest) targets.get( (Player) commandSender ).values().toArray()[0]; // actually doesnt work lmao
            if (args.length > 0) {
                request = targets.get( (Player) commandSender ).get( Bukkit.getPlayer( args[0] ) );
            } else {
                commandSender.sendMessage( "You need to add which player you're going to accept/deny." );
                return true;
            }
            if (alias.contains( "accept" )) {
                request.accept();
            } else if ( alias.contains( "decline" ) ) {
                request.decline();
            }
        } else {
            Player target = Bukkit.getPlayer( args[ 0 ] );
            targets.putIfAbsent( target, new HashMap<>() );
            TPARequest req;
            if ( alias.contains( "here" ) ) {
                req = new TPARequest( (Player) commandSender, target, false );
            } else {
                req = new TPARequest( (Player) commandSender, target, true );
            }
            targets.get( target ).put( (Player) commandSender, req );
        }
        return true;
    }


}
