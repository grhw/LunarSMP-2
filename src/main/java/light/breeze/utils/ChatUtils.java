package light.breeze.utils;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ChatUtils {
    public static BaseComponent[] formatText(String colorCode, String text) {
        return TextComponent.fromLegacyText(ChatColor.stripColor(colorCode) + text);
    }
}
