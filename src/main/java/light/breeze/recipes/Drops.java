package light.breeze.recipes;

import light.breeze.items.endingot.EndIngot;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class Drops implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void shulkerDrop(EntityDeathEvent e) {
        if (e.getEntity().getName().matches("Shulker")) {
            if (Math.random()*20 < 1) {
                Location loc = e.getEntity().getLocation();
                loc.getWorld().dropItem(loc,new EndIngot().createEndIngot());
            }
        }
    }
}