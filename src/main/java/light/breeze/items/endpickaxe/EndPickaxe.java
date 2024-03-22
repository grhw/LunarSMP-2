package light.breeze.items.endpickaxe;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;

public class EndPickaxe {
    public ItemStack createEndPickaxe(String maxdurability) {
        ItemStack customItem = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        Damageable meta = (Damageable) customItem.getItemMeta();

        meta.setCustomModelData(9006005);
        meta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "End Pickaxe " + "[" + maxdurability + "/" + maxdurability + "]");
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.DARK_BLUE + "" + ChatColor.ITALIC + "base grief tool");
        lore.add("");
        lore.add(ChatColor.GRAY + "When Broken:");
        lore.add(ChatColor.GREEN + "well it breaks");
        lore.add(ChatColor.GRAY + "When Blocks Broken While On Mainhand:");
        lore.add(ChatColor.GREEN + "Breaks a 3x3 area");
        meta.setLore(lore);
        customItem.setItemMeta(meta);
        return customItem;
    }
}