package light.breeze.anticheat;

import light.breeze.utils.FileStorage;
import light.breeze.utils.TickUtils;
import light.breeze.utils.Utils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class StopRedstoneClocks implements Listener {
    private final FileStorage fs;
    private final TickUtils tc;

    public StopRedstoneClocks() {
        this.fs = new FileStorage(Utils.getPlugin(), "redstoneclocks.yml");
        this.tc = new TickUtils();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void stopRedstoneClocks(BlockRedstoneEvent event) {
        if (event.getBlock().getType() == Material.REPEATER||event.getBlock().getType() == Material.OBSERVER||event.getBlock().getType() == Material.PISTON) {
            String chunk = event.getBlock().getChunk().toString();
            String res = this.fs.get(chunk);
            Integer tick = tc.tc.redstoneTicksAlive;
            //Utils.log(tick.toString());
            //Utils.log(res);
            if (res != null&&Math.abs(tick-Integer.parseInt(res)) < 4) {
                if (event.getNewCurrent() != 0) {
                    event.getBlock().getWorld().playSound(event.getBlock().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH,2,1);
                }
                event.setNewCurrent(0);
            } else {
                this.fs.store(chunk, tick.toString());
            }
        }
    }

    public class allowRedstone extends BukkitRunnable {
        private final String chunk;
        private final FileStorage fs;

        public allowRedstone(FileStorage fs, String chunk) {
            this.chunk = chunk;
            this.fs = fs;
        }

        @Override
        public void run() {
            this.fs.store(this.chunk,"yes");
        }
    }
}