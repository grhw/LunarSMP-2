package light.breeze.lunarsmp.utils;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemBuilder {
    public static Map<String, ItemStack> items = new HashMap<>();


    public static void addRawItem( String id, ItemStack item ) {
        items.put( id, item );
    }

    public static ItemStack createItem( Boolean autoAdd, String id, Material baseItem, TextComponent name, ArrayList<TextComponent> lore, int count, ArrayList<ItemFlag> flags ) {
        ItemStack item = new ItemStack( baseItem, count );
        ItemMeta meta = item.getItemMeta();

        for ( ItemFlag flag : flags ) {
            meta.addItemFlags( flag );
        }
        meta.displayName( name );
        meta.setCustomModelData( CmdIdSystem.toCMD( id ) );
        meta.lore( lore );
        if ( autoAdd ) {
            addRawItem( id, item );
        }

        item.setItemMeta( meta );

        return item;
    }

    public static ItemStack createItem( Boolean autoAdd, String id, Material baseItem, TextComponent name, ArrayList<TextComponent> lore, int count ) {
        return createItem( autoAdd, id, baseItem, name, lore, count, new ArrayList<>() );
    }

    public static ItemStack createItem( Boolean autoAdd, String id, Material baseItem, TextComponent name, ArrayList<TextComponent> lore ) {
        return createItem( autoAdd, id, baseItem, name, lore, 1 );
    }

    public static ItemStack createItem( Boolean autoAdd, String id, Material baseItem, TextComponent name ) {
        return createItem( autoAdd, id, baseItem, name, new ArrayList<>() );
    }
}
