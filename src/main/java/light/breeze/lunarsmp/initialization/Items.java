package light.breeze.lunarsmp.initialization;

import light.breeze.lunarsmp.items.WardenBound;
import light.breeze.lunarsmp.utils.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class Items {
    public static void init() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        Plugin plugin = ServerUtils.getPlugin();

        WardenBound.create();
        pm.registerEvents( new WardenBound(), plugin );
    }
}
