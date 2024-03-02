package light.breeze;

import light.breeze.commands.CustomGiveAutoCompleter;
import light.breeze.commands.Credits;
import light.breeze.commands.CustomGive;
import light.breeze.cosmetics.Cosmetics;
import light.breeze.items.Recipes;
import light.breeze.items.burningaxe.BurningAxeEvents;
import light.breeze.items.featherfalltotem.TotemOfFeatherfallEvents;
import light.breeze.items.wardenbound.WardenBoundEvents;
import light.breeze.items.withersword.WitherSwordEvents;
import light.breeze.recipes.Smelting;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class LunarSMP extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Loading LunarSMP");


        ////// Register Commands ////
        this.getCommand("lunarsmp").setExecutor(new Credits());
        this.getCommand("customgive").setExecutor(new CustomGive());
        this.getCommand("customgive").setTabCompleter(new CustomGiveAutoCompleter());


        ////// Register Events ////
        Bukkit.getPluginManager().registerEvents(new WardenBoundEvents(), this);
        Bukkit.getPluginManager().registerEvents(new BurningAxeEvents(), this);
        Bukkit.getPluginManager().registerEvents(new TotemOfFeatherfallEvents(), this);
        Bukkit.getPluginManager().registerEvents(new WitherSwordEvents(), this);

        Bukkit.getPluginManager().registerEvents(new Cosmetics(), this);


        ////// Register Recipes ////
        Recipes recipes = new Recipes();
        Smelting smeltrecipes = new Smelting();

        recipes.TOFRecipe(new NamespacedKey(this, "featherfall"));
        recipes.TophatRecipe(new NamespacedKey(this, "tophat"));

        smeltrecipes.Axolotl(new NamespacedKey(this, "axolotl"));
        smeltrecipes.Bonemeal(new NamespacedKey(this, "bonemeal"));
        smeltrecipes.RottenLeather(new NamespacedKey(this, "rotten_leather"));


        ////// Ascii Art ////
        getLogger().info("Loaded!" + lang.ascii_art);
        getLogger().info("Plugin by Gust");
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
