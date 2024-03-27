package light.breeze.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CustomModelDatas {
    public static int getCustomModelData( String name ) {
        return Math.abs(name.hashCode());
    }

    public static boolean checkFor( ItemStack item, String name ) {
        return Utils.checkIfMeta(item) && item.getItemMeta().getCustomModelData() == getCustomModelData(name);
    }

    public static boolean checkForWithCooldown( ItemStack item, String name, Player player ) {
        return checkFor(item, name) && ( ( ! player.hasCooldown(item.getType()) ) || player.getCooldown(item.getType()) < 1 );
    }
}
