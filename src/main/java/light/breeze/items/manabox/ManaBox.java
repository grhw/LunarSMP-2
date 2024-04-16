package light.breeze.items.manabox;

import light.breeze.utils.CustomModelDatas;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;

public class ManaBox {
    public ItemStack createManaBox() {
        ItemStack custom_item = new ItemStack( Material.SLIME_BALL, 1 );
        Damageable meta = (Damageable) custom_item.getItemMeta();
        meta.setCustomModelData( CustomModelDatas.getCustomModelData( "mana_box" ) );
        meta.setDisplayName( ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Mana Box" );
        List<String> lore = new ArrayList<>();

        lore.add( ChatColor.DARK_BLUE + "" + ChatColor.ITALIC + "pokemon ball" );
        lore.add( "" );
        lore.add( ChatColor.GRAY + "When Used:" );
        lore.add( ChatColor.GREEN + "Throws the box and captures any nearby entity" );
        meta.setLore( lore );
        custom_item.setItemMeta( meta );
        return custom_item;
    }
}
