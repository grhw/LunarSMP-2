package light.breeze.items.manabox;

import light.breeze.utils.CustomModelDatas;
import light.breeze.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntitySnapshot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SpawnEggMeta;

import java.util.ArrayList;
import java.util.List;

public class FilledManaBox {
    public ItemStack createFilledManaBox( EntitySnapshot entitySnapshot, String name ) {
        ItemStack custom_item = new ItemStack( Material.COD_SPAWN_EGG, 1 );
        SpawnEggMeta meta = (SpawnEggMeta) custom_item.getItemMeta();
        meta.setCustomModelData( CustomModelDatas.getCustomModelData( "mana_box" ) );
        meta.setDisplayName( ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Mana Box" );
        meta.setSpawnedEntity( entitySnapshot );
        List<String> lore = new ArrayList<>();

        lore.add( ChatColor.WHITE + Utils.Capitalize( name + " x1" ) );
        lore.add( ChatColor.DARK_BLUE + "" + ChatColor.ITALIC + "there's some mf in there!" );
        lore.add( "" );
        lore.add( ChatColor.GRAY + "When Used:" );
        lore.add( ChatColor.GREEN + "Spawns the entity." );
        meta.setLore( lore );
        custom_item.setItemMeta( meta );
        return custom_item;
    }
}