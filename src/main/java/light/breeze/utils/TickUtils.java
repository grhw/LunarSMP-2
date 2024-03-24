package light.breeze.utils;

import org.bukkit.scheduler.BukkitRunnable;


public class TickUtils {
    public TickCounter tc;
    public TickUtils() {
        this.tc = new TickCounter();
        this.tc.runTaskTimerAsynchronously(Utils.getPlugin(),0,2);
    }

    public class TickCounter extends BukkitRunnable {
        public Integer redstoneTicksAlive;

        public TickCounter() {
            this.redstoneTicksAlive = 1;
        }

        @Override
        public void run() {
            this.redstoneTicksAlive += 1;
        }
    }
}
