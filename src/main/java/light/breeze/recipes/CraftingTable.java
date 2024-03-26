package light.breeze.recipes;

import light.breeze.cosmetics.hats.TopHat;
import light.breeze.items.echobow.EchoBow;
import light.breeze.items.featherfalltotem.TotemOfFeatherFall;
import light.breeze.items.small_potion.SmallPotion;
import light.breeze.items.withersword.WitherSword;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftingTable {
    public void TOFRecipe(NamespacedKey key) {
        ShapedRecipe recipe = new ShapedRecipe(key, new TotemOfFeatherFall().createTOF());
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

    public void Echobow(NamespacedKey key) {
        ShapedRecipe recipe = new ShapedRecipe(key, new EchoBow().createEchoBow("50"));
        recipe.shape("#E ", "# E", "#E ");
        recipe.setIngredient('E', Material.ECHO_SHARD);
        recipe.setIngredient('#', Material.AMETHYST_SHARD);
        Bukkit.addRecipe(recipe);
    }

    public void Witherbane(NamespacedKey key) {
        ShapedRecipe recipe = new ShapedRecipe(key, new WitherSword().createWitherSword());
        recipe.shape(" #W", "NDX", "XXX");
        recipe.setIngredient('#', Material.FIRE_CHARGE);
        recipe.setIngredient('W', Material.WITHER_SKELETON_SKULL);
        recipe.setIngredient('N', Material.NETHER_STAR);
        recipe.setIngredient('D', Material.DIAMOND_SWORD);
        recipe.setIngredient('X', Material.NETHERRACK);
        Bukkit.addRecipe(recipe);
    }

    public void EchoshardDuplication(NamespacedKey key) {
        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.ECHO_SHARD,2));
        recipe.shape("iA", " E");
        recipe.setIngredient('i', Material.IRON_INGOT);
        recipe.setIngredient('A', Material.AMETHYST_SHARD);
        recipe.setIngredient('E', Material.ECHO_SHARD);
        Bukkit.addRecipe(recipe);
    }


    public void SmallPotion(NamespacedKey key) {
        ShapedRecipe recipe = new ShapedRecipe(key, new SmallPotion().createSmallPotion(Material.BUCKET));
        recipe.shape(" o ", "g g"," g ");
        recipe.setIngredient('g', Material.GLASS_PANE);
        recipe.setIngredient('o', Material.OAK_BUTTON);
        Bukkit.addRecipe(recipe);
    }
}
