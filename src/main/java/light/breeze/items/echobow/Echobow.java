package light.breeze.items.echobow;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.inventory.meta.tags.ItemTagType;

import java.util.ArrayList;
import java.util.List;

public class Echobow {
    public ItemStack createEchobow() {
        ItemStack customItem = new ItemStack(Material.BOW, 1);
        Damageable meta = (Damageable) customItem.getItemMeta();
        meta.setCustomModelData(9001001);
        meta.setDisplayName(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Echobow");
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.DARK_BLUE + "" + ChatColor.ITALIC + "bends time space and also space");
        lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "did i mention spsce");
        lore.add("");
        lore.add(ChatColor.GRAY + "When Used:");
        lore.add(ChatColor.GREEN + "i dont know? shoot??");
        meta.setLore(lore);
        customItem.setItemMeta(meta);
        return customItem;
    }
}