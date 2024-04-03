package light.breeze.commands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class TestDummyEvents implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onUse( EntityDamageByEntityEvent event ) {
        if (event.getEntity().getCustomName().matches("Test Dummy")) {
            event.getDamager().sendMessage(event.getDamage() + " Damage Dealt");
            event.setDamage(0);
            event.setCancelled(true);
        }
    }
}
