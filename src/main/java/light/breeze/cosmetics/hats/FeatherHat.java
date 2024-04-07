package light.breeze.cosmetics.hats;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class FeatherHat {
    public ItemStack createFeatherHat() {
        ItemStack customItem = new ItemStack( Material.WARPED_FUNGUS_ON_A_STICK, 1 );
        ItemMeta meta = customItem.getItemMeta();

        meta.setCustomModelData( 8001001 );
        meta.setDisplayName( "Feather hat" );
        List<String> lore = new ArrayList<>();

        lore.add( ChatColor.GRAY + "Cosmetic" );

        meta.setLore( lore );
        customItem.setItemMeta( meta );

        return customItem;
    }

    ;
}
