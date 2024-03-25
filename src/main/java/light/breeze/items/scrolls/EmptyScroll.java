package light.breeze.items.scrolls;

import light.breeze.CustomModelDatas;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;

public class EmptyScroll {
    public ItemStack createEmptyScrolls() {
        ItemStack customItem = new ItemStack(Material.FLOWER_BANNER_PATTERN, 1);
        Damageable meta = (Damageable) customItem.getItemMeta();
        meta.setCustomModelData(CustomModelDatas.getCustomModelData("empty_scroll"));
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Blank Scroll");
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "There's nothing in it.");
        lore.add("");
        lore.add(ChatColor.GRAY + "When Used:");
        lore.add(ChatColor.GREEN + "Nothing");
        meta.setLore(lore);
        customItem.setItemMeta(meta);
        return customItem;
    }

    public ItemStack createFireScroll() {
        ItemStack customItem = new ItemStack(Material.FLOWER_BANNER_PATTERN, 1);
        Damageable meta = (Damageable) customItem.getItemMeta();
        meta.setCustomModelData(CustomModelDatas.getCustomModelData("fire_scroll"));
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Scroll of Fire");
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.DARK_RED + "" + ChatColor.ITALIC + "P.K. FIRE!!");
        lore.add("");
        lore.add(ChatColor.GRAY + "When Used:");
        lore.add(ChatColor.GREEN + "Fire");
        meta.setLore(lore);
        customItem.setItemMeta(meta);
        return customItem;
    }
}