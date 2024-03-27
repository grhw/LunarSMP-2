package light.breeze.items.spearofjustice;

import light.breeze.utils.CustomModelDatas;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SpearOfJustice {
    public ItemStack createSpearOfJustice() {
        ItemStack custom_item = new ItemStack(Material.TRIDENT, 1);
        ItemMeta meta = custom_item.getItemMeta();

        meta.setCustomModelData(CustomModelDatas.getCustomModelData("spear_of_justice"));

        meta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Spear of Justice");
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.DARK_PURPLE + "Uses 100 Mana.");

        meta.setLore(lore);
        custom_item.setItemMeta(meta);
        return custom_item;
    }
}
