package light.breeze.lunarsmp.initialization;

import light.breeze.lunarsmp.commands.LunarGive;
import light.breeze.lunarsmp.commands.LunarGiveAutoComplete;
import light.breeze.lunarsmp.commands.TPA;
import light.breeze.lunarsmp.utils.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands {
    public static void init() {
        PluginManager pm = Bukkit.getPluginManager();
        JavaPlugin plugin = ServerUtils.getPlugin();

        plugin.getCommand( "lunar_give" ).setExecutor( new LunarGive() );
        plugin.getCommand( "lunar_give" ).setTabCompleter( new LunarGiveAutoComplete() );

        plugin.getCommand( "tpa" ).setExecutor( new TPA() );

    }
}
