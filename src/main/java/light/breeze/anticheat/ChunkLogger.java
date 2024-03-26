package light.breeze.anticheat;

import light.breeze.utils.FileStorage;
import light.breeze.utils.Utils;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class ChunkLogger implements Listener {
    FileStorage fs = new FileStorage(Utils.getPlugin(), "chunk_log.yml");

    @EventHandler(priority = EventPriority.HIGH)
    public void logChunk( PlayerMoveEvent event ) {
        Chunk chunk = event.getPlayer().getLocation().getChunk();
        if (event.getFrom().distance(event.getTo()) > 0) {
            fs.store(chunk.getX() + "." + chunk.getZ() + "." + event.getPlayer().getName(), "visited");
        }
    }
}
