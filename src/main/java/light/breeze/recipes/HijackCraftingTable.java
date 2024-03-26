package light.breeze.recipes;

import light.breeze.items.endingot.EndIngot;
import light.breeze.items.endpickaxe.EndPickaxe;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class HijackCraftingTable implements Listener {
    public Map<ItemStack, ItemStack[]> recipes;

    public HijackCraftingTable() {
        this.recipes = new HashMap<>();
    }

    public void newRecipe( String[] rows, Map<String, ItemStack> mapping, ItemStack res ) {
        ItemStack[] item_matrix = new ItemStack[9];
        mapping.put(" ", null);
        for (int i = 0; i < 9; i++) {
            String k = String.valueOf(rows[(int) Math.floor(i / 3)].charAt(i % 3));
            item_matrix[i] = mapping.get(k);
        }

        this.recipes.put(res, item_matrix);
    }


    public void EndPickaxe() {
        String[] recipe = new String[3];
        recipe[0] = "PPP";
        recipe[1] = "E|E";
        recipe[2] = " | ";
        Map<String, ItemStack> mapping = new HashMap<>();
        mapping.put("P", new ItemStack(Material.PURPUR_BLOCK));
        mapping.put("E", new EndIngot().createEndIngot());
        mapping.put("|", new ItemStack(Material.END_ROD));

        newRecipe(recipe, mapping, new EndPickaxe().createEndPickaxe("1600"));
    }

    public Boolean Compare( ItemStack[] from, ItemStack[] compareTo ) {
        for (int i = 0; i < from.length; i++) {
            if (! ( compareTo[i] == from[i] || ( compareTo[i] != null && compareTo[i].isSimilar(from[i]) ) )) {
                return false;
            }
        }
        return true;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onCraft( PrepareItemCraftEvent event ) {
        for (ItemStack result : this.recipes.keySet()) {
            if (Compare(this.recipes.get(result), event.getInventory().getMatrix())) {
                event.getInventory().setResult(result);
            }
        }
    }
}
