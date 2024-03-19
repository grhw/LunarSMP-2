package light.breeze.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class FileStorage {
    private final File file;
    private FileConfiguration config;

    public FileStorage(Plugin plugin, String filename) {
        this.file = new File(plugin.getDataFolder(), filename);
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void store(String key, String value) {
        config.set(key, value);
        save();
    }

    public String get(String key) {
        reload();
        if (config.contains(key)) {
            return config.getString(key);
        }
        return null;
    }

    private void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace(); // Handle save errors
        }
    }

    private void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }
}