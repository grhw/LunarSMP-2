package light.breeze;

import light.breeze.utils.Utils;
import org.bukkit.inventory.ItemStack;

public class CustomModelDatas {
    public static int getCustomModelData(String name)
    {
        return Math.abs(name.hashCode());
    }
    public static boolean checkFor(ItemStack item, String name)
    {
        return Utils.checkIfMeta(item) &&item.getItemMeta().getCustomModelData() == getCustomModelData(name);
    }
}
