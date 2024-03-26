package light.breeze.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class Smelting {
    public void Bonemeal( NamespacedKey key ) {
        FurnaceRecipe recipe = new FurnaceRecipe(key, new ItemStack(Material.BONE_MEAL), Material.BONE, 4f, 20);

        Bukkit.addRecipe(recipe);
    }

    public void Axolotl( NamespacedKey key ) {
        FurnaceRecipe recipe = new FurnaceRecipe(key, new ItemStack(Material.BUCKET), Material.AXOLOTL_BUCKET, 160f, 600);

        Bukkit.addRecipe(recipe);
    }

    public void RottenLeather( NamespacedKey key ) {
        FurnaceRecipe recipe = new FurnaceRecipe(key, new ItemStack(Material.LEATHER), Material.ROTTEN_FLESH, 2f, 40);

        Bukkit.addRecipe(recipe);
    }
}
