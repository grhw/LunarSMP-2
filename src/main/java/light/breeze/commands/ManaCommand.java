package light.breeze.commands;

import light.breeze.items.globofmana.GlobOfMana;
import light.breeze.lang;
import light.breeze.mana.ManaSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ManaCommand implements CommandExecutor {
    public ManaSystem mana;

    public ManaCommand() {
        this.mana = new ManaSystem();
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String s, String[] args ) {
        Player player = (Player) sender;
        if ( args.length > 0 && args[0] != null && args[0].matches( "sacrifice" ) ) {
            this.mana.addMana( player, - 25 );
            player.getInventory().addItem( new GlobOfMana().createGlobOfMana() );
        }
        sender.sendMessage( lang.notify_mana.replace( "$1", this.mana.getMana( player ) + "" ) );
        return true;
    }
}