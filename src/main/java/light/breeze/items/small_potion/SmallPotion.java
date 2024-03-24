package light.breeze.items.small_potion;

import light.breeze.CustomModelDatas;
import light.breeze.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.PotionMeta;

import java.util.ArrayList;
import java.util.List;

public class SmallPotion {
    public ItemStack createSmallPotion(Material mat) {
        ItemStack customItem = new ItemStack(mat, 1);
        Damageable meta = (Damageable) customItem.getItemMeta();

        meta.setCustomModelData(CustomModelDatas.getCustomModelData("small_potion"));
        String liquidName = "Oxygen";
        if (mat.name().split("_").length > 1) {
            liquidName = Utils.Capitalize(mat.name().toLowerCase().split("_")[0]);
        }
        meta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Small Potion of " + liquidName);
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + "No Effects");
        meta.setLore(lore);
        customItem.setItemMeta(meta);
        return customItem;
    }

    public ItemStack createSmallPotionWithFly() {
        ItemStack customItem = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) customItem.getItemMeta();

        meta.setColor(Color.WHITE);
        meta.setCustomModelData(CustomModelDatas.getCustomModelData("small_potion_fly"));
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        meta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Small Potion of Flight");
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.BLUE + "Flight (2:30)");
        meta.setLore(lore);
        customItem.setItemMeta(meta);
        return customItem;
    }
}
