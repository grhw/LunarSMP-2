package light.breeze.anticheat;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;


public class AntiFly implements Listener {
    @EventHandler( priority = EventPriority.HIGH )
    public void playerMove( PlayerMoveEvent event ) {
        Location old_pos = event.getFrom();
        Location new_pos = event.getTo();
        //if (oldPos.distance(newPos) > 80/event.getPlayer().getServer().getServerTickManager().getTickRate()) {
        //    event.getPlayer().teleport(oldPos);
        //}
        if ( ! event.getPlayer().isGliding() && old_pos.getY() - new_pos.getY() < - 0.5 && event.getPlayer().getVelocity().getY() > - 0.1 ) {
            event.getPlayer().setVelocity( new Vector( 0, 0, 0 ) );
            event.getPlayer().teleport( old_pos );
        }
    }
}
