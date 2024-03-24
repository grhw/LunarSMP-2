package light.breeze.anticheat;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;

public class AntiArtifactRename implements Listener {
    @EventHandler
    public static void onInventoryClick(InventoryClickEvent e){
        if (e.getInventory() instanceof AnvilInventory) {
            AnvilInventory anvil = (AnvilInventory) e.getInventory();
            if (anvil.getContents()[0] != null&&anvil.getContents()[0].hasItemMeta()&&!(anvil.getRenameText().matches(ChatColor.stripColor(anvil.getContents()[0].getItemMeta().getDisplayName())))) {
                e.setCancelled(true);
                e.getView().getPlayer().sendMessage("You are not allowed to rename Artifacts!");
            }
        }
    }
}
