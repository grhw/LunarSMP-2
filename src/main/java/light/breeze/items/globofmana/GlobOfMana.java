package light.breeze.items.globofmana;

import light.breeze.utils.CustomModelDatas;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;

public class GlobOfMana {
    public ItemStack createGlobOfMana(int c) {
        ItemStack custom_item = new ItemStack( Material.ROTTEN_FLESH, 1 );
        Damageable meta = (Damageable) custom_item.getItemMeta();
        meta.setCustomModelData( CustomModelDatas.getCustomModelData( "glob_of_mana" ) );
        meta.setDisplayName( ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Glob Of Mana" );
        List<String> lore = new ArrayList<>();

        lore.add( ChatColor.DARK_BLUE + "" + ChatColor.ITALIC + "..can't you like.. cook it?" );
        lore.add( "" );
        lore.add( ChatColor.GRAY + "When Eaten:" );
        lore.add( ChatColor.GREEN + "Food" );
        meta.setLore( lore );
        custom_item.setItemMeta( meta );
        custom_item.setAmount( c );
        return custom_item;
    }
    public ItemStack createGlobOfMana() {
        return createGlobOfMana(1);
    }
}