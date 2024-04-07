package light.breeze.utils;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

public class ChatUtils {
    public static BaseComponent[] formatText( String color_code, String text ) {
        return TextComponent.fromLegacyText( ChatColor.stripColor( color_code ) + text );
    }
}
