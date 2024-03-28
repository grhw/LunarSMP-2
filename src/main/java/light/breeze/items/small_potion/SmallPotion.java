package light.breeze.items.small_potion;

import light.breeze.utils.CustomModelDatas;
import light.breeze.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

import java.util.ArrayList;
import java.util.List;

public class SmallPotion {
    public ItemStack createSmallPotion( Material mat ) {
        ItemStack custom_item = new ItemStack(mat, 1);
        ItemMeta meta = custom_item.getItemMeta();

        meta.setCustomModelData(CustomModelDatas.getCustomModelData("small_potion"));
        String liquid_name = "Oxygen";
        if (mat.name().split("_").length > 1) {
            liquid_name = Utils.Capitalize(mat.name().toLowerCase().split("_")[0]);
        }
        meta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Small Potion of " + liquid_name);
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + "No Effects");
        meta.setLore(lore);
        custom_item.setItemMeta(meta);
        return custom_item;
    }

    public ItemStack createSmallPotionWithFly() {
        ItemStack custom_item = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) custom_item.getItemMeta();

        meta.setColor(Color.WHITE);
        meta.setCustomModelData(CustomModelDatas.getCustomModelData("small_potion_fly"));
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        meta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Small Potion of Flight");
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.BLUE + "Flight (2:30)");
        meta.setLore(lore);
        custom_item.setItemMeta(meta);
        return custom_item;
    }

    public ItemStack createSmallPotionWithObsidian() {
        ItemStack custom_item = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) custom_item.getItemMeta();

        meta.setColor(Color.WHITE);
        meta.setCustomModelData(CustomModelDatas.getCustomModelData("small_potion_obsidian"));
        //meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        meta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Small Potion of Obsidian");
        List<String> lore = new ArrayList<>();

        //lore.add(ChatColor.GRAY + "No Effects");
        meta.setLore(lore);
        custom_item.setItemMeta(meta);
        return custom_item;
    }

    public ItemStack createSmallPotionWithVoidProtection() {
        ItemStack custom_item = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) custom_item.getItemMeta();

        meta.setColor(Color.WHITE);
        meta.setCustomModelData(CustomModelDatas.getCustomModelData("small_potion_void_protection"));
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        meta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Small Potion of Void Protection");
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.BLUE + "Void Protection (10:00)");
        meta.setLore(lore);
        custom_item.setItemMeta(meta);
        return custom_item;
    }

    public ItemStack createSmallPotionWithMana() {
        ItemStack custom_item = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) custom_item.getItemMeta();

        meta.setColor(Color.WHITE);
        meta.setCustomModelData(CustomModelDatas.getCustomModelData("small_potion_of_mana"));
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        meta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Small Potion of Mana");
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.BLUE + "Instant Mana 10");
        meta.setLore(lore);
        custom_item.setItemMeta(meta);
        return custom_item;
    }
}
