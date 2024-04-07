package light.breeze.items.scrolls;

import light.breeze.utils.CustomModelDatas;
import light.breeze.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.List;

public class ScrollEvents implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onUse( PlayerInteractEvent event ) {
        if ( CustomModelDatas.checkFor( event.getItem(), "fire_scroll" ) ) {
            Location center_loc = event.getPlayer().getLocation();
            List<LivingEntity> targets = Utils.getEntitiesInRadius( center_loc, 10 );
            for ( int i = 0; i < targets.size(); i++ ) {
                Vector dir = targets.get( i ).getLocation().subtract( center_loc ).toVector().normalize();
                event.getPlayer().spawnParticle( Particle.FLAME, center_loc, 1, dir );
            }
        }
    }
}
