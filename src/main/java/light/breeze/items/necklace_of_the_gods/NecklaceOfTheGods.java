package light.breeze.items.necklace_of_the_gods;

import light.breeze.utils.CustomModelDatas;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;

public class NecklaceOfTheGods {
    public ItemStack createNecklaceOfTheGods() {
        ItemStack custom_item = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        Damageable meta = (Damageable) custom_item.getItemMeta();
        meta.setCustomModelData(CustomModelDatas.getCustomModelData("necklace_of_the_gods"));
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Necklace of the Gods");
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.DARK_BLUE + "" + ChatColor.ITALIC + "Mana-fueled regen necklace.");
        lore.add("");
        lore.add(ChatColor.GRAY + "When Worn:");
        lore.add(ChatColor.GREEN + "Regenerates 1hp/10s");
        lore.add("");
        lore.add(ChatColor.GRAY + "When Regenerated:");
        lore.add(ChatColor.GREEN + "Uses 40 mana");
        meta.setLore(lore);
        custom_item.setItemMeta(meta);
        return custom_item;
    }
}
