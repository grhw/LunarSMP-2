package light.breeze.commands;

import light.breeze.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class TestDummyEvents implements Listener {
    private long lastDamage = Utils.getTime();
    private long totalDamage = 0;

    @EventHandler(priority = EventPriority.HIGH)
    public void onUse( EntityDamageEvent event ) {
        if ( event.getEntity().getCustomName() != null && event.getEntity().getCustomName().startsWith( ChatColor.LIGHT_PURPLE + "Test Dummy" ) ) {
            if ( Utils.getTime() - lastDamage > 1 ) {
                totalDamage = 0;
            }
            lastDamage = Utils.getTime();
            totalDamage += (long) event.getDamage();
            event.getEntity().setCustomName( ChatColor.LIGHT_PURPLE + "Test Dummy: " + totalDamage );
            event.setDamage( 0 );
            //event.setCancelled(true);
        }
    }
}
