package light.breeze.cosmetics;

import light.breeze.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Cosmetics implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onUse( PlayerInteractEvent event ) {
        Player player = event.getPlayer();
        ItemStack hand = player.getEquipment().getItemInMainHand();
        if ( Utils.checkIfMeta( hand ) && Math.floor( hand.getItemMeta().getCustomModelData() / 1000000 ) == 8 ) {
            ItemStack[] currentStack = player.getInventory().getArmorContents();
            if ( currentStack[ 3 ] == null ) {
                ItemStack[] newArmor = new ItemStack[ 4 ];
                newArmor[ 3 ] = hand;
                newArmor[ 1 ] = currentStack[ 1 ];
                newArmor[ 2 ] = currentStack[ 2 ];
                newArmor[ 0 ] = currentStack[ 0 ];
                player.getInventory().setArmorContents( newArmor );
                hand.setAmount( hand.getAmount() - 1 );
            } else {
                player.sendMessage( "Remove the thing on your head first." );
            }
        }
    }
}
