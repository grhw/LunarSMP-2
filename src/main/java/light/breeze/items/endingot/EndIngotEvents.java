package light.breeze.items.endingot;

import light.breeze.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;

public class EndIngotEvents implements Listener {
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