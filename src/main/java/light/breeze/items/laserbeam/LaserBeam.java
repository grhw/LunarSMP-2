package light.breeze.items.laserbeam;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;

public class LaserBeam {
    public ItemStack createLaserBeam() {
        ItemStack customItem = new ItemStack(Material.BOW, 1);
        Damageable meta = (Damageable) customItem.getItemMeta();
        meta.setCustomModelData(9002003);
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Laser Beam");
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.DARK_BLUE + "" + ChatColor.ITALIC + "its shoots shit");
        lore.add("");
        lore.add(ChatColor.GRAY + "When Used:");
        lore.add(ChatColor.GREEN + "IMMA FIRIN MAH LAIIZAHH");
        meta.setLore(lore);
        customItem.setItemMeta(meta);
        return customItem;
    }
}