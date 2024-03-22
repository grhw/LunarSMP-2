package light.breeze.items.endingot;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;

public class EndIngot {
    public ItemStack createEndIngot() {
        ItemStack customItem = new ItemStack(Material.IRON_INGOT, 1);
        Damageable meta = (Damageable) customItem.getItemMeta();
        meta.setCustomModelData(9002005);
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "End Ingot");
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.DARK_BLUE + "" + ChatColor.ITALIC + "most useless ingot ever made");
        lore.add("");
        lore.add(ChatColor.GRAY + "When Used:");
        lore.add(ChatColor.GREEN + "nothing");
        meta.setLore(lore);
        customItem.setItemMeta(meta);
        return customItem;
    }
}