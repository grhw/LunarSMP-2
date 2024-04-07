package light.breeze.items.endsword;

import light.breeze.utils.CustomModelDatas;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;

public class EndSword {
    public ItemStack createEndSword() {
        ItemStack custom_item = new ItemStack( Material.NETHERITE_SWORD, 1 );
        Damageable meta = (Damageable) custom_item.getItemMeta();
        meta.setCustomModelData( CustomModelDatas.getCustomModelData( "end_sword" ) );
        meta.setDisplayName( ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "End Sword" );
        List<String> lore = new ArrayList<>();

        lore.add( ChatColor.DARK_BLUE + "" + ChatColor.ITALIC + "'make it go kaboom' - manolo" );
        lore.add( "" );
        lore.add( ChatColor.GRAY + "When Hit:" );
        lore.add( ChatColor.GREEN + "fucking combusts" );
        meta.setLore( lore );
        custom_item.setItemMeta( meta );
        return custom_item;
    }
}
