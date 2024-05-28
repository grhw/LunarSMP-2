package light.breeze.lunarsmp.initialization;

import light.breeze.lunarsmp.anticheat.VPNLogger;
import light.breeze.lunarsmp.items.WardenBound;
import light.breeze.lunarsmp.utils.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class Events {
    public static void init() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        Plugin plugin = ServerUtils.getPlugin();

        pm.registerEvents( new VPNLogger(), plugin );
    }
}
