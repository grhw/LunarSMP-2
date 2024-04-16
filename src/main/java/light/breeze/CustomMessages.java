package light.breeze;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CustomMessages implements Listener {
    @EventHandler( priority = EventPriority.NORMAL )
    public void onJoin( PlayerJoinEvent event ) {
        event.setJoinMessage( ChatColor.LIGHT_PURPLE + event.getPlayer().getName() + " joined the server !!" );
        //event.getPlayer().getServer().broadcastMessage();
        event.getPlayer().sendMessage( lang.ascii_art_2 );
    }

    @EventHandler( priority = EventPriority.NORMAL )
    public void onLeave( PlayerQuitEvent event ) {
        event.setQuitMessage( ChatColor.DARK_PURPLE + event.getPlayer().getName() + " left the server.." );
        //event.getPlayer().getServer().broadcastMessage();
    }

    @EventHandler( priority = EventPriority.NORMAL )
    public void onChat( AsyncPlayerChatEvent event ) {
        event.setFormat( PlaceholderAPI.setPlaceholders( event.getPlayer(), "%betterteams_color%" ) + event.getPlayer().getName() + ChatColor.RESET + ": " + event.getMessage() );
    }
}
