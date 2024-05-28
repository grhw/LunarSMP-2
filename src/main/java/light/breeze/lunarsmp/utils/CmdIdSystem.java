package light.breeze.lunarsmp.utils;

import org.bukkit.inventory.ItemStack;

public class CmdIdSystem {
    public static int toCMD( String name ) {
        return Math.abs( name.hashCode() );
    }

    public static boolean compareId( ItemStack item, String name ) {
        return ItemChecks.canHaveId( item ) && item.getItemMeta().getCustomModelData() == toCMD( name );
    }
}
