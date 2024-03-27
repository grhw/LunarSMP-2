package light.breeze.items.withersword;

import light.breeze.utils.CustomModelDatas;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;

public class WitherSword {
    public ItemStack createWitherSword() {
        ItemStack custom_item = new ItemStack(Material.IRON_SWORD, 1);
        Damageable meta = (Damageable) custom_item.getItemMeta();
        meta.setCustomModelData(CustomModelDatas.getCustomModelData("witherbane_sword"));
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Witherbane Sword [50/50]");
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.DARK_BLUE + "" + ChatColor.ITALIC + "Infused a wither into your sword.");
        lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "It's cool and all, but did you REALLY have to break the sword");
        lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "JUST to shove the nether star in? Then glue it with.. netherrack??");
        lore.add("");
        lore.add(ChatColor.GRAY + "When Used:");
        lore.add(ChatColor.GREEN + "Shoots a wither-inducing projectile");
        lore.add(ChatColor.GRAY + "Because of semi-broken sword:");
        lore.add(ChatColor.RED + "Does less damage");
        meta.setLore(lore);
        custom_item.setItemMeta(meta);
        return custom_item;
    }
}