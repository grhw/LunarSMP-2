package light.breeze.items;

import light.breeze.LunarSMP;
import light.breeze.cosmetics.hats.TopHat;
import light.breeze.items.featherfalltotem.TotemOfFeatherfall;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public class Recipes {
    public void TOFRecipe(NamespacedKey key) {
        ShapedRecipe recipe = new ShapedRecipe(key, new TotemOfFeatherfall().createTOF());
        recipe.shape("#H#", "#P#", "###");
        recipe.setIngredient('H', Material.HAY_BLOCK);
        recipe.setIngredient('P', Material.PINK_PETALS);
        recipe.setIngredient('#', Material.FEATHER);
        Bukkit.addRecipe(recipe);
    }
    public void TophatRecipe(NamespacedKey key) {
        ShapedRecipe recipe = new ShapedRecipe(key, new TopHat().createTopHat());
        recipe.shape("###", "ccc", "#S#");
        recipe.setIngredient('c', Material.COPPER_INGOT);
        recipe.setIngredient('S', Material.STRING);
        recipe.setIngredient('#', Material.GRAY_WOOL);
        Bukkit.addRecipe(recipe);
    }
}
