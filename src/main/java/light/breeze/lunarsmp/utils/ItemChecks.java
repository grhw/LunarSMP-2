package light.breeze.lunarsmp.utils;

import org.bukkit.inventory.ItemStack;

public class ItemChecks {
    public static Boolean canHaveId( ItemStack item ) {
        return item.hasItemMeta() && item.getItemMeta().hasCustomModelData();
    }
}
