package light.breeze.lunarsmp.utils;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeBuilder {
    public static Map<String, List<String>> recipes = new HashMap<>();
    public static Map<String, List<String>> inject_recipes = new HashMap<>();

    public static void addRawRecipe( String id, String first, String second, String third, Map<String, ItemStack> customRecipeMap ) {
        List<String> recipe = new ArrayList<>();
        recipe.add( first );
        recipe.add( second );
        recipe.add( third );
        inject_recipes.put( id, recipe );
    }
}
