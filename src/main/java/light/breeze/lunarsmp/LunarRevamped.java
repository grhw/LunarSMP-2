package light.breeze.lunarsmp;

import light.breeze.lunarsmp.initialization.Commands;
import light.breeze.lunarsmp.initialization.Items;
import org.bukkit.plugin.java.JavaPlugin;

public final class LunarRevamped extends JavaPlugin {

    @Override
    public void onEnable() {
        Items.init();
        Commands.init();

        new InfoBar.Updater().runTaskTimer( this, 20, 20 );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
