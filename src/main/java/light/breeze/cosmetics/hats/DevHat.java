package light.breeze.cosmetics.hats;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DevHat {
    public ItemStack createDevHat() {
        ItemStack customItem = new ItemStack( Material.WARPED_FUNGUS_ON_A_STICK, 1 );
        ItemMeta meta = customItem.getItemMeta();

        meta.setCustomModelData( 8001002 );
        meta.setDisplayName( ChatColor.GRAY + "" + ChatColor.BOLD + "Dev hat" );
        List<String> lore = new ArrayList<>();

        lore.add( ChatColor.GRAY + "Why have this?" );

        meta.setLore( lore );
        customItem.setItemMeta( meta );

        return customItem;
    }

}
