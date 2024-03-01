package light.breeze.items.featherfalltotem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.List;

public class TotemOfFeatherfall {
    public ItemStack createTOF() {
        ItemStack customItem = new ItemStack(Material.WARPED_FUNGUS_ON_A_STICK, 1);
        ItemMeta meta = customItem.getItemMeta();
        meta.setCustomModelData(9002001);
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Totem " + ChatColor.YELLOW + "Of " + ChatColor.WHITE + "Featherfalling" );
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.WHITE + "This totem, adorned with " + ChatColor.BOLD + "feathers, " + ChatColor.YELLOW + "wheat, " + ChatColor.RESET + "" + ChatColor.WHITE + " and " + ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "delicate petals,");
        lore.add(ChatColor.WHITE + "offers " + ChatColor.GREEN + " protection " + ChatColor.WHITE + " from idk earth's gravity ig");
        lore.add("");
        lore.add(ChatColor.GRAY + "On fall:");
        lore.add(ChatColor.BLUE + "Feathers your falling");
        meta.setLore(lore);
        customItem.setItemMeta(meta);
        return customItem;
    }
}

