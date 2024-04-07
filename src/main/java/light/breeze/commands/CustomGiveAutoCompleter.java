package light.breeze.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomGiveAutoCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete( CommandSender sender, Command command, String alias, String[] arguments ) {
        if ( command.getName().contains( "custom_give" ) ) {
            CustomGive cg = new CustomGive();
            Map<String, ItemStack> ci = cg.getCustomItems();
            List<String> ar = new ArrayList<>( ci.keySet() );
            List<String> finalac = new ArrayList<>();
            for ( int i = 0; i < ar.size(); i += 1 ) {
                if ( arguments.length < 2 && ar.get( i ).contains( arguments[ 0 ] ) ) {
                    finalac.add( ar.get( i ) );
                }
            }
            return finalac;

        }
        return null;
    }
}