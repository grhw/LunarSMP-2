package light.breeze.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomDurability {
    public static void add( ItemStack item, Integer change ) {
        ItemMeta bim = item.getItemMeta();
        String old = bim.getDisplayName();
        String[] num = old.split("\\[")[1].split("\\]")[0].split("/");
        bim.setDisplayName(old.split("\\[")[0] + "[" + ( Integer.parseInt(num[0]) + change ) + "/" + Integer.parseInt(num[1]) + "]");
        item.setItemMeta(bim);
        if (Integer.parseInt(num[0]) < 1) {
            item.setAmount(0);
        }
    }
}
