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

public class StopRedStoneClocks implements Listener {
    private final Map<Chunk,Long> chunk_time;
    private final double minTime;

    public StopRedStoneClocks() {
        this.chunk_time = new HashMap<>();
        this.minTime = 0.1;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void RedStoneClocks( BlockRedstoneEvent event) {
        if (event.getBlock().getType() == Material.REPEATER) {
            Chunk chunk = event.getBlock().getChunk();
            if (chunk_time.containsKey(chunk)&&Math.abs(chunk_time.get(chunk)-Utils.getTime()) < this.minTime) {
                if (event.getNewCurrent() != 0) {
                    event.getBlock().getWorld().playSound(event.getBlock().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH,2,1);
                }
                event.setNewCurrent(0);
            } else {
                chunk_time.put(chunk,Utils.getTime());
            }
        }
    }
}