package light.breeze.items.burningaxe;

import light.breeze.LunarSMP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class BurningAxe {
    public ItemStack createBurningAxe() {
        ItemStack customItem = new ItemStack(Material.WARPED_FUNGUS_ON_A_STICK, 1);
        ItemMeta meta = customItem.getItemMeta();

        meta.setCustomModelData(9003001);
        meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Burning Axe");
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "Modes: Fireball/Machine Gun/Enderdragon");
        lore.add("");
        lore.add(ChatColor.GRAY + "When Right Clicked while Sneaking:");
        lore.add(ChatColor.BLUE + "Changes ability");
        lore.add(ChatColor.GRAY + "When Right Clicked:");
        lore.add(ChatColor.BLUE + "Shoots the selected ability");
        lore.add(ChatColor.GRAY + "When Attacks Shielded Receiver:");
        lore.add(ChatColor.BLUE + "Acts as an axe");

        meta.setLore(lore);
        customItem.setItemMeta(meta);

        return customItem;
    };
}