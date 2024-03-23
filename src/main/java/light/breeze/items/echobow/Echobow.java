package light.breeze.items.echobow;

import light.breeze.CustomModelDatas;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;

public class Echobow {
    public ItemStack createEchobow(String ammo) {
        ItemStack customItem = new ItemStack(Material.BOW, 1);
        Damageable meta = (Damageable) customItem.getItemMeta();
        meta.setCustomModelData(CustomModelDatas.getCustomModelData("echo_bow"));
        meta.setDisplayName(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Echobow [" + ammo + "/" + ammo + "]");
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