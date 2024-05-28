package light.breeze.lunarsmp.commands;

import light.breeze.lunarsmp.utils.ItemBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LunarGiveAutoComplete implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete( @NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args ) {
        if ( args.length == 1 ) {
            ArrayList<String> autoComplete = new ArrayList<>();
            for ( String item : ItemBuilder.items.keySet() ) {
                if ( item.contains( args[ 0 ].toLowerCase() ) ) {
                    autoComplete.add( item );
                }
            }
            return autoComplete;
        }
        return null;
    }
}
