package light.breeze.items.manabox;

import light.breeze.mana.ManaSystem;
import light.breeze.utils.CustomModelDatas;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ManaBoxEvents implements Listener {
    @EventHandler( priority = EventPriority.HIGH )
    public void attemptCatchEntity( PlayerFishEvent event ) {
        if ( CustomModelDatas.checkFor( event.getPlayer().getInventory().getItemInMainHand(), "echo_bow" ) ) {
            if (event.getState() == PlayerFishEvent.State.CAUGHT_ENTITY) {
                if ( !(event.getCaught() instanceof Player) ) {
                    event.getPlayer().sendMessage( event.getCaught().getUniqueId() );
                } else {
                    event.getPlayer().sendMessage( "you cant catch players dumbass" );
                }
            }
        }
    }
}
