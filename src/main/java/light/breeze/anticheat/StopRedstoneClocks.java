package light.breeze.anticheat;

import light.breeze.utils.Utils;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

import java.util.HashMap;
import java.util.Map;

public class StopRedstoneClocks implements Listener {
    private final Map<Chunk,Long> chunkTime;
    private final double minTime;

    public StopRedstoneClocks() {
        this.chunkTime = new HashMap<>();
        this.minTime = 0.1;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void RedstoneClocks(BlockRedstoneEvent event) {
        if (event.getBlock().getType() == Material.REPEATER) {
            Chunk chunk = event.getBlock().getChunk();
            if (chunkTime.containsKey(chunk)&&Math.abs(chunkTime.get(chunk)-Utils.getTime()) < this.minTime) {
                if (event.getNewCurrent() != 0) {
                    event.getBlock().getWorld().playSound(event.getBlock().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH,2,1);
                }
                event.setNewCurrent(0);
            } else {
                chunkTime.put(chunk,Utils.getTime());
            }
        }
    }
}