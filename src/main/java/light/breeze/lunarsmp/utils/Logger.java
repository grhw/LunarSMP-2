package light.breeze.lunarsmp.utils;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class Logger {
    public static void log( String str ) {
        Bukkit.getLogger().log( Level.INFO, str );
    }

    public static void warn( String str ) {
        Bukkit.getLogger().log( Level.WARNING, str );
    }
}
