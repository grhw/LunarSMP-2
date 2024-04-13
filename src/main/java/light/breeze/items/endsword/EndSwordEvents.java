package light.breeze.items.endsword;

import light.breeze.mana.ManaSystem;
import light.breeze.utils.CustomModelDatas;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class EndSwordEvents implements Listener {
    private ManaSystem mana = new ManaSystem();

    @EventHandler( priority = EventPriority.HIGH )
    public void onUse( EntityDamageByEntityEvent event ) {
        if ( event.getDamager() instanceof Player ) {
            Player player = (Player) event.getDamager();
            ItemStack item = player.getInventory().getItemInMainHand();
            if ( CustomModelDatas.checkForWithCooldown( item, "end_sword", player ) && this.mana.checkManaWarn( player ) ) {
                player.setCooldown( item.getType(), 200 );
                event.getEntity().getWorld().createExplosion( event.getEntity().getLocation(), 2, true, true );
                this.mana.addMana( player, - 50 );
            }
        }
    }
}
