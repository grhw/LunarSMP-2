package light.breeze.block.endtable;

import light.breeze.utils.BlockUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class EndTable {
    public class Events implements Listener {
        @EventHandler(priority= EventPriority.HIGH)
        public void customBlockBroken(BlockBreakEvent event) {
            String blockid = BlockUtils.getCustomBlockLocations().get(event.getBlock().getLocation().toString());
            if (blockid == "EndTable") {
                
            }
        }
    }
}
