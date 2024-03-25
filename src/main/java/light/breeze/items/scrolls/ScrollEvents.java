package light.breeze.items.scrolls;

import light.breeze.CustomModelDatas;
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

public class ScrollEvents  implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onUse(PlayerInteractEvent event) {
        if (CustomModelDatas.checkFor(event.getItem(), "fire_scroll")) {
            Location centLoc = event.getPlayer().getLocation();
            List<LivingEntity> targ = Utils.getEntitiesInRadius(centLoc,10);
            for (int i = 0; i < targ.size(); i++) {
                Vector dir = targ.get(i).getLocation().subtract(centLoc).toVector().normalize();
                event.getPlayer().spawnParticle(Particle.FLAME,centLoc,1,dir);
            }
        }
    }
}
