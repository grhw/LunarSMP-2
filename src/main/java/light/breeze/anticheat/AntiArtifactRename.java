package light.breeze.anticheat;

import light.breeze.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class AntiArtifactRename implements Listener {
    @EventHandler
    public static void onInventoryClick( InventoryClickEvent e ) {
        if ( e.getInventory() instanceof AnvilInventory anvil_inventory ) {
            if ( anvil_inventory.getContents()[0] != null ) {
                if ( Utils.checkIfMeta( anvil_inventory.getContents()[0] ) ) {
                    //e.setCancelled(true);
                    if ( e.getRawSlot() == 2 ) {
                        ItemMeta im = e.getCurrentItem().getItemMeta();
                        im.setDisplayName( anvil_inventory.getContents()[0].getItemMeta().getDisplayName() );
                        e.getCurrentItem().setItemMeta( im );
                    }
                }
            }
        }
    }
}
